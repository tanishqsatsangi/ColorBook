package com.android.hspl.hsplcolorbook.Main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.hspl.hsplcolorbook.Paint.PaintMainActivity;
import com.android.hspl.hsplcolorbook.R;
import com.android.hspl.hsplcolorbook.Test.ABcActivity;
import com.android.hspl.hsplcolorbook.Test.TestActivity;
import com.android.hspl.hsplcolorbook.Test.aActivity;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    SharedPreferences sf;
    GalleryFragment gf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sf = getSharedPreferences("Music_Settings", MODE_PRIVATE);
        final ImageView sound_btn = (ImageView) findViewById(R.id.sound_btn);
        SharedPreferences.Editor editor = sf.edit();
        if (sf.getBoolean("state", true)) {
            mediaPlayer = MediaPlayer.create(this, R.raw.background_music);
            mediaPlayer.start();
            mediaPlayer.setLooping(true);
            sound_btn.setAlpha((float) 1.0);
        } else {
            sound_btn.setAlpha((float) 0.3);
        }
        ImageView drawing_btn = (ImageView) findViewById(R.id.drawing_btn);
        ImageView colorfun_btn = (ImageView) findViewById(R.id.colorfun_btn);
        ImageView puzzle_btn = (ImageView) findViewById(R.id.puzzle_btn);
        ImageView gallery_btn = (ImageView) findViewById(R.id.Gallery_btn);

        Button testbtn=findViewById(R.id.testbtn);
        testbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LetterActivity.class));
            }
        });
        drawing_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PaintMainActivity.class));

            }
        });
        colorfun_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Alphabet_Recycler_Activity.class));

            }
        });
        puzzle_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Puzzle");
                alert.setMessage("This Feature is coming Soon");
                alert
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alert.show();
            }
        });

        gallery_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gf = new GalleryFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.rame, gf).commit();

            }
        });
        sound_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sf.getBoolean("state", true)) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    SharedPreferences.Editor editor = sf.edit();
                    editor.putBoolean("state", false);
                    editor.apply();
                    sound_btn.setAlpha((float) 0.3);
                } else {
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.background_music);
                    mediaPlayer.start();
                    mediaPlayer.setLooping(true);
                    sound_btn.setAlpha((float) 1.0);
                    SharedPreferences.Editor editor = sf.edit();
                    editor.putBoolean("state", true);
                    editor.apply();


                }
            }
        });
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (gf != null && gf.isVisible()) {
            getSupportFragmentManager().beginTransaction().remove(gf).commit();
        }

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

}
