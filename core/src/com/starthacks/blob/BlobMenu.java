package com.starthacks.blob;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

/**
 * Created by okand on 24/03/2017.
 */

public class BlobMenu {
    private Game gameObject;
    public BlobMenu(Game gameObject)
    {
        this.gameObject = gameObject;
    }
    private void someMethod()
    {
        //Switches to a new MenuScreen...
        //useless in most cases but you get the idea
        gameObject.setScreen((Screen) new BlobMenu(gameObject));
    }
}
