package com.hipradeep.rest_api_example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;

import com.hipradeep.rest_api_example.using_retrofit.UsingRetrofitActivity;
import com.hipradeep.rest_api_example.using_retrofit.services.ApiServices;
import com.hipradeep.rest_api_example.using_retrofit.services.ServiceGenerator;

public class MainActivity extends AppCompatActivity {


    ImageButton ibtn_retrofit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ibtn_retrofit=findViewById(R.id.ibtn_retrofit);


        ibtn_retrofit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, UsingRetrofitActivity.class));


            }
        });


    }
}