package com.tistory.pgmkkh.bunkerapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Kyungho on 2017-12-07.
 */

public class Info extends AppCompatActivity implements OnMapReadyCallback {
    GoogleMap mGoogleMap = null;

 //   Intent intent = getIntent();

  //  String bName = intent.getStringExtra("bName");
   // String loc =intent.getStringExtra("loc");
  //  double lati = intent.getDoubleExtra("lati",999999);
   // double longi = intent.getDoubleExtra("longi",999999);
    //int capa = intent.getIntExtra("capa",999999);
    //String phone = intent.getStringExtra("phone");
    //String aName = intent.getStringExtra("aName");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        //프래그먼트에 지도 싱크
        mapFragment.getMapAsync(this);
    }

    //기본 지도 위치 및 마커
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;

        Intent intent = getIntent();
        String bName = intent.getStringExtra("bName");
        double lati = intent.getDoubleExtra("lati",999999);
        double longi = intent.getDoubleExtra("longi",999999);

        LatLng hansung = new LatLng(lati, longi);
        googleMap.addMarker(
                new MarkerOptions().
                        position(hansung).
                        title(bName));

        // move the camera
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hansung,15));

        //마커 클릭 이벤트
        mGoogleMap.setOnMarkerClickListener(new MyMarkerClickListener());
    }

    class MyMarkerClickListener implements GoogleMap.OnMarkerClickListener {
        Intent intent = getIntent();
        String bName = intent.getStringExtra("bName");
        @Override
        public boolean onMarkerClick(Marker marker) {
            if (marker.getTitle().equals(bName)) {
                Toast.makeText(getApplicationContext(),bName + "을 선택하셨습니다", Toast.LENGTH_SHORT).show();
            }
            return false;
        }
    }
}

