package com.ui.demo.localdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.ui.demo.model.UserInfo;

import java.util.ArrayList;
import java.util.List;

public class MySqlliteOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "UIDemo.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "User";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_AGE = "age";

    private static final String CREATE_ENTRIES = "CREATE TABLE " + TABLE_NAME +
            "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NAME + " TEXT, " + COLUMN_AGE + " TEXT)";

    private static final String DROP_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;


    public MySqlliteOpenHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_ENTRIES);
        onCreate(db);
    }

    public void insertData(UserInfo userInfo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, userInfo.getName());
        contentValues.put(COLUMN_AGE, userInfo.getAge());
        db.insert(TABLE_NAME, null, contentValues);
    }

    public List<UserInfo> getUserInfo() {
        List<UserInfo> userInfoList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_ID, COLUMN_NAME, COLUMN_AGE}, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME));
                String age = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_AGE));
                UserInfo userInfo = new UserInfo(name, age);
                userInfoList.add(userInfo);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return userInfoList;
    }


}
