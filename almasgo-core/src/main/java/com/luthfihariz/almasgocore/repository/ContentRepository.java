package com.luthfihariz.almasgocore.repository;

import com.luthfihariz.almasgocore.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {
}
