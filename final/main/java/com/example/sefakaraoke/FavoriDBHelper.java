package com.example.sefakaraoke;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class FavoriDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Favoriler.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "favoriler";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_BASLIK = "baslik";
    private static final String COLUMN_URL = "url";
    private static final String COLUMN_KATEGORI = "kategori";

    public FavoriDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_BASLIK + " TEXT,"
                + COLUMN_URL + " TEXT,"
                + COLUMN_KATEGORI + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void favoriEkle(String baslik, String kategori, String url) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_BASLIK, baslik);
        values.put(COLUMN_URL, url);
        values.put(COLUMN_KATEGORI, kategori);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<Sarki> tumFavoriler() {
        List<Sarki> favoriList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String baslik = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BASLIK));
                String url = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_URL));
                String kategori = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_KATEGORI));
                favoriList.add(new Sarki(baslik, kategori, url));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return favoriList;
    }

    public void favoriSil(String baslik) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_BASLIK + "=?", new String[]{baslik});
        db.close();
    }

    public boolean favorideMi(String baslik) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, COLUMN_BASLIK + "=?", new String[]{baslik}, null, null, null);
        boolean var = cursor.moveToFirst();
        cursor.close();
        db.close();
        return var;
    }

    public List<Sarki> getTumFavoriler() {
        List<Sarki> favoriList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String baslik = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BASLIK));
                String kategori = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_KATEGORI));
                String url = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_URL));

                favoriList.add(new Sarki(baslik, kategori, url));
            } while (cursor.moveToNext());
        }



        cursor.close();
        db.close();
        return favoriList;
    }
    public List<Sarki> kategoriyeGoreSarkilar(String kategori) {
        List<Sarki> kategoriSarkilar = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, null, COLUMN_KATEGORI + "=?", new String[]{kategori}, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String baslik = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BASLIK));
                String url = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_URL));
                kategoriSarkilar.add(new Sarki(baslik, kategori, url));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return kategoriSarkilar;
    }

}
