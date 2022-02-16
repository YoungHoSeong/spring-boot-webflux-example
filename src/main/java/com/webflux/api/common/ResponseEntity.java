package com.webflux.api.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Pageable;

@Data
@AllArgsConstructor
public class ResponseEntity<T> {

    private T data;
    private Long totalElements;
    private int totalPages;
    private int number;
    private Pageable pageable;
}
