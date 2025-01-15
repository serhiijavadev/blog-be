package com.blog.blogbe.infrastructure.persistance.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity
@Table(name = "articles")
@EntityListeners(AuditingEntityListener.class)

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id", callSuper = false)
@ToString(onlyExplicitlyIncluded = true)
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Include
    private Long id;

    @Column(name = "title")
    @ToString.Include
    private String title;

    @Column(name = "content")
    @ToString.Include
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private AuthorEntity author;

    @Column(name = "created_at")
    @ToString.Include
    @CreatedDate
    private Instant createdAt;

    @Column(name = "updated_at")
    @ToString.Include
    @LastModifiedDate
    private Instant updatedAt;

    @Version
    private Long version;
}
