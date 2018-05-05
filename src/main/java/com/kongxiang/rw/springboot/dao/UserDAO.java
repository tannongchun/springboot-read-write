package com.kongxiang.rw.springboot.dao;



import com.kongxiang.rw.springboot.entity.User;

import java.util.List;

/**
 * @version 1.0
 * @description:
 * @projectName: com.kongxiang.project.dao.mapper
 * @className: SpringBootRW
 * @author:谭农春
 * @createTime:2018/5/4 21:55
 */
public interface UserDAO {
  /**
   * 获取所有的用户
   * @return
   */
  public List<User> getAll();

  /**
   *  插入用户
   * @return
   */
  public int insert(User user);
}
