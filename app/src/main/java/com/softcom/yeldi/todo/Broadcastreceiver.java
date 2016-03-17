package com.softcom.yeldi.todo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by YELDI on 08-02-2016.
 */
public class Broadcastreceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        String message = intent.getStringExtra("EXTRA");
        int student_Id = intent.getIntExtra("student_Id", 0);
        String passMessage = intent.getStringExtra("MESSAGE_SEND");
       String getDat = intent.getStringExtra("DATE_SEND");
        String  getTim = intent.getStringExtra("TIME_SEND");

        Log.e("newmesage", "" + message);
 /*       Toast.makeText(context, "Happy Birthday to " +message,
                Toast.LENGTH_LONG).show();*/
        Intent secIntent = new Intent(context, AlertDialogClass.class);
        secIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        secIntent.putExtra("SEC_MESSAGE_SEND", message);
        secIntent.putExtra("student_Id", 0);
        secIntent.putExtra("MESSAGE_SEND", passMessage);
        secIntent.putExtra("DATE_SEND", getDat);
        secIntent.putExtra("TIME_SEND", getTim);
        context.startActivity(secIntent);



      /*  Bundle b = intent.getExtras();
        String recv_message = b.getString("EXTRA");
        Log.e("Recievedmsg", "" + recv_message);
        Intent secIntent = new Intent(context, AlertDialogClass.class);
        secIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        secIntent.putExtra("SEC_MESSAGE_SEND", recv_message);

        context.startActivity(secIntent);*/

        // Vibrate the mobile phone
    /*    Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        Ringtone r = RingtoneManager.getRingtone(context, notification);
        r.play();
      /*
        Log.e("newmesage", "" + message);
        Toast.makeText(context, "Happy Birthday to " +message,
                Toast.LENGTH_LONG).show();

        Vibrator vibrator = (Vibrator) context
                .getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(2000);*/

/*         Intent alarmIntent = new Intent("android.intent.action.MAIN");
     alarmIntent.setClass(context, AlertDialogClass.class);
        alarmIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Start the popup activity

        context.startActivity(alarmIntent);*/
    }

}
