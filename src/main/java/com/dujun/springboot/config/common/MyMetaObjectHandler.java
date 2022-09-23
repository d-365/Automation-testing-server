/*
 * author     : dujun
 * date       : 2022/8/18 13:47
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.config.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        //判断是否有传递该属性
        if (metaObject.hasSetter("createTime")) {
            Object createTime = getFieldValByName("createTime", metaObject);
            if (createTime == null) {
                this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
            }
        }
        if (metaObject.hasSetter("updateTime")) {
            Object createTime = getFieldValByName("updateTime", metaObject);
            if (createTime == null) {
                this.strictInsertFill(metaObject, "updateTime", Date.class, new Date());
            }
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //判断是否有传递该属性
        if (metaObject.hasSetter("updateTime")) {
            Object modifyTime = getFieldValByName("updateTime", metaObject);
            if (modifyTime == null) {
                this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
            }
        }

    }

}
