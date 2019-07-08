package com.android.hspl.hsplcolorbook.Main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.hspl.hsplcolorbook.Adapters.LetterAdapter;
import com.android.hspl.hsplcolorbook.R;
import com.android.hspl.hsplcolorbook.Test.TextFragment;

import java.util.ArrayList;

public class LetterActivity extends AppCompatActivity {
    TextFragment tf;
    ArrayList<Integer> letterdata;
    ImageView letterimg;
        RecyclerView letterview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter);
            setletterdata();
            letterview=findViewById(R.id.letterrecylerview);
       letterview.setHasFixedSize(true);
       letterview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        LetterAdapter adapter=new LetterAdapter(this,letterdata);

       letterview.setAdapter(adapter);
     //       letterimg=findViewById(R.id.letterimg);
       //     letterimg.setImageResource(R.drawable.letterimg_a);
        Button btn1,btn2;
        btn2=findViewById(R.id.textbtn2);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            tf= new TextFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.textdisplay, tf).commit();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(tf!=null && tf.isVisible() ){

            getSupportFragmentManager().beginTransaction().remove(tf).commit();
        }
        else{
            super.onBackPressed();
        }

    }
    void setletterdata(){
        letterdata=new ArrayList<>();
        letterdata.add(R.drawable.letteraa);
        letterdata.add(R.drawable.letterb);
        letterdata.add(R.drawable.letterc);
        letterdata.add(R.drawable.letterd);
        letterdata.add(R.drawable.lettere);
        letterdata.add(R.drawable.letterf);
        letterdata.add(R.drawable.letterg);
        letterdata.add(R.drawable.letterh);
        letterdata.add(R.drawable.letteri);
        letterdata.add(R.drawable.letterj);
        letterdata.add(R.drawable.letterk);



    }
}
