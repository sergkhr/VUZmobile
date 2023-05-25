package com.example.fifth.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fifth.ListClasses.ListItem;
import com.example.fifth.models.VMbs.NickNamesModel;

public class NickNameListViewModel extends ViewModel {

    //оказывается, методичка нагло врет, viewModel это один ко многим, а не на каждый свой
    //!!!! смотри не начни опять делать фигню


    private final MutableLiveData<NickNamesModel> nickNamesData = new MutableLiveData(new NickNamesModel(new ListItem[0]));
    public LiveData<NickNamesModel> getNickNamesData() {
        return nickNamesData;
    }

    //there is a chance we'll need to implement an update method here
    public void updateNickNamesData(ListItem[] nickNames) {
        nickNamesData.setValue(new NickNamesModel(nickNames));
    }

    public void addToNickNamesData(String newNickName){
        ListItem[] nickNames = nickNamesData.getValue().getNickNames();
        ListItem[] newNickNames = new ListItem[nickNames.length + 1];
        for(int i = 0; i < nickNames.length; i++){
            newNickNames[i] = nickNames[i];
        }
        // finding free number (yes O(n^2) but it's not a big deal)
        int newNumber = 0;
        boolean found = false;
        while(!found){
            found = true;
            for(int i = 0; i < newNickNames.length - 1; i++){
                if(newNickNames[i].getNumber() == newNumber){
                    found = false;
                    newNumber++;
                    break;
                }
            }
        }
        newNickNames[newNickNames.length - 1] = new ListItem(newNumber, newNickName);

        nickNamesData.setValue(new NickNamesModel(newNickNames));
    }

    public void addRandomToNickNamesData(){
        ListItem[] nickNames = nickNamesData.getValue().getNickNames();
        ListItem[] newNickNames = new ListItem[nickNames.length + 1];
        for(int i = 0; i < nickNames.length; i++){
            newNickNames[i] = nickNames[i];
        }
        // finding free number (yes O(n^2) but it's not a big deal)
        int newNumber = 0;
        boolean found = false;
        while(!found){
            found = true;
            for(int i = 0; i < newNickNames.length - 1; i++){
                if(newNickNames[i].getNumber() == newNumber){
                    found = false;
                    newNumber++;
                    break;
                }
            }
        }
        newNickNames[newNickNames.length - 1] = new ListItem(newNumber, generateRandomNickName());

        nickNamesData.setValue(new NickNamesModel(newNickNames));
    }

    private String generateRandomNickName(){
        String consonants = "bcdfghjklmnpqrstvwxz";
        String vowels = "aeiouy";
        String nickName = "";
        int length = (int)(Math.random() * (8-3)) + (1+3);
        for(int i = 0; i < length; i++){
            if(i % 2 == 0){
                nickName += consonants.charAt((int)(Math.random() * consonants.length()));
            } else {
                nickName += vowels.charAt((int)(Math.random() * vowels.length()));
            }
        }
        return nickName;
    }

    public boolean deleteNickNameById(int id){
        ListItem[] nickNames = nickNamesData.getValue().getNickNames();
        ListItem[] newNickNames = new ListItem[nickNames.length - 1];
        int foundIndex = -1;
        for(int i = 0; i < nickNames.length; i++){
            if(nickNames[i].getNumber() == id){
                foundIndex = i;
                break;
            }
        }
        if(foundIndex == -1){
            return false;
        }
        for(int i = 0; i < foundIndex; i++){
            newNickNames[i] = nickNames[i];
        }
        for(int i = foundIndex + 1; i < nickNames.length; i++){
            newNickNames[i - 1] = nickNames[i];
        }
        nickNamesData.setValue(new NickNamesModel(newNickNames));
        return true;
    }

    public void updateElementDescription(int index, String description){
        ListItem[] nickNames = nickNamesData.getValue().getNickNames();
        ListItem[] newNickNames = new ListItem[nickNames.length];
        for(int i = 0; i < nickNames.length; i++){
            if(i == index){
                newNickNames[i] = new ListItem(nickNames[i].getNumber(), nickNames[i].getName(), description);
            } else {
                newNickNames[i] = nickNames[i];
            }
        }
        nickNamesData.setValue(new NickNamesModel(newNickNames));
    }
}