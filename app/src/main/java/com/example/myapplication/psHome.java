package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;



public class psHome extends AppCompatActivity {
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ps_home);
        final CardView inst = (CardView) findViewById(R.id.card);
        final CardView pick = (CardView) findViewById(R.id.first);
        inst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(psHome.this, Home.class);
                startActivity(intent);
            }
        });

        pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(psHome.this, Home.class);
                startActivity(intent);
            }
        });
    }

}
