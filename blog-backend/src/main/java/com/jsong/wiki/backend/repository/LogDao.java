package com.jsong.wiki.backend.repository;

import com.jsong.wiki.backend.entity.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogDao extends JpaRepository<LogEntity,Integer> {
}
