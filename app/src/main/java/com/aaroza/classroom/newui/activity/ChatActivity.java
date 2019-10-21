package com.aaroza.classroom.newui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.sip.SipAudioCall;
import android.net.sip.SipErrorCode;
import android.net.sip.SipException;
import android.net.sip.SipManager;
import android.net.sip.SipProfile;
import android.net.sip.SipRegistrationListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.aaroza.classroom.newui.App;
import com.aaroza.classroom.newui.R;
import com.aaroza.classroom.newui.activity.call.OutgoingCallActivity;
import com.aaroza.classroom.newui.activity.login.MainActivity;
import com.aaroza.classroom.newui.adapter.MsgHistoryAdapter;
import com.aaroza.classroom.newui.databinding.ActivityChatBinding;
import com.aaroza.classroom.newui.dto.login.LoginRequestDto;
import com.aaroza.classroom.newui.dto.msg.MsgHistoryRequestDto;
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
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatActivity extends AppCompatActivity{

    private ActivityChatBinding binding;
    private Socket socket;

    public SipManager sipManager = null;
    public SipProfile sipProfile = null;

    private String email;

    private MsgHistoryAdapter msgHistoryAdapter;
    private List<MsgHistoryResponseDto> dtos;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat);

        email = getIntent().getStringExtra("email");


        binding.send.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                App.sendMsg(email, binding.text.getText().toString());

                MsgHistoryResponseDto msgHistoryResponseDto = new MsgHistoryResponseDto();
                msgHistoryResponseDto.setText(binding.text.getText().toString());
                msgHistoryResponseDto.setEmail(Constants.getSPreferences().getEmail());
                dtos.add( dtos.size(), msgHistoryResponseDto);
                binding.rvMsg.getAdapter().notifyItemInserted(dtos.size());
                binding.rvMsg.scrollToPosition(dtos.size() - 1);
//                try{
//                    sipManager.makeAudioCall(sipProfile.getUriString(), "sip:" + binding.text.getText().toString() + "@192.168.10.5", new SipAudioCall.Listener(){
//                        @Override
//                        public void onError(SipAudioCall call, int errorCode, String errorMessage){
//                            super.onError(call, errorCode, errorMessage);
//                            Log.e("onError", errorCode + " " + errorMessage);
//                        }
//
//                        @Override
//                        public void onReadyToCall(SipAudioCall call){
//                            super.onReadyToCall(call);
//                            Log.e("onReadyToCall", "call");
//                        }
//
//                        @Override
//                        public void onCalling(SipAudioCall call){
//                            super.onCalling(call);
//                            Log.e("onCalling", "call");
//                        }
//
//                        @Override
//                        public void onRinging(SipAudioCall call, SipProfile caller){
//                            super.onRinging(call, caller);
//                            Log.e("onRinging", "call");
//                        }
//
//                        @Override
//                        public void onRingingBack(SipAudioCall call){
//                            super.onRingingBack(call);
//                            Log.e("onRingingBack", "call");
//                        }
//
//                        @Override
//                        public void onCallEstablished(SipAudioCall call){
//                            super.onCallEstablished(call);
//                            call.startAudio();
//                            call.setSpeakerMode(false);
//                            Log.e("onCallEstablished", "call");
//                        }
//
//                        @Override
//                        public void onCallEnded(SipAudioCall call){
//                            super.onCallEnded(call);
//                            Log.e("onCallEnded", "call");
//                        }
//
//                        @Override
//                        public void onCallBusy(SipAudioCall call){
//                            super.onCallBusy(call);
//                            Log.e("onCallBusy", "call");
//                        }
//
//                        @Override
//                        public void onCallHeld(SipAudioCall call){
//                            super.onCallHeld(call);
//                            Log.e("onCallHeld", "call");
//                        }
//
//                        @Override
//                        public void onChanged(SipAudioCall call){
//                            super.onChanged(call);
//                            Log.e("onChanged", "call");
//                        }
//                    }, 0);
//                }catch(SipException e){
//                    e.printStackTrace();
//                }
            }
        });

        binding.call.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(ChatActivity.this, OutgoingCallActivity.class)
                        .putExtra("name", email)
                );
            }
        });



//        if (sipManager == null) {
//            sipManager = SipManager.newInstance(this);
//        }
//
//        if (sipProfile != null){
//            closeLocalProfile();
//        }
//
//        SipProfile.Builder builder = null;
//        try{
//            builder = new SipProfile.Builder("qwe", "qwer");
//            builder.setPassword("qwer");
//            sipProfile = builder.build();
//
//            Intent intent = new Intent();
//            intent.setAction("android.SipDemo.INCOMING_CALL");
//            final PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, Intent.FILL_IN_DATA);
//            sipManager.open(sipProfile);
//            Log.e("register", "register");
//
//
//            //sipManager.register(sipProfile, 30, lis);
//            sipManager.setRegistrationListener(sipProfile.getUriString(), new SipRegistrationListener(){
//                @Override
//                public void onRegistering(String localProfileUri){
//                    Log.e("onRegistering", localProfileUri);
//                }
//
//                @Override
//                public void onRegistrationDone(String localProfileUri, long expiryTime){
//                    Log.e("onRegistrationDone", localProfileUri + " " + expiryTime);
//                }
//
//                @Override
//                public void onRegistrationFailed(String localProfileUri, int errorCode, String errorMessage){
//                    Log.e("onRegistrationFailed", localProfileUri + " " + errorCode + " " + errorMessage);
//                }
//            });
//
//            if(SipManager.isVoipSupported(this)){
//                Log.e("isVoipSupported", "true");
//            }else {
//                Log.e("isVoipSupported", "false");
//            }
//
//
//        }catch(ParseException e){
//            Log.e("ParseException", e.toString());
//            e.printStackTrace();
//        }catch(SipException e){
//            Log.e("SipException", e.toString());
//            e.printStackTrace();
//        }

        MsgHistoryRequestDto msgHistoryRequestDto = new MsgHistoryRequestDto();
        msgHistoryRequestDto.setEmail(Constants.getSPreferences().getEmail());
        msgHistoryRequestDto.setReceiverEmail(email);

        Constants.getApiService().getMsgHistory(msgHistoryRequestDto).enqueue(new Callback<List<MsgHistoryResponseDto>>(){
            @Override
            public void onResponse(Call<List<MsgHistoryResponseDto>> call, Response<List<MsgHistoryResponseDto>> response){
                dtos = response.body();
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ChatActivity.this);
                //linearLayoutManager.setReverseLayout(true);
                binding.rvMsg.setLayoutManager(linearLayoutManager);
                msgHistoryAdapter = new MsgHistoryAdapter(dtos, ChatActivity.this);
                binding.rvMsg.setAdapter(msgHistoryAdapter);
                binding.rvMsg.scrollToPosition(dtos.size() - 1);
            }

            @Override
            public void onFailure(Call<List<MsgHistoryResponseDto>> call, Throwable t){

            }
        });

    }

    public void closeLocalProfile() {
        if (sipManager == null) {
            return;
        }
        try {
            if (sipProfile != null) {
                sipManager.close(sipProfile.getUriString());
            }
        } catch (Exception ee) {
            Log.e("StatusWindow/onDestroy", "Failed to close local profile.", ee);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MsgDto event) {
        if(event.getSender().equals(email)){
            MsgHistoryResponseDto msgHistoryResponseDto = new MsgHistoryResponseDto();
            msgHistoryResponseDto.setText(event.getText());
            msgHistoryResponseDto.setEmail(email);
            dtos.add(dtos.size(), msgHistoryResponseDto);
            binding.rvMsg.getAdapter().notifyItemInserted(dtos.size());
            binding.rvMsg.scrollToPosition(dtos.size() - 1);
        }
    }

    @Override
    protected void onPostResume(){
        super.onPostResume();
        App.userInfo();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
