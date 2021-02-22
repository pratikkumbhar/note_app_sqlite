package com.example.mynotesapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "NotesDatabase";

    public static final int DATABASE_VERSION = 1;
    public static final String USER_NOTES = "userNotes";
    public static final String ID = "ID";
    public static final String TITLE = "title";
    public static final String NOTE = "note";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table_query = "create table " + USER_NOTES + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TITLE + " TEXT, " + NOTE + " TEXT)";
        db.execSQL(create_table_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+USER_NOTES);
        onCreate(db);
    }


    public boolean insertData(Holder holder)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TITLE,holder.getTitle());
        cv.put(NOTE,holder.getNote());

        final long insert = db.insert(USER_NOTES, null, cv);
        if(insert == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public boolean deleteData(Holder id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
         String deleteQuery = "DELETE FROM " + USER_NOTES + " WHERE " + ID + " = " + id;
        final Cursor cursor = db.rawQuery(deleteQuery, null);
        if(cursor.moveToFirst())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public List<Holder> getAllData()
    {
        List<Holder> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String getAlldata = "SELECT * FROM " + USER_NOTES ;
        final Cursor c = db.rawQuery(getAlldata,null);

        if(c.moveToFirst())
        {
            do{
                int i = c.getInt(0);
                String title= c.getString(1);
                String note = c.getString(2);
                Holder holder = new Holder(i,title,note);
                list.add(holder);
            }while (c.moveToNext());
        }
        else
        {

        }
        c.close();
        db.close();
        return list;

    }

    public boolean updateData(Holder holder)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TITLE,holder.getTitle());
        cv.put(NOTE,holder.getNote());
        Cursor c = db.rawQuery("select * from "+USER_NOTES+ " where "+ID+ " = ?",new String[]{String.valueOf(holder.getId())});

        if(c.getCount()>0) {
            long b =  db.update(USER_NOTES, cv, ID + "=?", new String[] {String.valueOf(holder.getId())});
            if (b == -1) {
                return false;
            } else {
                return true;
            }
        }
        else {
            return false;
        }

    }








}
