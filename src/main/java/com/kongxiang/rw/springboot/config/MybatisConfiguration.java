package com.kongxiang.rw.springboot.config;

import com.kongxiang.rw.springboot.configdb.DataSourceType;
import com.kongxiang.rw.springboot.configdb.MyAbstractRoutingDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.*;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huguoju on 2016/12/28.
 * 配置mybatis
 */
@Slf4j
@Configuration
@ConditionalOnClass({EnableTransactionManagement.class})
@Import({ DataBaseConfiguration.class})
@MapperScan(basePackages={"com.kongxiang.rw.springboot.dao.mapper"},sqlSessionFactoryRef ="sqlSessionFactory" )
public class MybatisConfiguration {

  @Value("${datasource.readSize}")
  private String dataSourceSize;

  @Autowired
  @Qualifier("writeDataSource")
  private DataSource writeDataSource;
  @Autowired
  @Qualifier("readDataSources")
  private List<DataSource> readDataSources;

  //XxxMapper.xml文件所在路径
  @Value("${mybatis.mapperLocations}")
  private String mapperLocations;
  //  加载全局的配置文件
  @Value("${mybatis.mapperLocations.configLocation}")
  private String configLocation;
//  //  类型别名
  @Value("${mybatis.mapperLocations.typeAliasesPackage}")
  private String typeAliasesPackage;

  @Bean
  @ConditionalOnMissingBean
  public SqlSessionFactory sqlSessionFactory() throws Exception {
    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(roundRobinDataSouceProxy());
    // 直接注册
//    typeAliasesPackage：它一般对应我们的实体类所在的包，
//    这个时候会自动取对应包中不包括包名的简单类名作为包括包名的别名。
//    多个package之间可以用逗号或者分号等来进行分隔
    sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
        .getResources(mapperLocations));
    sqlSessionFactoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource(configLocation));
    sqlSessionFactoryBean.setTypeAliasesPackage(typeAliasesPackage);
    sqlSessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
    return sqlSessionFactoryBean.getObject();
  }

  @Bean(name = "sqlSessionTemplate")
  public SqlSessionTemplate sqlSessionTemplate() throws Exception {
    // 使用上面配置的Factory
    SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory());
    return template;
  }
  /**
   * 有多少个数据源就要配置多少个bean
   * @return
   */
  @Bean(name="roundRobinDataSouceProxy")
  public AbstractRoutingDataSource roundRobinDataSouceProxy() {
    int size = Integer.parseInt(dataSourceSize);
    MyAbstractRoutingDataSource proxy = new MyAbstractRoutingDataSource(size);
    Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
    // 写
    targetDataSources.put(DataSourceType.write.getType(),writeDataSource);
    //多个读数据库时
    for (int i = 0; i < size; i++) {
      targetDataSources.put(DataSourceType.read.getType()+i, readDataSources.get(i));
    }
    // 设置默认库
    proxy.setDefaultTargetDataSource(writeDataSource);
    // 设置数据源
    proxy.setTargetDataSources(targetDataSources);
    return proxy;
  }

}

