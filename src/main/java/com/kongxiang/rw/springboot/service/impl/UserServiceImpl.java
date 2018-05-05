package com.kongxiang.rw.springboot.service.impl;


import com.kongxiang.rw.springboot.dao.UserDAO;
import com.kongxiang.rw.springboot.entity.User;
import com.kongxiang.rw.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version 1.0
 * @description:
 * @projectName: com.kongxiang.project.service.impl
 * @className: SpringBootRW
 * @author:谭农春
 * @createTime:2018/5/4 21:58
 */
@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserDAO userDAO;
  /**
   * @description:
   *
   * @param
   *   -- 调用读数据
   * @author:谭农春
   * @createTime: 2018/5/5 17:10
   */

  @Override
  public List<User> getAll() {
    return userDAO.getAll();
  }

  /**
   * 插入调用写数据库
   *
   * @param user
   *
   * @return
   */
  @Override
  public int insert(User user) {
    return userDAO.insert(user);
  }
}
