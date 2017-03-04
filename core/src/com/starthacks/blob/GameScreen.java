package com.starthacks.blob;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;

public class GameScreen extends AbstractScreen {

    private Blob player;
    private int lastDownPointer;
    private boolean playerIsJumping, isPlayerDead;
    private float playerMoveDirection; // -1 (left), 0 (stay), 1 (right)

    public GameScreen(final MainActivity game) {
        super(game);
        playerIsJumping = false;
        isPlayerDead = false;

        player = new Blob(UNIT_X, UNIT_Y);
        player.setPosition(UNIT_X * 50f, UNIT_Y * 50f);

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
            playerIsJumping = true;
        }

        // move
        if (touches.get(pointer).touchX >= SCREEN_WIDTH * 0.85f) {
            playerMoveDirection = 1;
        } else if (touches.get(pointer).touchX >= SCREEN_WIDTH * 0.70f) {
            playerMoveDirection = -1;
        } else {
            playerMoveDirection = 0;
        }
    }

    private void checkTouch() {
        playerMoveDirection = 0;

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
            player.update(playerMoveDirection);
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
    }
}
