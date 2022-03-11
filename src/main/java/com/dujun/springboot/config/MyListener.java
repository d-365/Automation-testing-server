/*
 * author     : dujun
 * date       : 2022/3/9 15:41
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.config;
import lombok.extern.log4j.Log4j2;
import javax.servlet.*;
import javax.servlet.annotation.WebListener;

@Log4j2
@WebListener
public class MyListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sre) {
        log.debug("监听器销毁");
    }

    public void contextDestroyed(ServletContextEvent sre) {
        log.debug("监听器开始");
    }

}
