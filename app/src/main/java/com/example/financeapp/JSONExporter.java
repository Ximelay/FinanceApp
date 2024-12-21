package com.example.financeapp;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class JSONExporter {

    public static void exportToJSON(Context context, List<FinanceRecord> records) {
        try {
            JSONArray jsonArray = new JSONArray();
            for (FinanceRecord record : records) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("type", record.getType());
                jsonObject.put("amount", record.getAmount());
                jsonArray.put(jsonObject);
            }

            File file = new File(context.getExternalFilesDir(null), "finance.json");
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(jsonArray.toString(4));
            }

            Log.d("JSONExporter", "Файл сохранен: " + file.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}