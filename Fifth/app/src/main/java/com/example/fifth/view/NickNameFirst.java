package com.example.fifth.view;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.fifth.ListClasses.MyListAdapter;
import com.example.fifth.R;
import com.example.fifth.viewModels.NickNameListViewModel;

import java.util.Arrays;

public class NickNameFirst extends Fragment {

    private NickNameListViewModel mViewModel;

    public static NickNameFirst newInstance() {
        return new NickNameFirst();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nick_name_first, container, false);
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
            MyListAdapter adapter = new MyListAdapter(getActivity(), R.layout.list_item_layout, Arrays.asList(nickNamesModel.getNickNames()));
            ListView listView = (ListView) getView().findViewById(R.id.VM_first_list);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener((parent, view1, position, id) -> {
                Bundle bundle = new Bundle();
                bundle.putInt("index", position);
                NavHostFragment.findNavController(NickNameFirst.this)
                        .navigate(R.id.action_nickNameFirst_to_nickNameFifth, bundle);
            });
        });

        Button backBtn = view.findViewById(R.id.VM_to_main_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(NickNameFirst.this)
                        .navigate(R.id.action_nickNameFirst_to_main);
            }
        });

        Button nextBtn = view.findViewById(R.id.VM_first_next_btn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(NickNameFirst.this)
                        .navigate(R.id.action_nickNameFirst_to_nickNameSecond);
            }
        });
    }
}