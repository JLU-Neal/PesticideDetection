package com.shizhuayuan.mycamera;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;

public class Menu extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void skip(View view){
        Intent intent=new Intent();
        intent.setClass(Menu.this,CameraActivity.class);
        startActivity(intent);
    }
}


