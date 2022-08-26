/*
 * author     : dujun
 * date       : 2022/8/25 17:31
 * description: 自定义密码处理方式
 */

package com.dujun.springboot.config.springSecurity;

import com.dujun.springboot.utils.encryptionUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component("MyPasswordEncoder")
public class MyPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return encryptionUtils.md5Encryption(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encode(rawPassword).equals(encodedPassword);
    }

}
