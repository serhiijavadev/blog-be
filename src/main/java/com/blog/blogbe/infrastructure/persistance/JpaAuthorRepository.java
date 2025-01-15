package com.blog.blogbe.infrastructure.persistance;

import com.blog.blogbe.infrastructure.persistance.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaAuthorRepository extends JpaRepository<AuthorEntity, Long> {

}
