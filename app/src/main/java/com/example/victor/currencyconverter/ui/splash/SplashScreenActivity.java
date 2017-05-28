package com.example.victor.currencyconverter.ui.splash;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.victor.currencyconverter.ui.base.Injection;
import com.example.victor.currencyconverter.ui.converter.convert.ConverterActivity;
import com.example.victor.currencyconverter.R;

public class SplashScreenActivity extends AppCompatActivity implements SplashScreenView {

    private SplashScreenPresenter mPresenter;
    private AlertDialog mErrorDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new SplashScreenPresenter(this, Injection.provideTasksRepository(this));
        mPresenter.onStartApp();
    }

    @Override
    public void openNextScreen() {
        Intent intent = new Intent(this, ConverterActivity.class);
        startActivity(intent);
    }

    @Override
    public void onShowError(int res) {
        if (mErrorDialog == null) {
            mErrorDialog = new AlertDialog.Builder(this)
                    .setMessage(res)
                    .setPositiveButton(R.string.dialog_loading_error_positive_btn, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            mPresenter.retryLoading();
                        }
                    })
                    .setNegativeButton(R.string.dialog_loading_error_negatitive_btn, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            mPresenter.continueWithoutData();
                        }
                    })
                    .create();
        }
        mErrorDialog.show();
    }

    @Override
    public void setPresenter(SplashScreenPresenter presenter) {
        mPresenter = presenter;
    }
}
