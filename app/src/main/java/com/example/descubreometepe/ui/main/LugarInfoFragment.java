package com.example.descubreometepe.ui.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.example.descubreometepe.ActivityVerImagen;
import com.example.descubreometepe.R;
import com.example.descubreometepe.adaptadores.Lugares;
import com.example.descubreometepe.adaptadores.RecyclerViewAdapter_AL;
import com.example.descubreometepe.adaptadores.VolleySingleton;

import org.w3c.dom.Text;

public class LugarInfoFragment extends Fragment {

    private Lugares lugares;
    ImageView imageViewImagen;

    public static LugarInfoFragment newInstance() {
        LugarInfoFragment fragment = new LugarInfoFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lugares = RecyclerViewAdapter_AL.getLugares();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        lugares = RecyclerViewAdapter_AL.getLugares();

        TextView textViewNombre;
        TextView textViewHorarios;
        TextView textViewDescripcion;
        TextView textViewDireccion;
        TextView textViewTelefono;

        View root = inflater.inflate(R.layout.fragment_activity_info_lugar2, container, false);

        CargarImagen(lugares.getImagen());
        imageViewImagen = (ImageView) root.findViewById(R.id.imageView_infoLugares);
        textViewNombre = (TextView) root.findViewById(R.id.textView_infoLugar_Nombre);

        textViewDescripcion = (TextView) root.findViewById(R.id.textView_Descripcion_fragmnetInfoLugar);
        textViewDireccion = (TextView) root.findViewById(R.id.textView_Direccion_fragmentInfoLugar);
        textViewTelefono = (TextView) root.findViewById(R.id.textView_infoLugar_telefono);
        textViewHorarios = (TextView) root.findViewById(R.id.textView_Horarios_fragmentInfoLugar);

        textViewNombre.setText(lugares.getNombreLugar());
        textViewDescripcion.setText(lugares.getDescripcion());
        textViewDireccion.setText(lugares.getDireccion() + ", " + lugares.getComarca());
        textViewHorarios.setText(lugares.getHorario());
        textViewTelefono.setText(lugares.getTelefono());
        imageViewImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ActivityVerImagen.class);
                String url = getString(R.string.Servidor1) + "/Imagenes/" + lugares.getImagen();
                intent.putExtra("url", url);
                startActivity(intent);
            }

        });

        return root;
    }

    private void CargarImagen(String nombre) {

        String url = getString(R.string.Servidor1) + "/Imagenes/" + nombre;

        ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                imageViewImagen.setImageBitmap(bitmap);
            }
        }, 0, 0, ImageView.ScaleType.CENTER, null, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
               // String url = mContext.getString(R.string.Servidor2) + "/Imagenes/" + imagenN;
               // CargarImagen2(url);
            }
        });

        VolleySingleton.getVolleyInstacia(getContext()).addToRequestQueue(imageRequest);

    }
}