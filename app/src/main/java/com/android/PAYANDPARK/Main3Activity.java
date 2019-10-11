package com.android.PAYANDPARK;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {
    int prn_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Button sub=(Button) findViewById(R.id.sub);
        final EditText uname=(EditText) findViewById(R.id.uname);
        final EditText pass=(EditText) findViewById(R.id.pass);
        final SQLiteDatabase mydb = this.openOrCreateDatabase("User.db", MODE_PRIVATE, null);
        sub.setOnClickListener(      new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                try{
                    int fl=0;

                    int uname1 = Integer.parseInt(uname.getText().toString());
                    String pass1 = pass.getText().toString();
                    String s1="select * from user where S_prn="+uname1+" and S_pass='"+pass1+"'";
                    Cursor c=mydb.rawQuery(s1,null);
                    if(c.moveToFirst())
                    {
                        Intent it=new Intent(Main3Activity.this,Main4Activity.class);
                        it.putExtra("message",uname1);
                        finish();
                        startActivity(it);
                    }
                    else
                    {
                        Toast.makeText(Main3Activity.this, "INCORRECT USERNAME OR PASSWORD", Toast.LENGTH_LONG).show();
                        finish();
                        startActivity(getIntent());

                    }
                    c.close();
                    mydb.close();

                }
                catch(NumberFormatException e)
                {
                    e.printStackTrace();
                    Toast.makeText(Main3Activity.this, "INCORRECT USERNAME OR PASSWORD", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}
