package com.example.financeapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FinanceDatabaseHelper dbHelper;
    private FinanceAdapter financeAdapter;
    private ArrayList<FinanceRecord> financeRecords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new FinanceDatabaseHelper(this);
        financeRecords = dbHelper.getAllRecords();

        Spinner typeSpinner = findViewById(R.id.type_spinner);
        EditText amountEditText = findViewById(R.id.amount_input);
        Button addButton = findViewById(R.id.add_button);
        Button statsButton = findViewById(R.id.stats_button);
        Button exportButton = findViewById(R.id.export_button);
        Button emailButton = findViewById(R.id.email_button); // Новая кнопка
        ListView recordsListView = findViewById(R.id.records_list);

        financeAdapter = new FinanceAdapter(this, financeRecords);
        recordsListView.setAdapter(financeAdapter);

        addButton.setOnClickListener(v -> {
            String type = typeSpinner.getSelectedItem().toString();
            double amount = Double.parseDouble(amountEditText.getText().toString());
            FinanceRecord record = new FinanceRecord(type, amount);

            dbHelper.addRecord(record);
            financeRecords.add(record);
            financeAdapter.notifyDataSetChanged();

            Toast.makeText(this, "Запись добавлена", Toast.LENGTH_SHORT).show();
        });

        statsButton.setOnClickListener(v -> {
            startActivity(new Intent(this, StatisticsActivity.class));
        });

        exportButton.setOnClickListener(v -> {
            File jsonFile = JSONExporter.exportToJSON(this, financeRecords); // Получаем объект File
            if (jsonFile != null) {
                Toast.makeText(this, "Экспорт выполнен: " + jsonFile.getAbsolutePath(), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Ошибка экспорта данных!", Toast.LENGTH_SHORT).show();
            }
        });

        emailButton.setOnClickListener(v -> {
            File jsonFile = JSONExporter.exportToJSON(this, financeRecords); // Получаем объект File
            if (jsonFile != null) {
                EmailSender.sendEmailWithAttachment(this, jsonFile); // Отправляем JSON через Email
            } else {
                Toast.makeText(this, "Ошибка экспорта данных!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}