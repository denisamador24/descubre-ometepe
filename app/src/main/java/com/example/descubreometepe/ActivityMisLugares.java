package com.example.descubreometepe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.descubreometepe.adaptadores.Lugares;
import com.example.descubreometepe.adaptadores.RecyclerViewAdapter_AL;
import com.example.descubreometepe.adaptadores.VolleySingleton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ActivityMisLugares extends AppCompatActivity {
    private RecyclerView recyclerViewMisLugares;
    private List<Lugares> lugaresList;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_lugares);

        id = getIntent().getExtras().getString("idUsuario");
        lugaresList = new ArrayList<>();

        FloatingActionButton fab = findViewById(R.id.fabAgregarLugar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ActivityRegistrarLugar.class);
                intent.putExtra("idUsuario", id);
                startActivity(intent);
            }
        });

        recyclerViewMisLugares = findViewById(R.id.recyclerView_MisLuagres);
        CargarMisLugares();
    }

    private void setListRecyclerView(){
        RecyclerViewAdapter_AL adaptador = new RecyclerViewAdapter_AL(lugaresList, this);
        recyclerViewMisLugares.setHasFixedSize(true);
        recyclerViewMisLugares.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewMisLugares.setAdapter(adaptador);
    }

    public void CargarMisLugares(){
        String url = getString(R.string.Servidor1) + "/MisLugares.php?pertenece=" + id;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                JSONArray jsonArray = response.optJSONArray("mislugares");

                String  nombre,
                        comarca,
                        direccion,
                        descripcion,
                        telefono,
                        horario,
                        imagen;
                int id;

                JSONObject jsonObject2 = null;

                for(int i = 0; i < jsonArray.length(); i++) {
                    try {
                        jsonObject2 = jsonArray.getJSONObject(i);

                        nombre = jsonObject2.optString("nombre");
                        comarca = jsonObject2.optString("comarca");
                        direccion = jsonObject2.optString("direccion");
                        descripcion = jsonObject2.optString("descripcion");
                        telefono = jsonObject2.optString("telefono");
                        horario = jsonObject2.optString("horario");
                        imagen = jsonObject2.optString("imagen");
                        id = jsonObject2.optInt("id");

                        lugaresList.add(new Lugares(nombre, comarca, direccion, descripcion, telefono, horario, imagen, id));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                setListRecyclerView();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        VolleySingleton.getVolleyInstacia(this).addToRequestQueue(jsonObjectRequest);
    }
}