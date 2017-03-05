package com.starthacks.blob;

import com.badlogic.gdx.scenes.scene2d.Group;

import java.util.ArrayList;
import java.util.List;

public class Level1 extends AbstractLevel {

    private final int NUM_PLATFORMS = 5;

    public Level1(float unitX, float unitY) {
        super(unitX, unitY);

        for (int i = 0; i < NUM_PLATFORMS; ++i) {
            PlatformObject platformObject = new PlatformObject(this.texture, unitX, unitY);
            platformObjects.add(platformObject);
            this.addActor(platformObject);
        }

        platformObjects.get(0).setPlatformSize(unitX * 100f, unitY * 10f);
        platformObjects.get(0).setPosition(0, unitY * 0.05f);
        platformObjects.get(1).setPlatformSize(unitX * 30f, unitY * 10f);
        platformObjects.get(1).setPosition(unitX * 25f, unitY * 40f);
        platformObjects.get(2).setPlatformSize(unitX * 10f, unitY * 30f);
        platformObjects.get(2).setPosition(unitX * 45f, unitY * 10f);
        platformObjects.get(3).setPlatformSize(unitX * 30f, unitY * 10f);
        platformObjects.get(3).setPosition(unitX * 75f, unitY * 40f);
    }

    @Override
    public void update(float playerX, float playerY, float playerWidth, float playerHeight) {
        super.update(playerX, playerY, playerWidth, playerHeight);
    }
}
