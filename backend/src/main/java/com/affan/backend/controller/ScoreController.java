package com.affan.backend.controller;

import com.affan.backend.model.Score;
import com.affan.backend.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/scores")
@CrossOrigin(origins = "*")
public class ScoreController {
    @Autowired
    private ScoreService scoreService;

    // GET /api/scores/{scoreId}
    @GetMapping("/{scoreId}")
    public ResponseEntity<?> getScoreById(@PathVariable UUID scoreId) {
        Optional<Score> score = scoreService.getScoreByID(scoreId);

        if (score.isPresent()) {
            return ResponseEntity.ok(score.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", "Score not found with id: " + scoreId));
        }
    }

    // POST /api/scores
    @PostMapping
    public ResponseEntity<?> createScore(@RequestBody Score score) {
        try {
            Score newScore = scoreService.createScore(score);
            return ResponseEntity.status(HttpStatus.CREATED).body(newScore);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("error", e.getMessage()));
        }
    }

// 1. Add the appropriate annotation for a GET endpoint along with the appropriate endpoint
    @GetMapping
    public ResponseEntity<List<Score>> getAllScores() {
        // 2. Use scoreService to call getAllScores() and store those scores in a variable using List
        // 3. Return the variable containing those scores
        List<Score> scores = scoreService.getAllScores();
        return ResponseEntity.ok(scores);
    }

    // TODO:
// 1. Add the appropriate annotation for a GET endpoint along with the appropriate endpoint
    @GetMapping("/leaderboard")
    public  ResponseEntity<List<Score>> getLeaderboardByPoint(@RequestParam (defaultValue = "10") Integer limit){
	/* 2. add the '@RequestParam' parameter with defaultValue 10
	   3. as well as Integer limit */
        // 4. Use scoreService to call getLeaderboard() with the appropriate parameter
        //    and store those scores in a variable using List
        // 5. Return the variable containing those scores
        List<Score> Scorelist = scoreService.getLeaderboard(limit);
        return ResponseEntity.ok(Scorelist);
    }
    // TODO:
// 1. Add the appropriate annotation for a GET endpoint along with the appropriate endpoint
    @GetMapping("/above/{minValue}")
    public ResponseEntity<List<Score>> getScoresAboveValue( @PathVariable Integer minValue
        /* 2. add '@PathVariable' for Integer minValue*/){
        // 3. Use scoreService to call getScoreAboveValue() with the appropriate parameter
        //    and store those scores in a variable using List
        // 4. Return the variable containing those scores
        List<Score> scores = scoreService.getScoreAboveValue(minValue);
        return ResponseEntity.ok(scores);
    }
    // TODO:
// 1. Add the appropriate annotation for a GET endpoint along with the appropriate endpoint
    @GetMapping("/recent")
    public ResponseEntity<List<Score>> getRecentScores(){
        // 2. Use scoreService to call getRecentScores() with the appropriate parameter
        //    and store those scores in a variable using List
        // 3. Return the variable containing those scores
            List<Score> scores = scoreService.getRecentScores();
            return ResponseEntity.ok(scores);
    }
    // TODO:
// 1. Add the appropriate annotation for a DELETE endpoint along with the appropriate endpoint
    @DeleteMapping("/{scoreId}")
    public  ResponseEntity<?> deleteScore(@PathVariable UUID scoreId) {
        // 3. create a try-catch block
        try {
            scoreService.deleteScore(scoreId);
            return ResponseEntity.ok("Score with ID " + scoreId + " was successfully deleted");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
