package com.example.megha.todo_miniproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by megha on 3/7/2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="TODO_DATABASE";
    private static final String TABLE_NAME="TODO_DATA_LIST";
    private static final String KEY_ID="id";
    private static final String TITLE="title";
    private static final String DESCRIPTION="discription";
    private static final String DATE="date";
    private static final String STATUS="status";

    public DatabaseHandler(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_TABLE="Create table" + TABLE_NAME + "(" + KEY_ID + "INTEGER PRIMARY KEY," + TITLE +"TEXT,"
                + DESCRIPTION +"TEXT," + DATE + "TEXT," + STATUS +"TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("Drop table if exists "+ TABLE_NAME);
        onCreate(db);
    }

    public void addData(ListClass listClass)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("KEY_ID",listClass.getId());
        values.put("KEY_TITLE",listClass.getTitle());
        values.put("KEY_DESCRIPTION",listClass.getDescription());
        values.put("KEY_DATE",listClass.getDate());
        values.put("KEY_STATUS",listClass.getStatus());
        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public List<ListClass> getFullList()
    {
        List<ListClass> list=new ArrayList<ListClass>();
        String selectQuery="Select * from "+ TABLE_NAME;
        SQLiteDatabase sql_db=this.getWritableDatabase();
        Cursor cursor=sql_db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst())
        {
            do
            {
                ListClass list_data=new ListClass();
                list_data.setId(Integer.parseInt(cursor.getString(0)));
                list_data.setTitle(cursor.getString(1));
                list_data.setDescription(cursor.getString(2));
                list_data.setDate(cursor.getString(3));
                list_data.setStatus(cursor.getInt(4));
                list.add(list_data);
            }
            while(cursor.moveToNext());
        }
        return list;
    }
}
