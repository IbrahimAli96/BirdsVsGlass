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
import android.view.MotionEvent;

import java.util.Calendar;


/**
 * Created by Ibrahim on 04/07/2017.
 */

public class MenuScene implements Scene {

    private Rect r = new Rect();

    private int color;

    private SceneManager manager;

    private Rect bitmapRect;

    Calendar cal = Calendar.getInstance();
    boolean monday = cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY;
    boolean tuesday = cal.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY;
    boolean wednesday = cal.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY;
    boolean thursday = cal.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY;
    boolean friday = cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY;
    boolean saturday = cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY;
    boolean sunday = cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;

    public MenuScene() {

    }

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


    Bitmap bitmap100 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.back1, 300, 300);

    Bitmap bitmap1 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.play3, 150, 150);

    Bitmap bitmap2 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.store1, 100, 100);

    Bitmap bitmap3 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.buy1, 100, 100);

    Bitmap bitmap4 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.trophy1, 100, 100);

    Bitmap bitmap5 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.daily1, 100, 100);

    Bitmap bitmap111 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.sp2, 30, 30);

    Bitmap bitmapSP = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.survival, 30, 30);

    Bitmap resizedbitmap1 = Bitmap.createScaledBitmap(bitmap1, Constants.SCREEN_WIDTH/2 + Constants.SCREEN_WIDTH/3, Constants.SCREEN_WIDTH/4, true);

    Bitmap resizedbitmap2 = Bitmap.createScaledBitmap(bitmap2, Constants.SCREEN_WIDTH/6, Constants.SCREEN_WIDTH/6, true);

    Bitmap resizedbitmap3 = Bitmap.createScaledBitmap(bitmap3, Constants.SCREEN_WIDTH / 6, Constants.SCREEN_WIDTH / 6, true);

    Bitmap resizedbitmap4 = Bitmap.createScaledBitmap(bitmap4, Constants.SCREEN_WIDTH/6, Constants.SCREEN_WIDTH/6, true);

    Bitmap resizedbitmap5 = Bitmap.createScaledBitmap(bitmap5, Constants.SCREEN_WIDTH/6, Constants.SCREEN_WIDTH/5, true);


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
        canvas.drawBitmap(resizedbitmap1, ((Constants.SCREEN_WIDTH / 2 - w1/2)), ((Constants.SCREEN_HEIGHT / 2)), new Paint());

        int h2 = resizedbitmap2.getHeight();
        int w2 = resizedbitmap2.getWidth();
        canvas.drawBitmap(resizedbitmap2, ((Constants.SCREEN_WIDTH / 2) - w2*2), (((Constants.SCREEN_HEIGHT / 2) + (h2)) + (Constants.SCREEN_HEIGHT/9)), new Paint());

        int h3 = resizedbitmap3.getHeight();
        int w3 = resizedbitmap3.getWidth();
        canvas.drawBitmap(resizedbitmap3, ((Constants.SCREEN_WIDTH / 2) + w3), (((Constants.SCREEN_HEIGHT / 2) + (h3)) + (Constants.SCREEN_HEIGHT / 9)), new Paint());

        int h4 = resizedbitmap4.getHeight();
        int w4 = resizedbitmap4.getWidth();
        canvas.drawBitmap(resizedbitmap4, ((Constants.SCREEN_WIDTH / 2) - w4/2), (((Constants.SCREEN_HEIGHT / 2) + (h4)) + (Constants.SCREEN_HEIGHT/9)), new Paint());

        int h5 = resizedbitmap5.getHeight();
        int w5 = resizedbitmap5.getWidth();
        canvas.drawBitmap(resizedbitmap5, (0 + w5/4), 0 + (h5/4), new Paint());

        if (GamePanel.DailyTask1 == 1 || GamePanel.DailyTask2 == 1 || GamePanel.DailyTask3 == 1 || GamePanel.DailyTask4 == 1) {
            Bitmap bitmapComp = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.complete);
            Bitmap resizedbitmapComp = Bitmap.createScaledBitmap(bitmapComp, Constants.SCREEN_WIDTH/12, Constants.SCREEN_WIDTH / 7, true);
            canvas.drawBitmap(resizedbitmapComp, 0 + w5, 0 , new Paint());
        }

        Bitmap resizedbitmap111 = Bitmap.createScaledBitmap(bitmap111, Constants.SCREEN_WIDTH/10, Constants.SCREEN_WIDTH/10, true);
        int h111 = resizedbitmap111.getHeight();
        int w111 = resizedbitmap111.getWidth();
        canvas.drawBitmap(resizedbitmap111, ((Constants.SCREEN_WIDTH / 4)-( w111 / 2) + (Constants.SCREEN_WIDTH/2)), (((Constants.SCREEN_HEIGHT / 2) - (h111 / 2) - Constants.SCREEN_WIDTH/7) - (Constants.SCREEN_HEIGHT/3)), new Paint());

        Bitmap resizedbitmapSP = Bitmap.createScaledBitmap(bitmapSP, Constants.SCREEN_WIDTH/4 + w111/2, Constants.SCREEN_WIDTH/24, true);
        int hSP = resizedbitmapSP.getHeight();
        int wSP = resizedbitmapSP.getWidth();
        canvas.drawBitmap(resizedbitmapSP, ((Constants.SCREEN_WIDTH / 4) + (Constants.SCREEN_WIDTH/2)) - w111/2, (((Constants.SCREEN_HEIGHT / 2) - (h111) - Constants.SCREEN_WIDTH/7) - (Constants.SCREEN_HEIGHT/3)), new Paint());

        paint.setTextSize(Constants.SCREEN_WIDTH / 20);
        paint.setTypeface(myTypeface);
        paint.setColor(Color.BLACK);
        drawCentreText3(canvas, paint, "" + GamePanel.getHighCoin());

        if (GamePanel.Task1 == 1 || GamePanel.Task2 == 1 || GamePanel.Task3 == 1 || GamePanel.Task4 == 1 || GamePanel.Task5 == 1 ||
                GamePanel.Task6 == 1 || GamePanel.Task7 == 1 || GamePanel.Task8 == 1 || GamePanel.Task9 == 1 ||
                GamePanel.Task10 == 1 || GamePanel.Task11 == 1 || GamePanel.Task12 == 1) {
            Bitmap bitmapComp = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.complete, 50, 50);
            Bitmap resizedbitmapComp = Bitmap.createScaledBitmap(bitmapComp, Constants.SCREEN_WIDTH / 12, Constants.SCREEN_WIDTH / 7, true);
            canvas.drawBitmap(resizedbitmapComp, 0 + Constants.SCREEN_WIDTH / 4 + Constants.SCREEN_WIDTH / 2 + Constants.SCREEN_WIDTH / 30, (0 + ((Constants.SCREEN_HEIGHT)) - Constants.SCREEN_HEIGHT / 3) + Constants.SCREEN_HEIGHT / 80, new Paint());
        }
    }


    @Override
    public void terminate() {
        SceneManager.ACTIVE_SCENE = 4;


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
        float yRatio1 = (((Constants.SCREEN_HEIGHT / 2)));

        int h2 = resizedbitmap2.getHeight();
        int w2 = resizedbitmap2.getWidth();
        float xRatio2 = ((Constants.SCREEN_WIDTH / 2) - w2*2);
        float yRatio2 = (((Constants.SCREEN_HEIGHT / 2) + (h2)) + (Constants.SCREEN_HEIGHT/9));

        int h3 = resizedbitmap3.getHeight();
        int w3 = resizedbitmap3.getWidth();
        float xRatio3 = ((Constants.SCREEN_WIDTH / 2) + w3);
        float yRatio3 = (((Constants.SCREEN_HEIGHT / 2) + (h3)) + (Constants.SCREEN_HEIGHT/9));

        int h4 = resizedbitmap4.getHeight();
        int w4 = resizedbitmap4.getWidth();
        float xRatio4 = ((Constants.SCREEN_WIDTH / 2) - w4/2);
        float yRatio4 = (((Constants.SCREEN_HEIGHT / 2) + (h4)) + (Constants.SCREEN_HEIGHT/9));

        int h5 = resizedbitmap5.getHeight();
        int w5 = resizedbitmap5.getWidth();
        float xRatio5 = (0 + w5/4);
        float yRatio5 = 0 + (h5/4);


        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (x >= xRatio1 && x < (xRatio1 + resizedbitmap1.getWidth())
                        && y >= yRatio1 && y < (yRatio1 + resizedbitmap1.getHeight())) {
                        MainActivity.click.start();
                        SceneManager.ACTIVE_SCENE = 10;
                        GamePanel.Ad2 = 1;
                        GamePanel.Previous = 4;

                }
                if (x >= xRatio2 && x < (xRatio2 + resizedbitmap2.getWidth())
                        && y >= yRatio2 && y < (yRatio2 + resizedbitmap2.getHeight())) {
                    MainActivity.click.start();
                    SceneManager.ACTIVE_SCENE = 1;
                    GamePanel.Previous = 4;
                }
                if (x >= xRatio3 && x < (xRatio3 + resizedbitmap3.getWidth())
                        && y >= yRatio3 && y < (yRatio3 + resizedbitmap3.getHeight())) {
                    MainActivity.click.start();
                    SceneManager.ACTIVE_SCENE = 3;
                    GamePanel.Previous = 4;

                }
                if (x >= xRatio4 && x < (xRatio4 + resizedbitmap4.getWidth())
                        && y >= yRatio4 && y < (yRatio4 + resizedbitmap4.getHeight())) {
                    MainActivity.click.start();
                    SceneManager.ACTIVE_SCENE = 6;
                    GamePanel.Previous = 4;
                    GamePanel.HighCoin = GamePanel.HighCoin + 10000;
                    //System.exit(0);

                }
                if (x >= xRatio5 && x < (xRatio5 + resizedbitmap5.getWidth())
                        && y >= yRatio5 && y < (yRatio5 + resizedbitmap5.getHeight())) {
                    MainActivity.click.start();

                    SceneManager.ACTIVE_SCENE = 12;
                    GamePanel.Previous = 4;

                    if (monday== true && GamePanel.Monday == 0) {
                        resetDailyTask();
                        GamePanel.Monday = 1;
                        GamePanel.Tuesday = 0;
                        GamePanel.Wednesday = 0;
                        GamePanel.Thursday = 0;
                        GamePanel.Friday = 0;
                        GamePanel.Saturday = 0;
                        GamePanel.Sunday = 0;
                    } else if (tuesday== true && GamePanel.Tuesday == 0) {
                        resetDailyTask();
                        GamePanel.Monday = 0;
                        GamePanel.Tuesday = 1;
                        GamePanel.Wednesday = 0;
                        GamePanel.Thursday = 0;
                        GamePanel.Friday = 0;
                        GamePanel.Saturday = 0;
                        GamePanel.Sunday = 0;
                    } else if (wednesday== true && GamePanel.Wednesday == 0) {
                        resetDailyTask();
                        GamePanel.Monday = 0;
                        GamePanel.Tuesday = 0;
                        GamePanel.Wednesday = 1;
                        GamePanel.Thursday = 0;
                        GamePanel.Friday = 0;
                        GamePanel.Saturday = 0;
                        GamePanel.Sunday = 0;
                    } else if (thursday== true && GamePanel.Thursday == 0) {
                        resetDailyTask();
                        GamePanel.Monday = 0;
                        GamePanel.Tuesday = 0;
                        GamePanel.Wednesday = 0;
                        GamePanel.Thursday = 1;
                        GamePanel.Friday = 0;
                        GamePanel.Saturday = 0;
                        GamePanel.Sunday = 0;
                    } else if (friday== true && GamePanel.Friday == 0) {
                        resetDailyTask();
                        GamePanel.Monday = 0;
                        GamePanel.Tuesday = 0;
                        GamePanel.Wednesday = 0;
                        GamePanel.Thursday = 0;
                        GamePanel.Friday = 1;
                        GamePanel.Saturday = 0;
                        GamePanel.Sunday = 0;
                    } else if (saturday== true && GamePanel.Saturday == 0) {
                        resetDailyTask();
                        GamePanel.Monday = 0;
                        GamePanel.Tuesday = 0;
                        GamePanel.Wednesday = 0;
                        GamePanel.Thursday = 0;
                        GamePanel.Friday = 0;
                        GamePanel.Saturday = 1;
                        GamePanel.Sunday = 0;
                    } else if (sunday== true && GamePanel.Sunday == 0) {
                        resetDailyTask();
                        GamePanel.Monday = 0;
                        GamePanel.Tuesday = 0;
                        GamePanel.Wednesday = 0;
                        GamePanel.Thursday = 0;
                        GamePanel.Friday = 0;
                        GamePanel.Saturday = 0;
                        GamePanel.Sunday = 1;
                    }

                }
        } return true;
    }


    public void resetDailyTask() {

        GamePanel.DailyTask1 = 0;
        GamePanel.DailyTask2 = 0;
        GamePanel.DailyTask3 = 0;
        GamePanel.DailyTask3Counter = 0;

        GamePanel.DailyTask4 = 0;
        GamePanel.DailyTask4Counter = 0;
    }


    private void drawCentreText(Canvas canvas, Paint paint, String text) {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.getTextBounds(text, 0, text.length(), r);
        float x = cWidth / 2f - r.width() / 2f - r.left;
        float y = (cHeight / 2f + r.height() / 2f - r.bottom) - 280;
        canvas.drawText(text, x, y, paint);
    }
    private void drawCentreText1(Canvas canvas, Paint paint, String text) {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.getTextBounds(text, 0, text.length(), r);
        float x = cWidth / 2f - r.width() / 2f - r.left;
        float y = (cHeight / 2f + r.height() / 2f - r.bottom) - (Constants.SCREEN_HEIGHT/22);
        canvas.drawText(text, x, y, paint);
    }
    private void drawCentreText5(Canvas canvas, Paint paint, String text) {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.getTextBounds(text, 0, text.length(), r);
        float x = cWidth / 2f - r.width() / 2f - r.left;
        float y = (cHeight / 2f + r.height() / 2f - r.bottom) + (Constants.SCREEN_HEIGHT/16);
        canvas.drawText(text, x, y, paint);
    }
    private void drawCentreText6(Canvas canvas, Paint paint, String text) {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.getTextBounds(text, 0, text.length(), r);
        float x = cWidth / 2f - r.width() / 2f - r.left;
        float y = (cHeight / 2f + r.height() / 2f - r.bottom) + ((Constants.SCREEN_HEIGHT/6) + Constants.SCREEN_HEIGHT/80);
        canvas.drawText(text, x, y, paint);
    }
    private void drawCentreText2(Canvas canvas, Paint paint, String text) {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.getTextBounds(text, 0, text.length(), r);
        float x = (cWidth / 2f - r.width() / 2f - r.left) + ((Constants.SCREEN_WIDTH/4) + Constants.SCREEN_WIDTH/10);
        float y = (cHeight / 2f + r.height() / 2f - r.bottom) - ((Constants.SCREEN_HEIGHT/2) - Constants.SCREEN_WIDTH/14);
        canvas.drawText(text, x, y, paint);
    }
    private void drawCentreText3(Canvas canvas, Paint paint, String text) {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.getTextBounds(text, 0, text.length(), r);
        float x = (cWidth / 2f - r.width() / 2f - r.left) + ((Constants.SCREEN_WIDTH/4) + (Constants.SCREEN_WIDTH/7));
        float y = (cHeight / 2f + r.height() / 2f - r.bottom) - ((Constants.SCREEN_HEIGHT/2) - Constants.SCREEN_WIDTH/6);
        canvas.drawText(text, x, y, paint);
    }

}
