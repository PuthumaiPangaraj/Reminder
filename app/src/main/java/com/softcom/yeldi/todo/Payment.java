package com.softcom.yeldi.todo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Payment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_payment, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(this, MainActivity.class);
                this.startActivity(intent);
                break;
            case R.id.action_remainder:
                Intent i = new Intent(this, RemainderList.class);
                this.startActivity(i);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }
}
