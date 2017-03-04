package com.starthacks.blob;

import com.badlogic.gdx.scenes.scene2d.Group;

import java.util.ArrayList;
import java.util.List;

public class Level1 extends Group {

    private float unitX, unitY;
    private List<PlatformObject> platformObjects;

    public Level1(float unitX, float unitY) {
        this.unitX = unitX;
        this.unitY = unitY;

        platformObjects = new ArrayList<PlatformObject>();
    }

    public void checkCollision(float playerX, float playerY, float playerWidth, float playerHeight) {

    }

    public void update(float playerX, float playerY, float playerWidth, float playerHeight) {
        checkCollision(playerX, playerY, playerWidth, playerHeight);
    }

    public void dispose() {

    }
}
