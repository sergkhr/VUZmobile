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

public class NickNameFifth extends Fragment {

    private NickNameListViewModel mViewModel;
    private int index;

    public static NickNameFifth newInstance() {
        return new NickNameFifth();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nick_name_fifth, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(NickNameListViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        index = bundle.getInt("index");

        mViewModel.getNickNamesData().observe(getViewLifecycleOwner(), nickNamesModel -> {
            TextView numberView = (TextView) getView().findViewById(R.id.VM_element_number);
            TextView nameView = (TextView) getView().findViewById(R.id.VM_element_name);
            TextView descriptionView = (TextView) getView().findViewById(R.id.VM_element_description);

            numberView.setText(Integer.toString(nickNamesModel.getNickNames()[index].getNumber()));
            nameView.setText(nickNamesModel.getNickNames()[index].getName());
            descriptionView.setText(nickNamesModel.getNickNames()[index].getDescription());
        });

        Button backBtn = view.findViewById(R.id.VM_element_back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(NickNameFifth.this)
                        .navigate(R.id.action_nickNameFifth_to_nickNameFirst);
            }
        });

        Button updateDescriptionBtn = view.findViewById(R.id.VM_element_update_description_btn);
        updateDescriptionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView input = (TextView) getView().findViewById(R.id.VM_element_input);
                String description = input.getText().toString();
                mViewModel.updateElementDescription(index, description);
            }
        });
    }
}