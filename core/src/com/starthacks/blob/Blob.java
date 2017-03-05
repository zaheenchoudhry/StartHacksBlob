package com.starthacks.blob;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Blob extends Group {

    private float unitX, unitY;
    private float moveSpeedX;
    private float currentBlobSize;
    private Image blob;
    private Pixmap pixmap;
    private Texture texture;

    public Blob(float unitX, float unitY) {
        this.unitX = unitX;
        this.unitY = unitY;
        this.moveSpeedX = unitX * 0.8f;
        this.currentBlobSize = unitX * 10f;

        pixmap = new Pixmap((int)currentBlobSize, (int)currentBlobSize, Pixmap.Format.RGBA8888);
        pixmap.setColor(0, 0, 0, 1.0f);
        pixmap.fillRectangle(0, 0, (int)currentBlobSize, (int)currentBlobSize);
        texture = new Texture(pixmap);

        blob = new Image(texture);
        blob.setSize(currentBlobSize, currentBlobSize);

        this.addActor(blob);
    }

    public float getPlayerWidth() {
       return currentBlobSize;
    }

    public float getPlayerHeight() {
        return currentBlobSize;
    }

    public void update(float moveDirection) {
        if (((int)moveDirection == 1 && (this.getX() + currentBlobSize) < unitX * 88f) ||
                ((int)moveDirection == -1 && this.getX() > unitX * 12f)) {
            this.setX(this.getX() + moveSpeedX * moveDirection);
        }
    }


    public void dispose() {
        pixmap.dispose();
        texture.dispose();
    }
}
