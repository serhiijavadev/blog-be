package com.blog.blogbe.infrastructure.converter;

import com.blog.blogbe.api.dto.ArticleCreateRequest;
import com.blog.blogbe.api.dto.ArticleSummaryView;
import com.blog.blogbe.api.dto.ArticleView;
import com.blog.blogbe.infrastructure.persistance.entity.ArticleEntity;
import com.blog.blogbe.infrastructure.persistance.entity.AuthorEntity;
import com.blog.blogbe.infrastructure.persistance.projection.ArticleProjection;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ArticleEntityConverter {

    public static ArticleEntity toEntity(ArticleCreateRequest dto, AuthorEntity authorEntity) {
        return ArticleEntity.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .author(authorEntity)
                .build();
    }

    public static ArticleView toDto(ArticleEntity entity) {
        return ArticleView.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .author(ArticleView.AuthorView.builder()
                        .id(entity.getAuthor().getId())
                        .name(entity.getAuthor().getName())
                        .build())
                .createdDate(entity.getCreatedAt().toString())
                .updatedDate(entity.getUpdatedAt().toString())
                .version(entity.getVersion())
                .build();
    }

    public static ArticleSummaryView toDto(ArticleProjection projection) {
        return ArticleSummaryView.builder()
                .id(projection.getId())
                .title(projection.getTitle())
                .authorName(projection.getAuthorName())
                .build();
    }

}
