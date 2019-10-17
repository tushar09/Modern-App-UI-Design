package com.aaroza.classroom.newui.activity.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.aaroza.classroom.newui.R;
import com.aaroza.classroom.newui.activity.ChatActivity;
import com.aaroza.classroom.newui.databinding.ActivityMainBinding;
import com.aaroza.classroom.newui.dto.login.LoginRequestDto;
import com.aaroza.classroom.newui.dto.login.LoginResponseDto;
import com.aaroza.classroom.newui.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity{

    private ActivityMainBinding binding;

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

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        getSupportActionBar().hide();
        TextView tv = findViewById(R.id.tv_login);
        tv.setTextColor(Color.parseColor("#800CDD"));
        Shader textShader=new LinearGradient(0, 0, tv.getPaint().measureText("Login"), tv.getTextSize(),
                new int[]{Color.parseColor("#800CDD"), Color.parseColor("#3BA3F2")},
                new float[]{0, 1}, Shader.TileMode.CLAMP);
        tv.getPaint().setShader(textShader);

        findViewById(R.id.bt_login).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                login();
            }
        });

        binding.tvCreateAccount.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, SignupActivity.class));
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
                if(response.isSuccessful()){
                    startActivity(new Intent(MainActivity.this, ChatActivity.class)
                            .putExtra("email", dto.getEmail())
                    );
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
