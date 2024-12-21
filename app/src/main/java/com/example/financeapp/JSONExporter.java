package com.example.financeapp;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class JSONExporter {

    public static File exportToJSON(Context context, List<FinanceRecord> records) {
        try {
            // Преобразование записей в JSON
            JSONArray jsonArray = new JSONArray();
            for (FinanceRecord record : records) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("type", record.getType());
                jsonObject.put("amount", record.getAmount());
                jsonArray.put(jsonObject);
            }

            // Сохранение JSON в файл
            File file = new File(context.getExternalFilesDir(null), "finance.json");
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(jsonArray.toString(4)); // 4 - отступы для форматирования
            }

            Log.d("JSONExporter", "Файл сохранен: " + file.getAbsolutePath());
            return file; // Возвращаем объект File
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("JSONExporter", "Ошибка при экспорте в JSON", e);
            return null; // Возвращаем null в случае ошибки
        }
    }
}