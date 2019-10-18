package com.aaroza.classroom.newui.services;

import com.aaroza.classroom.newui.adapter.UserListAdapter;
import com.aaroza.classroom.newui.dto.login.LoginRequestDto;
import com.aaroza.classroom.newui.dto.login.LoginResponseDto;
import com.aaroza.classroom.newui.dto.msg.MsgHistoryRequestDto;
import com.aaroza.classroom.newui.dto.msg.MsgHistoryResponseDto;
import com.aaroza.classroom.newui.dto.users.GetUsersRequestDto;
import com.aaroza.classroom.newui.dto.users.GetUsersResponseDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService{
    @POST("users/createUser")
    Call<LoginResponseDto> signup(@Body LoginRequestDto dto);

    @POST("users/login")
    Call<LoginResponseDto> login(@Body LoginRequestDto dto);

    @POST("users/getUsers")
    Call<List<GetUsersResponseDto>> getUsers(@Body GetUsersRequestDto dto);

    @POST("users/msgHistory")
    Call<List<MsgHistoryResponseDto>> getMsgHistory(@Body MsgHistoryRequestDto dto);
}
