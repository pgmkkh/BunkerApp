package com.tistory.pgmkkh.bunkerapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.List;

/**
 * Created by Pgmkkh on 2017-12-19.
 */

public class SecondView extends AppCompatActivity implements OnMapReadyCallback {

    private FusedLocationProviderClient mFusedLocationClient;
    private ListView lvProduct;
    private Location mLastLocation;
    private List<Product> mProductList;
    private DatabaseHelper mDbHelper;
    private ListProductAdapter adapter;
    GoogleMap mGoogleMap = null;
    double latitude = 0.0;
    double longitude = 0.0;
    float precision = 0.0f;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondview);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        lvProduct = (ListView) findViewById(R.id.listview_product1);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map2);
        mapFragment.getMapAsync(this);
        mDbHelper = new DatabaseHelper(this);

        Button btn = (Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkLocationPermissions()) {
                    requestLocationPermissions(100);
                } else
                    getLastLocation();
            }
        });
        lvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //클릭 이벤트 뷰, 실제 Adapter 뷰, 클릭 위치, 항목 id
            public void onItemClick(AdapterView<?> parent, View vClicked,
                                    int position, long id) {

                String bName = ((Product) adapter.getItem(position)).getbName();
                String loc = ((Product) adapter.getItem(position)).getLoc();
                double lati = ((Product) adapter.getItem(position)).getLati();
                double longi = ((Product) adapter.getItem(position)).getLongi();
                String capa = ((Product) adapter.getItem(position)).getCapa();
                String phone = ((Product) adapter.getItem(position)).getPhone();
                String aName = (((Product) adapter.getItem(position)).getaName());


                Intent intent = new Intent(getApplicationContext(), Info.class);
                intent.putExtra("bName", bName);
                intent.putExtra("loc", loc);
                intent.putExtra("lati", lati);
                intent.putExtra("longi", longi);
                intent.putExtra("capa", capa);
                intent.putExtra("phone", phone);
                intent.putExtra("aName", aName);

                startActivityForResult(intent, 9999);
            }
        });

    }
    private boolean checkLocationPermissions() {
        int permissionState = ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }
    private void requestLocationPermissions(int requestCode) {
        ActivityCompat.requestPermissions(
                SecondView.this,            // MainActivity 액티비티의 객체 인스턴스를 나타냄
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},        // 요청할 권한 목록을 설정한 String 배열
                requestCode    // 사용자 정의 int 상수. 권한 요청 결과를 받을 때
        );
    }
    @SuppressWarnings("MissingPermission")
    private void getLastLocation() {
        Task task = mFusedLocationClient.getLastLocation();
        task.addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                // Got last known location. In some rare situations this can be null.
                if (location != null) {
                    mLastLocation = location;
                    updateValue();
                } else
                    Toast.makeText(getApplicationContext(), "asd", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateValue() {

        if (mLastLocation != null) {
            latitude = mLastLocation.getLatitude();
            longitude = mLastLocation.getLongitude();
            precision = mLastLocation.getAccuracy();
        }


        mProductList = mDbHelper.locationlist(latitude, longitude);
        adapter = new ListProductAdapter(getApplicationContext(), mProductList);
        lvProduct.setAdapter(adapter);

        for(int i =0; i < adapter.getCount(); i++){
            LatLng loc = new LatLng(mProductList.get(i).getLati(),mProductList.get(i).getLongi());
            mGoogleMap.addMarker(
                    new MarkerOptions().
                            position(loc).
                            alpha(0.8f).
                            title(mProductList.get(i).getbName()).
                            snippet(mProductList.get(i).getLoc())
            );
           // MarkerOptions marker = new MarkerOptions().position(loc);
           // mGoogleMap.addMarker(marker);
        }
        if (mGoogleMap != null) {
            LatLng location = new LatLng(latitude, longitude);
            mGoogleMap.addMarker(
                    new MarkerOptions().
                            position(location).
                            alpha(0.8f)
            );
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,15));
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
