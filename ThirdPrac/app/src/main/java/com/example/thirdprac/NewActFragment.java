package com.example.thirdprac;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class NewActFragment extends Fragment {

    private TextView textFromMain;
    private Button backToMain;
    private TextView textToMain;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        backToMain = (Button) getView().findViewById(R.id.backToMain);
        textToMain = (TextView) getView().findViewById(R.id.textToMain);

        backToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("text", textToMain.getText().toString());
                getParentFragmentManager().setFragmentResult("requestKey", bundle);
                ((MainActivity) getActivity()).setFragment(new MainFragment());
            }
        });

        return inflater.inflate(R.layout.fragment_new_act, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        getParentFragmentManager().setFragmentResultListener("requestKey", this, (requestKey, result) -> {
            String text = result.getString("text");
            textFromMain = (TextView) getView().findViewById(R.id.newActText);
        });
    }
}