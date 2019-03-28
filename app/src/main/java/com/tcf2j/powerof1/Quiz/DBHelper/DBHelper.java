package com.tcf2j.powerof1.Quiz.DBHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.tcf2j.powerof1.Quiz.Question.Question;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static String DATABASE_PATH = "/data/data/com.tcf2j.powerof1/databases/"; // database path
    private static final String DB_NAME = "po1.db";// database name
    private static final int DB_VER = 1; // database version
    private static final String Table = "question";// database table
    private static final String QCol = "Question";// database column
    private static final String YesCol = "Yes"; // Yes column in the database
    private static final String  NoCol = "No"; // No column in the database

    private static final String TAG = DBHelper.class.getSimpleName(); // get name for database exception

    private SQLiteDatabase db; // SQLlite database
    private Context context;   // context variable

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }


    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<>(); // create and object arraylist
        db = getReadableDatabase(); // gets readable database
        Cursor c = db.rawQuery("SELECT * FROM " + Table, null); // get raw data for database

        if (c.moveToFirst()) {
            do {
                Question qu = new Question();// get new question object
                qu.setQuestion(c.getString(c.getColumnIndex(QCol)));// get data from database
                qu.setNoValue(c.getInt(c.getColumnIndex(NoCol))); // get data from database
                qu.setYesValue(c.getInt(c.getColumnIndex(YesCol))); // get data from database
                qu.setOption1("Yes"); // option for radio button
                qu.setOption2("No");// option for radio button
                questionList.add(qu); //add question to ArrayList
            } while (c.moveToNext()); // move to next record in the database
        }
        c.close();
        return questionList; // return ArrayList


    }

    // check if database is exist
    public boolean checkDataBase() {
        boolean checkDB = false;
        try {
            File file = new File(DATABASE_PATH);// return true if in exist
            checkDB = file.exists();
        } catch (SQLiteException e) {
            Log.d(TAG, e.getMessage());
        }
        return checkDB; // return false if in exist
    }

    // Method to open database
    public void openDataBase() throws SQLException {
        String path = DATABASE_PATH + DB_NAME;
        db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS | SQLiteDatabase.CREATE_IF_NECESSARY); // open database
        Log.d("open","Open");
    }

    // copy database from assets folder
    public void CopyDataBaseFromAsset() throws IOException {
        InputStream in = context.getAssets().open(DB_NAME); // open asset
        Log.e("sample", "Starting copying");
        String outputFileName = DATABASE_PATH + DB_NAME;
        File databaseFile = new File("/data/data/com.tcf2j.powerof1/databases");
        // check if databases folder exists, if not create one and its subfolders
        if (!databaseFile.exists()) {
            databaseFile.mkdir();
        }
        OutputStream out = new FileOutputStream(outputFileName);

        byte[] buffer = new byte[1024];
        int length;


        while ((length = in.read(buffer)) > 0) {
            out.write(buffer, 0, length);
        }
        Log.e("sample", "Completed");
        out.flush(); // flush buffer
        out.close(); // close file
        in.close(); // close file

    }

    // for deleting database if needed
    public void deleteDb() {
        File file = new File(DATABASE_PATH);
        if (file.exists()) {
            file.delete();
            Log.d(TAG, "Database deleted.");
        }
    }
}