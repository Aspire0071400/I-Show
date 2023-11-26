package com.aspire.ishow.Model;

import java.util.ArrayList;

public class StoriesModel {
    private String storyBy;
    private long storyAt;
    ArrayList<StoryViewModel> stories;

    public StoriesModel() {
    }

    public String getStoryBy() {
        return storyBy;
    }

    public void setStoryBy(String storyBy) {
        this.storyBy = storyBy;
    }

    public long getStoryAt() {
        return storyAt;
    }

    public void setStoryAt(long storyAt) {
        this.storyAt = storyAt;
    }

    public ArrayList<StoryViewModel> getStories() {
        return stories;
    }

    public void setStories(ArrayList<StoryViewModel> stories) {
        this.stories = stories;
    }
}
