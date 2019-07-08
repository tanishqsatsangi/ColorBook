package com.android.hspl.hsplcolorbook.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.android.hspl.hsplcolorbook.Main.AlphabetActivity;
import com.android.hspl.hsplcolorbook.Main.ImgChooserActivity;
import com.android.hspl.hsplcolorbook.R;
import com.android.hspl.hsplcolorbook.Test.TextFragment;

import java.util.ArrayList;

public class LetterAdapter extends RecyclerView.Adapter<LetterAdapter.viewholder> {
  Context context;
  ArrayList<Integer> arrayList=new ArrayList<>();
  TextFragment tf;
  public  LetterAdapter(){
      //empty constructor
  }

    public LetterAdapter(Context context, ArrayList<Integer> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(context).inflate(R.layout.letterlayout,viewGroup,false);
        return new viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final viewholder viewholder, final int i) {
       final int pos =i;
        viewholder.letterimg.setImageResource(arrayList.get(i));
        viewholder.letterimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(tf!=null &&tf.isVisible()){
                    AppCompatActivity activity = (AppCompatActivity) context;
                    activity.getSupportFragmentManager().beginTransaction().remove(tf).commit();
                }
                else{
                    Intent intent=new Intent(context, ImgChooserActivity.class);
                    context.startActivity(intent);
                }

            }
        });
        viewholder.showtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) context;

                tf= new TextFragment();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.textdisplay, tf).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class  viewholder extends  RecyclerView.ViewHolder {
        ImageView letterimg,showtext;
        Button textbtn;
        FrameLayout textframelayout;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            letterimg=itemView.findViewById(R.id.letterimg);
            textbtn=itemView.findViewById(R.id.textbtn2);
            textframelayout=itemView.findViewById(R.id.textdisplay);
            showtext=itemView.findViewById(R.id.showtext);

        }

    }
}
