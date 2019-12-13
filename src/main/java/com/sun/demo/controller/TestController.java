package com.sun.demo.controller;

import com.sun.demo.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Title:
 * Description:
 * Copyright: 2019 北京拓尔思信息技术股份有限公司 版权所有.保留所有权
 * Company:北京拓尔思信息技术股份有限公司(TRS)
 * Project: demo
 * Author: 果子
 * Create Time:2019/11/29 17:04
 */
@RestController
public class TestController {
    @Autowired
    RedisUtil redisUtil;
//    @RequestMapping("/test")
//    public String  test(){
//        return "index";
//    }
    @RequestMapping("/test")
    public void  test()
    {
        //          map
        Map<String ,String> map = new HashMap<>();
        map.put("1","22");
        map.put("11","22");
        map.put("11","22");
        map.put("666","6666");
        redisUtil.hmset("map" ,map);
        System.out.println(redisUtil.hmget("map"));

//        list
        List<String> list = new ArrayList<>();
        list.add("11");
        list.add("22");
        list.add("666");
        redisUtil.setList("list" ,list);
        System.out.println(redisUtil.getList("list",0l));

//        set
        Set<String> set = new HashSet<>();
        set.add("11");
        set.add("22");
        set.add("33");
        redisUtil.setSet("set" ,set);
        System.out.println(redisUtil.getSet("set"));

        //zset
        redisUtil.setZSet("zset","111",1d);
        redisUtil.setZSet("zset","222",2d);
        redisUtil.setZSet("zset","333",3d);
        redisUtil.setZSet("zset","444",4d);
        redisUtil.setZSet("zset","555",6d);
        redisUtil.setZSet("zset","666",5d);
        Set<String> zSetVal = redisUtil.getZSet("zset", 0, 5);


    }
}
