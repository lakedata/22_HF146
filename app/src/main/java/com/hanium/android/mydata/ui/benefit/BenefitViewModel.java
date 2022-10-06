package com.hanium.android.mydata.ui.benefit;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BenefitViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public BenefitViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is benefit fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }


}