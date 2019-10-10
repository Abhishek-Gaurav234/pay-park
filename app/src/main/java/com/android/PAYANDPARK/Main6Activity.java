package com.android.PAYANDPARK;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class Main6Activity extends AppCompatActivity {

    DatePickerDialog.OnDateSetListener mdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        Button proceed=(Button)findViewById(R.id.proceed);
        Button cancel=(Button)findViewById(R.id.cancel);
        final TextView dte=(TextView) findViewById(R.id.dte);
        final TextView tv3=(TextView)findViewById(R.id.tv3);
        Intent intent=getIntent();
        final int prn_2=intent.getIntExtra("message",0);
        final SQLiteDatabase mydb = this.openOrCreateDatabase("User.db", MODE_PRIVATE, null);
        final String s4;
        try {
            String ss = "select * from park where Availability='y'";
            Cursor c = mydb.rawQuery(ss, null);
            c.moveToFirst();
            int id = c.getColumnIndex("Slot");
            s4 = c.getString(id);
            tv3.setText("Slot: " + s4);
            c.close();


            dte.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Calendar cal = Calendar.getInstance();
                    int year = cal.get(Calendar.YEAR);
                    int month = cal.get(Calendar.MONTH);
                    int day = cal.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog dialog = new DatePickerDialog(
                            Main6Activity.this,
                            android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                            mdate,
                            year, month, day
                    );
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();
                }
            });

            mdate = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    month++;
                    String sp = day + "-" + month + "-" + year;
                    dte.setText(sp);
                }
            };


            proceed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        mydb.execSQL("CREATE TABLE IF NOT EXISTS logs (S_prn INT(20),date1 DATE,amount int(10))");
                        String d = dte.getText().toString();
                        String s1 = "update user set S_amt=S_amt-50 where S_prn=" + prn_2;
                        String s2 = "insert into logs values(" + prn_2 + ",'" + d + "',50)";
                        String s3 = "update park set Availability='N' where Slot='" + s4 + "'";
                        mydb.execSQL(s1);
                        mydb.execSQL(s2);
                        mydb.execSQL(s3);
                        Toast.makeText(Main6Activity.this, "AMOUNT DEDUCTED", Toast.LENGTH_LONG).show();
                        mydb.close();
                        finish();
                    } catch (Exception e) {
                        Toast.makeText(Main6Activity.this, "LOW BALANCE", Toast.LENGTH_LONG).show();
                        finish();
                    }
                }
            });

            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(Main6Activity.this, "TRANSACTION ABORTED!!", Toast.LENGTH_LONG).show();
                    finish();
                }
            });
        }
        catch(Exception e)
        {
            e.printStackTrace();
            //tv3.setText("NO Slot available");
            Toast.makeText(Main6Activity.this, "NO Slot available!!", Toast.LENGTH_LONG).show();
            finish();
        }

    }
}
