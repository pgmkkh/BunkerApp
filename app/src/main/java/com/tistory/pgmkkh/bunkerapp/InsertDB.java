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
    private DatabaseHelper mDbHelper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inserdb);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map1);
        mapFragment.getMapAsync(this);

        mDbHelper = new DatabaseHelper(this);
        Button b1 = (Button)findViewById(R.id.find);
        final EditText e1 = (EditText)findViewById(R.id.i_nLocE);
        Button b2 = (Button) findViewById(R.id.Save);

        b1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                loc = e1.getText().toString();
                getAddress();

                if (mGoogleMap != null) {
                    LatLng location = new LatLng(lati, longi);
                    mGoogleMap.addMarker(
                            new MarkerOptions().
                                    position(location).
                                    title("창신역").
                                    alpha(0.8f).
                                    icon(BitmapDescriptorFactory.fromResource(R.drawable.arrow)).
                                    snippet("6호선")
                    );
                    mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,15));
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name = (EditText)findViewById(R.id.i_nNameE);
                EditText loc = (EditText)findViewById(R.id.i_nLocE);
                EditText capa = (EditText)findViewById(R.id.i_nCapaE);

              mDbHelper.insertUserBySQL(name.getText().toString(),loc.getText().toString(),capa.getText().toString(),lati,longi);

                Toast.makeText(getApplicationContext(),"저장 완료", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), FirstView.class);
                startActivity(intent);
            }
        });
        }

    private void getAddress() {
        try {
            Geocoder geocoder = new Geocoder(this, Locale.KOREA);
            List<Address> addresses = geocoder.getFromLocationName(loc,1);
            if (addresses.size() >0) {
                Address bestResult = (Address) addresses.get(0);

                lati =  bestResult.getLatitude();
                longi =  bestResult.getLongitude();
                Toast.makeText(getApplicationContext(),"이곳이 맞나요??", Toast.LENGTH_SHORT).show();

            }
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(),"해당지역이 없습니다.", Toast.LENGTH_SHORT).show();
            Log.e(getClass().toString(),"Failed in using Geocoder.", e);
            return;
        }

    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        LatLng hansung = new LatLng(37.5817891, 127.009854);
        googleMap.addMarker(
                new MarkerOptions().
                        position(hansung).
                        title("hi"));

        // move the camera
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hansung,15));
    }

}
