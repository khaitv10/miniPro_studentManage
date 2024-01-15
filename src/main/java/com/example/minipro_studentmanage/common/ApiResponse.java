package com.example.minipro_studentmanage.common;


import com.example.minipro_studentmanage.enums.ResponseCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse<T> {
    private Integer code;
    private String message;
    private T data;


    public ApiResponse ok(T data) {
        this.setCode(ResponseCode.SUCCESS.getCode());
        this.setMessage(ResponseCode.SUCCESS.getMessage());
        this.setData(data);
        return this;
    }
}
