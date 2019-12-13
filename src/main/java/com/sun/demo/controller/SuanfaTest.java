package com.sun.demo.controller;

import java.util.Arrays;

/**
 * Title:
 * Description:
 * Copyright: 2019 北京拓尔思信息技术股份有限公司 版权所有.保留所有权
 * Company:北京拓尔思信息技术股份有限公司(TRS)
 * Project: demo
 * Author: 果子
 * Create Time:2019/12/9 17:07
 */
public class SuanfaTest {
    public static void main(String[] args){
        int[]aa={5,3,6,4,2,7};
        sort(aa);
//        Arrays.asList(aa);
        for (int a:aa){
            System.out.println("a:"+a);
        }
    }
    public static void sort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j > 0; j--) {
                if (a[j] < a[j - 1]) {
                    int temp = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = temp;
                }
            }
        }
    }

}
