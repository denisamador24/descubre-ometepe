package com.example.descubreometepe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.descubreometepe.adaptadores.Categorias;
import com.example.descubreometepe.adaptadores.CategoriasAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_DescubreOmetepe);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void Comenzar(View view){
        Intent intent = new Intent(this, ActivityLugares.class);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.items, menu);

            return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.item_nuevo_lugar){

            SharedPreferences preferencias = getSharedPreferences("datos", Context.MODE_PRIVATE);
            String idUsuario = preferencias.getString("id", "");

             if(idUsuario.equals("")) {
                 Intent intent = new Intent(getApplicationContext(), ActivityRegistrarse.class);
                 startActivity(intent);
             } else {
                 Intent intent = new Intent(this, ActivityMisLugares.class);
                 intent.putExtra("idUsuario", idUsuario);
                 startActivity(intent);
             }
        }
        return super.onOptionsItemSelected(item);
    }
}