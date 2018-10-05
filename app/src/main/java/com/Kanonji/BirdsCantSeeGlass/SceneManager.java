package com.Kanonji.BirdsCantSeeGlass;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;

/**
 * Created by Ibrahim on 23/06/2017.
 */

public class SceneManager {
    private ArrayList<Scene> scenes = new ArrayList<>();
    public static int ACTIVE_SCENE;

    public SceneManager() {
        {
            ACTIVE_SCENE = 0;
            scenes.add(new GameplayScene());
        }
        {
            ACTIVE_SCENE = 1;
            scenes.add(new StoreScene());
        }
        {
            ACTIVE_SCENE = 2;
            scenes.add(new StoreScene2());
        }
        {
            ACTIVE_SCENE = 3;
            scenes.add(new BuyScene());
        }
        {
            ACTIVE_SCENE = 4;
            scenes.add(new MenuScene());
        }
        {
            ACTIVE_SCENE = 5;
            scenes.add(new StoreScene3());
        }
        {
            ACTIVE_SCENE = 6;
            scenes.add(new ScoreScene());
        }
        {
            ACTIVE_SCENE = 7;
            scenes.add(new BuyScene2());
        }
        {
            ACTIVE_SCENE = 8;
            scenes.add(new BuyScene3());
        }
        {
            ACTIVE_SCENE = 9;
            scenes.add(new StoryScene());
        }
        {
            ACTIVE_SCENE = 10;
            scenes.add(new MenuSelectScene());
        }
        {
            ACTIVE_SCENE = 11;
            scenes.add(new GameplayStoryScene());
        }
        {
            ACTIVE_SCENE = 12;
            scenes.add(new DailyTaskScene());
        }
        {
            ACTIVE_SCENE = 13;
            scenes.add(new StartUpScene());
        }
        {
            ACTIVE_SCENE = 14;
            scenes.add(new SplashScreen());
        }

    }

    public void recieveTouch(MotionEvent event) {
        scenes.get(ACTIVE_SCENE).recieveTouch(event);

    }

    public void update() {
        scenes.get(ACTIVE_SCENE).update();
    }

    public void draw(Canvas canvas) {
        scenes.get(ACTIVE_SCENE).draw(canvas);
    }
}
