package com.example.descubreometepe;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.descubreometepe.adaptadores.VolleySingleton;

import java.util.HashMap;
import java.util.Map;

public class ActivityAgregarUsuario extends AppCompatActivity {
    private EditText editTextNombre, editTextUser, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_usuario);

        editTextNombre = findViewById(R.id.editTextTextNombreAgregarUsuario);
        editTextUser = findViewById(R.id.editTextTextUsernameAgregarUsuario);
        editTextPassword = findViewById(R.id.editTextTextPasswordAgrergarUsuario);
    }

    public void AgregarUsuario(View view){
        String nombre = editTextNombre.getText().toString();
        String username = editTextUser.getText().toString();
        String password = editTextPassword.getText().toString();

        if(nombre.equals("")  || username.equals("") || password.equals("")){
            Toast.makeText(this, "Debes ingresar toda infoemacion", Toast.LENGTH_SHORT).show();
        } else {

            String url = getString(R.string.Servidor1) + "/RegistrarUsuario.php";

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(getApplicationContext(), "Reistro exitoso", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), "Error de conexión "+error.toString(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String, String> stringMap = new HashMap<>();

                    stringMap.put("nombre", nombre);
                    stringMap.put("username", username);
                    stringMap.put("password", password);

                    return stringMap;
                }
            };

            VolleySingleton.getVolleyInstacia(this).addToRequestQueue(stringRequest);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}