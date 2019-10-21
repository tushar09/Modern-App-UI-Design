package com.aaroza.classroom.newui.activity.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.ContextCompat;
import androidx.core.util.Pair;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import com.aaroza.classroom.newui.R;
import com.aaroza.classroom.newui.activity.ChatActivity;
import com.aaroza.classroom.newui.activity.HomeActivity;
import com.aaroza.classroom.newui.activity.call.CallActivity;
import com.aaroza.classroom.newui.activity.call.IncomingCallActivity;
import com.aaroza.classroom.newui.databinding.ActivityMainBinding;
import com.aaroza.classroom.newui.dto.login.LoginRequestDto;
import com.aaroza.classroom.newui.dto.login.LoginResponseDto;
import com.aaroza.classroom.newui.utils.Constants;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.aaroza.classroom.newui.utils.Constants.EXTRA_ROOMID;

public class MainActivity extends AppCompatActivity{

    private ActivityMainBinding binding;

    private ConstraintLayout constraintLayout;
    private ConstraintSet constraintSet1 = new ConstraintSet();
    private ConstraintSet constraintSet2 = new ConstraintSet();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

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

        getWindow().setEnterTransition(null);
        getWindow().setExitTransition(null);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        constraintLayout = binding.activityMain;
        constraintSet1.clone(constraintLayout);
        constraintSet2.clone(this, R.layout.activity_signup);

        getSupportActionBar().hide();
        TextView tv = findViewById(R.id.tv_login);
        tv.setTextColor(Color.parseColor("#800CDD"));
        Shader textShader=new LinearGradient(0, 0, tv.getPaint().measureText("Login"), tv.getTextSize(),
                new int[]{Color.parseColor("#800CDD"), Color.parseColor("#3BA3F2")},
                new float[]{0, 1}, Shader.TileMode.CLAMP);
        tv.getPaint().setShader(textShader);

        if(Constants.getSPreferences().isLoggedIn()){
            startActivity(new Intent(MainActivity.this, HomeActivity.class)
                    .putExtra("email", Constants.getSPreferences().getEmail())
            );
//            startActivity(new Intent(MainActivity.this, CallActivity.class)
//                    .putExtra(EXTRA_ROOMID, "zxcvbnm")
//            );

//            Intent intent = new Intent(MainActivity.this, CallActivity.class);
//            intent.putExtra(EXTRA_ROOMID, "" + System.currentTimeMillis());
//            startActivityForResult(intent, 1);

        }

        findViewById(R.id.bt_login).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                login();
            }
        });

        binding.tvCreateAccount.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Pair<View, String> p1 = Pair.create((View)binding.btLogin, "btLogin");
                Pair<View, String> p2 = Pair.create((View)binding.tvLogin, "tvLogin");
                Pair<View, String> p3 = Pair.create((View)binding.etEmail, "etEmail");
                Pair<View, String> p4 = Pair.create((View)binding.etPassword, "etPass");
                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, p1, p2, p3, p4);
                Intent in = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(in,activityOptionsCompat.toBundle());
            }
        });

    }

    private void login(){
        final LoginRequestDto dto = new LoginRequestDto();
        dto.setEmail(binding.etEmail.getText().toString());
        dto.setName("adsf");
        dto.setPhone("1234");
        Constants.getApiService().login(dto).enqueue(new Callback<LoginResponseDto>(){
            @Override
            public void onResponse(Call<LoginResponseDto> call, Response<LoginResponseDto> response){
                if(response.code() == HttpURLConnection.HTTP_OK){
                    if(response.body().getSuccess()){
                        Constants.getSPreferences().setLoggedIn(true);
                        Constants.getSPreferences().setEmail(dto.getEmail());
                        startActivity(new Intent(MainActivity.this, ChatActivity.class)
                                .putExtra("email", dto.getEmail())
                        );
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponseDto> call, Throwable t){

            }
        });
    }

    private boolean isValidEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }
}
