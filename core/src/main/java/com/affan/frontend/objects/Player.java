package com.affan.frontend.objects;

import com.affan.frontend.objects.enemies.Enemy;
import com.affan.frontend.objects.items.Item;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.affan.frontend.objects.items.ItemType;


public class Player extends GameObject {
    private String name;
    private int hp;
    private int power;
    private int spellCards;
    private long score;

    public Player(String name, int hp, int power, int spellCards) {
        super(280, 40, 32, 32, 200f, Color.RED);
        this.name = name;
        this.hp = Math.max(0, hp);
        this.power = power;
        this.spellCards = spellCards;
        this.score = 0;
    }

    public Player(float x, float y, String name, int hp, int power, int spellCards) {
        super(x, y, 32, 32, 200f, Color.RED);
        this.name = name;
        this.hp = Math.max(0, hp);
        this.power = power;
        this.spellCards = spellCards;
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = Math.max(0, hp);
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getSpellCards() {
        return spellCards;
    }

    public void setSpellCards(int spellCards) {
        this.spellCards = spellCards;
    }

    public long getScore() {
        return score;
    }

    public void addScore(long points) {
        if (points > 0) {
            this.score += points;
            System.out.println(getName() + " gained " + points + " pts! Total Score: " + this.score);
        }
    }

    public void collectItem(Item item) {
        ItemType type = item.getItemTypeEnum();
        if (type != null) {
            switch (type) {
                case POWER -> {
                    // 1. Increase power by type.getPowerBonus() via this.power
                    this.power += type.getPowerBonus();
                    // 2. Add score by item.getScoreValue() via addScore() (addScore() already automatically prints "gained X pts!")
                    addScore(item.getScoreValue());
                    // 3. Print: [name] collected POWER item! Power increased to [power]
                    System.out.println(name + "collected POINT item");
                    System.out.println(name + "collected POWER item power incresed to " + power);

                }
                case POINT -> {
                    // 1. Add score by item.getScoreValue() via addScore()
                    addScore(item.getScoreValue());
                    // 2. Print: [name] collected POINT item!
                }
                case BOMB -> {
                    // 1. Increase spellCards by 1
                    spellCards += 1;
                    // 2. Add score by item.getScoreValue() via addScore()
                    addScore(item.getScoreValue());
                    // 3. Print: [name] collected BOMB item! SpellCards: [spellCards]
                    System.out.println(name + "collected BOMB item! Spellcards: " + spellCards);

                }
                case LIFE -> {
                    // 1. Increase hp by 20
                    hp += 20;
                    // 2. Add score by item.getScoreValue() via addScore()
                    addScore(item.getScoreValue());
                    // 3. Print: [name] collected LIFE item! HP: [hp]
                    System.out.println(name + "collected LIFE item! HP: " + hp);
                }
            }
        } else {
            addScore(item.getScoreValue());
            System.out.println(name + " collected " + item.getItemType() + "!");
        }
    }

    public void takeDamage(int damage) {
        setHp(getHp() - damage);

        if (getHp() > 0) {
            System.out.println(getName() + " took " + damage + " damage! Remaining HP: " + getHp());
        } else {
            System.out.println(getName() + " was defeated!");
        }
    }

    public void shoot(Enemy target) {
        int damage = 10 + getPower();
        System.out.println(getName() + " shoots " + target.getName() + " dealing " + damage + " DMG!");
        if (target.takeDamage(damage)) {
            addScore(target.getScoreValue());
        }
    }

    public boolean isAlive() {
        return getHp() > 0;
    }
    @Override
    public void update(float delta) {
        if (Gdx.input != null) {
            // TODO: Check W / UP input   → y += 200f * delta
            if(Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP) )
            {
                y += speed * delta;
            }
            // TODO: Check S / DOWN input → y -= 200f * delta
            else if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN) )
            {
                y -= speed * delta;
            }
            // TODO: Check A / LEFT input → x -= speed * delta
            else if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT))
            {
                x -= speed * delta;
            }
            // TODO: Check D / RIGHT input → x += speed * delta
            else if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            {
                x += speed * delta;
            }
        }
    }

    @Override
    public void onCollision(Collidable other) {
        // TODO: Check whether the other received by this method is an Item
        if (other instanceof Item) {

            // TODO: Print "Player touches items" then call collectItem((Item) other)
            System.out.println("Player touches items");

            collectItem((Item) other);
        }
    }

}
