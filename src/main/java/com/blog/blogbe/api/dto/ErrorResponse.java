package com.blog.blogbe.api.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ErrorResponse {
    int status;
    String message;
}
