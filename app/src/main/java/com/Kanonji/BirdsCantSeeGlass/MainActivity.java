package com.Kanonji.BirdsCantSeeGlass;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

public class MainActivity extends Activity implements RewardedVideoAdListener {

    public static RewardedVideoAd mAd;
    public static RewardedVideoAd mAd2;
    public static MediaPlayer click;
    public static MediaPlayer unlock;
    public static MediaPlayer thud;
    public static MediaPlayer dizzy;
    public static MediaPlayer dizzyStart;
    public static MediaPlayer cash;
    public static MediaPlayer rumble;
    public static MediaPlayer roar;
    public static MediaPlayer teleport;
    public static MediaPlayer speed;
    public static MediaPlayer freeze;
    public static MediaPlayer explode;
    public static MediaPlayer whoosh;
    public static MediaPlayer ping;

    public static InterstitialAd mInterstitialAd;

    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
                & keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            Log.d("CDA", "onKeyDown Called");
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        if (SceneManager.ACTIVE_SCENE == 4 || SceneManager.ACTIVE_SCENE == 13 || SceneManager.ACTIVE_SCENE == 14) {
            System.exit(0);
        } else {
            SceneManager.ACTIVE_SCENE = GamePanel.Previous;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(getWindow().FEATURE_NO_TITLE);

        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constants.SCREEN_WIDTH = dm.widthPixels;
        Constants.SCREEN_HEIGHT = dm.heightPixels;

        setContentView(new GamePanel(this));

        mAd = MobileAds.getRewardedVideoAdInstance(this);
        mAd.setRewardedVideoAdListener(this);
        mAd2 = MobileAds.getRewardedVideoAdInstance(this);
        mAd2.setRewardedVideoAdListener(this);
        loadAd();

        click = MediaPlayer.create(getApplicationContext(), R.raw.click_sound);
        unlock = MediaPlayer.create(getApplicationContext(), R.raw.unlock_sound);
        thud = MediaPlayer.create(getApplicationContext(), R.raw.thud_sound);
        dizzy = MediaPlayer.create(getApplicationContext(), R.raw.dizzy2);
        dizzyStart = MediaPlayer.create(getApplicationContext(), R.raw.dizzystart);
        cash = MediaPlayer.create(getApplicationContext(), R.raw.cash);
        rumble = MediaPlayer.create(getApplicationContext(), R.raw.rumble);
        roar = MediaPlayer.create(getApplicationContext(), R.raw.roar);
        teleport = MediaPlayer.create(getApplicationContext(), R.raw.teleport);
        speed = MediaPlayer.create(getApplicationContext(), R.raw.speed);
        freeze = MediaPlayer.create(getApplicationContext(), R.raw.freeze);
        explode = MediaPlayer.create(getApplicationContext(), R.raw.explode);
        whoosh = MediaPlayer.create(getApplicationContext(), R.raw.whoosh);
        ping = MediaPlayer.create(getApplicationContext(), R.raw.ping);

        //MobileAds.initialize(this,"");

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("");
        //ca-app-pub-5053096756111759/4188580469
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.show();

        GamePanel.updateActivity(this);
        StartUpScene.updateActivity(this);
        GameplayScene.updateActivity(this);
        GameplayStoryScene.updateActivity(this);
        BuyScene.updateActivity(this);
        BuyScene2.updateActivity(this);
        BuyScene3.updateActivity(this);
        DailyTaskScene.updateActivity(this);
        StoreScene.updateActivity(this);
        StoreScene2.updateActivity(this);
        StoreScene3.updateActivity(this);

        //Appodeal.setTesting(true);

        //mActivityRef.get().finish();
    }

    private void click() {
        click.start();
    }
    private void unlock() {
        unlock.start();
    }
    private void thud() {
        thud.start();
    }

    private void loadAd() {
        if (!mAd.isLoaded()) {
            mAd.loadAd("", new AdRequest.Builder().build());
            //ca-app-pub-5053096756111759/4902896333

        }
        if (!mAd2.isLoaded()) {
            mAd2.loadAd("", new AdRequest.Builder().build());
            //ca-app-pub-5053096756111759/8761007024

        }

    }

    // Required to reward the user.
    @Override
    public void onRewarded(RewardItem reward) {
        if (GamePanel.Ad1 == 1) {
            GamePanel.HighCoin = GamePanel.HighCoin + 30;
            GamePanel.Ad1 = 0;
            Toast.makeText(this, "Congrats 30 Survival Points Added!", Toast.LENGTH_SHORT).show();
        }
        if (GamePanel.Ad2 == 1) {
            GamePanel.Ad2 = 2;
        }
    }

    // The following listener methods are optional.
    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdClosed() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int errorCode) {
    }

    @Override
    public void onRewardedVideoAdLoaded() {


    }

    @Override
    public void onRewardedVideoAdOpened() {

    }

    @Override
    public void onRewardedVideoStarted() {
    }

}
