package com.hanium.android.mydata;

import androidx.annotation.NonNull;

public class BenefitModel { //sample

    private String category;
    private String brandName;
    private String benefitKind;

    public BenefitModel(String category, String brandName, String benefitKind) {
        this.category = category;
        this.brandName = brandName;
        this.benefitKind = benefitKind;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBenefitKind() {
        return benefitKind;
    }

    public void setBenefitKind(String benefitKind) {
        this.benefitKind = benefitKind;
    }

    @Override
    public String toString() {
        return "benefit = " + brandName + ", " + category + ", " + benefitKind;
    }
}
