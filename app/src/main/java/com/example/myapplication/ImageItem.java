package com.example.myapplication;

public class ImageItem {
    private int imageResourceId;
    private String caption;

    public ImageItem(int imageResourceId, String caption) {
        this.imageResourceId = imageResourceId;
        this.caption = caption;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public String getCaption() {
        return caption;
    }
}
