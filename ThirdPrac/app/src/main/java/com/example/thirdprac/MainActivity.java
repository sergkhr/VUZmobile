package com.example.thirdprac;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setFragment(new MainFragment(), null);
    }

    void setFragment(Fragment fragment, Bundle bundle) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction().setReorderingAllowed(true);
        ft.replace(R.id.frame_layout, fragment);
        //в методичке сказано передать bundle после fragment в add, replace вызывает add. Но ни там ни там bundle не принимается, что за дерьмо
        ft.commit();
    }
}