package com.example.minipro_studentmanage.exception;


import com.example.minipro_studentmanage.enums.ResponseCode;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BusinessException extends RuntimeException{

    private ResponseCode responseCode;

    public BusinessException(ResponseCode responseCode){
        super(responseCode.getMessage());
        this.responseCode = responseCode;
    }
}
