package com.example.quickpay;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quickpay.databinding.ActivityMainBinding;

import org.w3c.dom.Text;

import java.io.IOException;
import java.text.NumberFormat;

public class HistoryActivity extends AppCompatActivity {

    private DBHandler dbHandler;
    private SQLiteDatabase database;

    private int userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        getSupportActionBar().hide();

        // Get the user ID from the previous activity
        Intent intent = getIntent();
        userID = intent.getIntExtra("userID", -1);

        ImageButton btnCloseHistory = findViewById(R.id.btnCloseHistory);

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