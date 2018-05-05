package com.kongxiang.rw.springboot.service;



import com.kongxiang.rw.springboot.entity.User;

import java.util.List;

/**
 * @version 1.0
 * @description:
 * @projectName: com.kongxiang.project.service
 * @className: SpringBootRW
 * @author:谭农春
 * @createTime:2018/5/4 21:57
 */
public interface UserService {
  /**
   * 查询调用 read 数据库
   * @return
   */
  public List<User> getAll();

  /**
   * 插入调用写数据库
   * @param user
   * @return
   */
  public int insert(User user);
}
