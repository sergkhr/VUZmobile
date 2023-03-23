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

public class MainFragment extends Fragment {

    private Button toRelativeBtn;
    private Button toLinearBtn;
    private TextView textFromNewAct;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        toRelativeBtn = (Button) getView().findViewById(R.id.toRelativeBtn);
        toLinearBtn = (Button) getView().findViewById(R.id.toLinearBtn);

        toRelativeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).setFragment(new RelativeFragment());
            }
        });

        toLinearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).setFragment(new LinearFragment());
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        getParentFragmentManager().setFragmentResultListener("requestKey", this, (requestKey, result) -> {
            String text = result.getString("text");
            textFromNewAct = (TextView) getView().findViewById(R.id.textView);
            textFromNewAct.setText(text);
        });
    }
}