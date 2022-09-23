/*
 * author     : dujun
 * date       : 2022/3/9 15:41
 * description: ServletRequest-客户端请求监听器
 */

package com.dujun.springboot.config.myListener;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

@Log4j2
@Component
public class MyServletRequestListener implements ServletRequestListener {

    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
        log.debug("请求执行前监听");
    }

    public void requestDestroyed(ServletRequestEvent sre) {
        log.debug("请求执行后监听");
    }

}
