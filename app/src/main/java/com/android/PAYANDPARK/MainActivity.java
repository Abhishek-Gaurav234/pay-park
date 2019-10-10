package com.android.PAYANDPARK;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SQLiteDatabase mydb = this.openOrCreateDatabase("User.db", MODE_PRIVATE, null);


        Button create=(Button) findViewById(R.id.signup);
        Button login=(Button) findViewById(R.id.login);
        Button admin=(Button) findViewById(R.id.admin);

        createpark();
        create.setOnClickListener(      new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    openactivity2();
                }
                catch(NumberFormatException e)
                {
                    Toast.makeText(MainActivity.this,"enter nos!!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        login.setOnClickListener(      new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    openactivity3();
                }
                catch(NumberFormatException e)
                {
                    Toast.makeText(MainActivity.this,"enter nos!!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        admin.setOnClickListener(      new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    openactivity9();
                }
                catch(NumberFormatException e)
                {
                    Toast.makeText(MainActivity.this,"enter nos!!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void openactivity2()
    {
        Intent intent=new Intent(this,Main2Activity.class);
        startActivity(intent);
    }

    public void openactivity3()
    {
        Intent intent=new Intent(this,Main3Activity.class);
        startActivity(intent);
    }

    public void openactivity9()
    {
        Intent intent=new Intent(this,Main9Activity.class);
        startActivity(intent);
    }

    public void createpark()
    {
        try{
        final SQLiteDatabase mydb = this.openOrCreateDatabase("User.db", MODE_PRIVATE, null);
        mydb.execSQL("DROP TABLE IF EXISTS park");
        mydb.execSQL("create table park(Slot char(20),Availability char(1) default ('y'))");
        mydb.execSQL("insert into park (Slot) values('P-101'),('P-102'),('P-103'),('P-104'),('P-105'),('P-106'),('P-107'),('P-108'),('P-109'),('P-110')");
    }
    catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
