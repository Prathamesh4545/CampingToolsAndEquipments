package com.example.campingtoolsandequipment.ui.mycard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyCardViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public MyCardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is My Card fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}