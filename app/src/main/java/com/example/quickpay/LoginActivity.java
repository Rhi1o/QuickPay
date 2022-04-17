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

                userID = attemptLogin(username,password);

                if (userID != -1) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("userID", userID);

                    startActivity(intent);
                } else {
                    txtPassword.setText("");

                    Toast.makeText(LoginActivity.this, "Invalid username/password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }// End onCreate

    // returns userID if successful, -1 otherwise.
    private int attemptLogin(String username, String password) {
        int id = dbHandler.verifyUser(database,username,password);

        return id;
    }// End attemptLogin
}// End class
