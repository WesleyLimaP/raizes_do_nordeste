package com.uninter.raiazesdonordeste.core.api.handler;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Builder
@Data
public class Error {
    private String message;
    private String details;
    private String title;
    private String path;
    private String timestamp;
    private int status;
    private ErrorType type;
    private Map<String, String> fieldsError;
}
