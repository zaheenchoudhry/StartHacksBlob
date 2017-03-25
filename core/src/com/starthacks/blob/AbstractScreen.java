package com.starthacks.blob;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractScreen extends Stage implements Screen   {

    public AbstractScreen() {

    }

    class TouchInfo {
        public float touchX = 0;
        public float touchY = 0;
        public boolean touched = false;
    }

    public static final float SCREEN_WIDTH = Gdx.graphics.getWidth();
    public static final float SCREEN_HEIGHT = Gdx.graphics.getHeight();
    public static final float UNIT_X = SCREEN_WIDTH / 100;
    public static final float UNIT_Y = SCREEN_HEIGHT / 100;

    protected MainActivity game;
    protected OrthographicCamera camera;
    protected Map<Integer, TouchInfo> touches;

    public AbstractScreen(MainActivity game) {
        this.game = game;

        this.touches = new HashMap<Integer, TouchInfo>();
        for (int i = 0; i < 2; i++) {
            touches.put(i, new TouchInfo());
        }

        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setViewport(new FitViewport(SCREEN_WIDTH, SCREEN_HEIGHT, camera));
    }

    public abstract void update();
}
