package com.blog.blogbe.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;


@Value
@Builder
public class ArticleCreateRequest {

    @NotBlank(message = "title is mandatory")
    String title;

    @NotBlank(message = "content is mandatory")
    String content;

    @NotNull(message = "authorId is mandatory")
    Long authorId;
}
