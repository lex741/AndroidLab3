package com.example.lab3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity
        implements InputFragment.InputListener, ResultFragment.ResultListener {

    private static final String TAG_INPUT = "INPUT";
    private static final String TAG_RESULT = "RESULT";

    private FrameLayout containerResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowCompat.setDecorFitsSystemWindows(getWindow(), true);

        setContentView(R.layout.activity_main);

        containerResult = findViewById(R.id.containerResult);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.containerInput, new InputFragment(), TAG_INPUT)
                    .commit();
        }
    }

    @Override
    public void onInputConfirmed(String productType, String company) {
        String resultText = "Вибрано: " + productType + ", фірма: " + company;

        ResultFragment resultFragment = ResultFragment.newInstance(resultText);
        containerResult.setVisibility(View.VISIBLE);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.containerResult, resultFragment, TAG_RESULT)
                .commit();

        boolean ok = StorageHelper.appendLine(this, resultText);
        Toast.makeText(this, ok ? "Запис успішний" : "Помилка запису", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onOpenRequested() {
        startActivity(new Intent(this, ViewDataActivity.class));
    }

    @Override
    public void onResultCanceled() {
        Fragment result = getSupportFragmentManager().findFragmentByTag(TAG_RESULT);
        if (result != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .remove(result)
                    .commit();
        }

        containerResult.setVisibility(View.GONE);

        Fragment input = getSupportFragmentManager().findFragmentByTag(TAG_INPUT);
        if (input instanceof InputFragment) {
            ((InputFragment) input).clearForm();
        }
    }
}