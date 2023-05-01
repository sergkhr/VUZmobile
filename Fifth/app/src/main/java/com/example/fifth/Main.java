package com.example.fifth;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.provider.Settings;
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
 * Use the {@link Main#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Main extends Fragment {

    private static final String CHANNEL_ID = "notify";
    private static final String APP_SPECIFIC_FILE_NAME = "myFile.txt";

    public Main() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static Main newInstance(String param1, String param2) {
        Main fragment = new Main();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "notify",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            channel.setDescription("notify");
            channel.setLightColor(R.color.purple_200);

            NotificationManager manager = getActivity().getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
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

        Bundle bundle = getArguments();
        if (bundle != null) {
            String income = bundle.getString("input");
            TextView title = view.findViewById(R.id.main_title);
            title.setText(income);
        }

        TextView input = view.findViewById(R.id.main_input);

        Button toFirstButton = view.findViewById(R.id.toFirstBtn);
        toFirstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("input", input.getText().toString());
                NavHostFragment.findNavController(Main.this)
                        .navigate(R.id.action_main_to_first, bundle);
            }
        });

        Button toSecondButton = view.findViewById(R.id.toSecondBtn);
        toSecondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("input", input.getText().toString());
                NavHostFragment.findNavController(Main.this)
                        .navigate(R.id.action_main_to_second, bundle);
            }
        });

        Button toThirdButton = view.findViewById(R.id.to_third_btn);
        toThirdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("input", input.getText().toString());
                NavHostFragment.findNavController(Main.this)
                        .navigate(R.id.action_main_to_trird, bundle);
            }
        });

        //notify button
        Button notifyButton = view.findViewById(R.id.notificationBtn);
        //show notification when clicked
        notifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNotification();
            }
        });


        //work with app specific storage
        Button saveButton = view.findViewById(R.id.saveAppSpecific);
        TextView inFileText = view.findViewById(R.id.textAppSpecificData);
        Button readButton = view.findViewById(R.id.readAppSpecific);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = input.getText().toString();
                File appSpecificFile = new File(getContext().getFilesDir(), APP_SPECIFIC_FILE_NAME);

                if(!appSpecificFile.exists()){
                    try {
                        appSpecificFile.createNewFile();
                    } catch (Exception e) {
                        Log.e("ERROR", "Error creating file");
                    }
                }
                //write to file
                try {
                    FileWriter writer = new FileWriter(appSpecificFile);
                    writer.write(text);
                    writer.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File appSpecificFile = new File(getContext().getFilesDir(), APP_SPECIFIC_FILE_NAME);
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
        });
    }



    private void showNotification(){
        //check for permission
        if (ContextCompat.checkSelfPermission(getContext(), "android.permission.POST_NOTIFICATIONS") != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{"android.permission.POST_NOTIFICATIONS"}, 1);
        }
        else{
            //permission granted
            NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle("Notification")
                    .setContentText("This is a notification")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getContext());

            notificationManager.notify(200, builder.build());
        }

    }
}