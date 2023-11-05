package com.aspire.ishow.Model;

public class StoriesModel {
    int story,storyType,profileDP;
    String usersname;

    public int getStory() {
        return story;
    }

    public void setStory(int story) {
        this.story = story;
    }

    public int getStoryType() {
        return storyType;
    }

    public void setStoryType(int storyType) {
        this.storyType = storyType;
    }

    public int getProfileDP() {
        return profileDP;
    }

    public void setProfileDP(int profileDP) {
        this.profileDP = profileDP;
    }

    public String getUsersname() {
        return usersname;
    }

    public void setUsersname(String usersname) {
        this.usersname = usersname;
    }

    public StoriesModel(int story, int storyType, int profileDP, String usersname) {
        this.story = story;
        this.storyType = storyType;
        this.profileDP = profileDP;
        this.usersname = usersname;
    }
}
