package com.dujun.springboot.temp;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.zip.DeflaterOutputStream;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)

@interface ZhuJie {
    int check() default 1;
}

class ZhuJieTest {

    @ZhuJie
    private int value;

    public static void main(String []args){
        Field[] fields=ZhuJieTest.class.getDeclaredFields();
        System.out.println(fields[0].getAnnotation(ZhuJie.class).check());


    }

}
