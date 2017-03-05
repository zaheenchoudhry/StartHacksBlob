package com.starthacks.blob;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;

public class GameScreen extends AbstractScreen {

    private float playerSpeedY, gravity;
    private float currentPlayerSpeedY, currentGravity;
    private float dropSpeedY, dropGravity;
    private Blob player;
    private int lastDownPointer;
    private boolean playerIsJumping, isPlayerDead;
    private float playerMoveDirection; // -1 (left), 0 (stay), 1 (right)
    private AbstractLevel level;

    public GameScreen(final MainActivity game) {
        super(game);
        playerSpeedY = 6.5f * UNIT_Y;
        gravity = 0.5f * UNIT_Y;
        playerIsJumping = false;
        isPlayerDead = false;
        currentPlayerSpeedY = playerSpeedY;
        currentGravity = gravity;
        dropSpeedY = 0;
        dropGravity = gravity;

        level = new Level1(UNIT_X, UNIT_Y);
        //level = new Level_2(UNIT_X,UNIT_Y);

        player = new Blob(UNIT_X, UNIT_Y);
        player.setPosition(UNIT_X * 10f, UNIT_Y * 10f);

        this.addActor(level);
        this.addActor(player);
    }

    @Override
    public void show() {
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(this);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void resize(int width, int height) {
        // set initial sizes and positions
    }

    private void checkTouchCond(int pointer) {
        // jump
        if (touches.get(pointer).touchX <= SCREEN_WIDTH * 0.30f) {
            if (!playerIsJumping) {
                playerIsJumping = true;
                currentPlayerSpeedY = playerSpeedY;
                currentGravity = gravity;
            }
        } else if (touches.get(pointer).touchX >= SCREEN_WIDTH * 0.85f) {
            playerMoveDirection = 1;
        } else if (touches.get(pointer).touchX >= SCREEN_WIDTH * 0.65f) {
            playerMoveDirection = -1;
        } else {
            //playerMoveDirection = 0;
        }
    }

    private void checkTouch() {
        if (touches.get(0).touched && touches.get(1).touched) {
            checkTouchCond(lastDownPointer);
        } else if (touches.get(0).touched) {
            checkTouchCond(0);
        } else if (touches.get(1).touched) {
            checkTouchCond(1);
        }
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (pointer == 0 || pointer == 1) {
            lastDownPointer = pointer;
            touches.get(pointer).touchX = screenX;
            touches.get(pointer).touchY = screenY;
            touches.get(pointer).touched = true;
            checkTouch();
            return true;
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (pointer == 0 || pointer == 1) {
            touches.get(pointer).touchX = 0;
            touches.get(pointer).touchY = 0;
            touches.get(pointer).touched = false;
            playerMoveDirection = 0;
            checkTouch();
            return true;
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (pointer == 0 || pointer == 1) {
            lastDownPointer = pointer;
            touches.get(pointer).touchX = screenX;
            touches.get(pointer).touchY = screenY;
            touches.get(pointer).touched = true;
            checkTouch();
            return true;
        }
        return false;
    }

    @Override
    public void update() {
        // update values like position size etc
        if (!isPlayerDead) {
            if ((playerMoveDirection == -1 && !level.playerCanMoveLeft(player.getX(), player.getY(), player.getPlayerWidth(), player.getPlayerHeight())) ||
                    (playerMoveDirection == 1 && !level.playerCanMoveRight(player.getX(), player.getY(), player.getPlayerWidth(), player.getPlayerHeight()))) {
                player.update(0);
            } else {
                player.update(playerMoveDirection);
            }


            if (playerIsJumping) {
                if (level.isPlatformAbove(player.getX(), player.getY(), player.getPlayerWidth(), player.getPlayerHeight())) {
                    currentPlayerSpeedY = (currentPlayerSpeedY > 0) ? -currentGravity : currentPlayerSpeedY;
                }
                player.setY(player.getY() + currentPlayerSpeedY);
                currentPlayerSpeedY -= currentGravity;
                currentGravity -= 0.01f * UNIT_Y;
                if (currentGravity <= 0.15f * UNIT_Y) {
                    currentGravity = 0.15f * UNIT_Y;
                }
                System.out.println("Gravity : " + (currentGravity / UNIT_Y));
            }

            level.update(player.getX(), player.getY(), player.getPlayerWidth(), player.getPlayerHeight());
            if (level.canPlayerFallDown() && !playerIsJumping) {
                player.setY(player.getY() + dropSpeedY);
                dropSpeedY -= dropGravity;
                dropGravity -= 0.01f * UNIT_Y;
            } else if (!level.canPlayerFallDown()) {
                dropSpeedY = 0;
                dropGravity = gravity;
                playerIsJumping = false;
                player.setY(level.getPlayerPositionY());
            }
        }
    }


    @Override
    public void render(float delta) {
        update();
        Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.act(delta);
        this.draw();
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        player.dispose();
        level.dispose();
    }
}
