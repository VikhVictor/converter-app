package com.example.victor.currencyconverter.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.victor.currencyconverter.ui.model.local.CurrencyModel;
import com.example.victor.currencyconverter.ui.model.response.CurrencyModelXml;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 25.05.17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "database";


    private static final String QUERY_CREATE_TABLE = "CREATE TABLE " + CurrencyEntry.TABLE_NAME +
            " (" + CurrencyEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                 + CurrencyEntry.COLUMN_ATTR_ID + " TEXT, "
                 + CurrencyEntry.COLUMN_NUM_CODE + " TEXT, "
                 + CurrencyEntry.COLUMN_CHAR_CODE + " TEXT,"
                 + CurrencyEntry.COLUMN_NOMINAL + " INTEGER, "
                 + CurrencyEntry.COLUMN_NAME + " TEXT, "
                 + CurrencyEntry.COLUMN_VALUE + " REAL);";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(QUERY_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }



}
