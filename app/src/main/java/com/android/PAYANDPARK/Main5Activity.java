package com.android.PAYANDPARK;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main5Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        Button cnfm=(Button)findViewById(R.id.cnfm);
        Intent intent=getIntent();
        final int prn_2=intent.getIntExtra("message",0);
        final EditText amt=(EditText)findViewById(R.id.amt);
        final SQLiteDatabase mydb = this.openOrCreateDatabase("User.db", MODE_PRIVATE, null);
        cnfm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    int no=Integer.parseInt(amt.getText().toString());
                    String s="update user set S_amt=S_amt+"+no+" where S_prn="+prn_2;
                    mydb.execSQL(s);
                    Toast.makeText(Main5Activity.this,"UPDATED!!",Toast.LENGTH_LONG).show();
                    mydb.close();
                    finish();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                    Toast.makeText(Main5Activity.this,"Invalid!!",Toast.LENGTH_LONG).show();
                    finish();
                    startActivity(getIntent());
                }
            }
        });
    }
}
