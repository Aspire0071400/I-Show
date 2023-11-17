package com.aspire.ishow.Model;

public class HomiesModel {
    public HomiesModel() {
    }

    private String followedBy;

    public String getFollowedBy() {
        return followedBy;
    }

    public void setFollowedBy(String followedBy) {
        this.followedBy = followedBy;
    }

    public long getFollowedAt() {
        return followedAt;
    }

    public void setFollowedAt(long followedAt) {
        this.followedAt = followedAt;
    }

    private long followedAt;
}
