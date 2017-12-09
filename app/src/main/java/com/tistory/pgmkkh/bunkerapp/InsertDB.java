package com.tistory.pgmkkh.bunkerapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
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

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by Pgmkkh on 2017-12-09.
 */

public class InsertDB extends AppCompatActivity implements OnMapReadyCallback{
    double lati;
    double longi;
    GoogleMap mGoogleMap = null;
    String loc;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inserdb);


        Button b1 = (Button)findViewById(R.id.find);
        final EditText e1 = (EditText)findViewById(R.id.i_nLocE);
        Button mMove = (Button) findViewById(R.id.find1);

        b1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                loc = e1.getText().toString();
                getAddress();
            }
        });

        mMove.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                    setGpsCurrent(lati,longi);
        }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map1);

        }

    private void getAddress() {
        TextView addressTextView = (TextView) findViewById(R.id.test);
        try {
            Geocoder geocoder = new Geocoder(this, Locale.KOREA);
            List<Address> addresses = geocoder.getFromLocationName(loc,1);
            if (addresses.size() >0) {
                Address bestResult = (Address) addresses.get(0);

                addressTextView.setText(String.format("[ %s , %s ]",
                        bestResult.getLatitude(),
                        bestResult.getLongitude()));

                lati =  bestResult.getLatitude();
                longi =  bestResult.getLongitude();
            }
        } catch (IOException e) {
            Log.e(getClass().toString(),"Failed in using Geocoder.", e);
            return;
        }

    }
    private void setGpsCurrent(double strLat, double strLng) {

            // Creating a LatLng object for the current location
            LatLng latLng = new LatLng(strLat, strLng);

            // Showing the current location in Google Map
            mGoogleMap.moveCamera(CameraUpdateFactory
                    .newLatLng(latLng));


            // 마커 설정.
            MarkerOptions optFirst = new MarkerOptions();
            optFirst.position(latLng);// 위도 • 경도
            optFirst.title("Current Position");// 제목 미리보기
            optFirst.snippet("Snippet");
            optFirst.icon(BitmapDescriptorFactory
                    .fromResource(R.drawable.ic_launcher));
            mGoogleMap.addMarker(optFirst).showInfoWindow();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng hansung = new LatLng(lati, longi);
        googleMap.addMarker(
                new MarkerOptions().
                        position(hansung).
                        title("hi"));

        // move the camera
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hansung,15));
    }

}
