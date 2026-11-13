package com.affan.frontend.objects.patterns.bulletStrategy;

import com.affan.frontend.objects.bullets.Bullet;

public interface BulletMovementPattern {
    void move(Bullet bullet, float delta);
}

