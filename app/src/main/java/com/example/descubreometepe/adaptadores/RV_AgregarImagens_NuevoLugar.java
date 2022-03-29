package com.example.descubreometepe.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.descubreometepe.R;

import java.util.ArrayList;
import java.util.List;

public class RV_AgregarImagens_NuevoLugar extends RecyclerView.Adapter<RV_AgregarImagens_NuevoLugar.ViewHolder> {
    private List<Bitmap>  Imagenes;
    private Context context;

    public RV_AgregarImagens_NuevoLugar(Context context, List<Bitmap> imagenes) {
        this.context = context;
        this.Imagenes = imagenes;
        Imagenes = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.images, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setImagen(position);
    }

    @Override
    public int getItemCount() {
        return Imagenes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView_imagenes);
        }

        public void setImagen(int position) {
           Bitmap bitmap = Imagenes.get(position);
           imageView.setImageBitmap(bitmap);
        }
    }
}
