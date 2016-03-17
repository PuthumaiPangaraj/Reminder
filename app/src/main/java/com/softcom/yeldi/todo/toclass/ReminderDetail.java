package com.softcom.yeldi.todo.toclass;

/**
 * Created by YELDI on 11/02/2016.
 */
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;


import com.softcom.yeldi.todo.R;
import com.softcom.yeldi.todo.db.DBHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class ReminderDetail extends ListActivity {


    private int _Reminder_Id=0;
    private String _MESSAGE_GET;
    private String _DATE_GET;
    private String _TIME_GET;
    TextView reminder_Id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remind_detail);
        Intent intent = getIntent();
        _Reminder_Id =intent.getIntExtra("reminder_Id", 0);
        ReminderRepo repo = new ReminderRepo(this);
        Reminder reminder = new Reminder();
        reminder = repo.getStudentById(_Reminder_Id);
        _MESSAGE_GET = intent.getStringExtra("MESSAGE_SEND");
        _DATE_GET = intent.getStringExtra("DATE_SEND");
        _TIME_GET = intent.getStringExtra("TIME_SEND");

        reminder.reminder_ID =_Reminder_Id;
        reminder.message=_MESSAGE_GET;
        reminder.time= _TIME_GET;//Integer.parseInt(editTextAge.getText().toString());
        reminder.date=_DATE_GET;


        if (_Reminder_Id==0){
            _Reminder_Id = repo.insert(reminder);
            Toast.makeText(this,"New Student Insert",Toast.LENGTH_SHORT).show();
        }else{
            repo.update(reminder);
            Toast.makeText(this,"Student Record updated",Toast.LENGTH_SHORT).show();
        }

        ArrayList<HashMap<String, String>> reminderList =  repo.getReminderList();
        if(reminderList.size()!=0) {
            ListView lv = getListView();
            ListAdapter adapter = new SimpleAdapter( ReminderDetail.this,reminderList, R.layout.view_reminder_entry, new String[]
                    { "id","message","time","date"}, new int[] {R.id.reminder_Id, R.id.reminder_message,R.id.reminder_Time,R.id.reminder_date});
            setListAdapter(adapter);
        }else{
            Toast.makeText(this, "No student!", Toast.LENGTH_SHORT).show();
        }
    }


    public static class ReminderRepo {
        private DBHelper dbHelper;

        public ReminderRepo(Context context) {
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
            db.delete(Reminder.TABLE, Reminder.KEY_ID + "= ?", new String[]{String.valueOf(reminder_Id)});
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

        public ArrayList<HashMap<String, String>> getReminderList() {
            //Open connection to read only
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            String selectQuery =  "SELECT  " +
                    Reminder.KEY_ID + "," +
                    Reminder.KEY_message + "," +
                    Reminder.KEY_time + "," +
                    Reminder.KEY_date+
                    " FROM " + Reminder.TABLE;

            //Student student = new Student();
            ArrayList<HashMap<String, String>> reminderList = new ArrayList<HashMap<String, String>>();

            Cursor cursor = db.rawQuery(selectQuery, null);
            // looping through all rows and adding to list

            if (cursor.moveToFirst()) {
                do {
                    HashMap<String, String> reminders = new HashMap<String, String>();
                    reminders.put("id", cursor.getString(cursor.getColumnIndex(Reminder.KEY_ID)));
                    reminders.put("message", cursor.getString(cursor.getColumnIndex(Reminder.KEY_message)));
                    reminders.put("time", cursor.getString(cursor.getColumnIndex(Reminder.KEY_time)));
                    reminders.put("date", cursor.getString(cursor.getColumnIndex(Reminder.KEY_date)));
                    reminderList.add(reminders);

                } while (cursor.moveToNext());
            }

            cursor.close();
            db.close();
            return reminderList;

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
                    reminder.date = cursor.getString(cursor.getColumnIndex(Reminder.KEY_date));

                } while (cursor.moveToNext());
            }

            cursor.close();
            db.close();
            return reminder;
        }

    }
}