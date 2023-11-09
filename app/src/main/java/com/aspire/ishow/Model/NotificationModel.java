package com.aspire.ishow.Model;

public class NotificationModel {
    public int getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(int profileImg) {
        this.profileImg = profileImg;
    }

    public String getNotify() {
        return notify;
    }

    public void setNotify(String notify) {
        this.notify = notify;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    int profileImg;
    String notify,time;

    public NotificationModel(int profileIma, String notify, String time) {
        this.profileImg = profileIma;
        this.notify = notify;
        this.time = time;
    }
}
