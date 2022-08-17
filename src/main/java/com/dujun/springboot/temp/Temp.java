/**
 * author     : dujun
 * date       : 2022/7/28 14:50
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.temp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathFactory;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class Temp {

    public static void main(String[] args) throws Exception {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
        Document document = builder.parse("C:\\Users\\dujun\\Desktop\\springboo-new\\src\\main\\java\\com\\dujun\\springboot\\mapper\\xml\\ActionMapper.xml");
        Element element = document.getDocumentElement();
        NodeList nodeList = element.getChildNodes();

        XPathFactory xPathFactory = XPathFactory.newInstance();

    }

    @Test
    public void test_fun1() throws InterruptedException {
        Locale locale = Locale.forLanguageTag("en");
        System.out.println(locale.getDisplayName());
        System.out.println(Arrays.toString(Locale.getISOLanguages()));

    }

}

class myThread extends Thread {

    private ArrayList<Integer> myList;

    myThread(ArrayList<Integer> list) {
        this.myList = list;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("执行5次了 退出");
        }
    }

}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Person implements Serializable {
    String name = "dujun";
}


@Data
class Son implements Serializable {
    String name = "son";

    Son() {
        System.out.println("构造方法");
    }

    public static void fun1() {
        System.out.println("静态方法");
    }
}