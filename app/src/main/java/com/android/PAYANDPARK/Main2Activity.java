package com.android.PAYANDPARK;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    Button submit;
    EditText name;
    EditText std;
    EditText prn;
    EditText pass;
    EditText amt;
    int amt1;
    int prn1;
    String pass1;
    String std1;
    String name1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        submit = (Button) findViewById(R.id.sub);
        name = (EditText) findViewById(R.id.name);
        std = (EditText) findViewById(R.id.std);
        prn = (EditText) findViewById(R.id.prn);
        pass = (EditText) findViewById(R.id.pass);
        amt = (EditText) findViewById(R.id.amt);
        final SQLiteDatabase mydb = this.openOrCreateDatabase("User.db", MODE_PRIVATE, null);

        submit.setOnClickListener(new View.OnClickListener() {
                                      //@Override
                                      public void onClick(View view) {
                                          try {
                                              mydb.execSQL("CREATE TABLE IF NOT EXISTS user (S_name VARCHAR(20),S_std VARCHAR(20),S_prn INT(20) PRIMARY KEY,S_pass VARCHAR(20),S_amt INT(20),CHECK (S_amt>=0))");
                                              name1 = name.getText().toString();
                                              std1 = std.getText().toString();
                                              pass1 = pass.getText().toString();
                                              prn1 = Integer.parseInt(prn.getText().toString());
                                              amt1 = Integer.parseInt(amt.getText().toString());
                                              String ins = "INSERT INTO user VALUES ('"+name1+"','"+std1+"',"+prn1+",'"+pass1+"',"+amt1+")";
                                              mydb.execSQL(ins);
                                              //Toast.makeText(this, "Saved to" + getFilesDir() + "/databases/user.db", Toast.LENGTH_LONG).show();
                                              Toast.makeText(Main2Activity.this, "Saved to" + getFilesDir() + "/databases/User.db", Toast.LENGTH_LONG).show();
                                              mydb.close();
                                              finish();
                                              //openactivity1();
                                          } catch (Exception e) {
                                              e.printStackTrace();
                                             Toast.makeText(Main2Activity.this, "NOT A VALID ENTRY!!", Toast.LENGTH_LONG).show();
                                          }
                                      }
                                  }

        );


    }
    void openactivity1()
    {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
