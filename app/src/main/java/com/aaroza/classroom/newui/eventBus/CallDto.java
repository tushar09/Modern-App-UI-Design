package com.aaroza.classroom.newui.eventBus;

public class CallDto{
    private String caller;
    private String receiver;
    private String roomId;

    public String getCaller(){
        return caller;
    }

    public void setCaller(String caller){
        this.caller = caller;
    }

    public String getRoomId(){
        return roomId;
    }

    public void setRoomId(String roomId){
        this.roomId = roomId;
    }

    public String getReceiver(){
        return receiver;
    }

    public void setReceiver(String receiver){
        this.receiver = receiver;
    }
}
