package com.dreamwolf.entity;
import lombok.Data;

/**
 * 接口返回通用类
 * @param <T> 返回值data的类型
 */
@Data
public class ResponseData<T> {
    // 返回码
    private Integer code;
    // 返回的提示信息
    private String message;
    private Integer ttl;
    // 返回的数据
    private T data;

    public ResponseData(Integer code, String message, Integer ttl, T data) {
        this.code = code;
        this.message = message;
        this.ttl = ttl;
        this.data = data;
    }
}
