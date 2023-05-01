package com.example.fifth;

import android.content.SharedPreferences;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link first#newInstance} factory method to
 * create an instance of this fragment.
 */
public class first extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final String MY_SHARED_FILE_NAME = "mySharedFile";
    private static final String APP_PREFERENCES_NICKNAME = "nickname";

    private SharedPreferences mSettings;
    

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public first() {
        // Required empty public constructor
    }


    public static first newInstance(String param1, String param2) {
        first fragment = new first();
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

        mSettings = getActivity().getSharedPreferences(MY_SHARED_FILE_NAME, getContext().MODE_PRIVATE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            String income = bundle.getString("input");
            TextView title = view.findViewById(R.id.first_title);
            title.setText(income);
        }

        TextView input = view.findViewById(R.id.first_input);
        Button backBtn = view.findViewById(R.id.firstBackBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("input", input.getText().toString());
                NavHostFragment.findNavController(first.this)
                        .navigate(R.id.action_first_to_main, bundle);
            }
        });


        //working with shared preferences
        //input already found
        Button saveBtn = view.findViewById(R.id.save_shared_pref);
        Button loadBtn = view.findViewById(R.id.load_shared_pref);
        TextView  sharedPrefText = view.findViewById(R.id.info_from_shared);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = mSettings.edit();
                editor.putString(APP_PREFERENCES_NICKNAME, input.getText().toString());
                editor.apply();
            }
        });

        loadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSettings.contains(APP_PREFERENCES_NICKNAME)) {
                    String fromWhere = mSettings.toString();
                    String info = fromWhere + "\n\n" + mSettings.getString(APP_PREFERENCES_NICKNAME, "");
                    sharedPrefText.setText(info);
                }
            }
        });
    }
}