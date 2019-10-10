package com.android.PAYANDPARK;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class Main8Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);

        Intent intent=getIntent();
        final int prn_2=intent.getIntExtra("message",0);

        SQLiteDatabase mydb=this.openOrCreateDatabase("User.db",MODE_PRIVATE,null);
        TextView tv2=(TextView) findViewById(R.id.tv2);
        try {
            String s = "select * from user where S_prn=" + prn_2;
            Cursor c = mydb.rawQuery(s, null);
            int in = c.getColumnIndex("S_amt");
            c.moveToFirst();
            Log.i("amt",Integer.toString(c.getInt(in)));
            int amt=c.getInt(in);
            tv2.setText(" "+amt);
            mydb.close();
            c.close();

        }
        catch(Exception e)
        {
            e.printStackTrace();
            Toast.makeText(this, "invalid!!", Toast.LENGTH_SHORT).show();
        }

    }
}
