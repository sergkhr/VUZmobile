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
import android.widget.Toast;

public class LinearFragment extends Fragment {

    private Button toMainBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(getActivity(), "LinearFragment create", Toast.LENGTH_SHORT).show();
        Log.d("LinearFragment", "onCreate");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_linear, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toast.makeText(getActivity(), "LinearFragment viewCreated", Toast.LENGTH_SHORT).show();
        Log.d("LinearFragment", "onViewCreated");

        toMainBtn = (Button) getView().findViewById(R.id.linearBackBtn);

        toMainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).setFragment(new MainFragment());
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        Toast.makeText(getActivity(), "LinearFragment start", Toast.LENGTH_SHORT).show();
        Log.d("LinearFragment", "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(getActivity(), "LinearFragment resume", Toast.LENGTH_SHORT).show();
        Log.d("LinearFragment", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Toast.makeText(getActivity(), "LinearFragment pause", Toast.LENGTH_SHORT).show();
        Log.d("LinearFragment", "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Toast.makeText(getActivity(), "LinearFragment stop", Toast.LENGTH_SHORT).show();
        Log.d("LinearFragment", "onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(getActivity(), "LinearFragment destroy", Toast.LENGTH_SHORT).show();
        Log.d("LinearFragment", "onDestroy");
    }
}