package com.github.holyloop.jencode.except;

import lombok.Data;

@Data
public class BusinessException extends RuntimeException {

    private String msg;

    public BusinessException() {}

    public BusinessException(String msg) {
        super(msg);
    }

}
