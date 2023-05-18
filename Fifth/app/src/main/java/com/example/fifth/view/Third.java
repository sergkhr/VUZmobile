package com.example.fifth.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.fifth.models.roomPackage.AppDatabase;
import com.example.fifth.models.roomPackage.Cat;
import com.example.fifth.models.roomPackage.CatDao;
import com.example.fifth.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Third#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Third extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Third() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Third.
     */
    // TODO: Rename and change types and number of parameters
    public static Third newInstance(String param1, String param2) {
        Third fragment = new Third();
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
        return inflater.inflate(R.layout.fragment_trird, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button backBtn = view.findViewById(R.id.third_back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(Third.this)
                        .navigate(R.id.action_third_to_main, null);
            }
        });

        TextView input = view.findViewById(R.id.third_input);
        Button saveBtn = view.findViewById(R.id.save_db_btn);
        Button loadBtn = view.findViewById(R.id.load_db_btn);
        TextView dbText = view.findViewById(R.id.db_text);
        Button deleteBtn = view.findViewById(R.id.delete_by_name_btn);

        AppDatabase db = Room.databaseBuilder(getContext(), AppDatabase.class, "database")
                .allowMainThreadQueries()
                .build();
        CatDao catDao = db.catDao();

        loadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Cat> cats = catDao.getAll();
                String text = "The Cats:\n";
                for (Cat cat : cats) {
                    text += cat.toString() + "\n";
                }
                dbText.setText(text);
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cat cat = new Cat(input.getText().toString());
                catDao.insertAll(cat);
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cat cat = catDao.findByName(input.getText().toString());
                catDao.delete(cat);
            }
        });
    }
}