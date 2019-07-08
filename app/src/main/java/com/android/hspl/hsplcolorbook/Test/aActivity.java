package com.android.hspl.hsplcolorbook.Test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.hspl.hsplcolorbook.Common.ActionSaver;
import com.android.hspl.hsplcolorbook.Common.QueueLinearFloodFiller;
import com.android.hspl.hsplcolorbook.R;

import java.util.ArrayList;

public class aActivity extends AppCompatActivity {
    ImageView aimg;
    Bitmap b;
    int sc,tc;
    Point pt;
    GestureDetector gd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        aimg=findViewById(R.id.aimgview);
        b=getBitmapFromVectorDrawable(this,R.drawable.ic_anew).copy(Bitmap.Config.ARGB_8888,true);
        aimg.setImageBitmap(b);

        final GestureDetector gd= new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
             Toast.makeText(getApplicationContext(),"clicked",Toast.LENGTH_SHORT).show();
                int x = (int) e.getX();
                int y = (int) e.getY();
                final int sourcecolor = b.getPixel(x, y);
                int targetColor = Color.RED;
                int action = e.getAction();
                ArrayList<Integer> arrX = new ArrayList<>();
                ArrayList<Integer> arrY = new ArrayList<>();
                switch (action & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        //ScaleImageView img = (ScaleImageView) findViewById(R.id.iv_coloring_figure);
                        BitmapDrawable drawable = (BitmapDrawable) aimg.getDrawable();
                        b= drawable.getBitmap();
                        Rect imageBounds = new Rect();
                        aimg.getDrawingRect(imageBounds);
                        // original height and width of the bitmap
                        int intrinsicHeight = drawable.getIntrinsicHeight();
                        int intrinsicWidth = drawable.getIntrinsicWidth();
                        // height and width of the visible (scaled) image
                        int scaledHeight = imageBounds.height();
                        int scaledWidth = imageBounds.width();
                        // Find the ratio of the original image to the scaled image Should normally be equal unless a disproportionate scaling (e.g. fitXY) is used.
                        float heightRatio = (float) intrinsicHeight / scaledHeight;
                        float widthRatio = (float) intrinsicWidth / scaledWidth;
                        // do whatever magic to get your touch point
                        // MotionEvent event;
                        // get the distance from the left and top of the image bounds
                        float scaledImageOffsetX = e.getX() - imageBounds.left;
                        float scaledImageOffsetY = e.getY() - imageBounds.top;
                        // scale these distances according to the ratio of your scaling
                        // For example, if the original image is 1.5x the size of the scaled image, and your offset is (10, 20), your original image offset values should be (15, 30).
                        int originalImageOffsetX = Math.round(scaledImageOffsetX * widthRatio);
                        int originalImageOffsetY = Math.round(scaledImageOffsetY * heightRatio);
                        int oldColor = b.getPixel(originalImageOffsetX, originalImageOffsetY);
                        arrX.add(originalImageOffsetX);
                        arrY.add(originalImageOffsetY);
                        int index = arrX.size();
                        int newColor ;
                        newColor = targetColor;
                        if (oldColor!=newColor ) {
                            Point p = new Point();
                            p.x = originalImageOffsetX;
                            p.y = originalImageOffsetY;
                            sc = sourcecolor;
                            tc = targetColor;
                            pt = new Point();
                            pt.x = p.x;
                            pt.y = p.y;
                            new TheTask().execute();
                        }
                        break;
                    default:
                        break;
                }
                return super.onSingleTapConfirmed(e);
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                Toast.makeText(getApplicationContext(),"Double Tap conirm",Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                super.onLongPress(e);

            }

            @Override
            public boolean onDoubleTapEvent(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

        });

        aimg.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                return gd.onTouchEvent(event);
            }
        });
    }


    public Bitmap getBitmapFromVectorDrawable(Context context, int drawableId) {
        Drawable drawable = ContextCompat.getDrawable(context, drawableId);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            drawable = (DrawableCompat.wrap(drawable)).mutate();
        }
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    class TheTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            QueueLinearFloodFiller f = new QueueLinearFloodFiller(b, sc, tc);
            f.setTolerance(0);
            f.floodFill(pt.x, pt.y);
            aimg.setImageBitmap(f.getImage());
            aimg.invalidate();
            return null;
        }
    }

}
