package com.blog.blogbe.application.repository;

import com.blog.blogbe.api.dto.ArticleCreateRequest;
import com.blog.blogbe.api.dto.ArticleSummaryView;
import com.blog.blogbe.api.dto.ArticleUpdateRequest;
import com.blog.blogbe.api.dto.ArticleView;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {

    ArticleView save(ArticleCreateRequest article);

    ArticleView update(Long id, ArticleUpdateRequest request);

    void deleteById(Long id);

    Optional<ArticleView> findById(Long id);

    List<ArticleSummaryView> findAll();
}
