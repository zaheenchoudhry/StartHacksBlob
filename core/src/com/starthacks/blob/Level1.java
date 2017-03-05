package com.starthacks.blob;

import com.badlogic.gdx.scenes.scene2d.Group;

import java.util.ArrayList;
import java.util.List;

public class Level1 extends AbstractLevel {

    private final int NUM_PLATFORMS = 6;

    public Level1(float unitX, float unitY) {
        super(unitX, unitY);
        destinationPositionX = 155f * unitX;
        destinationPositionY = 70f * unitY;

        for (int i = 0; i < NUM_PLATFORMS; ++i) {
            PlatformObject platformObject = new PlatformObject(this.texture, unitX, unitY);
            platformObjects.add(platformObject);
            this.addActor(platformObject);
        }

        platformObjects.get(0).setPlatformSize(unitX * 200f, unitY * 17f);
        platformObjects.get(0).setPosition(unitX * 0f, unitY * 0.04f);
        platformObjects.get(1).setPlatformSize(unitX * 30f, unitY * 10f);
        platformObjects.get(1).setPosition(unitX * 25f, unitY * 40f);
        platformObjects.get(2).setPlatformSize(unitX * 30f, unitY * 40f);
        platformObjects.get(2).setPosition(unitX * 25f, unitY * 10f);
        platformObjects.get(3).setPlatformSize(unitX * 30f, unitY * 10f);
        platformObjects.get(3).setPosition(unitX * 85f, unitY * 40f);
        platformObjects.get(4).setPlatformSize(unitX * 30f, unitY * 70f);
        platformObjects.get(4).setPosition(unitX * 140f, unitY * 0f);
        platformObjects.get(5).setPlatformSize(unitX * 30f, unitY * 160f);
        platformObjects.get(5).setPosition(unitX* 170f, unitY * 0f);
    }

    @Override
    public void update(float playerX, float playerY, float playerWidth, float playerHeight) {
        super.update(playerX, playerY, playerWidth, playerHeight);
    }
}
