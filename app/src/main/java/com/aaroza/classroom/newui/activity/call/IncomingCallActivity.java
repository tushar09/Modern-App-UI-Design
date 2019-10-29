package com.aaroza.classroom.newui.activity.call;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.aaroza.classroom.newui.App;
import com.aaroza.classroom.newui.R;
import com.aaroza.classroom.newui.activity.ChatActivity;
import com.aaroza.classroom.newui.databinding.ActivityIncomingCalBinding;
import com.aaroza.classroom.newui.eventBus.CallDto;
import com.google.gson.Gson;

import static com.aaroza.classroom.newui.utils.Constants.EXTRA_ROOMID;

public class IncomingCallActivity extends AppCompatActivity{

    private ActivityIncomingCalBinding binding;

    private String name;

    private CallDto callDto;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        //finally change the color
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();

            // clear FLAG_TRANSLUCENT_STATUS flag:
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.transparent));
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = getWindow().getDecorView();
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        name = getIntent().getStringExtra("name");
        callDto = new Gson().fromJson(getIntent().getStringExtra("data"), CallDto.class);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_incoming_cal);
        getSupportActionBar().hide();

        binding.tvName.setText(callDto.getCaller());

        binding.fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.e("room", callDto.getRoomId() + "");
                App.callReceive(true, callDto.getCaller());
                Intent intent = new Intent(IncomingCallActivity.this, CallActivity.class);
                intent.putExtra(EXTRA_ROOMID, callDto.getRoomId());
                startActivityForResult(intent, 1);

            }
        });

    }
}
