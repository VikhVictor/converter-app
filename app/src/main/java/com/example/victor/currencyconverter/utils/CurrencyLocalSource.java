package com.example.victor.currencyconverter.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.example.victor.currencyconverter.ui.model.local.CurrencyModel;
import com.example.victor.currencyconverter.ui.model.response.CurrencyModelXml;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 27.05.17.
 */

public class CurrencyLocalSource {

    private static CurrencyLocalSource mInstance;

    private DatabaseHelper mDBHelper;

    private CurrencyLocalSource(@NonNull Context context) {
        mDBHelper = new DatabaseHelper(context);
    }

    public static CurrencyLocalSource getInstance(@NonNull Context context) {
        if (mInstance == null) {
            mInstance = new CurrencyLocalSource(context);
        }
        return mInstance;
    }


    public void insertOrUpdateCurrencyList(List<CurrencyModelXml> currencies) {
        SQLiteDatabase database = mDBHelper.getWritableDatabase();



        for (CurrencyModelXml model : currencies) {
            ContentValues values = new ContentValues();
            values.put(CurrencyEntry.COLUMN_ATTR_ID, model.getId());
            values.put(CurrencyEntry.COLUMN_NAME, model.getName());
            values.put(CurrencyEntry.COLUMN_NOMINAL, model.getNominal());
            values.put(CurrencyEntry.COLUMN_CHAR_CODE, model.getCharCode());
            values.put(CurrencyEntry.COLUMN_NUM_CODE, model.getNumCode());
            values.put(CurrencyEntry.COLUMN_VALUE, model.getDoubleValue());

            int id = database.updateWithOnConflict(CurrencyEntry.TABLE_NAME,
                    values, CurrencyEntry.COLUMN_ATTR_ID + "=\"" + model.getId() + "\"", null, SQLiteDatabase.CONFLICT_IGNORE);
            if (id == 0) {
                database.insert(CurrencyEntry.TABLE_NAME, null, values);
            }
        }

        database.close();
    }

    @Deprecated
    public void insertCurrencyList(List<CurrencyModelXml> currencies) {
        SQLiteDatabase database = mDBHelper.getWritableDatabase();



        for (CurrencyModelXml model : currencies) {
            ContentValues values = new ContentValues();
            values.put(CurrencyEntry.COLUMN_ATTR_ID, model.getId());
            values.put(CurrencyEntry.COLUMN_NAME, model.getName());
            values.put(CurrencyEntry.COLUMN_NOMINAL, model.getNominal());
            values.put(CurrencyEntry.COLUMN_CHAR_CODE, model.getCharCode());
            values.put(CurrencyEntry.COLUMN_NUM_CODE, model.getNumCode());
            values.put(CurrencyEntry.COLUMN_VALUE, model.getDoubleValue());

            database.insert(CurrencyEntry.TABLE_NAME, null, values);
        }

        database.close();
    }


    public CurrencyModel getCurrencyById(int curId) {
        CurrencyModel result = null;
        SQLiteDatabase database = mDBHelper.getReadableDatabase();

        String[] columns = {CurrencyEntry.COLUMN_ID,
                CurrencyEntry.COLUMN_NAME,
                CurrencyEntry.COLUMN_CHAR_CODE,
                CurrencyEntry.COLUMN_NOMINAL,
                CurrencyEntry.COLUMN_VALUE};

        Cursor cursor = database.query(CurrencyEntry.TABLE_NAME,
                columns, CurrencyEntry.COLUMN_ID + "=" + curId,
                null, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToNext();
            int id = cursor.getInt(cursor.getColumnIndex(CurrencyEntry.COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndex(CurrencyEntry.COLUMN_NAME));
            String charCode = cursor.getString(cursor.getColumnIndex(CurrencyEntry.COLUMN_CHAR_CODE));
            int nominal = cursor.getInt(cursor.getColumnIndex(CurrencyEntry.COLUMN_NOMINAL));
            double value = cursor.getDouble(cursor.getColumnIndex(CurrencyEntry.COLUMN_VALUE));
            result = new CurrencyModel(id, name, charCode, nominal, value);
        }

        return result;
    }


    public List<CurrencyModel> getCurrencyList() {
        List<CurrencyModel> result = new ArrayList<>();
        SQLiteDatabase database = mDBHelper.getReadableDatabase();

        String[] columns = {CurrencyEntry.COLUMN_ID,
                CurrencyEntry.COLUMN_NAME,
                CurrencyEntry.COLUMN_CHAR_CODE,
                CurrencyEntry.COLUMN_NOMINAL,
                CurrencyEntry.COLUMN_VALUE};

        Cursor cursor = database.query(CurrencyEntry.TABLE_NAME,
                columns, null, null, null, null, CurrencyEntry.COLUMN_NAME);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex(CurrencyEntry.COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndex(CurrencyEntry.COLUMN_NAME));
                String charCode = cursor.getString(cursor.getColumnIndex(CurrencyEntry.COLUMN_CHAR_CODE));
                int nominal = cursor.getInt(cursor.getColumnIndex(CurrencyEntry.COLUMN_NOMINAL));
                double value = cursor.getDouble(cursor.getColumnIndex(CurrencyEntry.COLUMN_VALUE));
                result.add(new CurrencyModel(id, name, charCode, nominal, value));
            }
        }

        return result;
    }
}
