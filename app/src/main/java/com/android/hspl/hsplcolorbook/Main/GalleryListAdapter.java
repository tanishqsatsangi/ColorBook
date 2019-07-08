package com.android.hspl.hsplcolorbook.Main;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.android.hspl.hsplcolorbook.Models.Gallerymodel;
import com.android.hspl.hsplcolorbook.R;

import java.util.ArrayList;

public class GalleryListAdapter extends ArrayAdapter<Gallerymodel> {

    public GalleryListAdapter(Context context, ArrayList<Gallerymodel> list) {
        super(context, 0, list);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        if (itemView == null) {
            itemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.galleryview, parent, false);

        }
        Gallerymodel currentimg = getItem(position);
        ImageView galleryimgview = (ImageView) itemView.findViewById(R.id.galleryimg);
        Bitmap bmp = currentimg.getBitmap().copy(Bitmap.Config.ARGB_8888, true);
        galleryimgview.setImageBitmap(bmp);
        return super.getView(position, convertView, parent);


    }
}
