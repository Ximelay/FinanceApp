package com.example.financeapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class StatisticsActivity extends AppCompatActivity {

    private FinanceDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        dbHelper = new FinanceDatabaseHelper(this);

        TextView totalTextView = findViewById(R.id.total_text);
        TextView averageTextView = findViewById(R.id.average_text);

        double total = dbHelper.getTotalAmount();
        double average = dbHelper.getAverageAmount();

        totalTextView.setText(String.format("Общая сумма: %.2f", total));
        averageTextView.setText(String.format("Средний расход: %.2f", average));
    }
}