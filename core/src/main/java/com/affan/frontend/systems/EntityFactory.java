package com.affan.frontend.systems;

import com.affan.frontend.objects.enemies.Boss;
import com.affan.frontend.objects.enemies.Fairy;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class EntityFactory {
    public static Boss createBoss(float x, float y, String name, int hp) {
        // TODO:
        // 1. Create a new Boss using x, y, name, and hp from the parameters;
        //    store it in a local variable named `boss`.
        // 2. Retrieve the "boss_idle" animation using getAnimation(...)
        //    from AssetManager.getInstance(). Store the result in
        //    a local variable named `idleAnim`.
        // 3. Assign idleAnim to boss using boss.setAnimation(...).
        // 4. Return boss.
        Boss boss = new Boss(x, y, name, hp);
        Animation<TextureRegion> idleAnim = AssetManager.getInstance().getAnimation("boss_Idle");
        boss.setAnimation(idleAnim);
        return boss;
    }

    public static Fairy createFairy(float x, float y, String name, int hp) {
        // TODO:
        // 1. Create a new Fairy using x, y, name, and hp from the parameters;
        //    store it in a local variable named `fairy`.
        // 2. Retrieve the "fairy_idle_red" animation using getAnimation(...)
        //    from AssetManager.getInstance(). Store the result in
        //    a local variable named `idleAnim`.
        // 3. Assign idleAnim to fairy using fairy.setAnimation(...).
        // 4. Return fairy.
        Fairy fairy = new Fairy(x, y, name, hp);
        Animation<TextureRegion> idleAnim = AssetManager.getInstance().getAnimation("fairy_idle_red");
        fairy.setAnimation(idleAnim);
        return fairy;
    }

    public static Fairy createFairy(float x, float y, String name, int hp, String keyString) {
        // TODO:
        // 1. Create a new Fairy using x, y, name, and hp from the parameters;
        //    store it in a local variable named `fairy`.
        // 2. Retrieve the animation for keyString using getAnimation(...)
        //    from AssetManager.getInstance(). Store the result in
        //    a local variable named `idleAnim`.
        // 3. Assign idleAnim to fairy using fairy.setAnimation(...).
        // 4. Return fairy.
        Fairy fairy = new Fairy(x, y, name, hp);
        Animation<TextureRegion> idleAnim = AssetManager.getInstance().getAnimation(keyString);
        fairy.setAnimation(idleAnim);
        return fairy;
    }
}
