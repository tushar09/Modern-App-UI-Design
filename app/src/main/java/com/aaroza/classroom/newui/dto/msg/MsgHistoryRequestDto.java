package com.aaroza.classroom.newui.dto.msg;

public class MsgHistoryRequestDto{
    private String email;
    private String receiverEmail;

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getReceiverEmail(){
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail){
        this.receiverEmail = receiverEmail;
    }
}
