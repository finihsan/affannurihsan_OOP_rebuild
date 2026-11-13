package com.affan.frontend.objects.patterns.entityStrategy;

import com.affan.frontend.objects.GameObject;

public interface EntityMovementPattern {
    void move(GameObject entity, float delta);
}

