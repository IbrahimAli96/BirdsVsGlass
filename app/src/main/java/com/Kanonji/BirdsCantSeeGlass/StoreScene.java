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


/**
 * Created by Ibrahim on 10/07/2017.
 */

public class StoreScene implements Scene {

    private Rect r = new Rect();

    private int birdInfo = 0;

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

    Bitmap bitmap1 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.andy, 200, 200);
    Bitmap resizedbitmap1 = Bitmap.createScaledBitmap(bitmap1, Constants.SCREEN_WIDTH, Constants.SCREEN_WIDTH/4, true);

    Bitmap bitmap2 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.frank, 200, 200);
    Bitmap resizedbitmap2 = Bitmap.createScaledBitmap(bitmap2, Constants.SCREEN_WIDTH, Constants.SCREEN_WIDTH/4, true);

    Bitmap bitmap2buy = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.frankbuy, 200, 200);
    Bitmap resizedbitmap2buy = Bitmap.createScaledBitmap(bitmap2buy, Constants.SCREEN_WIDTH, Constants.SCREEN_WIDTH/4, true);

    Bitmap bitmap3 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.boulda, 200, 200);
    Bitmap resizedbitmap3 = Bitmap.createScaledBitmap(bitmap3, Constants.SCREEN_WIDTH, Constants.SCREEN_WIDTH/4, true);

    Bitmap bitmap3buy = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.bouldabuy, 200, 200);
    Bitmap resizedbitmap3buy = Bitmap.createScaledBitmap(bitmap3buy, Constants.SCREEN_WIDTH, Constants.SCREEN_WIDTH/4, true);

    Bitmap bitmap4 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.feather, 200, 200);
    Bitmap resizedbitmap4 = Bitmap.createScaledBitmap(bitmap4, Constants.SCREEN_WIDTH, Constants.SCREEN_WIDTH/4, true);

    Bitmap bitmap4buy = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.featherbuy, 200, 200);
    Bitmap resizedbitmap4buy = Bitmap.createScaledBitmap(bitmap4buy, Constants.SCREEN_WIDTH, Constants.SCREEN_WIDTH/4, true);

    Bitmap bitmapinfo = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.infobird, 100, 100);
    Bitmap resizedinfo = Bitmap.createScaledBitmap(bitmapinfo, Constants.SCREEN_WIDTH/6, Constants.SCREEN_WIDTH/6, true);
    int hinfo = resizedinfo.getHeight();
    int winfo = resizedinfo.getWidth();

    Bitmap bitmapbuy = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.buybird, 100, 100);
    Bitmap resizedbuy = Bitmap.createScaledBitmap(bitmapbuy, Constants.SCREEN_WIDTH/6, Constants.SCREEN_WIDTH/6, true);
    int hbuy = resizedbuy.getHeight();
    int wbuy = resizedbuy.getWidth();

    Bitmap bitmapselect = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.select, 100, 100);
    Bitmap resizedselect = Bitmap.createScaledBitmap(bitmapselect, Constants.SCREEN_WIDTH/6, Constants.SCREEN_WIDTH/6, true);
    int hselect = resizedselect.getHeight();
    int wselect = resizedselect.getWidth();

    Bitmap bitmaporange = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.orange, 100, 100);
    Bitmap resizedorange = Bitmap.createScaledBitmap(bitmaporange, Constants.SCREEN_WIDTH/6, Constants.SCREEN_WIDTH/6, true);
    int horange = resizedorange.getHeight();
    int worange = resizedorange.getWidth();

    Bitmap bitmap14 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.prev1, 100, 100);
    Bitmap resizedbitmap14 = Bitmap.createScaledBitmap(bitmap14, Constants.SCREEN_WIDTH/6, Constants.SCREEN_WIDTH/6, true);

    Bitmap bitmap15 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.next1, 100, 100);
    Bitmap resizedbitmap15 = Bitmap.createScaledBitmap(bitmap15, Constants.SCREEN_WIDTH/6, Constants.SCREEN_WIDTH/6, true);

    Bitmap bitmap16 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.menu1, 100, 100);
    Bitmap resizedbitmap16 = Bitmap.createScaledBitmap(bitmap16, Constants.SCREEN_WIDTH/6, Constants.SCREEN_WIDTH/6, true);

    Bitmap bitmapnest = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.birdnest1, 200, 200);
    Bitmap resizedbitmapnest = Bitmap.createScaledBitmap(bitmapnest, Constants.SCREEN_WIDTH - Constants.SCREEN_WIDTH/4, Constants.SCREEN_WIDTH/5, true);

    Bitmap bitmapTitle = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.shop, 100, 100);
    Bitmap resizedbitmapTitle = Bitmap.createScaledBitmap(bitmapTitle, Constants.SCREEN_WIDTH/3, Constants.SCREEN_WIDTH/8, true);

    Bitmap bitmap111 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.sp2, 30, 30);

    Bitmap bitmapSP = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.survival, 30, 30);


    @Override
    public void draw(Canvas canvas) {

        Paint paint = new Paint();

        Typeface myTypeface = Typeface.createFromAsset(Constants.CURRENT_CONTEXT.getAssets(), "chunk.ttf");

        canvas.drawColor(Color.rgb(0, 255, 255));

        //yello bird
        canvas.drawBitmap(resizedbitmap1, 0, 0 + ((Constants.SCREEN_HEIGHT / 5)), new Paint());


        canvas.drawBitmap(resizedinfo, ((Constants.SCREEN_WIDTH - winfo*2) - winfo/3), (0 + ((Constants.SCREEN_HEIGHT / 5)) + winfo/3), new Paint());

        if (GamePanel.YelowBird >= 2 || GamePanel.RedBird == 5) {
            canvas.drawBitmap(resizedorange, ((Constants.SCREEN_WIDTH - worange) - worange / 6), (0 + ((Constants.SCREEN_HEIGHT / 5)) + worange / 3), new Paint());
        } else {
            canvas.drawBitmap(resizedselect, ((Constants.SCREEN_WIDTH - wselect) - wselect / 6), (0 + ((Constants.SCREEN_HEIGHT / 5)) + wselect / 3), new Paint());
        }

        //redbird
        if (GamePanel.RedBird == 0) {
            canvas.drawBitmap(resizedbitmap2, 0, 0 + ((Constants.SCREEN_HEIGHT / 3)) + Constants.SCREEN_HEIGHT / 40, new Paint());
        } else {
            canvas.drawBitmap(resizedbitmap2buy, 0, 0 + ((Constants.SCREEN_HEIGHT / 3)) + Constants.SCREEN_HEIGHT / 40, new Paint());
        }

        canvas.drawBitmap(resizedinfo, ((Constants.SCREEN_WIDTH - winfo*2) - winfo/3), (0 + ((Constants.SCREEN_HEIGHT / 3)) + Constants.SCREEN_HEIGHT/40 + winfo/3), new Paint());

        if (GamePanel.RedBird == 0) {
            canvas.drawBitmap(resizedbuy, ((Constants.SCREEN_WIDTH - worange) - worange / 6), (0 + ((Constants.SCREEN_HEIGHT / 3)) + Constants.SCREEN_HEIGHT/40 + winfo/3), new Paint());
        } else if (GamePanel.RedBird == 2 || GamePanel.RedBird == 3 || GamePanel.RedBird == 4) {
            canvas.drawBitmap(resizedorange, ((Constants.SCREEN_WIDTH - worange) - worange / 6), (0 + ((Constants.SCREEN_HEIGHT / 3)) + Constants.SCREEN_HEIGHT/40 + winfo/3), new Paint());
        } else if (GamePanel.RedBird == 1) {
            canvas.drawBitmap(resizedselect, ((Constants.SCREEN_WIDTH - wselect) - wselect / 6), (0 + ((Constants.SCREEN_HEIGHT / 3)) + Constants.SCREEN_HEIGHT/40 + winfo/3), new Paint());
        }

        //blackbird
        if (GamePanel.BlackBird == 0) {
            canvas.drawBitmap(resizedbitmap3, 0, 0 + ((Constants.SCREEN_HEIGHT / 2)) + Constants.SCREEN_HEIGHT/60, new Paint());
        } else {
            canvas.drawBitmap(resizedbitmap3buy, 0, 0 + ((Constants.SCREEN_HEIGHT / 2)) + Constants.SCREEN_HEIGHT/60, new Paint());
        }

        canvas.drawBitmap(resizedinfo, ((Constants.SCREEN_WIDTH - winfo*2) - winfo/3), (0 + ((Constants.SCREEN_HEIGHT / 2)) + Constants.SCREEN_HEIGHT/60 + winfo/3), new Paint());

        if (GamePanel.BlackBird == 0) {
            canvas.drawBitmap(resizedbuy, ((Constants.SCREEN_WIDTH - worange) - worange / 6), (0 + ((Constants.SCREEN_HEIGHT / 2)) + Constants.SCREEN_HEIGHT/60 + winfo/3), new Paint());
        } else if (GamePanel.BlackBird >= 2) {
            canvas.drawBitmap(resizedorange, ((Constants.SCREEN_WIDTH - worange) - worange / 6), (0 + ((Constants.SCREEN_HEIGHT / 2)) + Constants.SCREEN_HEIGHT/60 + winfo/3), new Paint());
        } else if (GamePanel.BlackBird == 1) {
            canvas.drawBitmap(resizedselect, ((Constants.SCREEN_WIDTH - wselect) - wselect / 6), (0 + ((Constants.SCREEN_HEIGHT / 2)) + Constants.SCREEN_HEIGHT/60 + winfo / 3), new Paint());
        }


        //pinkbird
        if (GamePanel.PinkBird == 0) {
            canvas.drawBitmap(resizedbitmap4, 0, (0 + ((Constants.SCREEN_HEIGHT)) - Constants.SCREEN_HEIGHT/3) + Constants.SCREEN_HEIGHT/100, new Paint());
        } else {
            canvas.drawBitmap(resizedbitmap4buy, 0, (0 + ((Constants.SCREEN_HEIGHT)) - Constants.SCREEN_HEIGHT/3) + Constants.SCREEN_HEIGHT/100, new Paint());
        }

        canvas.drawBitmap(resizedinfo, ((Constants.SCREEN_WIDTH - winfo*2) - winfo/3), ((0 + ((Constants.SCREEN_HEIGHT)) - Constants.SCREEN_HEIGHT/3) + Constants.SCREEN_HEIGHT/100 + winfo/3), new Paint());

        if (GamePanel.PinkBird == 0) {
            canvas.drawBitmap(resizedbuy, ((Constants.SCREEN_WIDTH - worange) - worange / 6), ((0 + ((Constants.SCREEN_HEIGHT)) - Constants.SCREEN_HEIGHT/3) + Constants.SCREEN_HEIGHT/100 + winfo/3), new Paint());
        } else if (GamePanel.PinkBird >= 2) {
            canvas.drawBitmap(resizedorange, ((Constants.SCREEN_WIDTH - worange) - worange / 6), ((0 + ((Constants.SCREEN_HEIGHT)) - Constants.SCREEN_HEIGHT/3) + Constants.SCREEN_HEIGHT/100 + winfo/3), new Paint());
        } else if (GamePanel.PinkBird == 1) {
            canvas.drawBitmap(resizedselect, ((Constants.SCREEN_WIDTH - wselect) - wselect / 6), ((0 + ((Constants.SCREEN_HEIGHT)) - Constants.SCREEN_HEIGHT/3) + Constants.SCREEN_HEIGHT/100 + winfo / 3), new Paint());
        }

        //canvas.drawBitmap(resizedbuy, ((Constants.SCREEN_WIDTH - wbuy) - wbuy/6), (0 + ((Constants.SCREEN_HEIGHT / 5)) + wbuy/3), new Paint());


        //birdnest on top
        int hnest = resizedbitmapnest.getHeight();
        int wnest = resizedbitmapnest.getWidth();
        canvas.drawBitmap(resizedbitmapnest, ((Constants.SCREEN_WIDTH / 4)-( wnest / 2)), (((Constants.SCREEN_HEIGHT/8) - (hnest / 1))), new Paint());


        //Menu and Back button and title
        //int h14 = resizedbitmap14.getHeight();
        //int w14 = resizedbitmap14.getWidth();
        //canvas.drawBitmap(resizedbitmap14, (((Constants.SCREEN_WIDTH / 4) - (w14 / 2))), (((Constants.SCREEN_HEIGHT / 2) - (h14 / -1)) + (Constants.SCREEN_HEIGHT/4)), new Paint());

        int h15 = resizedbitmap15.getHeight();
        int w15 = resizedbitmap15.getWidth();
        canvas.drawBitmap(resizedbitmap15, ((((Constants.SCREEN_WIDTH / 4) + Constants.SCREEN_WIDTH / 2) - (w15 / 2))), (((Constants.SCREEN_HEIGHT / 2) - (h15 / -1))+ (Constants.SCREEN_HEIGHT/4)), new Paint());

        int h16 = resizedbitmap16.getHeight();
        int w16 = resizedbitmap16.getWidth();
        canvas.drawBitmap(resizedbitmap16, (Constants.SCREEN_WIDTH / 2) - (w16 / 2), (((Constants.SCREEN_HEIGHT / 2) - (h16 / -1))+ (Constants.SCREEN_HEIGHT/4)), new Paint());

        int wTitle = resizedbitmapTitle.getWidth();
        canvas.drawBitmap(resizedbitmapTitle, Constants.SCREEN_WIDTH/2 - wTitle/2, 0 + Constants.SCREEN_HEIGHT/9, new Paint());

        // Survival points
        Bitmap resizedbitmap111 = Bitmap.createScaledBitmap(bitmap111, Constants.SCREEN_WIDTH/10, Constants.SCREEN_WIDTH/10, true);
        int h111 = resizedbitmap111.getHeight();
        int w111 = resizedbitmap111.getWidth();
        canvas.drawBitmap(resizedbitmap111, ((Constants.SCREEN_WIDTH / 4)-( w111 / 2) + (Constants.SCREEN_WIDTH/2)), (((Constants.SCREEN_HEIGHT / 2) - (h111 / 2) - Constants.SCREEN_WIDTH/7) - (Constants.SCREEN_HEIGHT/3)), new Paint());

        Bitmap resizedbitmapSP = Bitmap.createScaledBitmap(bitmapSP, Constants.SCREEN_WIDTH/4 + w111/2, Constants.SCREEN_WIDTH/24, true);
        canvas.drawBitmap(resizedbitmapSP, ((Constants.SCREEN_WIDTH / 4) + (Constants.SCREEN_WIDTH/2)) - w111/2, (((Constants.SCREEN_HEIGHT / 2) - (h111) - Constants.SCREEN_WIDTH/7) - (Constants.SCREEN_HEIGHT/3)), new Paint());

        paint.setTextSize(Constants.SCREEN_WIDTH / 20);
        paint.setTypeface(myTypeface);
        paint.setColor(Color.BLACK);
        drawCentreText5(canvas, paint, "" + GamePanel.getHighCoin());

        if (birdInfo == 1) {
            Bitmap bitmapPanel = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.andypanel, 400, 400);
            Bitmap resizedbitmapPanel = Bitmap.createScaledBitmap(bitmapPanel, Constants.SCREEN_WIDTH - Constants.SCREEN_WIDTH/4, Constants.SCREEN_HEIGHT - Constants.SCREEN_HEIGHT/3, true);
            int hPanel = resizedbitmapPanel.getHeight();
            int wPanel = resizedbitmapPanel.getWidth();
            canvas.drawBitmap(resizedbitmapPanel, Constants.SCREEN_WIDTH/2 - wPanel/2, Constants.SCREEN_HEIGHT/2 - hPanel/2, new Paint());
        }
        if (birdInfo == 2) {
            Bitmap bitmapPanel = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.frankpanel, 400, 400);
            Bitmap resizedbitmapPanel = Bitmap.createScaledBitmap(bitmapPanel, Constants.SCREEN_WIDTH - Constants.SCREEN_WIDTH/4, Constants.SCREEN_HEIGHT - Constants.SCREEN_HEIGHT/3, true);
            int hPanel = resizedbitmapPanel.getHeight();
            int wPanel = resizedbitmapPanel.getWidth();
            canvas.drawBitmap(resizedbitmapPanel, Constants.SCREEN_WIDTH/2 - wPanel/2, Constants.SCREEN_HEIGHT/2 - hPanel/2, new Paint());
        }
        if (birdInfo == 3) {
            Bitmap bitmapPanel = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.bouldapanel, 400, 400);
            Bitmap resizedbitmapPanel = Bitmap.createScaledBitmap(bitmapPanel, Constants.SCREEN_WIDTH - Constants.SCREEN_WIDTH/4, Constants.SCREEN_HEIGHT - Constants.SCREEN_HEIGHT/3, true);
            int hPanel = resizedbitmapPanel.getHeight();
            int wPanel = resizedbitmapPanel.getWidth();
            canvas.drawBitmap(resizedbitmapPanel, Constants.SCREEN_WIDTH/2 - wPanel/2, Constants.SCREEN_HEIGHT/2 - hPanel/2, new Paint());
        }
        if (birdInfo == 4) {
            Bitmap bitmapPanel = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.featherpanel, 400, 400);
            Bitmap resizedbitmapPanel = Bitmap.createScaledBitmap(bitmapPanel, Constants.SCREEN_WIDTH - Constants.SCREEN_WIDTH/4, Constants.SCREEN_HEIGHT - Constants.SCREEN_HEIGHT/3, true);
            int hPanel = resizedbitmapPanel.getHeight();
            int wPanel = resizedbitmapPanel.getWidth();
            canvas.drawBitmap(resizedbitmapPanel, Constants.SCREEN_WIDTH/2 - wPanel/2, Constants.SCREEN_HEIGHT/2 - hPanel/2, new Paint());
        }


    }

    @Override
    public void terminate() {
        SceneManager.ACTIVE_SCENE = 1;


    }

    private static WeakReference<Activity> mActivityRef;
    public static void updateActivity(Activity activity) {
        mActivityRef = new WeakReference<Activity>(activity);
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

        int hinfo = resizedinfo.getHeight();
        int winfo = resizedinfo.getWidth();

        float xRatioinfo1 = ((Constants.SCREEN_WIDTH - winfo*2) - winfo/3);
        float yRatioinfo1 = (0 + ((Constants.SCREEN_HEIGHT / 5)) + hinfo/3);

        float xRatiobuy1 = ((Constants.SCREEN_WIDTH - worange) - worange / 6);
        float yRatiobuy1 = (0 + ((Constants.SCREEN_HEIGHT / 5)) + worange / 3);

        float xRatioinfo2 = ((Constants.SCREEN_WIDTH - winfo*2) - winfo/3);
        float yRatioinfo2 = (0 + ((Constants.SCREEN_HEIGHT / 3)) + Constants.SCREEN_HEIGHT/40 + winfo/3);

        float xRatiobuy2 = ((Constants.SCREEN_WIDTH - worange) - worange / 6);
        float yRatiobuy2 = (0 + ((Constants.SCREEN_HEIGHT / 3)) + Constants.SCREEN_HEIGHT/40 + winfo/3);

        float xRatioinfo3 = ((Constants.SCREEN_WIDTH - winfo*2) - winfo/3);
        float yRatioinfo3 = (0 + ((Constants.SCREEN_HEIGHT / 2)) + Constants.SCREEN_HEIGHT/60 + winfo/3);

        float xRatiobuy3 = ((Constants.SCREEN_WIDTH - worange) - worange / 6);
        float yRatiobuy3 = (0 + ((Constants.SCREEN_HEIGHT / 2)) + Constants.SCREEN_HEIGHT/60 + winfo/3);

        float xRatioinfo4 = ((Constants.SCREEN_WIDTH - winfo*2) - winfo/3);
        float yRatioinfo4 = ((0 + ((Constants.SCREEN_HEIGHT)) - Constants.SCREEN_HEIGHT/3) + Constants.SCREEN_HEIGHT/100 + winfo/3);

        float xRatiobuy4 = ((Constants.SCREEN_WIDTH - worange) - worange / 6);
        float yRatiobuy4 = ((0 + ((Constants.SCREEN_HEIGHT)) - Constants.SCREEN_HEIGHT/3) + Constants.SCREEN_HEIGHT/100 + winfo/3);

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (birdInfo == 0) {
                    if (x >= xRatio14 && x < (xRatio14 + resizedbitmap14.getWidth())
                            && y >= yRatio14 && y < (yRatio14 + resizedbitmap14.getHeight())) {

                    }
                    if (x >= xRatio15 && x < (xRatio15 + resizedbitmap15.getWidth())
                            && y >= yRatio15 && y < (yRatio15 + resizedbitmap15.getHeight())) {
                        MainActivity.click.start();
                        SceneManager.ACTIVE_SCENE = 2;
                    }
                    if (x >= xRatio16 && x < (xRatio16 + resizedbitmap16.getWidth())
                            && y >= yRatio16 && y < (yRatio16 + resizedbitmap16.getHeight())) {
                        MainActivity.click.start();
                        SceneManager.ACTIVE_SCENE = 4;
                        MainActivity.mInterstitialAd.loadAd((new AdRequest.Builder().build()));
                        MainActivity.mInterstitialAd.show();
                    }
                //yellow bird info on
                if (x >= xRatioinfo1 && x < (xRatioinfo1 + resizedinfo.getWidth())
                        && y >= yRatioinfo1 && y < (yRatioinfo1 + resizedinfo.getHeight())) {
                    MainActivity.click.start();
                    birdInfo = 1;
                }
                //equip yellow bird
                if (GamePanel.YelowBird == 1) {
                    if (x >= xRatiobuy1 && x < (xRatiobuy1 + resizedinfo.getWidth())
                            && y >= yRatiobuy1 && y < (yRatiobuy1 + resizedinfo.getHeight())) {
                        GamePanel.YelowBird = 2;
                        MainActivity.click.start();
                        if (GamePanel.RedBird >= 2) {
                            GamePanel.RedBird = 1;
                        }
                        if (GamePanel.BlackBird >= 2) {
                            GamePanel.BlackBird = 1;
                        }
                        if (GamePanel.PinkBird >= 2) {
                            GamePanel.PinkBird = 1;
                        }
                        if (GamePanel.RainbowBird >= 2) {
                            GamePanel.RainbowBird = 1;
                        }
                        if (GamePanel.DragonBird >= 2) {
                            GamePanel.DragonBird = 1;
                        }
                        if (GamePanel.NinjaBird >= 2) {
                            GamePanel.NinjaBird = 1;
                        }
                        if (GamePanel.StrongBird >= 2) {
                            GamePanel.StrongBird = 1;
                        }
                        if (GamePanel.NitroBird >= 2) {
                            GamePanel.NitroBird = 1;
                        }
                        if (GamePanel.LaserBird >= 2) {
                            GamePanel.LaserBird = 1;
                        }
                        if (GamePanel.SageBird >= 2) {
                            GamePanel.SageBird = 1;
                        }
                        if (GamePanel.BombBird >= 2) {
                            GamePanel.BombBird = 1;
                        }
                    }}

                    //red bird info on
                    if (x >= xRatioinfo2 && x < (xRatioinfo2 + resizedinfo.getWidth())
                            && y >= yRatioinfo2 && y < (yRatioinfo2 + resizedinfo.getHeight())) {
                        MainActivity.click.start();
                        birdInfo = 2;
                    }
                    //red bird buy and equip
                    if (x >= xRatiobuy2 && x < (xRatiobuy2 + resizedinfo.getWidth())
                            && y >= yRatiobuy2 && y < (yRatiobuy2 + resizedinfo.getHeight())) {

                        if (GamePanel.RedBird == 0 && GamePanel.HighCoin >= 500) {
                            MainActivity.cash.start();
                            Toast.makeText(mActivityRef.get(), "Congrats You Just Bought FRANK", Toast.LENGTH_SHORT).show();
                            GamePanel.HighCoin = GamePanel.HighCoin - 500;
                            GamePanel.RedBird = 1;
                            GamePanel.Task1 = 1;
                        } else if (GamePanel.RedBird == 0 && GamePanel.HighCoin < 500) {
                            MainActivity.click.start();
                            Toast.makeText(mActivityRef.get(), "Not Enough Survival Points", Toast.LENGTH_SHORT).show();
                        } else if (GamePanel.RedBird == 1) {
                            MainActivity.click.start();
                            GamePanel.RedBird = 2;
                            if (GamePanel.YelowBird >= 2) {
                                GamePanel.YelowBird = 1;
                            }
                            if (GamePanel.BlackBird >= 2) {
                                GamePanel.BlackBird = 1;
                            }
                            if (GamePanel.PinkBird >= 2) {
                                GamePanel.PinkBird = 1;
                            }
                            if (GamePanel.RainbowBird >= 2) {
                                GamePanel.RainbowBird = 1;
                            }
                            if (GamePanel.DragonBird >= 2) {
                                GamePanel.DragonBird = 1;
                            }
                            if (GamePanel.NinjaBird >= 2) {
                                GamePanel.NinjaBird = 1;
                            }
                            if (GamePanel.StrongBird >= 2) {
                                GamePanel.StrongBird = 1;
                            }
                            if (GamePanel.NitroBird >= 2) {
                                GamePanel.NitroBird = 1;
                            }
                            if (GamePanel.LaserBird >= 2) {
                                GamePanel.LaserBird = 1;
                            }
                            if (GamePanel.SageBird >= 2) {
                                GamePanel.SageBird = 1;
                            }
                            if (GamePanel.BombBird >= 2) {
                                GamePanel.BombBird = 1;
                            }
                        }}

                    //black bird info on
                    if (x >= xRatioinfo3 && x < (xRatioinfo3 + resizedinfo.getWidth())
                            && y >= yRatioinfo3 && y < (yRatioinfo3 + resizedinfo.getHeight())) {
                        MainActivity.click.start();
                        birdInfo = 3;
                    }
                    //black bird buy and equip
                    if (x >= xRatiobuy3 && x < (xRatiobuy3 + resizedinfo.getWidth())
                            && y >= yRatiobuy3 && y < (yRatiobuy3 + resizedinfo.getHeight())) {

                        if (GamePanel.BlackBird == 0 && GamePanel.HighCoin >= 1000) {
                            MainActivity.cash.start();
                            Toast.makeText(mActivityRef.get(), "Congrats You Just Bought Boulda", Toast.LENGTH_SHORT).show();
                            GamePanel.HighCoin = GamePanel.HighCoin - 1000;
                            GamePanel.BlackBird = 1;
                        } else if (GamePanel.BlackBird == 0 && GamePanel.HighCoin < 1000) {
                            MainActivity.click.start();
                            Toast.makeText(mActivityRef.get(), "Not Enough Survival Points", Toast.LENGTH_SHORT).show();
                        } else if (GamePanel.BlackBird == 1) {
                            MainActivity.click.start();
                            GamePanel.BlackBird = 2;
                            if (GamePanel.YelowBird >= 2) {
                                GamePanel.YelowBird = 1;
                            }
                            if (GamePanel.RedBird >= 2) {
                                GamePanel.RedBird = 1;
                            }
                            if (GamePanel.PinkBird >= 2) {
                                GamePanel.PinkBird = 1;
                            }
                            if (GamePanel.RainbowBird >= 2) {
                                GamePanel.RainbowBird = 1;
                            }
                            if (GamePanel.DragonBird >= 2) {
                                GamePanel.DragonBird = 1;
                            }
                            if (GamePanel.NinjaBird >= 2) {
                                GamePanel.NinjaBird = 1;
                            }
                            if (GamePanel.StrongBird >= 2) {
                                GamePanel.StrongBird = 1;
                            }
                            if (GamePanel.NitroBird >= 2) {
                                GamePanel.NitroBird = 1;
                            }
                            if (GamePanel.LaserBird >= 2) {
                                GamePanel.LaserBird = 1;
                            }
                            if (GamePanel.SageBird >= 2) {
                                GamePanel.SageBird = 1;
                            }
                            if (GamePanel.BombBird >= 2) {
                                GamePanel.BombBird = 1;
                            }
                        }}
                    //black bird info on
                    if (x >= xRatioinfo4 && x < (xRatioinfo4 + resizedinfo.getWidth())
                            && y >= yRatioinfo4 && y < (yRatioinfo4 + resizedinfo.getHeight())) {
                        MainActivity.click.start();
                        birdInfo = 4;
                    }
                    //black bird buy and equip
                    if (x >= xRatiobuy4 && x < (xRatiobuy4 + resizedinfo.getWidth())
                            && y >= yRatiobuy4 && y < (yRatiobuy4 + resizedinfo.getHeight())) {

                        if (GamePanel.PinkBird == 0 && GamePanel.HighCoin >= 1000) {
                            MainActivity.cash.start();
                            Toast.makeText(mActivityRef.get(), "Congrats You Just Bought Feather", Toast.LENGTH_SHORT).show();
                            GamePanel.HighCoin = GamePanel.HighCoin - 1000;
                            GamePanel.PinkBird = 1;
                        } else if (GamePanel.PinkBird == 0 && GamePanel.HighCoin < 1000) {
                            MainActivity.click.start();
                            Toast.makeText(mActivityRef.get(), "Not Enough Survival Points", Toast.LENGTH_SHORT).show();
                        } else if (GamePanel.PinkBird == 1) {
                            MainActivity.click.start();
                            GamePanel.PinkBird = 2;
                            if (GamePanel.YelowBird >= 2) {
                                GamePanel.YelowBird = 1;
                            }
                            if (GamePanel.RedBird >= 2) {
                                GamePanel.RedBird = 1;
                            }
                            if (GamePanel.BlackBird >= 2) {
                                GamePanel.BlackBird = 1;
                            }
                            if (GamePanel.RainbowBird >= 2) {
                                GamePanel.RainbowBird = 1;
                            }
                            if (GamePanel.DragonBird >= 2) {
                                GamePanel.DragonBird = 1;
                            }
                            if (GamePanel.NinjaBird >= 2) {
                                GamePanel.NinjaBird = 1;
                            }
                            if (GamePanel.StrongBird >= 2) {
                                GamePanel.StrongBird = 1;
                            }
                            if (GamePanel.NitroBird >= 2) {
                                GamePanel.NitroBird = 1;
                            }
                            if (GamePanel.LaserBird >= 2) {
                                GamePanel.LaserBird = 1;
                            }
                            if (GamePanel.SageBird >= 2) {
                                GamePanel.SageBird = 1;
                            }
                            if (GamePanel.BombBird >= 2) {
                                GamePanel.BombBird = 1;
                            }
                        }}
                } else if (birdInfo >= 1) {
                    MainActivity.click.start();
                    birdInfo = 0;
                }
        }

        return false;
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