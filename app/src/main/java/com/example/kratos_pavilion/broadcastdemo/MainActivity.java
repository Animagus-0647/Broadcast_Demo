package com.example.kratos_pavilion.broadcastdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TextView textView;
    private RecyclerView.LayoutManager layoutManager;
    private  ArrayList<IncomingNumber> arrayList=new ArrayList<>();
    private RecyclerAdapter adapter;
    private BroadcastReceiver broadcastReceiver;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerView);
        textView=findViewById(R.id.emptytxt);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter=new RecyclerAdapter(arrayList);
        recyclerView.setAdapter(adapter);
        readfrmDb();

        broadcastReceiver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
              readfrmDb();
            }
        };
    }
    private void readfrmDb()
    {
        arrayList.clear();
        DbHelper dbHelper=new DbHelper(this);
        SQLiteDatabase database=dbHelper.getWritableDatabase();
        Cursor cursor=dbHelper.readNum(database);
        if(cursor.getCount()>0)
        {

            while(cursor.moveToNext())
            {
                String number;
                int id;
                number=cursor.getString(cursor.getColumnIndex(Dbcontract.Incoming_number));
                id=cursor.getInt(cursor.getColumnIndex("ID"));
                arrayList.add(new IncomingNumber(id,number));
            }
            cursor.close();
            dbHelper.close();
            adapter.notifyDataSetChanged();
            recyclerView.setVisibility(View.VISIBLE);
            textView.setVisibility(View.GONE);
        }
        cursor.close();
        dbHelper.close();
    }
    protected void onResume()
    {
         super.onResume();
         registerReceiver(broadcastReceiver,new IntentFilter(Dbcontract.UPDATE_UI_FILTER));
    }
    protected void onPause()
    {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }
}
