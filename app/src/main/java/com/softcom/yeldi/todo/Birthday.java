package com.softcom.yeldi.todo;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.softcom.yeldi.todo.toclass.ViewHistoryList;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Birthday extends AppCompatActivity {
    private EditText edt_notes;
    private TextView pDisplayDate,pTime;
    private CheckBox bfrday,bfrehr,evryday,bfr5min;
    private Button pPickDate,pPickTime,setalarm;
    public static final String Name = "nameKey";
    private int pYear;
    private int pMonth;
    private int pDay;
    private int pHour;
    private int pMinute;
    Uri uriAlarm;
    private static int incrementedValue = 0;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;
    static final int TIME_DIALOG_ID = 1;
    /** This integer will uniquely define the dialog to be used for displaying date picker.*/
    static final int DATE_DIALOG_ID = 0;
    final static int RQS_1 = 1;
    static final int READ_BLOCK_SIZE = 100;
    /** Callback received when the user "picks" a date in the dialog */
    private DatePickerDialog.OnDateSetListener pDateSetListener =
            new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                    pYear = year;
                    pMonth = monthOfYear;
                    pDay = dayOfMonth;
                    updateDisplay();
                    displayToast();
                }
            };

    /** Updates the date in the TextView */
    private void updateDisplay() {
        pDisplayDate.setText(
                new StringBuilder()
                        // Month is 0 based so add 1
                        .append(pMonth + 1).append("/")
                        .append(pDay).append("/")
                        .append(pYear).append(" "));
    }

    /** Displays a notification when the date is updated */
    private void displayToast() {
        Toast.makeText(this, new StringBuilder().append("Date choosen is ").append(pDisplayDate.getText()), Toast.LENGTH_SHORT).show();

    }
    private TimePickerDialog.OnTimeSetListener mTimeSetListener =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    pHour = hourOfDay;
                    pMinute = minute;
                    updateDisplayt();
                   // displayToastt();
                }
            };
    private void updateDisplayt() {
        pTime.setText(
                new StringBuilder()
                        .append(pad(pHour)).append(":")
                        .append(pad(pMinute)));
    }

    /** Displays a notification when the time is updated */
   /* private void displayToastt() {
        Toast.makeText(this, new StringBuilder().append("Time choosen is ").append(pTime.getText()),   Toast.LENGTH_SHORT).show();

    }*/
    /** Add padding to numbers less than ten */
    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthday);
        /** Capture our View elements */

        pDisplayDate = (TextView) findViewById(R.id.edtdate);
        pPickDate = (Button) findViewById(R.id.pickDate);

        /** Listener for click event of the button */
        pPickDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });

        /** Get the current date */
        final Calendar cal = Calendar.getInstance();
        pYear = cal.get(Calendar.YEAR);
        pMonth = cal.get(Calendar.MONTH);
        pDay = cal.get(Calendar.DAY_OF_MONTH);
        updateDisplay();

        pTime = (TextView) findViewById(R.id.edttime);
        pPickTime = (Button) findViewById(R.id.button2);

        /** Listener for click event of the button */
        pPickTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(TIME_DIALOG_ID);
            }
        });

        /** Get the current time */
        final Calendar call = Calendar.getInstance();
        pHour = call.get(Calendar.HOUR_OF_DAY);
        pMinute = call.get(Calendar.MINUTE);

        /** Display the current time in the TextView */
        updateDisplayt();

        //setalram button
        setalarm = (Button)findViewById(R.id.setalarm);
        setalarm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                 setAlarm(cal);
                 }

        });

    }

    private void setAlarm(Calendar targetCal) {

        Intent intent = new Intent(getBaseContext(), Broadcastreceiver.class);

        edt_notes = (EditText) findViewById(R.id.edtnotes);

        String message = edt_notes.getText().toString();
        String saveDate = pDisplayDate.getText().toString();
        String saveTime = pTime.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString("EXTRA", message);
        intent.putExtra("KEY_TONE_URL", message);
        intent.putExtra("student_Id", 0);
        //intent.putExtra("MESSAGE_SEND", messagetxt.getText().toString());
        intent.putExtra("MESSAGE_SEND", message);
        intent.putExtra("DATE_SEND", saveDate);
        intent.putExtra("TIME_SEND", saveTime);
        //  evryday = (CheckBox) findViewById(R.id.evyday);
        bfrehr = (CheckBox) findViewById(R.id.bfrehr);
        bfrday = (CheckBox) findViewById(R.id.bfrday);
        bfr5min = (CheckBox) findViewById(R.id.bfr5min);
        // edt_notes.setText("");

       /* if (evryday.isChecked()) {
            intent.putExtras(bundle);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), PendingIntent.FLAG_ONE_SHOT, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            GregorianCalendar calendar = new GregorianCalendar(pYear, pMonth, pDay, pHour, pMinute);
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), (5 * 60 * 1000), pendingIntent);
            Toast.makeText(getBaseContext(), "Alarm is set successfully for every 5min", Toast.LENGTH_SHORT).show();
        }else */
        if (bfr5min.isChecked()) {
            intent.putExtras(bundle);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), RQS_1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            GregorianCalendar calendar = new GregorianCalendar(pYear, pMonth, pDay, pHour, pMinute);
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, (calendar.getTimeInMillis() - (5 * 60 * 1000)), pendingIntent);
            Toast.makeText(getBaseContext(), "Alarm is set successfully Before 5 mins", Toast.LENGTH_SHORT).show();
        } else if (bfrehr.isChecked()) {
            intent.putExtras(bundle);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), PendingIntent.FLAG_ONE_SHOT, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            GregorianCalendar calendar = new GregorianCalendar(pYear, pMonth, pDay, pHour, pMinute);
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
            Toast.makeText(getBaseContext(), "Alarm is set successfully Everyday", Toast.LENGTH_SHORT).show();
        } else if (bfrday.isChecked()) {
            intent.putExtras(bundle);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), RQS_1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            GregorianCalendar calendar = new GregorianCalendar(pYear, pMonth, pDay, pHour, pMinute);
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, (calendar.getTimeInMillis()- (24 * 60 * 60 * 1000 )),pendingIntent);
            Toast.makeText(getBaseContext(), "Alarm is set successfully Before 1 day", Toast.LENGTH_SHORT).show();
        } else  {
            intent.putExtras(bundle);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), RQS_1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        GregorianCalendar calendar = new GregorianCalendar(pYear, pMonth, pDay, pHour, pMinute);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        Toast.makeText(getBaseContext(), "Alarm is set successfully", Toast.LENGTH_SHORT).show();
        }

      /*  sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("Name", edt_notes.getText().toString());
        editor.commit();
        incrementedValue++;*/
       /* try {
            FileOutputStream fileout=openFileOutput("mytextfile.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
            outputWriter.write(edt_notes.getText().toString());
            outputWriter.close();

            //display file saved message
            Toast.makeText(getBaseContext(), "File " +
                            "saved successfully!",
                    Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }*/

    }

    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this,
                        pDateSetListener,
                        pYear, pMonth, pDay);
            case TIME_DIALOG_ID:
                return new TimePickerDialog(this,
                        mTimeSetListener, pHour, pMinute, false);
        }
        return null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_birthday, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
     /*   int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);*/
        switch(item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(this, MainActivity.class);
                this.startActivity(intent);
                break;
            case R.id.action_remainder:
                Intent intentlist = new Intent(this,ViewHistoryList.class);
                //intentlist.putExtra("student_Id",0);
                startActivity(intentlist);
            /*    Intent i = new Intent(this, RemainderList.class);

                edt_notes = (EditText) findViewById(R.id.edtnotes);
                String message = edt_notes.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("EXTRA", message);
                i.putExtras(bundle);
                this.startActivity(i);*/
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }
}
