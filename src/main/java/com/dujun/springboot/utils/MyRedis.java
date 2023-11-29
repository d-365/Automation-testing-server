/**
 * author     : dujun
 * date       : 2022/1/17 15:25
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.utils;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Set;

public class MyRedis {

    Jedis jRedis;

    /**
     * 通过Jedis连接到数据库
     *
     * @param ip       IP地址
     * @param password 密码
     */
    public MyRedis(String ip, String password,Integer... port) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //最大连接数，默认8
        jedisPoolConfig.setMaxTotal(20);
        //最大空闲数，默认8
        jedisPoolConfig.setMaxIdle(12);
        //最大等待时间（毫秒），默认-1，表示永不超时
        jedisPoolConfig.setMaxWaitMillis(3000);
        //创建jedis连接池
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, ip, 6379, 3000, password);
        //从连接池中获取连接
        jRedis = jedisPool.getResource();
        jRedis.auth(password);
    }

    /**
     * 设置 string 类型的数据
     *
     * @param key   String类型的key
     * @param value String类型的value
     */
    public void set(String key, String value) {
        jRedis.set(key, value);
    }

    /**
     * 获取字符串类型的key值
     * @param key String类型的key
     * @return String
     */
    public String get(String key) {
        return jRedis.get(key);
    }

    /**
     * 通过KEY 和 Field 插入一个HASH类型数据
     * @param key   String
     * @param field String
     * @param value String
     */
    public void hSet(String key, String field, String value) {
        jRedis.hset(key, field, value);
    }

    /**
     * 通过KEY和Field 获取一个HASH类型数据
     * @param key   String
     * @param field String
     * @return String
     */
    public String hGet(String key, String field) {
        return jRedis.hget(key, field);
    }

    /**
     * redis列表中右侧弹出移除一个元素
     * @param key String
     */
    public String rPop(String key) {
        return jRedis.rpop(key);
    }

    /**
     * 通过KEY 向redisLIst中插入多个值
     *
     * @param key     String
     * @param strings String ...
     */
    public void rPush(String key, String... strings) {
        jRedis.rpush(key, strings);
    }

    /**
     * 向Redis插入Set类型的数据( 无序 )
     *
     * @param key   String
     * @param value String...
     * @return Long
     */
    public Long sAdd(String key, String... value) {
        return jRedis.sadd(key, value);
    }

    /**
     *  通过KEY 获取对应  SET 集合的数据
     * @param key String
     * @return Set<String>
     */
    public Set<String> sMembers(String key) {
        return jRedis.smembers(key);
    }

    /**
     * 向Redis插入一个Sorted Set / ZSet 类型数据
     * @param key key
     * @param score 排序值
     * @param value String
     */
    public void zAdd(String key,Double score,String value){
        jRedis.zadd(key,score,value);
    }

    /**
     * 根据KEY 和索引范围获取对应ZSet中数据(按照scope从小到大获取)
     * @param key String
     * @param start beginIndex 从0开始
     * @param end  endIndex
     * @return List
     */
    public Set<String> ZRange (String key, Long start, Long end){
        return jRedis.zrange(key,start,end);
    }

    /**
     *根据KEY 和索引范围获取对应ZSet中数据(按照scope从小到大获取)
     * @param key String
     * @param start beginIndex 从0开始
     * @param end endIndex
     * @return List<String>
     */
    public Set<String> zRevRange(String key, Long start, Long end){
        return jRedis.zrevrange(key,start,end);
    }

    /**
     * 根据 key和 ZSet value值删除对应记录
     * @param key String
     * @param values String...
     * @return 删除记录条数
     */
    public Long zRem(String key,String... values){
        return jRedis.zrem(key,values);
    }

    /**
     * 获取SortSet 中记录条数
     * @param key String
     * @return Long
     */
    public Long zCard(String key){
        return jRedis.zcard(key);
    }

    /**
     * 根据下标切换数据库
     * @param index Integer
     */
    public void select(int index) {
        jRedis.select(index);
    }

    /**
     * 根据key名进行删除
     * @param key String
     */
    public void del(String... key){
        jRedis.del(key);
    }

    /**
     * 释放JRedis连接
     */
    public void close() {
        if (jRedis != null) {
            jRedis.close();
        }
    }

}

class RedisTest {

    @Test
    public void test_fun1() {
        MyRedis myRedis = new MyRedis("47.97.41.177", "b840fc02");
        System.out.println(myRedis.zCard("zadd"));
        myRedis.close();
    }

    public static void fun1(String... key){
        System.out.println();
    }

    public static void main(String[] args) {
        fun1();
    }


}