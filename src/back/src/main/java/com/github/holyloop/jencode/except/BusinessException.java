package com.github.holyloop.jencode.except;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BusinessException extends RuntimeException {

    private String msg;

    public BusinessException(String msg) {
        super(msg);
    }

}
