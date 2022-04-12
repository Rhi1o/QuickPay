package com.example.quickpay;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quickpay.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase database;

    //private ActivityMainBinding binding;
    private int userID = -1;
    private DBHandler dbHandler;
    private Button btnMenu;
    private Button btnReceive;
    private Button btnSend;
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

        getSupportActionBar().hide();

        btnMenu = findViewById(R.id.btnMenu);
        btnReceive = findViewById(R.id.btnRecieve);
        btnSend = findViewById(R.id.btnSend);

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

        database = dbHandler.getDatabase();

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenuPopup(v);
            }
        });

        btnReceive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showReceivePopup(v);
            }

        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSendPopup(v);
            }
        });

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

        if (temp.toString().equals("$ 0.00")) {
            temp = temp.replace(0, temp.length(), "$ ");
        }

        if (add == "d") {
            temp = temp.deleteCharAt(temp.length() - 1);
            if (temp.length() <= 2) {
                resetTransactionText();
                return;
            }
        }
        else if (temp.indexOf(".") != -1 && temp.length() - temp.indexOf(".") == 3) {
            // Do nothing
        }
        else {
            temp = temp.append(add);
        }

        txtTransactionAmt.setText(temp.toString());
    }// End editText

    private void resetTransactionText() {
        String temp = "$ 0.00";

        txtTransactionAmt.setText(temp);
    }// End editText

    public void showMenuPopup(View view) {
        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_menu, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow menuPopup = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        menuPopup.showAtLocation(view, Gravity.CENTER, 0, 0);

        ImageButton btnCloseMenu = (ImageButton) popupView.findViewById(R.id.btnCloseMenu);

        // dismiss the popup window when the close button is pressed
        btnCloseMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuPopup.dismiss();
            }
        });
    }

    public void showReceivePopup(View view) {
        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_receive, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow receivePopup = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        receivePopup.showAtLocation(view, Gravity.CENTER, 0, 0);

        TextView txtReceiveAmount = (TextView) popupView.findViewById(R.id.txtReceiveAmount);

        String transactionAmt = txtTransactionAmt.getText().toString().substring(2);
        transactionAmt = "Amount: $ " + transactionAmt;
        txtReceiveAmount.setText(transactionAmt);

        ImageButton btnCloseReceiving = (ImageButton) popupView.findViewById(R.id.btnCloseReceiving);

        // dismiss the popup window when the close button is pressed
        btnCloseReceiving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                receivePopup.dismiss();
            }
        });

        Button btnCancelReceiving = (Button) popupView.findViewById(R.id.btnCancelReceive);

        // dismiss the popup window when the close button is pressed
        btnCancelReceiving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                receivePopup.dismiss();
            }
        });

        Button btnConfirmReceiving = (Button) popupView.findViewById(R.id.btnConfirmReceive);

        // dismiss the popup window when the close button is pressed
        btnConfirmReceiving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Send data
                double amount = Double.parseDouble(txtTransactionAmt.getText().toString().substring(2));

                String otherParty = "Bank";

                DBHandler.addTransaction(database, userID,"Deposit",otherParty,amount);
            }
        });
    }

    public void showSendPopup(View view) {
        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_send, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow sendPopup = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        sendPopup.showAtLocation(view, Gravity.CENTER, 0, 0);

        TextView txtSendAmount = (TextView) popupView.findViewById(R.id.txtSendAmount);

        String transactionAmt = txtTransactionAmt.getText().toString().substring(2);
        transactionAmt = "Amount: $ " + transactionAmt;
        txtSendAmount.setText(transactionAmt);

        ImageButton btnCloseSending = (ImageButton) popupView.findViewById(R.id.btnCloseSending);

        // dismiss the popup window when the close button is pressed
        btnCloseSending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendPopup.dismiss();
            }
        });

        Button btnCancelSending = (Button) popupView.findViewById(R.id.btnCancelSend);

        // dismiss the popup window when the close button is pressed
        btnCancelSending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendPopup.dismiss();
            }
        });
    }
}// End main