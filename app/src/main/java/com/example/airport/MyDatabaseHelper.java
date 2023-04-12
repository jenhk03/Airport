package com.example.airport;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper
{
    private Context ctx;
    private static final String DATABASE_NAME = "db_airport_0018";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "tbl_airport_0018";
    private static final String FIELD_ID = "id";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_CITY = "city";
    private static final String FIELD_ADDRESS = "address";
    public MyDatabaseHelper(@Nullable Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.ctx = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String query = "CREATE TABLE " + TABLE_NAME + "( " +
                FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FIELD_NAME + " VARCHAR(50), " +
                FIELD_CITY + " VARCHAR(50), " +
                FIELD_ADDRESS + " VARCHAR(50)" +
                ");";
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV)
    {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        onCreate(db);
    }
    public long AddAirport(String name, String city, String address)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(FIELD_NAME, name);
        cv.put(FIELD_CITY, city);
        cv.put(FIELD_ADDRESS, address);
        long exec = db.insert(TABLE_NAME, null, cv);
        return exec;
    }
    public Cursor readDataAirport()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = null;
        if (db != null)
        {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    public long changeAirport(String id, String name, String city, String address)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(FIELD_NAME, name);
        cv.put(FIELD_CITY, city);
        cv.put(FIELD_ADDRESS, address);
        long exec = db.update(TABLE_NAME, cv, "id = ?", new String[]{id});
        return exec;
    }
    public long deleteAirport(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        long exec = db.delete(TABLE_NAME, "id = ?", new String[]{id});
        return exec;
    }
}