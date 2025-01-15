package com.blog.blogbe.api;

import com.blog.blogbe.ParentIT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.auditing.AuditingHandler;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.time.Clock;
import java.util.Optional;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ArticleControllerTests extends ParentIT {

    @Autowired
    private Clock fixedClock;
    @SpyBean
    private AuditingHandler auditingHandler;

    @BeforeEach
    void setUp() {
        auditingHandler.setDateTimeProvider(() -> Optional.of(fixedClock.instant()));
    }

    @Test
    @Sql(scripts = {
            "classpath:datasets/sql/purge.sql",
            "classpath:datasets/sql/ArticleControllerTests/insert-author-and-articles.sql"})
    void findAll_returns_articles() throws Exception {
        mockMvc.perform(get("/api/v1/articles"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(readFromFile(getJsonDataPrefixPath() + "response-findAllArticles.json")));
    }

    @Test
    @Sql(scripts = {
            "classpath:datasets/sql/purge.sql",
            "classpath:datasets/sql/ArticleControllerTests/insert-author-and-articles.sql"})
    void findById_returns_article() throws Exception {
        mockMvc.perform(get("/api/v1/articles/101"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(readFromFile(getJsonDataPrefixPath() + "response-findArticleById.json")));
    }

    @Test
    @Sql(scripts = {
            "classpath:datasets/sql/purge.sql",
            "classpath:datasets/sql/ArticleControllerTests/insert-author-and-articles.sql"})
    void findById_not_found() throws Exception {
        mockMvc.perform(get("/api/v1/articles/999"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(content().json(readFromFile(getJsonDataPrefixPath() + "response-articleNotFound.json")));
    }

    @Test
    @Sql(scripts = {
            "classpath:datasets/sql/purge.sql",
            "classpath:datasets/sql/ArticleControllerTests/insert-author.sql"})
    void create_creates_article() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/api/v1/articles")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                  "title": "test title",
                                  "content": "test content",
                                  "authorId": 42
                                }
                                """))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        String expected = readFromFile(getJsonDataPrefixPath() + "response-createArticle.json");

        assertThatJson(content).isEqualTo(expected);
    }

    @Test
    @Sql(scripts = {
            "classpath:datasets/sql/purge.sql",
            "classpath:datasets/sql/ArticleControllerTests/insert-author.sql"})
    void create_validates_request() throws Exception {
        mockMvc.perform(post("/api/v1/articles")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                  "title": "",
                                  "authorId": 42
                                }
                                """))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @Sql(scripts = {
            "classpath:datasets/sql/purge.sql",
            "classpath:datasets/sql/ArticleControllerTests/insert-author-and-articles.sql"})
    void update_updates_article() throws Exception {
        MvcResult mvcResult = mockMvc.perform(put("/api/v1/articles/101")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                  "title": "test title updated",
                                  "content": "test content updated"
                                }
                                """))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        String expected = readFromFile(getJsonDataPrefixPath() + "response-updateArticle.json");

        assertThatJson(content).isEqualTo(expected);
    }

    @Test
    void update_validates_request() throws Exception {
        mockMvc.perform(put("/api/v1/articles/101")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                  "title": ""
                                }
                                """))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @Sql(scripts = {
            "classpath:datasets/sql/purge.sql",
            "classpath:datasets/sql/ArticleControllerTests/insert-author-and-article.sql"})
    void delete_deletes_article() throws Exception {
        mockMvc.perform(delete("/api/v1/articles/101"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNoContent());
        assertThat(entityManager.createNativeQuery("SELECT COUNT(*) FROM articles").getSingleResult()).isEqualTo(0L);
    }
}
