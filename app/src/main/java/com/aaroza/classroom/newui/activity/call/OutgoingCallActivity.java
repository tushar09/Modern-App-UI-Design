package com.aaroza.classroom.newui.activity.call;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.aaroza.classroom.newui.App;
import com.aaroza.classroom.newui.R;
import com.aaroza.classroom.newui.databinding.ActivityOutgoingCallBinding;
import com.aaroza.classroom.newui.dto.msg.MsgHistoryResponseDto;
import com.aaroza.classroom.newui.eventBus.CallDto;
import com.aaroza.classroom.newui.eventBus.MsgDto;
import com.aaroza.classroom.newui.utils.Constants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.webrtc.Camera1Enumerator;
import org.webrtc.CameraEnumerator;
import org.webrtc.VideoCapturer;

import static com.aaroza.classroom.newui.utils.Constants.EXTRA_ROOMID;

public class OutgoingCallActivity extends AppCompatActivity{
    private ActivityOutgoingCallBinding binding;

    private String name;
    private String email;
    private String roomId;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        //finally change the color
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();

            // clear FLAG_TRANSLUCENT_STATUS flag:
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.transparent));
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = getWindow().getDecorView();
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        binding = DataBindingUtil.setContentView(this, R.layout.activity_outgoing_call);
        getSupportActionBar().hide();

        email = getIntent().getStringExtra("name");
        roomId = Constants.getSPreferences().getEmail() + System.currentTimeMillis();


        CallDto callDto = new CallDto();
        callDto.setCaller(Constants.getSPreferences().getEmail());
        callDto.setRoomId(roomId);
        App.call(email, Constants.getSPreferences().getEmail(), roomId);

        binding.tvName.setText(email);

    }

    private VideoCapturer createVideoCapturer() {
        VideoCapturer videoCapturer;
        Log.e("Webrtc", "Creating capturer using camera1 API.");
        videoCapturer = createCameraCapturer(new Camera1Enumerator(false));

        return videoCapturer;
    }

    private VideoCapturer createCameraCapturer(CameraEnumerator enumerator) {
        final String[] deviceNames = enumerator.getDeviceNames();

        // First, try to find front facing camera
        Log.e("Webrtc", "Looking for front facing cameras.");
        for (String deviceName : deviceNames) {
            if (enumerator.isFrontFacing(deviceName)) {
                Log.e("Webrtc", "Creating front facing camera capturer.");
                VideoCapturer videoCapturer = enumerator.createCapturer(deviceName, null);

                if (videoCapturer != null) {
                    return videoCapturer;
                }
            }
        }

        // Front facing camera not found, try something else
        Log.e("Webrtc", "Looking for other cameras.");
        for (String deviceName : deviceNames) {
            if (!enumerator.isFrontFacing(deviceName)) {
                Log.e("Webrtc", "Creating other camera capturer.");
                VideoCapturer videoCapturer = enumerator.createCapturer(deviceName, null);

                if (videoCapturer != null) {
                    return videoCapturer;
                }
            }
        }

        return null;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(String event) {
        startActivity(new Intent(this, CallActivity.class)
                .putExtra(EXTRA_ROOMID, roomId)
        );
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
