package com.aaroza.classroom.newui;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.aaroza.classroom.newui.dto.login.LoginRequestDto;
import com.aaroza.classroom.newui.dto.msg.MsgHistoryResponseDto;
import com.aaroza.classroom.newui.dto.msg.MsgRequestDto;
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
//                context.runOnUiThread(new Runnable(){
//                    @Override
//                    public void run(){
//                        MsgHistoryResponseDto msgHistoryResponseDto = new MsgHistoryResponseDto();
//                        msgHistoryResponseDto.setText(args[0].toString());
//                        msgHistoryResponseDto.setEmail(email);
//                        dtos.add( dtos.size(), msgHistoryResponseDto);
//                        binding.rvMsg.getAdapter().notifyItemInserted(dtos.size());
//                        binding.rvMsg.scrollToPosition(dtos.size() - 1);
//
//                        EventBus.getDefault().post(new MessageEvent());
//                    }
//                });
                EventBus.getDefault().post(dto);
                Log.e("msg", args[0].toString());

            }
        }).on("asdf", new Emitter.Listener(){
            @Override
            public void call(Object... args){

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
}
