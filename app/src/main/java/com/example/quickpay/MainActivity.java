package com.example.quickpay;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quickpay.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private DBHandler dbHandler;
    private TextView txtTransactionAmt;
    private Button btnCalcDelete;
    private Button btnCalcDecimal;
    private Button btnCalc0;
    private Button btnCalc1;
    private Button btnCalc2;
    private Button btnCalc3;
    private Button btnCalc4;
    private Button btnCalc5;
    private Button btnCalc6;
    private Button btnCalc7;
    private Button btnCalc8;
    private Button btnCalc9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTransactionAmt = findViewById(R.id.txtTransactionAmt);
        btnCalcDelete = findViewById(R.id.btnCalcDelete);
        btnCalcDecimal = findViewById(R.id.btnCalcDecimal);
        btnCalc0 = findViewById(R.id.btnCalc0);
        btnCalc1 = findViewById(R.id.btnCalc1);
        btnCalc2 = findViewById(R.id.btnCalc2);
        btnCalc3 = findViewById(R.id.btnCalc3);
        btnCalc4 = findViewById(R.id.btnCalc4);
        btnCalc5 = findViewById(R.id.btnCalc5);
        btnCalc6 = findViewById(R.id.btnCalc6);
        btnCalc7 = findViewById(R.id.btnCalc7);
        btnCalc8 = findViewById(R.id.btnCalc8);
        btnCalc9 = findViewById(R.id.btnCalc9);


        dbHandler = new DBHandler(MainActivity.this);

        btnCalcDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTransactionText("d");
            }
        });

        btnCalc0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTransactionText("0");
            }
        });

        btnCalc1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTransactionText("1");
            }
        });

        btnCalc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTransactionText("2");
            }
        });

        btnCalc3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTransactionText("3");
            }
        });

        btnCalc4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTransactionText("4");
            }
        });

        btnCalc5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTransactionText("5");
            }
        });

        btnCalc6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTransactionText("6");
            }
        });

        btnCalc7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTransactionText("7");
            }
        });

        btnCalc8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTransactionText("8");
            }
        });

        btnCalc9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTransactionText("9");
            }
        });

        btnCalcDecimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTransactionText(".");
            }
        });

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

    private void editTransactionText(String add) {
        StringBuilder temp = new StringBuilder(txtTransactionAmt.getText().toString());

        if (temp.toString().equals("$0.00")) {
            temp = temp.replace(0, temp.length(), "$");
        }

        if (add == "d") {
            temp = temp.deleteCharAt(temp.length() - 1);
            if (temp.length() <= 1) {
                resetTransactionText();
            }
        } else {
            temp = temp.append(add);
        }

        txtTransactionAmt.setText(temp);
    }// End editText

    private void resetTransactionText() {
        String temp = "$0.00";

        txtTransactionAmt.setText(temp);
    }// End editText
}// End main