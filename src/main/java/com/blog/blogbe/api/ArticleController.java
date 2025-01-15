package com.blog.blogbe.api;

import com.blog.blogbe.api.dto.ArticleCreateRequest;
import com.blog.blogbe.api.dto.ArticleSummaryView;
import com.blog.blogbe.api.dto.ArticleUpdateRequest;
import com.blog.blogbe.api.dto.ArticleView;
import com.blog.blogbe.application.ArticleService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/articles")
    public ResponseEntity<List<ArticleSummaryView>> findAll() {
        return ResponseEntity.ok(articleService.findAll());
    }

    @GetMapping("/articles/{id}")
    public ResponseEntity<ArticleView> findById(@PathVariable Long id) {
        return ResponseEntity.ok(articleService.findById(id));
    }

    @PostMapping("/articles")
    public ResponseEntity<ArticleView> create(@Valid @NotNull @RequestBody ArticleCreateRequest request) {
        return ResponseEntity.ok(articleService.create(request));
    }

    @PutMapping("/articles/{id}")
    public ResponseEntity<ArticleView> update(@PathVariable Long id,
                                              @Valid @NotNull @RequestBody ArticleUpdateRequest request) {
        return ResponseEntity.ok(articleService.update(id, request));
    }

    @DeleteMapping("/articles/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Long id) {
        articleService.delete(id);
    }
}
