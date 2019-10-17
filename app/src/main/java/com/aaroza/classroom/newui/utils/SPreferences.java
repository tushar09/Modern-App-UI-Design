package com.aaroza.classroom.newui.utils;

import android.content.Context;
import android.content.SharedPreferences;

/*
this class is used to handle all kind of data which is
stored in sharedpreferences.
 */
public class SPreferences{
    protected final static String IS_LOGGED_IN = "login";
    protected final static String EMAIL = "email";

    protected SharedPreferences sp;

    private Context context;

    public SPreferences(Context context){
        this.context = context;
        sp = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
    }


    public void setLoggedIn(boolean b){
        sp.edit().putBoolean(IS_LOGGED_IN, b).commit();
    }

    public boolean isLoggedIn(){
        return sp.getBoolean(IS_LOGGED_IN, false);
    }

    public void setEmail(String email){
        sp.edit().putString(EMAIL, email).commit();
    }

    public String getEmail(){
        return sp.getString(EMAIL, "n/a");
    }

}
