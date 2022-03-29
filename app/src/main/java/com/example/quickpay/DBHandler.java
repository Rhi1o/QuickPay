package com.example.quickpay;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    // creating a constant variables for the database.
    // below variable is for database name.
    private static final String DB_NAME = "quickpaydb";

    // below int is the database version
    private static final int DB_VERSION = 1;

    // below variables are for the users table
    private static final String USERS_TABLE_NAME = "users";
    private static final String USERS_ID_COL = "id";
    private static final String USERS_NAME_COL = "name";
    private static final String USERS_USERNAME_COL = "duration";
    private static final String USERS_PASSWORD_COL = "description";

    // below variables are for the transactions table
    private static final String TRANSACTIONS_TABLE_NAME = "users";
    private static final String TRANSACTIONS_ID_COL = "id";
    private static final String TRANSACTIONS_TYPE_COL = "type";
    private static final String TRANSACTIONS_AMOUNT_COL = "amount";
    // Dates must be stored as YYYY-MM-DD HH:MM:SS.SSS
    // Can use the SQLite querry "SELECT DATETIME('now') to get this format in UTC time.
    private static final String TRANSACTIONS_DATE_COL = "date";

    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creating an sqlite query and setting our column names
        // along with their data types for the users table.
        String query = "CREATE TABLE " + USERS_TABLE_NAME + " ("
                + USERS_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USERS_NAME_COL + " TEXT,"
                + USERS_USERNAME_COL + " TEXT,"
                + USERS_PASSWORD_COL + " TEXT)";

        // execute above sql query
        db.execSQL(query);

        // Creating an sqlite query and setting our column names
        // along with their data types for the transactions table.
        query = "CREATE TABLE " + TRANSACTIONS_TABLE_NAME + " ("
                + TRANSACTIONS_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TRANSACTIONS_TYPE_COL + " TEXT,"
                + TRANSACTIONS_AMOUNT_COL + " TEXT,"
                + TRANSACTIONS_DATE_COL + " TEXT)";

        // execute above sql query
        db.execSQL(query);
    }

    // this method is use to add new user to our sqlite database.
    public void addNewUser(String userName, String userUsername, String userPassword) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(USERS_NAME_COL, userName);
        values.put(USERS_USERNAME_COL, userUsername);
        values.put(USERS_PASSWORD_COL, userPassword);

        // after adding all values we are passing
        // content values to our table.
        db.insert(USERS_TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }

    // this method is use to add new transaction to our sqlite database.
    public void addNewTransaction(String transactionType, String transactionAmount, String transactionDate) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(TRANSACTIONS_TYPE_COL, transactionType);
        values.put(TRANSACTIONS_AMOUNT_COL, transactionAmount);
        values.put(TRANSACTIONS_DATE_COL, transactionDate);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TRANSACTIONS_TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + USERS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TRANSACTIONS_TABLE_NAME);
        onCreate(db);
    }
}

