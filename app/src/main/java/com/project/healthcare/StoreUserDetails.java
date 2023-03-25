package com.project.healthcare;

public class StoreUserDetails {

    public String username, mobile, email;

    public StoreUserDetails(String username, String mobile, String email) {
        this.username = username;
        this.mobile = mobile;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobils(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
