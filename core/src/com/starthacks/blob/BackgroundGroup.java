package com.starthacks.blob;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class BackgroundGroup extends Group {

    private float unitX, unitY;
    private Image backgroundImage1, backgroundImage2, backgroundImage3;
    private Texture backgroundImageTexture1, backgroundImageTexture2, backgroundImageTexture3;
    private float moveSpeedX;
    private Texture treeTexture1;
    private Image tree1;

    public BackgroundGroup(float unitX, float unitY) {
        this.unitX = unitX;
        this.unitY = unitY;
        moveSpeedX = unitX * 0.1f;

        backgroundImageTexture1 = new Texture("bg-night.png");
        backgroundImage1 = new Image(backgroundImageTexture1);
        backgroundImage1.setSize(unitX * 174f, unitY * 116f);
        backgroundImage1.setVisible(true);

        backgroundImageTexture2 = new Texture("bg-evening.png");
        backgroundImage2 = new Image(backgroundImageTexture2);
        backgroundImage2.setSize(unitX * 186f, unitY * 124f);
        backgroundImage2.setVisible(false);

        backgroundImageTexture3 = new Texture("bg-day.png");
        backgroundImage3 = new Image(backgroundImageTexture3);
        backgroundImage3.setSize(unitX * 180f, unitY * 120f);
        backgroundImage3.setVisible(false);


        treeTexture1 = new Texture("mountain-lightest.png");
        tree1 = new Image(treeTexture1);
        tree1.setSize(unitX * 15f, unitY * 30f);
        tree1.setPosition(0, unitY * 15f);

        this.addActor(backgroundImage1);
        this.addActor(backgroundImage2);
        this.addActor(backgroundImage3);
        this.addActor(tree1);
    }

    public void reset(int i) {
        backgroundImage1.setVisible(false);
        backgroundImage2.setVisible(false);
        backgroundImage3.setVisible(false);

        if (i == 1) {
            backgroundImage1.setY(0);
            backgroundImage1.setVisible(true);
        } else if (i == 2) {
            backgroundImage2.setY(0);
            backgroundImage2.setVisible(true);
        } else if (i == 3) {
            backgroundImage3.setY(0);
            backgroundImage3.setVisible(true);
        }
    }

    public void update(float moveDirection) {
        this.setX(this.getX() - moveSpeedX * moveDirection);
    }

    public void updateY(int i, float position) {
        if (i == 1) {
            backgroundImage1.setY(position);
        } else if (i == 2) {
            backgroundImage2.setY(position);
        } else if (i == 3) {
            backgroundImage3.setY(position);
        }
    }

    public void moveBackground(float moveDisplacement) {
        this.setY(this.getY() - moveDisplacement);
    }

    public void dispose() {
        backgroundImageTexture1.dispose();
        backgroundImageTexture2.dispose();
        backgroundImageTexture3.dispose();
    }
}
