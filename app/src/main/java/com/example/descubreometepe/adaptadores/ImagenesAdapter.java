
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

import com.example.descubreometepe.R;

import java.util.List;

public class ImagenesAdapter extends BaseAdapter {

    private Context context;
    private List<Imagenes> imagenesList;

    public ImagenesAdapter(Context context, List<Imagenes> imagenesList) {
        this.context = context;
        this.imagenesList = imagenesList;
    }

    @Override
    public int getCount() {
        return imagenesList.size();
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

        if(convertView == null)
            convertView = LayoutInflater.from(context).inflate(R.layout.images, null);

        Imagenes imagenes = imagenesList.get(position);

        imageView = (ImageView) convertView.findViewById(R.id.imageView_imagenes);

        byte[] imagenArray = Base64.decode(imagenes.getImagen(), Base64.DEFAULT);
        Bitmap imagen = BitmapFactory.decodeByteArray(imagenArray, 0, imagenArray.length);

        imageView.setImageBitmap(imagen);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        return convertView;
    }
}
