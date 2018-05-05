package com.kongxiang.rw.springboot.dao.mapper;



import com.kongxiang.rw.springboot.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @version 1.0
 * @description:
 * @projectName: com.kongxiang.mapper
 * @className: springmvc
 * @author:谭农春
 * @createTime:2018/4/22 9:49
 */
@Mapper
public interface UserMapper {
  /**
   *  通过name 查询
   * @param name
   * @return
   */
    public List<User> selectAll(@Param("name") String name) ;

    /**
     * 添加用户
     * @param user
     * @return
     */
    public int insert(User user) ;

}
