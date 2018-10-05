package com.Kanonji.BirdsCantSeeGlass;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by Ibrahim on 18/06/2017.
 */

public class RectPlayer implements GameObject {

    private Rect rectangle;
    private int color;
    private int image;

    private Animation idle;
    private Animation walkRight;
    private Animation walkLeft;
    private Animation idles;
    private Animation walkRights;
    private Animation walkLefts;
    private Animation idle1;
    private Animation walkRight1;
    private Animation walkLeft1;
    private Animation idle1s;
    private Animation walkRight1s;
    private Animation walkLeft1s;
    private Animation idle1sz;
    private Animation walkRight1sz;
    private Animation walkLeft1sz;
    private Animation idle2s;
    private Animation walkRight2s;
    private Animation walkLeft2s;
    private Animation idle2sw;
    private Animation walkRight2sw;
    private Animation walkLeft2sw;
    private Animation idle2;
    private Animation walkRight2;
    private Animation walkLeft2;
    private Animation idle3;
    private Animation walkRight3;
    private Animation walkLeft3;
    private Animation idle3s;
    private Animation walkRight3s;
    private Animation walkLeft3s;
    private Animation idle3ss;
    private Animation walkRight3ss;
    private Animation walkLeft3ss;
    private Animation idle4;
    private Animation walkRight4;
    private Animation walkLeft4;
    private Animation idle5;
    private Animation walkRight5;
    private Animation walkLeft5;
    private Animation idle5s;
    private Animation walkRight5s;
    private Animation walkLeft5s;
    private Animation idle5ss;
    private Animation walkRight5ss;
    private Animation walkLeft5ss;
    private Animation idle6;
    private Animation walkRight6;
    private Animation walkLeft6;
    private Animation idle6s;
    private Animation walkRight6s;
    private Animation walkLeft6s;
    private Animation idle6st;
    private Animation walkRight6st;
    private Animation walkLeft6st;
    private Animation idle7;
    private Animation walkRight7;
    private Animation walkLeft7;
    private Animation idle7s;
    private Animation walkRight7s;
    private Animation walkLeft7s;
    private Animation idle7ss;
    private Animation walkRight7ss;
    private Animation walkLeft7ss;
    private Animation idle8;
    private Animation walkRight8;
    private Animation walkLeft8;
    private Animation idle8s;
    private Animation walkRight8s;
    private Animation walkLeft8s;
    private Animation idle8ss;
    private Animation walkRight8ss;
    private Animation walkLeft8ss;
    private Animation idle9;
    private Animation walkRight9;
    private Animation walkLeft9;
    private Animation idle9s;
    private Animation walkRight9s;
    private Animation walkLeft9s;
    private Animation idle9ss;
    private Animation walkRight9ss;
    private Animation walkLeft9ss;
    private Animation idle10;
    private Animation walkRight10;
    private Animation walkLeft10;
    private Animation idle10s;
    private Animation walkRight10s;
    private Animation walkLeft10s;
    private Animation idle10ss;
    private Animation walkRight10ss;
    private Animation walkLeft10ss;
    private Animation idle11;
    private Animation walkRight11;
    private Animation walkLeft11;
    private Animation idle11s;
    private Animation walkRight11s;
    private Animation walkLeft11s;
    private Animation idle11ss;
    private Animation walkRight11ss;
    private Animation walkLeft11ss;
    private AnimationManager animManager;
    private AnimationManager animManagers;
    private AnimationManager animManager1;
    private AnimationManager animManager1s;
    private AnimationManager animManager1sz;
    private AnimationManager animManager2;
    private AnimationManager animManager2s;
    private AnimationManager animManager2sw;
    private AnimationManager animManager3;
    private AnimationManager animManager3s;
    private AnimationManager animManager3ss;
    private AnimationManager animManager4;
    private AnimationManager animManager5;
    private AnimationManager animManager5s;
    private AnimationManager animManager5ss;
    private AnimationManager animManager6;
    private AnimationManager animManager6s;
    private AnimationManager animManager6st;
    private AnimationManager animManager7;
    private AnimationManager animManager7s;
    private AnimationManager animManager7ss;
    private AnimationManager animManager8;
    private AnimationManager animManager8s;
    private AnimationManager animManager8ss;
    private AnimationManager animManager9;
    private AnimationManager animManager9s;
    private AnimationManager animManager9ss;
    private AnimationManager animManager10;
    private AnimationManager animManager10s;
    private AnimationManager animManager10ss;
    private AnimationManager animManager11;
    private AnimationManager animManager11s;
    private AnimationManager animManager11ss;

    public Rect getRectangle() {

        return rectangle;
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

    public RectPlayer(Rect rectangle, int color) {
        this.rectangle = rectangle;
        this.color = color;

        BitmapFactory bf = new BitmapFactory();
        //yellow
        Bitmap idleImg = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.bird11, 100, 100);
        Bitmap idleImg2 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.bird12, 100, 100);
        Bitmap idleImg3 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.bird13, 100, 100);
        //Bitmap walk1 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.birdright, 100, 100);
        //Bitmap walk2 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.birdleft, 100, 100);

        idle = new Animation(new Bitmap[]{idleImg, idleImg2, idleImg3}, 0.2f);
        walkRight = new Animation(new Bitmap[]{idleImg, idleImg2, idleImg3}, 0.2f);
        walkLeft = new Animation(new Bitmap[]{idleImg, idleImg2, idleImg3}, 0.2f);

        animManager = new AnimationManager(new Animation[]{idle, walkRight, walkLeft});

        Bitmap idleImgs = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.birdred3, 100, 100);
        Bitmap idleImg2s = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.birdred2, 100, 100);
        Bitmap idleImg3s = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.birdred1, 100, 100);

        idles = new Animation(new Bitmap[]{idleImgs, idleImg2s, idleImg3s}, 0.2f);
        walkRights = new Animation(new Bitmap[]{idleImgs, idleImg2s, idleImg3s}, 0.2f);
        walkLefts = new Animation(new Bitmap[]{idleImgs, idleImg2s, idleImg3s}, 0.2f);

        animManagers = new AnimationManager(new Animation[]{idles, walkRights, walkLefts});

        //red
        Bitmap idleImg11 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.redbird3, 100, 100);
        Bitmap idleImg12 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.redbird1, 100, 100);
        Bitmap idleImg13 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.redbird2, 100, 100);

        idle1 = new Animation(new Bitmap[]{idleImg11, idleImg12, idleImg13}, 0.2f);
        walkRight1 = new Animation(new Bitmap[]{idleImg11, idleImg12, idleImg13}, 0.2f);
        walkLeft1 = new Animation(new Bitmap[]{idleImg11, idleImg12, idleImg13}, 0.2f);

        animManager1 = new AnimationManager(new Animation[]{idle1, walkRight1, walkLeft1});

        Bitmap idleImg11s = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.redbird111, 100, 100);
        Bitmap idleImg12s = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.redbird112, 100, 100);
        Bitmap idleImg13s = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.redbird113, 100, 100);

        idle1s = new Animation(new Bitmap[]{idleImg11s, idleImg12s, idleImg13s}, 0.2f);
        walkRight1s = new Animation(new Bitmap[]{idleImg11s, idleImg12s, idleImg13s}, 0.2f);
        walkLeft1s = new Animation(new Bitmap[]{idleImg11s, idleImg12s, idleImg13s}, 0.2f);

        animManager1s = new AnimationManager(new Animation[]{idle1s, walkRight1s, walkLeft1s});

        Bitmap idleImg11sz = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.redbirdzoom1, 100, 100);
        Bitmap idleImg12sz = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.redbirdzoom2, 100, 100);
        Bitmap idleImg13sz = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.redbirdzoom3, 100, 100);

        idle1sz = new Animation(new Bitmap[]{idleImg11sz, idleImg12sz, idleImg13sz}, 0.2f);
        walkRight1sz = new Animation(new Bitmap[]{idleImg11sz, idleImg12sz, idleImg13sz}, 0.2f);
        walkLeft1sz = new Animation(new Bitmap[]{idleImg11sz, idleImg12sz, idleImg13sz}, 0.2f);

        animManager1sz = new AnimationManager(new Animation[]{idle1sz, walkRight1sz, walkLeft1sz});

        //black
        Bitmap idleImg21 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.blackbird3, 100, 100);
        Bitmap idleImg22 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.blackbird1, 100, 100);
        Bitmap idleImg23 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.blackbird2, 100, 100);

        idle2 = new Animation(new Bitmap[]{idleImg21, idleImg22, idleImg23}, 0.2f);
        walkRight2 = new Animation(new Bitmap[]{idleImg21, idleImg22, idleImg23}, 0.2f);
        walkLeft2 = new Animation(new Bitmap[]{idleImg21, idleImg22, idleImg23}, 0.2f);

        animManager2 = new AnimationManager(new Animation[]{idle2, walkRight2, walkLeft2});

        Bitmap idleImg21s = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.blackbird112, 100, 100);
        Bitmap idleImg22s = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.blackbird111, 100, 100);
        Bitmap idleImg23s = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.blackbird113, 100, 100);

        idle2s = new Animation(new Bitmap[]{idleImg21s, idleImg22s, idleImg23s}, 0.2f);
        walkRight2s = new Animation(new Bitmap[]{idleImg21s, idleImg22s, idleImg23s}, 0.2f);
        walkLeft2s = new Animation(new Bitmap[]{idleImg21s, idleImg22s, idleImg23s}, 0.2f);

        animManager2s = new AnimationManager(new Animation[]{idle2s, walkRight2s, walkLeft2s});

        Bitmap idleImg21sw = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.blackbirdwall1, 100, 100);
        Bitmap idleImg22sw = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.blackbirdwall2, 100, 100);
        Bitmap idleImg23sw = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.blackbirdwall3, 100, 100);

        idle2sw = new Animation(new Bitmap[]{idleImg21sw, idleImg22sw, idleImg23sw}, 0.2f);
        walkRight2sw = new Animation(new Bitmap[]{idleImg21sw, idleImg22sw, idleImg23sw}, 0.2f);
        walkLeft2sw = new Animation(new Bitmap[]{idleImg21sw, idleImg22sw, idleImg23sw}, 0.2f);

        animManager2sw = new AnimationManager(new Animation[]{idle2sw, walkRight2sw, walkLeft2sw});

        //pink
        Bitmap idleImg31 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.pinkbird12, 100, 100);
        Bitmap idleImg32 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.pinkbird11, 100, 100);
        Bitmap idleImg33 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.pinkbird13, 100, 100);

        idle3 = new Animation(new Bitmap[]{idleImg31, idleImg32, idleImg33}, 0.2f);
        walkRight3 = new Animation(new Bitmap[]{idleImg31, idleImg32, idleImg33}, 0.2f);
        walkLeft3 = new Animation(new Bitmap[]{idleImg31, idleImg32, idleImg33}, 0.2f);

        animManager3 = new AnimationManager(new Animation[]{idle3, walkRight3, walkLeft3});

        Bitmap idleImg31s = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.pinkbird111, 100, 100);
        Bitmap idleImg32s = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.pinkbird112, 100, 100);
        Bitmap idleImg33s = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.pinkbird113, 100, 100);

        idle3s = new Animation(new Bitmap[]{idleImg31s, idleImg32s, idleImg33s}, 0.2f);
        walkRight3s = new Animation(new Bitmap[]{idleImg31s, idleImg32s, idleImg33s}, 0.2f);
        walkLeft3s = new Animation(new Bitmap[]{idleImg31s, idleImg32s, idleImg33s}, 0.2f);

        animManager3s = new AnimationManager(new Animation[]{idle3s, walkRight3s, walkLeft3s});

        Bitmap idleImg31ss = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.pinkbirdsplit1, 100, 100);
        Bitmap idleImg32ss = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.pinkbirdsplit2, 100, 100);
        Bitmap idleImg33ss = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.pinkbirdsplit3, 100, 100);

        idle3ss = new Animation(new Bitmap[]{idleImg31ss, idleImg32ss, idleImg33ss}, 0.2f);
        walkRight3ss = new Animation(new Bitmap[]{idleImg31ss, idleImg32ss, idleImg33ss}, 0.2f);
        walkLeft3ss = new Animation(new Bitmap[]{idleImg31ss, idleImg32ss, idleImg33ss}, 0.2f);

        animManager3ss = new AnimationManager(new Animation[]{idle3ss, walkRight3ss, walkLeft3ss});

        //rainbow
        Bitmap idleImg41 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.rainbow1, 100, 100);
        Bitmap idleImg42 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.rainbow2, 100, 100);
        Bitmap idleImg43 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.rainbow3, 100, 100);
        Bitmap idleImg44 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.rainbow4, 100, 100);
        Bitmap idleImg45 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.rainbow5, 100, 100);
        Bitmap idleImg46 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.rainbow6, 100, 100);
        Bitmap idleImg47 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.rainbow7, 100, 100);


        idle4 = new Animation(new Bitmap[]{idleImg41, idleImg42, idleImg43, idleImg44, idleImg45, idleImg46, idleImg47}, 0.4f);
        walkRight4 = new Animation(new Bitmap[]{idleImg41, idleImg42, idleImg43, idleImg44, idleImg45, idleImg46, idleImg47}, 0.4f);
        walkLeft4 = new Animation(new Bitmap[]{idleImg41, idleImg42, idleImg43, idleImg44, idleImg45, idleImg46, idleImg47}, 0.4f);

        animManager4 = new AnimationManager(new Animation[]{idle4, walkRight4, walkLeft4});

        //dragon
        Bitmap idleImg51 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.dragon1, 100, 100);
        Bitmap idleImg52 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.dragon2, 100, 100);
        Bitmap idleImg53 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.dragon3, 100, 100);
        Bitmap idleImg54 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.dragon4, 100, 100);
        Bitmap idleImg55 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.dragon4, 100, 100);


        idle5 = new Animation(new Bitmap[]{idleImg51, idleImg52, idleImg53, idleImg54, idleImg55}, 0.2f);
        walkRight5 = new Animation(new Bitmap[]{idleImg51, idleImg52, idleImg53, idleImg54, idleImg55}, 0.2f);
        walkLeft5 = new Animation(new Bitmap[]{idleImg51, idleImg52, idleImg53, idleImg54, idleImg55}, 0.2f);

        animManager5 = new AnimationManager(new Animation[]{idle5, walkRight5, walkLeft5});

        Bitmap idleImg51s = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.dragon111, 100, 100);
        Bitmap idleImg52s = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.dragon112, 100, 100);
        Bitmap idleImg53s = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.dragon113, 100, 100);
        Bitmap idleImg54s = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.dragon114, 100, 100);


        idle5s = new Animation(new Bitmap[]{idleImg51s, idleImg52s, idleImg53s, idleImg54s}, 0.2f);
        walkRight5s = new Animation(new Bitmap[]{idleImg51s, idleImg52s, idleImg53s, idleImg54s}, 0.2f);
        walkLeft5s = new Animation(new Bitmap[]{idleImg51s, idleImg52s, idleImg53s, idleImg54s}, 0.2f);

        animManager5s = new AnimationManager(new Animation[]{idle5s, walkRight5s, walkLeft5s});

        Bitmap idleImg51ss = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.dragonsonar1, 100, 100);
        Bitmap idleImg52ss = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.dragonsonar2, 100, 100);
        Bitmap idleImg53ss = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.dragonsonar3, 100, 100);
        Bitmap idleImg54ss = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.dragonsonar4, 100, 100);


        idle5ss = new Animation(new Bitmap[]{idleImg51ss, idleImg52ss, idleImg53ss, idleImg54ss}, 0.2f);
        walkRight5ss = new Animation(new Bitmap[]{idleImg51ss, idleImg52ss, idleImg53ss, idleImg54ss}, 0.2f);
        walkLeft5ss = new Animation(new Bitmap[]{idleImg51ss, idleImg52ss, idleImg53ss, idleImg54ss}, 0.2f);

        animManager5ss = new AnimationManager(new Animation[]{idle5ss, walkRight5ss, walkLeft5ss});

        //ninja
        Bitmap idleImg61 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.ninja1, 100, 100);
        Bitmap idleImg62 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.ninja2, 100, 100);
        Bitmap idleImg63 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.ninja3, 100, 100);

        idle6 = new Animation(new Bitmap[]{idleImg61, idleImg62, idleImg63}, 0.2f);
        walkRight6 = new Animation(new Bitmap[]{idleImg61, idleImg62, idleImg63}, 0.2f);
        walkLeft6 = new Animation(new Bitmap[]{idleImg61, idleImg62, idleImg63}, 0.2f);

        animManager6 = new AnimationManager(new Animation[]{idle6, walkRight6, walkLeft6});

        Bitmap idleImg61s = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.ninjabird111, 100, 100);
        Bitmap idleImg62s = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.ninjabird112, 100, 100);
        Bitmap idleImg63s = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.ninjabird113, 100, 100);

        idle6s = new Animation(new Bitmap[]{idleImg61s, idleImg62s, idleImg63s}, 0.2f);
        walkRight6s = new Animation(new Bitmap[]{idleImg61s, idleImg62s, idleImg63s}, 0.2f);
        walkLeft6s = new Animation(new Bitmap[]{idleImg61s, idleImg62s, idleImg63s}, 0.2f);

        animManager6s = new AnimationManager(new Animation[]{idle6s, walkRight6s, walkLeft6s});

        Bitmap idleImg61st = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.ninjabirdtornado1, 100, 100);
        Bitmap idleImg62st = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.ninjabirdtornado3, 100, 100);
        Bitmap idleImg63st = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.ninjabirdtornado2, 100, 100);

        idle6st = new Animation(new Bitmap[]{idleImg61st, idleImg62st, idleImg63st}, 0.2f);
        walkRight6st = new Animation(new Bitmap[]{idleImg61st, idleImg62st, idleImg63st}, 0.2f);
        walkLeft6st = new Animation(new Bitmap[]{idleImg61st, idleImg62st, idleImg63st}, 0.2f);

        animManager6st = new AnimationManager(new Animation[]{idle6st, walkRight6st, walkLeft6st});

        //strong
            Bitmap idleImg71 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.strong1, 100, 100);
            Bitmap idleImg72 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.strong2, 100, 100);
            Bitmap idleImg73 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.strong3, 100, 100);

            idle7 = new Animation(new Bitmap[]{idleImg71, idleImg72, idleImg73}, 0.3f);
            walkRight7 = new Animation(new Bitmap[]{idleImg71, idleImg72, idleImg73}, 0.3f);
            walkLeft7 = new Animation(new Bitmap[]{idleImg71, idleImg72, idleImg73}, 0.3f);

            animManager7 = new AnimationManager(new Animation[]{idle7, walkRight7, walkLeft7});


            Bitmap idleImg71s = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.strong111, 100, 100);
            Bitmap idleImg72s = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.strong112, 100, 100);
            Bitmap idleImg73s = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.strong113, 100, 100);

            idle7s = new Animation(new Bitmap[]{idleImg71s, idleImg72s, idleImg73s}, 0.3f);
            walkRight7s = new Animation(new Bitmap[]{idleImg71s, idleImg72s, idleImg73s}, 0.3f);
            walkLeft7s = new Animation(new Bitmap[]{idleImg71s, idleImg72s, idleImg73s}, 0.3f);

            animManager7s = new AnimationManager(new Animation[]{idle7s, walkRight7s, walkLeft7s});


            Bitmap idleImg71ss = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.strongsmall1, 100, 100);
            Bitmap idleImg72ss = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.strongsmall2, 100, 100);
            Bitmap idleImg73ss = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.strongsmall3, 100, 100);

            idle7ss = new Animation(new Bitmap[]{idleImg71ss, idleImg72ss, idleImg73ss}, 0.2f);
            walkRight7ss = new Animation(new Bitmap[]{idleImg71ss, idleImg72ss, idleImg73ss}, 0.2f);
            walkLeft7ss = new Animation(new Bitmap[]{idleImg71ss, idleImg72ss, idleImg73ss}, 0.2f);

            animManager7ss = new AnimationManager(new Animation[]{idle7ss, walkRight7ss, walkLeft7ss});

        //Nitro
        Bitmap idleImg81 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.nitro1, 100, 100);
        Bitmap idleImg82 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.nitro2, 100, 100);
        Bitmap idleImg83 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.nitro3, 100, 100);

        idle8 = new Animation(new Bitmap[]{idleImg81, idleImg82, idleImg83}, 0.3f);
        walkRight8 = new Animation(new Bitmap[]{idleImg81, idleImg82, idleImg83}, 0.3f);
        walkLeft8 = new Animation(new Bitmap[]{idleImg81, idleImg82, idleImg83}, 0.3f);

        animManager8 = new AnimationManager(new Animation[]{idle8, walkRight8, walkLeft8});


        Bitmap idleImg81s = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.nitro1s, 100, 100);
        Bitmap idleImg82s = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.nitro2s, 100, 100);
        Bitmap idleImg83s = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.nitro3s, 100, 100);

        idle8s = new Animation(new Bitmap[]{idleImg81s, idleImg82s, idleImg83s}, 0.3f);
        walkRight8s = new Animation(new Bitmap[]{idleImg81s, idleImg82s, idleImg83s}, 0.3f);
        walkLeft8s = new Animation(new Bitmap[]{idleImg81s, idleImg82s, idleImg83s}, 0.3f);

        animManager8s = new AnimationManager(new Animation[]{idle8s, walkRight8s, walkLeft8s});


        Bitmap idleImg81ss = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.nitro1ss, 100, 100);
        Bitmap idleImg82ss = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.nitro2ss, 100, 100);
        Bitmap idleImg83ss = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.nitro3ss, 100, 100);

        idle8ss = new Animation(new Bitmap[]{idleImg81ss, idleImg82ss, idleImg83ss}, 0.3f);
        walkRight8ss = new Animation(new Bitmap[]{idleImg81ss, idleImg82ss, idleImg83ss}, 0.3f);
        walkLeft8ss = new Animation(new Bitmap[]{idleImg81ss, idleImg82ss, idleImg83ss}, 0.3f);

        animManager8ss = new AnimationManager(new Animation[]{idle8ss, walkRight8ss, walkLeft8ss});

        //laser
        Bitmap idleImg91 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.lukdlaser1, 100, 100);
        Bitmap idleImg92 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.lukdlaser2, 100, 100);
        Bitmap idleImg93 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.lukdlaser3, 100, 100);
        Bitmap idleImg94 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.lukdlaser4, 100, 100);

        idle9 = new Animation(new Bitmap[]{idleImg91, idleImg92, idleImg93, idleImg94}, 0.2f);
        walkRight9 = new Animation(new Bitmap[]{idleImg91, idleImg92, idleImg93, idleImg94}, 0.2f);
        walkLeft9 = new Animation(new Bitmap[]{idleImg91, idleImg92, idleImg93, idleImg94}, 0.2f);

        animManager9 = new AnimationManager(new Animation[]{idle9, walkRight9, walkLeft9});

        Bitmap idleImg91s = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.lukdlaser1s, 100, 100);
        Bitmap idleImg92s = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.lukdlaser2s, 100, 100);
        Bitmap idleImg93s = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.lukdlaser3s, 100, 100);
        Bitmap idleImg94s = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.lukdlaser4s, 100, 100);

        idle9s = new Animation(new Bitmap[]{idleImg91s, idleImg92s, idleImg93s, idleImg94s}, 0.2f);
        walkRight9s = new Animation(new Bitmap[]{idleImg91s, idleImg92s, idleImg93s, idleImg94s}, 0.2f);
        walkLeft9s = new Animation(new Bitmap[]{idleImg91s, idleImg92s, idleImg93s, idleImg94s}, 0.2f);

        animManager9s = new AnimationManager(new Animation[]{idle9s, walkRight9s, walkLeft9s});

        Bitmap idleImg91ss = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.lukdlaser1ss, 100, 100);
        Bitmap idleImg92ss = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.lukdlaser2ss, 100, 100);
        Bitmap idleImg93ss = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.lukdlaser3ss, 100, 100);
        Bitmap idleImg94ss = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.lukdlaser4ss, 100, 100);

        idle9ss = new Animation(new Bitmap[]{idleImg91ss, idleImg92ss, idleImg93ss, idleImg94ss}, 0.2f);
        walkRight9ss = new Animation(new Bitmap[]{idleImg91ss, idleImg92ss, idleImg93ss, idleImg94ss}, 0.2f);
        walkLeft9ss = new Animation(new Bitmap[]{idleImg91ss, idleImg92ss, idleImg93ss, idleImg94ss}, 0.2f);

        animManager9ss = new AnimationManager(new Animation[]{idle9ss, walkRight9ss, walkLeft9ss});

        //sage
        Bitmap idleImg101 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.sage1, 100, 100);
        Bitmap idleImg102 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.sage2, 100, 100);
        Bitmap idleImg103 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.sage3, 100, 100);

        idle10 = new Animation(new Bitmap[]{idleImg101, idleImg102, idleImg103}, 0.3f);
        walkRight10 = new Animation(new Bitmap[]{idleImg101, idleImg102, idleImg103}, 0.3f);
        walkLeft10 = new Animation(new Bitmap[]{idleImg101, idleImg102, idleImg103}, 0.3f);

        animManager10 = new AnimationManager(new Animation[]{idle10, walkRight10, walkLeft10});

        Bitmap idleImg101s = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.sage1s, 100, 100);
        Bitmap idleImg102s = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.sage2s, 100, 100);
        Bitmap idleImg103s = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.sage3s, 100, 100);

        idle10s = new Animation(new Bitmap[]{idleImg101s, idleImg102s, idleImg103s}, 0.3f);
        walkRight10s = new Animation(new Bitmap[]{idleImg101s, idleImg102s, idleImg103s}, 0.3f);
        walkLeft10s = new Animation(new Bitmap[]{idleImg101s, idleImg102s, idleImg103s}, 0.3f);

        animManager10s = new AnimationManager(new Animation[]{idle10s, walkRight10s, walkLeft10s});

        Bitmap idleImg101ss = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.sage1ss, 100, 100);
        Bitmap idleImg102ss = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.sage2ss, 100, 100);
        Bitmap idleImg103ss = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.sage3ss, 100, 100);

        idle10ss = new Animation(new Bitmap[]{idleImg101ss, idleImg102ss, idleImg103ss}, 0.3f);
        walkRight10ss = new Animation(new Bitmap[]{idleImg101ss, idleImg102ss, idleImg103ss}, 0.3f);
        walkLeft10ss = new Animation(new Bitmap[]{idleImg101ss, idleImg102ss, idleImg103ss}, 0.3f);

        animManager10ss = new AnimationManager(new Animation[]{idle10ss, walkRight10ss, walkLeft10ss});

        //bomb
        Bitmap idleImg111 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.bomb1, 100, 100);
        Bitmap idleImg112 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.bomb2, 100, 100);
        Bitmap idleImg113 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.bomb3, 100, 100);
        Bitmap idleImg114 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.bomb4, 100, 100);

        idle11 = new Animation(new Bitmap[]{idleImg111, idleImg112, idleImg113, idleImg114}, 0.3f);
        walkRight11 = new Animation(new Bitmap[]{idleImg111, idleImg112, idleImg113, idleImg114}, 0.3f);
        walkLeft11 = new Animation(new Bitmap[]{idleImg111, idleImg112, idleImg113, idleImg114}, 0.3f);

        animManager11 = new AnimationManager(new Animation[]{idle11, walkRight11, walkLeft11});

        Bitmap idleImg111s = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.bomb1s, 100, 100);
        Bitmap idleImg112s = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.bomb2s, 100, 100);
        Bitmap idleImg113s = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.bomb3s, 100, 100);
        Bitmap idleImg114s = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.bomb4s, 100, 100);

        idle11s = new Animation(new Bitmap[]{idleImg111s, idleImg112s, idleImg113s, idleImg114s}, 0.3f);
        walkRight11s = new Animation(new Bitmap[]{idleImg111s, idleImg112s, idleImg113s, idleImg114s}, 0.3f);
        walkLeft11s = new Animation(new Bitmap[]{idleImg111s, idleImg112s, idleImg113s, idleImg114s}, 0.3f);

        animManager11s = new AnimationManager(new Animation[]{idle11s, walkRight11s, walkLeft11s});

        Bitmap idleImg111ss = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.bomb1ss, 100, 100);
        Bitmap idleImg112ss = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.bomb2ss, 100, 100);
        Bitmap idleImg113ss = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.bomb3ss, 100, 100);
        Bitmap idleImg114ss = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.bomb4ss, 100, 100);
        Bitmap idleImg115ss = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.bomb5ss, 100, 100);
        Bitmap idleImg116ss = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.bomb6ss, 100, 100);
        Bitmap idleImg117ss = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.bomb7ss, 100, 100);
        Bitmap idleImg118ss = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.bomb8ss, 100, 100);

        idle11ss = new Animation(new Bitmap[]{idleImg111ss, idleImg112ss, idleImg113ss, idleImg114ss, idleImg115ss, idleImg116ss, idleImg117ss, idleImg118ss}, 0.3f);
        walkRight11ss = new Animation(new Bitmap[]{idleImg111ss, idleImg112ss, idleImg113ss, idleImg114ss, idleImg115ss, idleImg116ss, idleImg117ss, idleImg118ss}, 0.3f);
        walkLeft11ss = new Animation(new Bitmap[]{idleImg111ss, idleImg112ss, idleImg113ss, idleImg114ss, idleImg115ss, idleImg116ss, idleImg117ss, idleImg118ss}, 0.3f);

        animManager11ss = new AnimationManager(new Animation[]{idle11ss, walkRight11ss, walkLeft11ss});
    }

    @Override
    public void draw(Canvas canvas) {
        //Paint paint = new Paint();
        //paint.setColor(color);
        //canvas.drawRect(rectangle, paint);
        if(GamePanel.YelowBird == 2) {
            animManager.draw(canvas, rectangle);
        }
        if(GamePanel.YelowBird == 3) {
            animManagers.draw(canvas, rectangle);
        }
        if(GamePanel.RedBird == 2 || GamePanel.RedBird == 5) {
            animManager1.draw(canvas, rectangle);

        }
        if(GamePanel.RedBird == 3) {
            animManager1s.draw(canvas, rectangle);

        }
        if(GamePanel.RedBird == 4) {
            animManager1sz.draw(canvas, rectangle);

        }
        if(GamePanel.BlackBird == 2) {
            animManager2.draw(canvas, rectangle);

        }
        if(GamePanel.BlackBird == 3) {
            animManager2s.draw(canvas, rectangle);

        }
        if(GamePanel.BlackBird == 4) {
            animManager2sw.draw(canvas, rectangle);

        }
        if(GamePanel.PinkBird == 2) {
            animManager3.draw(canvas, rectangle);

        }
        if(GamePanel.PinkBird == 3) {
            animManager3s.draw(canvas, rectangle);

        }
        if(GamePanel.PinkBird == 4) {
            animManager3ss.draw(canvas, rectangle);

        }
        if(GamePanel.RainbowBird >= 2) {
            animManager4.draw(canvas, rectangle);

        }
        if(GamePanel.DragonBird == 2) {
            animManager5.draw(canvas, rectangle);

        }
        if(GamePanel.DragonBird == 3) {
            animManager5s.draw(canvas, rectangle);

        }
        if(GamePanel.DragonBird == 4) {
            animManager5ss.draw(canvas, rectangle);

        }
        if(GamePanel.NinjaBird == 2) {
            animManager6.draw(canvas, rectangle);

        }
        if(GamePanel.NinjaBird == 3) {
            animManager6s.draw(canvas, rectangle);

        }
        if(GamePanel.NinjaBird == 4) {
            animManager6st.draw(canvas, rectangle);

        }
        if(GamePanel.StrongBird == 2) {
            animManager7.draw(canvas, rectangle);

        }
        if(GamePanel.StrongBird == 3) {
            animManager7s.draw(canvas, rectangle);

        }
        if(GamePanel.StrongBird == 4) {
            animManager7ss.draw(canvas, rectangle);

        }
        if(GamePanel.NitroBird == 2) {
            animManager8.draw(canvas, rectangle);

        }
        if(GamePanel.NitroBird == 5) {
            animManager8.draw(canvas, rectangle);

        }
        if(GamePanel.NitroBird == 3) {
            animManager8s.draw(canvas, rectangle);

        }
        if(GamePanel.NitroBird == 4) {
            animManager8ss.draw(canvas, rectangle);

        }
        if(GamePanel.LaserBird == 2) {
            animManager9.draw(canvas, rectangle);

        }
        if(GamePanel.LaserBird == 5) {
            animManager9.draw(canvas, rectangle);

        }
        if(GamePanel.LaserBird == 3) {
            animManager9s.draw(canvas, rectangle);

        }
        if(GamePanel.LaserBird == 4) {
            animManager9ss.draw(canvas, rectangle);

        }
        if(GamePanel.SageBird == 2) {
            animManager10.draw(canvas, rectangle);

        }
        if(GamePanel.SageBird == 5) {
            animManager10.draw(canvas, rectangle);

        }
        if(GamePanel.SageBird == 3) {
            animManager10s.draw(canvas, rectangle);

        }
        if(GamePanel.SageBird == 4) {
            animManager10ss.draw(canvas, rectangle);

        }
        if(GamePanel.BombBird == 2) {
            animManager11.draw(canvas, rectangle);

        }
        if(GamePanel.BombBird == 5) {
            animManager11.draw(canvas, rectangle);

        }
        if(GamePanel.BombBird == 3) {
            animManager11s.draw(canvas, rectangle);

        }
        if(GamePanel.BombBird == 4) {
            animManager11ss.draw(canvas, rectangle);

        }


    }

    @Override
    public void update() {
        if(GamePanel.YelowBird == 2) {
            animManager.update();
        }
        if(GamePanel.YelowBird == 3) {
            animManagers.update();
        }
        if(GamePanel.RedBird == 2 || GamePanel.RedBird == 5) {
            animManager1.update();

        }
        if(GamePanel.RedBird == 3) {
            animManager1s.update();

        }
        if(GamePanel.RedBird == 4) {
            animManager1sz.update();

        }
        if(GamePanel.BlackBird == 2) {
            animManager2.update();

        }
        if(GamePanel.BlackBird == 3) {
            animManager2s.update();

        }
        if(GamePanel.BlackBird == 4) {
            animManager2sw.update();

        }
        if(GamePanel.PinkBird == 2) {
            animManager3.update();

        }
        if(GamePanel.PinkBird == 3) {
            animManager3s.update();

        }
        if(GamePanel.PinkBird == 4) {
            animManager3ss.update();

        }
        if(GamePanel.RainbowBird >= 2) {
            animManager4.update();

        }
        if(GamePanel.DragonBird == 2) {
            animManager5.update();

        }
        if(GamePanel.DragonBird == 3) {
            animManager5s.update();

        }
        if(GamePanel.DragonBird == 4) {
            animManager5ss.update();

        }
        if(GamePanel.NinjaBird == 2) {
            animManager6.update();

        }
        if(GamePanel.NinjaBird == 3) {
            animManager6s.update();

        }
        if(GamePanel.NinjaBird == 4) {
            animManager6st.update();

        }
        if(GamePanel.StrongBird == 2) {
            animManager7.update();

        }
        if(GamePanel.StrongBird == 3) {
            animManager7s.update();

        }
        if(GamePanel.StrongBird == 4) {
            animManager7ss.update();

        }
        if(GamePanel.NitroBird == 2) {
            animManager8.update();

        }
        if(GamePanel.NitroBird == 5) {
            animManager8.update();

        }
        if(GamePanel.NitroBird == 3) {
            animManager8s.update();

        }
        if(GamePanel.NitroBird == 4 ) {
            animManager8ss.update();

        }
        if(GamePanel.LaserBird == 2 ) {
            animManager9.update();

        }
        if(GamePanel.LaserBird == 5 ) {
            animManager9.update();

        }
        if(GamePanel.LaserBird == 3) {
            animManager9s.update();

        }
        if(GamePanel.LaserBird == 4 ) {
            animManager9ss.update();

        }
        if(GamePanel.SageBird == 2 ) {
            animManager10.update();

        }
        if(GamePanel.SageBird == 5 ) {
            animManager10.update();

        }
        if(GamePanel.SageBird == 3 ) {
            animManager10s.update();

        }
        if(GamePanel.SageBird == 4 ) {
            animManager10ss.update();

        }
        if(GamePanel.BombBird == 2 ) {
            animManager11.update();

        }
        if(GamePanel.BombBird == 5 ) {
            animManager11.update();

        }
        if(GamePanel.BombBird == 3 ) {
            animManager11s.update();

        }
        if(GamePanel.BombBird == 4 ) {
            animManager11ss.update();

        }

    }

    public void update(Point point) {
        float oldLeft = rectangle.left;

        rectangle.set(point.x - rectangle.width()/2, point.y - rectangle.height()/2, point.x + rectangle.width()/2, point.y + rectangle.height()/2 );

        int state = 0;
        if(rectangle.left - oldLeft > 5)
            state = 1;
        else if(rectangle.left - oldLeft < -5)
            state = 2;

        if(GamePanel.YelowBird == 2) {
            animManager.playAnim(state);
            animManager.update();
        }
        if(GamePanel.YelowBird == 3 ) {
            animManagers.playAnim(state);
            animManagers.update();
        }
        if(GamePanel.RedBird == 2 || GamePanel.RedBird == 5) {
            animManager1.playAnim(state);
            animManager1.update();
        }
        if(GamePanel.RedBird == 3) {
            animManager1s.playAnim(state);
            animManager1s.update();
        }
        if(GamePanel.RedBird == 4) {
            animManager1sz.playAnim(state);
            animManager1sz.update();
        }
        if(GamePanel.BlackBird == 2 ) {
            animManager2.playAnim(state);
            animManager2.update();
        }
        if(GamePanel.BlackBird == 3 ) {
            animManager2s.playAnim(state);
            animManager2s.update();
        }
        if(GamePanel.BlackBird == 4 ) {
            animManager2sw.playAnim(state);
            animManager2sw.update();
        }
        if(GamePanel.PinkBird == 2) {
            animManager3.playAnim(state);
            animManager3.update();
        }
        if(GamePanel.PinkBird == 3) {
            animManager3s.playAnim(state);
            animManager3s.update();
        }
        if(GamePanel.PinkBird == 4 ) {
            animManager3ss.playAnim(state);
            animManager3ss.update();
        }
        if(GamePanel.RainbowBird >= 2 ) {
            animManager4.playAnim(state);
            animManager4.update();
        }
        if(GamePanel.DragonBird == 2) {
            animManager5.playAnim(state);
            animManager5.update();
        }
        if(GamePanel.DragonBird == 3 ) {
            animManager5s.playAnim(state);
            animManager5s.update();
        }
        if(GamePanel.DragonBird == 4 ) {
            animManager5ss.playAnim(state);
            animManager5ss.update();
        }
        if(GamePanel.NinjaBird == 2 ) {
            animManager6.playAnim(state);
            animManager6.update();
        }
        if(GamePanel.NinjaBird == 3 ) {
            animManager6s.playAnim(state);
            animManager6s.update();
        }
        if(GamePanel.NinjaBird == 4 ) {
            animManager6st.playAnim(state);
            animManager6st.update();
        }
        if(GamePanel.StrongBird == 2 ) {
            animManager7.playAnim(state);
            animManager7.update();
        }
        if(GamePanel.StrongBird == 3 ) {
            animManager7s.playAnim(state);
            animManager7s.update();
        }
        if(GamePanel.StrongBird == 4 ) {
            animManager7ss.playAnim(state);
            animManager7ss.update();
        }
        if(GamePanel.NitroBird == 2 ) {
            animManager8.playAnim(state);
            animManager8.update();
        }
        if(GamePanel.NitroBird == 5 ) {
            animManager8.playAnim(state);
            animManager8.update();
        }
        if(GamePanel.NitroBird == 3 ) {
            animManager8s.playAnim(state);
            animManager8s.update();
        }
        if(GamePanel.NitroBird == 4 ) {
            animManager8ss.playAnim(state);
            animManager8ss.update();
        }
        if(GamePanel.LaserBird == 2 ) {
            animManager9.playAnim(state);
            animManager9.update();
        }
        if(GamePanel.LaserBird == 5 ) {
            animManager9.playAnim(state);
            animManager9.update();
        }
        if(GamePanel.LaserBird == 3 ) {
            animManager9s.playAnim(state);
            animManager9s.update();
        }
        if(GamePanel.LaserBird == 4 ) {
            animManager9ss.playAnim(state);
            animManager9ss.update();
        }
        if(GamePanel.SageBird == 2 ) {
            animManager10.playAnim(state);
            animManager10.update();
        }
        if(GamePanel.SageBird == 5 ) {
            animManager10.playAnim(state);
            animManager10.update();
        }
        if(GamePanel.SageBird == 3 ) {
            animManager10s.playAnim(state);
            animManager10s.update();
        }
        if(GamePanel.SageBird == 4 ) {
            animManager10ss.playAnim(state);
            animManager10ss.update();
        }
        if(GamePanel.BombBird == 2 ) {
            animManager11.playAnim(state);
            animManager11.update();
        }
        if(GamePanel.BombBird == 5 ) {
            animManager11.playAnim(state);
            animManager11.update();
        }
        if(GamePanel.BombBird == 3 ) {
            animManager11s.playAnim(state);
            animManager11s.update();
        }
        if(GamePanel.BombBird == 4 ) {
            animManager11ss.playAnim(state);
            animManager11ss.update();
        }

    }
}
