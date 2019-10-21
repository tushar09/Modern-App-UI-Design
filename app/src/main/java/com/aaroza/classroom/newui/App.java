package com.aaroza.classroom.newui;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.aaroza.classroom.newui.activity.call.IncomingCallActivity;
import com.aaroza.classroom.newui.dto.login.LoginRequestDto;
import com.aaroza.classroom.newui.dto.msg.MsgHistoryResponseDto;
import com.aaroza.classroom.newui.dto.msg.MsgRequestDto;
import com.aaroza.classroom.newui.eventBus.CallDto;
import com.aaroza.classroom.newui.eventBus.CallReceive;
import com.aaroza.classroom.newui.eventBus.MsgDto;
import com.aaroza.classroom.newui.utils.Constants;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.engineio.client.transports.WebSocket;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.net.URISyntaxException;

public class App extends Application{

    public static Context context;

    IO.Options opts;
    public static Socket socket;


    @Override
    public void onCreate(){
        super.onCreate();
        context = this;
        SharedPreferences sp = getSharedPreferences("asdf", Context.MODE_PRIVATE);
        sp.edit().putString("email", "2222").commit();

        opts = new IO.Options();
        opts.transports = new String[]{
                WebSocket.NAME};
        try{
            socket = IO.socket(Constants.BASE_URL_SOCKET, opts);
        }catch(URISyntaxException e){
            e.printStackTrace();
        }
        socket.connect();
        userInfo();

        socket.on("msg", new Emitter.Listener(){
            @Override
            public void call(final Object... args){
                MsgDto dto = new Gson().fromJson(args[0].toString(), MsgDto.class);
                EventBus.getDefault().post(dto);
            }
        }).on("call", new Emitter.Listener(){
            @Override
            public void call(Object... args){
                startActivity(new Intent(context, IncomingCallActivity.class)
                        .putExtra("data", args[0].toString())
                );
                Log.e("deetcted", args[0].toString());
            }
        }).on("callReceive", new Emitter.Listener(){
            @Override
            public void call(Object... args){
                EventBus.getDefault().post("adfs");
            }
        });
    }

    public static void userInfo(){
        LoginRequestDto dto = new LoginRequestDto();
        dto.setName("name");
        dto.setEmail(Constants.getSPreferences().getEmail());
        dto.setPass("pass");
        socket.emit("userInfo", new Gson().toJson(dto));
    }

    public static void sendMsg(String email, String text){
        MsgRequestDto dto = new MsgRequestDto();
        dto.setEmail(email);
        dto.setSenderEmail(Constants.getSPreferences().getEmail());
        dto.setText(text);
        socket.emit("sendMsg", new Gson().toJson(dto));
    }


    public static void call(String receiver, String caller, String roomId){

        CallDto callDto = new CallDto();
        callDto.setRoomId(roomId);
        callDto.setCaller(caller);
        callDto.setReceiver(receiver);

        socket.emit("call", new Gson().toJson(callDto));
    }

    public static void callReceive(boolean isReceived, String caller){
        CallReceive callReceive = new CallReceive();
        callReceive.setReceived(isReceived);
        callReceive.setCaller(caller);
        socket.emit("callReceive", new Gson().toJson(callReceive));
    }

}
