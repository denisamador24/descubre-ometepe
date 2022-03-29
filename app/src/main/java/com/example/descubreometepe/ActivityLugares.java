package com.example.descubreometepe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ActivityLugares extends AppCompatActivity{
    private String categoriaSeleccionada;

    private LugaresFragment lugaresFragmentRestaurantes, lugaresFragmentBares, lugaresFragmentPlayas, lugaresFragmentHoteles, lugaresFragmentLocales;

    private String[] categorias = {
            "Restaurantes",
            "Bares",
            "playas",
            "Hoteles",
            "Locales"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lugares);

       /* categoriaSeleccionada = getIntent().getExtras().getString("categoria");

        String TituloBar = "";
        switch (categoriaSeleccionada){
            case "comida":  TituloBar = "Restaurantes"; break;
            case "bar":     TituloBar = "Bares"; break;
            case "playa" :  TituloBar = "Playas"; break;
            case "hotel":   TituloBar = "Hoteles"; break;
            case "local" :  TituloBar = "Locales"; break;
        }
            getSupportActionBar().setTitle(TituloBar);
*/
    }


    public void CambiarFragment(View view){
        FragmentTransaction  fragmentTransaction = getSupportFragmentManager().beginTransaction();
        int id = view.getId();
        TextView textView;

        switch (id){
            case R.id.cardViewRestaurante:
                if(lugaresFragmentRestaurantes == null){
                    lugaresFragmentRestaurantes = new LugaresFragment("comida");
                    fragmentTransaction.replace(R.id.fragmentLayout_activityLugares, lugaresFragmentRestaurantes);
                } else{
                    fragmentTransaction.replace(R.id.fragmentLayout_activityLugares, lugaresFragmentRestaurantes);
                }
                getSupportActionBar().setTitle(getString(R.string.restaurante));
                break;

            case R.id.cardViewBar:
                if(lugaresFragmentBares == null){
                    lugaresFragmentBares = new LugaresFragment("bar");
                    fragmentTransaction.replace(R.id.fragmentLayout_activityLugares, lugaresFragmentBares);
                } else {
                    fragmentTransaction.replace(R.id.fragmentLayout_activityLugares, lugaresFragmentBares);
                }
                getSupportActionBar().setTitle(getString(R.string.bares));
                break;

            case R.id.cardViewPlaya:
                if(lugaresFragmentPlayas == null){
                    lugaresFragmentPlayas = new LugaresFragment("playa");
                    fragmentTransaction.replace(R.id.fragmentLayout_activityLugares, lugaresFragmentPlayas);
                } else{
                    fragmentTransaction.replace(R.id.fragmentLayout_activityLugares, lugaresFragmentPlayas);
                }
                getSupportActionBar().setTitle(getString(R.string.playas));
                break;

            case R.id.cardViewHoltes:
                if(lugaresFragmentHoteles == null){
                    lugaresFragmentHoteles = new LugaresFragment("hotel");
                    fragmentTransaction.replace(R.id.fragmentLayout_activityLugares, lugaresFragmentHoteles);
                } else {
                    fragmentTransaction.replace(R.id.fragmentLayout_activityLugares, lugaresFragmentHoteles);
                }
                getSupportActionBar().setTitle(getString(R.string.hoteles));
                break;

            case R.id.cardViewLocal:
                if(lugaresFragmentLocales ==  null){
                    lugaresFragmentLocales = new LugaresFragment("local");
                    fragmentTransaction.replace(R.id.fragmentLayout_activityLugares, lugaresFragmentLocales);
                } else {
                    fragmentTransaction.replace(R.id.fragmentLayout_activityLugares, lugaresFragmentLocales);
                }
                getSupportActionBar().setTitle(getString(R.string.local));
                break;
        }
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}