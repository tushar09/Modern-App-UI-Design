package com.aaroza.classroom.newui.activity.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.aaroza.classroom.newui.R;

public class Login1Activity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);
        getSupportActionBar().hide();
    }
}
