package com.android.hspl.hsplcolorbook.Adapters;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.hspl.hsplcolorbook.Main.AlphabetActivity;
import com.android.hspl.hsplcolorbook.Main.GalleryFragment;
import com.android.hspl.hsplcolorbook.R;

import java.io.File;
import java.util.ArrayList;

public class GalleryRecyclerViewAdapter extends RecyclerView.Adapter<GalleryRecyclerViewAdapter.ViewHolder> {

    private ArrayList<Bitmap> bitmaps = new ArrayList<>();
    GalleryFragment g;
    File fileDirectory;

    public GalleryRecyclerViewAdapter(GalleryFragment galleryFragment, ArrayList<Bitmap> bitmapslist, File Fd) {
        bitmaps = bitmapslist;
        g = galleryFragment;
        fileDirectory = Fd;
    }

    public GalleryRecyclerViewAdapter() {

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.galleryview, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final int j = i;
        final Bitmap bitmap = bitmaps.get(i).copy(Bitmap.Config.ARGB_8888, true);
        Toast.makeText(g.getContext(), "" + bitmap, Toast.LENGTH_SHORT).show();
        BitmapFactory.Options options = null;
        viewHolder.galleryimg.setImageBitmap(bitmap);
        viewHolder.galleryimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(g.getContext(), AlphabetActivity.class);
                g.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bitmaps.size();

    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView galleryimg;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            galleryimg = (ImageView) itemView.findViewById(R.id.galleryimg);
        }
    }
}
