package com.jsong.wiki.backend.repository;

import com.jsong.wiki.backend.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagDao extends JpaRepository<TagEntity,Integer> {
}
