package com.softcom.yeldi.todo;


import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
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

import java.sql.Time;
import java.util.GregorianCalendar;

public class AlertSnooze extends Activity {
    TextView secInfo;
    Button btnStop,btnSnooz;
    Ringtone ringTone;
    private int pHour;
    private int pMinute;
    private int pYear;
    private int pMonth;
    private int pDay;
    final static int RQS_1 = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alaramsnooze);

        btnStop = (Button)findViewById(R.id.setalarm);
        btnSnooz = (Button)findViewById(R.id.snooze);
        secInfo = (TextView)findViewById(R.id.edtnotes);


        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        ringTone = RingtoneManager.getRingtone(getApplicationContext(), notification);
        ringTone.play();

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ringTone.stop();
                finish();
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

}
