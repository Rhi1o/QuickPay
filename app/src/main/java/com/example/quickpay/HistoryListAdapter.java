package com.example.quickpay;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ActionMenuView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class HistoryListAdapter extends ArrayAdapter<String> {
    ArrayList<String> list;
    Context context;

    public HistoryListAdapter(Context context, ArrayList<String> items) {
        super(context, R.layout.transaction_list_row, items);
        this.context = context;
        list = items;
    }// End constructor

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.transaction_list_row, null);

            String[] temp = list.get(position).split(",");

            TextView date = convertView.findViewById(R.id.date);
            date.setText(formatDate(temp[0]));

            TextView party = convertView.findViewById(R.id.party);
            party.setText(temp[1]);

            TextView amount = convertView.findViewById(R.id.amount);
            if (temp[2].equals("Deposit")) {
                amount.setText("+$ " + temp[3]);
            } else {
                amount.setText("-$ " + temp[3]);
            }
        }
        return convertView;
    }// End getView

    private String formatDate(String date) {
        return date.substring(0,date.length() - 3);
    }// End formatDate
}// End class
