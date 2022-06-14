/*
 * author     : dujun
 * date       : 2022/5/16 11:34
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.temp;

import lombok.Data;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

@Component
@Data
public class Person implements BeanNameAware {

    private  String name;

    @Override
    public void setBeanName(String s) {
        this.name = s;
    }
}
