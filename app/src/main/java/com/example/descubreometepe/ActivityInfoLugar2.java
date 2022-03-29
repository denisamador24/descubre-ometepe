package com.example.descubreometepe;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.example.descubreometepe.adaptadores.Lugares;
import com.example.descubreometepe.adaptadores.RecyclerViewAdapter_AL;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.descubreometepe.ui.main.SectionsPagerAdapter;

public class ActivityInfoLugar2 extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_lugar2);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());

        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        FloatingActionButton fab = findViewById(R.id.fabLlamar);
        Lugares lugares = RecyclerViewAdapter_AL.getLugares();
        String telefono = lugares.getTelefono();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!telefono.equals("")) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:"+telefono));
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Este lugar no tiene numero de telefono", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

     @Override
    public void onBackPressed() {
        finish();
    }
}