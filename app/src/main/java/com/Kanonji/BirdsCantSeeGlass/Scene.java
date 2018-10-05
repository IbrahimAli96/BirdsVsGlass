package com.Kanonji.BirdsCantSeeGlass;

import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;

/**
 * Created by Ibrahim on 23/06/2017.
 */

public interface Scene {
    public void update();

    public void draw(Canvas canvas);
    public void terminate();
    public boolean recieveTouch(MotionEvent event);
}
