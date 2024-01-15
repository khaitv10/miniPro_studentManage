package com.example.minipro_studentmanage.common;


import com.example.minipro_studentmanage.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalException {


//    @ExceptionHandler(Exception.class)
//    @ResponseBody
//    public ApiResponse handleException(Exception ex) {
//        ApiResponse apiResponse = new ApiResponse();
//        apiResponse.setMessage(ex.getMessage());
//        return apiResponse;
//    }


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<?> handleException(Exception ex) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(ex.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }



//    @ExceptionHandler(BusinessException.class)
//    @ResponseBody
//    public ApiResponse handleException(BusinessException ex) {
//        ApiResponse apiResponse = new ApiResponse();
//        apiResponse.setMessage(ex.getResponseCode().getMessage());
//        apiResponse.setCode(ex.getResponseCode().getCode());
//        return apiResponse;
//    }



    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public ResponseEntity<?> handleBusinessException(BusinessException ex) {
        ApiResponse  apiResponse = new ApiResponse();
        apiResponse.setMessage(ex.getResponseCode().getMessage());
        apiResponse.setCode(ex.getResponseCode().getCode());
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(
        MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        StringBuilder stringBuilder = new StringBuilder();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
            stringBuilder.append(fieldName).append(":").append(errorMessage).append(";");
        });
        ApiResponse  apiResponse = new ApiResponse();
        apiResponse.setMessage(stringBuilder.toString());
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

}
