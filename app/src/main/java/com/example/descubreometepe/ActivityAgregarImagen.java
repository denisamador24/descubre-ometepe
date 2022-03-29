package com.example.descubreometepe;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.descubreometepe.adaptadores.VolleySingleton;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ActivityAgregarImagen extends AppCompatActivity {
    private ImageView imageView;
    private Bitmap imagen;
    private boolean ya = false;
    private int id;
    private String nombre;

    private static final int COD_SELECCIONA = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_imagen);

        id = getIntent().getExtras().getInt("id");
        nombre = getIntent().getExtras().getString("nombre");


        imageView = (ImageView) findViewById(R.id.imageViewNuevaImagen_AgragarImagen);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/");
                startActivityForResult(intent.createChooser(intent, "Seleccione"), COD_SELECCIONA);
            }
        });

    }

    public void Agregar(View view){

        String url = getString(R.string.Servidor1) + "/RegistrarImagenLugar.php";

        if(ya){
            StringRequest stringRequest =new  StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(getApplicationContext(), "Registo Exitoso", Toast.LENGTH_LONG).show();

                }
                }, new Response.ErrorListener() {


                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), "No se pudo registrar", Toast.LENGTH_SHORT).show();

                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    imagen.compress(Bitmap.CompressFormat.JPEG,100,stream);
                    byte[] imagenArray = stream.toByteArray();
                    String imagenString = Base64.encodeToString(imagenArray, Base64.DEFAULT);

                    Map<String, String> stringMap = new HashMap<>();
                    stringMap.put("imagen", imagenString);
                    nombre = nombre + String.valueOf(id);
                    stringMap.put("nombre", nombre);
                    stringMap.put("id", String.valueOf(id));
                    return  stringMap;
                }
            };

            VolleySingleton.getVolleyInstacia(this).addToRequestQueue(stringRequest);
        } else {
            Toast.makeText(getApplicationContext(), "Seleciona una imagen", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == COD_SELECCIONA) {

            if(data != null) {
                Uri path = data.getData();
                try {
                    if (path != null) {
                        imagen = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), path);
                        imageView.setImageBitmap(imagen);
                        ya = true;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(this, "No seleccionaste imagen", Toast.LENGTH_SHORT).show();
            }
        }

        //imagen = redimencionarImagen(imagen, 600, 800);
    }
}