package com.example.quickpay;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quickpay.databinding.ActivityMainBinding;

import org.w3c.dom.Text;

import java.io.IOException;
import java.text.NumberFormat;

public class LoginActivity extends AppCompatActivity {

    private DBHandler dbHandler;
    private SQLiteDatabase database;

    private int userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        Button btnSubmitLogin = findViewById(R.id.btnSubmitLogin);
        Button btnRegister = findViewById(R.id.btnRegister);
        TextView txtUsername = findViewById(R.id.txtInputUsername);
        TextView txtPassword = findViewById(R.id.txtInputPassword);

        dbHandler = new DBHandler(LoginActivity.this);

        database = dbHandler.getDatabase();

        btnSubmitLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = txtUsername.getText().toString();
                String password = txtPassword.getText().toString();

                if (!username.equals("") && !password.equals("")) {
                    userID = attemptLogin(username, password);
                } else {
                    userID = -1;
                }

                if (userID != -1) {
                    goToMainActivity();
                } else {
                    txtPassword.setText("");

                    Toast.makeText(LoginActivity.this, "Invalid username/password",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRegisterPopup(view);
            }
        });
    }// End onCreate

    public void showRegisterPopup(View view) {
        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_register, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow registerPopup = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        registerPopup.showAtLocation(view, Gravity.CENTER, 0, 0);

        Button btnSubmitRegistration = (Button) popupView.findViewById(R.id.btnSubmitRegistration);
        Button btnCancelRegistration = (Button) popupView.findViewById(R.id.btnCancelRegistration);
        EditText txtInputRegUsername = (EditText) popupView.findViewById(R.id.txtInputRegUsername);
        EditText txtInputRegPassword = (EditText) popupView.findViewById(R.id.txtInputRegPassword);
        EditText txtInputRegFName = (EditText) popupView.findViewById(R.id.txtInputRegFName);
        EditText txtInputRegLName = (EditText) popupView.findViewById(R.id.txtInputRegLName);
        EditText txtInputRegRouting = (EditText) popupView.findViewById(R.id.txtInputRegRouting);
        EditText txtInputRegAccount = (EditText) popupView.findViewById(R.id.txtInputRegAccount);

        btnSubmitRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = txtInputRegUsername.getText().toString();
                String password = txtInputRegPassword.getText().toString();
                String fName = txtInputRegFName.getText().toString();
                String lName = txtInputRegLName.getText().toString();
                int routing = -1;
                int account = -1;
                try {
                    // Check to see if user entered values for these two fields, if so assign them.
                    routing = Integer.parseInt(txtInputRegRouting.getText().toString());
                    account = Integer.parseInt(txtInputRegAccount.getText().toString());
                } catch (Exception exception) {

                }

                if (username.equals("") || password.equals("") || fName.equals("") ||
                        lName.equals("") || routing == -1 || account == -1) {
                    // A field is left empty, display error and try again.
                    Toast.makeText(LoginActivity.this, "All fields must be populated!",
                            Toast.LENGTH_SHORT).show();
                } else if (dbHandler.addUser(database, fName, lName, username, password, account,
                            routing)) {
                    // Add the user to the database.
                        registerPopup.dismiss();
                } else {
                    Toast.makeText(LoginActivity.this,
                            "Something went wrong, try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // dismiss the popup window when the close button is pressed
        btnCancelRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerPopup.dismiss();
            }
        });
    }

    // returns userID if successful, -1 otherwise.
    private int attemptLogin(String username, String password) {
        int id = dbHandler.verifyUser(database,username,password);

        return id;
    }// End attemptLogin

    private void goToMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra("userID",userID);

        startActivity(intent);
    }// End goToMainActivity
}// End class
