package com.blog.blogbe.api.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ArticleView {
    Long id;
    String title;
    String content;
    String createdDate;
    String updatedDate;
    AuthorView author;
    Long version;

    @Value
    @Builder
    public static class AuthorView {
        Long id;
        String name;
    }
}
