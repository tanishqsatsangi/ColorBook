package com.android.hspl.hsplcolorbook.Main;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.hspl.hsplcolorbook.Common.ActionSaver;
import com.android.hspl.hsplcolorbook.Common.QueueLinearFloodFiller;
import com.android.hspl.hsplcolorbook.Common.TouchImageView;
import com.android.hspl.hsplcolorbook.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

public class AlphabetActivity extends AppCompatActivity {
    int s, resid_img;
    Bitmap bitmap, mBitmap;
    TouchImageView img;
    ArrayList<ActionSaver> lastaction = new ArrayList<>();
    ImageButton undobtn, resetbtn, sharebtn;
    int sc, tc;
    Point pt;
    KonfettiView viewKonfetti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabet);
        img = findViewById(R.id.drawing_imgview);
        viewKonfetti = findViewById(R.id.viewKonfetti);
        undobtn = findViewById(R.id.undo);
        resetbtn = findViewById(R.id.reset);
        sharebtn = findViewById(R.id.share);
        PaletteBar paletteBar = findViewById(R.id.colorchooserbox);

        Intent i = getIntent();
        resid_img = i.getIntExtra("resid", R.drawable.ic_anew);
        Bitmap gallerybitmap = getBitmapFromVectorDrawable(this, resid_img);
        //    bitmap = BitmapFactory.decodeResource(getResources(), resid_img).copy(Bitmap.Config.ARGB_8888, true);
        //mBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        mBitmap = gallerybitmap.copy(Bitmap.Config.ARGB_8888, true);
        //Touchable image view
        img.setImageBitmap(mBitmap);
        img.setOnDoubleTapListener(new tapListner());

        paletteBar.setColorMarginPx(8);
        paletteBar.setCOntext(AlphabetActivity.this);
        paletteBar.setListener(new PaletteBar.PaletteBarListener() {
            @Override
            public void onColorSelected(int colorInt) {
                // Use the selected color
                s = colorInt;

            }
        });
        resetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(AlphabetActivity.this)
                        .setTitle("New Drawing")
                        .setMessage("Are you sure you want to start new Drawing")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                //Bitmap newbitmap = BitmapFactory.decodeResource(getResources(), resid_img).copy(Bitmap.Config.ARGB_8888, true);
                                Bitmap newbitmap=getBitmapFromVectorDrawable(AlphabetActivity.this,resid_img).copy(Bitmap.Config.ARGB_8888,true);
                                img.setImageBitmap(newbitmap);
                                lastaction.clear();
                            }
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
        sharebtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                BitmapDrawable drawable = (BitmapDrawable) img.getDrawable();
                Bitmap bitsave = drawable.getBitmap();
                createDirectoryAndSaveFile(bitsave, "drawingabcd" + System.currentTimeMillis() + ".png");
                Toast.makeText(getApplicationContext(), "Drawing is saved", Toast.LENGTH_SHORT).show();
                shareit();
            }
        });

        undobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!lastaction.isEmpty()) {
                    ActionSaver a = lastaction.get(lastaction.size() - 1);
                    lastaction.remove(lastaction.size() - 1);
                    QueueLinearFloodFiller f = new QueueLinearFloodFiller(mBitmap, a.getReplacementcolor(), a.getSourcecolor());
                    f.setTolerance(50);
                    Point pt;
                    pt = a.getP();
                    f.floodFill(pt.x, pt.y);
                    img.setImageBitmap(f.getImage());
                    img.invalidate();
                }
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

    private void createDirectoryAndSaveFile(Bitmap imageToSave, String fileName) {

        File direct = new File(Environment.getExternalStorageDirectory() + "/HSPL");

        if (!direct.exists()) {
            File wallpaperDirectory = new File("/sdcard/HSPL/");
            wallpaperDirectory.mkdirs();
        }

        File file = new File(new File("/sdcard/HSPL/"), fileName);
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(file);
            imageToSave.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        BitmapDrawable drawable = (BitmapDrawable) img.getDrawable();
        Bitmap bitsave = drawable.getBitmap();
        createDirectoryAndSaveFile(bitsave, "drawingabcd" + System.currentTimeMillis() + ".png");

        Toast.makeText(getApplicationContext(), "Image is Saved", Toast.LENGTH_SHORT).show();
    }

    class tapListner extends GestureDetector.SimpleOnGestureListener {
        tapListner() {
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            int x = (int) e.getX();
            int y = (int) e.getY();
            final int sourcecolor = mBitmap.getPixel(x, y);
            int targetColor = s;
            int action = e.getAction();
            ArrayList<Integer> arrX = new ArrayList<>();
            ArrayList<Integer> arrY = new ArrayList<>();
            switch (action & MotionEvent.ACTION_MASK) {
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
                    int originalImageOffsetX = (int)(scaledImageOffsetX * widthRatio);
                    int originalImageOffsetY = (int)(scaledImageOffsetY * heightRatio);
                    int oldColor = mBitmap.getPixel(originalImageOffsetX, originalImageOffsetY);
                    arrX.add(originalImageOffsetX);
                    arrY.add(originalImageOffsetY);
                    int index = arrX.size();
                    int newColor = s;
                    newColor = targetColor;
                    if (oldColor!=newColor ) {
                        Point p = new Point();
                        p.x = originalImageOffsetX;
                        p.y = originalImageOffsetY;
                        ActionSaver act = new ActionSaver(p, sourcecolor, targetColor);
                        lastaction.add(act);

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
    }

    private void shareit() {
        Bitmap icon = mBitmap;
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/png");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        icon.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(getContentResolver(),
                icon, "HSPL Drawing ", "Drawn using HSPL COLORBOOK");
        Uri imageUri = Uri.parse(path);
        share.putExtra(Intent.EXTRA_STREAM, imageUri);
        startActivity(Intent.createChooser(share, "Share Via"));
    }

    class KonfettiTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            viewKonfetti.build()
                    .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                    .setDirection(0.0, 359.0)
                    .setSpeed(1f, 5f)
                    .setFadeOutEnabled(true)
                    .setTimeToLive(2000L)
                    .addShapes(Shape.RECT, Shape.CIRCLE)
                    .addSizes(new Size(12, 5))
                    .setPosition(-50f, viewKonfetti.getWidth() + 50f, -50f, -50f)
                    .streamFor(300, 5000L);


            return null;
        }
    }

    class TheTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {

        QueueLinearFloodFiller f = new QueueLinearFloodFiller(mBitmap, sc, tc);
            f.setTolerance(0);
            f.floodFill(pt.x, pt.y);
            img.setImageBitmap(f.getImage());
            img.invalidate();
            return null;
        }
    }


}

