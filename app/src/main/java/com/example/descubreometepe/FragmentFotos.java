package com.example.descubreometepe;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.descubreometepe.adaptadores.Imagenes;
import com.example.descubreometepe.adaptadores.Lugares;
import com.example.descubreometepe.adaptadores.RecyclerViewAdapter_AL;
import com.example.descubreometepe.adaptadores.RecyclerViewAdapter_FF;
import com.example.descubreometepe.adaptadores.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FragmentFotos extends Fragment{

    private RecyclerView recyclerViewImagenes;
    private List<Imagenes> imagenesList;
    public static String ImagenOup;
    private int idLugar;

    public FragmentFotos() {
        // Required empty public constructor
    }

    public static FragmentFotos newInstance() {
        FragmentFotos fragment = new FragmentFotos();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Lugares lugares = RecyclerViewAdapter_AL.getLugares();
        idLugar = lugares.getId();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_fotos, container, false);
        recyclerViewImagenes = (RecyclerView) root.findViewById(R.id.recyclerView_infoLugares_imagenes);


        CargarImages();
        return  root;
    }

    private void setListRecyclerView(){
        RecyclerViewAdapter_FF adaptador = new RecyclerViewAdapter_FF(imagenesList, getContext());
        recyclerViewImagenes.setHasFixedSize(true);
        recyclerViewImagenes.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerViewImagenes.setAdapter(adaptador);
    }


    private void CargarImages(){
        imagenesList = new ArrayList<>();
        String url = getString(R.string.Servidor1) + "/Imagenes.php?pertenece=" + idLugar;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray jsonArray = response.optJSONArray("imagenes");

                String imagen;

                JSONObject jsonObject2 = null;
                try {
                    for (int i = 0; i < jsonArray.length(); i++) {

                        jsonObject2 = jsonArray.getJSONObject(i);

                        imagen = jsonObject2.optString("imagen");

                        imagenesList.add(new Imagenes(imagen));

                    }

                    setListRecyclerView();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            }, new Response.ErrorListener() {


            @Override
            public void onErrorResponse(VolleyError error) {
                String url2 = getString(R.string.Servidor2) + "/Imagenes.php?pertenece="+ idLugar;

                CargarImagen2(url2);
            }
        });

        VolleySingleton.getVolleyInstacia(getContext()).addToRequestQueue(jsonObjectRequest);
    }

    private void CargarImagen2(String url){

        JsonObjectRequest jsonObjectRequest2 = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray jsonArray = response.optJSONArray("imagenes");

                String imagen;

                JSONObject jsonObject2 = null;
                try {
                    for (int i = 0; i < jsonArray.length(); i++) {

                        jsonObject2 = jsonArray.getJSONObject(i);

                        imagen = jsonObject2.optString("imagen");

                        imagenesList.add(new Imagenes(imagen));

                    }
                    setListRecyclerView();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            }, new Response.ErrorListener() {


            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        VolleySingleton.getVolleyInstacia(getContext()).addToRequestQueue(jsonObjectRequest2);
    }

}