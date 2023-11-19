package com.aspire.ishow.Model;

public class FeedsModel {
    private String postId,postedBy,postImage,postDescription;
    private long postedAt;

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

    public String getPostImage() {
        return postImage;
    }

    public void setPostImage(String postImage) {
        this.postImage = postImage;
    }

    public String getPostDescription() {
        return postDescription;
    }

    public void setPostDescription(String postDescription) {
        this.postDescription = postDescription;
    }

    public long getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(long postedAt) {
        this.postedAt = postedAt;
    }

    public FeedsModel() {
    }

    public FeedsModel(String postId, String postedBy, String postImage, String postDescription, long postedAt) {
        this.postId = postId;
        this.postedBy = postedBy;
        this.postImage = postImage;
        this.postDescription = postDescription;
        this.postedAt = postedAt;
    }
}
