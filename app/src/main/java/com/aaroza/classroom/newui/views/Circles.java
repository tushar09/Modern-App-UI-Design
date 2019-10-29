package com.aaroza.classroom.newui.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.Random;

public class Circles extends View{

    private Paint paint;
    private Paint paint2;

    private float x = 0;
    private float y = 0;
    private float incnmtX = 0;
    private float incnmtY = 0;

    private float x1;
    private float y1;
    private float incnmtX1 = 0;
    private float incnmtY1 = 0;

    Path p;

    private Random rand;

    public Circles(Context context, @Nullable AttributeSet attrs){
        super(context, attrs);

        paint = new Paint();
        paint2 = new Paint();
        paint.setColor(Color.parseColor("#50CCCCCC"));
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);

        paint2.setColor(Color.parseColor("#50CCCCCC"));
        paint2.setStyle(Paint.Style.FILL);
        paint2.setAntiAlias(true);

        rand = new Random();

        incnmtX = getNewRandom();
        incnmtY = getNewRandom();

        incnmtX1 = getNewRandom();
        incnmtY1 = getNewRandom();

        x1 = 500;
        y1 = 800;



    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        //path.addCircle(x, y, 100, Path.Direction.CW);
        canvas.drawCircle(x, y, getWidth(), paint);
        canvas.drawCircle(x1, y1, getWidth() / 2, paint2);
        //canvas.drawPath(path, paint);

        Log.e("cast", x1 + " ----- " + y1);

        x += incnmtX;
        y += incnmtY;

        x1 += incnmtX1;
        y1 += incnmtY1;

        if(x > getWidth()){
            incnmtX = -getNewRandom();
        }
        if(x < 0){
            incnmtX = getNewRandom();
        }

        if(y > getHeight()){
            incnmtY = -getNewRandom();
        }
        if(y < 0){
            incnmtY = getNewRandom();
        }


        if(x1 > getWidth()){
            incnmtX1 = -getNewRandom();
        }
        if(x1 < 0){
            incnmtX1 = getNewRandom();
        }

        if(y1 > getHeight()){
            incnmtY1 = -getNewRandom();
        }
        if(y1 < 0){
            incnmtY1 = getNewRandom();
        }

        invalidate();
    }

    private float getNewRandom(){
        double d = 0.2 + (0.3 - 0.2) * rand.nextFloat();

        return (float) d;
    }

}
