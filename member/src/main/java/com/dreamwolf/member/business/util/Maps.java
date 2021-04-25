package com.dreamwolf.member.business.util;

import java.util.HashMap;
import java.util.Map;

public class Maps {
    public Map jkmap(){
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("code",400);
        map.put("message","数据为空");//信息
        map.put("ttl",1);
        map.put("data","");
        return map;
    }
}
