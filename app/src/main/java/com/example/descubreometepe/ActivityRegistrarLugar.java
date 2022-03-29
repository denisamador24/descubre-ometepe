package com.example.descubreometepe;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
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
import java.util.List;
import java.util.Map;

public class ActivityRegistrarLugar extends AppCompatActivity {

    //private RecyclerView recyclerViewAgregarImagenes;
    private ImageView imageView;
    private EditText textViewNombre;
    private EditText textViewHorarios;
    private EditText textViewDescripcion;
    private EditText textViewDireccion;
    private EditText textViewTelefono;
    private EditText editTextComarca;
    private Spinner spinner;
    private Bitmap imagen;
    private boolean ya = false;
    //private int cantidadImagen = 0;
    String idUsuario;
    ProgressDialog dialog;

    private static final int COD_SELECCIONA = 10;

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
        setContentView(R.layout.activity_registrar_lugar);

        idUsuario = getIntent().getExtras().getString("idUsuario");

        //recyclerViewAgregarImagenes = (RecyclerView) findViewById(R.id.recyclerView_agregarIganes_nuevoLugar);
        imageView = (ImageView) findViewById(R.id.imageView_AgregarImagenes_Registrar);
        textViewNombre = (EditText) findViewById(R.id.editText_NombreLugar_Registrarse);
        editTextComarca = (EditText) findViewById(R.id.editTextComarca_Registarse);
        textViewDescripcion = (EditText) findViewById(R.id.editTextDesccripcion_Registrarse);
        textViewDireccion = (EditText) findViewById(R.id.editTextDireccion_Registrarse);
        textViewHorarios = (EditText) findViewById(R.id.editTextHorarios_Registrarse);
        textViewTelefono = (EditText) findViewById(R.id.editTextTelefono_Registrase);
        spinner = (Spinner) findViewById(R.id.spinnerCategorias_Registrase);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categorias);
        spinner.setAdapter(adapter);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/");
                startActivityForResult(intent.createChooser(intent, getString(R.string.seleccione)), COD_SELECCIONA);
            }
        });

        /*imagen = new ArrayList<>();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cantidadImagen < 4) {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/");
                    startActivityForResult(intent.createChooser(intent, "Seleccione"), COD_SELECCIONA);
                }else {
                    Toast.makeText(getApplicationContext(), "Solamente puedes agragar 4 imagens, luegos puedes agregar mas" +
                            "en mis luares", Toast.LENGTH_LONG).show();
                }
            }
        });*/

    }

    public void Crear(View view){


        if(ya) {
            String url = getString(R.string.Servidor1) + "/RegistrarLugar.php";
            String nombre, comarca, telefono, horarios, direccion, descripcion, categoria;

            nombre = textViewNombre.getText().toString();
            comarca = editTextComarca.getText().toString();
            telefono = textViewTelefono.getText().toString();
            horarios = textViewHorarios.getText().toString();
            direccion = textViewDireccion.getText().toString();
            descripcion = textViewDescripcion.getText().toString();
            categoria = spinner.getSelectedItem().toString();

            switch (categoria) {
                case " Restaurantes":
                    categoria = "comida";
                    break;
                    case "Bares":
                        categoria = "bar";
                        break;

                    case "playas":
                        categoria = "playa";
                        break;

                    case "Hoteles":
                        categoria = "hotel";
                        break;

                    case "Locales":
                        categoria = "local";
                        break;

                }

            String categoriaCV = categoria;

            dialog = new ProgressDialog(this);
            dialog.setMessage("Registrando");
            dialog.show();
            StringRequest stringRequest =new  StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(getApplicationContext(), getString(R.string.registroE), Toast.LENGTH_LONG).show();
                            dialog.hide();
                            finish();
                        }
                    }, new Response.ErrorListener() {


                @Override
                public void onErrorResponse(VolleyError error) {
                    dialog.hide();
                    Toast.makeText(getApplicationContext(), getString(R.string.registroErr), Toast.LENGTH_LONG).show();

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
                    stringMap.put("telefono", telefono);
                    stringMap.put("nombre", nombre);
                    stringMap.put("comarca", comarca);
                    stringMap.put("direccion", direccion);
                    stringMap.put("descripcion", descripcion);
                    stringMap.put("horarios", horarios);
                    stringMap.put("categoria", categoriaCV);
                    stringMap.put("pertenece", idUsuario);
                    return  stringMap;
                }
            };

            VolleySingleton.getVolleyInstacia(this).addToRequestQueue(stringRequest);

        } else {
            Toast.makeText(this, getString(R.string.seleccione_1_im_mini), Toast.LENGTH_LONG).show();
        }

    }

   /* private void Agregarimagenes(int position){
        String url = getString(R.string.Servidor1) + "/RegistrarImagenLugar.php";

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
                imagen.get(position).compress(Bitmap.CompressFormat.JPEG,100,stream);
                byte[] imagenArray = stream.toByteArray();
                String imagenString = Base64.encodeToString(imagenArray, Base64.DEFAULT);

                Map<String, String> stringMap = new HashMap<>();
                stringMap.put("imagen", imagenString);
                String nombre = textViewNombre.getText().toString() + position;
                stringMap.put("nombre", nombre);
                stringMap.put("id", String.valueOf(id));
                return  stringMap;
            }
        };

        VolleySingleton.getVolleyInstacia(this).addToRequestQueue(stringRequest);
    }*/
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri path = data.getData();

        if (requestCode == COD_SELECCIONA) {
            try {
                imagen = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), path);
                imageView.setImageBitmap(imagen);
                ya = true;
                //cantidadImagen++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //imagen = redimencionarImagen(imagen, 600, 800);
    }
}