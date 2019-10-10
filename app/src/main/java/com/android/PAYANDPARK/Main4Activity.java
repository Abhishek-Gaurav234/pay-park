package com.android.PAYANDPARK;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main4Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        Intent in=getIntent();
        final int prn_2=in.getIntExtra("message",0);
        Button update=(Button)findViewById(R.id.update);
        Button check=(Button) findViewById(R.id.check);
        Button pay=(Button)findViewById(R.id.pay);
        Button history=(Button)findViewById(R.id.history);
        TextView tv=(TextView) findViewById(R.id.tv);
        SQLiteDatabase mydb=this.openOrCreateDatabase("User.db",MODE_PRIVATE,null);
        Cursor c=mydb.rawQuery("select * from user where S_prn="+prn_2,null);
        int i=c.getColumnIndex("S_name");
        c.moveToFirst();
        String sss=c.getString(i);
        tv.setText("Welcome "+sss);
        mydb.close();
        c.close();

        check.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View view) {
                                          Intent intent=new Intent(Main4Activity.this,Main8Activity.class);
                                          intent.putExtra("message",prn_2);
                                          startActivity(intent);
                                      }
                                  }

        );

        update.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View view) {
                                          Intent intent=new Intent(Main4Activity.this,Main5Activity.class);
                                          intent.putExtra("message",prn_2);
                                          startActivity(intent);
                                      }
                                  }

        );

        pay.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View view) {
                                          Intent intent=new Intent(Main4Activity.this,Main6Activity.class);
                                          intent.putExtra("message",prn_2);
                                          startActivity(intent);
                                      }
                                  }

        );

        history.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View view) {
                                       Intent intent=new Intent(Main4Activity.this,Main7Activity.class);
                                       intent.putExtra("message",prn_2);
                                       startActivity(intent);
                                   }
                               }

        );


    }
   /* void openactivity5()
    {
        Intent intent=new Intent(this,Main5Activity.class);
        intent.putExtra("message",prn_2);
        startActivity(intent);
    }*/
}
