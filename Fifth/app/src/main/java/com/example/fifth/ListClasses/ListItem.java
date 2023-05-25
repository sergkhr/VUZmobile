package com.example.fifth.ListClasses;

public class ListItem {
    private int number;
    private String name;
    private String description;

    public ListItem(int number, String name) {
        this.number = number;
        this.name = name;
        this.description = "";
    }

    public ListItem(int number, String name, String description) {
        this.number = number;
        this.name = name;
        this.description = description;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    @Override
    public String toString() {
        return "ListItem{" +
                "number='" + number + '\'' +
                ", text='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
