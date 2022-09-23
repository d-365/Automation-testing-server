/*
 * author     : dujun
 * date       : 2022/9/7 10:53
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.base;

import com.dujun.springboot.VO.TokenInfo;
import com.dujun.springboot.utils.JwtUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.function.Function;

@Log4j2
public class BaseController {

    public static final Function<HttpServletRequest, String> TOKEN_GETTER = (req) -> {
        String token = req.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            token = req.getParameter("token");
        }
        log.info("BaseController获取token：" + token);
        return token;
    };

    private String getToken() {
        try {
            ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            return TOKEN_GETTER.apply(attrs.getRequest());
        } catch (Exception var2) {
            log.info("非http请求");
            return null;
        }
    }

    public TokenInfo tokenInfo() {
        TokenInfo tokenInfo = new TokenInfo();
        String token = getToken();
        tokenInfo.setUserId(Integer.valueOf(Objects.requireNonNull(JwtUtil.getTokenStr(token, "uId"))));
        tokenInfo.setAccount(JwtUtil.getTokenStr(token,"account"));
        return tokenInfo;
    }

}
