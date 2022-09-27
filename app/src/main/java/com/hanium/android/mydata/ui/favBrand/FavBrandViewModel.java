package com.hanium.android.mydata.ui.favBrand;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FavBrandViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public FavBrandViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is fav brand fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}