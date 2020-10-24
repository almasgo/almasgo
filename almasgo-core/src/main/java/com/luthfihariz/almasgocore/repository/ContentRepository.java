package com.luthfihariz.almasgocore.repository;

import com.luthfihariz.almasgocore.model.Content;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {

    public List<Content> findAllByUserId(Long userId, Pageable pageable);
}
