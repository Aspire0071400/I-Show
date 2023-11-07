package com.aspire.ishow.Model;

public class FeedsModel {
    int profileDp,postImage,save;
    String username;
    String bio;
    String likes;
    String comment;

    public int getProfileDp() {
        return profileDp;
    }

    public void setProfileDp(int profileDp) {
        this.profileDp = profileDp;
    }

    public int getPostImage() {
        return postImage;
    }

    public void setPostImage(int postImage) {
        this.postImage = postImage;
    }

    public int getSave() {
        return save;
    }

    public void setSave(int save) {
        this.save = save;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getShare() {
        return share;
    }

    public void setShare(String share) {
        this.share = share;
    }

    String share;

    public FeedsModel(int profileDp, int postImage, int save, String username,
                      String bio, String likes, String comment, String share) {
        this.profileDp = profileDp;
        this.postImage = postImage;
        this.save = save;
        this.username = username;
        this.bio = bio;
        this.likes = likes;
        this.comment = comment;
        this.share = share;
    }
}
