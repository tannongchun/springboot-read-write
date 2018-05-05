package com.kongxiang.rw.springboot.configdb;


import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @version 1.0
 * @description:
 * @projectName: com.kongxiang.rw.springboot.configdb
 * @className: springboot
 * @author:谭农春
 * @createTime:2018/5/5 11:19
 */
public class MyAbstractRoutingDataSource extends AbstractRoutingDataSource {
  private final int dataSourceNumber;
  private AtomicInteger count = new AtomicInteger(0);

  public MyAbstractRoutingDataSource(int dataSourceNumber) {
    this.dataSourceNumber = dataSourceNumber;
  }

  @Override
  protected Object determineCurrentLookupKey() {
    String typeKey = DataSourceContextHolder.getJdbcType();
    // 返回write
    if (DataSourceType.write.getType().equals(typeKey)){
      return DataSourceType.write.getType();
    }
    // 读 简单负载均衡
    int number = count.getAndAdd(1);
    int lookupKey = number % dataSourceNumber;
    return DataSourceType.read.getType()+lookupKey;

  }
}

