package com.example.fifth;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link second#newInstance} factory method to
 * create an instance of this fragment.
 */
public class second extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final String APP_SPECIFIC_FILE_NAME = "myFile.txt";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public second() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment second.
     */
    // TODO: Rename and change types and number of parameters
    public static second newInstance(String param1, String param2) {
        second fragment = new second();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            String income = bundle.getString("input");
            TextView title = view.findViewById(R.id.second_title);
            title.setText(income);
        }

        Button backBtn = view.findViewById(R.id.secondBackBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(second.this)
                        .navigate(R.id.action_second_to_main);
            }
        });


        //work with app specific storage
        Button saveButton = view.findViewById(R.id.saveExternal);
        TextView inFileText = view.findViewById(R.id.textFromExternal);
        Button readButton = view.findViewById(R.id.readExternal);
        TextView input = view.findViewById(R.id.inputSecond);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                    android.content.pm.PackageManager.PERMISSION_GRANTED) {
                    File appSpecificFile = new File(Environment.getExternalStorageDirectory(), APP_SPECIFIC_FILE_NAME);
                    if(!appSpecificFile.exists()){
                        if(ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.MANAGE_EXTERNAL_STORAGE) ==
                                android.content.pm.PackageManager.PERMISSION_GRANTED) {
                            try {
                                appSpecificFile.createNewFile();
                                try {
                                    FileWriter writer = new FileWriter(appSpecificFile);
                                    writer.write(input.getText().toString());
                                    writer.close();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            } catch (Exception e) {
                                Log.e("ERROR", e.toString());
                            }
                        }
                        else{
                            getActivity().requestPermissions(new String[]{android.Manifest.permission.MANAGE_EXTERNAL_STORAGE}, 0);
                        }
                    }
                    else {
                        //write to file
                        try {
                            FileWriter writer = new FileWriter(appSpecificFile);
                            writer.write(input.getText().toString());
                            writer.close();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                else{
                    getActivity().requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
                }
            }
        });

        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.READ_EXTERNAL_STORAGE) ==
                        android.content.pm.PackageManager.PERMISSION_GRANTED) {
                    File appSpecificFile = new File(Environment.getExternalStorageDirectory(), APP_SPECIFIC_FILE_NAME);
                    if(appSpecificFile.exists()){
                        try {
                            String fromWhere = appSpecificFile.toString();
                            FileReader reader = new FileReader(appSpecificFile);
                            Scanner scanner = new Scanner(reader);
                            String text = fromWhere + "\n\n" + scanner.nextLine();
                            inFileText.setText(text);
                            reader.close();
                        } catch (Exception e) {
                            Log.e("ERROR", "Error reading file");
                        }
                    }
                }
                else{
                    getActivity().requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
                }
            }
        });
    }
}