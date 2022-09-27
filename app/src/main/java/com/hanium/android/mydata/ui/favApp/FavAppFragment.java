package com.hanium.android.mydata.ui.favApp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.hanium.android.mydata.databinding.FragmentFavAppBinding;

public class FavAppFragment extends Fragment {

    private FavAppViewModel favAppViewModelViewModel;
    private FragmentFavAppBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        favAppViewModelViewModel =
                new ViewModelProvider(this).get(FavAppViewModel.class);

        binding = FragmentFavAppBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textFavApp;
        favAppViewModelViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
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