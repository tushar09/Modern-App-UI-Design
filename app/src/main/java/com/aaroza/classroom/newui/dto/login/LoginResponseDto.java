package com.aaroza.classroom.newui.dto.login;

public class LoginResponseDto{

    private boolean success;
    private String msg;

    public boolean getSuccess(){
        return success;
    }

    public void setSuccess(boolean success){
        this.success = success;
    }

    public String getMsg(){
        return msg;
    }

    public void setMsg(String msg){
        this.msg = msg;
    }
}
