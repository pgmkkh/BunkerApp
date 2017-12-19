package com.tistory.pgmkkh.bunkerapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Kyungho on 2017-12-06.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "data.db";
    public static final String DBLOCATION = "/data/data/com.tistory.pgmkkh.bunkerapp/databases/";
    private Context mContext;
    private SQLiteDatabase mDatabase;
    int bid = 999999;
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
        Log.d(TAG, "ID = " + Big);
        Log.d(TAG, "ID = " + Small);
        List<Product> productList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("select * from Product where loc LIKE " + "'%" + Big + "%' and loc LIKE " + "'%" + Small + "%'" , null);
        Log.d(TAG, "ID = " + cursor);
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

    public long insertUserByMethod(String name, String loc, String capa , double lati, double longi ) {

        SQLiteDatabase db = getWritableDatabase();
        //getWritableDatabase()의 insert 함수를 사용하기 위하여 ContentValues 준비

        ContentValues values = new ContentValues();

        values.put("bName", name);
        values.put("loc", loc);
        values.put("lali", lati);
        values.put("longo", longi);
        values.put("capa", capa);

        //long insert (String table, String nullColumnHack,  ContentValues values)
        return db.insert("Product", null, values);
    }

    public void insertUserBySQL(String name, String loc, String capa , double lati, double longi) {
        try {
            String sql = String.format (
                    "INSERT INTO %s (%s, %s, %s, %s, %s, %s) VALUES ('%s', '%s', '%s', '%s', '%s', '%s')",
                    "Product",
                    "id",
                    "bName",
                    "loc",
                    "lali",
                    "longi",
                    "capa",
                    bid++,
                    name,
                    loc,
                    lati,
                    longi,
                    capa);
            Log.e(TAG,"Error in inserting recodes1");
            Log.e(TAG,"Error in inserting recodes2");



            getWritableDatabase().execSQL(sql);
        } catch (SQLException e) {
            Log.e(TAG,"Error in inserting recodes");
        }
    }

    public int deleteUserBySQL(int id) {
        try {
            String sql = String.format (
                    "DELETE FROM %s WHERE %s = %d",
                    "Product",
                    "id",
                    id);

            getWritableDatabase().execSQL(sql);

            return 1;
        } catch (SQLException e) {
            Log.e(TAG,"Error in deleting recodes");
            return 0;
        }
    }

        public List<Product> locationlist(double leti , double longi ) {
            Product product = null;
            double leti1 = leti - 0.01;
            double leti2 = leti + 0.01;
            double longi1 = longi - 0.01;
            double longi2 = longi + 0.01;
            Log.d("asd","여긴가?");
            List<Product> productList = new ArrayList<>();
            openDatabase();
            Cursor cursor = mDatabase.rawQuery("select * from Product where lali > "+ leti1 +" and lali < "+ leti2 + " and longi > "+ longi1 + " and longi < " + longi2 , null);
            Log.d("asd","여긴가11?");
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
