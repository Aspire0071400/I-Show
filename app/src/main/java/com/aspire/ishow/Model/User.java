package com.aspire.ishow.Model;

public class User {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String bio;

    public User(String name, String bio, String email, String password) {
        this.name = name;
        this.bio = bio;
        this.email = email;
        this.password = password;
    }

    private String email;
    private String password;

}
