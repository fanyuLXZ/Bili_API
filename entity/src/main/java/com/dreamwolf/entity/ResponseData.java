package com.dreamwolf.entity;
import lombok.Data;

@Data
public class ResponseData<T> {
    private Integer code;
    private String message;
    private Integer ttl;
    private T data;

    public ResponseData(Integer code, String message, Integer ttl, T data) {
        this.code = code;
        this.message = message;
        this.ttl = ttl;
        this.data = data;
    }
}
