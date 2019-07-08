package com.android.hspl.hsplcolorbook.Main;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.hspl.hsplcolorbook.Adapters.GalleryRecyclerViewAdapter;
import com.android.hspl.hsplcolorbook.R;

import java.io.File;
import java.util.ArrayList;

public class GalleryFragment extends Fragment {
    RecyclerView galleryrecyclerview;
    ImageView img;
    File fileDirectory;
    ArrayList<Bitmap> bitmaps = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gallery, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        galleryrecyclerview = (RecyclerView) view.findViewById(R.id.galleryRecyclerview);
        ImageView closebtn = (ImageView) view.findViewById(R.id.galleryclosebtn);
        closebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        openDirectoryAndReadFile();
        GalleryRecyclerViewAdapter adapter = new GalleryRecyclerViewAdapter(GalleryFragment.this, bitmaps, fileDirectory);
        galleryrecyclerview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        galleryrecyclerview.setAdapter(adapter);
        galleryrecyclerview.setHasFixedSize(true);
        openDirectoryAndReadFile();
    }

    private void openDirectoryAndReadFile() {
        fileDirectory = new File(Environment.getExternalStorageDirectory() + "/HSPL");
// lists all the files into an array
        File[] dirFiles = fileDirectory.listFiles();
        if (dirFiles.length != 0) {
            // loops through the array of files, outputing the name to console
            for (File dirFile : dirFiles) {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                Bitmap abitmap = BitmapFactory.decodeFile(dirFile.getAbsolutePath(), options);
                bitmaps.add(abitmap);
            }
        }
    }

}
