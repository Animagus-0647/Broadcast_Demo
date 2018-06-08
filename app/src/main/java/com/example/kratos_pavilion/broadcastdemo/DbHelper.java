package com.example.kratos_pavilion.broadcastdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public static  final String DATABASE_NAME="numberDb";
    public static  final int DATABASE_VERSION=1;
    public static  final String CREATE;

    static {
        CREATE = "create table " + (Dbcontract.Table_Name) + "(id integer primary key autoincrement," + (Dbcontract.Incoming_number) + ("text);");
    }

    public static final String Drop_table="drop table if exists "+Dbcontract.Table_Name;

public DbHelper(Context context)
{
    super(context,DATABASE_NAME,null,DATABASE_VERSION);
}


    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL(CREATE);

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
        database.insert(Dbcontract.Table_Name,null,contentValues);
    }
    public Cursor readNum(SQLiteDatabase database)
    {
    String[] projection={"id",Dbcontract.Incoming_number};
    return (database.query(Dbcontract.Table_Name,projection,null,null,null,null,null));

    }
}
