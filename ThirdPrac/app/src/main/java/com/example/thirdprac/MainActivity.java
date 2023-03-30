package com.example.thirdprac;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setFragment(new MainFragment());
    }

    void setFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction().setReorderingAllowed(true);
        ft.replace(R.id.frame_layout, fragment);
        ft.commit();
    }

    List<ListItem> generateList(int size){
        List<ListItem> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            String imgRes = Math.random() < 0.5 ? "metodichka" : "studying";
            list.add(new ListItem(imgRes, "Description " + i));
        }
        return list;
    }
}