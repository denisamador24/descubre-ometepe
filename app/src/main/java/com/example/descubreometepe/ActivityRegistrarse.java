package com.example.descubreometepe;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class ActivityRegistrarse extends AppCompatActivity {

    private EditText editTextUser, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);


        editTextUser = (EditText) findViewById(R.id.editTextTextUsename);
        editTextPassword = (EditText) findViewById(R.id.editTextTextPassword);
    }

    public void Iniciar(View view){
        String username = editTextUser.getText().toString();
        String password = editTextPassword.getText().toString();

        String url = getString(R.string.Servidor1) + "/ConsultaMiUsuario.php";

        if(username.equals("")  || password.equals("")){
            Toast.makeText(this, "Ingresa los datos completos", Toast.LENGTH_LONG).show();
        } else {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                if(response.equals("incorreccto")){
                                    Toast.makeText(getApplicationContext(), getString(R.string.incorectoSesion), Toast.LENGTH_SHORT).show();
                                } else  {
                                    SharedPreferences preferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.putString("id", response);
                                    editor.commit();

                                    Intent intent = new Intent(getApplicationContext(), ActivityMisLugares.class);
                                    intent.putExtra("idUsuario", response);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(), getString(R.string.registroErr), Toast.LENGTH_SHORT).show();
                            }
                        }) {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> stringMap = new HashMap<>();

                    stringMap.put("username", username);
                    stringMap.put("password", password);

                    return stringMap;
                }
            };
            VolleySingleton.getVolleyInstacia(this).addToRequestQueue(stringRequest);
        }
    }

    public void CrearCuenta(View view){
        Intent intent = new Intent(this, ActivityAgregarUsuario.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}