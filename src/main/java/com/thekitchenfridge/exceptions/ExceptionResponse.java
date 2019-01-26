package com.thekitchenfridge.exceptions;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public final class ExceptionResponse {
    private HttpStatus status;
    private String error_code;
    private String message;
    private String detail;
}