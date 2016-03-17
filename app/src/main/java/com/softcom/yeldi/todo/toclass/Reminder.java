package com.softcom.yeldi.todo.toclass;

/**
 * Created by YELDI on 11/02/2016.
 */

public class Reminder {
    // Labels table name
    public static final String TABLE = "Reminder";
    // Labels Table Columns names
    public static final String KEY_ID = "id";
    public static final String KEY_message = "message";
    public static final String KEY_time = "time";
    public static final String KEY_date = "date";

    // property help us to keep data
    public int reminder_ID;
    public String message;
    public String time;
    public String date;
}