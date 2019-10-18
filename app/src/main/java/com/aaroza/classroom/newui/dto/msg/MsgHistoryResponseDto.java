package com.aaroza.classroom.newui.dto.msg;

public class MsgHistoryResponseDto{

    private String socket_id;
    private String phone;
    private String email;
    private String name;
    private String updated_at;
    private int receiver_id;
    private String text;
    private int sender_id;
    private int id;

    public String getSocket_id(){
        return socket_id;
    }

    public void setSocket_id(String socket_id){
        this.socket_id = socket_id;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getUpdated_at(){
        return updated_at;
    }

    public void setUpdated_at(String updated_at){
        this.updated_at = updated_at;
    }

    public int getReceiver_id(){
        return receiver_id;
    }

    public void setReceiver_id(int receiver_id){
        this.receiver_id = receiver_id;
    }

    public String getText(){
        return text;
    }

    public void setText(String text){
        this.text = text;
    }

    public int getSender_id(){
        return sender_id;
    }

    public void setSender_id(int sender_id){
        this.sender_id = sender_id;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }
}
