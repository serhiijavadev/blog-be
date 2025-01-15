package com.blog.blogbe.infrastructure.persistance;

import com.blog.blogbe.infrastructure.persistance.entity.ArticleEntity;
import com.blog.blogbe.infrastructure.persistance.projection.ArticleProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

@Repository
public interface JpaArticleRepository extends JpaRepository<ArticleEntity, Long> {
    @Query(value = """
            SELECT art.id as id,
                   art.title as title,
                   aut.name as authorName
            FROM articles art INNER JOIN authors aut ON art.author_id = aut.id;
            """, nativeQuery = true)
    Collection<ArticleProjection> findAllArticleSummaries();

    //Write a Spring Data JPA repository query to find all articles published before a certain date
    @Query("SELECT art FROM ArticleEntity art JOIN FETCH art.author WHERE art.createdAt < :timestamp")
    List<ArticleEntity> findArticlesBefore(@Param("timestamp") Instant timestamp);
}
