package com.example.quickpay;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHandler extends SQLiteOpenHelper {

    // creating a constant variables for the database.
    // below variable is for database name.
    private static final String DB_NAME = "quickpaydb";

    // below int is the database version
    private static final int DB_VERSION = 1;

    // below variables are for the users table
    private static final String USERS_TABLE_NAME = "users";
    private static final String USERS_ID_COL = "id";
    private static final String USERS_FNAME_COL = "fname";
    private static final String USERS_LNAME_COL = "lname";
    private static final String USERS_USERNAME_COL = "username";
    private static final String USERS_PASSWORD_COL = "password";
    private static final String USERS_BALANCE_COL = "balance";
    private static final String USERS_ACCOUNT_COL = "account";
    private static final String USERS_ROUTING_COL = "routing";

    // below variables are for the transactions table
    private static final String TRANSACTIONS_TABLE_NAME = "transactions";
    private static final String TRANSACTIONS_ID_COL = "id";
    private static final String TRANSACTIONS_TYPE_COL = "type";
    private static final String TRANSACTIONS_OTHER_PARTY_COL = "party";
    private static final String TRANSACTIONS_AMOUNT_COL = "amount";
    // Dates must be stored as YYYY-MM-DD HH:MM:SS.SSS
    // Can use the SQLite query "SELECT DATETIME('now')" to get this format in UTC time.
    private static final String TRANSACTIONS_DATE_COL = "date";

    // below variables are for the drafts table
    private static final String DRAFTS_TABLE_NAME = "drafts";
    private static final String DRAFTS_ID_COL = "id";
    private static final String DRAFTS_TYPE_COL = "type";
    private static final String DRAFTS_FREQUENCY_COL = "frequency";
    private static final String DRAFTS_AMOUNT_COL = "amount";
    private static final String DRAFTS_OTHER_PARTY_COL = "party";

    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    // Called the first time a database is accessed. There should be code here to create a new
    // database.
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creating an sqlite query and setting our column names
        // along with their data types for the users table.
        String query = "CREATE TABLE " + USERS_TABLE_NAME + " ("
                + USERS_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USERS_FNAME_COL + " TEXT,"
                + USERS_LNAME_COL + " TEXT,"
                + USERS_USERNAME_COL + " TEXT,"
                + USERS_PASSWORD_COL + " TEXT,"
                + USERS_BALANCE_COL + " DECIMAL,"
                + USERS_ACCOUNT_COL + " INT,"
                + USERS_ROUTING_COL + " INT (9))";

        // execute above sql query
        db.execSQL(query);

        // Creating an sqlite query and setting our column names
        // along with their data types for the transactions table.
        query = "CREATE TABLE " + TRANSACTIONS_TABLE_NAME + " ("
                + TRANSACTIONS_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TRANSACTIONS_TYPE_COL + " TEXT,"
                + TRANSACTIONS_OTHER_PARTY_COL + " TEXT,"
                + TRANSACTIONS_AMOUNT_COL + " DECIMAL,"
                + TRANSACTIONS_DATE_COL + " TEXT)";

        // execute above sql query
        db.execSQL(query);

        // Creating an sqlite query and setting our column names
        // along with their data types for the drafts table.
        query = "CREATE TABLE " + DRAFTS_TABLE_NAME + " ("
                + DRAFTS_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DRAFTS_TYPE_COL + " TEXT,"
                + DRAFTS_FREQUENCY_COL + " TEXT,"
                + DRAFTS_OTHER_PARTY_COL + " TEXT,"
                + DRAFTS_AMOUNT_COL + " DECIMAL)";

        // execute above sql query
        db.execSQL(query);
    }// End onCreate

    // this method is use to add new user to our sqlite database.
    // Returns true if successful insertion, false otherwise.
    public boolean addNewUser(String userFName, String userLName, String userUsername,
                              String userPassword, double balance, int account, int routing) {
        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues cv = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        cv.put(USERS_FNAME_COL, userFName);
        cv.put(USERS_LNAME_COL, userLName);
        cv.put(USERS_USERNAME_COL, userUsername);
        cv.put(USERS_PASSWORD_COL, userPassword);
        cv.put(USERS_BALANCE_COL, balance);
        cv.put(USERS_ACCOUNT_COL, account);
        cv.put(USERS_ROUTING_COL, routing);

        // after adding all values we are passing
        // content values to our table.
        long insert = db.insert(USERS_TABLE_NAME, null, cv);

        db.close();

        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }// End addNewUser

    // this method is use to add new transaction to our sqlite database.
    // Returns true if successful insertion, false otherwise.
    public boolean addNewDraft(String type, String frequency, String otherParty, double amount) {
        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues cv = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        cv.put(DRAFTS_TYPE_COL, type);
        cv.put(DRAFTS_OTHER_PARTY_COL, otherParty);
        cv.put(DRAFTS_AMOUNT_COL, amount);
        cv.put(DRAFTS_FREQUENCY_COL, frequency);

        // after adding all values we are passing
        // content values to our table.
        long insert = db.insert(DRAFTS_TABLE_NAME, null, cv);

        db.close();

        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }// End addNewTransaction

    // this method is use to add new transaction to our sqlite database.
    // Returns true if successful insertion, false otherwise.
    public boolean addNewTransaction(String transactionType, String otherParty, double transactionAmount, String transactionDate) {
        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues cv = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        cv.put(TRANSACTIONS_TYPE_COL, transactionType);
        cv.put(TRANSACTIONS_OTHER_PARTY_COL, otherParty);
        cv.put(TRANSACTIONS_AMOUNT_COL, transactionAmount);
        cv.put(TRANSACTIONS_DATE_COL, transactionDate);

        // after adding all values we are passing
        // content values to our table.
        long insert = db.insert(TRANSACTIONS_TABLE_NAME, null, cv);

        db.close();

        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }// End addNewTransaction

    public boolean editUser(int id, String userFName, String userLName, String userUsername,
                            String userPassword, double balance, int account, int routing) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(USERS_FNAME_COL, userFName);
        cv.put(USERS_LNAME_COL, userLName);
        cv.put(USERS_USERNAME_COL, userUsername);
        cv.put(USERS_PASSWORD_COL, userPassword);
        cv.put(USERS_BALANCE_COL, balance);
        cv.put(USERS_ACCOUNT_COL, account);
        cv.put(USERS_ROUTING_COL, routing);

        int update = db.update(USERS_TABLE_NAME, cv, "id = " + id, null);

        if (update == 0) {
            return false;
        } else {
            return true;
        }
    }// End editUser

    public boolean editDraft(int id, String type, String frequency, double amount, String party) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        int update = 1;

        if (update == 0) {
            return false;
        } else {
            return true;
        }
    }// End editDraft

    public boolean deleteDraft(int id, String transactionType, String otherParty,
                               double transactionAmount, String transactionDate) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        int delete = 1;

        if (delete == 0) {
            return false;
        } else {
            return true;
        }
    }// End deleteDraft

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + USERS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TRANSACTIONS_TABLE_NAME);
        onCreate(db);
    }// End onUpgrade
}

