package com.example.financeapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class FinanceDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "finance.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_RECORDS = "records";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TYPE = "type";
    private static final String COLUMN_AMOUNT = "amount";

    public FinanceDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_RECORDS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TYPE + " TEXT, " +
                COLUMN_AMOUNT + " REAL)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECORDS);
        onCreate(db);
    }

    public void addRecord(FinanceRecord record) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TYPE, record.getType());
        values.put(COLUMN_AMOUNT, record.getAmount());
        db.insert(TABLE_RECORDS, null, values);
        db.close();
    }

    public ArrayList<FinanceRecord> getAllRecords() {
        ArrayList<FinanceRecord> records = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_RECORDS, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            records.add(new FinanceRecord(
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TYPE)),
                    cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_AMOUNT))
            ));
        }
        cursor.close();
        db.close();
        return records;
    }

    public double getTotalAmount() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(" + COLUMN_AMOUNT + ") FROM " + TABLE_RECORDS, null);
        double total = 0;
        if (cursor.moveToFirst()) total = cursor.getDouble(0);
        cursor.close();
        db.close();
        return total;
    }

    public double getAverageAmount() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT AVG(" + COLUMN_AMOUNT + ") FROM " + TABLE_RECORDS, null);
        double average = 0;
        if (cursor.moveToFirst()) average = cursor.getDouble(0);
        cursor.close();
        db.close();
        return average;
    }
}