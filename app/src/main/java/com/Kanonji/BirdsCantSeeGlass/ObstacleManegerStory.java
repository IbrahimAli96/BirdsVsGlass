package com.Kanonji.BirdsCantSeeGlass;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Handler;

import java.util.ArrayList;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by Ibrahim on 21/06/2017.
 */

public class ObstacleManegerStory {

    Timer t = new Timer();

    private ArrayList<Obstcale> obstcales;
    public static int playerGap;
    public static int obstacleGap;
    private int obstacleHeight;
    private int color;
    private Rect r = new Rect();

    private long startTime;
    private long initTime;

    public static int myFeathers = 0; // Assigning a value;

    public static int myCoins = 0;

    public static int getFeathers()
    {
        return myFeathers;
    }

    public static int getMyCoins()
    {
        return myCoins;
    }


    public ObstacleManegerStory(int playerGap, int obstacleGap, int obstacleHeight, int color) {
        this.playerGap = playerGap;
        this.obstacleGap = obstacleGap;
        this.obstacleHeight = obstacleHeight;
        this.color = color;

        startTime = initTime = System.currentTimeMillis();

        obstcales = new ArrayList<>();

        populateObstacles();

    }

    public boolean playerCollide(RectPlayer player) {
        for(Obstcale ob : obstcales) {
            if(ob.playerCollide(player))
                return true;
        }
        return false;
    }

    private void populateObstacles() {
        int currY = -5*Constants.SCREEN_HEIGHT/4;
        while(currY < 0) {
            int xStart = (int) (Math.random() * (Constants.SCREEN_WIDTH - playerGap));
                obstcales.add(new Obstcale(obstacleHeight, color, xStart, currY, playerGap));

            currY += obstacleHeight + obstacleGap;

        }
    }

    public void update() {
        if (startTime < Constants.INIT_TIME)
            startTime = Constants.INIT_TIME;
        int elapsedTime = (int) (System.currentTimeMillis() - startTime);
        startTime = System.currentTimeMillis();

        if (GamePanel.StoryLevel == 0) {
            float speed = (float) (Math.sqrt(1 + (startTime - initTime) / 1500.0)) * Constants.SCREEN_HEIGHT / (15000.0f);
            for (Obstcale ob : obstcales) {
                ob.incrementY(speed * elapsedTime);
            }
        } else if (GamePanel.StoryLevel == 1) {
            float speed = (float) (Math.sqrt(1 + (startTime - initTime) / 1000.0)) * Constants.SCREEN_HEIGHT / (15000.0f);
            for (Obstcale ob : obstcales) {
                ob.incrementY(speed * elapsedTime);
            }
        } else if (GamePanel.StoryLevel == 2) {
            float speed = (float) (Math.sqrt(1 + (startTime - initTime) / 400.0)) * Constants.SCREEN_HEIGHT / (15000.0f);
            for (Obstcale ob : obstcales) {
                ob.incrementY(speed * elapsedTime);
            }
        } else if (GamePanel.StoryLevel == 3) {
            float speed = (float) (Math.sqrt(1 + (startTime - initTime) / 1000.0)) * Constants.SCREEN_HEIGHT / (15000.0f);
            for (Obstcale ob : obstcales) {
                ob.incrementY(speed * elapsedTime);
            }
        } else if (GamePanel.StoryLevel == 4) {
            float speed = (float) (Math.sqrt(1 + (startTime - initTime) / 200.0)) * Constants.SCREEN_HEIGHT / (15000.0f);
            for (Obstcale ob : obstcales) {
                ob.incrementY(speed * elapsedTime);
            }
        } else if (GamePanel.StoryLevel == 5) {
            float speed = (float) (Math.sqrt(1 + (startTime - initTime) / 500.0)) * Constants.SCREEN_HEIGHT / (15000.0f);
            for (Obstcale ob : obstcales) {
                ob.incrementY(speed * elapsedTime);
            }
        } else if (GamePanel.StoryLevel == 6) {
            float speed = (float) (Math.sqrt(1 + (startTime - initTime) / 2000.0)) * Constants.SCREEN_HEIGHT / (15000.0f);
            for (Obstcale ob : obstcales) {
                ob.incrementY(speed * elapsedTime);
            }
        } else if (GamePanel.StoryLevel == 7) {
            float speed = (float) (Math.sqrt(1 + (startTime - initTime) / 1500.0)) * Constants.SCREEN_HEIGHT / (15000.0f);
            for (Obstcale ob : obstcales) {
                ob.incrementY(speed * elapsedTime);
            }
        } else if (GamePanel.StoryLevel == 8) {
            float speed = (float) (Math.sqrt(1 + (startTime - initTime) / 200.0)) * Constants.SCREEN_HEIGHT / (15000.0f);
            for (Obstcale ob : obstcales) {
                ob.incrementY(speed * elapsedTime);
            }
        } else if (GamePanel.StoryLevel == 9) {
            float speed = (float) (Math.sqrt(1 + (startTime - initTime) / 1000.0)) * Constants.SCREEN_HEIGHT / (15000.0f);
            for (Obstcale ob : obstcales) {
                ob.incrementY(speed * elapsedTime);
            }
        }

        //LEVEL 10 >
        if (GamePanel.StoryLevel == 10) {
            float speed = (float) (Math.sqrt(1 + (startTime - initTime) / 1000.0)) * Constants.SCREEN_HEIGHT / (15000.0f);
            for (Obstcale ob : obstcales) {
                ob.incrementY(speed * elapsedTime);
            }
        } else if (GamePanel.StoryLevel == 11) {
            float speed = (float) (Math.sqrt(1 + (startTime - initTime) / 800.0)) * Constants.SCREEN_HEIGHT / (15000.0f);
            for (Obstcale ob : obstcales) {
                ob.incrementY(speed * elapsedTime);
            }
        } else if (GamePanel.StoryLevel == 12) {
            float speed = (float) (Math.sqrt(1 + (startTime - initTime) / 200.0)) * Constants.SCREEN_HEIGHT / (15000.0f);
            for (Obstcale ob : obstcales) {
                ob.incrementY(speed * elapsedTime);
            }
        } else if (GamePanel.StoryLevel == 13) {
            float speed = (float) (Math.sqrt(1 + (startTime - initTime) / 800.0)) * Constants.SCREEN_HEIGHT / (15000.0f);
            for (Obstcale ob : obstcales) {
                ob.incrementY(speed * elapsedTime);
            }
        } else if (GamePanel.StoryLevel == 14) {
            float speed = (float) (Math.sqrt(1 + (startTime - initTime) / 100.0)) * Constants.SCREEN_HEIGHT / (15000.0f);
            for (Obstcale ob : obstcales) {
                ob.incrementY(speed * elapsedTime);
            }
        } else if (GamePanel.StoryLevel == 15) {
            float speed = (float) (Math.sqrt(1 + (startTime - initTime) / 200.0)) * Constants.SCREEN_HEIGHT / (15000.0f);
            for (Obstcale ob : obstcales) {
                ob.incrementY(speed * elapsedTime);
            }
        } else if (GamePanel.StoryLevel == 16) {
            float speed = (float) (Math.sqrt(1 + (startTime - initTime) / 1000.0)) * Constants.SCREEN_HEIGHT / (15000.0f);
            for (Obstcale ob : obstcales) {
                ob.incrementY(speed * elapsedTime);
            }
        } else if (GamePanel.StoryLevel == 17) {
            float speed = (float) (Math.sqrt(1 + (startTime - initTime) / 500.0)) * Constants.SCREEN_HEIGHT / (15000.0f);
            for (Obstcale ob : obstcales) {
                ob.incrementY(speed * elapsedTime);
            }
        } else if (GamePanel.StoryLevel == 18) {
            float speed = (float) (Math.sqrt(1 + (startTime - initTime) / 100.0)) * Constants.SCREEN_HEIGHT / (15000.0f);
            for (Obstcale ob : obstcales) {
                ob.incrementY(speed * elapsedTime);
            }
        } else if (GamePanel.StoryLevel == 19) {
            float speed = (float) (Math.sqrt(1 + (startTime - initTime) / 600.0)) * Constants.SCREEN_HEIGHT / (15000.0f);
            for (Obstcale ob : obstcales) {
                ob.incrementY(speed * elapsedTime);
            }
        }



        if (obstcales.get(obstcales.size() - 1).getRectangle().top >= Constants.SCREEN_HEIGHT) {
            int xStart = (int) (Math.random() * (Constants.SCREEN_WIDTH - playerGap));
            obstcales.add(0, new Obstcale(obstacleHeight, color, xStart, obstcales.get(0).getRectangle().top - obstacleGap, playerGap));
            obstcales.remove(obstcales.size() - 1);

            if (GamePanel.StoryLevel >= 5 && GamePanel.StoryLevel <= 9) {
                myFeathers ++;
                myFeathers ++;
            } else if (GamePanel.StoryLevel >= 10 && GamePanel.StoryLevel <= 14) {
                myFeathers ++;
                myFeathers ++;
                myFeathers ++;
            } else if (GamePanel.StoryLevel >= 15) {
                myFeathers ++;
                myFeathers ++;
                myFeathers ++;
                myFeathers ++;
            }
            else {
                myFeathers++;
            }
            MainActivity.whoosh.start();
            GamePanel.HiddenStoryScore++;
            GamePanel.ScoreCounter = 1;

        }


    }

    public void draw(Canvas canvas) {
        Typeface myTypeface = Typeface.createFromAsset(Constants.CURRENT_CONTEXT.getAssets(), "chunk.ttf");
        for(Obstcale ob : obstcales)
            ob.draw(canvas);
        Paint paint = new Paint();

        if (GamePanel.StoryLevel >=5 && GamePanel.StoryLevel <= 9) {
        if (GamePanel.ScoreCounter == 1) {
            paint.setTextSize(Constants.SCREEN_WIDTH / 14);
            paint.setTypeface(myTypeface);
            paint.setColor(Color.YELLOW);
            canvas.drawText("+ 2 SP", 0 + Constants.SCREEN_WIDTH/100 , Constants.SCREEN_HEIGHT - Constants.SCREEN_HEIGHT/100, paint);
            if (GamePanel.ScoreCounter == 1) {
                GamePanel.ScoreCounter = 0;
            }
        }} if (GamePanel.StoryLevel >= 10 && GamePanel.StoryLevel <= 14) {
            if (GamePanel.ScoreCounter == 1) {
                paint.setTextSize(Constants.SCREEN_WIDTH / 14);
                paint.setTypeface(myTypeface);
                paint.setColor(Color.WHITE);
                canvas.drawText("+ 3 SP", 0 + Constants.SCREEN_WIDTH/100 , Constants.SCREEN_HEIGHT - Constants.SCREEN_HEIGHT/100, paint);
                if (GamePanel.ScoreCounter == 1) {
                    GamePanel.ScoreCounter = 0;
                }
            }} if (GamePanel.StoryLevel >=15) {
            if (GamePanel.ScoreCounter == 1) {
                paint.setTextSize(Constants.SCREEN_WIDTH / 14);
                paint.setTypeface(myTypeface);
                paint.setColor(Color.BLUE);
                canvas.drawText("+ 4 SP", 0 + Constants.SCREEN_WIDTH/100 , Constants.SCREEN_HEIGHT - Constants.SCREEN_HEIGHT/100, paint);
                if (GamePanel.ScoreCounter == 1) {
                    GamePanel.ScoreCounter = 0;
                }
            }} else {
            if (GamePanel.ScoreCounter == 1) {
                paint.setTextSize(Constants.SCREEN_WIDTH / 14);
                paint.setTypeface(myTypeface);
                paint.setColor(Color.RED);
                canvas.drawText("+ 1 SP", 0 + Constants.SCREEN_WIDTH/100 , Constants.SCREEN_HEIGHT - Constants.SCREEN_HEIGHT/100, paint);
                if (GamePanel.ScoreCounter == 1) {
                    GamePanel.ScoreCounter = 0;
                }
            }
        }

    }

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




}
