package com.Kanonji.BirdsCantSeeGlass;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Handler;
import android.view.MotionEvent;

/**
 * Created by Ibrahim on 28/01/2018.
 */

public class SplashScreen implements Scene{

    public SplashScreen() {

        new Handler().postDelayed(new Runnable() {

            @Override

            public void run() {

                SceneManager.ACTIVE_SCENE = 13;
                MainActivity.dizzyStart.start();

            }

        }, 3*1000);
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

    Bitmap bitmap1 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.tkf2, 500, 500);

    @Override
    public void draw(Canvas canvas) {

        Paint paint = new Paint();

        Typeface myTypeface = Typeface.createFromAsset(Constants.CURRENT_CONTEXT.getAssets(), "chunk.ttf");

        canvas.drawColor(Color.rgb(255, 255, 255));

        Bitmap resizedBitmap = bitmap1.createScaledBitmap(bitmap1, Constants.SCREEN_WIDTH, Constants.SCREEN_WIDTH, false);
        int rh1 = resizedBitmap.getHeight();
        int rw1 = resizedBitmap.getWidth();
        canvas.drawBitmap(resizedBitmap, ((Constants.SCREEN_WIDTH / 2 - (rw1/2))), (((Constants.SCREEN_HEIGHT/2 - (rh1/2)))), new Paint());

    }

    @Override
    public void terminate() {
        SceneManager.ACTIVE_SCENE = 14;
    }

    @Override
    public boolean recieveTouch(MotionEvent event) {
        return false;
    }
}
