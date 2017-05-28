package com.example.victor.currencyconverter.ui.splash;

import android.content.Context;
import android.os.AsyncTask;

import com.example.victor.currencyconverter.ui.model.response.CurrencyCourseXml;
import com.example.victor.currencyconverter.utils.CurrencyLocalSource;
import com.example.victor.currencyconverter.utils.DatabaseHelper;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.example.victor.currencyconverter.R;

/**
 * Created by victor on 24.05.17.
 */


public class SplashScreenPresenter {

    private static final String DATA_URL = "http://www.cbr.ru/scripts/XML_daily.asp";
    private DataLoader mLoader;
    private SplashScreenView mView;

    private CurrencyLocalSource mDBHelper;

    SplashScreenPresenter(SplashScreenView view, CurrencyLocalSource databaseHelper) {
        mDBHelper = databaseHelper;
        mView = view;
    }

    public void onStartApp() {
        loadData();
    }

    private void loadData() {
        mLoader = new DataLoader();
        mLoader.execute(DATA_URL);
    }

    private void onDataLoaded() {
        if (mView != null) {
            mView.openNextScreen();
        }
    }

    public void retryLoading() {
        mLoader = new DataLoader();
        mLoader.execute(DATA_URL);
    }

    public void continueWithoutData() {
        // check data in database
        mView.openNextScreen();
    }

    class DataLoader extends AsyncTask<String, Integer, CurrencyCourseXml> {

        @Override
        protected CurrencyCourseXml doInBackground(String... strings) {
            try {
                URL url = new URL(DATA_URL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream responseInput = new BufferedInputStream(connection.getInputStream());
                Serializer serializer = new Persister();
                CurrencyCourseXml curs = serializer.read(CurrencyCourseXml.class, responseInput);
                return curs;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(CurrencyCourseXml course) {
            super.onPostExecute(course);
            if (course != null) {
                mDBHelper.insertOrUpdateCurrencyList(course.getValutes());
                onDataLoaded();
            } else {
                mView.onShowError(R.string.error_failed_data_update);
            }
        }
    }
}
