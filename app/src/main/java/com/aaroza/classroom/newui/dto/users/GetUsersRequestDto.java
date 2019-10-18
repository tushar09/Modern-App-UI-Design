package com.aaroza.classroom.newui.dto.users;

public class GetUsersRequestDto{
    private String email;

    public GetUsersRequestDto(String email){
        this.email = email;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }
}
