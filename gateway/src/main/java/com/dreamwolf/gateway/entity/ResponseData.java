package com.dreamwolf.gateway.entity;

import lombok.Data;

@Data
public class ResponseData<T> {
    private Integer code;
    private String message;
    private T data;
}
