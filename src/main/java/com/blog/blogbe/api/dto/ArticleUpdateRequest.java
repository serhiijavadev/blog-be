package com.blog.blogbe.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ArticleUpdateRequest {
    @NotBlank(message = "title is mandatory")
    String title;

    @NotBlank(message = "content is mandatory")
    String content;
}
