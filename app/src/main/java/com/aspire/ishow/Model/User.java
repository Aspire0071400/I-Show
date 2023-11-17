package com.aspire.ishow.Model;

public class User {
    private String name;
    private String userID;
    private String email;
    private String password;
    private String profession;
    private String about;
    private String coverPhoto;
    private String profile;
    private int followerCount;


    public User() {
    }

    public int getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(int followerCount) {this.followerCount = followerCount; }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }


    public String getCoverPhoto() {
        return coverPhoto;
    }

    public void setCoverPhoto(String coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
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

    public String getUserID() {return userID; }

    public void setUserID(String userID) { this.userID = userID; }


    public User(String name, String profession, String about, String email, String password) {
        this.name = name;
        this.profession = profession;
        this.about = about;
        this.email = email;
        this.password = password;

    }
}
