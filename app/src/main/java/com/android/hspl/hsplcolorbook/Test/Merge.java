package com.android.hspl.hsplcolorbook.Test;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.hspl.hsplcolorbook.Common.QueueLinearFloodFiller;
import com.android.hspl.hsplcolorbook.R;
import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

public class Merge extends AppCompatActivity  {
    int s;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merge);
       final ImageView img=(ImageView)findViewById(R.id.testimg);
         bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.a1).copy(Bitmap.Config.ARGB_8888, true);
        img.setImageBitmap(bitmap);

        Button colorbtn=(Button)findViewById(R.id.colorbtn);
      // img.setImageResource(R.drawable.a1);
        colorbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Buttpn clicked",Toast.LENGTH_SHORT).show();
                ColorPickerDialogBuilder.with(Merge.this).setTitle("Choose Color").initialColor(ContextCompat.getColor(Merge.this,R.color.white) )
                        .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                        .density(12).setOnColorSelectedListener(new OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int selectedColor) {
                        s=selectedColor;
                    }
                }).setPositiveButton("Ok", new ColorPickerClickListener() {
                    @Override
                    public void onClick(DialogInterface d, int lastSelectedColor, Integer[] allColors) {
                        Toast.makeText(getApplicationContext(),"cird"+s,Toast.LENGTH_SHORT).show();

                    }
                }).build().show();
            }
        });

        final GestureDetector gd= new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                Toast.makeText(getApplicationContext(),"Single Tap COnfirmed ",Toast.LENGTH_SHORT).show();
                //color filling event
                colorfilling(e);
                return super.onSingleTapConfirmed(e);
            }

//here is the method for double tap

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                Toast.makeText(getApplicationContext(),"Double Tap conirm",Toast.LENGTH_SHORT).show();
                //your action here for double tap e.g.
                //Log.d("OnDoubleTapListener", "onDoubleTap");

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

//here yourView is the View on which you want to set the double tap action

        img.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                return gd.onTouchEvent(event);
            }
        });

    }
public  void colorfilling(MotionEvent e){
    Point p=new Point();
    p.x=(int)e.getX();
    p.y=(int)e.getY();
    final int scolor=bitmap.getPixel(p.x,p.y);
    final int tcolor=s;
    QueueLinearFloodFiller f=new QueueLinearFloodFiller(bitmap,scolor,tcolor);
    f.floodFill(p.x,p.y);

}

public void Zooming(){

}
}
