package com.kongxiang.rw.springboot.controller;


import com.kongxiang.rw.springboot.entity.User;
import com.kongxiang.rw.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

/**
 * @version 1.0
 * @description:
 * @projectName: com.kongxiang.project.controller
 * @className: SpringBootRW
 * @author:谭农春
 * @createTime:2018/5/4 21:53
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

  @Autowired
  private UserService userService;
  /**
   * 获取所有的用户
   * @return
   * @throws Exception
   */
  @GetMapping("all")
  public List<User> getAll() throws  Exception{
    return  this.userService.getAll();
  }


  /**
   *  保存用户
   * @return
   * @throws Exception
   */
  @PostMapping("insert")
  public Integer insert(@RequestBody User user ) throws  Exception{
    return  this.userService.insert(user);
  }

}
