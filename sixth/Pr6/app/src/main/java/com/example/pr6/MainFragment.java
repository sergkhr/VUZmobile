package com.example.pr6;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.Manifest;
import android.widget.TextView;

public class MainFragment extends Fragment {
    private static final String CHANNEL_ID = "CHANNEL_ID";
    private static final int PERMISSION_POST_NOTIFICATIONS_REQUEST_CODE = 0;
    private static final int DRAW_OVERLAY_REQUEST_CODE = 1;
    private static final int NOTIFICATION_ID = 0;

    public void showNotification(String text) {
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getContext());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            managerCompat.createNotificationChannel(new NotificationChannel(CHANNEL_ID,
                    "channel name", NotificationManager.IMPORTANCE_HIGH));

            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.POST_NOTIFICATIONS},
                        PERMISSION_POST_NOTIFICATIONS_REQUEST_CODE);
            }
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.star)
                .setContentTitle("Переданный текст")
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        managerCompat.notify(NOTIFICATION_ID, builder.build());
    }

    public void startService() {
        TextView textView = getView().findViewById(R.id.editTextTextPersonName);
        if (Settings.canDrawOverlays(getContext())) {
            Intent intent = new Intent(getActivity().getApplicationContext(), RenderingService.class);
            intent.putExtra("title", "Переданный текст");
            intent.putExtra("description", textView.getText().toString());
            intent.putExtra("imageId", R.drawable.star);
            getActivity().startService(intent);
        } else {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getActivity().getPackageName()));
            startActivityForResult(intent, DRAW_OVERLAY_REQUEST_CODE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        view.findViewById(R.id.notificationButton).setOnClickListener(v -> {
            showNotification(((TextView)view.findViewById(R.id.editTextTextPersonName)).getText().toString());
        });

        view.findViewById(R.id.serviceButton).setOnClickListener((v) -> {
            startService();
        });
        return view;
    }
}