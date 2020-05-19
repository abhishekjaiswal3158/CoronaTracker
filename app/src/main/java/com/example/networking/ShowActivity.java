package com.example.networking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        Intent intent = getIntent();
        String district = intent.getStringExtra("DISTRICT");
        String active=intent.getStringExtra("ACTIVE");
        String recover=intent.getStringExtra("RECOVER");
        String death=intent.getStringExtra("DEATH");
        String total=intent.getStringExtra("TOTAL");

        TextView dis=(TextView)findViewById(R.id.splace);
        TextView a=(TextView)findViewById(R.id.sactive);
        TextView r=(TextView)findViewById(R.id.srecover);
        TextView d=(TextView)findViewById(R.id.sdeath);
        TextView t=(TextView)findViewById(R.id.stotal);

        dis.setText(district);
        a.setText(active);
        r.setText(recover);
        d.setText(death);
        t.setText(total);
    }
}
