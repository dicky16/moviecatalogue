package com.example.moviecatalogue.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.moviecatalogue.R;
import com.example.moviecatalogue.adapter.SectionPagerAdapter;
import com.google.android.material.tabs.TabLayout;




public class MainActivity extends AppCompatActivity {
//    private boolean status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

                SectionPagerAdapter sectionPagerAdapter = new SectionPagerAdapter(this, getSupportFragmentManager());
                ViewPager viewPager = findViewById(R.id.view_pager);
                viewPager.setAdapter(sectionPagerAdapter);
                TabLayout tabs = findViewById(R.id.tabs);
                tabs.setupWithViewPager(viewPager);
                getSupportActionBar().setElevation(0);

    }

}
