package com.example.thirdprac;

public class ListItem {
    private String imageResource;
    private String text;

    public ListItem(String imageResource, String text) {
        this.imageResource = imageResource;
        this.text = text;
    }

    public ListItem(String text) {
        this.text = text;
        this.imageResource = "metodichka";
    }

    public String getImageResource() {
        return imageResource;
    }

    public void setImageResource(String imageResource) {
        this.imageResource = imageResource;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "ListItem{" +
                "imageResource='" + imageResource + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
