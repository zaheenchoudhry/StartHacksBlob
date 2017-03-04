package com.starthacks.blob;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractLevel extends Group {

    protected float unitX, unitY;
    protected List<PlatformObject> platformObjects;
    private Pixmap pixmap;
    protected Texture texture;

    public AbstractLevel(float unitX, float unitY) {
        this.unitX = unitX;
        this.unitY = unitY;

        pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(0, 0, 0, 1.0f);
        pixmap.fillRectangle(0, 0, 1, 1);
        texture = new Texture(pixmap);

        platformObjects = new ArrayList<PlatformObject>();
    }

    public void checkCollision(float playerX, float playerY, float playerWidth, float playerHeight) {

    }

    public void update(float playerX, float playerY, float playerWidth, float playerHeight) {
        checkCollision(playerX, playerY, playerWidth, playerHeight);
    }

    public void dispose() {
        pixmap.dispose();
        texture.dispose();
    }
}
