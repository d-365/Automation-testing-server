/*
 * author     : dujun
 * date       : 2022/6/15 16:24
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.config.springSecurity;

import com.dujun.springboot.utils.encryptionUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

//@Component
public class MyPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        return encryptionUtils.md5Encryption((String) charSequence);
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(encryptionUtils.md5Encryption((String) charSequence));
    }
}
