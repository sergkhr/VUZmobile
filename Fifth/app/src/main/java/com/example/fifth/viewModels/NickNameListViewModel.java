package com.example.fifth.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fifth.ListClasses.ListItem;
import com.example.fifth.models.VMbs.NickNamesModel;

public class NickNameListViewModel extends ViewModel {

    //оказывается, методичка нагло врет, viewModel это один ко многим, а не на каждый свой
    //!!!! смотри не начни опять делать фигню


    // TODO: Implement the ViewModel
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
}