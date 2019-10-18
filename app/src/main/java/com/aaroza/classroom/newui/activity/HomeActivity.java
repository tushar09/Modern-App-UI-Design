package com.aaroza.classroom.newui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.aaroza.classroom.newui.R;
import com.aaroza.classroom.newui.adapter.UserListAdapter;
import com.aaroza.classroom.newui.databinding.ActivityHomeBinding;
import com.aaroza.classroom.newui.dto.users.GetUsersRequestDto;
import com.aaroza.classroom.newui.dto.users.GetUsersResponseDto;
import com.aaroza.classroom.newui.utils.Constants;

import java.net.HttpURLConnection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity{

    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        GetUsersRequestDto dto = new GetUsersRequestDto(Constants.getSPreferences().getEmail());
        Constants.getApiService().getUsers(dto).enqueue(new Callback<List<GetUsersResponseDto>>(){
            @Override
            public void onResponse(Call<List<GetUsersResponseDto>> call, Response<List<GetUsersResponseDto>> response){
                if(response.code() == HttpURLConnection.HTTP_OK){
                    UserListAdapter uld = new UserListAdapter(response.body(), HomeActivity.this);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HomeActivity.this);
                    binding.rvUsers.setLayoutManager(linearLayoutManager);
                    binding.rvUsers.setAdapter(uld);
                }
            }

            @Override
            public void onFailure(Call<List<GetUsersResponseDto>> call, Throwable t){

            }
        });

    }
}
