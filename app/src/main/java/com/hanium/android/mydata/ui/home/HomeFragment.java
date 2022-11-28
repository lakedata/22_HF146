package com.hanium.android.mydata.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.hanium.android.mydata.MainActivity2;
import com.hanium.android.mydata.databinding.FragmentHomeBinding;
import com.hanium.android.mydata.ui.favApp.FavAppActivity;
import com.hanium.android.mydata.ui.favBrand.FavBrandActivity;
import com.hanium.android.mydata.ui.map.MapActivity;


public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });


        ImageView home_map = binding.icHomeMap;
        home_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent((MainActivity2) getActivity(), MapActivity.class);
                startActivity(intent);
            }
        });

        ImageView home_fav_brand = binding.icHomeFavBrand;
        home_fav_brand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentM = new Intent((MainActivity2) getActivity(), FavBrandActivity.class);
                startActivity(intentM);
            }
        });

        ImageView home_fav_app = binding.icHomeFavApp;
        home_fav_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentM = new Intent((MainActivity2) getActivity(), FavAppActivity.class);
                startActivity(intentM);
            }
        });

        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}