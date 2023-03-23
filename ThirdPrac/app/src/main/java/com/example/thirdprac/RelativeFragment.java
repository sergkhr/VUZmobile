package com.example.thirdprac;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class RelativeFragment extends Fragment {

    private Button toMainBtn;
    private Button toNewAct;
    private TextView textToNewAct;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_relative, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        toMainBtn = (Button) getView().findViewById(R.id.relativeBackBtn);
        toNewAct = (Button) getView().findViewById(R.id.toNewActBtn);
        textToNewAct = (TextView) getView().findViewById(R.id.textToNewAct);

        toMainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).setFragment(new MainFragment());
            }
        });

        toNewAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("text", textToNewAct.getText().toString());
                getParentFragmentManager().setFragmentResult("requestKey", bundle);
                ((MainActivity) getActivity()).setFragment(new NewActFragment());
            }
        });
    }
}