package com.kongxiang.rw.springboot.configdb;

import lombok.extern.slf4j.Slf4j;

/**
 * @version 1.0
 * @description:
 * @projectName: com.kongxiang.rw.springboot.configdb
 * @className: springboot
 * @author:谭农春
 * @createTime:2018/5/5 11:17
 */
@Slf4j
public class DataSourceContextHolder {

  private static final ThreadLocal<String> local = new ThreadLocal<String>();

  public static ThreadLocal<String> getLocal() {
    return local;
  }

  /**
   * 读可能是多个库
   */
  public static void read() {

    local.set(DataSourceType.read.getType());
  }

  /**
   * 写只有一个库
   */
  public static void write() {
    log.debug("writewritewrite");
    local.set(DataSourceType.write.getType());
  }

  public static String getJdbcType() {
    return local.get();
  }
}
