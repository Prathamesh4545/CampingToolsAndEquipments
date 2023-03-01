package com.example.campingtoolsandequipment.ui.allcategories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AllCategoriesViewModel extends ViewModel  {

    private MutableLiveData<String> mText;

    public AllCategoriesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is All Categories fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}