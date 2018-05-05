package com.kongxiang.rw.springboot.configdb;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;



/**
 * Created by huguoju on 2016/12/29.
 * 拦截设置本地线程变量
 */
@Slf4j
@Aspect
@Component
@EnableAspectJAutoProxy(exposeProxy=true,proxyTargetClass=true)
public class DataSourceAop implements PriorityOrdered {
  @Before("execution(* com.kongxiang.rw.springboot.dao.mapper..*.select*(..))" +
      " || execution(* com.kongxiang.rw.springboot.dao.mapper..*.get*(..)) " +
      " || execution(* com.kongxiang.rw.springboot.dao.mapper..*.fetch*(..)) " +
      " || execution(* com.kongxiang.rw.springboot.dao.mapper..*.find*(..))" +
      " || execution(* com.kongxiang.rw.springboot.dao.mapper..*.query*(..))")
  public void setReadDataSourceType() {
    DataSourceContextHolder.read();
    log.info("dataSource切换到：Read");
  }

  /**
   * @description:
   *  update delete  insert
   *  调用写方法
   * @author:谭农春
   * @createTime: 2018/5/5 17:19
   */

  @Before("execution(* com.kongxiang.rw.springboot.dao.mapper..*.insert*(..)) " +
      " || execution(* com.kongxiang.rw.springboot.dao.mapper..*.update*(..)) " +
      " || execution(* com.kongxiang.rw.springboot.dao.mapper..*.delete*(..)) " +
      " || execution(* com.kongxiang.rw.springboot.dao.mapper..*.save*(..)) " +
      " || execution(* com.kongxiang.rw.springboot.dao.mapper..*.remove*(..)) " +
      " || execution(* com.kongxiang.rw.springboot.dao.mapper..*.add*(..))")
  public void setWriteDataSourceType() {
    DataSourceContextHolder.write();
    log.info("dataSource切换到：write");
  }
  @Override
  public int getOrder() {
    /**
     * 值越小，越优先执行
     * 要优于事务的执行
     * 在启动类中加上了@EnableTransactionManagement(order = 10)
     */
    return 1;
  }
}