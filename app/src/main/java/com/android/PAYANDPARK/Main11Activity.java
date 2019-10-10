package com.android.PAYANDPARK;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Main11Activity extends AppCompatActivity {

    List<String> name=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main11);
        Intent inx=getIntent();
        String date1=inx.getStringExtra("message");

        TextView tv=(TextView)findViewById(R.id.tv);

        final SQLiteDatabase mydb = this.openOrCreateDatabase("User.db", MODE_PRIVATE, null);
        ListView list=(ListView)findViewById(R.id.lv1);

        String s4="select sum(amount) as total from logs group by date1 having date1='"+date1+"'";
        Cursor c2=mydb.rawQuery(s4,null);
        int in2=c2.getColumnIndex("total");
        c2.moveToFirst();
        String sum=c2.getString(in2);
        c2.close();
        tv.setText("TOTAL AMOUNT COLLECTED: "+sum);

        try{

            String s1 = "select * from logs where date1='"+date1+"'";
            Cursor c = mydb.rawQuery(s1, null);
            int in = c.getColumnIndex("S_prn");
            c.moveToFirst();
            do{
                Log.i("date", c.getString(in));
                int ss=c.getInt(in);
                String s2="select * from user where S_prn="+ss;
                Cursor c1 = mydb.rawQuery(s2, null);
                int in1=c1.getColumnIndex("S_name");
                c1.moveToFirst();
                String s3=c1.getString(in1);
                name.add(s3);
                c1.close();

            }while(c.moveToNext());
            c.close();
            mydb.close();
            ArrayAdapter<String> array = new ArrayAdapter<String>(Main11Activity.this, android.R.layout.simple_list_item_1,name);
            list.setAdapter(array);

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
