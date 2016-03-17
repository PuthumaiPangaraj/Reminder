package com.softcom.yeldi.todo;


import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.net.Uri;
import android.widget.TimePicker;
import android.widget.Toast;

import com.softcom.yeldi.todo.db.DBHelper;
import com.softcom.yeldi.todo.toclass.Reminder;

import java.sql.Time;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class AlertDialogClass extends Activity {
    TextView secInfo;
    Button btnStop,btnSnooz;
    Ringtone ringTone;
    private int pHour;
    private int pMinute;
    private int pYear;
    private int pMonth;
    private int pDay;
    final static int RQS_1 = 1;
    private int _Reminder_Id=0;
    private String _MESSAGE_SEND,DATE_SEND,TIME_SEND;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alaram);

        btnStop = (Button)findViewById(R.id.setalarm);
        btnSnooz = (Button)findViewById(R.id.snooze);
        secInfo = (TextView)findViewById(R.id.edtnotes);

             Bundle extras = getIntent().getExtras();

            String receivemessage = extras.getString("SEC_MESSAGE_SEND");
            Log.e("alertmsg", "" + receivemessage);
            secInfo.setText(receivemessage);

        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        ringTone = RingtoneManager.getRingtone(getApplicationContext(), notification);
        ringTone.play();
        _Reminder_Id =0;
        Intent intent = getIntent();
        _Reminder_Id =intent.getIntExtra("reminder_Id", 0);
        ReminderRepo repo = new ReminderRepo(this);
        Reminder reminder = new Reminder();
        reminder = repo.getStudentById(_Reminder_Id);
        _MESSAGE_SEND = intent.getStringExtra("MESSAGE_SEND");
        DATE_SEND = intent.getStringExtra("DATE_SEND");
        TIME_SEND = intent.getStringExtra("TIME_SEND");
        reminder.time= _MESSAGE_SEND;//Integer.parseInt(editTextAge.getText().toString());
        reminder.date=DATE_SEND;
        reminder.message=TIME_SEND;
        reminder.reminder_ID =_Reminder_Id;

        if (_Reminder_Id==0){
            _Reminder_Id = repo.insert(reminder);
            Toast.makeText(this, "New Reminder Insert", Toast.LENGTH_SHORT).show();
        }else{
            repo.update(reminder);
            Toast.makeText(this,"Reminder Record updated",Toast.LENGTH_SHORT).show();
        }
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ringTone.stop();

                AlertDialogClass.this.finish();
            }
        });
        btnSnooz.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent arg1) {
                ringTone.stop();

                Intent intent = new Intent(getBaseContext(), Broadcastreceiversnooze.class);

                PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), PendingIntent.FLAG_ONE_SHOT, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                GregorianCalendar calendar = new GregorianCalendar(pYear, pMonth, pDay, pHour, pMinute);
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), (1 * 60 * 1000), pendingIntent);
              /*  long currentTimeMillis = System.currentTimeMillis();
                long nextUpdateTimeMillis = currentTimeMillis + 1 * DateUtils.MINUTE_IN_MILLIS;

                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC, nextUpdateTimeMillis, pendingIntent);*/
                Toast.makeText(getBaseContext(), "Alarm is Snoozed for every 1min", Toast.LENGTH_SHORT).show();
                                            finish();

                                            return true;
                                        }
                                    });
/*                btnSnooz.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(getBaseContext(), Broadcastreceiversnooze.class);
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), PendingIntent.FLAG_ONE_SHOT, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                        GregorianCalendar calendar = new GregorianCalendar(pYear, pMonth, pDay, pHour, pMinute);
                        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), (1 * 60 * 1000), pendingIntent);
                        AlertDialogClass.this.finish();
                        Toast.makeText(getBaseContext(), "Alarm is Snoozed for every 1min", Toast.LENGTH_SHORT).show();
                        alarmManager.cancel(pendingIntent);
                        ringTone.stop();


                    }
                });*/

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

        public ArrayList<HashMap<String, String>> getStudentList() {
            //Open connection to read only
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            String selectQuery =  "SELECT  " +
                    Reminder.KEY_ID + "," +
                    Reminder.KEY_time + "," +
                    Reminder.KEY_date + "," +
                    Reminder.KEY_message+
                    " FROM " + Reminder.TABLE;

            //Student student = new Student();
            ArrayList<HashMap<String, String>> studentList = new ArrayList<HashMap<String, String>>();

            Cursor cursor = db.rawQuery(selectQuery, null);
            // looping through all rows and adding to list

            if (cursor.moveToFirst()) {
                do {
                    HashMap<String, String> reminderList = new HashMap<String, String>();
                    reminderList.put("id", cursor.getString(cursor.getColumnIndex(Reminder.KEY_ID)));
                    reminderList.put("message", cursor.getString(cursor.getColumnIndex(Reminder.KEY_message)));
                    reminderList.put("time", cursor.getString(cursor.getColumnIndex(Reminder.KEY_date)));
                    reminderList.put("date", cursor.getString(cursor.getColumnIndex(Reminder.KEY_time)));
                    studentList.add(reminderList);

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
                    reminder.date = cursor.getString(cursor.getColumnIndex(Reminder.KEY_date));

                } while (cursor.moveToNext());
            }

            cursor.close();
            db.close();
            return reminder;
        }

    }

}
