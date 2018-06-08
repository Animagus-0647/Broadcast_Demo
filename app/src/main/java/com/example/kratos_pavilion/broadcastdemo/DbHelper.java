package com.example.kratos_pavilion.broadcastdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    private static  final String DATABASE_NAME="number.Db";
    private static  final int DATABASE_VERSION=1;
    private static  final String CREATE_TABLE_INFO = "CREATE TABLE " +Dbcontract.TABLE_NAME+" ("+
               Dbcontract.COLUMN_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT " +
            Dbcontract.Incoming_number+ " TEXT" +
            ");";




    public static final String Drop_table="drop table if exists "+Dbcontract.TABLE_NAME;

public DbHelper(Context context)
{
    super(context,DATABASE_NAME,null,DATABASE_VERSION);
}


    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL(CREATE_TABLE_INFO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Drop_table);
        onCreate(db);

    }
    public void savenumber(String number,SQLiteDatabase database)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put(Dbcontract.Incoming_number,number);
        database.insert(Dbcontract.TABLE_NAME,null,contentValues);
    }
    public Cursor readNum(SQLiteDatabase database)
    {
    String[] projection={"id",Dbcontract.Incoming_number};
    return (database.query(Dbcontract.TABLE_NAME,projection,null,null,null,null,null));

    }
}
