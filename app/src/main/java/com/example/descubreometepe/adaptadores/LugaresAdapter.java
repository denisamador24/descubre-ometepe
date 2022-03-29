package com.example.descubreometepe.adaptadores;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.descubreometepe.R;

import java.util.ArrayList;
import java.util.List;

public class LugaresAdapter extends BaseAdapter {

    private Context context;
    private List<Lugares> lugaresList;

    public LugaresAdapter(Context context, List<Lugares> lugaresList) {
        this.context = context;
        this.lugaresList = lugaresList;
    }

    @Override
    public int getCount() {
        return lugaresList.size();
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

        ImageView imageView;
        TextView textViewNombreLugar;
        TextView textViewComaraca;

        Lugares lugares = lugaresList.get(position);

        if(convertView == null)
            convertView = LayoutInflater.from(context).inflate(R.layout.lugares, null);

        imageView  = (ImageView) convertView.findViewById(R.id.imageViewLugar);
        textViewNombreLugar = (TextView)  convertView.findViewById(R.id.textViewLugar);
        textViewComaraca = (TextView) convertView.findViewById(R.id.textViewComarca);

        byte[] imagenArray = Base64.decode(lugares.getImagen(), Base64.DEFAULT);
        Bitmap imagen = BitmapFactory.decodeByteArray(imagenArray, 0 , imagenArray.length);

        imageView.setImageBitmap(imagen);
        textViewNombreLugar.setText(lugares.getNombreLugar());
        textViewComaraca.setText(lugares.getComarca());

        return convertView;
    }
}
