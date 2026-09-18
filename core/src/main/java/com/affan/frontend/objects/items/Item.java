package com.affan.frontend.objects.items;

import com.affan.frontend.objects.GameObject;
import com.badlogic.gdx.graphics.Color;
import com.affan.frontend.objects.Collidable;
import com.affan.frontend.objects.Player;

public class Item extends GameObject {
    private String itemType;
    private long scoreValue;
    private ItemType itemTypeEnum;

    // Constructors legacy Modul 2
    public Item(float x, float y, String itemType) {
        super(x, y, 16, 16, 100f, Color.WHITE);
        this.itemType = itemType;
        this.scoreValue = 1000L;
    }

    public Item(float x, float y, float width, float height, float speed, String itemType) {
        super(x, y, width, height, speed, Color.WHITE);
        this.itemType = itemType;
        this.scoreValue = 1000L;
    }

    public Item(float x, float y, float width, float height, float speed, String itemType, long scoreValue) {
        super(x, y, width, height, speed, Color.WHITE);
        this.itemType = itemType;
        this.scoreValue = scoreValue;
    }

    // Constructor baru Modul 3
    public Item(float x, float y, ItemType itemTypeEnum) {
        super(x, y, 16, 16, 100f, Color.WHITE);
        this.itemTypeEnum = itemTypeEnum;
        this.itemType = itemTypeEnum.name();
        this.scoreValue = itemTypeEnum.getScoreValue();
    }

    public Item(float x, float y, float width, float height, float speed, ItemType itemTypeEnum, long scoreValue) {
        super(x, y, width, height, speed, Color.WHITE);
        this.itemTypeEnum = itemTypeEnum;
        this.itemType = itemTypeEnum.name();
        this.scoreValue = scoreValue;
    }

    public ItemType getItemTypeEnum() {
        return itemTypeEnum;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public long getScoreValue() {
        return scoreValue;
    }

    public void setScoreValue(long scoreValue) {
        this.scoreValue = scoreValue;
    }

    @Override
    public void update(float delta) {
        this.y -= speed * delta;
    }
    @Override
    public void onCollision(Collidable other) {
        if (other instanceof Player) {
            // Item pickup is handled on the Player side via collectItem()
        }
    }
}
