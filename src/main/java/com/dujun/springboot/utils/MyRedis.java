/**
 * author     : dujun
 * date       : 2022/1/17 15:25
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.utils;
import com.dujun.springboot.tools.YmlTools;
import redis.clients.jedis.Jedis;

public class MyRedis {

    Jedis jedis = null;

    public MyRedis(String ip, String password){
        jedis = new Jedis(ip,6379);
        jedis.auth(password);
    }

    public MyRedis(String ip){
        jedis = new Jedis(ip,6379);
    }

    public void set(String key,String value){
        jedis.set(key,value);
    }

    public String get(String key){
        String value = jedis.get(key);
        return value;
    }

    // 切换数据
    public void  select(int index){
        jedis.select(index);
    }

    public void close(){
        jedis.close();
    }


}
