package com.example.descubreometepe.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.descubreometepe.R;

import java.util.List;

public class CategoriasAdapter extends BaseAdapter {

    private Context context;
    private List<Categorias> categoriasList;

    public CategoriasAdapter(Context context, List<Categorias> categoriasList) {
        this.context = context;
        this.categoriasList = categoriasList;
    }


    @Override
    public int getCount() {
        return categoriasList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView textViewCategoria;
        ImageView imageViewCategoria;

        Categorias categorias = categoriasList.get(position);

        if(convertView == null)
            convertView = LayoutInflater.from(context).inflate(R.layout.categorias, null);

        textViewCategoria = convertView.findViewById(R.id.textViewCategoria);
        imageViewCategoria = convertView.findViewById(R.id.imageViewCategorias);

        imageViewCategoria.setImageResource(categorias.idImagen);
        textViewCategoria.setText(categorias.Categoria);

        return convertView;
    }
}
