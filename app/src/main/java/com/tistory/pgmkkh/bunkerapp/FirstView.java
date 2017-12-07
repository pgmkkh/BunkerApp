package com.tistory.pgmkkh.bunkerapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyungho on 2017-12-06.
 */

public class FirstView extends Activity {
    private ListView lvProduct;
    private ListProductAdapter adapter;
    private List<Product> mProductList;
    private DatabaseHelper mDBHelper;
    private Spinner spinner;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.firstview);
        lvProduct = (ListView)findViewById(R.id.listview_product);
        mDBHelper = new DatabaseHelper(this);

        File database = getApplicationContext().getDatabasePath(DatabaseHelper.DBNAME);
        if(false == database.exists()) {
            mDBHelper.getReadableDatabase();
            //Copy db
            if(copyDatabase(this)) {
                Toast.makeText(this, "Copy database succes", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Copy data error", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        mProductList = mDBHelper.getListProduct();
        adapter = new ListProductAdapter(this, mProductList);
        lvProduct.setAdapter(adapter);
        spinner = (Spinner)findViewById(R.id.spinner);

        final ArrayList<String> list = new ArrayList<>();
        list.add("지역");
        list.add("서울특별시   ");
        list.add("경기도   ");
        list.add("강원도   ");
        list.add("경상남도   ");
        list.add("경상북도   ");
        list.add("경상남도   ");
        list.add("광주광역시   ");
        list.add("대구광역시   ");
        list.add("대전광역시   ");
        list.add("부산광역시   ");
        list.add("울산광역시   ");
        list.add("인천광역시   ");
        list.add("전라남도   ");
        list.add("전라북도   ");
        list.add("제주도   ");
        list.add("충청남도   ");
        list.add("충청북도   ");

        ArrayAdapter spinnerAdapter;
        spinnerAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, list);
        spinner.setAdapter(spinnerAdapter);
}

    private boolean copyDatabase(Context context) {
        try {

            InputStream inputStream = context.getAssets().open(DatabaseHelper.DBNAME);
            String outFileName = DatabaseHelper.DBLOCATION + DatabaseHelper.DBNAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[]buff = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            Log.w("MainActivity","DB copied");
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}