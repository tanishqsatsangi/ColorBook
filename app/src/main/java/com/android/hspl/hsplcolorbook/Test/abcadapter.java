package com.android.hspl.hsplcolorbook.Test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.hspl.hsplcolorbook.R;

import java.util.ArrayList;

public class abcadapter extends RecyclerView.Adapter<abcadapter.viewholder> {
    Context context;
    ArrayList<Integer> img_Array1=new ArrayList<>();
    abcadapter(){

    }
    abcadapter(Context context,ArrayList<Integer> list){
        this.context=context;
        img_Array1=list;
    }
    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.abclayout,viewGroup,false);
        return new viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final viewholder viewholder, int i) {
            Bitmap b=getBitmapFromVectorDrawable(context,img_Array1.get(i)).copy(Bitmap.Config.ARGB_8888,true);
            viewholder.testimg.setImageBitmap(b);
            viewholder.testimg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewholder.okimg.setImageResource(R.drawable.a1low);
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

    @Override
    public int getItemCount() {
        return img_Array1.size();
    }

    class  viewholder extends RecyclerView.ViewHolder {
        public ImageView testimg,okimg;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            testimg=itemView.findViewById(R.id.abcimgview);
            okimg=itemView.findViewById(R.id.okimg);



        }
    }
}
