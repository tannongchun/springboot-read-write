package com.kongxiang.rw.springboot.configdb;

import lombok.Getter;

/**
 * @version 1.0
 * @description:
 *      读写数据库分离
 * @projectName: com.kongxiang.rw.springboot.config
 * @className: springboot
 * @author:谭农春
 * @createTime:2018/5/5 11:01
 */
public enum DataSourceType {
  read("read", "从库"),
  write("write", "主库");
  @Getter
  private String type;
  @Getter
  private String name;

  DataSourceType(String type, String name) {
    this.type = type;
    this.name = name;
  }
}
