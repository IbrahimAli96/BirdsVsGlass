package com.Kanonji.BirdsCantSeeGlass;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import java.lang.ref.WeakReference;

/**
 * Created by Ibrahim on 17/06/2017.
 */

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;

    private SceneManager manager;

    private static SharedPreferences prefs;

    private int i = ObstacleManeger.getScore();

    public static int Previous = 0;
    private String previous = "Previous";

    public static int HighScore = 0;
    public static int HighScore1 = 0;
    public static int HighScore2 = 0;

    public static int getHighScore()
    {
        return HighScore;
    }
    public static int getHighScore1()
    {
        return HighScore1;
    }
    public static int getHighScore2()
    {
        return HighScore2;
    }

    private String saveScore = "Highscore";
    private String saveScore1 = "Highscore1";
    private String saveScore2 = "Highscore2";

    public static int HighCoin = 0;

    public static int getHighCoin()
    {
        return HighCoin;
    }

    private String saveCoin = "HighCoin";

    public static int Feathers = 0;
    private String feathers = "Feathers";

    public static int HiddenStoryScore = 0;
    private String hiddenStoryScore = "HiddenStoryScore";

    public static int Ad1 = 0;
    private String ad1 = "Ad1";

    public static int Ad2 = 0;
    private String ad2 = "Ad2";

    public static int YelowBird = 2;
    private String yellowBird = "YellowBird";

    public static int RedBird = 0;
    private String redBird = "RedBird";

    public static int BlackBird = 0;
    private String blackBird = "BlackBird";

    public static int PinkBird = 0;
    private String pinkBird = "PinkBird";

    public static int RainbowBird = 0;
    private String rainbowBird = "RainbowBird";

    public static int NinjaBird = 0;
    private String ninjaBird = "NinjaBird";

    public static int StrongBird = 0;
    private String strongBird = "StrongBird";

    public static int DragonBird = 0;
    private String dragonBird = "DragonBird";

    public static int NitroBird = 0;
    private String nitroBird = "NitroBird";

    public static int LaserBird = 0;
    private String laserBird = "LaserBird";

    public static int SageBird = 0;
    private String sageBird = "SageBird";

    public static int BombBird = 0;
    private String bombBird = "bombBird";

    public static int Task1 = 0;
    private String task1 = "Task1";
    public static int Task2 = 0;
    private String task2 = "Task2";
    public static int Task3 = 0;
    private String task3 = "Task3";
    public static int Task4 = 0;
    private String task4 = "Task4";
    public static int Task5 = 0;
    private String task5 = "Task5";
    public static int Task6 = 0;
    private String task6 = "Task6";
    public static int Task7 = 0;
    private String task7 = "Task7";
    public static int Task8 = 0;
    private String task8 = "Task8";
    public static int Task9 = 0;
    private String task9 = "Task9";
    public static int Task10 = 0;
    private String task10 = "Task10";
    public static int Task11 = 0;
    private String task11 = "Task11";
    public static int Task12 = 0;
    private String task12 = "Task12";

    public static int Monday = 0;
    private String monday = "Monday";
    public static int Tuesday = 0;
    private String tuesday = "Tuesday";
    public static int Wednesday = 0;
    private String wednesday = "Wednesday";
    public static int Thursday = 0;
    private String thursday = "Thursday";
    public static int Friday = 0;
    private String friday = "Friday";
    public static int Saturday = 0;
    private String saturday = "Saturday";
    public static int Sunday = 0;
    private String sunday = "Sunday";

    public static int DailyTask1 = 0;
    private String dailyTask1 = "DailyTask1";
    public static int DailyTask2 = 0;
    private String dailyTask2 = "dailyTask2";
    public static int DailyTask3 = 0;
    private String dailyTask3 = "dailyTask3";
    public static int DailyTask4 = 0;
    private String dailyTask4 = "dailyTask4";

    public static int DailyTask3Counter = 0;
    private String dailyTask3Counter = "DailyTask3Counter";

    public static int DailyTask4Counter = 0;
    private String dailyTask4Counter = "DailyTask4Counter";

    public static int Help = 0;
    private String help = "Help";

    public static int BuyBtn = 0;
    private String buyBtn = "BuyBtn";

    public static int ScoreCounter = 0;
    private String scoreCounter = "ScoreCounter";

    public static int StoryLevel = 0;
    private String storyLevel = "StoryLevel";

    public static int DayOfTheWeek = 0;
    private String dayOfTheWeek = "DayOfTheWeek";

    public GamePanel (Context context) {
        super(context);

        prefs = context.getSharedPreferences("com.practice.practice", context.MODE_PRIVATE);

        String spackage = "com.practice.practice";

        Previous = prefs.getInt(previous, 0);

        HighScore = prefs.getInt(saveScore, 0);
        HighScore1 = prefs.getInt(saveScore1, 0);
        HighScore2 = prefs.getInt(saveScore2, 0);

        HighCoin = prefs.getInt(saveCoin, 0);

        Feathers = prefs.getInt(feathers, 0);
        HiddenStoryScore = prefs.getInt(hiddenStoryScore, 0);

        Ad1 = prefs.getInt(ad1, 0);
        Ad2 = prefs.getInt(ad2, 0);

        YelowBird = prefs.getInt(yellowBird, 2);
        RedBird = prefs.getInt(redBird, 0);
        BlackBird = prefs.getInt(blackBird, 0);
        PinkBird = prefs.getInt(pinkBird, 0);
        RainbowBird = prefs.getInt(rainbowBird, 0);
        DragonBird = prefs.getInt(dragonBird, 0);
        NinjaBird = prefs.getInt(ninjaBird, 0);
        StrongBird = prefs.getInt(strongBird, 0);
        NitroBird = prefs.getInt(nitroBird, 0);
        LaserBird = prefs.getInt(laserBird, 0);
        SageBird = prefs.getInt(sageBird, 0);
        BombBird = prefs.getInt(bombBird, 0);

        Task1 = prefs.getInt(task1, 0);
        Task2 = prefs.getInt(task2, 0);
        Task3 = prefs.getInt(task3, 0);
        Task4 = prefs.getInt(task4, 0);
        Task5 = prefs.getInt(task5, 0);
        Task6 = prefs.getInt(task6, 0);
        Task7 = prefs.getInt(task7, 0);
        Task8 = prefs.getInt(task8, 0);
        Task9 = prefs.getInt(task9, 0);
        Task10 = prefs.getInt(task10, 0);
        Task11 = prefs.getInt(task11, 0);
        Task12 = prefs.getInt(task12, 0);

        DailyTask1 = prefs.getInt(dailyTask1, 0);
        DailyTask2 = prefs.getInt(dailyTask2, 0);
        DailyTask3 = prefs.getInt(dailyTask3, 0);
        DailyTask4 = prefs.getInt(dailyTask4, 0);

        DailyTask3Counter = prefs.getInt(dailyTask3Counter, 0);
        DailyTask4Counter = prefs.getInt(dailyTask4Counter, 0);

        Monday = prefs.getInt(monday, 0);
        Tuesday = prefs.getInt(tuesday, 0);
        Wednesday = prefs.getInt(wednesday, 0);
        Thursday = prefs.getInt(thursday, 0);
        Friday = prefs.getInt(friday, 0);
        Saturday = prefs.getInt(saturday, 0);
        Sunday = prefs.getInt(sunday, 0);

        Help = prefs.getInt(help, 0);
        BuyBtn = prefs.getInt(buyBtn, 0);

        ScoreCounter = prefs.getInt(scoreCounter, 0);

        StoryLevel = prefs.getInt(storyLevel, 0);

        DayOfTheWeek = prefs.getInt(dayOfTheWeek, 0);

        getHolder().addCallback(this);

        Constants.CURRENT_CONTEXT = context;

        thread = new MainThread(getHolder(), this);

        manager = new SceneManager();

        setFocusable(true);
    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){

    }

    private static WeakReference<Activity> mActivityRef;
    public static void updateActivity(Activity activity) {
        mActivityRef = new WeakReference<Activity>(activity);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new MainThread(getHolder(), this);
        Constants.INIT_TIME = System.currentTimeMillis();
        thread.setRunning(true);
        thread.start();

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){

        //if (SceneManager.ACTIVE_SCENE == 0 && GameplayScene.gameOver == true){
            //thread.stop();
        //} else if (SceneManager.ACTIVE_SCENE == 0) {

        //} else {
            //thread.stop();
        //}

        boolean retry = true;
        while(retry) {
            try {
                thread.setRunning(false);
                thread.join();

            } catch(Exception e) {e.printStackTrace();}
            retry = false;

        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        manager.recieveTouch(event);

        return true;
        //return super.onTouchEvent(event);

    }

    public void update() {

        prefs.edit().putInt(previous, Previous).commit();

        prefs.edit().putInt(saveScore, HighScore).commit();
        prefs.edit().putInt(saveScore1, HighScore1).commit();
        prefs.edit().putInt(saveScore2, HighScore2).commit();

        prefs.edit().putInt(saveCoin, HighCoin).commit();
        prefs.edit().putInt(feathers, Feathers).commit();
        prefs.edit().putInt(hiddenStoryScore, HiddenStoryScore).commit();

        prefs.edit().putInt(ad1, Ad1).commit();
        prefs.edit().putInt(ad2, Ad2).commit();

        prefs.edit().putInt(yellowBird, YelowBird).commit();
        prefs.edit().putInt(redBird, RedBird).commit();
        prefs.edit().putInt(blackBird, BlackBird).commit();
        prefs.edit().putInt(pinkBird, PinkBird).commit();
        prefs.edit().putInt(rainbowBird, RainbowBird).commit();
        prefs.edit().putInt(dragonBird, DragonBird).commit();
        prefs.edit().putInt(ninjaBird, NinjaBird).commit();
        prefs.edit().putInt(strongBird, StrongBird).commit();
        prefs.edit().putInt(nitroBird, NitroBird).commit();
        prefs.edit().putInt(laserBird, LaserBird).commit();
        prefs.edit().putInt(sageBird, SageBird).commit();
        prefs.edit().putInt(bombBird, BombBird).commit();


        prefs.edit().putInt(task1, Task1).commit();
        prefs.edit().putInt(task2, Task2).commit();
        prefs.edit().putInt(task3, Task3).commit();
        prefs.edit().putInt(task4, Task4).commit();
        prefs.edit().putInt(task5, Task5).commit();
        prefs.edit().putInt(task6, Task6).commit();
        prefs.edit().putInt(task7, Task7).commit();
        prefs.edit().putInt(task8, Task8).commit();
        prefs.edit().putInt(task9, Task9).commit();
        prefs.edit().putInt(task10, Task10).commit();
        prefs.edit().putInt(task11, Task11).commit();
        prefs.edit().putInt(task12, Task12).commit();

        prefs.edit().putInt(dailyTask1, DailyTask1).commit();
        prefs.edit().putInt(dailyTask2, DailyTask2).commit();
        prefs.edit().putInt(dailyTask3, DailyTask3).commit();
        prefs.edit().putInt(dailyTask4, DailyTask4).commit();

        prefs.edit().putInt(dailyTask3Counter, DailyTask3Counter).commit();
        prefs.edit().putInt(dailyTask4Counter, DailyTask4Counter).commit();

        prefs.edit().putInt(monday, Monday).commit();
        prefs.edit().putInt(tuesday, Tuesday).commit();
        prefs.edit().putInt(wednesday, Wednesday).commit();
        prefs.edit().putInt(thursday, Thursday).commit();
        prefs.edit().putInt(friday, Friday).commit();
        prefs.edit().putInt(saturday, Saturday).commit();
        prefs.edit().putInt(sunday, Sunday).commit();


        prefs.edit().putInt(help, Help).commit();

        prefs.edit().putInt(buyBtn, BuyBtn).commit();

        prefs.edit().putInt(scoreCounter, ScoreCounter).commit();

        prefs.edit().putInt(storyLevel, StoryLevel).commit();

        prefs.edit().putInt(dayOfTheWeek, DayOfTheWeek).commit();


        manager.update();

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        manager.draw(canvas);


    }


}
