package com.jsong.wiki.backend.repository;

import com.jsong.wiki.backend.entity.FolderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FolderDao extends JpaRepository<FolderEntity,Integer> {
}
