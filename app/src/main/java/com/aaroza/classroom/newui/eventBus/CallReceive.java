package com.aaroza.classroom.newui.eventBus;

public class CallReceive{
    private boolean isReceived;
    private String caller;
    private String roomId;

    public boolean isReceived(){
        return isReceived;
    }

    public void setReceived(boolean received){
        isReceived = received;
    }

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
}
