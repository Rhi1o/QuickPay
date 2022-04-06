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

        //getActionBar().setHomeButtonEnabled(true);
        //getActionBar().setDisplayHomeAsUpEnabled(true);

        /*
        // TESTING DATABASE
        int counter = 0;

        if (!dbHandler.addNewUser("a", "b", "f", "d", 1, 2, 3)) {counter++;}
        if (!dbHandler.addNewUser("a", "b", "c", "d", 1, 2, 3)) {counter++;}
        if (!dbHandler.addNewUser("a", "b", "c", "d", 1, 2, 3)) {counter++;}
        if (!dbHandler.addNewUser("a", "b", "c", "d", 1, 2, 3)) {counter++;}

        if (!dbHandler.editUser(1,"a", "b", "c", "d", 1, 2, 3)) {counter++;}
        if (!dbHandler.editUser(2,"b", "c", "d", "e", 2, 3, 4)) {counter++;}
        if (!dbHandler.editUser(3,"c", "d", "e", "f", 3, 4, 5)) {counter++;}
        if (!dbHandler.editUser(4,"d", "e", "f", "g", 4, 5, 6)) {counter++;}
        if (!dbHandler.editUser(5,"d", "e", "f", "g", 4, 5, 6)) {counter++;}//
        if (!dbHandler.editUser(0,"d", "e", "f", "g", 4, 5, 6)) {counter++;}//

        if (!dbHandler.addNewTransaction(1,"Withdrawal","a",1,"01/01/2000 01:01:00.001")) {counter++;}
        if (!dbHandler.addNewTransaction(1,"Deposit","b",1,"01/01/2000 01:01:00.002")) {counter++;}
        if (!dbHandler.addNewTransaction(1,"Withdrawal","c",1,"01/01/2000 01:01:00.003")) {counter++;}

        if (!dbHandler.addNewDraft(1,"Withdrawal","Monthly","b",15.00)) {counter++;}
        if (!dbHandler.addNewDraft(1,"Withdrawal","Monthly","b",15.00)) {counter++;}
        if (!dbHandler.addNewDraft(1,"Deposit","Monthly","b",15.00)) {counter++;}

        if (!dbHandler.editDraft(1,"withdrawal","Bi-weekly",15.00,"b")) {counter++;}
        if (!dbHandler.editDraft(2,"dep","monthly",16.00,"c")) {counter++;}
        if (!dbHandler.editDraft(3,"deposit","semianually",17.00,"d")) {counter++;}
        if (!dbHandler.editDraft(4,"a","Bi-weekly",15.00,"b")) {counter++;}//

        if (!dbHandler.addNewDraft(1,"Withdrawal","Monthly","b",15.00)) {counter++;}
        if (!dbHandler.deleteDraft(4)) {counter++;}
        if (!dbHandler.deleteDraft(4)) {counter++;}//

        Toast.makeText(MainActivity.this, "Failed: " + counter + "/4", Toast.LENGTH_SHORT).show();
        */
    }// End onCreate
}// End main