package com.blog.blogbe.infrastructure;

import com.blog.blogbe.api.dto.ArticleCreateRequest;
import com.blog.blogbe.api.dto.ArticleSummaryView;
import com.blog.blogbe.api.dto.ArticleUpdateRequest;
import com.blog.blogbe.api.dto.ArticleView;
import com.blog.blogbe.application.exception.ArticleNotFoundException;
import com.blog.blogbe.application.repository.ArticleRepository;
import com.blog.blogbe.infrastructure.converter.ArticleEntityConverter;
import com.blog.blogbe.infrastructure.persistance.JpaArticleRepository;
import com.blog.blogbe.infrastructure.persistance.JpaAuthorRepository;
import com.blog.blogbe.infrastructure.persistance.entity.ArticleEntity;
import com.blog.blogbe.infrastructure.persistance.entity.AuthorEntity;
import com.blog.blogbe.infrastructure.persistance.projection.ArticleProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ArticleRepositoryImpl implements ArticleRepository {

    private final JpaArticleRepository jpaArticleRepository;
    private final JpaAuthorRepository jpaAuthorRepository;


    @Override
    public ArticleView save(ArticleCreateRequest request) {
        AuthorEntity authorEntity = jpaAuthorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new ArticleNotFoundException(request.getAuthorId().toString()));
        ArticleEntity saved = jpaArticleRepository.save(ArticleEntityConverter.toEntity(request, authorEntity));

        return ArticleEntityConverter.toDto(saved);
    }

    @Override
    public ArticleView update(Long id, ArticleUpdateRequest request) {
        ArticleEntity articleEntity = jpaArticleRepository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException(id.toString()));
        articleEntity.setTitle(request.getTitle());
        articleEntity.setContent(request.getContent());
        ArticleEntity updated = jpaArticleRepository.saveAndFlush(articleEntity);

        return ArticleEntityConverter.toDto(updated);
    }

    @Override
    public Optional<ArticleView> findById(Long id) {
        return jpaArticleRepository.findById(id)
                .map(ArticleEntityConverter::toDto);
    }

    @Override
    public List<ArticleSummaryView> findAll() {
        Collection<ArticleProjection> allArticleSummaries = jpaArticleRepository.findAllArticleSummaries();

        return allArticleSummaries.stream()
                .map(ArticleEntityConverter::toDto)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        jpaArticleRepository.deleteById(id);
    }
}
