package com.hipradeep.rest_api_example.using_volley;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hipradeep.rest_api_example.R;
import com.hipradeep.rest_api_example.using_volley.fragments.GetFragment;
import com.hipradeep.rest_api_example.using_volley.fragments.PostFragment;

public class UsingVolleyActivity extends AppCompatActivity implements View.OnClickListener   {

    private Fragment currentFragment;
    private Button btn_get, btn_post;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_using_volley);
        btn_get=findViewById(R.id.btn_get);
        btn_post=findViewById(R.id.btn_post);
        btn_get.setOnClickListener(this);
        btn_post.setOnClickListener(this);

        //default fragments
        replaceFragment(new GetFragment(), "");
    }

    private void replaceFragment(Fragment setFragment, String title) {
        //new Handler().postDelayed(() -> {
        currentFragment = setFragment;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, setFragment, title);
        //toolbar.setTitle(title);
        transaction.commitAllowingStateLoss();
        // }, 200);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_get:
                if (!(currentFragment instanceof GetFragment))
                    replaceFragment(new GetFragment(), "");
                break;
            case R.id.btn_post:
                if (!(currentFragment instanceof PostFragment))
                    replaceFragment(new PostFragment(), "");
                break;
        }


    }
}