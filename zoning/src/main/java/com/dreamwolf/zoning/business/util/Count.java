package com.dreamwolf.zoning.business.util;

import java.util.Random;

public class Count {
    public int count(int ps, int count){//ps显示数  count 总数
        //1.第几页开始(随机) 一个页面显示多少条
        Integer i=count/ps;
        Random rnd = new Random();
        int n = rnd.nextInt(i)+1; // nextInt(int x) 返回 0~(x-1) 之间的随机数。
        return n;
    }
}
