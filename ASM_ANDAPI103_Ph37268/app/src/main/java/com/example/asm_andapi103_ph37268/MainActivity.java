package com.example.asm_andapi103_ph37268;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
           Intent intent=new Intent(MainActivity.this, HomeActivity.class);
           startActivity(intent);
            }
        }, 3000); // 5000 milliseconds = 5 giây
    }
}