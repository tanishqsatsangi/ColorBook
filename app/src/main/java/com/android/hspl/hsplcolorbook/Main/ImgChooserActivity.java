package com.android.hspl.hsplcolorbook.Main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.android.hspl.hsplcolorbook.R;

public class ImgChooserActivity extends AppCompatActivity {

    ImageView img1,img2,img3,img4,img4in1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_chooser);
    }

    public  void clicked(View v){
        Intent intent=new Intent(ImgChooserActivity.this,AlphabetActivity.class);
        intent.putExtra("resid",R.drawable.ic_anew);
        startActivity(intent);
    }
}
