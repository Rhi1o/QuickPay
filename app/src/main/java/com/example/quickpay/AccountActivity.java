package com.example.quickpay;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

//import com.example.quickpay.databinding.ActivityMainBinding;

import org.w3c.dom.Text;

import java.io.IOException;
import java.text.NumberFormat;

public class AccountActivity extends AppCompatActivity {

    private DBHandler dbHandler;
    private SQLiteDatabase database;

    private int userID;
    private String userFName;
    private String userLName;
    private String userUsername;
    private String userPassword;
    private String userAccount;
    private String userRouting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        getSupportActionBar().hide();

        Button btnCloseAccountInformation = findViewById(R.id.btnCloseAccountInformation);
        Button btnSaveAccountInformation = findViewById(R.id.btnSaveAccountInformation);
        TextView txtInputEditFName = findViewById(R.id.txtInputEditFName);
        TextView txtInputEditLName = findViewById(R.id.txtInputEditLName);
        TextView txtInputEditUsername = findViewById(R.id.txtInputEditUsername);
        TextView txtInputEditPassword = findViewById(R.id.txtInputEditPassword);
        TextView txtInputEditAccount = findViewById(R.id.txtInputEditAccount);
        TextView txtInputEditRouting = findViewById(R.id.txtInputEditRouting);

        dbHandler = new DBHandler(AccountActivity.this);

        database = dbHandler.getDatabase();

        // Get the user ID from the previous activity
        Intent intent = getIntent();
        userID = intent.getIntExtra("userID", -1);

        userFName = dbHandler.getUserFName(database,userID);
        userLName = dbHandler.getUserLName(database,userID);
        userUsername = dbHandler.getUserUsername(database,userID);
        userPassword = dbHandler.getUserPassword(database,userID);
        userAccount = dbHandler.getUserAccount(database,userID);
        userRouting = dbHandler.getUserRouting(database,userID);

        txtInputEditFName.setText(userFName, TextView.BufferType.EDITABLE);
        txtInputEditLName.setText(userLName, TextView.BufferType.EDITABLE);
        txtInputEditUsername.setText(userUsername, TextView.BufferType.EDITABLE);
        txtInputEditAccount.setText(userAccount, TextView.BufferType.EDITABLE);
        txtInputEditRouting.setText(userRouting, TextView.BufferType.EDITABLE);

        btnSaveAccountInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newFName = txtInputEditFName.getText().toString();
                String newLName = txtInputEditLName.getText().toString();
                String newUsername = txtInputEditUsername.getText().toString();
                String newPassword = txtInputEditPassword.getText().toString();
                int newAccount = -1;
                int newRouting = -1;
                try {
                    newAccount = Integer.parseInt(txtInputEditAccount.getText().toString());
                    newRouting = Integer.parseInt(txtInputEditRouting.getText().toString());
                } catch (Exception exception) {

                }

                if (newFName.equals(""))
                    newFName = userFName;
                if (newLName.equals(""))
                    newLName = userLName;
                if (newUsername.equals(""))
                    newUsername = userUsername;
                if (newPassword.equals(""))
                    newPassword = userPassword;
                if (newAccount == -1)
                    newAccount = Integer.parseInt(userAccount);
                if (newRouting == -1)
                    newRouting = Integer.parseInt(userRouting);

                if (dbHandler.editUser(database,userID,newFName,newLName,newUsername,newPassword,
                        newAccount,newRouting)) {
                    goToMainActivity();
                } else {
                    Toast.makeText(AccountActivity.this, "Something went wrong.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCloseAccountInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToMainActivity();
            }
        });
    }// End onCreate

    private void goToMainActivity () {
        Intent intent = new Intent(AccountActivity.this, MainActivity.class);
        intent.putExtra("userID",userID);

        startActivity(intent);
    }// End goToMainActivity
}// End class