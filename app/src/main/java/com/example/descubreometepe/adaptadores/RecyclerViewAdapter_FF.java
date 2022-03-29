package com.example.descubreometepe.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.example.descubreometepe.ActivityVerImagen;
import com.example.descubreometepe.R;

import java.util.List;

public class RecyclerViewAdapter_FF extends RecyclerView.Adapter<RecyclerViewAdapter_FF.ViewHolder> {
    private List<Imagenes> mImagenesList;
    private Context mContext;
    private LayoutInflater inflater;
    private static Bitmap imagenVer;

    public RecyclerViewAdapter_FF(List<Imagenes> imagenesList, Context context){
        mImagenesList = imagenesList;
        inflater = LayoutInflater.from(context);
        mContext = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View root = inflater.inflate(R.layout.images, null);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Imagenes imagenes = mImagenesList.get(position);
        String nombre = imagenes.getImagen();
        holder.CargarImagen(nombre);
    }

    @Override
    public int getItemCount() {
        return mImagenesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView_imagenes);

        }

        public void CargarImagen(String nombre) {
            String url = mContext.getString(R.string.Servidor1) + "/" + nombre;

            ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
                @Override
                public void onResponse(Bitmap response) {
                    imageView.setImageBitmap(response);
                    //imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, ActivityVerImagen.class);
                            intent.putExtra("url", url);
                            mContext.startActivity(intent);
                        }
                    });
                }
            }, 0, 0, ImageView.ScaleType.CENTER_CROP, null,
                    new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    CargarImagen2(nombre);
                }
            });

            VolleySingleton.getVolleyInstacia(mContext).addToRequestQueue(imageRequest);
        }

        private void CargarImagen2(String nombre) {
            String url = mContext.getString(R.string.Servidor2) + "/Imagenes/" + nombre;

            ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
                @Override
                public void onResponse(Bitmap response) {
                    imageView.setImageBitmap(response);
                }
            }, 0, 0, ImageView.ScaleType.CENTER, null,
                    new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

            VolleySingleton.getVolleyInstacia(mContext).addToRequestQueue(imageRequest);
        }


    }

    public static Bitmap getImagenVer(){
        return imagenVer;
    }
}
