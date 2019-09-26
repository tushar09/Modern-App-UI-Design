package com.aaroza.classroom.newui.views.login1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.aaroza.classroom.newui.R;

public class CircleView extends View{

    private Path line, line2, line3, line4;
    private Paint paint, paint2, paint3, paint4;

    private Context context;


    public CircleView(Context context, @Nullable AttributeSet attrs){
        super(context, attrs);
        line = new Path();
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);

        line2 = new Path();
        paint2 = new Paint();
        paint2.setStyle(Paint.Style.FILL);
        paint2.setAntiAlias(true);

        line3 = new Path();
        paint3 = new Paint();
        paint3.setStyle(Paint.Style.FILL);
        paint3.setAntiAlias(true);

        line4 = new Path();
        paint4 = new Paint();
        paint4.setStyle(Paint.Style.FILL);
        paint4.setAntiAlias(true);

        this.context = context;
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        // TODO Auto-generated method stub
        line = new Path();
        paint.setColor(Color.parseColor("#33CCCCCC"));
        //paint.setShader(new LinearGradient(0, 0, 0, getHeight(), Color.BLACK, Color.WHITE, Shader.TileMode.MIRROR));
        //paint.setShader(new LinearGradient(0, 0, 0, getHeight(), Color.parseColor("#AA56C7"), Color.parseColor("#E966B7"), Shader.TileMode.CLAMP));
        paint.setStrokeWidth(4);
        line.moveTo(20, 0);
        line.quadTo(getWidth() / 3, getHeight() / 3, getWidth(), (float) (getHeight() / 4));
        line.lineTo(getWidth(), 0);
        line.lineTo(0, 0);

        line2.moveTo((getWidth() / 2), 0);
        paint2.setColor(Color.parseColor("#33CCCCCC"));
        line2.quadTo((getWidth() / 2) + 30, (float) (getHeight() / 4), getWidth(), (float) (getHeight() / 2.4));
        line2.lineTo(getWidth(), 0);
        line2.lineTo(0, 0);

        line3.moveTo((float) (getWidth() / 1.2), 0);
        paint3.setColor(Color.parseColor("#33CCCCCC"));
        line3.quadTo((float) (getWidth() / 1.2), getHeight() / 2, 0, (float) (getHeight() / 2));
        line3.lineTo(0, 0);

        line4.moveTo(0, 0);
        paint4.setColor(Color.parseColor("#33CCCCCC"));
        line4.quadTo((float) (getWidth() / 8), getHeight() / 2, getWidth(), (float) (getHeight() / 2.3));
        line4.lineTo(getWidth(), 0);
        //line4.lineTo(0, 0);
        //line.close();

        //line.moveTo(getWidth() / 2, getHeight() / 2);
        //line.moveTo(0, getHeight());
        //line.close();

        canvas.drawPath(line, paint);
        canvas.drawPath(line2, paint2);
        canvas.drawPath(line3, paint3);
        canvas.drawPath(line4, paint4);
    }
}
