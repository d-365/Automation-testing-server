/*
 * author     : dujun
 * date       : 2022/3/11 11:01
 * description: 加密解密工具类
 */

package com.dujun.springboot.utils;

import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class encryptionUtils {

    /**
     * md5加密
     * @return String
     */
    public static String md5Encryption(String text){
        byte[] digest = null;
        text = text + "---salt";
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            digest  = md5.digest(text.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        //16是表示转换为16进制数
        assert digest != null;
        return new BigInteger(1, digest).toString(16);
    }

    /**
     * 返回 UUID
     * @return String
     */
    public static String getUUID(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    /**
     * Base64加密
     * @param text String
     * @return String
     */
    public static String base64Encryption(String text){
        return Base64Coder.encodeString(text);
    }

    /**
     * Base64解密
     * @param cipherText String
     * @return String
     */
    public static String base64Decryption(String cipherText){
        return  Base64Coder.decodeString(cipherText);
    }

    public static void main(String[] args) {
        System.out.println(md5Encryption("123456"));
    }

}
