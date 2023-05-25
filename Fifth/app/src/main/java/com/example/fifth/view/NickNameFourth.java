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

public class NickNameFourth extends Fragment {

    private NickNameListViewModel mViewModel;

    public static NickNameFourth newInstance() {
        return new NickNameFourth();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nick_name_fourth, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(NickNameListViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button backBtn = view.findViewById(R.id.VM_fourth_back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(NickNameFourth.this)
                        .navigate(R.id.action_nickNameFourth_to_nickNameThird);
            }
        });

        Button deleteBtn = view.findViewById(R.id.VM_fourth_delete_btn);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView input = (TextView) getView().findViewById(R.id.VM_fourth_input);
                int index = Integer.parseInt(input.getText().toString());
                boolean objectDeleted = mViewModel.deleteNickNameById(index);
                TextView textView = (TextView) getView().findViewById(R.id.VM_fourth_text);
                if(objectDeleted){
                    textView.setText("nickname deleted");
                }
                else{
                    textView.setText("nickname not found");
                }
            }
        });
    }
}