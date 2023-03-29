package com.example.thirdprac;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainFragment extends Fragment {

    private Button toRelativeBtn;
    private Button toLinearBtn;
    private TextView textFromNewAct;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Toast.makeText(getActivity(), "MainFragment create", Toast.LENGTH_SHORT).show();
        Log.d("MainFragment", "onCreate");
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
        Toast.makeText(getActivity(), "MainFragment viewCreated", Toast.LENGTH_SHORT).show();
        Log.d("MainFragment", "onViewCreated");

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
        Toast.makeText(getActivity(), "MainFragment start", Toast.LENGTH_SHORT).show();
        Log.d("MainFragment", "onStart");
        getParentFragmentManager().setFragmentResultListener("requestKey2", this, (requestKey, result) -> {
            Log.d("MainFragment", "fragmentResult");
            String text = result.getString("text");
            textFromNewAct = (TextView) getView().findViewById(R.id.textView);
            textFromNewAct.setText(text);
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(getActivity(), "MainFragment resume", Toast.LENGTH_SHORT).show();
        Log.d("MainFragment", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Toast.makeText(getActivity(), "MainFragment pause", Toast.LENGTH_SHORT).show();
        Log.d("MainFragment", "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Toast.makeText(getActivity(), "MainFragment stop", Toast.LENGTH_SHORT).show();
        Log.d("MainFragment", "onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(getActivity(), "MainFragment destroy", Toast.LENGTH_SHORT).show();
        Log.d("MainFragment", "onDestroy");
    }
}