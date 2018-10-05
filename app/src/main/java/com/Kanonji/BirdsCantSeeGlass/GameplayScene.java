package com.Kanonji.BirdsCantSeeGlass;

import android.app.Activity;
import android.content.Intent;
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

public class GameplayScene implements Scene {

    Timer t = new Timer();

    private static final long DOUBLE_PRESS_INTERVAL = 250; // in millis
    private long lastPressTime;
    boolean mHasDoubleClicked = false;

    private Rect r = new Rect();

    public static boolean tutorial = true;

    public static RectPlayer player;
    private static Point playerPoint;
    private static ObstacleManeger obstacleManeger;
    //private static ObstacleManeger2 obstacleManeger2;

    public static RectCircle circle;
    private static Point circlePoint;
    private static boolean movingCircle = false;

    private int color;
    private static boolean movingPlayer = false;

    public static boolean gameOver = false;

    private long gameOverTime;

    private  OrientationData orientationData;
    private long frameTime;

    //private int i = ObstacleManeger.getScore();

    //private int i1 = GamePanel.getHighScore();


    public GameplayScene() {
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

        obstacleManeger = new ObstacleManeger(300, 400, 100, Color.rgb(153, 255, 255));

        //obstacleManeger2 = new ObstacleManeger2(300, 400, 40, Color.YELLOW);
        orientationData = new OrientationData();
        //if (GamePanel.Help == 1) {
        //    orientationData.register();
        //}
        frameTime = System.currentTimeMillis();
        ObstacleManeger.myScore = 0;

    }


    public static void reset() {
        playerPoint = new Point(Constants.SCREEN_WIDTH / 2, 3 * Constants.SCREEN_HEIGHT / 4);
        player.update(playerPoint);
        obstacleManeger = new ObstacleManeger(Constants.SCREEN_WIDTH / 4 + Constants.SCREEN_WIDTH/30, Constants.SCREEN_WIDTH / 2 - (Constants.SCREEN_WIDTH / 12), Constants.SCREEN_WIDTH / 12, Color.argb(150, 255, 255, 255));
        //obstacleManeger2 = new ObstacleManeger2(300, Constants.SCREEN_HEIGHT, 40, Color.YELLOW);
        movingPlayer = false;
        movingCircle = false;
        ObstacleManeger.myScore = 0;
        MainActivity.mAd.isLoaded();
        GamePanel.Ad1 = 0;
        tutorial = true;

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

        if (GamePanel.YelowBird >= 1 && GamePanel.RedBird >= 1) {
        if (GamePanel.YelowBird == 4 || GamePanel.YelowBird == 2) {
            GamePanel.YelowBird = 3;
            GamePanel.RedBird = 1;
        }}
        if (GamePanel.RedBird == 4 || GamePanel.RedBird == 2) {
            GamePanel.RedBird = 3;
        }
        if (GamePanel.BlackBird == 4 || GamePanel.BlackBird == 3) {
            GamePanel.BlackBird = 2;
        }
        if (GamePanel.PinkBird == 4 || GamePanel.PinkBird == 3) {
            GamePanel.PinkBird = 2;
        }
        if (GamePanel.RainbowBird >= 2) {
            GamePanel.RainbowBird = 3;
        }
        if (GamePanel.StrongBird == 4 || GamePanel.StrongBird == 2) {
           GamePanel.StrongBird = 3;
        }
        if (GamePanel.NinjaBird == 4 || GamePanel.NinjaBird == 2) {
            GamePanel.NinjaBird = 3;
        }
        if (GamePanel.DragonBird == 4 || GamePanel.DragonBird == 3) {
            GamePanel.DragonBird = 2;
        }
        if (GamePanel.NitroBird == 4 || GamePanel.NitroBird == 2 || GamePanel.NitroBird == 5) {
            GamePanel.NitroBird = 3;
        }
        if (GamePanel.LaserBird == 4 || GamePanel.LaserBird == 2 || GamePanel.LaserBird == 5) {
            GamePanel.LaserBird = 3;
        }
        if (GamePanel.SageBird == 4 || GamePanel.SageBird == 2 || GamePanel.SageBird == 5) {
            GamePanel.SageBird = 3;
        }
        if (GamePanel.BombBird == 4 || GamePanel.BombBird == 2 || GamePanel.BombBird == 5) {
            GamePanel.BombBird = 3;
        }

    }

    public static void reset1() {
        obstacleManeger = new ObstacleManeger(Constants.SCREEN_WIDTH / 4 + Constants.SCREEN_WIDTH/30, Constants.SCREEN_WIDTH / 2 - (Constants.SCREEN_WIDTH / 12), Constants.SCREEN_WIDTH / 12, Color.argb(150, 255, 255, 255));
        //obstacleManeger2 = new ObstacleManeger2(300, Constants.SCREEN_HEIGHT, 40, Color.YELLOW);

    }


    @Override
    public void terminate() {
        SceneManager.ACTIVE_SCENE = 1;

    }

    private static WeakReference<Activity> mActivityRef;
    public static void updateActivity(Activity activity) {
        mActivityRef = new WeakReference<Activity>(activity);
    }

    private void loadAd() {
        if (!MainActivity.mAd.isLoaded()) {
            MainActivity.mAd.loadAd("", new AdRequest.Builder().build());
            //ca-app-pub-5053096756111759/4902896333
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
                int h77 = resizedbitmap77.getHeight();
                int w77 = resizedbitmap77.getWidth();
                float xRatio55 = ((Constants.SCREEN_WIDTH / 4)-( w55 / 4));
                float yRatio55 = (((Constants.SCREEN_HEIGHT / 2) + (h55 / 2)) + (Constants.SCREEN_HEIGHT/6));
                float xRatio66 = ((Constants.SCREEN_WIDTH / 2)+( w66 / 4) + (Constants.SCREEN_HEIGHT/24));
                float yRatio66 = (((Constants.SCREEN_HEIGHT / 2) + (h66 / 2)) + (Constants.SCREEN_HEIGHT/6));
                float xRatio77 = ((Constants.SCREEN_WIDTH / 2) - ( w77 / 2));
                float yRatio77 = (((Constants.SCREEN_HEIGHT / 2) + (h77 / 1)) + (Constants.SCREEN_HEIGHT/4));

                //help
                int h5 = resizedbitmap4.getHeight();
                int w5 = resizedbitmap4.getWidth();
                float xRatio5 = ((Constants.SCREEN_WIDTH / 4) - (w5 / 1));
                float yRatio5 = (((Constants.SCREEN_HEIGHT / 8) - (h5 / 1)));

                long pressTime = System.currentTimeMillis();

                // If double click...
                if (pressTime - lastPressTime <= DOUBLE_PRESS_INTERVAL) {

                    if (!gameOver) {
                        if (GamePanel.YelowBird >= 1 && GamePanel.RedBird >= 1) {
                        if (GamePanel.YelowBird == 3) {
                            MainActivity.click.start();
                            final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

                            final Runnable task = new Runnable() {
                                public void run() {
                                    if (GamePanel.YelowBird > 0 && GamePanel.RedBird > 0) {
                                        if (GamePanel.YelowBird == 3) {
                                            GamePanel.YelowBird = 4;
                                            GamePanel.RedBird = 5;
                                            if (GamePanel.Task3 == 0) {
                                                GamePanel.Task3 = 1;
                                            }
                                        }

                                    }


                                }

                            };
                            final ScheduledFuture<?> handle =
                                    scheduler.scheduleAtFixedRate(task, 0, 5, TimeUnit.SECONDS);

                            scheduler.schedule(new Runnable() {
                                public void run() {
                                    if (GamePanel.YelowBird > 1) {
                                        GamePanel.YelowBird = 2;
                                        GamePanel.RedBird = 1;
                                    }
                                    handle.cancel(true);
                                }
                            }, 5, TimeUnit.SECONDS);
                        }}

                        if (GamePanel.RedBird == 3) {
                            MainActivity.click.start();
                            final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

                            final Runnable task = new Runnable() {
                                public void run() {

                                    GamePanel.RedBird = 4;
                                    if (GamePanel.Task4 == 0) {
                                        GamePanel.Task4 = 1;
                                    }

                                }

                            };
                            final ScheduledFuture<?> handle =
                                    scheduler.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS);

                            scheduler.schedule(new Runnable() {
                                public void run() {
                                    if (GamePanel.RedBird > 1) {
                                        GamePanel.RedBird = 2;
                                    }
                                    handle.cancel(true);
                                }
                            }, 1, TimeUnit.SECONDS);

                        }

                        if (GamePanel.BlackBird == 3) {
                            MainActivity.rumble.start();
                            final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

                            final Runnable task = new Runnable() {
                                public void run() {

                                    GamePanel.BlackBird = 4;

                                }

                            };
                            final ScheduledFuture<?> handle =
                                    scheduler.scheduleAtFixedRate(task, 0, 2, TimeUnit.SECONDS);

                            scheduler.schedule(new Runnable() {
                                public void run() {
                                    if (GamePanel.BlackBird > 1) {
                                        GamePanel.BlackBird = 2;
                                    } else {
                                        GamePanel.BlackBird = 1;
                                    }
                                    handle.cancel(true);
                                }
                            }, 2, TimeUnit.SECONDS);

                        }

                        if (GamePanel.PinkBird == 3) {
                            MainActivity.rumble.start();
                            final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

                            final Runnable task = new Runnable() {
                                public void run() {

                                    GamePanel.PinkBird = 4;

                                }

                            };
                            final ScheduledFuture<?> handle =
                                    scheduler.scheduleAtFixedRate(task, 0, 3, TimeUnit.SECONDS);

                            scheduler.schedule(new Runnable() {
                                public void run() {
                                    if (GamePanel.PinkBird > 1) {
                                        GamePanel.PinkBird = 2;
                                    } else {
                                        GamePanel.PinkBird = 1;
                                    }
                                    handle.cancel(true);
                                }
                            }, 3, TimeUnit.SECONDS);

                        }

                        if (GamePanel.RainbowBird >= 2) {
                            if (GamePanel.RainbowBird == 3) {
                                GamePanel.RainbowBird = 4;
                            }
                            if (GamePanel.RainbowBird == 5) {
                                GamePanel.RainbowBird = 6;
                            }
                            if (GamePanel.RainbowBird == 7) {
                                GamePanel.RainbowBird = 8;
                            }
                            if (GamePanel.RainbowBird == 9) {
                                GamePanel.RainbowBird = 10;
                            }
                            if (GamePanel.RainbowBird == 11) {
                                GamePanel.RainbowBird = 12;
                            }
                            if (GamePanel.RainbowBird == 13) {
                                GamePanel.RainbowBird = 14;
                            }
                            if (GamePanel.RainbowBird == 15) {
                                GamePanel.RainbowBird = 16;
                            }

                        }

                        if (GamePanel.StrongBird == 3) {
                            MainActivity.click.start();
                            final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

                            final Runnable task = new Runnable() {
                                public void run() {
                                    if(Constants.SCREEN_HEIGHT > 1600) {
                                        player = new RectPlayer(new Rect(180, 180, Constants.SCREEN_WIDTH / 4, Constants.SCREEN_WIDTH / 4), Color.rgb(255, 0, 0));
                                        circle = new RectCircle(new Rect(180, 180, Constants.SCREEN_WIDTH / 4, Constants.SCREEN_WIDTH / 4), Color.rgb(255, 0, 0));
                                    }
                                    if(Constants.SCREEN_HEIGHT > 1100 && Constants.SCREEN_HEIGHT < 1600) {
                                        player = new RectPlayer(new Rect(180, 180,  Constants.SCREEN_WIDTH / 2 - (Constants.SCREEN_WIDTH / 4), Constants.SCREEN_WIDTH / 2 - (Constants.SCREEN_WIDTH / 4)), Color.rgb(255, 0, 0));
                                        circle = new RectCircle(new Rect(180, 180,  Constants.SCREEN_WIDTH / 2 - (Constants.SCREEN_WIDTH / 4), Constants.SCREEN_WIDTH / 2 - (Constants.SCREEN_WIDTH / 4)), Color.rgb(255, 0, 0));
                                    }
                                    if(Constants.SCREEN_HEIGHT < 1100 && Constants.SCREEN_HEIGHT > 900) {
                                        player = new RectPlayer(new Rect(180, 180, Constants.SCREEN_WIDTH / 2 - (Constants.SCREEN_WIDTH / 10), Constants.SCREEN_WIDTH / 2 - (Constants.SCREEN_WIDTH / 10)), Color.rgb(255, 0, 0));
                                        circle = new RectCircle(new Rect(180, 180, Constants.SCREEN_WIDTH / 2 - (Constants.SCREEN_WIDTH / 10), Constants.SCREEN_WIDTH / 2 - (Constants.SCREEN_WIDTH / 10)), Color.rgb(255, 0, 0));
                                    }
                                    if(Constants.SCREEN_HEIGHT < 900) {
                                        player = new RectPlayer(new Rect(180, 180, Constants.SCREEN_WIDTH / 2, Constants.SCREEN_WIDTH / 2), Color.rgb(255, 0, 0));
                                        circle = new RectCircle(new Rect(180, 180, Constants.SCREEN_WIDTH / 2, Constants.SCREEN_WIDTH / 2), Color.rgb(255, 0, 0));
                                    }
                                    if (GamePanel.StrongBird > 1) {
                                        GamePanel.StrongBird = 4;
                                    }

                                }

                            };
                            final ScheduledFuture<?> handle =
                                    scheduler.scheduleAtFixedRate(task, 0, 10, TimeUnit.SECONDS);

                            scheduler.schedule(new Runnable() {
                                public void run() {
                                    if (GamePanel.StrongBird > 1) {
                                        GamePanel.StrongBird = 2;
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
                                    } else if (GamePanel.StrongBird ==1) {
                                        GamePanel.StrongBird = 1;
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
                                    }
                                    handle.cancel(true);
                                }
                            }, 10, TimeUnit.SECONDS);

                        }

                        if (GamePanel.NinjaBird == 3) {
                            MainActivity.click.start();
                            final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

                            final Runnable task = new Runnable() {
                                public void run() {
                                    if (GamePanel.NinjaBird > 1) {
                                        GamePanel.NinjaBird = 4;
                                    }

                                }

                            };
                            final ScheduledFuture<?> handle =
                                    scheduler.scheduleAtFixedRate(task, 0, 10, TimeUnit.SECONDS);

                            scheduler.schedule(new Runnable() {
                                public void run() {
                                    if (GamePanel.NinjaBird > 1) {
                                        GamePanel.NinjaBird = 2;
                                        reset1();
                                    } else if (GamePanel.NinjaBird ==1) {
                                        GamePanel.NinjaBird =1;
                                    }
                                    handle.cancel(true);
                                }
                            }, 10, TimeUnit.SECONDS);

                        }

                        if (GamePanel.DragonBird == 3) {
                            MainActivity.roar.start();
                            final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

                            final Runnable task = new Runnable() {
                                public void run() {

                                    if (GamePanel.DragonBird > 1) {
                                        GamePanel.DragonBird = 4;
                                    }

                                }

                            };
                            final ScheduledFuture<?> handle =
                                    scheduler.scheduleAtFixedRate(task, 0, 5, TimeUnit.SECONDS);

                            scheduler.schedule(new Runnable() {
                                public void run() {
                                    if (GamePanel.DragonBird > 1) {
                                        GamePanel.DragonBird = 2;
                                    } else {
                                        GamePanel.DragonBird = 1;
                                    }
                                    handle.cancel(true);
                                }
                            }, 5, TimeUnit.SECONDS);

                        }

                        if (GamePanel.NitroBird == 3) {
                            MainActivity.speed.start();
                            final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

                            final Runnable task = new Runnable() {
                                public void run() {

                                    if (GamePanel.NitroBird > 1) {
                                        GamePanel.NitroBird = 4;
                                    }

                                }

                            };
                            final ScheduledFuture<?> handle =
                                    scheduler.scheduleAtFixedRate(task, 0, 5, TimeUnit.SECONDS);

                            scheduler.schedule(new Runnable() {
                                public void run() {
                                    if (GamePanel.NitroBird > 1) {
                                        GamePanel.NitroBird = 5;
                                    } else {
                                        GamePanel.NitroBird = 1;
                                    }
                                    handle.cancel(true);
                                }
                            }, 5, TimeUnit.SECONDS);

                        }

                        if (GamePanel.LaserBird == 3) {
                            MainActivity.speed.start();
                            final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

                            final Runnable task = new Runnable() {
                                public void run() {

                                    if (GamePanel.LaserBird > 1) {
                                        GamePanel.LaserBird = 4;
                                    }

                                }

                            };
                            final ScheduledFuture<?> handle =
                                    scheduler.scheduleAtFixedRate(task, 0, 3, TimeUnit.SECONDS);

                            scheduler.schedule(new Runnable() {
                                public void run() {
                                    if (GamePanel.LaserBird > 1) {
                                        GamePanel.LaserBird = 5;
                                    } else {
                                        GamePanel.LaserBird = 1;
                                    }
                                    handle.cancel(true);
                                }
                            }, 3, TimeUnit.SECONDS);

                        }

                        if (GamePanel.SageBird == 3) {
                            MainActivity.freeze.start();
                            final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

                            final Runnable task = new Runnable() {
                                public void run() {

                                    if (GamePanel.SageBird > 1) {
                                        GamePanel.SageBird = 4;
                                    }

                                }

                            };
                            final ScheduledFuture<?> handle =
                                    scheduler.scheduleAtFixedRate(task, 0, 10, TimeUnit.SECONDS);

                            scheduler.schedule(new Runnable() {
                                public void run() {
                                    if (GamePanel.SageBird > 1) {
                                        GamePanel.SageBird = 5;
                                    } else {
                                        GamePanel.SageBird = 1;
                                    }
                                    handle.cancel(true);
                                }
                            }, 10, TimeUnit.SECONDS);

                        }

                        if (GamePanel.BombBird == 3) {
                            MainActivity.explode.start();
                            final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

                            final Runnable task = new Runnable() {
                                public void run() {

                                    if (GamePanel.BombBird > 1) {
                                        GamePanel.BombBird = 4;
                                    }

                                }

                            };
                            final ScheduledFuture<?> handle =
                                    scheduler.scheduleAtFixedRate(task, 0, 500, TimeUnit.MILLISECONDS);

                            scheduler.schedule(new Runnable() {
                                public void run() {
                                    if (GamePanel.BombBird > 1) {
                                        GamePanel.BombBird = 5;
                                        reset1();
                                        ObstacleManeger.myScore = ObstacleManeger.getScore() + 10;
                                    } else {
                                        GamePanel.BombBird = 1;
                                    }
                                    handle.cancel(true);
                                }
                            }, 500, TimeUnit.MILLISECONDS);

                        }




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
                            if (GamePanel.Ad1 == 1) {
                                MainActivity.click.start();
                                MainActivity.mAd = MobileAds.getRewardedVideoAdInstance(mActivityRef.get());
                                loadAd();
                                MainActivity.mAd.show();
                                //Appodeal.show(mActivityRef.get(), Appodeal.REWARDED_VIDEO);

                            }
                        }
                        if (x >= xRatio55 && x < (xRatio55 + resizedbitmap55.getWidth())
                                && y >= yRatio55 && y < (yRatio55 + resizedbitmap55.getHeight())) {
                            MainActivity.click.start();
                            reset();
                            gameOver = false;
                            orientationData.newGame();

                            if (GamePanel.DailyTask3 == 0) {
                                GamePanel.DailyTask3Counter = GamePanel.DailyTask3Counter + 1;
                            }
                            if (GamePanel.DailyTask3 == 0) {
                                if (GamePanel.DailyTask3Counter >= 10) {
                                    GamePanel.DailyTask3 = 1;
                                }
                            }
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
                        if (x >= xRatio77 && x < (xRatio77 + resizedbitmap77.getWidth())
                                && y >= yRatio77 && y < (yRatio77 + resizedbitmap77.getHeight())) {
                            MainActivity.click.start();
                            Intent intent = new Intent();
                            intent.setAction(Intent.ACTION_SEND);

                            intent.setType("text/plain");
                            intent.putExtra(Intent.EXTRA_TEXT, "OMG, I just Scored " + obstacleManeger.getScore() + " https://bit.ly/2Hphhgn, Try and beat me!");
                            mActivityRef.get().startActivity(Intent.createChooser(intent, "Share"));

                        }
                        if (x >= xRatio5 && x < (xRatio5 + resizedbitmap4.getWidth())
                                && y >= yRatio5 && y < (yRatio5 + resizedbitmap4.getHeight())) {
                            if (GamePanel.Help == 0) {
                                GamePanel.Help = 1;
                                if (GamePanel.Help == 1) {
                                    MainActivity.click.start();
                                    orientationData.register();
                                }

                            } else if (GamePanel.Help == 1) {
                                GamePanel.Help = 0;
                                if (GamePanel.Help == 0) {
                                    MainActivity.click.start();
                                    orientationData.pause();
                                }
                            }

                        }

                    } return true;
                }

                break;
            case MotionEvent.ACTION_MOVE:
                if (GamePanel.NinjaBird == 4) {
                    MainActivity.teleport.start();
                    if (!gameOver)
                        playerPoint.set((int) event.getX(), (int) event.getY());
                    if (!gameOver)
                        circlePoint.set((int) event.getX(), (int) event.getY()+Constants.SCREEN_HEIGHT/8);
                } else {
                    if (!gameOver && movingPlayer )
                        playerPoint.set((int) event.getX(), (int) event.getY()-Constants.SCREEN_HEIGHT/8);
                    if (!gameOver && movingCircle)
                        circlePoint.set((int) event.getX(), (int) event.getY());
                }
                break;
            case MotionEvent.ACTION_UP:
                movingPlayer = false;
                movingCircle = false;
                break;

        }

        return false;
    }

    Bitmap backgroundImage = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.cloudback, 500, 500);
    Bitmap resizedbackgroundImage = Bitmap.createScaledBitmap(backgroundImage, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, true);

    private void drawEndlessBackground(Canvas canvas, float left, float top) {
        float modLeft = left % Constants.SCREEN_WIDTH;
        canvas.drawBitmap(resizedbackgroundImage, modLeft, modLeft, null);
        if (left < 0) {
            canvas.drawBitmap(resizedbackgroundImage, left % Constants.SCREEN_WIDTH, top, null);
        } else {
            canvas.drawBitmap(resizedbackgroundImage, left % Constants.SCREEN_WIDTH, top, null);
        }
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

    Bitmap bitmap4 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.help, 100, 100);
    Bitmap resizedbitmap4 = Bitmap.createScaledBitmap(bitmap4, Constants.SCREEN_WIDTH/6, Constants.SCREEN_WIDTH/6, true);

    Bitmap bitmap11 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.help2, 100, 100);
    Bitmap resizedbitmap11 = Bitmap.createScaledBitmap(bitmap11, Constants.SCREEN_WIDTH/6, Constants.SCREEN_WIDTH/6, true);

    Bitmap bitmap55 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.retry1, 100, 100);
    Bitmap resizedbitmap55 = Bitmap.createScaledBitmap(bitmap55, Constants.SCREEN_WIDTH/6, Constants.SCREEN_WIDTH/6, true);

    Bitmap bitmap66 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.menu1, 100, 100);
    Bitmap resizedbitmap66 = Bitmap.createScaledBitmap(bitmap66, Constants.SCREEN_WIDTH/6, Constants.SCREEN_WIDTH/6, true);

    Bitmap bitmap77 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.sharebtn, 100, 100);
    Bitmap resizedbitmap77 = Bitmap.createScaledBitmap(bitmap77, Constants.SCREEN_WIDTH/6, Constants.SCREEN_WIDTH/6, true);

    Bitmap bitmap200 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.tutorial, 150, 150);

    Bitmap bitmap6 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.glasssp1, 150, 150);
    Bitmap resizedbitmap6 = Bitmap.createScaledBitmap(bitmap6, Constants.SCREEN_WIDTH/2, Constants.SCREEN_HEIGHT/11, true);
    Bitmap bitmap6c = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.glasssp2, 150, 150);
    Bitmap resizedbitmap6c = Bitmap.createScaledBitmap(bitmap6c, Constants.SCREEN_WIDTH/2, Constants.SCREEN_HEIGHT/11, true);

    Bitmap d = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.cloud5, 300, 300);
    Bitmap bitmap111 = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.sp2, 50, 50);
    Bitmap bitmapTitle = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.gameover, 100, 100);
    Bitmap bitmapScore = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.scorecloud, 100, 100);
    Bitmap bitmapHighScore = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.highscorecloud, 100, 100);
    Bitmap bitmapSP = decodeSampledBitmapFromResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.survival, 50, 50);

    @Override
    public void draw(Canvas canvas) {
        //canvas.drawBitmap(BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.beach1), -250, 200, null);
        //Bitmap background= BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.beach1);
        //background= resizeImage(background, Constants.SCREEN_WIDTH ,Constants.SCREEN_HEIGHT);
        //canvas.drawBitmap(background, 0, 0, null);

        Paint paint = new Paint();

        Typeface myTypeface = Typeface.createFromAsset(Constants.CURRENT_CONTEXT.getAssets(), "chunk.ttf");

        canvas.drawColor(Color.rgb(0, 255, 255));

        if (GamePanel.RainbowBird == 4 || GamePanel.RainbowBird == 5) {
            canvas.drawColor(Color.rgb(255, 0, 0));
        }
        if (GamePanel.RainbowBird == 6 || GamePanel.RainbowBird == 7) {
            canvas.drawColor(Color.rgb(255, 150, 0));
        }
        if (GamePanel.RainbowBird == 8 || GamePanel.RainbowBird == 9) {
            canvas.drawColor(Color.rgb(255, 215, 0));
        }
        if (GamePanel.RainbowBird == 10 || GamePanel.RainbowBird == 11) {
            canvas.drawColor(Color.rgb(0, 255, 0));
        }
        if (GamePanel.RainbowBird == 12 || GamePanel.RainbowBird == 13) {
            canvas.drawColor(Color.rgb(0, 191, 255));
        }
        if (GamePanel.RainbowBird == 14 || GamePanel.RainbowBird == 15) {
            canvas.drawColor(Color.rgb(75, 0, 130));
        }
        if (GamePanel.RainbowBird == 16 || GamePanel.RainbowBird == 17) {
            canvas.drawColor(Color.rgb(238, 130, 238));
        }

        //BitmapFactory.Options options = new BitmapFactory.Options();
        //options.inJustDecodeBounds = false;
        //options.inSampleSize = 1;

        drawEndlessBackground(canvas, 0, 0);

        player.draw(canvas);
        circle.draw(canvas);
        obstacleManeger.draw(canvas);
        //obstacleManeger2.draw(canvas);

        if (gameOver == false) {
            if (GamePanel.YelowBird == 3 || GamePanel.RedBird == 3 || GamePanel.BlackBird == 3 ||
                    GamePanel.PinkBird == 3 || GamePanel.RainbowBird == 3 || GamePanel.StrongBird == 3 ||
                    GamePanel.NinjaBird == 3 || GamePanel.DragonBird == 3 || GamePanel.NitroBird == 3 ||
                    GamePanel.LaserBird == 3 || GamePanel.SageBird == 3 || GamePanel.BombBird == 3) {
                paint.setTextSize(Constants.SCREEN_WIDTH / 30);
                paint.setTypeface(myTypeface);
                paint.setColor(Color.BLACK);
                drawCentreText8(canvas, paint, "Double Tap to ACTIVATE ABILITY");
            }
        }
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

        if (GamePanel.BombBird == 5) {
            canvas.drawColor(Color.rgb(255, 255, 255));
        }

        if(gameOver) {

            //canvas.drawBitmap(BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.cloud), -250, 200, null);
            //Bitmap background= BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.beach1);
            //background= resizeImage(background, Constants.SCREEN_WIDTH ,Constants.SCREEN_HEIGHT);
            //canvas.drawBitmap(background, 0, 0, null);
            if (GamePanel.Help == 0) {
                int h11 = resizedbitmap11.getHeight();
                int w11 = resizedbitmap11.getWidth();
                canvas.drawBitmap(resizedbitmap11, ((Constants.SCREEN_WIDTH / 4) - (w11 / 1)), (((Constants.SCREEN_HEIGHT / 8) - (h11 / 1))), new Paint());
            }
            if (GamePanel.Help == 1) {
                int h4 = resizedbitmap4.getHeight();
                int w4 = resizedbitmap4.getWidth();
                canvas.drawBitmap(resizedbitmap4, ((Constants.SCREEN_WIDTH / 4) - (w4 / 1)), (((Constants.SCREEN_HEIGHT / 8) - (h4 / 1))), new Paint());
            }
            //retry
            int h55 = resizedbitmap55.getHeight();
            int w55 = resizedbitmap55.getWidth();
            canvas.drawBitmap(resizedbitmap55, ((Constants.SCREEN_WIDTH / 4)-( w55 / 4)), (((Constants.SCREEN_HEIGHT / 2) + (h55 / 2)) + (Constants.SCREEN_HEIGHT/6)), new Paint());
            //menu
            int h66 = resizedbitmap66.getHeight();
            int w66 = resizedbitmap66.getWidth();
            canvas.drawBitmap(resizedbitmap66, ((Constants.SCREEN_WIDTH / 2)+( w66 / 4) + (Constants.SCREEN_HEIGHT/24)), (((Constants.SCREEN_HEIGHT / 2) + (h66 / 2)) + (Constants.SCREEN_HEIGHT/6)), new Paint());
            //share
            int h77 = resizedbitmap77.getHeight();
            int w77 = resizedbitmap77.getWidth();
            canvas.drawBitmap(resizedbitmap77, ((Constants.SCREEN_WIDTH / 2) - ( w77 / 2)), (((Constants.SCREEN_HEIGHT / 2) + (h77 / 1)) + (Constants.SCREEN_HEIGHT/4)), new Paint());

            if (GamePanel.Ad1 == 1) {
                int h6 = resizedbitmap6.getHeight();
                int w6 = resizedbitmap6.getWidth();
                canvas.drawBitmap(resizedbitmap6, ((Constants.SCREEN_WIDTH / 2) - (w6 / 2)), (((Constants.SCREEN_HEIGHT / 2) - (h6 / 2)) + (Constants.SCREEN_WIDTH / 4 + (Constants.SCREEN_WIDTH / 7)) - Constants.SCREEN_HEIGHT / 10), new Paint());
            } else {
                int h6 = resizedbitmap6c.getHeight();
                int w6 = resizedbitmap6c.getWidth();
                canvas.drawBitmap(resizedbitmap6c, ((Constants.SCREEN_WIDTH / 2) - (w6 / 2)), (((Constants.SCREEN_HEIGHT / 2) - (h6 / 2)) + (Constants.SCREEN_WIDTH / 4 + (Constants.SCREEN_WIDTH / 7)) - Constants.SCREEN_HEIGHT / 10), new Paint());
            }


            Bitmap resizedbitmapd = Bitmap.createScaledBitmap(d, Constants.SCREEN_WIDTH - Constants.SCREEN_WIDTH/3, Constants.SCREEN_WIDTH/2, true);
            int h = resizedbitmapd.getHeight();
            int w = resizedbitmapd.getWidth();
            canvas.drawBitmap(resizedbitmapd, ((Constants.SCREEN_WIDTH / 2) - (w / 2)), (((Constants.SCREEN_HEIGHT / 2) - (h / 2)) - Constants.SCREEN_HEIGHT/10), null);

            Bitmap resizedbitmap111 = Bitmap.createScaledBitmap(bitmap111, Constants.SCREEN_WIDTH/10, Constants.SCREEN_WIDTH/10, true);
            int h111 = resizedbitmap111.getHeight();
            int w111 = resizedbitmap111.getWidth();
            canvas.drawBitmap(resizedbitmap111, ((Constants.SCREEN_WIDTH / 4)-( w111 / 2) + (Constants.SCREEN_WIDTH/2)), (((Constants.SCREEN_HEIGHT / 2) - (h111 / 2) - Constants.SCREEN_WIDTH/7) - (Constants.SCREEN_HEIGHT/3)), new Paint());

            Bitmap resizedbitmapTitle = Bitmap.createScaledBitmap(bitmapTitle, Constants.SCREEN_WIDTH/2 + Constants.SCREEN_WIDTH/4, Constants.SCREEN_WIDTH/8, true);
            int hTitle = resizedbitmapTitle.getHeight();
            int wTitle = resizedbitmapTitle.getWidth();
            canvas.drawBitmap(resizedbitmapTitle, Constants.SCREEN_WIDTH/2 - wTitle/2, 0 + Constants.SCREEN_HEIGHT/6, new Paint());

            paint.setTextSize(Constants.SCREEN_WIDTH / 16);
            paint.setTypeface(myTypeface);
            paint.setColor(Color.BLACK);

            Bitmap resizedbitmapScore = Bitmap.createScaledBitmap(bitmapScore, Constants.SCREEN_WIDTH/5, Constants.SCREEN_WIDTH/12, true);
            int hScore = resizedbitmapScore.getHeight();
            int wScore = resizedbitmapScore.getWidth();
            canvas.drawBitmap(resizedbitmapScore, Constants.SCREEN_WIDTH/2 - wScore/2, (Constants.SCREEN_HEIGHT/2 - hScore/2) - ((Constants.SCREEN_WIDTH/8) + Constants.SCREEN_HEIGHT/10), new Paint());

            paint.setTextSize(Constants.SCREEN_WIDTH / 10);
            paint.setColor(Color.BLACK);
            drawCentreText4(canvas, paint, "" + ObstacleManeger.getScore());

            Bitmap resizedbitmapHighScore = Bitmap.createScaledBitmap(bitmapHighScore, Constants.SCREEN_WIDTH/3, Constants.SCREEN_WIDTH/12, true);
            int hHighScore = resizedbitmapHighScore.getHeight();
            int wHighScore = resizedbitmapHighScore.getWidth();
            canvas.drawBitmap(resizedbitmapHighScore, Constants.SCREEN_WIDTH/2 - wHighScore/2, (Constants.SCREEN_HEIGHT/2 - hHighScore/2) + ((Constants.SCREEN_WIDTH/18) - Constants.SCREEN_HEIGHT/10), new Paint());

            paint.setTextSize(Constants.SCREEN_WIDTH / 20);
            paint.setColor(Color.BLACK);
            drawCentreText5(canvas, paint, "" + GamePanel.getHighScore());

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


            if (ObstacleManeger.getScore() >= 100) {
                paint.setTextSize(Constants.SCREEN_WIDTH / 30);
                paint.setTypeface(myTypeface);
                paint.setColor(Color.BLUE);
                drawCentreText19(canvas, paint, "++ " + ObstacleManeger.getScore());
            } else ; {
            if (ObstacleManeger.getScore() >=50 && ObstacleManeger.getScore() < 100) {
                paint.setTextSize(Constants.SCREEN_WIDTH / 30);
                paint.setTypeface(myTypeface);
                paint.setColor(Color.rgb(255, 215, 0));
                drawCentreText19(canvas, paint, "++ " + ObstacleManeger.getScore());
            } else ;
                {

                }
            if (ObstacleManeger.getScore() > 0 && ObstacleManeger.getScore() < 50) {
                paint.setTextSize(Constants.SCREEN_WIDTH / 30);
                paint.setTypeface(myTypeface);
                paint.setColor(Color.rgb(255, 140, 0));
                drawCentreText19(canvas, paint, "++ " + ObstacleManeger.getScore());
            } else ;
                    {
                        if (ObstacleManeger.getScore() <= 0) {
                            paint.setTextSize(Constants.SCREEN_WIDTH / 30);
                            paint.setTypeface(myTypeface);
                            paint.setColor(Color.RED);
                            drawCentreText19(canvas, paint, "++ " + ObstacleManeger.getScore());
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
            obstacleManeger.update();
            //obstacleManeger2.update();

            if (GamePanel.NitroBird == 5) {
                final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

                final Runnable task = new Runnable() {
                    public void run() {


                    }

                };
                final ScheduledFuture<?> handle =
                        scheduler.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS);

                scheduler.schedule(new Runnable() {
                    public void run() {
                        if (GamePanel.NitroBird > 1) {
                            GamePanel.NitroBird = 2;
                        } else {
                            GamePanel.NitroBird = 1;
                        }
                        handle.cancel(true);
                    }
                }, 1, TimeUnit.SECONDS);

            }

            if (GamePanel.LaserBird == 5) {
                final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

                final Runnable task = new Runnable() {
                    public void run() {


                    }

                };
                final ScheduledFuture<?> handle =
                        scheduler.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS);

                scheduler.schedule(new Runnable() {
                    public void run() {
                        if (GamePanel.LaserBird > 1) {
                            GamePanel.LaserBird = 2;
                        } else {
                            GamePanel.LaserBird = 1;
                        }
                        handle.cancel(true);
                    }
                }, 1, TimeUnit.SECONDS);

            }

            if (GamePanel.SageBird == 5) {
                final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

                final Runnable task = new Runnable() {
                    public void run() {


                    }

                };
                final ScheduledFuture<?> handle =
                        scheduler.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS);

                scheduler.schedule(new Runnable() {
                    public void run() {
                        if (GamePanel.SageBird > 1) {
                            GamePanel.SageBird = 2;
                        } else {
                            GamePanel.SageBird = 1;
                        }
                        handle.cancel(true);
                    }
                }, 1, TimeUnit.SECONDS);

            }

            if (GamePanel.BombBird == 5) {
                final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

                final Runnable task = new Runnable() {
                    public void run() {


                    }

                };
                final ScheduledFuture<?> handle =
                        scheduler.scheduleAtFixedRate(task, 0, 500, TimeUnit.MILLISECONDS);

                scheduler.schedule(new Runnable() {
                    public void run() {
                        if (GamePanel.BombBird > 1) {
                            GamePanel.BombBird = 2;
                        } else {
                            GamePanel.BombBird = 1;
                        }
                        handle.cancel(true);
                    }
                }, 500, TimeUnit.MILLISECONDS);

            }


            if (obstacleManeger.playerCollide(player)) {
                if (GamePanel.NitroBird == 4 || GamePanel.NitroBird == 5 || GamePanel.LaserBird == 4 || GamePanel.LaserBird == 5 || GamePanel.SageBird == 5) {

                } else {
                    MainActivity.thud.start();
                    MainActivity.dizzy.start();
                    gameOver = true;
                    gameOverTime = System.currentTimeMillis();
                    GamePanel.HighCoin = (ObstacleManeger.getScore() + GamePanel.HighCoin);
                    GamePanel.Ad1 = 1;
                    //Highscore
                    if (ObstacleManeger.getScore() < GamePanel.HighScore && ObstacleManeger.getScore() < GamePanel.HighScore1 &&
                            ObstacleManeger.getScore() > GamePanel.HighScore2) {

                        GamePanel.HighScore2 = ObstacleManeger.getScore();

                    } else if (ObstacleManeger.getScore() < GamePanel.HighScore && ObstacleManeger.getScore() > GamePanel.HighScore1) {

                        GamePanel.HighScore2 = GamePanel.HighScore1;
                        GamePanel.HighScore1 = ObstacleManeger.getScore();

                    } else if (ObstacleManeger.getScore() > GamePanel.HighScore) {

                        GamePanel.HighScore2 = GamePanel.HighScore1;
                        GamePanel.HighScore1 = GamePanel.HighScore;
                        GamePanel.HighScore = ObstacleManeger.getScore();

                    }
                    //Challenges
                    if(GamePanel.Task2 == 0) {
                        if(ObstacleManeger.getScore() >= 20) {
                            GamePanel.Task2 = 1;
                        }
                    }
                    if(GamePanel.Task6 == 0) {
                        if(ObstacleManeger.getScore() >= 60) {
                            GamePanel.Task6 = 1;
                        }
                    }
                    if(GamePanel.Task10 == 0) {
                        if(ObstacleManeger.getScore() >= 100) {
                            GamePanel.Task10 = 1;
                        }
                    }
                    if(GamePanel.Task7 == 0 && GamePanel.YelowBird >= 2 || GamePanel.RedBird == 5) {
                        if(ObstacleManeger.getScore() >= 50) {
                            GamePanel.Task7 = 1;
                        }
                    }
                    if(GamePanel.Task8 == 0 && GamePanel.RedBird >= 2 && GamePanel.RedBird <=4) {
                        if(ObstacleManeger.getScore() >= 50) {
                            GamePanel.Task8 = 1;
                        }
                    }
                    if(GamePanel.Task11 == 0 && GamePanel.RainbowBird >= 16) {
                            GamePanel.Task11 = 1;

                    }
                    if(GamePanel.Task12 == 0 && GamePanel.NitroBird >= 2) {
                        if(ObstacleManeger.getScore() >= 150) {
                            GamePanel.Task12 = 1;
                        }
                    }
                    //DailyTask
                    if(GamePanel.DailyTask1 == 0) {
                        if(ObstacleManeger.getScore() >= 50) {
                            GamePanel.DailyTask1 = 1;
                        }
                    }
                }

            }


            //ABILITY ACTIVATES
            if (GamePanel.YelowBird == 2 && GamePanel.RedBird >= 1) {
                if (ObstacleManeger.getScore() == 10) {
                    GamePanel.YelowBird =3;
                }
                if (ObstacleManeger.getScore() == 20) {
                    GamePanel.YelowBird =3;
                }
                if (ObstacleManeger.getScore() == 30) {
                    GamePanel.YelowBird =3;
                }
                if (ObstacleManeger.getScore() == 40) {
                    GamePanel.YelowBird =3;
                }
                if (ObstacleManeger.getScore() == 60) {
                    GamePanel.YelowBird =3;
                }
                if (ObstacleManeger.getScore() == 80) {
                    GamePanel.YelowBird =3;
                }
                if (ObstacleManeger.getScore() == 100) {
                    GamePanel.YelowBird =3;
                }
                if (ObstacleManeger.getScore() == 120) {
                    GamePanel.YelowBird =3;
                }
                if (ObstacleManeger.getScore() == 150) {
                    GamePanel.YelowBird =3;
                }
                if (ObstacleManeger.getScore() == 200) {
                    GamePanel.YelowBird =3;
                }

            }
            if (GamePanel.RedBird == 2) {
                if (ObstacleManeger.getScore() == 10) {
                    GamePanel.RedBird = 3;
                }
                if (ObstacleManeger.getScore() == 20) {
                    GamePanel.RedBird = 3;
                }
                if (ObstacleManeger.getScore() == 30) {
                    GamePanel.RedBird = 3;
                }
                if (ObstacleManeger.getScore() == 40) {
                    GamePanel.RedBird = 3;
                }
                if (ObstacleManeger.getScore() == 60) {
                    GamePanel.RedBird = 3;
                }
                if (ObstacleManeger.getScore() == 80) {
                    GamePanel.RedBird = 3;
                }
                if (ObstacleManeger.getScore() == 100) {
                    GamePanel.RedBird = 3;
                }
                if (ObstacleManeger.getScore() == 120) {
                    GamePanel.RedBird = 3;
                }
                if (ObstacleManeger.getScore() == 150) {
                    GamePanel.RedBird = 3;
                }
                if (ObstacleManeger.getScore() == 200) {
                    GamePanel.RedBird = 3;
                }
            }
            if (GamePanel.BlackBird == 2) {
                if (ObstacleManeger.getScore() == 10) {
                    GamePanel.BlackBird = 3;
                }
                if (ObstacleManeger.getScore() == 20) {
                    GamePanel.BlackBird = 3;
                }
                if (ObstacleManeger.getScore() == 30) {
                    GamePanel.BlackBird = 3;
                }
                if (ObstacleManeger.getScore() == 40) {
                    GamePanel.BlackBird = 3;
                }
                if (ObstacleManeger.getScore() == 60) {
                    GamePanel.BlackBird = 3;
                }
                if (ObstacleManeger.getScore() == 80) {
                    GamePanel.BlackBird = 3;
                }
                if (ObstacleManeger.getScore() == 100) {
                    GamePanel.BlackBird = 3;
                }
                if (ObstacleManeger.getScore() == 120) {
                    GamePanel.BlackBird = 3;
                }
                if (ObstacleManeger.getScore() == 150) {
                    GamePanel.BlackBird = 3;
                }
                if (ObstacleManeger.getScore() == 200) {
                    GamePanel.BlackBird = 3;
                }


            }

            if (GamePanel.PinkBird == 2) {
                if (ObstacleManeger.getScore() == 10) {
                    GamePanel.PinkBird = 3;
                }
                if (ObstacleManeger.getScore() == 20) {
                    GamePanel.PinkBird = 3;
                }
                if (ObstacleManeger.getScore() == 30) {
                    GamePanel.PinkBird = 3;
                }
                if (ObstacleManeger.getScore() == 40) {
                    GamePanel.PinkBird = 3;
                }
                if (ObstacleManeger.getScore() == 60) {
                    GamePanel.PinkBird = 3;
                }
                if (ObstacleManeger.getScore() == 80) {
                    GamePanel.PinkBird = 3;
                }
                if (ObstacleManeger.getScore() == 100) {
                    GamePanel.PinkBird = 3;
                }
                if (ObstacleManeger.getScore() == 120) {
                    GamePanel.PinkBird = 3;
                }
                if (ObstacleManeger.getScore() == 150) {
                    GamePanel.PinkBird = 3;
                }
                if (ObstacleManeger.getScore() == 200) {
                    GamePanel.PinkBird = 3;
                }
            }

            if (GamePanel.NinjaBird == 2) {
                if (ObstacleManeger.getScore() == 30) {
                    GamePanel.NinjaBird = 3;
                }
                if (ObstacleManeger.getScore() == 60) {
                    GamePanel.NinjaBird = 3;
                }
                if (ObstacleManeger.getScore() == 90) {
                    GamePanel.NinjaBird = 3;
                }
                if (ObstacleManeger.getScore() == 120) {
                    GamePanel.NinjaBird = 3;
                }
                if (ObstacleManeger.getScore() == 150) {
                    GamePanel.NinjaBird = 3;
                }
                if (ObstacleManeger.getScore() == 180) {
                    GamePanel.NinjaBird = 3;
                }
                if (ObstacleManeger.getScore() == 210) {
                    GamePanel.NinjaBird = 3;
                }
                if (ObstacleManeger.getScore() == 240) {
                    GamePanel.NinjaBird = 3;
                }
                if (ObstacleManeger.getScore() == 270) {
                    GamePanel.NinjaBird = 3;
                }
                if (ObstacleManeger.getScore() == 300) {
                    GamePanel.NinjaBird = 3;
                }

            }

            if (GamePanel.StrongBird == 2) {
                if (ObstacleManeger.getScore() == 20) {
                    GamePanel.StrongBird = 3;
                }
                if (ObstacleManeger.getScore() == 40) {
                    GamePanel.StrongBird = 3;
                }
                if (ObstacleManeger.getScore() == 60) {
                    GamePanel.StrongBird = 3;
                }
                if (ObstacleManeger.getScore() == 80) {
                    GamePanel.StrongBird = 3;
                }
                if (ObstacleManeger.getScore() == 100) {
                    GamePanel.StrongBird = 3;
                }
                if (ObstacleManeger.getScore() == 120) {
                    GamePanel.StrongBird = 3;
                }
                if (ObstacleManeger.getScore() == 140) {
                    GamePanel.StrongBird = 3;
                }
                if (ObstacleManeger.getScore() == 160) {
                    GamePanel.StrongBird = 3;
                }
                if (ObstacleManeger.getScore() == 180) {
                    GamePanel.StrongBird = 3;
                }
                if (ObstacleManeger.getScore() == 200) {
                    GamePanel.StrongBird = 3;
                }

            }

            if (GamePanel.DragonBird == 2) {
                if (ObstacleManeger.getScore() == 5) {
                    GamePanel.DragonBird = 3;
                }
                if (ObstacleManeger.getScore() == 20) {
                    GamePanel.DragonBird = 3;
                }
                if (ObstacleManeger.getScore() == 40) {
                    GamePanel.DragonBird = 3;
                }
                if (ObstacleManeger.getScore() == 60) {
                    GamePanel.DragonBird = 3;
                }
                if (ObstacleManeger.getScore() == 80) {
                    GamePanel.DragonBird = 3;
                }
                if (ObstacleManeger.getScore() == 100) {
                    GamePanel.DragonBird = 3;
                }
                if (ObstacleManeger.getScore() == 120) {
                    GamePanel.DragonBird = 3;
                }
                if (ObstacleManeger.getScore() == 140) {
                    GamePanel.DragonBird = 3;
                }
                if (ObstacleManeger.getScore() == 160) {
                    GamePanel.DragonBird = 3;
                }
                if (ObstacleManeger.getScore() == 180) {
                    GamePanel.DragonBird = 3;
                }
                if (ObstacleManeger.getScore() == 200) {
                    GamePanel.DragonBird = 3;
                }
            }

            if (GamePanel.RainbowBird >= 2) {
                if (ObstacleManeger.getScore() == 10) {
                    GamePanel.RainbowBird = 5;
                }
                if (ObstacleManeger.getScore() == 20) {
                    GamePanel.RainbowBird = 7;
                }
                if (ObstacleManeger.getScore() == 30) {
                    GamePanel.RainbowBird = 9;
                }
                if (ObstacleManeger.getScore() == 40) {
                    GamePanel.RainbowBird = 11;
                }
                if (ObstacleManeger.getScore() == 50) {
                    GamePanel.RainbowBird = 13;
                }
                if (ObstacleManeger.getScore() == 60) {
                    GamePanel.RainbowBird = 15;
                }
            }

            if (GamePanel.NitroBird == 2) {
                if (ObstacleManeger.getScore() == 25) {
                    GamePanel.NitroBird = 3;
                }
                if (ObstacleManeger.getScore() == 50) {
                    GamePanel.NitroBird = 3;
                }
                if (ObstacleManeger.getScore() == 75) {
                    GamePanel.NitroBird = 3;
                }
                if (ObstacleManeger.getScore() == 100) {
                    GamePanel.NitroBird = 3;
                }
                if (ObstacleManeger.getScore() == 125) {
                    GamePanel.NitroBird = 3;
                }
                if (ObstacleManeger.getScore() == 150) {
                    GamePanel.NitroBird = 3;
                }
                if (ObstacleManeger.getScore() == 175) {
                    GamePanel.NitroBird = 3;
                }
                if (ObstacleManeger.getScore() == 200) {
                    GamePanel.NitroBird = 3;
                }
                if (ObstacleManeger.getScore() == 250) {
                    GamePanel.NitroBird = 3;
                }
                if (ObstacleManeger.getScore() == 300) {
                    GamePanel.NitroBird = 3;
                }

            }

            if (GamePanel.LaserBird == 2) {
                if (ObstacleManeger.getScore() == 15) {
                    GamePanel.LaserBird = 3;
                }
                if (ObstacleManeger.getScore() == 30) {
                    GamePanel.LaserBird = 3;
                }
                if (ObstacleManeger.getScore() == 50) {
                    GamePanel.LaserBird = 3;
                }
                if (ObstacleManeger.getScore() == 70) {
                    GamePanel.LaserBird = 3;
                }
                if (ObstacleManeger.getScore() == 100) {
                    GamePanel.LaserBird = 3;
                }
                if (ObstacleManeger.getScore() == 130) {
                    GamePanel.LaserBird = 3;
                }
                if (ObstacleManeger.getScore() == 160) {
                    GamePanel.LaserBird = 3;
                }
                if (ObstacleManeger.getScore() == 200) {
                    GamePanel.LaserBird = 3;
                }
                if (ObstacleManeger.getScore() == 250) {
                    GamePanel.LaserBird = 3;
                }
                if (ObstacleManeger.getScore() == 300) {
                    GamePanel.LaserBird = 3;
                }

            }

            if (GamePanel.SageBird == 2) {
                if (ObstacleManeger.getScore() == 30) {
                    GamePanel.SageBird = 3;
                }
                if (ObstacleManeger.getScore() == 60) {
                    GamePanel.SageBird = 3;
                }
                if (ObstacleManeger.getScore() == 90) {
                    GamePanel.SageBird = 3;
                }
                if (ObstacleManeger.getScore() == 120) {
                    GamePanel.SageBird = 3;
                }
                if (ObstacleManeger.getScore() == 150) {
                    GamePanel.SageBird = 3;
                }
                if (ObstacleManeger.getScore() == 200) {
                    GamePanel.SageBird = 3;
                }
                if (ObstacleManeger.getScore() == 250) {
                    GamePanel.SageBird = 3;
                }
                if (ObstacleManeger.getScore() == 300) {
                    GamePanel.SageBird = 3;
                }

            }

            if (GamePanel.BombBird == 2) {
                if (ObstacleManeger.getScore() == 20) {
                    GamePanel.BombBird = 3;
                }
                if (ObstacleManeger.getScore() == 40) {
                    GamePanel.BombBird = 3;
                }
                if (ObstacleManeger.getScore() == 60) {
                    GamePanel.BombBird = 3;
                }
                if (ObstacleManeger.getScore() == 80) {
                    GamePanel.BombBird = 3;
                }
                if (ObstacleManeger.getScore() == 100) {
                    GamePanel.BombBird = 3;
                }
                if (ObstacleManeger.getScore() == 140) {
                    GamePanel.BombBird = 3;
                }
                if (ObstacleManeger.getScore() == 180) {
                    GamePanel.BombBird = 3;
                }
                if (ObstacleManeger.getScore() == 220) {
                    GamePanel.BombBird = 3;
                }
                if (ObstacleManeger.getScore() == 260) {
                    GamePanel.BombBird = 3;
                }
                if (ObstacleManeger.getScore() == 300) {
                    GamePanel.BombBird = 3;
                }

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

    private void drawCentreText8(Canvas canvas, Paint paint, String text) {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.getTextBounds(text, 0, text.length(), r);
        float x = cWidth / 2f - r.width() / 2f - r.left;
        float y = (cHeight / 2f + r.height() / 2f - r.bottom) - ((Constants.SCREEN_WIDTH/2 +(Constants.SCREEN_WIDTH/6)));
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
