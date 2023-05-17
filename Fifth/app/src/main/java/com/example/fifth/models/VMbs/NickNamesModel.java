package com.example.fifth.models.VMbs;

import com.example.fifth.ListClasses.ListItem;

public class NickNamesModel {
    private final ListItem[] nickNames;

    public NickNamesModel(ListItem[] nickNames) {
        this.nickNames = nickNames;
    }

    public ListItem[] getNickNames() {
        return nickNames;
    }
}
