package com.kongxiang.rw.springboot.entity;

import java.io.Serializable;

/**
 * @version 1.0
 * @description:
 * @projectName: com.kongxiang.entity
 * @className: springmvc
 * @author:谭农春
 * @createTime:2018/4/21 21:35
 */
public class User implements Serializable {
  private Integer id;
  private String name ;
  private String age;

  public User(String name, String age) {
    this.name = name;
    this.age = age;
  }

  public User(String name) {
    this.name = name;
  }

  public User() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
}
