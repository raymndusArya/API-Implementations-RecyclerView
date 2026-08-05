package com.example.api_recyclerview;


public class UserModel {

    int id;
    String nama;
    String username;
    String email;

    public UserModel(int id, String nama, String username, String email) {
        this.id = id;
        this.nama = nama;
        this.username = username;
        this.email = email;

    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
