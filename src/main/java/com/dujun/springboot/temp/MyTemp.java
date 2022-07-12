package com.dujun.springboot.temp;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class Son{
    private String name;
}

public class MyTemp {

    public static void main(String[] args) {
        Son s = new Son("dujun");
        Son s2 = new Son("son");
        System.out.println(s.getName());
    }

}