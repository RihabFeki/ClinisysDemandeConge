package com.csys.clinisys.demandeconge.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

    public DatabaseHandler(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(CliniqueDAO.TABLE_CREATE);
        db.execSQL(UserDAO.TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(CliniqueDAO.TABLE_DROP);
        db.execSQL(CliniqueDAO.TABLE_CREATE);
        db.execSQL(UserDAO.TABLE_DROP);
        db.execSQL(UserDAO.TABLE_CREATE);
    }
}
