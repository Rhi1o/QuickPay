package com.example.quickpay;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.NumberFormat;

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
    private static final String TRANSACTIONS_USER_COL = "userid";
    private static final String TRANSACTIONS_TYPE_COL = "type";
    private static final String TRANSACTIONS_OTHER_PARTY_COL = "party";
    private static final String TRANSACTIONS_AMOUNT_COL = "amount";
    // Dates must be stored as YYYY-MM-DD HH:MM:SS.SSS
    // Can use the SQLite query "SELECT DATETIME('now')" to get this format in UTC time.
    private static final String TRANSACTIONS_DATE_COL = "date";

    // below variables are for the drafts table
    private static final String DRAFTS_TABLE_NAME = "drafts";
    private static final String DRAFTS_ID_COL = "id";
    private static final String DRAFTS_USER_COL = "userid";
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
                + USERS_BALANCE_COL + " TEXT,"
                + USERS_ACCOUNT_COL + " INT,"
                + USERS_ROUTING_COL + " INT (9))";

        // execute above sql query
        db.execSQL(query);

        // Creating an sqlite query and setting our column names
        // along with their data types for the transactions table.
        query = "CREATE TABLE " + TRANSACTIONS_TABLE_NAME + " ("
                + TRANSACTIONS_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TRANSACTIONS_USER_COL + " INT,"
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
                + DRAFTS_USER_COL + " INT,"
                + DRAFTS_TYPE_COL + " TEXT,"
                + DRAFTS_FREQUENCY_COL + " TEXT,"
                + DRAFTS_OTHER_PARTY_COL + " TEXT,"
                + DRAFTS_AMOUNT_COL + " DECIMAL)";

        // execute above sql query
        db.execSQL(query);
    }// End onCreate

    // this method is use to add new user to our sqlite database.
    // Returns true if successful insertion, false otherwise.
    public static boolean addUser(SQLiteDatabase db, String userFName, String userLName,
                                  String userUsername, String userPassword,
                                  int account, int routing) {
        // creating a variable for content values.
        ContentValues cv = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        cv.put(USERS_FNAME_COL, userFName);
        cv.put(USERS_LNAME_COL, userLName);
        cv.put(USERS_USERNAME_COL, userUsername);
        cv.put(USERS_PASSWORD_COL, userPassword);
        cv.put(USERS_BALANCE_COL, "0.00");
        cv.put(USERS_ACCOUNT_COL, account);
        cv.put(USERS_ROUTING_COL, routing);

        // after adding all values we are passing
        // content values to our table.
        long insert = db.insert(USERS_TABLE_NAME, null, cv);

        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }// End addNewUser

    // this method is use to add new transaction to our sqlite database.
    // Returns true if successful insertion, false otherwise.
    public static boolean addDraft(SQLiteDatabase db, int userID, String type, String frequency,
                                   String otherParty, double amount) {
        // creating a variable for content values.
        ContentValues cv = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        cv.put(TRANSACTIONS_USER_COL, userID);
        cv.put(DRAFTS_TYPE_COL, type);
        cv.put(DRAFTS_OTHER_PARTY_COL, otherParty);
        cv.put(DRAFTS_AMOUNT_COL, amount);
        cv.put(DRAFTS_FREQUENCY_COL, frequency);

        // after adding all values we are passing
        // content values to our table.
        long insert = db.insert(DRAFTS_TABLE_NAME, null, cv);


        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }// End addNewTransaction

    public SQLiteDatabase getDatabase() {
        return this.getWritableDatabase();
    }

    // this method is use to add new transaction to our sqlite database.
    // Returns true if successful insertion, false otherwise.
    public static boolean addTransaction(SQLiteDatabase db, int userID, String transactionType,
                                         String otherParty, double transactionAmount) {
        // creating a variable for content values.
        ContentValues cv = new ContentValues();

        String transactionDate = "(SELECT DATETIME('now'))";

        // on below line we are passing all values
        // along with its key and value pair.
        cv.put(TRANSACTIONS_USER_COL, userID);
        cv.put(TRANSACTIONS_TYPE_COL, transactionType);
        cv.put(TRANSACTIONS_OTHER_PARTY_COL, otherParty);
        cv.put(TRANSACTIONS_AMOUNT_COL, transactionAmount);
        cv.put(TRANSACTIONS_DATE_COL, transactionDate);

        // after adding all values we are passing
        // content values to our table.
        long insert = db.insert(TRANSACTIONS_TABLE_NAME, null, cv);

        if (transactionType.equals("Withdrawal")) {
            transactionAmount = transactionAmount * (-1);
        }

        boolean update = updateUserBalance(db,userID,transactionAmount);

        if (insert == -1 || update) {
            return false;
        } else {
            return true;
        }
    }// End addNewTransaction

    public static boolean editUser(SQLiteDatabase db, int userID, String userFName,
                                   String userLName, String userUsername, String userPassword,
                                   int account, int routing) {

        ContentValues cv = new ContentValues();

        cv.put(USERS_FNAME_COL, userFName);
        cv.put(USERS_LNAME_COL, userLName);
        cv.put(USERS_USERNAME_COL, userUsername);
        cv.put(USERS_PASSWORD_COL, userPassword);
        cv.put(USERS_ACCOUNT_COL, account);
        cv.put(USERS_ROUTING_COL, routing);

        int update = db.update(USERS_TABLE_NAME, cv,  USERS_ID_COL + " = " + userID,
                null);

        if (update < 1) {
            return false;
        } else {
            return true;
        }
    }// End editUser

    public static int verifyUser(SQLiteDatabase db, String username, String password) {
        String query = "SELECT " + USERS_ID_COL + ", " + USERS_PASSWORD_COL + " FROM " +
                USERS_TABLE_NAME + " WHERE " + USERS_USERNAME_COL + " = '" + username + "'";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null)
            cursor.moveToFirst();
        else
            return -1;

        int userID = cursor.getInt(0);
        String userPassword = cursor.getString(1);

        if (userPassword.equals(password)) {
            return userID;
        }

        return -1;
    }

    public static int getUserID(SQLiteDatabase db, String username) {
        String query = "SELECT " + USERS_ID_COL + " FROM " +
                USERS_TABLE_NAME + " WHERE " + USERS_USERNAME_COL + " = '" + username + "'";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null)
            cursor.moveToFirst();
        else
            return -1;

        int userID = cursor.getInt(0);

        return userID;
    }

    public static String getUserBalance(SQLiteDatabase db, int userID) {
        String query = "SELECT " + USERS_BALANCE_COL + " FROM " +
                USERS_TABLE_NAME + " WHERE " + USERS_ID_COL + " = " + userID;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null)
            cursor.moveToFirst();

        String balance = cursor.getString(0);

        return balance;
    }// End getUserBalance

    public static String getUserFName(SQLiteDatabase db, int userID) {
        String query = "SELECT " + USERS_FNAME_COL + " FROM " +
                USERS_TABLE_NAME + " WHERE " + USERS_ID_COL + " = " + userID;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null)
            cursor.moveToFirst();

        String value = cursor.getString(0);

        return value;
    }// End getUserFName

    public static String getUserLName(SQLiteDatabase db, int userID) {
        String query = "SELECT " + USERS_LNAME_COL + " FROM " +
                USERS_TABLE_NAME + " WHERE " + USERS_ID_COL + " = " + userID;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null)
            cursor.moveToFirst();

        String value = cursor.getString(0);

        return value;
    }// End getUserLName

    public static String getUserUsername(SQLiteDatabase db, int userID) {
        String query = "SELECT " + USERS_USERNAME_COL + " FROM " +
                USERS_TABLE_NAME + " WHERE " + USERS_ID_COL + " = " + userID;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null)
            cursor.moveToFirst();

        String value = cursor.getString(0);

        return value;
    }// End getUserUsername

    public static String getUserPassword(SQLiteDatabase db, int userID) {
        String query = "SELECT " + USERS_PASSWORD_COL + " FROM " +
                USERS_TABLE_NAME + " WHERE " + USERS_ID_COL + " = " + userID;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null)
            cursor.moveToFirst();

        String value = cursor.getString(0);

        return value;
    }// End getUserPassword

    public static String getUserAccount(SQLiteDatabase db, int userID) {
        String query = "SELECT " + USERS_ACCOUNT_COL + " FROM " +
                USERS_TABLE_NAME + " WHERE " + USERS_ID_COL + " = " + userID;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null)
            cursor.moveToFirst();

        String value = cursor.getString(0);

        return value;
    }// End getUserAccount

    public static String getUserRouting(SQLiteDatabase db, int userID) {
        String query = "SELECT " + USERS_ROUTING_COL + " FROM " +
                USERS_TABLE_NAME + " WHERE " + USERS_ID_COL + " = " + userID;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null)
            cursor.moveToFirst();

        String value = cursor.getString(0);

        return value;
    }// End getUserRouting

    // Returns a string list of all transactions linked to the passed user.
    public static String[] getTransactions(SQLiteDatabase db, int userID) {
        String[] transactions = new String[0];

        return transactions;
    }// End getTransactions

    // Returns a string list of all drafts linked to the passed user.
    public static String[] getDrafts(SQLiteDatabase db, int userID) {
        String[] drafts = new String[0];

        return drafts;
    }// End getDrafts

    // Edits the draft based on the draft ID, must pass all values for draft, will edit all values.
    public static boolean editDraft(SQLiteDatabase db, int draftID, String type, String frequency,
                                    double amount, String otherParty) {
        ContentValues cv = new ContentValues();

        cv.put(DRAFTS_TYPE_COL, type);
        cv.put(DRAFTS_OTHER_PARTY_COL, otherParty);
        cv.put(DRAFTS_AMOUNT_COL, amount);
        cv.put(DRAFTS_FREQUENCY_COL, frequency);

        int update = db.update(DRAFTS_TABLE_NAME, cv, DRAFTS_ID_COL + " = " + draftID,
                null);

        if (update < 1) {
            return false;
        } else {
            return true;
        }
    }// End editDraft

    public static boolean deleteDraft(SQLiteDatabase db, int draftID) {
        int delete = db.delete(DRAFTS_TABLE_NAME, DRAFTS_ID_COL + " = " + draftID,
                null);

        if (delete < 1) {
            return false;
        } else {
            return true;
        }
    }// End deleteDraft

    private static boolean updateUserBalance(SQLiteDatabase db, int userID, double amount) {
        double balance = Double.parseDouble(getUserBalance(db,userID));

        balance += amount;

        ContentValues cv = new ContentValues();

        cv.put(USERS_BALANCE_COL, balance);

        int update = db.update(USERS_TABLE_NAME, cv, USERS_ID_COL + " = " + userID,
                null);

        if (update < 1) {
            return false;
        } else {
            return true;
        }
    }// End updateUserBalance

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + USERS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TRANSACTIONS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DRAFTS_TABLE_NAME);
        onCreate(db);
    }// End onUpgrade
}

