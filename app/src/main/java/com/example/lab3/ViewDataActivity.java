package com.example.lab3;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

public class ViewDataActivity extends AppCompatActivity {

    private TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowCompat.setDecorFitsSystemWindows(getWindow(), true);

        setContentView(R.layout.activity_view_data);

        tvData = findViewById(R.id.tvData);
        Button btnBack = findViewById(R.id.btnBack);
        Button btnClear = findViewById(R.id.btnClear);

        showData();

        btnBack.setOnClickListener(v -> finish());

        btnClear.setOnClickListener(v -> {
            boolean ok = StorageHelper.clear(this);
            Toast.makeText(this, ok ? "Очищено" : "Помилка очищення", Toast.LENGTH_SHORT).show();
            showData();
        });
    }

    private void showData() {
        String data = StorageHelper.readAll(this);
        if (data == null || data.trim().isEmpty()) {
            tvData.setText("Дані відсутні (сховище пусте).");
        } else {
            tvData.setText(data);
        }
    }
}