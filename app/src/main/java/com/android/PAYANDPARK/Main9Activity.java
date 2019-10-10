package com.android.PAYANDPARK;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main9Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);

        Button verify=(Button) findViewById(R.id.verify);
        final EditText pin=(EditText) findViewById(R.id.pin);

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s=pin.getText().toString();
                if(s.equals("pass@123"))
                {
                    Intent i=new Intent(Main9Activity.this,Main10Activity.class);
                    finish();
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(Main9Activity.this,"INVALID!!",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
