package com.example.showadvertisement.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdDBHelper extends SQLiteOpenHelper {

    private AdDBHelper adDBHelper = null;

    private SQLiteDatabase database = null;

    public AdDBHelper(@Nullable Context context,
                      @Nullable String name,
                      @Nullable SQLiteDatabase.CursorFactory factory,
                      int version) {
        super(context, name, factory, version);
    }

    public AdDBHelper getInstanceDB(Context context,int version) {
        if (adDBHelper == null) {
            adDBHelper = new AdDBHelper(context,"Ad.db",null,version);
        }
        return adDBHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
