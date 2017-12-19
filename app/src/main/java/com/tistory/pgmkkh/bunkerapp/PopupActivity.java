package com.tistory.pgmkkh.bunkerapp;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Pgmkkh on 2017-12-20.
 */

public class PopupActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup_activity);

        Intent intent = getIntent();
        int mIcon = intent.getIntExtra("pic",1);
        String nName = intent.getStringExtra("name");
        String nhint = intent.getStringExtra("hint");
        final String nTel = intent.getStringExtra("call");
        final String nDns = intent.getStringExtra("DNS");

        ImageView icon = (ImageView)findViewById(R.id.iconItem5);
        icon.setImageResource(mIcon);

        TextView name = (TextView)findViewById(R.id.textItem6);
        name.setText(nName);

        TextView hint = (TextView)findViewById(R.id.textItem7);
        hint.setText(nhint);

        Button btn1 = (Button)findViewById(R.id.call);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkLocationPermissions()) {
                    requestLocationPermissions(100);
                } else {
                    startActivity(new Intent("android.intent.action.CALL", Uri.parse(nTel)));
                }
            }
        });
        Button btn2 = (Button)findViewById(R.id.homepage);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(nDns)));
            }
        });

        Button btn3 = (Button)findViewById(R.id.close);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(PopupActivity.this,ThirdView.class);
                startActivity(intent1);
            }
        });
    }
    private boolean checkLocationPermissions() {
        int permissionState = ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.CALL_PHONE);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }
    private void requestLocationPermissions(int requestCode) {
        ActivityCompat.requestPermissions(
                PopupActivity.this,            // MainActivity 액티비티의 객체 인스턴스를 나타냄
                new String[]{Manifest.permission.CALL_PHONE},        // 요청할 권한 목록을 설정한 String 배열
                requestCode    // 사용자 정의 int 상수. 권한 요청 결과를 받을 때
        );
    }
}
