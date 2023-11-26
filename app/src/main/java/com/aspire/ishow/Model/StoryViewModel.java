package com.aspire.ishow.Model;

public class StoryViewModel {
    private String image;
    private long storyAt;


    public StoryViewModel() {
    }

    public StoryViewModel(String image, long storyAt) {
        this.image = image;
        this.storyAt = storyAt;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getStoryAt() {
        return storyAt;
    }

    public void setStoryAt(long storyAt) {
        this.storyAt = storyAt;
    }
}
