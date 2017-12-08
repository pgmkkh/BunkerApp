package com.tistory.pgmkkh.bunkerapp;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyungho on 2017-12-06.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "data.db";
    public static final String DBLOCATION = "/data/data/com.tistory.pgmkkh.bunkerapp/databases/";
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public DatabaseHelper(Context context) {
        super(context, DBNAME, null, 1);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void openDatabase() {
        String dbPath = mContext.getDatabasePath(DBNAME).getPath();
        if(mDatabase != null && mDatabase.isOpen()) {
            return;
        }
        mDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDatabase() {
        if(mDatabase!=null) {
            mDatabase.close();
        }
    }

    public List<Product> getListProduct(CharSequence Big, CharSequence Small) {
        Product product = null;
        Log.d("asd","Big");
        List<Product> productList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("select * from PRODUCT", null);
        Log.d("asd","B23123123123ig");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            product = new Product(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getDouble(3), cursor.getDouble(4), cursor.getString(5), cursor.getString(6),cursor.getString(7));
            productList.add(product);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return productList;
    }
}
