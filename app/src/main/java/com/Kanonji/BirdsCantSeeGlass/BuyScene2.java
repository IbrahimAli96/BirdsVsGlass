package com.Kanonji.BirdsCantSeeGlass;

import android.app.Activity;
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
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;

import java.lang.ref.WeakReference;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by Ibrahim on 18/07/2017.
 */

public class BuyScene2 implements Scene {

    private Rect r = new Rect();

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

    Bitmap bitmap14 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.prev1, 100, 100);
    Bitmap resizedbitmap14 = Bitmap.createScaledBitmap(bitmap14, Constants.SCREEN_WIDTH/6, Constants.SCREEN_WIDTH/6, true);

    Bitmap bitmap15 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.next1, 100 ,100);
    Bitmap resizedbitmap15 = Bitmap.createScaledBitmap(bitmap15, Constants.SCREEN_WIDTH/6, Constants.SCREEN_WIDTH/6, true);

    Bitmap bitmap16 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.menu1, 100 , 100);
    Bitmap resizedbitmap16 = Bitmap.createScaledBitmap(bitmap16, Constants.SCREEN_WIDTH/6, Constants.SCREEN_WIDTH/6, true);

    Bitmap bitmap100 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.back3, 300, 300);

    Bitmap bitmap111 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.sp2, 30, 30);

    Bitmap bitmap1 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.task5, 150, 150);
    Bitmap resizedbitmap1 = Bitmap.createScaledBitmap(bitmap1, Constants.SCREEN_WIDTH, Constants.SCREEN_WIDTH / 5, true);
    Bitmap bitmap1c = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.task52, 150, 150);
    Bitmap resizedbitmap1c = Bitmap.createScaledBitmap(bitmap1c, Constants.SCREEN_WIDTH, Constants.SCREEN_WIDTH / 5, true);
    Bitmap bitmap2 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.task6, 150, 150);
    Bitmap resizedbitmap2 = Bitmap.createScaledBitmap(bitmap2, Constants.SCREEN_WIDTH, Constants.SCREEN_WIDTH / 5, true);
    Bitmap bitmap2c = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.task62, 150, 150);
    Bitmap resizedbitmap2c = Bitmap.createScaledBitmap(bitmap2c, Constants.SCREEN_WIDTH, Constants.SCREEN_WIDTH / 5, true);
    Bitmap bitmap3 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.task7, 150, 150);
    Bitmap resizedbitmap3 = Bitmap.createScaledBitmap(bitmap3, Constants.SCREEN_WIDTH, Constants.SCREEN_WIDTH / 5, true);
    Bitmap bitmap3c = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.task72, 150, 150);
    Bitmap resizedbitmap3c = Bitmap.createScaledBitmap(bitmap3c, Constants.SCREEN_WIDTH, Constants.SCREEN_WIDTH / 5, true);
    Bitmap bitmap4 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.task8, 150, 150);
    Bitmap resizedbitmap4 = Bitmap.createScaledBitmap(bitmap4, Constants.SCREEN_WIDTH, Constants.SCREEN_WIDTH / 5, true);
    Bitmap bitmap4c = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.task82, 150, 150);
    Bitmap resizedbitmap4c = Bitmap.createScaledBitmap(bitmap4c, Constants.SCREEN_WIDTH, Constants.SCREEN_WIDTH / 5, true);

    Bitmap bitmapComp = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.complete, 50, 50);
    Bitmap resizedbitmapComp = Bitmap.createScaledBitmap(bitmapComp, Constants.SCREEN_WIDTH/12, Constants.SCREEN_WIDTH / 7, true);

    Bitmap bitmapTitle = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.challenges, 100, 100);

    Bitmap bitmapSP = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.survival, 30, 30);

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();

        Typeface myTypeface = Typeface.createFromAsset(Constants.CURRENT_CONTEXT.getAssets(), "chunk.ttf");

        canvas.drawColor(Color.rgb(0, 255, 255));

        //BitmapFactory.Options options = new BitmapFactory.Options();
        //options.inJustDecodeBounds = false;
        //options.inSampleSize = 1;

        //Bitmap bitmap12 = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.grass);
        //int h12 = bitmap12.getHeight();
        //int w12 = bitmap12.getWidth();
        //canvas.drawBitmap(bitmap12, ((Constants.SCREEN_WIDTH / 2)-( w12 / 2)), (((Constants.SCREEN_HEIGHT) - (h12 / 1))), new Paint());

        canvas.drawBitmap(bitmap100, null, new RectF(0, 0, (Constants.SCREEN_WIDTH), Constants.SCREEN_HEIGHT) , null);

        //Menu and Back button and title
        int h14 = resizedbitmap14.getHeight();
        int w14 = resizedbitmap14.getWidth();
        canvas.drawBitmap(resizedbitmap14, (((Constants.SCREEN_WIDTH / 4) - (w14 / 2))), (((Constants.SCREEN_HEIGHT / 2) - (h14 / -1)) + (Constants.SCREEN_HEIGHT/4)), new Paint());

        int h15 = resizedbitmap15.getHeight();
        int w15 = resizedbitmap15.getWidth();
        canvas.drawBitmap(resizedbitmap15, ((((Constants.SCREEN_WIDTH / 4) + Constants.SCREEN_WIDTH / 2) - (w15 / 2))), (((Constants.SCREEN_HEIGHT / 2) - (h15 / -1))+ (Constants.SCREEN_HEIGHT/4)), new Paint());

        int h16 = resizedbitmap16.getHeight();
        int w16 = resizedbitmap16.getWidth();
        canvas.drawBitmap(resizedbitmap16, (Constants.SCREEN_WIDTH / 2) - (w16 / 2), (((Constants.SCREEN_HEIGHT / 2) - (h16 / -1))+ (Constants.SCREEN_HEIGHT/4)), new Paint());

        Bitmap resizedbitmap111 = Bitmap.createScaledBitmap(bitmap111, Constants.SCREEN_WIDTH/10, Constants.SCREEN_WIDTH/10, true);
        int h111 = resizedbitmap111.getHeight();
        int w111 = resizedbitmap111.getWidth();
        canvas.drawBitmap(resizedbitmap111, ((Constants.SCREEN_WIDTH / 4)-( w111 / 2) + (Constants.SCREEN_WIDTH/2)), (((Constants.SCREEN_HEIGHT / 2) - (h111 / 2) - Constants.SCREEN_WIDTH/7) - (Constants.SCREEN_HEIGHT/3)), new Paint());

        //challenges

        if (GamePanel.Task5 == 0 || GamePanel.Task5 == 1) {

            canvas.drawBitmap(resizedbitmap1, 0, 0 + ((Constants.SCREEN_HEIGHT / 5)), new Paint());
        } else if (GamePanel.Task5 == 2) {

            canvas.drawBitmap(resizedbitmap1c, 0, 0 + ((Constants.SCREEN_HEIGHT / 5)), new Paint());
        }

        if (GamePanel.Task6 == 0 || GamePanel.Task6 == 1) {

            canvas.drawBitmap(resizedbitmap2, 0, 0 + ((Constants.SCREEN_HEIGHT / 3)) + Constants.SCREEN_HEIGHT/40, new Paint());
        } else if (GamePanel.Task6 == 2) {

            canvas.drawBitmap(resizedbitmap2c, 0, 0 + ((Constants.SCREEN_HEIGHT / 3)) + Constants.SCREEN_HEIGHT/40, new Paint());
        }

        if (GamePanel.Task7 == 0 || GamePanel.Task7 == 1) {

            canvas.drawBitmap(resizedbitmap3, 0, 0 + ((Constants.SCREEN_HEIGHT / 2)) + Constants.SCREEN_HEIGHT/60, new Paint());
        } else if (GamePanel.Task7 == 2) {

            canvas.drawBitmap(resizedbitmap3c, 0, 0 + ((Constants.SCREEN_HEIGHT / 2)) + Constants.SCREEN_HEIGHT/60, new Paint());
        }

        if (GamePanel.Task8 == 0 || GamePanel.Task8 == 1) {

            canvas.drawBitmap(resizedbitmap4, 0, (0 + ((Constants.SCREEN_HEIGHT)) - Constants.SCREEN_HEIGHT/3) + Constants.SCREEN_HEIGHT/100, new Paint());
        } else if (GamePanel.Task8 == 2) {

            canvas.drawBitmap(resizedbitmap4c, 0, (0 + ((Constants.SCREEN_HEIGHT)) - Constants.SCREEN_HEIGHT/3) + Constants.SCREEN_HEIGHT/100, new Paint());
        }


        if (GamePanel.Task5 == 1) {
            canvas.drawBitmap(resizedbitmapComp, 0 + Constants.SCREEN_WIDTH/3 + Constants.SCREEN_WIDTH/3, 0 + ((Constants.SCREEN_HEIGHT / 5)) - Constants.SCREEN_WIDTH/20, new Paint());
        }
        if (GamePanel.Task6 == 1) {
            canvas.drawBitmap(resizedbitmapComp, 0 + Constants.SCREEN_WIDTH/3 + Constants.SCREEN_WIDTH/3, 0 + ((Constants.SCREEN_HEIGHT / 3)) + Constants.SCREEN_HEIGHT/40 - Constants.SCREEN_WIDTH/20, new Paint());
        }
        if (GamePanel.Task7 == 1) {
            canvas.drawBitmap(resizedbitmapComp, 0 + Constants.SCREEN_WIDTH/3 + Constants.SCREEN_WIDTH/3, 0 + ((Constants.SCREEN_HEIGHT / 2)) + Constants.SCREEN_HEIGHT/60 - Constants.SCREEN_WIDTH/20, new Paint());
        }
        if (GamePanel.Task8 == 1) {
            canvas.drawBitmap(resizedbitmapComp, 0 + Constants.SCREEN_WIDTH/3 + Constants.SCREEN_WIDTH/3, (0 + ((Constants.SCREEN_HEIGHT)) - Constants.SCREEN_HEIGHT/3) + Constants.SCREEN_HEIGHT/100 - Constants.SCREEN_WIDTH/20, new Paint());
        }

        Bitmap resizedbitmapTitle = Bitmap.createScaledBitmap(bitmapTitle, Constants.SCREEN_WIDTH/2 + Constants.SCREEN_WIDTH/4, Constants.SCREEN_WIDTH/8, true);
        int hTitle = resizedbitmapTitle.getHeight();
        int wTitle = resizedbitmapTitle.getWidth();
        canvas.drawBitmap(resizedbitmapTitle, Constants.SCREEN_WIDTH/2 - wTitle/2, 0 + Constants.SCREEN_HEIGHT/9, new Paint());

        Bitmap resizedbitmapSP = Bitmap.createScaledBitmap(bitmapSP, Constants.SCREEN_WIDTH/4 + w111/2, Constants.SCREEN_WIDTH/24, true);
        int hSP = resizedbitmapSP.getHeight();
        int wSP = resizedbitmapSP.getWidth();
        canvas.drawBitmap(resizedbitmapSP, ((Constants.SCREEN_WIDTH / 4) + (Constants.SCREEN_WIDTH/2)) - w111/2, (((Constants.SCREEN_HEIGHT / 2) - (h111) - Constants.SCREEN_WIDTH/7) - (Constants.SCREEN_HEIGHT/3)), new Paint());

        paint.setTextSize(Constants.SCREEN_WIDTH / 20);
        paint.setTypeface(myTypeface);
        paint.setColor(Color.BLACK);
        drawCentreText5(canvas, paint, "" + GamePanel.getHighCoin());


    }

    @Override
    public void terminate() {
        SceneManager.ACTIVE_SCENE = 7;


    }

    private static WeakReference<Activity> mActivityRef;
    public static void updateActivity(Activity activity) {
        mActivityRef = new WeakReference<Activity>(activity);
    }

    private void loadAd() {
        if (!MainActivity.mAd2.isLoaded()) {
            MainActivity.mAd2.loadAd("", new AdRequest.Builder().build());
        }

    }

    @Override
    public boolean recieveTouch(MotionEvent event) {
        int action = event.getAction();
        int x = (int) event.getX();  // or getRawX();
        int y = (int) event.getY();

        int h14 = resizedbitmap14.getHeight();
        int w14 = resizedbitmap14.getWidth();
        float xRatio14 = (((Constants.SCREEN_WIDTH / 4) - (w14 / 2)));
        float yRatio14 = (((Constants.SCREEN_HEIGHT / 2) - (h14 / -1)) + (Constants.SCREEN_HEIGHT/4));
        int h15 = resizedbitmap15.getHeight();
        int w15 = resizedbitmap15.getWidth();
        float xRatio15 = ((((Constants.SCREEN_WIDTH / 4) + Constants.SCREEN_WIDTH / 2) - (w15 / 2)));
        float yRatio15 = (((Constants.SCREEN_HEIGHT / 2) - (h15 / -1))+ (Constants.SCREEN_HEIGHT/4));
        int h16 = resizedbitmap16.getHeight();
        int w16 = resizedbitmap16.getWidth();
        float xRatio16 = (Constants.SCREEN_WIDTH / 2) - (w16 / 2);
        float yRatio16 = (((Constants.SCREEN_HEIGHT / 2) - (h16 / -1))+ (Constants.SCREEN_HEIGHT/4));

        //TAsks
        float xRatio1 = 0;
        float yRatio1 = 0 + ((Constants.SCREEN_HEIGHT / 5));

        float xRatio2 = 0;
        float yRatio2 = 0 + ((Constants.SCREEN_HEIGHT / 3)) + Constants.SCREEN_HEIGHT/40;

        float xRatio3 = 0;
        float yRatio3 = 0 + ((Constants.SCREEN_HEIGHT / 2)) + Constants.SCREEN_HEIGHT/60;

        float xRatio4 = 0;
        float yRatio4 = (0 + ((Constants.SCREEN_HEIGHT)) - Constants.SCREEN_HEIGHT/3) + Constants.SCREEN_HEIGHT/100;


        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (x >= xRatio14 && x < (xRatio14 + resizedbitmap14.getWidth())
                        && y >= yRatio14 && y < (yRatio14 + resizedbitmap14.getHeight())) {
                    MainActivity.click.start();
                    SceneManager.ACTIVE_SCENE = 3;
                }
                if (x >= xRatio15 && x < (xRatio15 + resizedbitmap15.getWidth())
                        && y >= yRatio15 && y < (yRatio15 + resizedbitmap15.getHeight())) {
                    MainActivity.click.start();
                    SceneManager.ACTIVE_SCENE = 8;
                }
                if (x >= xRatio16 && x < (xRatio16 + resizedbitmap16.getWidth())
                        && y >= yRatio16 && y < (yRatio16 + resizedbitmap16.getHeight())) {
                    MainActivity.click.start();
                    MainActivity.mInterstitialAd.loadAd((new AdRequest.Builder().build()));
                    MainActivity.mInterstitialAd.show();
                    //Appodeal.show(mActivityRef.get(), Appodeal.INTERSTITIAL);
                    SceneManager.ACTIVE_SCENE = 4;
                }

                //Task1
                if (x >= xRatio1 && x < (xRatio1 + resizedbitmap1.getWidth())
                        && y >= yRatio1 && y < (yRatio1 + resizedbitmap1.getHeight())) {

                    if (GamePanel.Task5 == 1) {
                        MainActivity.cash.start();
                        Toast.makeText(mActivityRef.get(), "Congrats You Just Received 500 Survival Points", Toast.LENGTH_SHORT).show();
                        GamePanel.HighCoin = GamePanel.HighCoin + 500;
                        GamePanel.Task5 = 2;
                    } else if (GamePanel.Task5 == 0){
                        Toast.makeText(mActivityRef.get(), "Task Incomplete", Toast.LENGTH_SHORT).show();
                    } else if (GamePanel.Task5 == 2) {
                        Toast.makeText(mActivityRef.get(), "Task Complete, Already Received 500 Survival Points", Toast.LENGTH_SHORT).show();
                    }

                }
                //task2
                if (x >= xRatio2 && x < (xRatio2 + resizedbitmap2.getWidth())
                        && y >= yRatio2 && y < (yRatio2 + resizedbitmap2.getHeight())) {

                    if (GamePanel.Task6 == 1) {
                        MainActivity.cash.start();
                        Toast.makeText(mActivityRef.get(), "Congrats You Just Received 500 Survival Points", Toast.LENGTH_SHORT).show();
                        GamePanel.HighCoin = GamePanel.HighCoin + 500;
                        GamePanel.Task6 = 2;
                    } else if (GamePanel.Task6 == 0){
                        Toast.makeText(mActivityRef.get(), "Task Incomplete", Toast.LENGTH_SHORT).show();
                    } else if (GamePanel.Task6 == 2) {
                        Toast.makeText(mActivityRef.get(), "Task Complete, Already Received 500 Survival Points", Toast.LENGTH_SHORT).show();
                    }

                }
                //task3
                if (x >= xRatio3 && x < (xRatio3 + resizedbitmap3.getWidth())
                        && y >= yRatio3 && y < (yRatio3 + resizedbitmap3.getHeight())) {

                    if (GamePanel.Task7 == 1) {
                        MainActivity.cash.start();
                        Toast.makeText(mActivityRef.get(), "Congrats You Just Received 500 Survival Points", Toast.LENGTH_SHORT).show();
                        GamePanel.HighCoin = GamePanel.HighCoin + 500;
                        GamePanel.Task7 = 2;
                    } else if (GamePanel.Task7 == 0){
                        Toast.makeText(mActivityRef.get(), "Task Incomplete", Toast.LENGTH_SHORT).show();
                    } else if (GamePanel.Task7 == 2) {
                        Toast.makeText(mActivityRef.get(), "Task Complete, Already Received 500 Survival Points", Toast.LENGTH_SHORT).show();
                    }

                }
                //task4
                if (x >= xRatio4 && x < (xRatio4 + resizedbitmap4.getWidth())
                        && y >= yRatio4 && y < (yRatio4 + resizedbitmap4.getHeight())) {

                    if (GamePanel.Task8 == 1) {
                        MainActivity.cash.start();
                        Toast.makeText(mActivityRef.get(), "Congrats You Just Received 500 Survival Points", Toast.LENGTH_SHORT).show();
                        GamePanel.HighCoin = GamePanel.HighCoin + 500;
                        GamePanel.Task8 = 2;
                    } else if (GamePanel.Task8 == 0){
                        Toast.makeText(mActivityRef.get(), "Task Incomplete", Toast.LENGTH_SHORT).show();
                    } else if (GamePanel.Task8 == 2) {
                        Toast.makeText(mActivityRef.get(), "Task Complete, Already Received 500 Survival Points", Toast.LENGTH_SHORT).show();
                    }

                }


        }
        return false;
    }

    private void drawCentreText2(Canvas canvas, Paint paint, String text) {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.getTextBounds(text, 0, text.length(), r);
        float x = cWidth / 2f - r.width() / 2f - r.left;
        float y = (cHeight / 2f + r.height() / 2f - r.bottom) - ((Constants.SCREEN_HEIGHT/3) + (Constants.SCREEN_HEIGHT/40));
        canvas.drawText(text, x, y, paint);
    }

    private void drawCentreText8(Canvas canvas, Paint paint, String text) {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.getTextBounds(text, 0, text.length(), r);
        float x = cWidth / 2f - r.width() / 2f - r.left;
        float y = (cHeight / 2f + r.height() / 2f - r.bottom) - ((Constants.SCREEN_HEIGHT/3) - (Constants.SCREEN_HEIGHT/26));
        canvas.drawText(text, x, y, paint);
    }

    private void drawCentreText4(Canvas canvas, Paint paint, String text) {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.getTextBounds(text, 0, text.length(), r);
        float x = (cWidth / 2f - r.width() / 2f - r.left) + ((Constants.SCREEN_WIDTH/4) + Constants.SCREEN_WIDTH/10);
        float y = (cHeight / 2f + r.height() / 2f - r.bottom) - ((Constants.SCREEN_HEIGHT/2) - Constants.SCREEN_WIDTH/14);
        canvas.drawText(text, x, y, paint);
    }
    private void drawCentreText5(Canvas canvas, Paint paint, String text) {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.getTextBounds(text, 0, text.length(), r);
        float x = (cWidth / 2f - r.width() / 2f - r.left) + ((Constants.SCREEN_WIDTH/4) + (Constants.SCREEN_WIDTH/7));
        float y = (cHeight / 2f + r.height() / 2f - r.bottom) - ((Constants.SCREEN_HEIGHT/2 - Constants.SCREEN_WIDTH/6));
        canvas.drawText(text, x, y, paint);
    }



}
