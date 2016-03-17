package com.softcom.yeldi.todo.db;

/**
 * Created by YELDI on 11/02/2016.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.softcom.yeldi.todo.toclass.Reminder;


public class DBHelper extends SQLiteOpenHelper {
    //version number to upgrade database version
    //each time if you Add, Edit table, you need to change the
    //version number.
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "reminder.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //All necessary tables you like to create will create here

        String CREATE_TABLE_STUDENT = "CREATE TABLE " + Reminder.TABLE  + "("
                + Reminder.KEY_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Reminder.KEY_message + " TEXT, "
                + Reminder.KEY_time + " TEXT, "
                + Reminder.KEY_date + " TEXT )";

        db.execSQL(CREATE_TABLE_STUDENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed, all data will be gone!!!
        db.execSQL("DROP TABLE IF EXISTS " + Reminder.TABLE);

        // Create tables again
        onCreate(db);

    }

}
