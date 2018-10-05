package com.Kanonji.BirdsCantSeeGlass;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.view.KeyEvent;
import android.view.MotionEvent;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


/**
 * Created by Ibrahim on 04/07/2017.
 */

public class MenuSelectScene implements Scene {

    @Override
    public void update() {

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

    Bitmap bitmap1 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.storymode2, 300, 300);

    Bitmap bitmap2 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.endlessmode2, 300 , 300);

    Bitmap bitmap100 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.back1, 300, 300);

    Bitmap resizedbitmap1 = Bitmap.createScaledBitmap(bitmap1, Constants.SCREEN_WIDTH, Constants.SCREEN_WIDTH/2 - Constants.SCREEN_WIDTH/10, true);

    Bitmap resizedbitmap2 = Bitmap.createScaledBitmap(bitmap2, Constants.SCREEN_WIDTH, Constants.SCREEN_WIDTH/2 - Constants.SCREEN_WIDTH/10, true);

    @Override
    public void draw(Canvas canvas) {

        Paint paint = new Paint();

        Typeface myTypeface = Typeface.createFromAsset(Constants.CURRENT_CONTEXT.getAssets(), "chunk.ttf");

        canvas.drawColor(Color.rgb(0, 255, 255));

        //BitmapFactory.Options options = new BitmapFactory.Options();
        //options.inJustDecodeBounds = false;
        //options.inSampleSize = 1;

        canvas.drawBitmap(bitmap100, null, new RectF(0, 0, (Constants.SCREEN_WIDTH), Constants.SCREEN_HEIGHT) , null);

        int h1 = resizedbitmap1.getHeight();
        int w1 = resizedbitmap1.getWidth();
        canvas.drawBitmap(resizedbitmap1, ((Constants.SCREEN_WIDTH / 2 - w1/2)), (((Constants.SCREEN_HEIGHT / 3))) + h1/2, new Paint());

        int h2 = resizedbitmap2.getHeight();
        int w2 = resizedbitmap2.getWidth();
        canvas.drawBitmap(resizedbitmap2, ((Constants.SCREEN_WIDTH / 2) - w2/2), (((Constants.SCREEN_HEIGHT / 2) + (h2/2 + h2/3))), new Paint());


    }


    @Override
    public void terminate() {
        SceneManager.ACTIVE_SCENE = 10;


    }

    @Override
    public boolean recieveTouch(MotionEvent event) {
        //switch (event.getAction()) {
        //case MotionEvent.ACTION_DOWN:
        //SceneManager.ACTIVE_SCENE = 1;
        //GameplayScene.reset();
        //}


        int action = event.getAction();
        int x = (int) event.getX();  // or getRawX();
        int y = (int) event.getY();

        int h1 = resizedbitmap1.getHeight();
        int w1 = resizedbitmap1.getWidth();
        float xRatio1 = ((Constants.SCREEN_WIDTH / 2 - w1/2));
        float yRatio1 = (((Constants.SCREEN_HEIGHT / 3))) + h1/2;

        int h2 = resizedbitmap2.getHeight();
        int w2 = resizedbitmap2.getWidth();
        float xRatio2 = ((Constants.SCREEN_WIDTH / 2) - w2/2);
        float yRatio2 = (((Constants.SCREEN_HEIGHT / 2) + (h2/2 + h2/3)));

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (x >= xRatio1 && x < (xRatio1 + resizedbitmap1.getWidth())
                        && y >= yRatio1 && y < (yRatio1 + resizedbitmap1.getHeight())) {
                    MainActivity.click.start();
                    SceneManager.ACTIVE_SCENE = 9;
                    GameplayScene.reset();

                }
                if (x >= xRatio2 && x < (xRatio2 + resizedbitmap2.getWidth())
                        && y >= yRatio2 && y < (yRatio2 + resizedbitmap2.getHeight())) {
                    MainActivity.click.start();
                    SceneManager.ACTIVE_SCENE = 0;
                    GameplayScene.reset();

                    if (GamePanel.DailyTask3 == 0) {
                        GamePanel.DailyTask3Counter = GamePanel.DailyTask3Counter + 1;
                    }
                    if (GamePanel.DailyTask3 == 0) {
                        if (GamePanel.DailyTask3Counter >= 10) {
                            GamePanel.DailyTask3 = 1;
                        }
                    }
                }



        } return true;
    }
    

}
