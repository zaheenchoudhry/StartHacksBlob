package com.starthacks.blob;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class BackgroundGroup extends Group {

    private float unitX, unitY;
    private Image backgroundImage;
    private Texture backgroundImageTexture;
    private float moveSpeedX;
    private Texture treeTexture1;
    private Image tree1;

    public BackgroundGroup(float unitX, float unitY) {
        this.unitX = unitX;
        this.unitY = unitY;
        moveSpeedX = unitX * 0.1f;

        backgroundImageTexture = new Texture("bg-night.png");
        backgroundImage = new Image(backgroundImageTexture);
        backgroundImage.setSize(unitX * 150f, unitY * 100f);

        treeTexture1 = new Texture("mountain-lightest.png");
        tree1 = new Image(treeTexture1);
        tree1.setSize(unitX * 15f, unitY * 30f);
        tree1.setPosition(0, unitY * 15f);

        this.addActor(backgroundImage);
        this.addActor(tree1);
    }

    public void update(float moveDirection) {
        this.setX(this.getX() - moveSpeedX * moveDirection);
    }

    public void dispose() {
        backgroundImageTexture.dispose();
    }
}
