package com.example.networking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class global_show_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_show);
        Intent intent = getIntent();
        String place = intent.getStringExtra("PLACE");
        String active=intent.getStringExtra("ACTIVE");
        String recover=intent.getStringExtra("RECOVER");
        String death=intent.getStringExtra("DEATH");
        String total=intent.getStringExtra("TOTAL");

        TextView dis=(TextView)findViewById(R.id.gplace);
        TextView a=(TextView)findViewById(R.id.gactive);
        TextView r=(TextView)findViewById(R.id.grecover);
        TextView d=(TextView)findViewById(R.id.gdeath);
        TextView t=(TextView)findViewById(R.id.gtotal);

        dis.setText(place);
        a.setText(active);
        r.setText(recover);
        d.setText(death);
        t.setText(total);
    }
}
