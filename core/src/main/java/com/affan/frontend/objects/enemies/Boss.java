package com.affan.frontend.objects.enemies;

import com.affan.frontend.objects.patterns.entityStrategy.FixedMovement;
import com.badlogic.gdx.graphics.Color;
import com.affan.frontend.objects.patterns.shootingStrategy.SpreadShot;

public class Boss extends Enemy {
    public Boss(String name, int hp) {
        super(380, 400, 64, 64, Color.BLUE, name, hp, 5000L);
        this.shootingPattern = new SpreadShot(200f, 3, 30f);
        this.movementPattern = new FixedMovement();
    }

    public Boss(float x, float y, String name, int hp) {
        super(x, y, 64, 64, Color.BLUE, name, hp, 5000L);
        this.shootingPattern = new SpreadShot(200f, 3, 30f);
    }
}
