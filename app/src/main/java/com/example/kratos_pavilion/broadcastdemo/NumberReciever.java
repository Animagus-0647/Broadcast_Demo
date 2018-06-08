package com.example.kratos_pavilion.broadcastdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.telephony.TelephonyManager;

public class NumberReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent)
    {
        String state=intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        if(state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
            String number=intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
            DbHelper dbHelper=new DbHelper(context);
            SQLiteDatabase database=dbHelper.getWritableDatabase();
            dbHelper.savenumber(number,database);
            dbHelper.close();
        }
    }
}
