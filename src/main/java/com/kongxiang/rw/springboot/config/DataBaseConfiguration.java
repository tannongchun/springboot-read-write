package com.kongxiang.rw.springboot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @description:
 * @projectName: com.kongxiang.rw.springboot.config
 * @className: springboot
 * @author:谭农春
 * @createTime:2018/5/5 10:35
 */

@Slf4j
@Configuration
@PropertySource("classpath:mybatis.properties")
public class DataBaseConfiguration {
//
//  @Value("${spring.datasource.type}")
//  private Class<? extends DataSource> dataSourceType;

  @Bean(name="writeDataSource")
  @Primary
  @ConfigurationProperties(prefix = "spring.datasource")
  public DataSource writeDataSource() {
    log.info("-------------------- writeDataSource init ---------------------");
    return DataSourceBuilder.create().build();
  }
  /**
   * 有多少个从库就要配置多少个
   * @return
   */
  @Bean(name = "readDataSource1")
  @ConfigurationProperties(prefix = "spring.slave")
  public DataSource readDataSourceOne(){
    log.info("-------------------- readDataSourceOne init ---------------------");
    return DataSourceBuilder.create().build();
  }

  @Bean(name = "readDataSource2")
  @ConfigurationProperties(prefix = "spring.read2")
  public DataSource readDataSourceTwo() {
    log.info("-------------------- readDataSourceTwo init ---------------------");
    return DataSourceBuilder.create().build();
  }

  // 合并数据源
  @Bean("readDataSources")
  public List<DataSource> readDataSources(){
    List<DataSource> dataSources=new ArrayList<>();
    dataSources.add(readDataSourceOne());
    dataSources.add(readDataSourceTwo());
    return dataSources;
  }
}
