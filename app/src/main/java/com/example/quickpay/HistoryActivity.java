package com.example.quickpay;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    private DBHandler dbHandler;
    private SQLiteDatabase database;

    private int userID;

    private ListView lstTransactions;
    private ArrayList<String> items;
    private HistoryListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        getSupportActionBar().hide();

        // Get the user ID from the previous activity
        Intent intent = getIntent();
        userID = intent.getIntExtra("userID", -1);

        lstTransactions = findViewById(R.id.lstTransactions);
        ImageButton btnCloseHistory = findViewById(R.id.btnCloseHistory);
        items = new ArrayList<>();
        items.add("hello world");
        adapter = new HistoryListAdapter(getApplicationContext(), items);

        lstTransactions.setAdapter(adapter);

        btnCloseHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToMainActivity();
            }
        });
    }// End onCreate

    private void goToMainActivity() {
        Intent intent = new Intent(HistoryActivity.this, MainActivity.class);
        intent.putExtra("userID",userID);

        startActivity(intent);
    }// End goToMainActivity
}// End class