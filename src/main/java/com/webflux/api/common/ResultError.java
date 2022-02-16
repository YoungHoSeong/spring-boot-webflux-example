package com.webflux.api.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResultError<T> {
    private T clazz;
    private String message;
    private Integer status;
}
