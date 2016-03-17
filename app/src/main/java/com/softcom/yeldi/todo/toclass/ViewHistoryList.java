package com.softcom.yeldi.todo.toclass;

import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;


import com.softcom.yeldi.todo.Birthday;
import com.softcom.yeldi.todo.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by YELDI on 12/02/2016.
 */
public class ViewHistoryList extends ListActivity {
    TextView student_Id;
    ReminderDetail.ReminderRepo repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_activity);
        repo  = new ReminderDetail.ReminderRepo(this);

        ArrayList<HashMap<String, String>> reminderList =  repo.getReminderList();
        if(reminderList.size()!=0) {
            ListView lv = getListView();

           lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
               @Override
               public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                   student_Id = (TextView) view.findViewById(R.id.reminder_Id);
                   AlertDialog.Builder alert = new AlertDialog.Builder(ViewHistoryList.this);
                   alert.setTitle("Do you want Delete or Edit ?");
                   alert.setIcon(R.mipmap.ic_launcher);
                   alert.setPositiveButton("Edit", new DialogInterface.OnClickListener() {

                       public void onClick(DialogInterface dialog, int which) {
                           // TODO Auto-generated method stub
                           Intent i = new Intent(getApplicationContext(), Birthday.class);
                           i.putExtra("id", 0);
                           startActivity(i);
                       }
                   });

                   alert.setNegativeButton("Delete", new DialogInterface.OnClickListener() {

                       public void onClick(DialogInterface dialog, int which) {
                           // TODO Auto-generated method stub

                           String studentId = student_Id.getText().toString();
                           repo.delete(Integer.parseInt(studentId));
                           Intent intent = getIntent();
                           finish();
                           startActivity(intent);

                       }
                   });

                   /*Intent objIndent = new Intent(getApplicationContext(), ReminderDetail.class);
                   objIndent.putExtra("reminder_Id", Integer.parseInt(studentId));
                   startActivity(objIndent);*/
                   alert.show();
                   return true;
               }
           });

          /*  lv.setOnItemLongClickListener(new OnItemLongClickListener(){
                @Override
                public boolean onItemLongClick(AdapterView<?> av, View v, int pos, long id) {
                    String selectedItem = items.get(pos);
                    items.remove(selectedItem);
                    adapter.notifyDataSetChanged();
                    return true;
                }
            });*/
            ListAdapter adapter = new SimpleAdapter(ViewHistoryList.this,reminderList, R.layout.view_reminder_entry, new String[]
                    {"id","message","time","date"}, new int[] {R.id.reminder_Id,R.id.reminder_message,R.id.reminder_Time,R.id.reminder_date});
            setListAdapter(adapter);
        }else{
            Toast.makeText(this, "No student!", Toast.LENGTH_SHORT).show();
        }

    }
}