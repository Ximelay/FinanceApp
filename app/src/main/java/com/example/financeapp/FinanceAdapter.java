package com.example.financeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class FinanceAdapter extends ArrayAdapter<FinanceRecord> {

    public FinanceAdapter(Context context, ArrayList<FinanceRecord> records) {
        super(context, 0, records);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_record, parent, false);
        }

        FinanceRecord record = getItem(position);

        TextView typeTextView = convertView.findViewById(R.id.type_text);
        TextView amountTextView = convertView.findViewById(R.id.amount_text);

        typeTextView.setText(record.getType());
        amountTextView.setText(String.valueOf(record.getAmount()));

        return convertView;
    }
}
