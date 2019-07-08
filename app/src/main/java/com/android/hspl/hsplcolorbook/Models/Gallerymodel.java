package com.android.hspl.hsplcolorbook.Models;

import android.graphics.Bitmap;

public class Gallerymodel {
    Bitmap bitmap;

    public Gallerymodel() {

    }

    public Gallerymodel(Bitmap mbitmap) {
        bitmap = mbitmap;
    }


    public Bitmap getBitmap() {
        return bitmap;
    }
}
