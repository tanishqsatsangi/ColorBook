package com.android.hspl.hsplcolorbook.Test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Button;

import com.android.hspl.hsplcolorbook.Common.QueueLinearFloodFiller;
import com.android.hspl.hsplcolorbook.Common.TouchImageView;
import com.android.hspl.hsplcolorbook.R;

import java.util.ArrayList;

public class ZoomtestActivity extends AppCompatActivity {
    TouchImageView img;
    Bitmap mBitmap;
    Button testbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoomtest);
        img=findViewById(R.id.zoommimgview);
        testbtn=findViewById(R.id.btntestcoolor);
        Bitmap b= BitmapFactory.decodeResource(getResources(),R.drawable.a1low).copy(Bitmap.Config.ARGB_8888,true);
        mBitmap=b.copy(Bitmap.Config.ARGB_8888,true);
        img.setImageBitmap(b);
 img.setOnDoubleTapListener(new tapListner());
    }
    class  tapListner extends GestureDetector.SimpleOnGestureListener{
        tapListner(){
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            int y=(int)e.getY();
            int x=(int)e.getX();
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
                    if (oldColor != newColor )
                    {
                        QueueLinearFloodFiller f=new QueueLinearFloodFiller(mBitmap,sourcecolor,targetColor);
                        f.floodFill(originalImageOffsetX, originalImageOffsetY);

                        img.invalidate();
                    }
                    break;

                default:
                    break;
            }


            return super.onSingleTapConfirmed(e);
        }
    }
}
