package com.jsong.wiki.backend.repository;

import com.jsong.wiki.backend.entity.FloderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FloderDao extends JpaRepository<FloderEntity,Integer> {
}
