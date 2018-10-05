package com.Kanonji.BirdsCantSeeGlass;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.MotionEvent;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by Ibrahim on 23/06/2017.
 */

public class GameplayStoryScene implements Scene {

    Timer t = new Timer();

    private static final long DOUBLE_PRESS_INTERVAL = 250; // in millis
    private long lastPressTime;
    boolean mHasDoubleClicked = false;

    private Rect r = new Rect();

    public static boolean tutorial = true;

    public static RectPlayer player;
    private static Point playerPoint;
    private static ObstacleManegerStory obstacleManegerStory;

    public static RectCircle circle;
    private static Point circlePoint;
    private static boolean movingCircle = false;

    private int color;
    private static boolean movingPlayer = false;

    public static boolean gameOver = false;

    private long gameOverTime;

    private  OrientationData orientationData;
    private long frameTime;

    public static int life = 0;
    public static int lifePic = 0;

    public static int levelText = 0;
    //private int i = ObstacleManeger.getScore();

    //private int i1 = GamePanel.getHighScore();


    public GameplayStoryScene() {
        if(Constants.SCREEN_HEIGHT > 1600) {
            player = new RectPlayer(new Rect(180, 180, Constants.SCREEN_WIDTH / 3, Constants.SCREEN_WIDTH / 3), Color.rgb(255, 0, 0));
        }
        if(Constants.SCREEN_HEIGHT > 1100 && Constants.SCREEN_HEIGHT < 1600) {
            player = new RectPlayer(new Rect(180, 180,  Constants.SCREEN_WIDTH / 2 - (Constants.SCREEN_WIDTH / 10), Constants.SCREEN_WIDTH / 2 - (Constants.SCREEN_WIDTH / 10)), Color.rgb(255, 0, 0));
        }
        if(Constants.SCREEN_HEIGHT < 1100 && Constants.SCREEN_HEIGHT > 900) {
            player = new RectPlayer(new Rect(180, 180, Constants.SCREEN_WIDTH / 2 - (Constants.SCREEN_WIDTH / 16), Constants.SCREEN_WIDTH / 2 - (Constants.SCREEN_WIDTH / 16)), Color.rgb(255, 0, 0));
        }
        if(Constants.SCREEN_HEIGHT < 900) {
            player = new RectPlayer(new Rect(180, 180, Constants.SCREEN_WIDTH / 2 + (Constants.SCREEN_WIDTH / 20), Constants.SCREEN_WIDTH / 2 + (Constants.SCREEN_WIDTH / 20)), Color.rgb(255, 0, 0));
        }

        circle = new RectCircle(new Rect(180, 180, Constants.SCREEN_WIDTH / 3, Constants.SCREEN_WIDTH / 3), Color.rgb(255, 0, 0));
        circlePoint = new Point(Constants.SCREEN_WIDTH/2, (3*Constants.SCREEN_HEIGHT/4) - Constants.SCREEN_HEIGHT/8);

        playerPoint = new Point(Constants.SCREEN_WIDTH/2, 3*Constants.SCREEN_HEIGHT/4);
        player.update(playerPoint);

        obstacleManegerStory = new ObstacleManegerStory(300, 400, 100, Color.rgb(153, 255, 255));

        orientationData = new OrientationData();
        //if (GamePanel.Help == 1) {
        //    orientationData.register();
        //}
        frameTime = System.currentTimeMillis();
        ObstacleManegerStory.myFeathers = 0;

    }


    public static void resetStory() {
        playerPoint = new Point(Constants.SCREEN_WIDTH / 2, 3 * Constants.SCREEN_HEIGHT / 4);
        player.update(playerPoint);
        obstacleManegerStory = new ObstacleManegerStory(Constants.SCREEN_WIDTH / 3, Constants.SCREEN_WIDTH / 2 - (Constants.SCREEN_WIDTH / 10), Constants.SCREEN_WIDTH / 12, Color.argb(150, 255, 255, 255));
        movingPlayer = false;
        movingCircle = false;
        ObstacleManegerStory.myFeathers = 0;
        MainActivity.mAd.isLoaded();
        GamePanel.Ad2 = 1;
        tutorial = true;
        life = 0;
        lifePic = 0;
        GamePanel.HiddenStoryScore = 0;
        GamePanel.StoryLevel = 0;

        circlePoint = new Point(Constants.SCREEN_WIDTH/2, (3*Constants.SCREEN_HEIGHT/4) + Constants.SCREEN_HEIGHT/8);

        if(Constants.SCREEN_HEIGHT > 1600) {
            player = new RectPlayer(new Rect(180, 180, Constants.SCREEN_WIDTH / 3, Constants.SCREEN_WIDTH / 3), Color.rgb(255, 0, 0));
            circle = new RectCircle(new Rect(180, 180, Constants.SCREEN_WIDTH / 3, Constants.SCREEN_WIDTH / 3), Color.rgb(255, 0, 0));
        } else if(Constants.SCREEN_HEIGHT > 1100 && Constants.SCREEN_HEIGHT < 1600) {
            player = new RectPlayer(new Rect(180, 180,  Constants.SCREEN_WIDTH / 2 - (Constants.SCREEN_WIDTH / 10), Constants.SCREEN_WIDTH / 2 - (Constants.SCREEN_WIDTH / 10)), Color.rgb(255, 0, 0));
            circle = new RectCircle(new Rect(180, 180, Constants.SCREEN_WIDTH / 2 - (Constants.SCREEN_WIDTH / 10), Constants.SCREEN_WIDTH / 2 - (Constants.SCREEN_WIDTH / 10)), Color.rgb(255, 0, 0));
        }else if(Constants.SCREEN_HEIGHT < 1100 && Constants.SCREEN_HEIGHT > 900) {
            player = new RectPlayer(new Rect(180, 180, Constants.SCREEN_WIDTH / 2 - (Constants.SCREEN_WIDTH / 16), Constants.SCREEN_WIDTH / 2 - (Constants.SCREEN_WIDTH / 16)), Color.rgb(255, 0, 0));
            circle = new RectCircle(new Rect(180, 180, Constants.SCREEN_WIDTH / 2 - (Constants.SCREEN_WIDTH / 16), Constants.SCREEN_WIDTH / 2 - (Constants.SCREEN_WIDTH / 16)), Color.rgb(255, 0, 0));
        } else if(Constants.SCREEN_HEIGHT < 900) {
            player = new RectPlayer(new Rect(180, 180, Constants.SCREEN_WIDTH / 2 + (Constants.SCREEN_WIDTH / 20), Constants.SCREEN_WIDTH / 2 + (Constants.SCREEN_WIDTH / 20)), Color.rgb(255, 0, 0));
            circle = new RectCircle(new Rect(180, 180, Constants.SCREEN_WIDTH / 2 + (Constants.SCREEN_WIDTH / 20), Constants.SCREEN_WIDTH / 2 + (Constants.SCREEN_WIDTH / 20)), Color.rgb(255, 0, 0));
        }

        if (GamePanel.YelowBird > 2) {
            GamePanel.YelowBird = 2;
        }
        if (GamePanel.RedBird > 2) {
            GamePanel.RedBird = 2;
        }
        if (GamePanel.BlackBird > 2) {
            GamePanel.BlackBird = 2;
        }
        if (GamePanel.PinkBird > 2) {
            GamePanel.PinkBird = 2;
        }
        if (GamePanel.RainbowBird > 2) {
            GamePanel.RainbowBird = 2;
        }
        if (GamePanel.StrongBird > 2) {
            GamePanel.StrongBird = 2;
        }
        if (GamePanel.NinjaBird > 2) {
            GamePanel.NinjaBird = 2;
        }
        if (GamePanel.DragonBird > 2) {
            GamePanel.DragonBird = 2;
        }
        if (GamePanel.NitroBird > 2) {
            GamePanel.NitroBird = 2;
        }
        if (GamePanel.LaserBird > 2) {
            GamePanel.LaserBird = 2;
        }
        if (GamePanel.SageBird > 2) {
            GamePanel.SageBird = 2;
        }
        if (GamePanel.BombBird > 2) {
            GamePanel.BombBird = 2;
        }

        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        final Runnable task = new Runnable() {
            public void run() {

                levelText = 1;

            }

        };
        final ScheduledFuture<?> handle =
                scheduler.scheduleAtFixedRate(task, 0, 2, TimeUnit.SECONDS);

        scheduler.schedule(new Runnable() {
            public void run() {

                levelText = 0;

                handle.cancel(true);
            }
        }, 2, TimeUnit.SECONDS);

    }

    public static void resetStory5() {

        obstacleManegerStory = new ObstacleManegerStory(Constants.SCREEN_WIDTH / 3, Constants.SCREEN_WIDTH / 2 - (Constants.SCREEN_WIDTH / 10), Constants.SCREEN_WIDTH / 12, Color.argb(150, 255, 255, 255));

    }
    public static void resetStory1() {

        obstacleManegerStory = new ObstacleManegerStory(Constants.SCREEN_WIDTH / 3, Constants.SCREEN_WIDTH/2 - (Constants.SCREEN_WIDTH / 10), Constants.SCREEN_WIDTH / 12, Color.argb(150, 255, 255, 255));

    }
    public static void resetStory2() {

        obstacleManegerStory = new ObstacleManegerStory(Constants.SCREEN_WIDTH / 4, Constants.SCREEN_WIDTH/2 - (Constants.SCREEN_WIDTH / 10), Constants.SCREEN_WIDTH / 12, Color.argb(150, 255, 255, 255));


    }
    public static void resetStory3() {

        obstacleManegerStory = new ObstacleManegerStory(Constants.SCREEN_WIDTH / 4, Constants.SCREEN_HEIGHT, Constants.SCREEN_WIDTH / 12, Color.argb(150, 255, 255, 255));


    }
    public static void resetStory4() {

        obstacleManegerStory = new ObstacleManegerStory(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_WIDTH/2 - (Constants.SCREEN_WIDTH / 6), Constants.SCREEN_WIDTH / 12, Color.argb(150, 255, 255, 255));

    }

    @Override
    public void terminate() {
        SceneManager.ACTIVE_SCENE = 11;

    }

    private static WeakReference<Activity> mActivityRef;
    public static void updateActivity(Activity activity) {
        mActivityRef = new WeakReference<Activity>(activity);
    }

    private void loadAd() {
        if (!MainActivity.mAd.isLoaded()) {
            MainActivity.mAd.loadAd("", new AdRequest.Builder().build());
            //ca-app-pub-5053096756111759/8761007024
        }
    }


    public boolean recieveTouch(MotionEvent event) {
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                int action = event.getAction();
                int x = (int) event.getX();  // or getRawX();
                int y = (int) event.getY();
                int h2 = resizedbitmap6.getHeight();
                int w2 = resizedbitmap6.getWidth();
                float xRatio2 = (Constants.SCREEN_WIDTH / 2)-( w2 / 2);
                float yRatio2 = (Constants.SCREEN_HEIGHT / 2)-( h2 / 2) + (Constants.SCREEN_WIDTH/4 + (Constants.SCREEN_WIDTH/6)- Constants.SCREEN_HEIGHT/10);

                int h55 = resizedbitmap55.getHeight();
                int w55 = resizedbitmap55.getWidth();
                int h66 = resizedbitmap66.getHeight();
                int w66 = resizedbitmap66.getWidth();
                float xRatio55 = ((Constants.SCREEN_WIDTH / 4)-( w55 / 4));
                float yRatio55 = (((Constants.SCREEN_HEIGHT / 2) + (h55 / 2)) + (Constants.SCREEN_HEIGHT/6));
                float xRatio66 = ((Constants.SCREEN_WIDTH / 2)+( w66 / 4) + (Constants.SCREEN_HEIGHT/24));
                float yRatio66 = (((Constants.SCREEN_HEIGHT / 2) + (h66 / 2)) + (Constants.SCREEN_HEIGHT/6));

                long pressTime = System.currentTimeMillis();

                // If double click...
                if (pressTime - lastPressTime <= DOUBLE_PRESS_INTERVAL) {

                    if (!gameOver) {






                    mHasDoubleClicked = true;
                }}
                else {     // If not double click....
                    mHasDoubleClicked = false;
                // record the last time the menu button was pressed.
                lastPressTime = pressTime;
                    if (!gameOver && player.getRectangle().contains((int) event.getX(), (int) event.getY()-Constants.SCREEN_HEIGHT/8)) {
                        movingPlayer = true;
                        tutorial = false;
                    }
                    if (!gameOver && circle.getCircle().contains((int) event.getX(), (int) event.getY()))
                        movingCircle = true;
                    if (gameOver && System.currentTimeMillis() - gameOverTime >= 0) {
                        if (x >= xRatio2 && x < (xRatio2 + resizedbitmap6.getWidth())
                                && y >= yRatio2 && y < (yRatio2 + resizedbitmap6.getHeight())) {
                            if (GamePanel.Ad2 == 1) {
                                MainActivity.click.start();
                                MainActivity.mAd2 = MobileAds.getRewardedVideoAdInstance(mActivityRef.get());
                                loadAd();
                                MainActivity.mAd2.show();
                                //Appodeal.show(mActivityRef.get(), Appodeal.REWARDED_VIDEO);
                            } else if (GamePanel.Ad2 == 2) {
                                gameOver = false;
                                GamePanel.Ad2 = 0;
                                life = 2;
                                lifePic = 2;

                                if (GamePanel.StoryLevel == 1 || GamePanel.StoryLevel == 3 || GamePanel.StoryLevel == 5 ||
                                        GamePanel.StoryLevel == 11 || GamePanel.StoryLevel == 13 || GamePanel.StoryLevel == 15) {
                                    resetStory1();

                                } else if (GamePanel.StoryLevel == 6 || GamePanel.StoryLevel == 7 || GamePanel.StoryLevel == 9 ||
                                        GamePanel.StoryLevel == 16 || GamePanel.StoryLevel == 17 || GamePanel.StoryLevel == 19) {
                                    resetStory2();

                                } else if (GamePanel.StoryLevel == 2 || GamePanel.StoryLevel == 8 || GamePanel.StoryLevel == 12 ||
                                        GamePanel.StoryLevel == 18) {
                                    resetStory3();

                                } else if (GamePanel.StoryLevel == 4 || GamePanel.StoryLevel == 14) {
                                    resetStory4();

                                } else if (GamePanel.StoryLevel == 10) {
                                    resetStory5();

                                }

                                if (GamePanel.DailyTask4 == 0) {
                                    GamePanel.DailyTask4Counter = GamePanel.DailyTask4Counter + 1;
                                }
                                if (GamePanel.DailyTask4 == 0) {
                                    if (GamePanel.DailyTask4Counter >= 5) {
                                        GamePanel.DailyTask4 = 1;
                                    }
                                }
                            }
                        }
                        if (x >= xRatio55 && x < (xRatio55 + resizedbitmap55.getWidth())
                                && y >= yRatio55 && y < (yRatio55 + resizedbitmap55.getHeight())) {
                            MainActivity.click.start();
                            resetStory();
                            gameOver = false;
                            orientationData.newGame();
                        }
                        if (x >= xRatio66 && x < (xRatio66 + resizedbitmap66.getWidth())
                                && y >= yRatio66 && y < (yRatio66 + resizedbitmap66.getHeight())) {
                            MainActivity.click.start();
                            SceneManager.ACTIVE_SCENE = 4;
                            MainActivity.mInterstitialAd.loadAd((new AdRequest.Builder().build()));
                            MainActivity.mInterstitialAd.show();
                            //Appodeal.show(mActivityRef.get(), Appodeal.INTERSTITIAL);

                            gameOver = false;

                        }

                    } return true;
                }

                break;
            case MotionEvent.ACTION_MOVE:

                if (!gameOver && movingPlayer )
                    playerPoint.set((int) event.getX(), (int) event.getY()-Constants.SCREEN_HEIGHT/8);
                if (!gameOver && movingCircle)
                    circlePoint.set((int) event.getX(), (int) event.getY());

                break;
            case MotionEvent.ACTION_UP:
                movingPlayer = false;
                movingCircle = false;
                break;

        }

        return false;
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    //Bitmap bitmap2 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.startbtn1, 200, 200);
    //Bitmap resizedbitmap2 = Bitmap.createScaledBitmap(bitmap2, Constants.SCREEN_WIDTH/2, Constants.SCREEN_HEIGHT/11, true);

    Bitmap bitmap55 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.retry1, 100, 100);
    Bitmap resizedbitmap55 = Bitmap.createScaledBitmap(bitmap55, Constants.SCREEN_WIDTH/6, Constants.SCREEN_WIDTH/6, true);

    Bitmap bitmap66 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.menu1, 100, 100);
    Bitmap resizedbitmap66 = Bitmap.createScaledBitmap(bitmap66, Constants.SCREEN_WIDTH/6, Constants.SCREEN_WIDTH/6, true);

    Bitmap backgroundImage = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.cloudback, 300, 300);
    Bitmap resizedbackgroundImage = Bitmap.createScaledBitmap(backgroundImage, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, true);

    Bitmap bitmap200 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.tutorial, 150, 150);

    Bitmap bitmap301 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.sp2, 50, 50);
    Bitmap d = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.cloud5, 300, 300);
    Bitmap bitmap111 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.sp2, 50, 50);
    Bitmap bitmapTitle = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.gameover, 100, 100);
    Bitmap bitmapSP = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.survival, 50, 50);

    Bitmap bitmap6 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.lifead1, 150, 150);
    Bitmap resizedbitmap6 = Bitmap.createScaledBitmap(bitmap6, Constants.SCREEN_WIDTH/2, Constants.SCREEN_HEIGHT/11, true);
    Bitmap bitmap6a = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.lifead2, 150, 150);
    Bitmap resizedbitmap6a = Bitmap.createScaledBitmap(bitmap6a, Constants.SCREEN_WIDTH/2, Constants.SCREEN_HEIGHT/11, true);
    Bitmap bitmap6b = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.lifead3, 150, 150);
    Bitmap resizedbitmap6b = Bitmap.createScaledBitmap(bitmap6b, Constants.SCREEN_WIDTH/2, Constants.SCREEN_HEIGHT/11, true);


    @Override
    public void draw(Canvas canvas) {
        //canvas.drawBitmap(BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.beach1), -250, 200, null);
        //Bitmap background= BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.beach1);
        //background= resizeImage(background, Constants.SCREEN_WIDTH ,Constants.SCREEN_HEIGHT);
        //canvas.drawBitmap(background, 0, 0, null);

        Paint paint = new Paint();

        Typeface myTypeface = Typeface.createFromAsset(Constants.CURRENT_CONTEXT.getAssets(), "chunk.ttf");

        canvas.drawColor(Color.rgb(0, 255, 255));

        drawEndlessBackground(canvas, 0, 0);

        player.draw(canvas);
        circle.draw(canvas);
        obstacleManegerStory.draw(canvas);

        //BitmapFactory.Options options = new BitmapFactory.Options();
        //options.inJustDecodeBounds = false;
        //options.inSampleSize = 1;

        if (tutorial == true) {
            Bitmap resizedbitmap200 = Bitmap.createScaledBitmap(bitmap200, Constants.SCREEN_WIDTH/2 - Constants.SCREEN_WIDTH/20, Constants.SCREEN_WIDTH/8, true);
            int h200 = resizedbitmap200.getHeight();
            int w200 = resizedbitmap200.getWidth();
            canvas.drawBitmap(resizedbitmap200, Constants.SCREEN_WIDTH - w200, (3*Constants.SCREEN_HEIGHT/4) + Constants.SCREEN_HEIGHT/11, new Paint());
        }
        //if (GamePanel.SageBird == 4) {
            //Bitmap bitmap100 = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.snow2);
            //canvas.drawBitmap(bitmap100, null, new RectF(0, 0, (Constants.SCREEN_WIDTH), Constants.SCREEN_HEIGHT) , null);
        //}

        if (lifePic == 0) {
            Bitmap bitmap300 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.life3, 100, 100);
            Bitmap resizedbitmap300 = Bitmap.createScaledBitmap(bitmap300, Constants.SCREEN_WIDTH/3, Constants.SCREEN_WIDTH/10, true);
            int h300 = resizedbitmap300.getHeight();
            int w300 = resizedbitmap300.getWidth();
            canvas.drawBitmap(resizedbitmap300, 0, 0, new Paint());
        } else if (lifePic == 1) {
            Bitmap bitmap300 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.life2, 100, 100);
            Bitmap resizedbitmap300 = Bitmap.createScaledBitmap(bitmap300, Constants.SCREEN_WIDTH/3, Constants.SCREEN_WIDTH/10, true);
            int h300 = resizedbitmap300.getHeight();
            int w300 = resizedbitmap300.getWidth();
            canvas.drawBitmap(resizedbitmap300, 0, 0, new Paint());
        } else if (lifePic == 2) {
            Bitmap bitmap300 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.life1, 100, 100);
            Bitmap resizedbitmap300 = Bitmap.createScaledBitmap(bitmap300, Constants.SCREEN_WIDTH/3, Constants.SCREEN_WIDTH/10, true);
            int h300 = resizedbitmap300.getHeight();
            int w300 = resizedbitmap300.getWidth();
            canvas.drawBitmap(resizedbitmap300, 0, 0, new Paint());
        } else if (lifePic == 3) {
            Bitmap bitmap300 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.life0, 100, 100);
            Bitmap resizedbitmap300 = Bitmap.createScaledBitmap(bitmap300, Constants.SCREEN_WIDTH/3, Constants.SCREEN_WIDTH/10, true);
            int h300 = resizedbitmap300.getHeight();
            int w300 = resizedbitmap300.getWidth();
            canvas.drawBitmap(resizedbitmap300, 0, 0, new Paint());
        }



        if (GamePanel.StoryLevel == 0) {
            Bitmap bitmapLevel = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.level1, 100, 100);
            Bitmap resizedbitmapLevel = Bitmap.createScaledBitmap(bitmapLevel, Constants.SCREEN_WIDTH/4 + Constants.SCREEN_WIDTH/20, Constants.SCREEN_WIDTH / 12, true);
            canvas.drawBitmap(resizedbitmapLevel, Constants.SCREEN_WIDTH / 3 + Constants.SCREEN_WIDTH/26, 0, new Paint());
        } else if (GamePanel.StoryLevel == 1) {
            Bitmap bitmapLevel = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.level2, 100, 100);
            Bitmap resizedbitmapLevel = Bitmap.createScaledBitmap(bitmapLevel, Constants.SCREEN_WIDTH/4 + Constants.SCREEN_WIDTH/20, Constants.SCREEN_WIDTH / 12, true);
            canvas.drawBitmap(resizedbitmapLevel, Constants.SCREEN_WIDTH / 3 + Constants.SCREEN_WIDTH/26, 0, new Paint());
        } else if (GamePanel.StoryLevel == 2) {
            Bitmap bitmapLevel = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.level3, 100, 100);
            Bitmap resizedbitmapLevel = Bitmap.createScaledBitmap(bitmapLevel, Constants.SCREEN_WIDTH/4 + Constants.SCREEN_WIDTH/20, Constants.SCREEN_WIDTH / 12, true);
            canvas.drawBitmap(resizedbitmapLevel, Constants.SCREEN_WIDTH / 3 + Constants.SCREEN_WIDTH/26, 0, new Paint());
        } else if (GamePanel.StoryLevel == 3) {
            Bitmap bitmapLevel = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.level4, 100, 100);
            Bitmap resizedbitmapLevel = Bitmap.createScaledBitmap(bitmapLevel, Constants.SCREEN_WIDTH/4 + Constants.SCREEN_WIDTH/20, Constants.SCREEN_WIDTH / 12, true);
            canvas.drawBitmap(resizedbitmapLevel, Constants.SCREEN_WIDTH / 3 + Constants.SCREEN_WIDTH/26, 0, new Paint());
        } else if (GamePanel.StoryLevel == 4) {
            Bitmap bitmapLevel = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.level5, 100, 100);
            Bitmap resizedbitmapLevel = Bitmap.createScaledBitmap(bitmapLevel, Constants.SCREEN_WIDTH/4 + Constants.SCREEN_WIDTH/20, Constants.SCREEN_WIDTH / 12, true);
            canvas.drawBitmap(resizedbitmapLevel, Constants.SCREEN_WIDTH / 3 + Constants.SCREEN_WIDTH/26, 0, new Paint());
        } else if (GamePanel.StoryLevel == 5) {
            Bitmap bitmapLevel = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.level6, 100, 100);
            Bitmap resizedbitmapLevel = Bitmap.createScaledBitmap(bitmapLevel, Constants.SCREEN_WIDTH/4 + Constants.SCREEN_WIDTH/20, Constants.SCREEN_WIDTH / 12, true);
            canvas.drawBitmap(resizedbitmapLevel, Constants.SCREEN_WIDTH / 3 + Constants.SCREEN_WIDTH/26, 0, new Paint());
        } else if (GamePanel.StoryLevel == 6) {
            Bitmap bitmapLevel = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.level7, 100, 100);
            Bitmap resizedbitmapLevel = Bitmap.createScaledBitmap(bitmapLevel, Constants.SCREEN_WIDTH/4 + Constants.SCREEN_WIDTH/20, Constants.SCREEN_WIDTH / 12, true);
            canvas.drawBitmap(resizedbitmapLevel, Constants.SCREEN_WIDTH / 3 + Constants.SCREEN_WIDTH/26, 0, new Paint());
        } else if (GamePanel.StoryLevel == 7) {
            Bitmap bitmapLevel = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.level8, 100, 100);
            Bitmap resizedbitmapLevel = Bitmap.createScaledBitmap(bitmapLevel, Constants.SCREEN_WIDTH/4 + Constants.SCREEN_WIDTH/20, Constants.SCREEN_WIDTH / 12, true);
            canvas.drawBitmap(resizedbitmapLevel, Constants.SCREEN_WIDTH / 3 + Constants.SCREEN_WIDTH/26, 0, new Paint());
        } else if (GamePanel.StoryLevel == 8) {
            Bitmap bitmapLevel = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.level9, 100, 100);
            Bitmap resizedbitmapLevel = Bitmap.createScaledBitmap(bitmapLevel, Constants.SCREEN_WIDTH/4 + Constants.SCREEN_WIDTH/20, Constants.SCREEN_WIDTH / 12, true);
            canvas.drawBitmap(resizedbitmapLevel, Constants.SCREEN_WIDTH / 3 + Constants.SCREEN_WIDTH/26, 0, new Paint());
        } else if (GamePanel.StoryLevel == 9) {
            Bitmap bitmapLevel = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.level10, 100, 100);
            Bitmap resizedbitmapLevel = Bitmap.createScaledBitmap(bitmapLevel, Constants.SCREEN_WIDTH/4 + Constants.SCREEN_WIDTH/20, Constants.SCREEN_WIDTH / 12, true);
            canvas.drawBitmap(resizedbitmapLevel, Constants.SCREEN_WIDTH / 3 + Constants.SCREEN_WIDTH/26, 0, new Paint());
        }

        if (GamePanel.StoryLevel == 10) {
            Bitmap bitmapLevel = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.level11, 100, 100);
            Bitmap resizedbitmapLevel = Bitmap.createScaledBitmap(bitmapLevel, Constants.SCREEN_WIDTH/4 + Constants.SCREEN_WIDTH/20, Constants.SCREEN_WIDTH / 12, true);
            canvas.drawBitmap(resizedbitmapLevel, Constants.SCREEN_WIDTH / 3 + Constants.SCREEN_WIDTH/26, 0, new Paint());
        } else if (GamePanel.StoryLevel == 11) {
            Bitmap bitmapLevel = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.level12, 100, 100);
            Bitmap resizedbitmapLevel = Bitmap.createScaledBitmap(bitmapLevel, Constants.SCREEN_WIDTH/4 + Constants.SCREEN_WIDTH/20, Constants.SCREEN_WIDTH / 12, true);
            canvas.drawBitmap(resizedbitmapLevel, Constants.SCREEN_WIDTH / 3 + Constants.SCREEN_WIDTH/26, 0, new Paint());
        } else if (GamePanel.StoryLevel == 12) {
            Bitmap bitmapLevel = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.level13, 100, 100);
            Bitmap resizedbitmapLevel = Bitmap.createScaledBitmap(bitmapLevel, Constants.SCREEN_WIDTH/4 + Constants.SCREEN_WIDTH/20, Constants.SCREEN_WIDTH / 12, true);
            canvas.drawBitmap(resizedbitmapLevel, Constants.SCREEN_WIDTH / 3 + Constants.SCREEN_WIDTH/26, 0, new Paint());
        } else if (GamePanel.StoryLevel == 13) {
            Bitmap bitmapLevel = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.level14, 100, 100);
            Bitmap resizedbitmapLevel = Bitmap.createScaledBitmap(bitmapLevel, Constants.SCREEN_WIDTH/4 + Constants.SCREEN_WIDTH/20, Constants.SCREEN_WIDTH / 12, true);
            canvas.drawBitmap(resizedbitmapLevel, Constants.SCREEN_WIDTH / 3 + Constants.SCREEN_WIDTH/26, 0, new Paint());
        } else if (GamePanel.StoryLevel == 14) {
            Bitmap bitmapLevel = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.level15, 100, 100);
            Bitmap resizedbitmapLevel = Bitmap.createScaledBitmap(bitmapLevel, Constants.SCREEN_WIDTH/4 + Constants.SCREEN_WIDTH/20, Constants.SCREEN_WIDTH / 12, true);
            canvas.drawBitmap(resizedbitmapLevel, Constants.SCREEN_WIDTH / 3 + Constants.SCREEN_WIDTH/26, 0, new Paint());
        } else if (GamePanel.StoryLevel == 15) {
            Bitmap bitmapLevel = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.level16, 100, 100);
            Bitmap resizedbitmapLevel = Bitmap.createScaledBitmap(bitmapLevel, Constants.SCREEN_WIDTH/4 + Constants.SCREEN_WIDTH/20, Constants.SCREEN_WIDTH / 12, true);
            canvas.drawBitmap(resizedbitmapLevel, Constants.SCREEN_WIDTH / 3 + Constants.SCREEN_WIDTH/26, 0, new Paint());
        } else if (GamePanel.StoryLevel == 16) {
            Bitmap bitmapLevel = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.level17, 100, 100);
            Bitmap resizedbitmapLevel = Bitmap.createScaledBitmap(bitmapLevel, Constants.SCREEN_WIDTH/4 + Constants.SCREEN_WIDTH/20, Constants.SCREEN_WIDTH / 12, true);
            canvas.drawBitmap(resizedbitmapLevel, Constants.SCREEN_WIDTH / 3 + Constants.SCREEN_WIDTH/26, 0, new Paint());
        } else if (GamePanel.StoryLevel == 17) {
            Bitmap bitmapLevel = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.level18, 100, 100);
            Bitmap resizedbitmapLevel = Bitmap.createScaledBitmap(bitmapLevel, Constants.SCREEN_WIDTH/4 + Constants.SCREEN_WIDTH/20, Constants.SCREEN_WIDTH / 12, true);
            canvas.drawBitmap(resizedbitmapLevel, Constants.SCREEN_WIDTH / 3 + Constants.SCREEN_WIDTH/26, 0, new Paint());
        } else if (GamePanel.StoryLevel == 18) {
            Bitmap bitmapLevel = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.level19, 100, 100);
            Bitmap resizedbitmapLevel = Bitmap.createScaledBitmap(bitmapLevel, Constants.SCREEN_WIDTH/4 + Constants.SCREEN_WIDTH/20, Constants.SCREEN_WIDTH / 12, true);
            canvas.drawBitmap(resizedbitmapLevel, Constants.SCREEN_WIDTH / 3 + Constants.SCREEN_WIDTH/26, 0, new Paint());
        } else if (GamePanel.StoryLevel == 19) {
            Bitmap bitmapLevel = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.level20, 100, 100);
            Bitmap resizedbitmapLevel = Bitmap.createScaledBitmap(bitmapLevel, Constants.SCREEN_WIDTH/4 + Constants.SCREEN_WIDTH/20, Constants.SCREEN_WIDTH / 12, true);
            canvas.drawBitmap(resizedbitmapLevel, Constants.SCREEN_WIDTH / 3 + Constants.SCREEN_WIDTH/26, 0, new Paint());
        }

        //paint.setTextSize(Constants.SCREEN_WIDTH / 10);
        //paint.setTypeface(myTypeface);
        //paint.setColor(Color.BLACK);
        //canvas.drawText("" + (GamePanel.StoryLevel + 1), Constants.SCREEN_WIDTH / 2 + Constants.SCREEN_WIDTH/10, 0 + Constants.SCREEN_WIDTH/20, paint);


        if (levelText == 1) {
            Bitmap bitmapLevel = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.level1, 50, 50);
            Bitmap resizedbitmapLevel = Bitmap.createScaledBitmap(bitmapLevel, Constants.SCREEN_WIDTH/2, Constants.SCREEN_WIDTH / 8, true);
            int hlevel = resizedbitmapLevel.getHeight();
            int wlevel = resizedbitmapLevel.getWidth();
            canvas.drawBitmap(resizedbitmapLevel, Constants.SCREEN_WIDTH / 2 - wlevel/2, 0 + Constants.SCREEN_HEIGHT/3, new Paint());
        } else if (levelText == 2) {
            Bitmap bitmapLevel = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.level2, 50, 50);
            Bitmap resizedbitmapLevel = Bitmap.createScaledBitmap(bitmapLevel, Constants.SCREEN_WIDTH/2, Constants.SCREEN_WIDTH / 8, true);
            int hlevel = resizedbitmapLevel.getHeight();
            int wlevel = resizedbitmapLevel.getWidth();
            canvas.drawBitmap(resizedbitmapLevel, Constants.SCREEN_WIDTH / 2 - wlevel/2, 0 + Constants.SCREEN_HEIGHT/3, new Paint());
        } else if (levelText == 3) {
            Bitmap bitmapLevel = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.level3, 50, 50);
            Bitmap resizedbitmapLevel = Bitmap.createScaledBitmap(bitmapLevel, Constants.SCREEN_WIDTH/2 , Constants.SCREEN_WIDTH / 8, true);
            int hlevel = resizedbitmapLevel.getHeight();
            int wlevel = resizedbitmapLevel.getWidth();
            canvas.drawBitmap(resizedbitmapLevel, Constants.SCREEN_WIDTH / 2 - wlevel/2, 0 + Constants.SCREEN_HEIGHT/3, new Paint());
        } else if (levelText == 4) {
            Bitmap bitmapLevel = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.level4, 50, 50);
            Bitmap resizedbitmapLevel = Bitmap.createScaledBitmap(bitmapLevel, Constants.SCREEN_WIDTH/2 , Constants.SCREEN_WIDTH / 8, true);
            int hlevel = resizedbitmapLevel.getHeight();
            int wlevel = resizedbitmapLevel.getWidth();
            canvas.drawBitmap(resizedbitmapLevel, Constants.SCREEN_WIDTH / 2 - wlevel/2, 0 + Constants.SCREEN_HEIGHT/3, new Paint());
        } else if (levelText == 5) {
            Bitmap bitmapLevel = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.level5, 50, 50);
            Bitmap resizedbitmapLevel = Bitmap.createScaledBitmap(bitmapLevel, Constants.SCREEN_WIDTH/2 , Constants.SCREEN_WIDTH / 8, true);
            int hlevel = resizedbitmapLevel.getHeight();
            int wlevel = resizedbitmapLevel.getWidth();
            canvas.drawBitmap(resizedbitmapLevel, Constants.SCREEN_WIDTH / 2 - wlevel/2, 0 + Constants.SCREEN_HEIGHT/3, new Paint());
        } else if (levelText == 6) {
            Bitmap bitmapLevel = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.level6, 50, 50);
            Bitmap resizedbitmapLevel = Bitmap.createScaledBitmap(bitmapLevel, Constants.SCREEN_WIDTH/2 , Constants.SCREEN_WIDTH / 8, true);
            int hlevel = resizedbitmapLevel.getHeight();
            int wlevel = resizedbitmapLevel.getWidth();
            canvas.drawBitmap(resizedbitmapLevel, Constants.SCREEN_WIDTH / 2 - wlevel/2, 0 + Constants.SCREEN_HEIGHT/3, new Paint());
            paint.setTextSize(Constants.SCREEN_WIDTH / 20);
            paint.setTypeface(myTypeface);
            paint.setColor(Color.BLACK);
            drawCentreText7(canvas, paint, "Life + 1");
            paint.setTextSize(Constants.SCREEN_WIDTH / 20);
            paint.setTypeface(myTypeface);
            paint.setColor(Color.BLACK);
            drawCentreText8(canvas, paint, "SP x 2");
        } else if (levelText == 7) {
            Bitmap bitmapLevel = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.level7, 50, 50);
            Bitmap resizedbitmapLevel = Bitmap.createScaledBitmap(bitmapLevel, Constants.SCREEN_WIDTH/2 , Constants.SCREEN_WIDTH / 8, true);
            int hlevel = resizedbitmapLevel.getHeight();
            int wlevel = resizedbitmapLevel.getWidth();
            canvas.drawBitmap(resizedbitmapLevel, Constants.SCREEN_WIDTH / 2 - wlevel/2, 0 + Constants.SCREEN_HEIGHT/3, new Paint());
        } else if (levelText == 8) {
            Bitmap bitmapLevel = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.level8, 50, 50);
            Bitmap resizedbitmapLevel = Bitmap.createScaledBitmap(bitmapLevel, Constants.SCREEN_WIDTH/2 , Constants.SCREEN_WIDTH / 8, true);
            int hlevel = resizedbitmapLevel.getHeight();
            int wlevel = resizedbitmapLevel.getWidth();
            canvas.drawBitmap(resizedbitmapLevel, Constants.SCREEN_WIDTH / 2 - wlevel/2, 0 + Constants.SCREEN_HEIGHT/3, new Paint());
        } else if (levelText == 9) {
            Bitmap bitmapLevel = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.level9, 50, 50);
            Bitmap resizedbitmapLevel = Bitmap.createScaledBitmap(bitmapLevel, Constants.SCREEN_WIDTH/2 , Constants.SCREEN_WIDTH / 8, true);
            int hlevel = resizedbitmapLevel.getHeight();
            int wlevel = resizedbitmapLevel.getWidth();
            canvas.drawBitmap(resizedbitmapLevel, Constants.SCREEN_WIDTH / 2 - wlevel/2, 0 + Constants.SCREEN_HEIGHT/3, new Paint());
        } else if (levelText == 10) {
            Bitmap bitmapLevel = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.level10, 50, 50);
            Bitmap resizedbitmapLevel = Bitmap.createScaledBitmap(bitmapLevel, Constants.SCREEN_WIDTH/2 , Constants.SCREEN_WIDTH / 8, true);
            int hlevel = resizedbitmapLevel.getHeight();
            int wlevel = resizedbitmapLevel.getWidth();
            canvas.drawBitmap(resizedbitmapLevel, Constants.SCREEN_WIDTH / 2 - wlevel/2, 0 + Constants.SCREEN_HEIGHT/3, new Paint());
        }

        if (levelText == 11) {
            Bitmap bitmapLevel = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.level11, 50, 50);
            Bitmap resizedbitmapLevel = Bitmap.createScaledBitmap(bitmapLevel, Constants.SCREEN_WIDTH/2 , Constants.SCREEN_WIDTH / 8, true);
            int hlevel = resizedbitmapLevel.getHeight();
            int wlevel = resizedbitmapLevel.getWidth();
            canvas.drawBitmap(resizedbitmapLevel, Constants.SCREEN_WIDTH / 2 - wlevel/2, 0 + Constants.SCREEN_HEIGHT/3, new Paint());
            paint.setTextSize(Constants.SCREEN_WIDTH / 20);
            paint.setTypeface(myTypeface);
            paint.setColor(Color.BLACK);
            drawCentreText7(canvas, paint, "Life + 1");
            paint.setTextSize(Constants.SCREEN_WIDTH / 20);
            paint.setTypeface(myTypeface);
            paint.setColor(Color.BLACK);
            drawCentreText8(canvas, paint, "SP x 3");
        } else if (levelText == 12) {
            Bitmap bitmapLevel = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.level12, 50, 50);
            Bitmap resizedbitmapLevel = Bitmap.createScaledBitmap(bitmapLevel, Constants.SCREEN_WIDTH/2 , Constants.SCREEN_WIDTH / 8, true);
            int hlevel = resizedbitmapLevel.getHeight();
            int wlevel = resizedbitmapLevel.getWidth();
            canvas.drawBitmap(resizedbitmapLevel, Constants.SCREEN_WIDTH / 2 - wlevel/2, 0 + Constants.SCREEN_HEIGHT/3, new Paint());
        } else if (levelText == 13) {
            Bitmap bitmapLevel = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.level13, 50, 50);
            Bitmap resizedbitmapLevel = Bitmap.createScaledBitmap(bitmapLevel, Constants.SCREEN_WIDTH/2 , Constants.SCREEN_WIDTH / 8, true);
            int hlevel = resizedbitmapLevel.getHeight();
            int wlevel = resizedbitmapLevel.getWidth();
            canvas.drawBitmap(resizedbitmapLevel, Constants.SCREEN_WIDTH / 2 - wlevel/2, 0 + Constants.SCREEN_HEIGHT/3, new Paint());
        } else if (levelText == 14) {
            Bitmap bitmapLevel = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.level14, 50, 50);
            Bitmap resizedbitmapLevel = Bitmap.createScaledBitmap(bitmapLevel, Constants.SCREEN_WIDTH/2 , Constants.SCREEN_WIDTH / 8, true);
            int hlevel = resizedbitmapLevel.getHeight();
            int wlevel = resizedbitmapLevel.getWidth();
            canvas.drawBitmap(resizedbitmapLevel, Constants.SCREEN_WIDTH / 2 - wlevel/2, 0 + Constants.SCREEN_HEIGHT/3, new Paint());
        } else if (levelText == 15) {
            Bitmap bitmapLevel = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.level15, 50, 50);
            Bitmap resizedbitmapLevel = Bitmap.createScaledBitmap(bitmapLevel, Constants.SCREEN_WIDTH/2, Constants.SCREEN_WIDTH / 8, true);
            int hlevel = resizedbitmapLevel.getHeight();
            int wlevel = resizedbitmapLevel.getWidth();
            canvas.drawBitmap(resizedbitmapLevel, Constants.SCREEN_WIDTH / 2 - wlevel/2, 0 + Constants.SCREEN_HEIGHT/3, new Paint());
        } else if (levelText == 16) {
            Bitmap bitmapLevel = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.level16, 50, 50);
            Bitmap resizedbitmapLevel = Bitmap.createScaledBitmap(bitmapLevel, Constants.SCREEN_WIDTH/2 , Constants.SCREEN_WIDTH / 8, true);
            int hlevel = resizedbitmapLevel.getHeight();
            int wlevel = resizedbitmapLevel.getWidth();
            canvas.drawBitmap(resizedbitmapLevel, Constants.SCREEN_WIDTH / 2 - wlevel/2, 0 + Constants.SCREEN_HEIGHT/3, new Paint());
            paint.setTextSize(Constants.SCREEN_WIDTH / 20);
            paint.setTypeface(myTypeface);
            paint.setColor(Color.BLACK);
            drawCentreText7(canvas, paint, "Life + 1");
            paint.setTextSize(Constants.SCREEN_WIDTH / 20);
            paint.setTypeface(myTypeface);
            paint.setColor(Color.BLACK);
            drawCentreText8(canvas, paint, "SP x 4");
        } else if (levelText == 17) {
            Bitmap bitmapLevel = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.level17, 50, 50);
            Bitmap resizedbitmapLevel = Bitmap.createScaledBitmap(bitmapLevel, Constants.SCREEN_WIDTH/2 , Constants.SCREEN_WIDTH / 8, true);
            int hlevel = resizedbitmapLevel.getHeight();
            int wlevel = resizedbitmapLevel.getWidth();
            canvas.drawBitmap(resizedbitmapLevel, Constants.SCREEN_WIDTH / 2 - wlevel/2, 0 + Constants.SCREEN_HEIGHT/3, new Paint());
        } else if (levelText == 18) {
            Bitmap bitmapLevel = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.level18, 50, 50);
            Bitmap resizedbitmapLevel = Bitmap.createScaledBitmap(bitmapLevel, Constants.SCREEN_WIDTH/2 , Constants.SCREEN_WIDTH / 8, true);
            int hlevel = resizedbitmapLevel.getHeight();
            int wlevel = resizedbitmapLevel.getWidth();
            canvas.drawBitmap(resizedbitmapLevel, Constants.SCREEN_WIDTH / 2 - wlevel/2, 0 + Constants.SCREEN_HEIGHT/3, new Paint());
        } else if (levelText == 19) {
            Bitmap bitmapLevel = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.level19, 50, 50);
            Bitmap resizedbitmapLevel = Bitmap.createScaledBitmap(bitmapLevel, Constants.SCREEN_WIDTH/2 , Constants.SCREEN_WIDTH / 8, true);
            int hlevel = resizedbitmapLevel.getHeight();
            int wlevel = resizedbitmapLevel.getWidth();
            canvas.drawBitmap(resizedbitmapLevel, Constants.SCREEN_WIDTH / 2 - wlevel/2, 0 + Constants.SCREEN_HEIGHT/3, new Paint());
        } else if (levelText == 20) {
            Bitmap bitmapLevel = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.level20, 50, 50);
            Bitmap resizedbitmapLevel = Bitmap.createScaledBitmap(bitmapLevel, Constants.SCREEN_WIDTH/2 , Constants.SCREEN_WIDTH / 8, true);
            int hlevel = resizedbitmapLevel.getHeight();
            int wlevel = resizedbitmapLevel.getWidth();
            canvas.drawBitmap(resizedbitmapLevel, Constants.SCREEN_WIDTH / 2 - wlevel/2, 0 + Constants.SCREEN_HEIGHT/3, new Paint());
            paint.setTextSize(Constants.SCREEN_WIDTH / 20);
            paint.setTypeface(myTypeface);
            paint.setColor(Color.BLACK);
            drawCentreText7(canvas, paint, "Life + 1");
        }

        if (!gameOver) {
            Bitmap resizedbitmap301 = Bitmap.createScaledBitmap(bitmap301, Constants.SCREEN_WIDTH / 10, Constants.SCREEN_WIDTH / 10, true);
            int h301 = resizedbitmap301.getHeight();
            int w301 = resizedbitmap301.getWidth();
            canvas.drawBitmap(resizedbitmap301, (Constants.SCREEN_WIDTH - (Constants.SCREEN_WIDTH / 4 + Constants.SCREEN_WIDTH/24)), 0, new Paint());

            paint.setTextSize(Constants.SCREEN_WIDTH / 14);
            paint.setTypeface(myTypeface);
            paint.setColor(Color.BLACK);
            canvas.drawText("" + ObstacleManegerStory.getFeathers(), Constants.SCREEN_WIDTH - Constants.SCREEN_WIDTH / 6, 0 + Constants.SCREEN_HEIGHT / 30, paint);

            if (GamePanel.StoryLevel >= 5 && GamePanel.StoryLevel <= 9) {
                paint.setTextSize(Constants.SCREEN_WIDTH / 20);
                paint.setTypeface(myTypeface);
                paint.setColor(Color.YELLOW);
                canvas.drawText("x 2", Constants.SCREEN_WIDTH - Constants.SCREEN_WIDTH / 6, 0 + Constants.SCREEN_HEIGHT / 16, paint);

            } else if (GamePanel.StoryLevel >= 10 && GamePanel.StoryLevel <= 14) {
                paint.setTextSize(Constants.SCREEN_WIDTH / 20);
                paint.setTypeface(myTypeface);
                paint.setColor(Color.WHITE);
                canvas.drawText("x 3", Constants.SCREEN_WIDTH - Constants.SCREEN_WIDTH / 6, 0 + Constants.SCREEN_HEIGHT / 16, paint);

            } else if (GamePanel.StoryLevel >= 15) {
                paint.setTextSize(Constants.SCREEN_WIDTH / 20);
                paint.setTypeface(myTypeface);
                paint.setColor(Color.BLUE);
                canvas.drawText("x 4", Constants.SCREEN_WIDTH - Constants.SCREEN_WIDTH / 6, 0 + Constants.SCREEN_HEIGHT / 16, paint);

            } else if (GamePanel.StoryLevel < 5) {
                paint.setTextSize(Constants.SCREEN_WIDTH / 20);
                paint.setTypeface(myTypeface);
                paint.setColor(Color.RED);
                canvas.drawText("x 1", Constants.SCREEN_WIDTH - Constants.SCREEN_WIDTH / 6, 0 + Constants.SCREEN_HEIGHT / 16, paint);
            }
        }

        if(gameOver) {

            //retry
            int h55 = resizedbitmap55.getHeight();
            int w55 = resizedbitmap55.getWidth();
            canvas.drawBitmap(resizedbitmap55, ((Constants.SCREEN_WIDTH / 4)-( w55 / 4)), (((Constants.SCREEN_HEIGHT / 2) + (h55 / 2)) + (Constants.SCREEN_HEIGHT/6)), new Paint());
            //menu
            int h66 = resizedbitmap66.getHeight();
            int w66 = resizedbitmap66.getWidth();
            canvas.drawBitmap(resizedbitmap66, ((Constants.SCREEN_WIDTH / 2)+( w66 / 4) + (Constants.SCREEN_HEIGHT/24)), (((Constants.SCREEN_HEIGHT / 2) + (h66 / 2)) + (Constants.SCREEN_HEIGHT/6)), new Paint());

            if (GamePanel.Ad2 == 1) {
                int h6 = resizedbitmap6.getHeight();
                int w6 = resizedbitmap6.getWidth();
                canvas.drawBitmap(resizedbitmap6, ((Constants.SCREEN_WIDTH / 2) - (w6 / 2)), (((Constants.SCREEN_HEIGHT / 2) - (h6 / 2)) + (Constants.SCREEN_WIDTH / 4 + (Constants.SCREEN_WIDTH / 7)) - Constants.SCREEN_HEIGHT / 10), new Paint());
            } else if (GamePanel.Ad2 == 2){
                int h6a = resizedbitmap6a.getHeight();
                int w6a = resizedbitmap6a.getWidth();
                canvas.drawBitmap(resizedbitmap6a, ((Constants.SCREEN_WIDTH / 2) - (w6a / 2)), (((Constants.SCREEN_HEIGHT / 2) - (h6a / 2)) + (Constants.SCREEN_WIDTH / 4 + (Constants.SCREEN_WIDTH / 7)) - Constants.SCREEN_HEIGHT / 10), new Paint());
            } else if (GamePanel.Ad2 == 0){
                int h6b = resizedbitmap6b.getHeight();
                int w6b = resizedbitmap6b.getWidth();
                canvas.drawBitmap(resizedbitmap6b, ((Constants.SCREEN_WIDTH / 2) - (w6b / 2)), (((Constants.SCREEN_HEIGHT / 2) - (h6b / 2)) + (Constants.SCREEN_WIDTH / 4 + (Constants.SCREEN_WIDTH / 7)) - Constants.SCREEN_HEIGHT / 10), new Paint());
            }

            Bitmap resizedbitmapd = Bitmap.createScaledBitmap(d, Constants.SCREEN_WIDTH - Constants.SCREEN_WIDTH/4, Constants.SCREEN_WIDTH/2, true);
            int h = resizedbitmapd.getHeight();
            int w = resizedbitmapd.getWidth();
            canvas.drawBitmap(resizedbitmapd, ((Constants.SCREEN_WIDTH / 2) - (w / 2)), 0 + Constants.SCREEN_HEIGHT/3 - (h / 3), null);

            Bitmap resizedbitmap111 = Bitmap.createScaledBitmap(bitmap111, Constants.SCREEN_WIDTH/10, Constants.SCREEN_WIDTH/10, true);
            int h111 = resizedbitmap111.getHeight();
            int w111 = resizedbitmap111.getWidth();
            canvas.drawBitmap(resizedbitmap111, ((Constants.SCREEN_WIDTH / 4)-( w111 / 2) + (Constants.SCREEN_WIDTH/2)), (((Constants.SCREEN_HEIGHT / 2) - (h111 / 2) - Constants.SCREEN_WIDTH/7) - (Constants.SCREEN_HEIGHT/3)), new Paint());

            Bitmap resizedbitmapTitle = Bitmap.createScaledBitmap(bitmapTitle, Constants.SCREEN_WIDTH/2 + Constants.SCREEN_WIDTH/4, Constants.SCREEN_WIDTH/8, true);
            int hTitle = resizedbitmapTitle.getHeight();
            int wTitle = resizedbitmapTitle.getWidth();
            canvas.drawBitmap(resizedbitmapTitle, Constants.SCREEN_WIDTH/2 - wTitle/2, 0 + Constants.SCREEN_HEIGHT/3, new Paint());


            //paint.setTextSize(Constants.SCREEN_WIDTH / 16);
            //paint.setTypeface(myTypeface);
            //paint.setColor(Color.BLACK);
            //drawCentreText2(canvas, paint, "Score");

            //paint.setTextSize(Constants.SCREEN_WIDTH / 10);
            //paint.setColor(Color.BLACK);
            //drawCentreText4(canvas, paint, "" + ObstacleManegerStory.getFeathers());

            //paint.setTextSize(Constants.SCREEN_WIDTH / 22);
            //paint.setColor(Color.BLACK);
            //drawCentreText3(canvas, paint, "Highscore");

            //paint.setTextSize(Constants.SCREEN_WIDTH / 20);
            //paint.setColor(Color.BLACK);
            //drawCentreText5(canvas, paint, "" + GamePanel.getHighScore());

            //paint.setTextSize(Constants.SCREEN_WIDTH / 20);
            //paint.setColor(Color.BLACK);
            //drawCentreText6(canvas, paint, "MENU");

            //paint.setTextSize(Constants.SCREEN_WIDTH / 20);
            //paint.setColor(Color.BLACK);
            //drawCentreText7(canvas, paint, "RETRY");

            paint.setTextSize(Constants.SCREEN_WIDTH / 20);
            paint.setTypeface(myTypeface);
            paint.setColor(Color.BLACK);
            drawCentreText18(canvas, paint, "" + GamePanel.getHighCoin());

            Bitmap resizedbitmapSP = Bitmap.createScaledBitmap(bitmapSP, Constants.SCREEN_WIDTH/4 + w111/2, Constants.SCREEN_WIDTH/24, true);
            int hSP = resizedbitmapSP.getHeight();
            int wSP = resizedbitmapSP.getWidth();
            canvas.drawBitmap(resizedbitmapSP, ((Constants.SCREEN_WIDTH / 4) + (Constants.SCREEN_WIDTH/2)) - w111/2, (((Constants.SCREEN_HEIGHT / 2) - (h111) - Constants.SCREEN_WIDTH/7) - (Constants.SCREEN_HEIGHT/3)), new Paint());


            if (ObstacleManegerStory.getFeathers() >= 500) {
                paint.setTextSize(Constants.SCREEN_WIDTH / 30);
                paint.setTypeface(myTypeface);
                paint.setColor(Color.BLUE);
                drawCentreText19(canvas, paint, "++ " + ObstacleManegerStory.getFeathers());
            } else ; {
            if (ObstacleManegerStory.getFeathers() >=400 && ObstacleManegerStory.getFeathers() < 500) {
                paint.setTextSize(Constants.SCREEN_WIDTH / 30);
                paint.setTypeface(myTypeface);
                paint.setColor(Color.rgb(255, 215, 0));
                drawCentreText19(canvas, paint, "++ " + ObstacleManegerStory.getFeathers());
            } else ;
                {

                }
            if (ObstacleManegerStory.getFeathers() > 200 && ObstacleManegerStory.getFeathers() < 400) {
                paint.setTextSize(Constants.SCREEN_WIDTH / 30);
                paint.setTypeface(myTypeface);
                paint.setColor(Color.rgb(255, 140, 0));
                drawCentreText19(canvas, paint, "++ " + ObstacleManegerStory.getFeathers());
            } else ;
                    {
                        if (ObstacleManegerStory.getFeathers() <= 200) {
                            paint.setTextSize(Constants.SCREEN_WIDTH / 30);
                            paint.setTypeface(myTypeface);
                            paint.setColor(Color.RED);
                            drawCentreText19(canvas, paint, "++ " + ObstacleManegerStory.getFeathers());
                        } else ;
                        {

                        }
                    }}
        }
    }

    //public Bitmap resizeImage(Bitmap image, int maxWidth, int maxHeight)
    //{
        //Bitmap resizedImage = null;
        //try {
            //int imageHeight = image.getHeight();


            //if (imageHeight > maxHeight)
                //imageHeight = maxHeight;
            //int imageWidth = (imageHeight * image.getWidth())
                    /// image.getHeight();

            //if (imageWidth > maxWidth) {
                //imageWidth = maxWidth;
                //imageHeight = (imageWidth * image.getHeight())
                        /// image.getWidth();
            //}

            //if (imageHeight > maxHeight)
                //imageHeight = maxHeight;
            //if (imageWidth > maxWidth)
                //imageWidth = maxWidth;


            //resizedImage = Bitmap.createScaledBitmap(image, imageWidth,
                    //imageHeight, true);
        //} catch (OutOfMemoryError e) {

            //e.printStackTrace();
        //}catch(NullPointerException e)
        //{
        //    e.printStackTrace();
        //}
        //catch (Exception e) {
            //e.printStackTrace();
        //}
        //return resizedImage;
    //}

    private void drawEndlessBackground(Canvas canvas, float left, float top) {

        float modLeft = left % Constants.SCREEN_WIDTH;

        canvas.drawBitmap(resizedbackgroundImage, modLeft, modLeft, null);

        if (left < 0) {

            canvas.drawBitmap(resizedbackgroundImage, left % Constants.SCREEN_WIDTH, top, null);

        } else {

            canvas.drawBitmap(resizedbackgroundImage, left % Constants.SCREEN_WIDTH, top, null);

        }

    }

    @Override
    public void update() {
        if (!gameOver) {
            if (frameTime < Constants.INIT_TIME)
                frameTime = Constants.INIT_TIME;
            int elapsedTime = (int) (System.currentTimeMillis() - frameTime);
            frameTime = System.currentTimeMillis();
            if (orientationData.getOrientation() != null && orientationData.getStartOrientation() != null) {
                float pitch = orientationData.getOrientation()[1] - orientationData.getStartOrientation()[1];
                float roll = orientationData.getOrientation()[2] - orientationData.getStartOrientation()[2];

                float xSpeed = 2 * roll * Constants.SCREEN_WIDTH / 1000f;
                float ySpeed = pitch * Constants.SCREEN_HEIGHT / 1000f;

                playerPoint.x += Math.abs(xSpeed * elapsedTime) > 5 ? xSpeed * elapsedTime : 0;
                playerPoint.y -= Math.abs(ySpeed * elapsedTime) > 5 ? ySpeed * elapsedTime : 0;

                circlePoint.x += Math.abs(xSpeed * elapsedTime) > 5 ? xSpeed * elapsedTime : 0;
                circlePoint.y -= Math.abs(ySpeed * elapsedTime) > 5 ? ySpeed * elapsedTime : 0;
            }

            if (playerPoint.x < 0)
                playerPoint.x = 0;
            else if (playerPoint.x > Constants.SCREEN_WIDTH)
                playerPoint.x = Constants.SCREEN_WIDTH;
            if (playerPoint.y < 0)
                playerPoint.y = 0;
            else if (playerPoint.y > Constants.SCREEN_HEIGHT)
                playerPoint.y = Constants.SCREEN_HEIGHT;

            if (circlePoint.x < 0)
                circlePoint.x = 0;
            else if (circlePoint.x > Constants.SCREEN_WIDTH)
                circlePoint.x = Constants.SCREEN_WIDTH;
            if (circlePoint.y < 0)
                circlePoint.y = 0;
            else if (circlePoint.y > Constants.SCREEN_HEIGHT)
                circlePoint.y = Constants.SCREEN_HEIGHT;

            player.update(playerPoint);
            circle.update(circlePoint);
            obstacleManegerStory.update();


            if (obstacleManegerStory.playerCollide(player) && life == 0) {
                final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

                final Runnable task = new Runnable() {
                    public void run() {

                        MainActivity.dizzy.start();
                        lifePic = 1;

                    }

                };
                final ScheduledFuture<?> handle =
                        scheduler.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS);

                scheduler.schedule(new Runnable() {
                    public void run() {

                        life = 1;

                        handle.cancel(true);
                    }
                }, 1, TimeUnit.SECONDS);

            }

            if (obstacleManegerStory.playerCollide(player) && life == 1) {
                final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

                final Runnable task = new Runnable() {
                    public void run() {

                        MainActivity.dizzy.start();
                        lifePic = 2;

                    }

                };
                final ScheduledFuture<?> handle =
                        scheduler.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS);

                scheduler.schedule(new Runnable() {
                    public void run() {

                        life = 2;

                        handle.cancel(true);
                    }
                }, 1, TimeUnit.SECONDS);
            }

            if (obstacleManegerStory.playerCollide(player) && life == 2) {

                    MainActivity.thud.start();
                    MainActivity.dizzy.start();
                    gameOver = true;
                    gameOverTime = System.currentTimeMillis();
                    GamePanel.HighCoin = (ObstacleManegerStory.getFeathers() + GamePanel.HighCoin);
                    life = 3;
                    lifePic = 3;

            }


            //LEVEL 2
            if (GamePanel.HiddenStoryScore == 20 && GamePanel.StoryLevel == 0) {

                MainActivity.ping.start();
                resetStory1();
                GamePanel.StoryLevel = 1;


                final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

                final Runnable task = new Runnable() {
                    public void run() {

                        levelText = 2;

                    }

                };
                final ScheduledFuture<?> handle =
                        scheduler.scheduleAtFixedRate(task, 0, 2, TimeUnit.SECONDS);

                scheduler.schedule(new Runnable() {
                    public void run() {

                        levelText = 0;

                        handle.cancel(true);
                    }
                }, 2, TimeUnit.SECONDS);

            }
            //LEVEL 3
            else if (GamePanel.HiddenStoryScore == 40 && GamePanel.StoryLevel == 1) {

                MainActivity.ping.start();
                resetStory3();
                GamePanel.StoryLevel = 2;

                final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

                final Runnable task = new Runnable() {
                    public void run() {

                        levelText = 3;

                    }

                };
                final ScheduledFuture<?> handle =
                        scheduler.scheduleAtFixedRate(task, 0, 2, TimeUnit.SECONDS);

                scheduler.schedule(new Runnable() {
                    public void run() {

                        levelText = 0;

                        handle.cancel(true);
                    }
                }, 2, TimeUnit.SECONDS);


            }
            //LEVEL 4
            else if (GamePanel.HiddenStoryScore == 50 && GamePanel.StoryLevel == 2) {

                MainActivity.ping.start();
                resetStory1();
                GamePanel.StoryLevel = 3;

                final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

                final Runnable task = new Runnable() {
                    public void run() {

                        levelText = 4;

                    }

                };
                final ScheduledFuture<?> handle =
                        scheduler.scheduleAtFixedRate(task, 0, 2, TimeUnit.SECONDS);

                scheduler.schedule(new Runnable() {
                    public void run() {

                        levelText = 0;

                        handle.cancel(true);
                    }
                }, 2, TimeUnit.SECONDS);

            }
            //LEVEL 5
            else if (GamePanel.HiddenStoryScore == 70 && GamePanel.StoryLevel == 3) {

                MainActivity.ping.start();
                resetStory4();
                GamePanel.StoryLevel = 4;

                final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

                final Runnable task = new Runnable() {
                    public void run() {

                        levelText = 5;

                    }

                };
                final ScheduledFuture<?> handle =
                        scheduler.scheduleAtFixedRate(task, 0, 2, TimeUnit.SECONDS);

                scheduler.schedule(new Runnable() {
                    public void run() {

                        levelText = 0;

                        handle.cancel(true);
                    }
                }, 2, TimeUnit.SECONDS);

            }
            //LEVEL 6
            else if (GamePanel.HiddenStoryScore == 100 && GamePanel.StoryLevel == 4) {

                MainActivity.ping.start();
                resetStory1();
                GamePanel.StoryLevel = 5;

                if (life == 0) {
                } else if (life == 1){
                    life = 0;
                } else if (life == 2){
                    life = 1;
                }
                if (lifePic == 0) {

                } else if (lifePic == 1){
                    lifePic = 0;
                } else if (lifePic == 2){
                    lifePic = 1;
                }

                final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

                final Runnable task = new Runnable() {
                    public void run() {

                        levelText = 6;

                    }

                };
                final ScheduledFuture<?> handle =
                        scheduler.scheduleAtFixedRate(task, 0, 2, TimeUnit.SECONDS);

                scheduler.schedule(new Runnable() {
                    public void run() {

                        levelText = 0;

                        handle.cancel(true);
                    }
                }, 2, TimeUnit.SECONDS);

            }
            //LEVEL 7
            else if (GamePanel.HiddenStoryScore == 120 && GamePanel.StoryLevel == 5) {

                MainActivity.ping.start();
                resetStory2();
                GamePanel.StoryLevel = 6;

                final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

                final Runnable task = new Runnable() {
                    public void run() {

                        levelText = 7;

                    }

                };
                final ScheduledFuture<?> handle =
                        scheduler.scheduleAtFixedRate(task, 0, 2, TimeUnit.SECONDS);

                scheduler.schedule(new Runnable() {
                    public void run() {

                        levelText = 0;

                        handle.cancel(true);
                    }
                }, 2, TimeUnit.SECONDS);

            }
            //LEVEL 8
            else if (GamePanel.HiddenStoryScore == 140 && GamePanel.StoryLevel == 6) {

                MainActivity.ping.start();
                resetStory2();
                GamePanel.StoryLevel = 7;

                final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

                final Runnable task = new Runnable() {
                    public void run() {

                        levelText = 8;

                    }

                };
                final ScheduledFuture<?> handle =
                        scheduler.scheduleAtFixedRate(task, 0, 2, TimeUnit.SECONDS);

                scheduler.schedule(new Runnable() {
                    public void run() {

                        levelText = 0;

                        handle.cancel(true);
                    }
                }, 2, TimeUnit.SECONDS);

            }
            //LEVEL 9
            else if (GamePanel.HiddenStoryScore == 160 && GamePanel.StoryLevel == 7) {

                MainActivity.ping.start();
                resetStory3();
                GamePanel.StoryLevel = 8;

                final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

                final Runnable task = new Runnable() {
                    public void run() {

                        levelText = 9;

                    }

                };
                final ScheduledFuture<?> handle =
                        scheduler.scheduleAtFixedRate(task, 0, 2, TimeUnit.SECONDS);

                scheduler.schedule(new Runnable() {
                    public void run() {

                        levelText = 0;

                        handle.cancel(true);
                    }
                }, 2, TimeUnit.SECONDS);

            }
            //LEVEL 10
            else if (GamePanel.HiddenStoryScore == 170 && GamePanel.StoryLevel == 8) {

                MainActivity.ping.start();
                resetStory2();
                GamePanel.StoryLevel = 9;

                if (GamePanel.DailyTask2 == 0) {
                    GamePanel.DailyTask2 = 1;
                }

                final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

                final Runnable task = new Runnable() {
                    public void run() {

                        levelText = 10;

                    }

                };
                final ScheduledFuture<?> handle =
                        scheduler.scheduleAtFixedRate(task, 0, 2, TimeUnit.SECONDS);

                scheduler.schedule(new Runnable() {
                    public void run() {

                        levelText = 0;

                        handle.cancel(true);
                    }
                }, 2, TimeUnit.SECONDS);

            }

            //LEVEL 11
            if (GamePanel.HiddenStoryScore == 190 && GamePanel.StoryLevel == 9) {

                MainActivity.ping.start();
                resetStory5();
                GamePanel.StoryLevel = 10;

                if (life == 0) {
                } else if (life == 1){
                    life = 0;
                } else if (life == 2){
                    life = 1;
                }
                if (lifePic == 0) {

                } else if (lifePic == 1){
                    lifePic = 0;
                } else if (lifePic == 2){
                    lifePic = 1;
                }

                final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

                final Runnable task = new Runnable() {
                    public void run() {

                        levelText = 11;

                    }

                };
                final ScheduledFuture<?> handle =
                        scheduler.scheduleAtFixedRate(task, 0, 2, TimeUnit.SECONDS);

                scheduler.schedule(new Runnable() {
                    public void run() {

                        levelText = 0;

                        handle.cancel(true);
                    }
                }, 2, TimeUnit.SECONDS);

            }
            //LEVEL 12
            else if (GamePanel.HiddenStoryScore == 230 && GamePanel.StoryLevel == 10) {

                MainActivity.ping.start();
                resetStory1();
                GamePanel.StoryLevel = 11;

                final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

                final Runnable task = new Runnable() {
                    public void run() {

                        levelText = 12;

                    }

                };
                final ScheduledFuture<?> handle =
                        scheduler.scheduleAtFixedRate(task, 0, 2, TimeUnit.SECONDS);

                scheduler.schedule(new Runnable() {
                    public void run() {

                        levelText = 0;

                        handle.cancel(true);
                    }
                }, 2, TimeUnit.SECONDS);


            }
            //LEVEL 13
            else if (GamePanel.HiddenStoryScore == 260 && GamePanel.StoryLevel == 11) {

                MainActivity.ping.start();
                resetStory3();
                GamePanel.StoryLevel = 12;

                final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

                final Runnable task = new Runnable() {
                    public void run() {

                        levelText = 13;

                    }

                };
                final ScheduledFuture<?> handle =
                        scheduler.scheduleAtFixedRate(task, 0, 2, TimeUnit.SECONDS);

                scheduler.schedule(new Runnable() {
                    public void run() {

                        levelText = 0;

                        handle.cancel(true);
                    }
                }, 2, TimeUnit.SECONDS);

            }
            //LEVEL 14
            else if (GamePanel.HiddenStoryScore == 280 && GamePanel.StoryLevel == 12) {

                MainActivity.ping.start();
                resetStory1();
                GamePanel.StoryLevel = 13;

                final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

                final Runnable task = new Runnable() {
                    public void run() {

                        levelText = 14;

                    }

                };
                final ScheduledFuture<?> handle =
                        scheduler.scheduleAtFixedRate(task, 0, 2, TimeUnit.SECONDS);

                scheduler.schedule(new Runnable() {
                    public void run() {

                        levelText = 0;

                        handle.cancel(true);
                    }
                }, 2, TimeUnit.SECONDS);

            }
            //LEVEL 15
            else if (GamePanel.HiddenStoryScore == 310 && GamePanel.StoryLevel == 13) {

                MainActivity.ping.start();
                resetStory4();
                GamePanel.StoryLevel = 14;

                final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

                final Runnable task = new Runnable() {
                    public void run() {

                        levelText = 15;

                    }

                };
                final ScheduledFuture<?> handle =
                        scheduler.scheduleAtFixedRate(task, 0, 2, TimeUnit.SECONDS);

                scheduler.schedule(new Runnable() {
                    public void run() {

                        levelText = 0;

                        handle.cancel(true);
                    }
                }, 2, TimeUnit.SECONDS);

            }
            //LEVEL 16
            else if (GamePanel.HiddenStoryScore == 350 && GamePanel.StoryLevel == 14) {

                MainActivity.ping.start();
                resetStory1();
                GamePanel.StoryLevel = 15;

                if (life == 0) {
                } else if (life == 1){
                    life = 0;
                } else if (life == 2){
                    life = 1;
                }
                if (lifePic == 0) {

                } else if (lifePic == 1){
                    lifePic = 0;
                } else if (lifePic == 2){
                    lifePic = 1;
                }

                final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

                final Runnable task = new Runnable() {
                    public void run() {

                        levelText = 16;

                    }

                };
                final ScheduledFuture<?> handle =
                        scheduler.scheduleAtFixedRate(task, 0, 2, TimeUnit.SECONDS);

                scheduler.schedule(new Runnable() {
                    public void run() {

                        levelText = 0;

                        handle.cancel(true);
                    }
                }, 2, TimeUnit.SECONDS);

            }
            //LEVEL 17
            else if (GamePanel.HiddenStoryScore == 390 && GamePanel.StoryLevel == 15) {

                MainActivity.ping.start();
                resetStory2();
                GamePanel.StoryLevel = 16;

                final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

                final Runnable task = new Runnable() {
                    public void run() {

                        levelText = 17;

                    }

                };
                final ScheduledFuture<?> handle =
                        scheduler.scheduleAtFixedRate(task, 0, 2, TimeUnit.SECONDS);

                scheduler.schedule(new Runnable() {
                    public void run() {

                        levelText = 0;

                        handle.cancel(true);
                    }
                }, 2, TimeUnit.SECONDS);

            }
            //LEVEL 18
            else if (GamePanel.HiddenStoryScore == 430 && GamePanel.StoryLevel == 16) {

                MainActivity.ping.start();
                resetStory2();
                GamePanel.StoryLevel = 17;

                final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

                final Runnable task = new Runnable() {
                    public void run() {

                        levelText = 18;

                    }

                };
                final ScheduledFuture<?> handle =
                        scheduler.scheduleAtFixedRate(task, 0, 2, TimeUnit.SECONDS);

                scheduler.schedule(new Runnable() {
                    public void run() {

                        levelText = 0;

                        handle.cancel(true);
                    }
                }, 2, TimeUnit.SECONDS);

            }
            //LEVEL 19
            else if (GamePanel.HiddenStoryScore == 470 && GamePanel.StoryLevel == 17) {

                MainActivity.ping.start();
                resetStory3();
                GamePanel.StoryLevel = 18;

                final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

                final Runnable task = new Runnable() {
                    public void run() {

                        levelText = 19;

                    }

                };
                final ScheduledFuture<?> handle =
                        scheduler.scheduleAtFixedRate(task, 0, 2, TimeUnit.SECONDS);

                scheduler.schedule(new Runnable() {
                    public void run() {

                        levelText = 0;

                        handle.cancel(true);
                    }
                }, 2, TimeUnit.SECONDS);

            }
            //LEVEL 20
            else if (GamePanel.HiddenStoryScore == 500 && GamePanel.StoryLevel == 18) {

                MainActivity.ping.start();
                resetStory2();
                GamePanel.StoryLevel = 19;

                if (life == 0) {
                } else if (life == 1){
                    life = 0;
                } else if (life == 2){
                    life = 1;
                }
                if (lifePic == 0) {

                } else if (lifePic == 1){
                    lifePic = 0;
                } else if (lifePic == 2){
                    lifePic = 1;
                }

                final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

                final Runnable task = new Runnable() {
                    public void run() {

                        levelText = 20;

                    }

                };
                final ScheduledFuture<?> handle =
                        scheduler.scheduleAtFixedRate(task, 0, 2, TimeUnit.SECONDS);

                scheduler.schedule(new Runnable() {
                    public void run() {

                        levelText = 0;

                        handle.cancel(true);
                    }
                }, 2, TimeUnit.SECONDS);

            }
        }


    }



//gameover
    private void drawCentreText(Canvas canvas, Paint paint, String text) {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.getTextBounds(text, 0, text.length(), r);
        float x = cWidth / 2f - r.width() / 2f - r.left;
        float y = (cHeight / 2f + r.height() / 2f - r.bottom) - ((Constants.SCREEN_WIDTH/2) + Constants.SCREEN_WIDTH/16);
        canvas.drawText(text, x, y, paint);
    }

//score
    private void drawCentreText2(Canvas canvas, Paint paint, String text) {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.getTextBounds(text, 0, text.length(), r);
        float x = cWidth / 2f - r.width() / 2f - r.left;
        float y = (cHeight / 2f + r.height() / 2f - r.bottom) - ((Constants.SCREEN_WIDTH/8) + Constants.SCREEN_HEIGHT/10);
        canvas.drawText(text, x, y, paint);
    }
//score
    private void drawCentreText4(Canvas canvas, Paint paint, String text) {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.getTextBounds(text, 0, text.length(), r);
        float x = cWidth / 2f - r.width() / 2f - r.left;
        float y = (cHeight / 2f + r.height() / 2f - r.bottom - (Constants.SCREEN_WIDTH/30) - Constants.SCREEN_HEIGHT/10);
        canvas.drawText(text, x, y, paint);
    }
//high
    private void drawCentreText3(Canvas canvas, Paint paint, String text) {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.getTextBounds(text, 0, text.length(), r);
        float x = cWidth / 2f - r.width() / 2f - r.left;
        float y = (cHeight / 2f + r.height() / 2f - r.bottom) + ((Constants.SCREEN_WIDTH/18) - Constants.SCREEN_HEIGHT/10);
        canvas.drawText(text, x, y, paint);
    }

    private void drawCentreText5(Canvas canvas, Paint paint, String text) {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.getTextBounds(text, 0, text.length(), r);
        float x = cWidth / 2f - r.width() / 2f - r.left;
        float y = (cHeight / 2f + r.height() / 2f - r.bottom) + ((Constants.SCREEN_WIDTH/8)- Constants.SCREEN_HEIGHT/10);
        canvas.drawText(text, x, y, paint);
    }

    private void drawCentreText6(Canvas canvas, Paint paint, String text) {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.getTextBounds(text, 0, text.length(), r);
        float x = cWidth / 2f - r.width() / 2f - r.left;
        float y = (cHeight / 2f + r.height() / 2f - r.bottom) - Constants.SCREEN_HEIGHT/10;
        canvas.drawText(text, x, y, paint);
    }
    private void drawCentreText7(Canvas canvas, Paint paint, String text) {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.getTextBounds(text, 0, text.length(), r);
        float x = cWidth / 2f - r.width() / 2f - r.left;
        float y = (cHeight / 2f + r.height() / 2f - r.bottom) - (Constants.SCREEN_WIDTH/14);
        canvas.drawText(text, x, y, paint);
    }

    private void drawCentreText8(Canvas canvas, Paint paint, String text) {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.getTextBounds(text, 0, text.length(), r);
        float x = cWidth / 2f - r.width() / 2f - r.left;
        float y = (cHeight / 2f + r.height() / 2f - r.bottom) - (Constants.SCREEN_WIDTH/50);
        canvas.drawText(text, x, y, paint);
    }

    private void drawCentreText17(Canvas canvas, Paint paint, String text) {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.getTextBounds(text, 0, text.length(), r);
        float x = (cWidth / 2f - r.width() / 2f - r.left) + ((Constants.SCREEN_WIDTH/4) + Constants.SCREEN_WIDTH/10);
        float y = (cHeight / 2f + r.height() / 2f - r.bottom) - ((Constants.SCREEN_HEIGHT/2) - Constants.SCREEN_WIDTH/14);
        canvas.drawText(text, x, y, paint);
    }

    private void drawCentreText18(Canvas canvas, Paint paint, String text) {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.getTextBounds(text, 0, text.length(), r);
        float x = (cWidth / 2f - r.width() / 2f - r.left) + ((Constants.SCREEN_WIDTH/4) + (Constants.SCREEN_WIDTH/7));
        float y = (cHeight / 2f + r.height() / 2f - r.bottom) - (((Constants.SCREEN_HEIGHT/2) - Constants.SCREEN_WIDTH/6));
        canvas.drawText(text, x, y, paint);
    }

    private void drawCentreText19(Canvas canvas, Paint paint, String text) {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.getTextBounds(text, 0, text.length(), r);
        float x = (cWidth / 2f - r.width() / 2f - r.left) + ((Constants.SCREEN_WIDTH/4) + (Constants.SCREEN_WIDTH/7));
        float y = (cHeight / 2f + r.height() / 2f - r.bottom) - (((Constants.SCREEN_HEIGHT/2) - Constants.SCREEN_HEIGHT/6) + (Constants.SCREEN_WIDTH/20));
        canvas.drawText(text, x, y, paint);
    }




}
