package com.android.PAYANDPARK;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Main10Activity extends AppCompatActivity implements AdapterView.OnItemClickListener {


    List<String> date1=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main10);

        ListView list=(ListView)findViewById(R.id.lv);

        try{
            final SQLiteDatabase mydb = this.openOrCreateDatabase("User.db", MODE_PRIVATE, null);
            String s1 = "select * from logs group by date1 order by date1 desc";
            Cursor c = mydb.rawQuery(s1, null);
            int in = c.getColumnIndex("date1");
            c.moveToFirst();
            do{
                Log.i("date", c.getString(in));
                String ss=c.getString(in);
                date1.add(ss);

            }while(c.moveToNext());
            c.close();
            mydb.close();
            ArrayAdapter<String> array = new ArrayAdapter<String>(Main10Activity.this, android.R.layout.simple_list_item_1,date1);
            list.setAdapter(array);
            list.setOnItemClickListener(this);

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        TextView tv=(TextView) view;
        Intent in=new Intent(this,Main11Activity.class);
        in.putExtra("message",tv.getText());
        startActivity(in);

    }
}
