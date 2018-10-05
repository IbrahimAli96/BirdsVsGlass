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

import com.google.android.gms.ads.AdRequest;

import java.lang.ref.WeakReference;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by Ibrahim on 18/07/2017.
 */

public class ScoreScene implements Scene {

    private Rect r = new Rect();

    boolean strong = false;

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

    Bitmap bitmap100 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.back3, 300, 300);

    Bitmap bitmap14 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.menu1, 100, 100);
    Bitmap resizedbitmap14 = Bitmap.createScaledBitmap(bitmap14, Constants.SCREEN_WIDTH/6, Constants.SCREEN_WIDTH/6, true);

    Bitmap bitmap1 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.stomp, 250, 250);
    Bitmap resizedbitmap1 = Bitmap.createScaledBitmap(bitmap1, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT/3, true);

    Bitmap bitmap111 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.sp2, 300, 300);

    Bitmap bitmapTitle = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.highscore, 100, 100);

    Bitmap bitmapSP = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.survival, 300, 300);


    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();

        Typeface myTypeface = Typeface.createFromAsset(Constants.CURRENT_CONTEXT.getAssets(), "chunk.ttf");

        canvas.drawColor(Color.rgb(0, 255, 255));

        canvas.drawBitmap(bitmap100, null, new RectF(0, 0, (Constants.SCREEN_WIDTH), Constants.SCREEN_HEIGHT) , null);

        //Bitmap bitmap12 = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.grass);
        //int h12 = bitmap12.getHeight();
        //int w12 = bitmap12.getWidth();
        //canvas.drawBitmap(bitmap12, ((Constants.SCREEN_WIDTH / 2)-( w12 / 2)), (((Constants.SCREEN_HEIGHT) - (h12 / 1))), new Paint());

        int h14 = resizedbitmap14.getHeight();
        int w14 = resizedbitmap14.getWidth();
        canvas.drawBitmap(resizedbitmap14, (((Constants.SCREEN_WIDTH / 2) - (w14 / 2))), (((Constants.SCREEN_HEIGHT / 2) - (h14 / -1)) + (Constants.SCREEN_HEIGHT/4)), new Paint());

        int h1 = resizedbitmap1.getHeight();
        int w1 = resizedbitmap1.getWidth();
        canvas.drawBitmap(resizedbitmap1, ((Constants.SCREEN_WIDTH / 2) - (w1 / 2)), ((Constants.SCREEN_HEIGHT / 2) - (h1 / 2)) + Constants.SCREEN_HEIGHT/8, new Paint());

        Bitmap resizedbitmap111 = Bitmap.createScaledBitmap(bitmap111, Constants.SCREEN_WIDTH/10, Constants.SCREEN_WIDTH/10, true);
        int h111 = resizedbitmap111.getHeight();
        int w111 = resizedbitmap111.getWidth();
        canvas.drawBitmap(resizedbitmap111, ((Constants.SCREEN_WIDTH / 4)-( w111 / 2) + (Constants.SCREEN_WIDTH/2)), (((Constants.SCREEN_HEIGHT / 2) - (h111 / 2) - Constants.SCREEN_WIDTH/7) - (Constants.SCREEN_HEIGHT/3)), new Paint());

        paint.setTextSize(Constants.SCREEN_WIDTH / 14);
        paint.setTypeface(myTypeface);
        paint.setColor(Color.BLACK);
        //price

        if (strong == false) {
            Bitmap bitmap2 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.lifting1, 300, 300);
            Bitmap resizedbitmap2 = Bitmap.createScaledBitmap(bitmap2, Constants.SCREEN_WIDTH/2, Constants.SCREEN_WIDTH / 2, true);
            int h2 = resizedbitmap2.getHeight();
            int w2 = resizedbitmap2.getWidth();
            canvas.drawBitmap(resizedbitmap2, Constants.SCREEN_WIDTH/2 - w2/2, Constants.SCREEN_HEIGHT/2 - h2, new Paint());
        } else if (strong == true) {
            Bitmap bitmap2 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.lifting2, 300, 300);
            Bitmap resizedbitmap2 = Bitmap.createScaledBitmap(bitmap2, Constants.SCREEN_WIDTH/2, Constants.SCREEN_WIDTH / 2, true);
            int h2 = resizedbitmap2.getHeight();
            int w2 = resizedbitmap2.getWidth();
            canvas.drawBitmap(resizedbitmap2, Constants.SCREEN_WIDTH/2 - w2/2, Constants.SCREEN_HEIGHT/2 - h2, new Paint());
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

        paint.setTextSize(Constants.SCREEN_WIDTH / 20);
        paint.setTypeface(myTypeface);
        paint.setColor(Color.BLACK);
        canvas.drawText("" + GamePanel.getHighScore() , (Constants.SCREEN_WIDTH / 2) + Constants.SCREEN_WIDTH/26, (Constants.SCREEN_HEIGHT / 2) + Constants.SCREEN_HEIGHT/11, paint);

        paint.setTextSize(Constants.SCREEN_WIDTH / 20);
        paint.setTypeface(myTypeface);
        paint.setColor(Color.BLACK);
        canvas.drawText("" + GamePanel.getHighScore1() , (Constants.SCREEN_WIDTH / 2) + Constants.SCREEN_WIDTH/26, (Constants.SCREEN_HEIGHT / 2) + Constants.SCREEN_HEIGHT/7, paint);

        paint.setTextSize(Constants.SCREEN_WIDTH / 20);
        paint.setTypeface(myTypeface);
        paint.setColor(Color.BLACK);
        canvas.drawText("" + GamePanel.getHighScore2() , (Constants.SCREEN_WIDTH / 2) + Constants.SCREEN_WIDTH/26, (Constants.SCREEN_HEIGHT / 2) + Constants.SCREEN_HEIGHT/5, paint);

    }

    @Override
    public void terminate() {
        SceneManager.ACTIVE_SCENE = 6;


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
        float xRatio14 = (((Constants.SCREEN_WIDTH / 2) - (w14 / 2)));
        float yRatio14 = (((Constants.SCREEN_HEIGHT / 2) - (h14 / -1)) + (Constants.SCREEN_HEIGHT/4));

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (x >= xRatio14 && x < (xRatio14 + resizedbitmap14.getWidth())
                        && y >= yRatio14 && y < (yRatio14 + resizedbitmap14.getHeight())) {
                    MainActivity.click.start();
                    MainActivity.mInterstitialAd.loadAd((new AdRequest.Builder().build()));
                    MainActivity.mInterstitialAd.show();
                    //Appodeal.show(mActivityRef.get(), Appodeal.INTERSTITIAL);
                    SceneManager.ACTIVE_SCENE = 4;
                }

                if (strong == false) {
                    strong = true;
                } else if (strong == true){
                    strong = false;
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
