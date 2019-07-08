package com.android.hspl.hsplcolorbook.Adapters;

import android.content.Context;
import android.content.Intent;
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
import android.widget.Toast;

import com.android.hspl.hsplcolorbook.Main.AlphabetActivity;
import com.android.hspl.hsplcolorbook.Models.horizontalModel;
import com.android.hspl.hsplcolorbook.R;

import java.util.ArrayList;

public class horizontalRecyclerViewAdapter extends RecyclerView.Adapter<horizontalRecyclerViewAdapter.horizontalViewHolder> {
    Context context;
    int pos_vertical;
    ArrayList<horizontalModel> hmarrayList = new ArrayList<>();

    public horizontalRecyclerViewAdapter() {

    }

    public horizontalRecyclerViewAdapter(Context context, ArrayList<horizontalModel> array, int pos_Vertical) {
        this.context = context;
        this.hmarrayList = array;
        this.pos_vertical = pos_Vertical;
    }

    @NonNull
    @Override
    public horizontalRecyclerViewAdapter.horizontalViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_view_layout, viewGroup, false);
        return new horizontalViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull horizontalRecyclerViewAdapter.horizontalViewHolder horizontalViewHolder, int i) {
        final horizontalModel h = hmarrayList.get(i);
        final int pos = i;
        Bitmap b=getBitmapFromVectorDrawable(context,h.getImg()).copy(Bitmap.Config.ARGB_8888,true);
      //  horizontalViewHolder.horizontalimg.setImageResource(h.getImg());
        horizontalViewHolder.horizontalimg.setImageBitmap(b);
        horizontalViewHolder.horizontalimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(h.getImg()==R.drawable.lock_img){
                Toast.makeText(context,"Not yet Available",Toast.LENGTH_SHORT).show();
            }
            else {
                Intent intent = new Intent(context, AlphabetActivity.class);
                intent.putExtra("resid", h.getImg());
                intent.putExtra("horizontalpos", pos + 1);
                intent.putExtra("verticalpos", pos_vertical + 1);
                context.startActivity(intent);
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

    @Override
    public int getItemCount() {
        return hmarrayList.size();
    }
    class horizontalViewHolder extends RecyclerView.ViewHolder {
        ImageView horizontalimg;

        public horizontalViewHolder(@NonNull View itemView) {
            super(itemView);
            horizontalimg = (ImageView) itemView.findViewById(R.id.imgview_horizontal);

        }
    }
}
