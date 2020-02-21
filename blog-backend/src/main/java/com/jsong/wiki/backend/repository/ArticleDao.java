package com.jsong.wiki.backend.repository;

import com.jsong.wiki.backend.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleDao extends JpaRepository<ArticleEntity, Integer> {
}
