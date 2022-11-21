package com.hanium.android.mydata.ui.mypage;

import com.hanium.android.mydata.ui.benefit.BenefitModel;

import java.util.ArrayList;

public class Manager {
    private ArrayList<BenefitModel> data = new ArrayList<>();


    private Manager() {
        data.add(new BenefitModel("카페", "스타벅스", "쿠폰"));
    }

    private static Manager instance=new Manager(); //instance는 manager의 객체이름
    //3
    public static Manager getInstance() {
        return instance;
    }

    public void add(BenefitModel m){//메모한개 추가하는 작업
        data.add(m);
    }
    public void add(int index,BenefitModel m){data.add(index,m);}
    public BenefitModel get(int index){
        return data.get(index);
    }
    public void update(int index,BenefitModel m){
        data.set(index,m);
    }
    public void delete(int index){
        data.remove(index);
    }
//    public void set(BenefitModel m){m.setFavor(1);}
    public ArrayList<BenefitModel> getData() { //MemoList의 화면에 현재 시점의 data가 뿌려지도록
        return data;
    }
    public ArrayList<BenefitModel>getBenefitData(){
        ArrayList<BenefitModel> BenefitList=new ArrayList<>();
        for (BenefitModel benefit : data) {
            if(benefit.getFavor()==1)
                BenefitList.add(benefit);
        }
        for (BenefitModel benefit : data) {
            if(benefit.getFavor()==0)
                BenefitList.remove(benefit);
        }
        return BenefitList;
    }}
