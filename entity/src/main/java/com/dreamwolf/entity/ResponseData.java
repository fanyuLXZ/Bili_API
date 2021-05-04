package com.dreamwolf.entity;


import lombok.Data;

@Data
public class ResponseData<T> {
    private Integer code;
    private String message;
    private T data;

    public ResponseData(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseData() {
    }
}
