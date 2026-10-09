package com.affan.backend.service;

import com.affan.backend.model.Score;
import com.affan.backend.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ScoreService {
    @Autowired
    private ScoreRepository scoreRepository;

    public Score createScore(Score score) {
        return scoreRepository.save(score);
    }

    public Optional<Score> getScoreByID(UUID scoreId) {
        return scoreRepository.findById(scoreId);
    }

    public List<Score> getAllScores() {
        return scoreRepository.findAll();
    }

    public List<Score> getRecentScores() {
        return scoreRepository.findAllByOrderByCreatedAtDesc();
    }

    public List<Score> getScoreAboveValue(Integer minValue){
        // TODO: Use scoreRepository to find all scores in the database whose points are above a certain value
        // use minValue as the lower bound of the point value
        return scoreRepository.findByPointGreaterThan(minValue);
    }

    public List<Score> getLeaderboard(Integer limit) {
        // TODO: Use scoreRepository to find the Top Scores and provide the appropriate parameter
        return scoreRepository.findAllByOrderByPointDesc(PageRequest.of(0, limit));
    }

    public void deleteScore(UUID scoreId) {
        // TODO:
        // 1. Find the score you want to delete using scoreRepository, then store that score (hint: see how it's done in getScoreById())
        // 2. Check whether the score was found or not with `.orElseThrow(()-> new RuntimeException("Score with ID " + scoreId + " was not found"));`
        // 3. Call delete() from scoreRepository to delete the score stored earlier
        Score score = scoreRepository.findById(scoreId)
            .orElseThrow(() -> new RuntimeException("Score with ID " + scoreId + " was not found"));

        scoreRepository.delete(score);
    }
}
