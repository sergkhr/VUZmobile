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

public class NewActFragment extends Fragment {

    private TextView textFromMain;
    private Button backToMain;
    private TextView textToMain;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(getActivity(), "NewActFragment create", Toast.LENGTH_SHORT).show();
        Log.d("NewActFragment", "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_new_act, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toast.makeText(getActivity(), "NewActFragment viewCreated", Toast.LENGTH_SHORT).show();
        Log.d("NewActFragment", "onViewCreated");


        backToMain = (Button) getView().findViewById(R.id.backToMain);
        textToMain = (TextView) getView().findViewById(R.id.textToMain);

        backToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("text", textToMain.getText().toString());
                getParentFragmentManager().setFragmentResult("requestKey2", bundle);
                ((MainActivity) getActivity()).setFragment(new MainFragment());
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        Toast.makeText(getActivity(), "NewActFragment start", Toast.LENGTH_SHORT).show();
        Log.d("NewActFragment", "onStart");
        getParentFragmentManager().setFragmentResultListener("requestKey", this, (requestKey, result) -> {
            Log.d("NewActFragment", "FragmentResult");
            String text = result.getString("text");
            textFromMain = (TextView) getView().findViewById(R.id.newActText);
            textFromMain.setText(text);
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(getActivity(), "NewActFragment resume", Toast.LENGTH_SHORT).show();
        Log.d("NewActFragment", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Toast.makeText(getActivity(), "NewActFragment pause", Toast.LENGTH_SHORT).show();
        Log.d("NewActFragment", "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Toast.makeText(getActivity(), "NewActFragment stop", Toast.LENGTH_SHORT).show();
        Log.d("NewActFragment", "onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(getActivity(), "NewActFragment destroy", Toast.LENGTH_SHORT).show();
        Log.d("NewActFragment", "onDestroy");
    }
}