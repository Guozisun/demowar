package com.sun.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Title:
 * Description:
 * Copyright: 2019 北京拓尔思信息技术股份有限公司 版权所有.保留所有权
 * Company:北京拓尔思信息技术股份有限公司(TRS)
 * Project: demo
 * Author: 果子
 * Create Time:2019/11/29 17:04
 */
@Controller
public class TestController {
    @RequestMapping("/test")
    public String  test(){
        return "index";
    }
}
