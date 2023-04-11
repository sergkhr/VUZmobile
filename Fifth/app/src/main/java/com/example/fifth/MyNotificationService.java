package com.example.fifth;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.IBinder;
import android.provider.Settings;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

public class MyNotificationService extends Service {

    private WindowManager mWindowManager;
    private View mOverlayView;

    public MyNotificationService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //check for alert window permission
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                //ask for permission
                Intent intent1 = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent1);
            }
        }


        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        mOverlayView =  LayoutInflater.from(this).inflate(R.layout.overlay, null);
        //create window above other apps
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                PixelFormat.TRANSLUCENT
        );
        mWindowManager.addView(mOverlayView, params);
        params.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;

        mWindowManager.updateViewLayout(mOverlayView, params);


        TextView overlayText = mOverlayView.findViewById(R.id.overlayText);
        overlayText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open app
                Intent intent = new Intent(MyNotificationService.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

                mWindowManager.removeView(mOverlayView);
                stopSelf();
            }
        });

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}