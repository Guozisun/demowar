package com.sun.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.*;

/**
 * Title:
 * Description:
 * Copyright: 2019 北京拓尔思信息技术股份有限公司 版权所有.保留所有权
 * Company:北京拓尔思信息技术股份有限公司(TRS)
 * Project: demo
 * Author: 果子
 * Create Time:2019/12/9 11:02
 */
@Component
public class RedisUtil {
    @Autowired
    private RedisTemplate redisTemplate;

    //    如果使用RedisTemplate需要更改序列化方式
    public void tt() {
        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
    }

    /**
     * 批量删除对应的value 递归删除
     *
     * @param keys
     */
//    public void remove(final String... keys) {
//        for (String key : keys) {
//            remove(key);
//        }
//    }
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }
//    public void remove(String key){
//        if (exists(key)){
//            redisTemplate.delete(key);
//        }
//    }

    /**
     * 批量删除key
     *
     * @param pattern
     */
    public void removePattern(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0)
            redisTemplate.delete(keys);
    }

    /**
     * 删除单个key
     *
     * @param key
     */
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * String类型 取
     * 根据 key 获取对应的value 如果key不存在则返回null
     *
     * @param key 查询的key
     * @return 查询结果
     */
    public String get(final String key) {
        Object result = null;
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        if (result == null) {
            return null;
        }
        return result.toString();
    }

    /**
     * String类型 存
     * <p>
     * 设置 key 的值为 value
     * 如果key不存在添加key 保存值为value
     * 如果key存在则对value进行覆盖
     *
     * @param key   key值
     * @param value 存入的value值
     * @return 添加结果
     */
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * map类型 存
     * 为散列添加多个key-value键值对
     *
     * @param key   key值
     * @param value map值
     * @return 添加结果
     */
    public boolean hmset(String key, Map<String, String> value) {
        boolean result = false;
        try {
            //putAll 添加多个key-value    添加一个使用put(H key, HK hashKey, HV value)
            redisTemplate.opsForHash().putAll(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * map类型 取
     * 获取散列的key-value键值对集合
     *
     * @param key key值
     * @return 查询结果
     */
    public Map<String, String> hmget(String key) {
        Map<String, String> result = null;
        try {
            result = redisTemplate.opsForHash().entries(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * list 类型 存
     * 插入数据到list
     *
     * @param key
     * @param list
     * @return 添加结果
     */
    public boolean setList(String key, List<String> list) {
        boolean result = false;
        try {
            ListOperations listOperations = redisTemplate.opsForList();
            listOperations.leftPush(key, list);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * list类型 取
     * 根据下表获取指定list数据
     *
     * @param key
     * @param index
     * @return
     */
    public String getList(final String key, Long index) {
        Object result = null;
        //设置序列化
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        ListOperations listOperations = redisTemplate.opsForList();
        result = listOperations.index(key, index);
        if (result == null) {
            return null;
        }
        return result.toString();
    }

    /**
     * @param key
     * @param set
     * @return
     */
    public boolean setSet(String key, Set<String> set) {
        boolean result = false;
        try {
            SetOperations setOperations = redisTemplate.opsForSet();
            setOperations.add(key, set);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取set中的值
     *
     * @param key
     * @return
     */
    public String getSet(final String key) {
        Object result = null;
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        SetOperations setOperations = redisTemplate.opsForSet();
        result = setOperations.members(key);
        if (result == null) {
            return null;
        }
        return result.toString();
    }

    /**
     * ZSet 类型 存
     * 给有序集合添加一个指定分数的成员 如果成员存在则覆盖
     *
     * @param key   key值
     * @param value value值
     * @param score 分数
     * @return 添加结果
     */
    public boolean setZSet(String key, String value, double score) {
        boolean result = false;
        try {
            ZSetOperations setOperations = redisTemplate.opsForZSet();
            setOperations.add(key, value, score);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Set<String> getZSet(final String key, long start, long end) {
        Object result = null;
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        ZSetOperations listOperations = redisTemplate.opsForZSet();

//        result = listOperations.rangeWithScores(key ,start ,end); //获取有序集合中指定分数范围内的成员集合  获取 value 分数
        result = listOperations.reverseRange(key, start, end); //从有序集合中获取指定范围内从高到低的成员集合 获取 value
//        result = listOperations.range(key ,start ,end); //指定范围内   （0 -1）返回所有。

        if (result == null) {
            return null;
        }
//        return result.toString();
        Iterator iterator = ((Set) result).iterator();
        Set<String> zSetValList = new HashSet<>();
        while (iterator.hasNext()) {
            //使用rangeWithScores 方法获取的
//            DefaultTypedTuple next = (DefaultTypedTuple)iterator.next();
//            System.out.println();
//            System.out.println(next.getScore());
//            System.out.println(next.getValue());

            //使用reverseRange 方法获取
            String next = (String) iterator.next();
            zSetValList.add(next);
        }
        return zSetValList;
    }
}
