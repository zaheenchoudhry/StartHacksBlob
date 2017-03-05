package com.starthacks.blob;


import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.util.ArrayList;
import java.util.List;

public class Level3 extends AbstractLevel {

    private final int NUM_PLATFORMS = 12;
//    private Texture grasstexture;
//    private Image grass;


    public Level3(float unitX, float unitY) {
        super(unitX, unitY);
        destinationPositionX = 5f * unitX;
        destinationPositionY = 115f * unitY;

//        grasstexture=new Texture ("platform.png");
//        grass=new Image (grasstexture);

        for (int i = 0; i < NUM_PLATFORMS; ++i) {
            GrassPlatform platformObject = new GrassPlatform(this.texture, unitX, unitY);
            GrassObjects.add(platformObject);
            this.addActor(platformObject);
        }

        GrassObjects.get(0).setGrassPlatformSize(unitX * 300f, unitY * 10f);
        GrassObjects.get(0).setPosition(unitX * 0f, unitY * 0.5f);

        GrassObjects.get(2).setGrassPlatformSize(unitX * 10f, unitY * 35f);
        GrassObjects.get(2).setPosition(unitX * 50f, unitY * 10f);

        GrassObjects.get(1).setGrassPlatformSize(unitX * 30f, unitY * 10f);
        GrassObjects.get(1).setPosition(unitX * 30f, unitY * 35f);

        GrassObjects.get(3).setGrassPlatformSize(unitX * 30f, unitY * 10f);
        GrassObjects.get(3).setPosition(unitX * 70f, unitY * 50f);

        GrassObjects.get(4).setGrassPlatformSize(unitX * 30f, unitY * 10f);
        GrassObjects.get(4).setPosition(unitX * 110f, unitY * 27f);

        GrassObjects.get(5).setGrassPlatformSize(unitX * 30f, unitY * 10f);
        GrassObjects.get(5).setPosition(unitX * 150f, unitY * 55f);

        GrassObjects.get(6).setGrassPlatformSize(unitX * 30f, unitY * 10f);
        GrassObjects.get(6).setPosition(unitX * 175f, unitY * 85f);

        GrassObjects.get(7).setGrassPlatformSize(unitX * 30f, unitY * 145f);
        GrassObjects.get(7).setPosition(unitX * 205f, unitY * 0.05f);

        GrassObjects.get(8).setGrassPlatformSize(unitX * 30f, unitY * 10f);
        GrassObjects.get(8).setPosition(unitX * 125f, unitY * 115f);

        GrassObjects.get(9).setGrassPlatformSize(unitX * 30f, unitY * 10f);
        GrassObjects.get(9).setPosition(unitX * 85f, unitY * 145f);

        GrassObjects.get(10).setGrassPlatformSize(unitX * 30f, unitY * 10f);
        GrassObjects.get(10).setPosition(unitX * 45f, unitY * 125f);

        GrassObjects.get(11).setGrassPlatformSize(unitX * 30f, unitY * 10f);
        GrassObjects.get(11).setPosition(unitX * 0f, unitY * 105f);
    }

    @Override
    public void update(float playerX, float playerY, float playerWidth, float playerHeight) {
        super.update(playerX, playerY, playerWidth, playerHeight);
    }
}

