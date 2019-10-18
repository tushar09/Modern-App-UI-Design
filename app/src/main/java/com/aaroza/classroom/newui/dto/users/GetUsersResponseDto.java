package com.aaroza.classroom.newui.dto.users;

public class GetUsersResponseDto{

    private String updated_at;
    private String created_at;
    private String socket_id;
    private String phone;
    private String email;
    private String name;
    private int id;

    public String getUpdated_at(){
        return updated_at;
    }

    public void setUpdated_at(String updated_at){
        this.updated_at = updated_at;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getCreated_at(){
        return created_at;
    }

    public void setCreated_at(String created_at){
        this.created_at = created_at;
    }

    public String getSocket_id(){
        return socket_id;
    }

    public void setSocket_id(String socket_id){
        this.socket_id = socket_id;
    }
}
