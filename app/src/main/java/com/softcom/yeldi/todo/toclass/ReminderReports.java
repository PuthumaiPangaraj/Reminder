package com.softcom.yeldi.todo.toclass;

/**
 * Created by YELDI on 11/02/2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.softcom.yeldi.todo.db.DBHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class ReminderReports {
    private DBHelper dbHelper;

    public ReminderReports(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int insert(Reminder reminder) {

        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Reminder.KEY_message, reminder.message);
        values.put(Reminder.KEY_time, reminder.time);
        values.put(Reminder.KEY_date, reminder.date);

        // Inserting Row
        long reminder_Id = db.insert(Reminder.TABLE, null, values);
        db.close(); // Closing database connection
        return (int) reminder_Id;
    }

    public void delete(int reminder_Id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(Reminder.TABLE, Reminder.KEY_ID + "= ?", new String[] { String.valueOf(reminder_Id) });
        db.close(); // Closing database connection
    }

    public void update(Reminder reminder) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Reminder.KEY_message, reminder.message);
        values.put(Reminder.KEY_time, reminder.time);
        values.put(Reminder.KEY_date, reminder.date);

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(Reminder.TABLE, values, Reminder.KEY_ID + "= ?", new String[] { String.valueOf(reminder.reminder_ID) });
        db.close(); // Closing database connection
    }

    public ArrayList<HashMap<String, String>>  getStudentList() {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Reminder.KEY_ID + "," +
                Reminder.KEY_message + "," +
                Reminder.KEY_time + "," +
                Reminder.KEY_date+
                " FROM " + Reminder.TABLE;

        //Student student = new Student();
        ArrayList<HashMap<String, String>> studentList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> reminders = new HashMap<String, String>();
                reminders.put("id", cursor.getString(cursor.getColumnIndex(Reminder.KEY_ID)));
                reminders.put("message", cursor.getString(cursor.getColumnIndex(Reminder.KEY_message)));
                reminders.put("time", cursor.getString(cursor.getColumnIndex(Reminder.KEY_time)));
                reminders.put("date", cursor.getString(cursor.getColumnIndex(Reminder.KEY_date)));
                studentList.add(reminders);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return studentList;

    }

    public Reminder getStudentById(int Id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Reminder.KEY_ID + "," +
                Reminder.KEY_message + "," +
                Reminder.KEY_time + "," +
                Reminder.KEY_date +
                " FROM " + Reminder.TABLE
                + " WHERE " +
                Reminder.KEY_ID + "=?";// It's a good practice to use parameter ?, instead of concatenate string

        int iCount =0;
        Reminder reminder = new Reminder();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if (cursor.moveToFirst()) {
            do {
                reminder.reminder_ID =cursor.getInt(cursor.getColumnIndex(Reminder.KEY_ID));
                reminder.message =cursor.getString(cursor.getColumnIndex(Reminder.KEY_message));
                reminder.time  =cursor.getString(cursor.getColumnIndex(Reminder.KEY_time));
                reminder.date =cursor.getString(cursor.getColumnIndex(Reminder.KEY_date));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return reminder;
    }

}