package com.blog.blogbe.infrastructure.persistance.projection;

public interface ArticleProjection {
    Long getId();

    String getTitle();

    String getAuthorName();
}
