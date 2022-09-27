package com.hanium.android.mydata.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

        Button chromeBtn = binding.chromeBtn;
        chromeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String packageName = "com.android.chrome";
                Context context = (MainActivity2)getActivity();
                Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
                startActivity(intent);
            }
        });

        Button mainLoginBtn = binding.mainLoginBtn;
        mainLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentL = new Intent((MainActivity2)getActivity(), LoginActivity.class);
                startActivity(intentL);
            }
        });

        Button mainJoinBtn = binding.mainJoinBtn;
        mainLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentJ = new Intent((MainActivity2)getActivity(), JoinActivity.class);
                startActivity(intentJ);
            }
        });

        return root;
    }

//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.chromeBtn:
//                String packageName = "com.android.chrome";
//                Context context = MainActivity2.this;
//                Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
//                startActivity(intent);
//                break;
//            case R.id.mainLoginBtn:
//                Intent intentL = new Intent(MainActivity2.this, LoginActivity.class);
//                startActivity(intentL);
//                break;
//            case R.id.mainJoinBtn:
//                Intent intentJ = new Intent(MainActivity2.this, JoinActivity.class);
//                startActivity(intentJ);
//                break;
//        }
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}