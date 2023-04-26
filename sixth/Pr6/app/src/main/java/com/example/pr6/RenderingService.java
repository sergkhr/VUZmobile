package com.example.pr6;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class RenderingService extends Service {
    View view;
    TextView title;
    TextView description;
    ImageView image;
    WindowManager windowManager;

    @Override
    public void onCreate() {
        super.onCreate();
        view = LayoutInflater.from(this).inflate(R.layout.service_layout, null);
        title = view.findViewById(R.id.service_title);
        description = view.findViewById(R.id.service_description);
        image = view.findViewById(R.id.service_image);
        view.setBackgroundColor(getResources().getColor(R.color.teal_200));
        windowManager = (WindowManager)getSystemService(Context.WINDOW_SERVICE);

        view.setOnClickListener((v) -> {
            Intent intent = getPackageManager().getLaunchIntentForPackage("com.example.pr6");
            windowManager.removeView(view);
            startActivity(intent);
        });
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (view.findViewById(R.id.service_layout).isShown()) {
            return super.onStartCommand(intent, flags, startId);
        }
        Bundle bundle = intent.getExtras();
        title.setText(bundle.getString("title"));
        description.setText(bundle.getString("description"));
        image.setImageResource(bundle.getInt("imageId"));

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT
        );

        windowManager.addView(view, params);
        params.gravity = Gravity.TOP | Gravity.END;
        windowManager.updateViewLayout(view, params);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (view != null) {
            windowManager.removeView(view);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}