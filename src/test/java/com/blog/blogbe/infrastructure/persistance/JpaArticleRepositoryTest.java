package com.blog.blogbe.infrastructure.persistance;

import com.blog.blogbe.RuntimeDependencies;
import com.blog.blogbe.infrastructure.persistance.entity.ArticleEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.context.ImportTestcontainers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@ImportTestcontainers(RuntimeDependencies.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class JpaArticleRepositoryTest {

    @Autowired
    private JpaArticleRepository jpaArticleRepository;

    @Test
    @Sql(scripts = {
            "classpath:datasets/sql/purge.sql",
            "classpath:datasets/sql/JpaArticleRepositoryTest/insert-author-and-articles.sql"})
    void findArticlesBefore() {
        LocalDate localDate = LocalDate.parse("2023-12-12");
        LocalDateTime localDateTime = localDate.atStartOfDay();
        Instant instant = localDateTime.toInstant(ZoneOffset.UTC);
        List<ArticleEntity> articlesBefore = jpaArticleRepository.findArticlesBefore(instant);

        assertThat(articlesBefore).hasSize(3);
    }
}