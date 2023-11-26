package com.aspire.ishow.Model;

public class NotificationModel {
    private String notificationBy,type,postId,postedBy,notifyerID;
    private Long notificationAt;
    private boolean ckeckOpen;

    public String getNotificationBy() {
        return notificationBy;
    }

    public void setNotificationBy(String notificationBy) {
        this.notificationBy = notificationBy;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public Long getNotificationAt() {
        return notificationAt;
    }

    public void setNotificationAt(Long notificationAt) {
        this.notificationAt = notificationAt;
    }

    public boolean isCkeckOpen() {
        return ckeckOpen;
    }

    public void setCkeckOpen(boolean ckeckOpen) {
        this.ckeckOpen = ckeckOpen;
    }

    public String getNotifyerID() {
        return notifyerID;
    }

    public void setNotifyerID(String notifyerID) {
        this.notifyerID = notifyerID;
    }
}
