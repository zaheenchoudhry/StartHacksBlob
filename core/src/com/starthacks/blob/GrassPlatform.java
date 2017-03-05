package com.starthacks.blob;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class GrassPlatform extends Group {

    private float unitX, unitY, platformSizeX, platformSizeY;
    private Image platform;
    private Texture texture;

    public GrassPlatform(Texture texture, float unitX, float unitY) {
        this.unitX = unitX;
        this.unitY = unitY;
        this.texture = texture;

        texture= new Texture ("platform.png");
        platform = new Image(texture);

        this.addActor(platform);
    }

    public void setGrassPlatformSize(float platformSizeX, float platformSizeY) {
        this.platformSizeX = platformSizeX;
        this.platformSizeY = platformSizeY;
        platform.setSize(platformSizeX, platformSizeY);
    }

    public void update(float moveDirection) {

    }

    public void dispose() {
    }

    public float getGrassPlatformWidth() {
        return platformSizeX;
    }

    public float getGrassPlatformHeight() {
        return platformSizeY;
    }
}
