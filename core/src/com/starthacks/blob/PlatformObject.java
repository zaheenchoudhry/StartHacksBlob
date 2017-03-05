package com.starthacks.blob;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class PlatformObject extends Group {

    private float unitX, unitY, platformSizeX, platformSizeY;
    private Image platform;
    private Texture texture;

    public PlatformObject(Texture texture, float unitX, float unitY) {
        this.unitX = unitX;
        this.unitY = unitY;
        this.texture = texture;

        platform = new Image(texture);

        this.addActor(platform);
    }

    public void setPlatformSize(float platformSizeX, float platformSizeY) {
        this.platformSizeX = platformSizeX;
        this.platformSizeY = platformSizeY;
        platform.setSize(platformSizeX, platformSizeY);
    }

    public void update(float moveDirection) {

    }

    public void dispose() {
    }

    public float getPlatformWidth() {
        return platformSizeX;
    }

    public float getPlatformHeight() {
        return platformSizeY;
    }
}
