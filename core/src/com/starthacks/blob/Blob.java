package com.starthacks.blob;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.util.ArrayList;

public class Blob extends Group {

    private float unitX, unitY;
    private float moveSpeedX;
    private float currentBlobSize;
    private Image blobright1;
    private Image blobright2;
    private Image blobright3;
    private Image blobright4;
    private Image blobright5;
    private Image blobright6;
    private Image blobleft1;
    private Image blobleft2;
    private Image blobleft3;
    private Image blobleft4;
    private Image blobleft5;
    private Image blobleft6;
    private Texture textureright1;
    private Texture textureright2;
    private Texture textureright3;
    private Texture textureright4;
    private Texture textureright5;
    private Texture textureright6;
    private Texture textureleft1;
    private Texture textureleft2;
    private Texture textureleft3;
    private Texture textureleft4;
    private Texture textureleft5;
    private Texture textureleft6;
    private ArrayList<Image> rightarray;
    private ArrayList<Image> leftarray;
    private int counter;
    private int counterleft;
    private int currentindex;
//    private Pixmap pixmap;

    public Blob(float unitX, float unitY) {
        this.unitX = unitX;
        this.unitY = unitY;
        this.moveSpeedX = unitX * 0.8f;
        this.currentBlobSize = unitX * 10f;
        currentindex=0;
//        pixmap = new Pixmap((int)currentBlobSize, (int)currentBlobSize, Pixmap.Format.RGBA8888);
//        pixmap.setColor(0, 0, 0, 1.0f);
//        pixmap.fillRectangle(0, 0, (int)currentBlobSize, (int)currentBlobSize);

        rightarray=new ArrayList<Image>();
        leftarray=new ArrayList<Image>();

        textureright1 = new Texture("right 1.png");
        textureright2 = new Texture("right 2.png");
        textureright3 = new Texture("right 3.png");
        textureright4 = new Texture("right 4.png");
        textureright5 = new Texture("right 5.png");
        textureright6 = new Texture("right 6.png");
        textureleft1 = new Texture ("left1.png");
        textureleft2 = new Texture ("left2.png");
        textureleft3 = new Texture ("left3.png");
        textureleft4 = new Texture ("left4.png");
        textureleft5 = new Texture ("left5.png");
        textureleft6 = new Texture ("left6.png");

        blobright1 = new Image(textureright1);
        blobright2 = new Image(textureright2);
        blobright3 = new Image(textureright3);
        blobright4 = new Image(textureright4);
        blobright5 = new Image(textureright5);
        blobright6 = new Image(textureright6);
        blobleft1 = new Image (textureleft1);
        blobleft2 = new Image (textureleft2);
        blobleft3 = new Image (textureleft3);
        blobleft4 = new Image (textureleft4);
        blobleft5 = new Image (textureleft5);
        blobleft6 = new Image (textureleft6);

        blobright1.setY(-currentBlobSize * 0.15f);
        blobright2.setY(-currentBlobSize * 0.15f);
        blobright3.setY(-currentBlobSize * 0.15f);
        blobright4.setY(-currentBlobSize * 0.15f);
        blobright5.setY(-currentBlobSize * 0.15f);
        blobright6.setY(-currentBlobSize * 0.15f);
        blobleft1.setY(-currentBlobSize * 0.15f);
        blobleft2.setY(-currentBlobSize * 0.15f);
        blobleft3.setY(-currentBlobSize * 0.15f);
        blobleft4.setY(-currentBlobSize * 0.15f);
        blobleft5.setY(-currentBlobSize * 0.15f);
        blobleft6.setY(-currentBlobSize * 0.15f);

        blobright1.setX(-currentBlobSize * 0.15f);
        blobright2.setX(-currentBlobSize * 0.15f);
        blobright3.setX(-currentBlobSize * 0.15f);
        blobright4.setX(-currentBlobSize * 0.15f);
        blobright5.setX(-currentBlobSize * 0.15f);
        blobright6.setX(-currentBlobSize * 0.15f);

        blobright1.setSize(currentBlobSize, currentBlobSize);
        blobright2.setSize(currentBlobSize, currentBlobSize);
        blobright3.setSize(currentBlobSize, currentBlobSize);
        blobright4.setSize(currentBlobSize, currentBlobSize);
        blobright5.setSize(currentBlobSize, currentBlobSize);
        blobright6.setSize(currentBlobSize, currentBlobSize);
        blobleft1.setSize(currentBlobSize, currentBlobSize);
        blobleft2.setSize(currentBlobSize, currentBlobSize);
        blobleft3.setSize(currentBlobSize, currentBlobSize);
        blobleft4.setSize(currentBlobSize, currentBlobSize);
        blobleft5.setSize(currentBlobSize, currentBlobSize);
        blobleft6.setSize(currentBlobSize, currentBlobSize);

        blobright1.setVisible(true);
        blobright2.setVisible(false);
        blobright3.setVisible(false);
        blobright4.setVisible(false);
        blobright5.setVisible(false);
        blobright6.setVisible(false);
        blobleft1.setVisible(false);
        blobleft2.setVisible(false);
        blobleft3.setVisible(false);
        blobleft4.setVisible(false);
        blobleft5.setVisible(false);
        blobleft6.setVisible(false);

        rightarray.add(blobright1);
        rightarray.add(blobright2);
        rightarray.add(blobright3);
        rightarray.add(blobright4);
        rightarray.add(blobright5);
        rightarray.add(blobright6);
        leftarray.add(blobleft1);
        leftarray.add(blobleft2);
        leftarray.add(blobleft3);
        leftarray.add(blobleft4);
        leftarray.add(blobleft5);
        leftarray.add(blobleft6);

        this.addActor(blobright1);
        this.addActor(blobleft1);


    }

    public float getPlayerWidth() {
        return currentBlobSize * 0.85f;
    }

    public float getPlayerHeight() {
        return currentBlobSize* 0.85f;
    }

    public void update(float moveDirection) {
        if ((int) moveDirection == 1 && (this.getX() + currentBlobSize) < unitX * 88f) {
            this.setX(this.getX() + moveSpeedX * moveDirection);
            counter += 1;
            if (counter >= 5) {
                counter = 0;
                rightarray.get(currentindex).setVisible(false);
                currentindex += 1;
                currentindex = (currentindex >= 6) ? 0 : currentindex;
                clearChildren();
                rightarray.get(currentindex).setVisible(true);
                this.addActor(rightarray.get(currentindex));
            }
        } else if ((int) moveDirection == -1 && this.getX() > unitX * 12f) {
            this.setX(this.getX() + moveSpeedX * moveDirection);
            counter += 1;
            if (counter >= 5) {
                counter = 0;
                leftarray.get(currentindex).setVisible(false);
                currentindex += 1;
                currentindex = (currentindex >= 6) ? 0 : currentindex;
                clearChildren();
                leftarray.get(currentindex).setVisible(true);
                this.addActor(leftarray.get(currentindex));
            }
        }
    }

    public void dispose() {
//        pixmap.dispose();
        textureright1.dispose();
        textureright2.dispose();
        textureright3.dispose();
        textureright4.dispose();
        textureright5.dispose();
        textureright6.dispose();
        textureleft1.dispose();
        textureleft2.dispose();
        textureleft3.dispose();
        textureleft4.dispose();
        textureleft5.dispose();
        textureleft6.dispose();

    }
}
