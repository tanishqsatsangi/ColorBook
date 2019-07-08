package com.android.hspl.hsplcolorbook.Test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.hspl.hsplcolorbook.Common.ActionSaver;
import com.android.hspl.hsplcolorbook.Common.QueueLinearFloodFiller;
import com.android.hspl.hsplcolorbook.Main.AlphabetActivity;
import com.android.hspl.hsplcolorbook.R;

import java.util.ArrayList;

public class TestActivity extends AppCompatActivity {
    ImageView img;
    Bitmap mBitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testmainlayout);/**
        img =findViewById(R.id.testimgview);
mBitmap=BitmapFactory.decodeResource(getResources(),R.drawable.b1low).copy(Bitmap.Config.ARGB_8888,true);
        img.setImageBitmap(mBitmap);

        final GestureDetector gd= new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                Toast.makeText(getApplicationContext(),"Single Tap COnfirmed ",Toast.LENGTH_SHORT).show();
                int x=(int)e.getY();
                int y=(int)e.getX();
                final int sourcecolor=mBitmap.getPixel(x,y);
                int targetColor= R.color.materialRed;
                int action = e.getAction();
                ArrayList<Integer> arrX=new ArrayList<>();
                ArrayList<Integer>arrY=new ArrayList<>();
                switch (action & MotionEvent.ACTION_MASK)
                {
                    case MotionEvent.ACTION_DOWN:
                        //ScaleImageView img = (ScaleImageView) findViewById(R.id.iv_coloring_figure);
                        BitmapDrawable drawable = (BitmapDrawable) img.getDrawable();
                        mBitmap = drawable.getBitmap();
                        Rect imageBounds = new Rect();
                        img.getDrawingRect(imageBounds);

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
                        int oldColor = mBitmap.getPixel(originalImageOffsetX, originalImageOffsetY);

                        arrX.add(originalImageOffsetX);
                        arrY.add(originalImageOffsetY);
                        Log.e("arrX", "originalImageOffsetX is " + arrX);
                        Log.e("arrY", "originalImageOffsetY is " + arrY);
                     int   index = arrX.size();

                        int newColor = 0;
                        Log.e("sel", "sel color"+targetColor);
                newColor=targetColor;
                        if (oldColor != newColor && oldColor != Color.BLACK)
                        {

                            QueueLinearFloodFiller f=new QueueLinearFloodFiller(mBitmap,sourcecolor,targetColor);
                            f.floodFill(originalImageOffsetX, originalImageOffsetY);
        */
                           /*Point p = new Point();
                           p.x=originalImageOffsetX;
                           p.y=originalImageOffsetY;
                           new TheTask(mBitmap,p,sourcecolor,targetColor);
                            */
          /*                 img.invalidate();
                        }
                        break;

                    default:
                        break;
                }

                return super.onSingleTapConfirmed(e);
            }
        });

//here yourView is the View on which you want to set the double tap action

        img.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                return gd.onTouchEvent(event);
            }
        });
        */

}

    class TheTask extends AsyncTask<Void, Integer, Void> {

        Bitmap bmp;
        Point pt;
        int replacementColor, targetColor;

        public TheTask(Bitmap bm, Point p, int sc, int tc) {
            this.bmp = bm;
            this.pt = p;
            this.replacementColor = tc;
            this.targetColor = sc;
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onProgressUpdate(Integer... values) {

        }

        @Override
        protected Void doInBackground(Void... params) {

            QueueLinearFloodFiller f=new QueueLinearFloodFiller(bmp,targetColor,replacementColor);
            f.floodFill(pt.x,pt.y);
            mBitmap=f.getImage();

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
        img.setImageBitmap(mBitmap);
        }
    }


}
