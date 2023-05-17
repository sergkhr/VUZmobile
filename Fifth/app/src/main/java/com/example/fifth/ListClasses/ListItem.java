package com.example.fifth.ListClasses;

public class ListItem {
    private int number;
    private String text;

    public ListItem(int number, String text) {
        this.number = number;
        this.text = text;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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
                "number='" + number + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
