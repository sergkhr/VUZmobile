package com.example.fifth.view;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.fifth.R;
import com.example.fifth.viewModels.NickNameListViewModel;

public class NickNameThird extends Fragment {

    private NickNameListViewModel mViewModel;

    public static NickNameThird newInstance() {
        return new NickNameThird();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nick_name_third, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(NickNameListViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewModel.getNickNamesData().observe(getViewLifecycleOwner(), nickNamesModel -> {
            TextView textView = (TextView) getView().findViewById(R.id.VM_third_text);
            if(nickNamesModel.getNickNames().length == 0) {
                textView.setText("no nicknames added yet");
            }
            else{
                String text = "last added nickname:\n\n" + nickNamesModel.getNickNames()[nickNamesModel.getNickNames().length - 1].getName();
                textView.setText(text);
            }
        });

        Button backBtn = view.findViewById(R.id.VM_third_back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(NickNameThird.this)
                        .navigate(R.id.action_nickNameThird_to_nickNameSecond);
            }
        });

        Button nextBtn = view.findViewById(R.id.VM_third_next_btn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(NickNameThird.this)
                        .navigate(R.id.action_nickNameThird_to_nickNameFourth);
            }
        });

        Button addRandomBtn = view.findViewById(R.id.VM_third_add_random_btn);
        addRandomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.addRandomToNickNamesData();
            }
        });
    }
}