package com.aaroza.classroom.newui.dto.msg;

public class MsgRequestDto{
    private String email;
    private String text;

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getText(){
        return text;
    }

    public void setText(String text){
        this.text = text;
    }
}
