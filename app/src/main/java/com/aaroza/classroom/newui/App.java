package com.aaroza.classroom.newui;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class App extends Application{

    public static Context context;

    @Override
    public void onCreate(){
        super.onCreate();
        context = this;
        SharedPreferences sp = getSharedPreferences("asdf", Context.MODE_PRIVATE);
        sp.edit().putString("email", "2222").commit();
    }
}
