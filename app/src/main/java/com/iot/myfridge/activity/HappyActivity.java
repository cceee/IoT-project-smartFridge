package com.iot.myfridge.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.iot.myfridge.R;

public class HappyActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_happy);
        findViewById(R.id.activity_main_separate_rfab_sample_btn).setOnClickListener(this);
        findViewById(R.id.activity_main_label_list_sample_btn).setOnClickListener(this);
        findViewById(R.id.activity_main_rfab_group_btn).setOnClickListener(this);
        findViewById(R.id.activity_main_card_list_sample_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            default:
                break;
        }
    }
}

