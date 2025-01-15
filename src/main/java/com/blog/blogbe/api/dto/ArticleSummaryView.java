package com.blog.blogbe.api.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ArticleSummaryView {
    Long id;
    String title;
    String authorName;
}
