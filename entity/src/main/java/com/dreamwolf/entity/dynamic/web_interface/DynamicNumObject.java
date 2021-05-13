package com.dreamwolf.entity.dynamic.web_interface;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class DynamicNumObject {
    private Integer uid;
    private Long num;


    public DynamicNumObject() {
    }
}
