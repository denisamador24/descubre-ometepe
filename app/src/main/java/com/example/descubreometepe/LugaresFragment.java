package com.example.descubreometepe;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.descubreometepe.adaptadores.Lugares;
import com.example.descubreometepe.adaptadores.RecyclerViewAdapter_AL;
import com.example.descubreometepe.adaptadores.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LugaresFragment extends Fragment {
    private String categoria;
    private List<Lugares> lugaresList;
    RecyclerView recyclerViewLugares;

    public LugaresFragment(String categoria) {
        this.categoria = categoria;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_lugares, container, false);

        recyclerViewLugares = root.findViewById(R.id.recyclerView_LugaresFragment);
        CargarList();
        return root;
    }

    private void setListRecyclerView(){
        RecyclerViewAdapter_AL adaptador = new RecyclerViewAdapter_AL(lugaresList, getContext());
        recyclerViewLugares.setHasFixedSize(true);
        recyclerViewLugares.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewLugares.setAdapter(adaptador);
    }

    private void CargarList(){
        String url = getString(R.string.Servidor1) + "/Lugares.php?categoria="+categoria;
        lugaresList = new ArrayList<>();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        JSONArray jsonArray = response.optJSONArray("lugares");

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
                Toast.makeText(getContext(), ""+ error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        VolleySingleton.getVolleyInstacia(getContext()).addToRequestQueue(jsonObjectRequest);
    }

}