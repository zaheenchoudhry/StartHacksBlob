package com.starthacks.blob;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Blob extends Group {

    private float unitX, unitY;
    private float moveSpeedX;
    private Image blob;
    private Pixmap pixmap;
    private Texture texture;

    public Blob(float unitX, float unitY) {
        this.unitX = unitX;
        this.unitY = unitY;
        this.moveSpeedX = unitX * 0.5f;

        pixmap = new Pixmap((int)(unitX * 10f), (int)(unitX * 10f), Pixmap.Format.RGBA8888);
        pixmap.setColor(0, 0, 0, 1.0f);
        pixmap.fillRectangle(0, 0, (int)(unitX * 10f), (int)(unitX * 10f));
        texture = new Texture(pixmap);

        blob = new Image(texture);
        blob.setSize(unitX * 10f, unitX * 10f);
        blob.setPosition(0, 0);

        this.addActor(blob);
    }

    public void update(float moveDirection) {
        this.setX(this.getX() + moveSpeedX * moveDirection);
    }

    public void dispose() {
        pixmap.dispose();
        texture.dispose();
    }
}
