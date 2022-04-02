package com.example.quickpay;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.quickpay.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHandler = new DBHandler(MainActivity.this);
/*
        if (dbHandler.addNewUser("Floyd", "Cain", "FC", "Hello",
                1000000.00, 123456, 987654321)) {
            Toast.makeText(MainActivity.this, "User added", Toast.LENGTH_SHORT).show();
        }



        dbHandler.editUser(1, "Floyd", "Cain", "FC", "Hello",
                1000000.00, 654321, 987654321);
        dbHandler.editUser(2, "John", "Roll", "JR5862", "NO",
                00.00, 879456, 111222333);
        dbHandler.editUser(3, "Floyd", "Yooo", "FC", "Hello",
                1000000.00, 654321, 987654321);

        if (!dbHandler.editUser(7, "Floyd", "Yooo", "FC", "Hello",
                1000000.00, 654321, 987654321)) {
            Toast.makeText(MainActivity.this, "Failed to update", Toast.LENGTH_SHORT).show();
        }

        if (dbHandler.addNewTransaction("Withdrawal", "WALL-MART",
                14.45, "2022/04/01 17:16:13.0000")) {
            Toast.makeText(MainActivity.this, "Transaction added", Toast.LENGTH_SHORT).show();
        }

 */


    }// End onCreate
}// End main