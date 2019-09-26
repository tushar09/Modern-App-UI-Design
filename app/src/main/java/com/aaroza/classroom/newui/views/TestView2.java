package com.aaroza.classroom.newui.views;

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

public class TestView2 extends View{

    private Path line;
    private Paint paint;

    private Context context;


    public TestView2(Context context, AttributeSet attributes){
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
        //paint.setColor(Color.parseColor(context.getString(R.color.dash_path)));
        paint.setShader(new LinearGradient(0, 0, 0, getHeight(), context.getColor(R.color.shadow_start), context.getColor(R.color.shadow_end), Shader.TileMode.MIRROR));
        paint.setShadowLayer(12, 0, 0, Color.YELLOW);
        //paint.setShader(new LinearGradient(0, 0, 0, getHeight(), Color.parseColor("#AA56C7"), Color.parseColor("#E966B7"), Shader.TileMode.MIRROR));
        paint.setStrokeWidth(4);
        line.lineTo(getWidth(), 0);
        line.lineTo(getWidth(), getHeight());
        line.lineTo(0, getHeight());
        line.lineTo(0, 0);
        line.close();
        //line.moveTo(0, getHeight());
        //line.close();

        canvas.drawPath(line, paint);
    }
}
