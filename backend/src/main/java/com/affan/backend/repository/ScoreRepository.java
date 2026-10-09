package com.affan.backend.repository;

import com.affan.backend.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

@Repository
public interface ScoreRepository extends JpaRepository<Score, UUID> {
    List<Score> findAllByOrderByCreatedAtDesc();
    List<Score> findByPointGreaterThan(Integer minValue);
    List<Score> findAllByOrderByPointDesc(Pageable pageable);

    @Query("SELECT s FROM Score s ORDER BY s.point DESC")
    List<Score> findTopScores(Integer limit);
}
