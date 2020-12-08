package com.luthfihariz.almasgocore.repository;

import com.luthfihariz.almasgocore.model.Engine;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EngineRepository extends JpaRepository<Engine, Long> {

    List<Engine> findAllByUserId(Long userId, Pageable pageable);

    Engine findByIdAndApiKey(Long id, String apiKey);
}
