package com.softcom.yeldi.todo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by YELDI on 09-02-2016.
 */
public class Broadcastreceiversnooze extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "snoozed alarm",
                Toast.LENGTH_LONG).show();

 /*       Toast.makeText(context, "Happy Birthday to " +message,
                Toast.LENGTH_LONG).show();*/
       /* Intent snoozeIntent = new Intent(context, AlertSnooze.class);
        snoozeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
     context.startActivity(snoozeIntent);*/



    /*    Vibrator vibrator = (Vibrator) context
                .getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(2000);*/

  /*      Bundle b = intent.getExtras();
        String recv_message = b.getString("SEND_MESSAGE");
        Log.e("Recievedmsg", "" + recv_message);
        Intent secIntent = new Intent(context, AlertDialogClass.class);
        secIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //   secIntent.putExtra("SEC_RINGTONE_URI", uriString);

        secIntent.putExtra("SEC_MESSAGE_SEND", recv_message);

        context.startActivity(secIntent);*/
    }

}
