package com.hanium.android.mydata.ui.favApp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FavAppViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public FavAppViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is fav app fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}