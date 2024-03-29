package com.aaroza.classroom.newui.activity.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.aaroza.classroom.newui.R;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // finally change the color
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
//            Window window = getWindow();
//
//            // clear FLAG_TRANSLUCENT_STATUS flag:
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//
//            // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(ContextCompat.getColor(this, R.color.white));
//        }

        getSupportActionBar().hide();
        TextView tv = findViewById(R.id.tv_login);
        tv.setTextColor(Color.parseColor("#800CDD"));
        Shader textShader=new LinearGradient(0, 0, tv.getPaint().measureText("Login"), tv.getTextSize(),
                new int[]{Color.parseColor("#800CDD"), Color.parseColor("#3BA3F2")},
                new float[]{0, 1}, Shader.TileMode.CLAMP);
        tv.getPaint().setShader(textShader);

        findViewById(R.id.bt_login).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, Login1Activity.class));
            }
        });
    }
}
