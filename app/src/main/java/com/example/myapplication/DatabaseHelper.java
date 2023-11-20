package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "LogBook.db";
    private static final int DATABASE_VERSION = 1;

    // Define the table and its columns
    public static final String TABLE_NAME = "logbook3";
    public static final String ID = "id";
    public static final String COLUMN_NAME_FIELD = "name";
    public static final String DATE_OF_BIRTH_FIELD = "dateOfBirth";
    public static final String EMAIL_FIELD = "email";
    public static final String IMAGE_SELECTED_FIELD = "imageSelected";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME_FIELD + " TEXT, " +
                DATE_OF_BIRTH_FIELD + " TEXT, " +
                EMAIL_FIELD + " TEXT, " +
                IMAGE_SELECTED_FIELD + " TEXT);";

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long addUserData(String name, String dateOfBirth, String email, String iamgeSelected) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME_FIELD, name);
        cv.put(DATE_OF_BIRTH_FIELD, dateOfBirth);
        cv.put(EMAIL_FIELD, email);
        cv.put(IMAGE_SELECTED_FIELD, iamgeSelected);
        long dataAdd = db.insert(TABLE_NAME, null, cv);
        db.close();
        return dataAdd;
    }

    public Cursor getUsersData() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_NAME, null, null, null, null, null, null);
    }

    public int updateUserData(int id, String name, String dateOfBirth, String email, String imageSelected) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_FIELD, name);
        values.put(DATE_OF_BIRTH_FIELD, dateOfBirth);
        values.put(EMAIL_FIELD, email);
        values.put(IMAGE_SELECTED_FIELD, imageSelected);
        int userUpdate = db.update(TABLE_NAME, values, ID + "=?", new String[]{String.valueOf(id)});
        db.close();
        return userUpdate;
    }

    public int deleteUser(int userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int userDelete = db.delete(TABLE_NAME, ID + "=?", new String[]{String.valueOf(userId)});
        db.close();
        return userDelete;
    }
}
