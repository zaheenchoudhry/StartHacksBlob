package com.starthacks.blob;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractLevel extends Group {

    protected float unitX, unitY;
    protected List<PlatformObject> platformObjects;
    protected List<GrassPlatform> GrassObjects;
    private Pixmap pixmap;
    protected Texture texture;
    protected boolean playerCanFallDown;
    protected float playerPositionX, playerPositionY;
    protected float destinationPositionX, destinationPositionY;

    public AbstractLevel(float unitX, float unitY) {
        this.unitX = unitX;
        this.unitY = unitY;
        playerCanFallDown = false;

        pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(0.163f, 0.145f, 0.165f, 1.0f);
        pixmap.fillRectangle(0, 0, 1, 1);
        texture = new Texture(pixmap);

        platformObjects = new ArrayList<PlatformObject>();
        GrassObjects = new ArrayList<GrassPlatform>();
    }

    public boolean playerCanMoveLeft(float playerX, float playerY, float playerWidth, float playerHeight) {
        int numOfPlatformObjects = platformObjects.size();
        int numOfGPlatformObjects = GrassObjects.size();
        for(int i = 0; i < numOfPlatformObjects; ++i) {
            PlatformObject platform = platformObjects.get(i);
            if ((playerX <= (platform.getX() + platform.getPlatformWidth() + this.getX())) &&
                    ((playerX + playerWidth) >= (platform.getX() + platform.getPlatformWidth() + this.getX())) &&
                    ((playerY >= (platform.getY() + this.getY()) && (playerY <= (platform.getY() + platform.getPlatformHeight() * 0.95f + this.getY()))) ||
                            ((playerY + playerHeight) >= (platform.getY() + this.getY()) && (playerY + playerHeight) <= (platform.getY() + platform.getPlatformHeight() + this.getY())))) {
                return false;
            }
        }

        for(int k = 0; k < numOfGPlatformObjects; ++k) {
            GrassPlatform gplatform = GrassObjects.get(k);
            if ((playerX <= (gplatform.getX() + gplatform.getGrassPlatformWidth() + this.getX())) &&
                    ((playerX + playerWidth) >= (gplatform.getX() + gplatform.getGrassPlatformWidth() + this.getX())) &&
                    ((playerY >= (gplatform.getY() + this.getY()) && (playerY <= (gplatform.getY() + gplatform.getGrassPlatformHeight() * 0.95f + this.getY()))) ||
                            ((playerY + playerHeight) >= (gplatform.getY() + this.getY()) && (playerY + playerHeight) <= (gplatform.getY() + gplatform.getGrassPlatformHeight() + this.getY())))) {
                return false;
            }
        }
        return true;
    }

    public boolean playerCanMoveRight(float playerX, float playerY, float playerWidth, float playerHeight) {
        int numOfPlatformObjects = platformObjects.size();
        int numOfGPlatformObjects = GrassObjects.size();
        for(int i = 0; i < numOfPlatformObjects; ++i) {
            PlatformObject platform = platformObjects.get(i);
            if (((playerX + playerWidth) >= (platform.getX() + this.getX())) && (playerX <= (platform.getX() + this.getX())) &&
                    ((playerY >= (platform.getY() + this.getY()) && (playerY <= (platform.getY() + platform.getPlatformHeight() + this.getY() - (0.05f * unitY)))) ||
                            ((playerY + playerHeight) >= (platform.getY() + this.getY()) && (playerY + playerHeight) <= (platform.getY() + platform.getPlatformHeight() + this.getY())))) {
                return false;
            }
        }

        for(int k = 0; k < numOfGPlatformObjects; ++k) {
            GrassPlatform gplatform = GrassObjects.get(k);
            if (((playerX + playerWidth) >= (gplatform.getX() + this.getX())) && (playerX <= (gplatform.getX() + this.getX())) &&
                    ((playerY >= (gplatform.getY() + this.getY()) && (playerY <= (gplatform.getY() + gplatform.getGrassPlatformHeight() + this.getY() - (0.05f * unitY)))) ||
                            ((playerY + playerHeight) >= (gplatform.getY() + this.getY()) && (playerY + playerHeight) <= (gplatform.getY() + gplatform.getGrassPlatformHeight() + this.getY())))) {
                return false;
            }
        }
        return true;
    }

    public boolean isPlatformAbove(float playerX, float playerY, float playerWidth, float playerHeight) {
        int numOfPlatformObjects = platformObjects.size();
        int numOfGPlatformObjects = GrassObjects.size();
        for(int i = 0; i < numOfPlatformObjects; ++i) {
            PlatformObject platform = platformObjects.get(i);
            if (((playerY + playerHeight) >= (platform.getY() + this.getY())) &&
                    ((playerY + playerHeight) <= (platform.getY() + (unitY * 8f) + this.getY())) &&
                    ((playerX >= (platform.getX() + this.getX()) && playerX <= (platform.getX() + platform.getPlatformWidth() + this.getX() - (0.05f * unitX)))
                            || ((playerX + playerWidth) >= (platform.getX() + this.getX()) && (playerX + playerWidth) <= (platform.getX() + platform.getPlatformWidth() + this.getX())))) {
                return true;
            }
        }

        for(int k = 0; k < numOfGPlatformObjects; ++k) {
            GrassPlatform gplatform = GrassObjects.get(k);
            if (((playerY + playerHeight) >= (gplatform.getY() + this.getY())) &&
                    ((playerY + playerHeight) <= (gplatform.getY() + (unitY * 8f) + this.getY())) &&
                    ((playerX >= (gplatform.getX() + this.getX()) && playerX <= (gplatform.getX() + gplatform.getGrassPlatformWidth() + this.getX() - (0.05f * unitX)))
                            || ((playerX + playerWidth) >= (gplatform.getX() + this.getX()) && (playerX + playerWidth) <= (gplatform.getX() + gplatform.getGrassPlatformWidth() + this.getX())))) {
                return true;
            }
        }
        return false;
    }

    public void checkCollision(float playerX, float playerY, float playerWidth, float playerHeight) {
        playerCanFallDown = true;

        int numOfPlatformObjects = platformObjects.size();
        int numOfGPlatformObjects = GrassObjects.size();
        for(int i = 0; i < numOfPlatformObjects; ++i) {
            //System.out.println(this.getY());
            //System.out.println("This Y : " + this.getY());
            //System.out.println("PlayerY" + playerY);


            PlatformObject platform = platformObjects.get(i);
            //System.out.println("Platform Y" + platform.getY());
            if ((playerY <= (platform.getY() + platform.getPlatformHeight() + this.getY())) &&
                    (playerY >= (platform.getY() + platform.getPlatformHeight() + this.getY() - unitY * 8f)) &&
                    ((playerX >= (platform.getX() + this.getX()) && playerX <= (platform.getX() + platform.getPlatformWidth() + this.getX() - (0.05f * unitX)))
                            || ((playerX + playerWidth) >= (platform.getX() + this.getX()) && (playerX + playerWidth) <= (platform.getX() + platform.getPlatformWidth() + this.getX())))) {
                playerCanFallDown = false;
                playerPositionY = platform.getY() + platform.getPlatformHeight() + this.getY();
                break;
            }
        }

        for(int k = 0; k < numOfGPlatformObjects; ++k) {
            //System.out.println(this.getY());
            //System.out.println("This Y : " + this.getY());
            //System.out.println("PlayerY" + playerY);


            GrassPlatform gplatform = GrassObjects.get(k);
            //System.out.println("Platform Y" + platform.getY());
            if ((playerY <= (gplatform.getY() + gplatform.getGrassPlatformHeight() + this.getY())) &&
                    (playerY >= (gplatform.getY() + gplatform.getGrassPlatformHeight() + this.getY() - unitY * 8f)) &&
                    ((playerX >= (gplatform.getX() + this.getX()) && playerX <= (gplatform.getX() + gplatform.getGrassPlatformWidth() + this.getX() - (0.05f * unitX)))
                            || ((playerX + playerWidth) >= (gplatform.getX() + this.getX()) && (playerX + playerWidth) <= (gplatform.getX() + gplatform.getGrassPlatformWidth() + this.getX())))) {
                playerCanFallDown = false;
                playerPositionY = gplatform.getY() + gplatform.getGrassPlatformHeight() + this.getY();
                break;
            }
        }
    }
    /*
    (playerY <= (platform.getY() + platform.getHeight() + this.getY())) &&
            ((playerX >= (platform.getX() + this.getX()) && playerX <= (platform.getX() + platform.getWidth() + this.getX()))
            || ((playerX + playerWidth) >= (platform.getX() + this.getX()) && (playerX + playerWidth) <= (platform.getX() + platform.getWidth() + this.getX())))
    */
    public void update(float playerX, float playerY, float playerWidth, float playerHeight) {
        checkCollision(playerX, playerY, playerWidth, playerHeight);
    }

    public void dispose() {
        this.clearChildren();
        pixmap.dispose();
        texture.dispose();
    }

    public boolean canPlayerFallDown() {
        return playerCanFallDown;
    }

    public float getPlayerPositionX() {
        return playerPositionX;
    }

    public float getPlayerPositionY() {
        return playerPositionY;
    }

    public float getDestinationPositionX() {
        return destinationPositionX;
    }

    public float getDestinationPositionY() {
        return destinationPositionY;
    }
}
