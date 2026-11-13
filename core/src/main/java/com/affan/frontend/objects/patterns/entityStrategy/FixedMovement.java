package com.affan.frontend.objects.patterns.entityStrategy;

import com.affan.frontend.objects.GameObject;

public class FixedMovement implements EntityMovementPattern {
    @Override
    public void move(GameObject entity, float delta) {
        // Fixed position: no positional displacement applied to entity
    }
}

