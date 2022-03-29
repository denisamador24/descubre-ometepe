package com.example.descubreometepe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.example.descubreometepe.adaptadores.Imagenes;
import com.example.descubreometepe.adaptadores.VolleySingleton;
import com.example.descubreometepe.ui.main.LugarInfoFragment;

public class ActivityVerImagen extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_imagen);

        getSupportActionBar().setTitle("Imagen");

        String url = getIntent().getExtras().getString("url");

        imageView = (ImageView) findViewById(R.id.imageView_VerImagen);

        CargarImagen(url);
    }

    private void CargarImagen(String url) {
        ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                imageView.setImageBitmap(response);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), ActivityVerImagen.class);
                        intent.putExtra("url", url);
                        startActivity(intent);
                    }
                });
            }
        }, 0, 0, ImageView.ScaleType.CENTER, null,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                       // CargarImagen2(nombre);
                    }
                });

        VolleySingleton.getVolleyInstacia(this).addToRequestQueue(imageRequest);
    }
}