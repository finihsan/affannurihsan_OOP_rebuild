package com.affan.frontend.systems;

import com.affan.frontend.objects.GameObject;
import com.affan.frontend.objects.Player;
import com.affan.frontend.objects.bullets.Bullet;
import com.affan.frontend.objects.enemies.Enemy;
import com.affan.frontend.objects.items.Item;
import com.affan.frontend.objects.items.ItemType;

import java.util.ArrayList;
import java.util.List;

public class CollisionReferee {

    public void resolveCollisions(Player player, List<GameObject> entities, BulletManager bulletManager) {
        if (player == null || entities == null || bulletManager == null) return;

        List<GameObject> newDrops = new ArrayList<>();

        // 1. Evaluate Player Bullets vs Enemies (Question 3)
        for (Bullet bullet : bulletManager.getActivePlayerBullets()) {
            if (bullet.isDestroyed()) continue;

            for (GameObject entity : entities) {
                if (entity instanceof Enemy enemy && !enemy.isDestroyed()) {
                    if (bullet.getCoreHitbox().overlaps(enemy.getCoreHitbox())) {
                        boolean defeated = enemy.takeDamage(bullet.getDamage());
                        if (defeated) {
                            player.addScore(enemy.getScoreValue());
                            newDrops.add(EntityFactory.createItem(enemy.getX(), enemy.getY(), ItemType.POWER));
                        }
                        bullet.destroy();
                        break;
                    }
                }
            }
        }

        // 2. Evaluate Enemy Bullets vs Player (Core Hit vs Graze Hit) (Question 4)
        for (Bullet bullet : bulletManager.getActiveEnemyBullets()) {
            if (bullet.isDestroyed()) continue;

            if (!player.isDestroyed()) {
                // PRIORITY 1: Core Hit (Player was Shot)
                if (bullet.getCoreHitbox().overlaps(player.getCoreHitbox())) {
                    player.takeDamage(100, bulletManager, entities);
                    bullet.destroy();
                }
                // PRIORITY 2: Graze Hit (Bullet Grazes Player)
                else if (!bullet.hasBeenGrazed() && bullet.getCoreHitbox().overlaps(player.getGrazeHitbox())) {
                    bullet.setGrazed(true);
                    player.addGraze();
                    System.out.println("[CollisionReferee] GRAZE! " + player.getName() + " grazed an enemy bullet (+50 pts)!");
                }
            }
        }

        // 3. Evaluate Player vs Items and Physical Enemy Contact (Question 5)
        for (GameObject entity : entities) {
            if (entity.isDestroyed()) continue;

            // CASE A: Item Collection
            if (entity instanceof Item item) {
                if (item.getCoreHitbox().overlaps(player.getCoreHitbox()) || item.getCoreHitbox().overlaps(player.getGrazeHitbox())) {
                    player.collectItem(item);
                }
            }
            // CASE B: Physical Contact with Enemy
            else if (entity instanceof Enemy enemy) {
                if (player.getCoreHitbox().overlaps(enemy.getCoreHitbox())) {
                    player.takeDamage(100, bulletManager, entities);                }
            }
        }

        entities.addAll(newDrops);

        for (Bullet bullet : bulletManager.getActiveEnemyBullets()) {
            if (bullet.isDestroyed()) continue;

            if (!player.isDestroyed()) {
                // Priority A: Direct Core Hitbox Collision (Damage & Life Loss)
                if (bullet.getCoreHitbox().overlaps(player.getCoreHitbox())) {
                    // TODO 1: Wrap the contents of this block with an `if (!player.isInvulnerable())` check,
                    if (!player.isInvulnerable()) {
                        System.out.println("[CollisionReferee] CORE HIT! Enemy Bullet hit " + player.getName() + " for " + bullet.getDamage() + " DMG!");
                        // TODO 2: Replace `player.takeDamage(bullet.getDamage())` with `player.takeDamage(100, bulletManager, entities)`
                        bullet.destroy(); // Mark bullet for recycling by BulletManager
                        // TODO 3: Add `break` at the end!
                        break;
                    }
                }
                // Priority B: Graze Hitbox Collision (Passes close to player without hitting core)
                else if (!bullet.hasBeenGrazed() && bullet.getCoreHitbox().overlaps(player.getGrazeHitbox())) {
                    bullet.setGrazed(true);
                    // TODO 4: Replace player.addScore(50) with a call to the player's addGraze() method
                    System.out.println("[CollisionReferee] GRAZE! " + player.getName() + " grazed an enemy bullet (+50 pts)!");
                }
            }
        }

        // 3. Mediator checks Player vs Items and Enemies (Core vs Graze)
        for (GameObject entity : entities) {
            // ... Item checking code from Module 7 remains the same ...
        } else if (entity instanceof Enemy enemy) {
            if (player.getCoreHitbox().overlaps(enemy.getCoreHitbox())) {
                if (!player.isInvulnerable()){
                // TODO 5: Wrap the contents of this block with `if (!player.isInvulnerable())`
                System.out.println("[CollisionReferee] CORE COLLISION! Player collided with " + enemy.getName() + "!");
                // TODO 6: Replace `player.takeDamage(10)` with `player.takeDamage(100, bulletManager, entities)`
                    }
            }
        }
    }
    }
}
