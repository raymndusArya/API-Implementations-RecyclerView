package com.example.api_recyclerview;


public class UserModel {

    int id;
    String nama;
    String username;
    String email;
    String address;
    String phone;
    String website;
    String company;
    String catchPhrase;
    String bs;
    String lat;
    String lng;


    public UserModel(int id, String nama, String username, String email, String address, String phone, String website,
                     String company, String catchPhrase, String bs, String lat, String lng
    ) {
        this.id = id;
        this.nama = nama;
        this.username = username;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.company = company;
        this.catchPhrase = catchPhrase;
        this.bs = bs;
        this.lat = lat;
        this.lng = lng;
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

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }

    public String getEmail() {
        return email;
    }

    public String getCompany() {
        return company;
    }

    public String getCatchPhrase() {
        return catchPhrase;
    }

    public String getBs() {
        return bs;
    }

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }
}


