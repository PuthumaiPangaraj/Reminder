package com.softcom.yeldi.todo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class RemainderList extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    String getvalue;
    public static final String MyPREFERENCES = "MyPrefs" ;
    private static final String TAG = "ArrayAdapterListViewActivity";
    EditText editText;
    Button addButton;
    TextView textView;
    SimpleArrayAdapter adapter;
    ListView listview;
    ArrayList<String> arrayList;
    Runnable run;
    static final int READ_BLOCK_SIZE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remainder_list);
        TextView  secInfo = (TextView)findViewById(R.id.list);
      /*  try {
            FileInputStream fileIn=openFileInput("mytextfile.txt");
            InputStreamReader InputRead= new InputStreamReader(fileIn);

            char[] inputBuffer= new char[READ_BLOCK_SIZE];
            String s="";
            int charRead;

            while ((charRead=InputRead.read(inputBuffer))>0) {
                // char to string conversion
                String readstring=String.copyValueOf(inputBuffer,0,charRead);
                s +=readstring;
            }
            InputRead.close();
            secInfo.setText(s);
            Toast.makeText(getBaseContext(), s, Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }*/
      /*  TextView  secInfo = (TextView)findViewById(R.id.list);

        Bundle extras = getIntent().getExtras();
        String receivemessage = extras.getString("EXTRA");
        Log.e("alertmsg", "" + receivemessage);

       sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        getvalue =  sharedpreferences.getString("Name", "null");
        Log.e("alertmsg", "" + getvalue);
       // secInfo.setText(getvalue);

       // addButton = (Button) findViewById(R.id.addButton);
        textView = (TextView) findViewById(R.id.textView);
      //ss  editText = (EditText) findViewById(R.id.editText);
        listview = (ListView) findViewById(R.id.listview);

        String[] values = new String[] { "Lion",
                "Leopard",
                 };

        arrayList = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            arrayList.add(values[i]);
        }
        adapter = new SimpleArrayAdapter(this,
                android.R.layout.simple_list_item_1, arrayList);
        listview.setAdapter(adapter);
        adapter.add(getvalue);
        arrayList.add(getvalue);
        adapter.add(receivemessage);
        arrayList.add(receivemessage);
//        editText.setText("");
        adapter.notifyDataSetChanged();
        listview.smoothScrollToPosition(adapter.getCount() - 1);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                view.animate().setDuration(2000).alpha(0)
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                textView.setText("Selected: "+ item);

                                //to remove item from list
                                //list.remove(item);
                                //adapter.notifyDataSetChanged();

                                view.setAlpha(1);
                            }
                        });
            }

        });*/

   /*     editText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    return addItem();
                }
                return false;
            }
        });*/

      /*  addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                addItem();
            }
        });*/
    }

   /* private boolean addItem(){
        adapter.add(getvalue);
        arrayList.add(getvalue);
        editText.setText("");
        adapter.notifyDataSetChanged();
        listview.smoothScrollToPosition(adapter.getCount() - 1);
        return true;
    }*/
}