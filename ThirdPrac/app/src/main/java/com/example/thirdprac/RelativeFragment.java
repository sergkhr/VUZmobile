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

public class RelativeFragment extends Fragment {

    private Button toMainBtn;
    private Button toNewAct;
    private TextView textToNewAct;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Toast.makeText(getActivity(), "RelativeFragment create", Toast.LENGTH_SHORT).show();
        Log.d("RelativeFragment", "onCreate");
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
        Toast.makeText(getActivity(), "RelativeFragment viewCreated", Toast.LENGTH_SHORT).show();
        Log.d("RelativeFragment", "onViewCreated");

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

    @Override
    public void onStart() {
        super.onStart();
        Toast.makeText(getActivity(), "RelativeFragment start", Toast.LENGTH_SHORT).show();
        Log.d("RelativeFragment", "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(getActivity(), "RelativeFragment resume", Toast.LENGTH_SHORT).show();
        Log.d("RelativeFragment", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Toast.makeText(getActivity(), "RelativeFragment pause", Toast.LENGTH_SHORT).show();
        Log.d("RelativeFragment", "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Toast.makeText(getActivity(), "RelativeFragment stop", Toast.LENGTH_SHORT).show();
        Log.d("RelativeFragment", "onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(getActivity(), "RelativeFragment destroy", Toast.LENGTH_SHORT).show();
        Log.d("RelativeFragment", "onDestroy");
    }
}