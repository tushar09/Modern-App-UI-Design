package com.aaroza.classroom.newui.views.login1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.aaroza.classroom.newui.R;

public class TestView extends View{

    private Path line;
    private Paint paint;

    private Context context;


    public TestView(Context context, AttributeSet attributes){
        super(context, attributes);
        line = new Path();
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);

        this.context = context;
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        line = new Path();
        paint.setColor(Color.parseColor(context.getString(R.color.dash_path)));
        //paint.setShader(new LinearGradient(0, 0, 0, getHeight(), Color.BLACK, Color.WHITE, Shader.TileMode.MIRROR));
        paint.setShader(new LinearGradient(0, 0, 0, getHeight(), Color.parseColor("#AA56C7"), Color.parseColor("#E966B7"), Shader.TileMode.CLAMP));
        paint.setStrokeWidth(4);
        line.moveTo(0, 0);
        line.cubicTo(0, (float) (getHeight() / .75), getWidth() / 2, (float) (getHeight() / 1.5), (int)(getWidth() / 1.2), getHeight());
        line.lineTo(0, getHeight());
        line.lineTo(0, 0);
        line.close();
        //line.moveTo(0, getHeight());
        //line.close();

        canvas.drawPath(line, paint);
    }
}
