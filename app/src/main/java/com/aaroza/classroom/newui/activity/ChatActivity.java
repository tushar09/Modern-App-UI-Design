package com.aaroza.classroom.newui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.aaroza.classroom.newui.R;
import com.aaroza.classroom.newui.databinding.ActivityChatBinding;
import com.aaroza.classroom.newui.dto.login.LoginRequestDto;
import com.aaroza.classroom.newui.dto.msg.MsgRequestDto;
import com.aaroza.classroom.newui.utils.Constants;
import com.github.nkzawa.engineio.client.transports.WebSocket;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.google.gson.Gson;

import java.net.URISyntaxException;

public class ChatActivity extends AppCompatActivity{

    private ActivityChatBinding binding;
    private Socket socket;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat);

        IO.Options opts = new IO.Options();
        opts.transports = new String[]{WebSocket.NAME};

        try{
            LoginRequestDto dto = new LoginRequestDto();
            dto.setName("name");
            dto.setEmail(binding.receiver.getText().toString());
            dto.setPass("pass");
            socket = IO.socket(Constants.BASE_URL_SOCKET, opts);
            socket.emit("userInfo", new Gson().toJson(dto));
        }catch(URISyntaxException e){
            Log.e("connec err", e.toString());
            e.printStackTrace();
        }
        socket.connect();

        binding.send.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                MsgRequestDto dto = new MsgRequestDto();
                dto.setEmail(binding.receiver.getText().toString());
                dto.setText(binding.text.getText().toString());
                socket.emit("sendMsg", new Gson().toJson(dto));
            }
        });
    }
}
