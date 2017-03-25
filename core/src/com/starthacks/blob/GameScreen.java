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
    private BackgroundGroup backgroundGroup;
    private int currentLevel;
    private WinDisplay winDisplay;

    public GameScreen(final MainActivity game) {
        super(game);
        playerSpeedY = 3.5f * UNIT_Y;
        gravity = 0.4f * UNIT_Y;
        playerIsJumping = false;
        isPlayerDead = false;
        currentPlayerSpeedY = playerSpeedY;
        currentGravity = gravity;
        dropSpeedY = 0;
        dropGravity = gravity;
        currentLevel = 1;

        backgroundGroup = new BackgroundGroup(UNIT_X, UNIT_Y);

        level = new Level1(UNIT_X, UNIT_Y);
        //level = new Level_2(UNIT_X,UNIT_Y);

        player = new Blob(UNIT_X, UNIT_Y);
        player.setPosition(UNIT_X * 1f, UNIT_Y * 10f);

        winDisplay = new WinDisplay(UNIT_X, UNIT_Y);
        winDisplay.setVisible(false);

        this.addActor(backgroundGroup);
        this.addActor(level);
        this.addActor(winDisplay);
        this.addActor(player);
    }

    public GameScreen() {
        super();
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
        if (touches.get(pointer).touchX <= SCREEN_WIDTH * 0.30f && currentLevel != 4) {
            if (!playerIsJumping && !level.canPlayerFallDown()) {
                playerIsJumping = true;
                //currentPlayerSpeedY = playerSpeedY;
                //currentGravity = gravity;
            }
        } else if (touches.get(pointer).touchX >= SCREEN_WIDTH * 0.85f && currentLevel != 4) {
            playerMoveDirection = 1;
        } else if (touches.get(pointer).touchX >= SCREEN_WIDTH * 0.65f && currentLevel != 4) {
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
        if ((pointer == 0 || pointer == 1) && currentLevel != 4) {
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
        if ((pointer == 0 || pointer == 1) && currentLevel != 4) {
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
        if ((pointer == 0 || pointer == 1) && currentLevel != 4) {
            lastDownPointer = pointer;
            touches.get(pointer).touchX = screenX;
            touches.get(pointer).touchY = screenY;
            touches.get(pointer).touched = true;
            checkTouch();
            return true;
        }
        return false;
    }

    /*
    ((player.getX() + player.getCurrentBlobSize() >= level.getDestinationPositionX() + level.getX()) ||
                (player.getX() <= level.getDestinationPositionX() + level.getX() + UNIT_X * 10f)) &&
                ((player.getY() <= level.getDestinationPositionY() + level.getY() + UNIT_Y * 10f) ||
                        (player.getY() + player.getCurrentBlobSize() >= level.getDestinationPositionY() + level.getY()))
     */
    @Override
    public void update() {
        // update values like position size etc
        if (currentLevel == 4) {
            player.update(0);
        }
        if (level.getDestinationPositionX() + level.getX() <= player.getX() + player.getCurrentBlobSize() &&
                (level.getDestinationPositionX() + level.getX() + UNIT_X * 10f) > player.getX() &&
                (level.getDestinationPositionY() + level.getY()) <= player.getY() + player.getCurrentBlobSize() &&
                (level.getDestinationPositionY() + level.getY() + UNIT_Y * 10f) > player.getY() &&
                currentLevel != 4) {
            level.dispose();
            currentLevel++;
            if (currentLevel == 2) {
                level = new Level_2(UNIT_X, UNIT_Y);
            } else if (currentLevel == 3 ){
                level = new Level3(UNIT_X, UNIT_Y);
            }
            if (currentLevel == 4) {
                winDisplay.setVisible(true);
                player.setPlayerSize(UNIT_Y * 70f);
                player.setPosition(UNIT_X * 55f - player.getCurrentBlobSize() / 2, UNIT_Y * 55f - player.getCurrentBlobSize() / 2);
                player.update(1);
                player.update(1);
            } else {
                level.setX(0);
                level.setY(0);
                backgroundGroup.reset(currentLevel);
                backgroundGroup.setX(0);
                player.setPlayerSize(player.getOriginalBlobSize());
                player.setPosition(UNIT_X * 1f, UNIT_Y * 10f);
                this.addActor(level);
            }
        }
        if (isPlayerDead && currentLevel != 4) {
            isPlayerDead = false;
            level.setX(0);
            level.setY(0);
            backgroundGroup.reset(currentLevel);
            backgroundGroup.setX(0);
            player.setPlayerSize(player.getOriginalBlobSize());
            player.setPosition(UNIT_X * 1f, UNIT_Y * 10f);
        }
        if (!isPlayerDead && currentLevel != 4) {
            if ((playerMoveDirection == -1 && !level.playerCanMoveLeft(player.getX(), player.getY(), player.getPlayerWidth(), player.getPlayerHeight())) ||
                    (playerMoveDirection == 1 && !level.playerCanMoveRight(player.getX(), player.getY(), player.getPlayerWidth(), player.getPlayerHeight()))) {
                player.update(0);
            } else if ((int) playerMoveDirection == -1 && player.getX() > UNIT_X * 1f) {
                player.update(playerMoveDirection);
                backgroundGroup.update(playerMoveDirection);
                level.setX(level.getX() + player.getPlayerSpeed());
            } else if (playerMoveDirection != -1) {
                player.update(playerMoveDirection);
                backgroundGroup.update(playerMoveDirection);
                level.setX(level.getX() + player.getPlayerSpeed() * playerMoveDirection * -1);
            }

            /*
            else if ((int) playerMoveDirection == 1 && (player.getX() + player.getPlayerWidth()) < UNIT_X * 98f) {
                player.update(playerMoveDirection);
                level.setX(level.getX() - player.getPlayerSpeed());
            }
             */


            if (playerIsJumping) {
                if (level.isPlatformAbove(player.getX(), player.getY(), player.getPlayerWidth(), player.getPlayerHeight())) {
                    currentPlayerSpeedY = (currentPlayerSpeedY > 0) ? -currentGravity : currentPlayerSpeedY;
                }
                player.setY(player.getY() + currentPlayerSpeedY);
                level.setY(level.getY() - currentPlayerSpeedY);
                if (level.getY() > 0) {
                    level.setY(0);
                }
                backgroundGroup.updateY(currentLevel, level.getY() / 3f);
                currentPlayerSpeedY -= currentGravity;
                currentGravity -= 0.02f * UNIT_Y;
                if (currentGravity <= 0.15f * UNIT_Y) {
                    currentGravity = 0.15f * UNIT_Y;
                }
            }

            level.update(player.getX(), player.getY(), player.getPlayerWidth(), player.getPlayerHeight());
            if (level.canPlayerFallDown() && !playerIsJumping) {
                player.setY(player.getY() + dropSpeedY);
                level.setY(level.getY() - dropSpeedY);
                if (level.getY() > 0) {
                    level.setY(0);
                }
                backgroundGroup.updateY(currentLevel, level.getY() / 3f);
                dropSpeedY -= dropGravity;
                dropGravity -= 0.02f * UNIT_Y;
                if (dropGravity <= 0.15f * UNIT_Y) {
                    dropGravity = 0.15f * UNIT_Y;
                }
            } else if (!level.canPlayerFallDown()) {
                float dropDisplacement = 0;
                float dropDamage = 0;
                if (dropSpeedY != 0 || currentPlayerSpeedY != playerSpeedY) {
                    dropDisplacement += -dropSpeedY;
                    dropDisplacement += (currentPlayerSpeedY < 0) ? -currentPlayerSpeedY : 0;
                    dropDamage = dropDisplacement * 0.003f;
                    System.out.println("Displacement " + dropDisplacement);
                    System.out.println("Damage " + dropDamage);
                    System.out.println("Size Decrease " + (player.getOriginalBlobSize() * dropDamage));
                    System.out.println("Size Decrease % " + ((player.getOriginalBlobSize() * dropDamage) / UNIT_Y));
                    player.setPlayerSize(player.getCurrentBlobSize() - player.getOriginalBlobSize() * dropDamage);
                    if (player.getCurrentBlobSize() <= (player.getOriginalBlobSize() / 10f) * 1.5f) {
                        isPlayerDead = true;
                    }
                }
                currentPlayerSpeedY = playerSpeedY;
                currentGravity = gravity;
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
        super.dispose();
        player.dispose();
        level.dispose();
        backgroundGroup.dispose();
        winDisplay.dispose();
    }
}
