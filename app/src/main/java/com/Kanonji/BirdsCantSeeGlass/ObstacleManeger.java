package com.Kanonji.BirdsCantSeeGlass;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Ibrahim on 21/06/2017.
 */

public class ObstacleManeger {

    Timer t = new Timer();

    private ArrayList<Obstcale> obstcales;
    public static int playerGap;
    public static int obstacleGap;
    private int obstacleHeight;
    private int color;
    private Rect r = new Rect();

    private long startTime;
    private long initTime;

    public static int myScore = 0; // Assigning a value;

    public static int myCoins = 0;


    public static int getScore()
    {
        return myScore;
    }

    public static int getMyCoins()
    {
        return myCoins;
    }


    public ObstacleManeger(int playerGap, int obstacleGap, int obstacleHeight, int color) {
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
        if (GamePanel.YelowBird == 2 || GamePanel.YelowBird == 3) {
            float speed = (float) (Math.sqrt(1 + (startTime - initTime) / 1500.0)) * Constants.SCREEN_HEIGHT / (15000.0f);
            for (Obstcale ob : obstcales) {
                ob.incrementY(speed * elapsedTime);
            }
        } else if (GamePanel.RedBird == 5) {
            float speed = (float) (Math.sqrt(1 + (startTime - initTime) / 3000.0)) * Constants.SCREEN_HEIGHT / (15000.0f);
            for (Obstcale ob : obstcales) {
                ob.incrementY(speed * elapsedTime);
            }
        } else if (GamePanel.RedBird == 2 || GamePanel.RedBird == 3) {
            float speed = (float) (Math.sqrt(1 + (startTime - initTime) / 2000.0)) * Constants.SCREEN_HEIGHT / (15000.0f);
            for (Obstcale ob : obstcales) {
                ob.incrementY(speed * elapsedTime);
            }
        } else if (GamePanel.RedBird == 4) {
            float speed = (float) (Math.sqrt(1 + (startTime - initTime) / 500.0)) * Constants.SCREEN_HEIGHT / (15000.0f);
            for (Obstcale ob : obstcales) {
                ob.incrementY(speed * elapsedTime);
            }
        } else if (GamePanel.BlackBird >= 2) {
            float speed = (float) (Math.sqrt(1 + (startTime - initTime) / 1500.0)) * Constants.SCREEN_HEIGHT / (15000.0f);
            for (Obstcale ob : obstcales) {
                ob.incrementY(speed * elapsedTime);
            }
        } else if (GamePanel.PinkBird >= 2) {
            float speed = (float) (Math.sqrt(1 + (startTime - initTime) / 1500.0)) * Constants.SCREEN_HEIGHT / (15000.0f);
            for (Obstcale ob : obstcales) {
                ob.incrementY(speed * elapsedTime);
            }
        } else if (GamePanel.NinjaBird == 2 || GamePanel.NinjaBird ==3) {
            float speed = (float) (Math.sqrt(1 + (startTime - initTime) / 1500.0)) * Constants.SCREEN_HEIGHT / (15000.0f);
            for (Obstcale ob : obstcales) {
                ob.incrementY(speed * elapsedTime);
            }
        } else if (GamePanel.NinjaBird == 4) {
            float speed = (float) (Math.sqrt(1 + (startTime - initTime) / 800.0)) * Constants.SCREEN_HEIGHT / (15000.0f);
            for (Obstcale ob : obstcales) {
                ob.incrementY(speed * elapsedTime);
            }
        } else if (GamePanel.DragonBird == 2 || GamePanel.DragonBird == 3) {
            float speed = (float) (Math.sqrt(1 + (startTime - initTime) / 1500.0)) * Constants.SCREEN_HEIGHT / (15000.0f);
            for (Obstcale ob : obstcales) {
                ob.incrementY(speed * elapsedTime);
            }
        } else if (GamePanel.DragonBird == 4) {
            float speed = (float) (Math.sqrt(1 + (startTime - initTime) / 1500.0)) * Constants.SCREEN_HEIGHT / (15000.0f);
            for (Obstcale ob : obstcales) {
                ob.incrementY(speed * elapsedTime);
            }
        }
        else if (GamePanel.RainbowBird >= 2) {
            float speed = (float) (Math.sqrt(1 + (startTime - initTime) / 2000.0)) * Constants.SCREEN_HEIGHT / (15000.0f);
            for (Obstcale ob : obstcales) {
                ob.incrementY(speed * elapsedTime);
            }
        } else if (GamePanel.StrongBird == 2 || GamePanel.StrongBird == 3) {
            float speed = (float) (Math.sqrt(1 + (startTime - initTime) / 1000.0)) * Constants.SCREEN_HEIGHT / (15000.0f);
            for (Obstcale ob : obstcales) {
                ob.incrementY(speed * elapsedTime);
            }
        } else if (GamePanel.StrongBird == 4) {
            float speed = (float) (Math.sqrt(1 + (startTime - initTime) / 1500.0)) * Constants.SCREEN_HEIGHT / (15000.0f);
            for (Obstcale ob : obstcales) {
                ob.incrementY(speed * elapsedTime);
            }
        } else if (GamePanel.NitroBird == 2 || GamePanel.NitroBird == 3 || GamePanel.NitroBird == 5) {
            float speed = (float) (Math.sqrt(1 + (startTime - initTime) / 1000.0)) * Constants.SCREEN_HEIGHT / (15000.0f);
            for (Obstcale ob : obstcales) {
                ob.incrementY(speed * elapsedTime);
            }
        } else if (GamePanel.NitroBird == 4) {
            float speed = (float) (Math.sqrt(1 + (startTime - initTime) / 100.0)) * Constants.SCREEN_HEIGHT / (15000.0f);
            for (Obstcale ob : obstcales) {
                ob.incrementY(speed * elapsedTime);
            }
        } else if (GamePanel.LaserBird == 2 || GamePanel.LaserBird == 3 || GamePanel.LaserBird == 5) {
            float speed = (float) (Math.sqrt(1 + (startTime - initTime) / 1000.0)) * Constants.SCREEN_HEIGHT / (15000.0f);
            for (Obstcale ob : obstcales) {
                ob.incrementY(speed * elapsedTime);
            }
        } else if (GamePanel.LaserBird == 4) {
            float speed = (float) (Math.sqrt(1 + (startTime - initTime) / 100.0)) * Constants.SCREEN_HEIGHT / (15000.0f);
            for (Obstcale ob : obstcales) {
                ob.incrementY(speed * elapsedTime);
            }
        } else if (GamePanel.SageBird == 2 || GamePanel.SageBird == 3 || GamePanel.SageBird == 5) {
            float speed = (float) (Math.sqrt(1 + (startTime - initTime) / 1500.0)) * Constants.SCREEN_HEIGHT / (15000.0f);
            for (Obstcale ob : obstcales) {
                ob.incrementY(speed * elapsedTime);
            }
        } else if (GamePanel.SageBird == 4) {
            float speed = (float) (Math.sqrt(1 + (startTime - initTime) / 8000.0)) * Constants.SCREEN_HEIGHT / (15000.0f);
            for (Obstcale ob : obstcales) {
                ob.incrementY(speed * elapsedTime);
            }
        } else if (GamePanel.BombBird >= 2) {
            float speed = (float) (Math.sqrt(1 + (startTime - initTime) / 1500.0)) * Constants.SCREEN_HEIGHT / (15000.0f);
            for (Obstcale ob : obstcales) {
                ob.incrementY(speed * elapsedTime);
            }
        }



        if (GamePanel.BlackBird == 4) {
            if (obstcales.get(obstcales.size() - 1).getRectangle().top >= Constants.SCREEN_HEIGHT) {
                int xStart = (int) (Math.random() * (Constants.SCREEN_WIDTH - playerGap));
                obstcales.add(0, new Obstcale(obstacleHeight, color, xStart, obstcales.get(0).getRectangle().top - obstacleGap * Constants.SCREEN_HEIGHT/800, playerGap));
                obstcales.remove(obstcales.size() - 1);
                myScore++;
                MainActivity.whoosh.start();
                GamePanel.ScoreCounter = 1;
            }

        } else if (GamePanel.PinkBird == 4) {
            if (obstcales.get(obstcales.size() - 1).getRectangle().top >= Constants.SCREEN_HEIGHT) {
                int xStart = (int) (Math.random() * (Constants.SCREEN_WIDTH - playerGap));
                obstcales.add(0, new Obstcale(obstacleHeight, color, xStart, obstcales.get(0).getRectangle().top - obstacleGap, playerGap * Constants.SCREEN_HEIGHT/800));
                obstcales.remove(obstcales.size() - 1);
                myScore++;
                MainActivity.whoosh.start();
                GamePanel.ScoreCounter = 1;
            }
        } else if (GamePanel.DragonBird == 2 || GamePanel.DragonBird == 3) {
            if (obstcales.get(obstcales.size() - 1).getRectangle().top >= Constants.SCREEN_HEIGHT) {
                int xStart = (int) (Math.random() * (Constants.SCREEN_WIDTH - playerGap));
                obstcales.add(0, new Obstcale(obstacleHeight, color, xStart, obstcales.get(0).getRectangle().top - obstacleGap, playerGap));
                obstcales.remove(obstcales.size() - 1);
                myScore++;
                MainActivity.whoosh.start();
                GamePanel.ScoreCounter = 1;
            }
        } else if (GamePanel.DragonBird == 4) {
            if (obstcales.get(obstcales.size() - 1).getRectangle().top >= Constants.SCREEN_HEIGHT) {
                int xStart = (int) (Math.random() * (Constants.SCREEN_WIDTH - playerGap));
                obstcales.add(0, new Obstcale(obstacleHeight, color, xStart, obstcales.get(0).getRectangle().top - obstacleGap * Constants.SCREEN_HEIGHT/800, playerGap * Constants.SCREEN_HEIGHT/800));
                obstcales.remove(obstcales.size() - 1);
                myScore++;
                MainActivity.whoosh.start();
                GamePanel.ScoreCounter = 1;
            }
        }else {
            if (obstcales.get(obstcales.size() - 1).getRectangle().top >= Constants.SCREEN_HEIGHT) {
                int xStart = (int) (Math.random() * (Constants.SCREEN_WIDTH - playerGap));
                obstcales.add(0, new Obstcale(obstacleHeight, color, xStart, obstcales.get(0).getRectangle().top - obstacleGap, playerGap));
                obstcales.remove(obstcales.size() - 1);
                myScore++;
                MainActivity.whoosh.start();
                GamePanel.ScoreCounter = 1;
            }
        }
    }

    public void draw(Canvas canvas) {
        Typeface myTypeface = Typeface.createFromAsset(Constants.CURRENT_CONTEXT.getAssets(), "chunk.ttf");
        for(Obstcale ob : obstcales)
            ob.draw(canvas);
        Paint paint = new Paint();
        paint.setTextSize(Constants.SCREEN_WIDTH / 10);
        paint.setTypeface(myTypeface);
        paint.setColor(Color.BLACK);
        drawCentreText4(canvas,paint, "" + myScore);

        if (GamePanel.ScoreCounter == 1) {
            paint.setTextSize(Constants.SCREEN_WIDTH / 14);
            paint.setTypeface(myTypeface);
            paint.setColor(Color.BLACK);
            canvas.drawText("+ 1", 0 + Constants.SCREEN_WIDTH/100 , Constants.SCREEN_HEIGHT - Constants.SCREEN_HEIGHT/100, paint);
            if (GamePanel.ScoreCounter == 1) {
                GamePanel.ScoreCounter = 0;
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
