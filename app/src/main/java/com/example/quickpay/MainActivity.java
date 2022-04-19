package com.example.quickpay;

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

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private DBHandler dbHandler;
    private SQLiteDatabase database;

    //private ActivityMainBinding binding;
    private int userID;
    private String userBalance;
    private String userUsername = "testUsername";

    private Button btnMenu;
    private Button btnReceive;
    private Button btnSend;
    private TextView txtBalance;
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

        txtBalance = findViewById(R.id.txtBalance);

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

        /*
        dbHandler.addUser(database, "test", "user", "test",
                "asdf",  654321, 987654321);
        dbHandler.addUser(database, "admin", "user", "admin",
                "admin",  123456, 123456789);
         */

        Intent intent = getIntent();
        userID = intent.getIntExtra("userID",-1);

        setBalanceText();

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
    }// End onCreate

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
        Button btnAccountInformation = (Button) popupView.findViewById(R.id.btnAccountInformation);
        Button btnTransactionHistory = (Button) popupView.findViewById(R.id.btnTransactionHistory);
        Button btnAutomaticPayments = (Button) popupView.findViewById(R.id.btnAutomaticPayments);
        Button btnLogOut = (Button) popupView.findViewById(R.id.btnLogOut);

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);

                startActivity(intent);
            }
        });

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

        Double amount = Double.parseDouble(txtTransactionAmt.getText().toString().substring(2));
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String transactionAmt = "Amount: $ " + formatter.format(amount).substring(1);
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

                String otherParty = "Bank transfer";

                DBHandler.addTransaction(database, userID,"Deposit",otherParty,amount);

                // Update the balance and transaction texts
                setBalanceText();
                resetTransactionText();
                receivePopup.dismiss();
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

        Double amount = Double.parseDouble(txtTransactionAmt.getText().toString().substring(2));
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String transactionAmt = "Amount: $ " + formatter.format(amount).substring(1);
        txtSendAmount.setText(transactionAmt);

        ImageButton btnCloseSending = (ImageButton) popupView.findViewById(R.id.btnCloseSending);

        // dismiss the popup window when the close button is pressed
        btnCloseSending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendPopup.dismiss();
            }
        });

        EditText txtInputSendTo = (EditText) popupView.findViewById(R.id.txtInputSendTo);
        RadioButton rdoSendToBank = (RadioButton) popupView.findViewById(R.id.rdoSendToBank);
        RadioButton rdoSendToUser = (RadioButton) popupView.findViewById(R.id.rdoSendToUser);

        rdoSendToBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtInputSendTo.setEnabled(false);
            }
        });

        rdoSendToUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtInputSendTo.setEnabled(true);
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

        Button btnConfirmSending = (Button) popupView.findViewById(R.id.btnConfirmSend);

        // dismiss the popup window when the close button is pressed
        btnConfirmSending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Send data
                double amount = Double.parseDouble(txtTransactionAmt.getText().toString().substring(2));

                if (amount > Double.parseDouble(userBalance)) {
                    Toast.makeText(MainActivity.this, "$ "+ amount + " is not available",
                            Toast.LENGTH_SHORT).show();
                } else {
                    String otherParty = "Bank transfer";
                    int otherUserID = 0;

                    if (rdoSendToUser.isChecked()) {
                        otherParty = txtInputSendTo.getText().toString();
                        otherUserID = dbHandler.getUserID(database,otherParty);
                    }

                    if (otherUserID != -1) {
                        dbHandler.addTransaction(database, userID, "Withdrawal",
                                otherParty, amount);
                        if (otherUserID != 0) {
                            dbHandler.addTransaction(database, otherUserID, "Deposit",
                                    userUsername, amount);
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "User does not exist",
                                Toast.LENGTH_SHORT).show();
                    }
                }
                // Update the balance and transaction texts
                setBalanceText();
                resetTransactionText();
                sendPopup.dismiss();

            }
        });
    }// End showSendPopup

    // Sets the balance text on screen to the users balance
    private void setBalanceText() {
        userBalance = dbHandler.getUserBalance(database, userID);

        double balance = Double.parseDouble(userBalance);

        // Format it to currency format to look fancy
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String balanceString = formatter.format(balance).substring(1);

        txtBalance.setText("Available: $ " + balanceString);
    }

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
}// End main