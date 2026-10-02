package com.affan.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "scores")
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID scoreId;

    private String playerName;
    private Integer point;

    private LocalDateTime createdAt;

    // Menampung waktu pembuatan data secara otomatis sebelum disimpan ke DB
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // Constructor Default (Wajib untuk JPA)
    public Score() {
    }

    // Constructor dengan Parameter
    public Score(String playerName, Integer point) {
        this.playerName = playerName;
        this.point = point;
    }

    // Getter dan Setter
    public UUID getScoreId() {
        return scoreId;
    }

    public void setScoreId(UUID scoreId) {
        this.scoreId = scoreId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
