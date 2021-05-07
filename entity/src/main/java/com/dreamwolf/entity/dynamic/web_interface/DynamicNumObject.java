package com.dreamwolf.entity.dynamic.web_interface;

import lombok.Data;

import java.util.Map;

@Data
public class DynamicNumObject {
    private Integer uid;
    private Long num;

    public DynamicNumObject(Map<String,Object> map) {
        this.uid= (Integer) map.get("uid");
        this.num= (Long) map.get("num");
    }

    public DynamicNumObject() {
    }
}
