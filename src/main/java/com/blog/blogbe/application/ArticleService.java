package com.blog.blogbe.application;

import com.blog.blogbe.api.dto.ArticleCreateRequest;
import com.blog.blogbe.api.dto.ArticleSummaryView;
import com.blog.blogbe.api.dto.ArticleUpdateRequest;
import com.blog.blogbe.api.dto.ArticleView;
import com.blog.blogbe.application.exception.ArticleNotFoundException;
import com.blog.blogbe.application.repository.ArticleRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public List<ArticleSummaryView> findAll() {
        log.info("Fetching all articles");
        return articleRepository.findAll();
    }

    @Transactional(readOnly = true)
    public ArticleView findById(Long id) {
        log.info("Fetching article by id: {}", id);
        return articleRepository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException(id.toString()));
    }

    @Transactional
    public ArticleView create(ArticleCreateRequest request) {
        log.info("Creating article: {}", request);
        return articleRepository.save(request);
    }

    @Transactional
    public ArticleView update(Long id, @Valid @NotNull ArticleUpdateRequest request) {
        log.info("Updating article with id: {} and request: {}", id, request);
        return articleRepository.update(id, request);
    }

    @Transactional
    public void delete(Long id) {
        log.info("Deleting article with id: {}", id);
        articleRepository.deleteById(id);
    }
}
