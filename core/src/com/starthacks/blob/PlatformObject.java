package com.starthacks.blob;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class PlatformObject extends Group {

    private float unitX, unitY;
    private Image platform;
    private Pixmap pixmap;
    private Texture texture;

    public PlatformObject(float unitX, float unitY, float platformSizeX, float platformSizeY) {
        this.unitX = unitX;
        this.unitY = unitY;

        pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(0, 0, 0, 1.0f);
        pixmap.fillRectangle(0, 0, 1, 1);
        texture = new Texture(pixmap);

        platform = new Image(texture);
        platform.setSize(platformSizeX, platformSizeY);

        this.addActor(platform);
    }

    public void update(float moveDirection) {

    }

    public void dispose() {
        pixmap.dispose();
        texture.dispose();
    }
}
