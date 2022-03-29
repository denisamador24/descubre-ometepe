package com.example.descubreometepe.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.example.descubreometepe.ActivityInfoLugar2;
import com.example.descubreometepe.R;

import java.util.List;


// Esta Adaptador trabaja con la clase ActivityLugares
public class RecyclerViewAdapter_AL extends RecyclerView.Adapter<RecyclerViewAdapter_AL.ViewHolder> {
    private List<Lugares> lugaresList;
    private LayoutInflater inflater;
    private Context mContext;
    private static  Lugares lugares;

    public RecyclerViewAdapter_AL(List<Lugares> listElemtsList, Context context) {
        this.inflater = LayoutInflater.from(context);
        this.lugaresList = listElemtsList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter_AL.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.lugares, null);
        return new RecyclerViewAdapter_AL.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewAdapter_AL.ViewHolder holder, final int position) {
        holder.bingData(lugaresList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return lugaresList.size();
    }

    public void setItems(List<Lugares> items){
        lugaresList = items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textViewNombre, textViewComarca;

        public ViewHolder(View itemsView){
            super(itemsView);

            imageView = (ImageView) itemView.findViewById(R.id.imageViewLugar);
            textViewNombre = itemView.findViewById(R.id.textViewLugar);
            textViewComarca = itemView.findViewById(R.id.textViewComarca);


        }


        void bingData(final Lugares lugares, int position){
            String url = mContext.getString(R.string.Servidor1) + "/Imagenes/" + lugares.getImagen();

            textViewNombre.setText(lugares.getNombreLugar());
            textViewComarca.setText(lugares.getComarca());

            if(lugares.getImagen().equals("")){
                imageView.setImageResource(R.drawable.local);
            } else {
                CargarImagen(url, lugares.Imagen);
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, ActivityInfoLugar2.class);
                    RecyclerViewAdapter_AL.lugares = lugares;
                    mContext.startActivity(intent);
                }
            });
        }

        private void CargarImagen(String urlImagen, String imagenN) {

            ImageRequest imageRequest = new ImageRequest(urlImagen, new Response.Listener<Bitmap>() {
                @Override
                public void onResponse(Bitmap bitmap) {
                    imageView.setImageBitmap(bitmap);
                }
            }, 0, 0, ImageView.ScaleType.CENTER, null, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    String url = mContext.getString(R.string.Servidor2) + "/Imagenes/" + imagenN;
                    CargarImagen2(url);
                }
            });

            VolleySingleton.getVolleyInstacia(mContext).addToRequestQueue(imageRequest);
        }

        private void CargarImagen2(String urlImagen) {

            ImageRequest imageRequest2 = new ImageRequest(urlImagen, new Response.Listener<Bitmap>() {
                @Override
                public void onResponse(Bitmap bitmap) {
                    imageView.setImageBitmap(bitmap);
                }
            }, 0, 0, ImageView.ScaleType.CENTER, null, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError volleyError) {

                }
            });

            VolleySingleton.getVolleyInstacia(mContext).addToRequestQueue(imageRequest2);
        }

    }

    public static Lugares getLugares(){
        return lugares;
    }
}
