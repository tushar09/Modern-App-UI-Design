package com.aaroza.classroom.newui.services;

import com.aaroza.classroom.newui.dto.login.LoginRequestDto;
import com.aaroza.classroom.newui.dto.login.LoginResponseDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService{
    @POST("users/createUser")
    Call<LoginResponseDto> signup(@Body LoginRequestDto dto);

    @POST("users/login")
    Call<LoginResponseDto> login(@Body LoginRequestDto dto);

}
