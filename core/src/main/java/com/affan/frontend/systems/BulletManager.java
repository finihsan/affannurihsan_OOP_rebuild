package com.affan.frontend.systems;

import com.affan.frontend.objects.GameObject;
import com.affan.frontend.objects.bullets.BulletType;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.affan.frontend.objects.bullets.Bullet;

import java.util.*;

public class BulletManager {
    // 1. Pool Queues for Object Recycling (Object Pool Pattern)
    private Queue<Bullet> playerBulletPool;
    private Queue<Bullet> enemyBulletPool;

    // 2. Lists of Active Bullets on Screen
    private List<Bullet> activePlayerBullets;
    private List<Bullet> activeEnemyBullets;

    public BulletManager() {
        playerBulletPool = new LinkedList<>();
        enemyBulletPool = new LinkedList<>();
        activePlayerBullets = new ArrayList<>();
        activeEnemyBullets = new ArrayList<>();
    }

    public Bullet spawnPlayerBullet(float x, float y, float vx, float vy, int damage) {
        Bullet bullet;
        // TODO:
        // 1. Check whether playerBulletPool is not empty.
        //    If so, poll from the pool. If not, create a new one using the Factory.
        if(playerBulletPool.isEmpty()){
            bullet = playerBulletPool.poll();
        }
        else{
        }
        // 2. Re-initialize the bullet.
        bullet.init(x, y, vx, vy, BulletType.AMULET, damage, true);
        // 3. Add the bullet to the activePlayerBullets list.
        activePlayerBullets.add(bullet);
        // 4. Return the bullet instance.
        return bullet;
    }

    public Bullet spawnEnemyBullet(float x, float y, float vx, float vy, int damage) {
        Bullet bullet;
        // TODO:
        // 1. Check whether enemyBulletPool is not empty:
        //    If so, poll from the pool. If not, create a new one using the Factory.
        if (enemyBulletPool.isEmpty()) {
            bullet = enemyBulletPool.poll();
        } else {

        }

        // 2. Re-initialize the bullet.
        bullet.init(x, y, vx, vy, BulletType.AMULET, damage, true);
        // 3. Add the bullet to the activeEnemyBullets list
        activeEnemyBullets.add(bullet);
        // 4. Return the bullet instance
        return bullet;
    }

    public void update(float delta, float screenWidth, float screenHeight) {
        // 1. Player bullet recycling & movement (5 Points)
        // TODO:
        // - Copy-paste the iterator loop from updateAndClean() in Main.java
        Iterator<Bullet> piterator = activePlayerBullets.iterator();
        while (piterator.hasNext()) {
            Bullet entity = piterator.next();
            entity.update(delta);

            if (entity.isOffScreen(screenWidth, screenHeight) || entity.isDestroyed()) {
                piterator.remove(); // Safe removal using Iterator!
                entity.destroy();               // Reset active flag
                playerBulletPool.offer(entity); // Return to pool for reuse!
            }
        }
        // - Change the class referenced by the iterator to `Bullet` instead of T
        //   and change the referenced list to activePlayerBullets
        // - Change the check to whether the bullet is off-screen
        //   OR the bullet is already marked as destroyed
        //   - If YES:
        //     * Mark the bullet as destroyed (destroy)
        //     * Safely remove the bullet from the active bullet list
        //     * Return the bullet to the pool queue

        // 2. Enemy bullet recycling & movement (5 Points)
        // TODO:
        // - Follow the exact same procedure as above for activeEnemyBullets
        // - Enemy bullets that go off-screen or are destroyed are returned to enemyBulletPool via offer()
        Iterator<Bullet> eiterator = activeEnemyBullets.iterator();
        while (eiterator.hasNext()) {
            Bullet entity = eiterator.next();
            entity.update(delta);

            if (entity.isOffScreen(screenWidth, screenHeight) || entity.isDestroyed()) {
                eiterator.remove(); // Safe removal using Iterator!
                entity.destroy();               // Reset active flag
                playerBulletPool.offer(entity); // Return to pool for reuse!
            }
        }
    }

    public void render(SpriteBatch batch) {
        for (Bullet b : activePlayerBullets) {
            if (!b.isDestroyed()) b.render(batch);
        }
        for (Bullet b : activeEnemyBullets) {
            if (!b.isDestroyed()) b.render(batch);
        }
    }

    // Helper methods to access pools and active lists
    public List<Bullet> getActivePlayerBullets() { return activePlayerBullets; }
    public List<Bullet> getActiveEnemyBullets() { return activeEnemyBullets; }
    public int getPlayerPoolSize() { return playerBulletPool.size(); }
    public int getEnemyPoolSize() { return enemyBulletPool.size(); }
}

