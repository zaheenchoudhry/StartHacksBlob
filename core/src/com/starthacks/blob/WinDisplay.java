package com.starthacks.blob;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class WinDisplay extends Group {

    private float unitX, unitY;
    private Pixmap overlayPixmap;
    private Texture overlayTexture;
    private Image overlay;

    public WinDisplay(float unitX, float unitY) {
        this.unitX = unitX;
        this.unitY = unitY;

        overlayPixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        overlayPixmap.setColor(0.163f, 0.145f, 0.165f, 1.0f);
        overlayPixmap.fillRectangle(0, 0, 1, 1);
        overlayTexture = new Texture(overlayPixmap);

        overlay = new Image(overlayTexture);
        overlay.setSize(unitX * 100f, unitY * 100f);

        this.addActor(overlay);
    }

    public void update() {

    }

    public void dispose() {
        overlayTexture.dispose();
        overlayPixmap.dispose();
    }
}
