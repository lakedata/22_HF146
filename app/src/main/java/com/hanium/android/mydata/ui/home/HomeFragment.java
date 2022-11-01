package com.hanium.android.mydata.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.hanium.android.mydata.JoinActivity;
import com.hanium.android.mydata.LoginActivity;
import com.hanium.android.mydata.MainActivity2;
import com.hanium.android.mydata.R;
import com.hanium.android.mydata.SearchActivity;
import com.hanium.android.mydata.XAMPPTestActivity;
import com.hanium.android.mydata.databinding.FragmentHomeBinding;


public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        // login
        Button mainLoginBtn = binding.mainLoginBtn;
        mainLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentL = new Intent(getActivity(), LoginActivity.class);
                startActivity(intentL);
            }
        });


        // join
        Button mainJoinBtn = binding.mainJoinBtn;
        mainJoinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentJ = new Intent(getActivity(), JoinActivity.class);
                startActivity(intentJ);
            }
        });

        // search
        ImageView searchBtn = binding.searchBtn;
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentS = new Intent(getActivity(), SearchActivity.class);
                startActivity(intentS);
            }
        });

        // xampp test
        Button xampp = binding.xampptest;
        xampp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentX = new Intent(getActivity(), XAMPPTestActivity.class);
                startActivity(intentX);
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