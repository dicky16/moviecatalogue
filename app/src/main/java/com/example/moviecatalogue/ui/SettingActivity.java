package com.example.moviecatalogue.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.moviecatalogue.R;
import com.example.moviecatalogue.alarm.MySettingPreferenceFragment;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        //preference
        getSupportFragmentManager().beginTransaction().add(R.id.setting_holder, new MySettingPreferenceFragment()).commit();


    }
}
