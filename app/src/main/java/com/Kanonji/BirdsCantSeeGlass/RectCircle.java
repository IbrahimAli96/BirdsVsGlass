package com.Kanonji.BirdsCantSeeGlass;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by Ibrahim on 18/06/2017.
 */

public class RectCircle implements GameObject {

    private Rect circle;
    private int color;

    private Animation idle;
    private Animation walkRight;
    private Animation walkLeft;

    private AnimationManager animManager;


    public Rect getCircle() {

        return circle;
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


    public RectCircle(Rect circle, int color) {
        this.circle = circle;
        this.color = color;

        BitmapFactory bf = new BitmapFactory();
        //finger
        Bitmap idleImg = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.finger0, 100, 100);
        Bitmap idleImg2 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.finger2, 100, 100);
        Bitmap idleImg3 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.finger3, 100, 100);
        Bitmap idleImg4 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.finger4, 100, 100);
        Bitmap idleImg5 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.finger5, 100, 100);
        Bitmap idleImg6 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.finger0, 100, 100);

        idle = new Animation(new Bitmap[]{idleImg, idleImg2, idleImg3, idleImg4, idleImg5}, 1f);
        walkRight = new Animation(new Bitmap[]{idleImg6}, 1f);
        walkLeft = new Animation(new Bitmap[]{idleImg6}, 1f);

        animManager = new AnimationManager(new Animation[]{idle, walkRight, walkLeft});

    }

    @Override
    public void draw(Canvas canvas) {
        //Paint paint = new Paint();
        //paint.setColor(color);
        //canvas.drawRect(circle, paint);

        if(SceneManager.ACTIVE_SCENE == 0 || SceneManager.ACTIVE_SCENE == 11) {
            animManager.draw(canvas, circle);
        }

    }

    @Override
    public void update() {

        if(SceneManager.ACTIVE_SCENE == 0 || SceneManager.ACTIVE_SCENE == 11) {
            animManager.update();
        }


    }

    public void update(Point circlePoint) {
        float oldLeft = circle.left;

        circle.set(circlePoint.x - circle.width()/2, circlePoint.y - circle.height()/2, circlePoint.x + circle.width()/2, circlePoint.y + circle.height()/2 );

        int state = 0;
        if(circle.left - oldLeft > 5)
            state = 1;
        else if(circle.left - oldLeft < -5)
            state = 2;

        if(SceneManager.ACTIVE_SCENE == 0 || SceneManager.ACTIVE_SCENE == 11) {
            animManager.playAnim(state);
            animManager.update();
        }

    }
}
