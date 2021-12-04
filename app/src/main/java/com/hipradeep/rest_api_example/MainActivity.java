package com.hipradeep.rest_api_example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.hipradeep.rest_api_example.using_volley.UsingVolleyActivity;


public class MainActivity extends AppCompatActivity {

ImageButton ibtn_volley;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ibtn_volley=findViewById(R.id.ibtn_volley);

        ibtn_volley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, UsingVolleyActivity.class));
            }
        });

    }
}