package com.android.PAYANDPARK;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

public class Main7Activity extends AppCompatActivity {

    ListView lst;
    List<String> date1=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        lst = (ListView) findViewById(R.id.listvw);
        Intent intent = getIntent();
        final int prn_2 = intent.getIntExtra("message", 0);
        try{
        final SQLiteDatabase mydb = this.openOrCreateDatabase("User.db", MODE_PRIVATE, null);
        String s1 = "select * from logs where S_prn=" + prn_2 + " order by date1 desc";
        Cursor c = mydb.rawQuery(s1, null);
        int in = c.getColumnIndex("date1");
        c.moveToFirst();
        do{
            Log.i("date", c.getString(in));
            String ss=c.getString(in);
            date1.add(ss);

        }while(c.moveToNext());
        c.close();
        ArrayAdapter<String> array = new ArrayAdapter<String>(Main7Activity.this, android.R.layout.simple_list_item_1,date1);
        lst.setAdapter(array);
    }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
