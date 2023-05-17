package com.example.fifth.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fifth.ListClasses.ListItem;
import com.example.fifth.models.VMbs.NickNamesModel;

public class NickNameFirstViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private final MutableLiveData<NickNamesModel> nickNamesData = new MutableLiveData(new NickNamesModel(new ListItem[0]));
    public LiveData<NickNamesModel> getNickNamesData() {
        return nickNamesData;
    }

    //there is a chance we'll need to implement an update method here
}