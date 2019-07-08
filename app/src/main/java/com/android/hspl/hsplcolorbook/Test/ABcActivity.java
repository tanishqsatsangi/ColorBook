package com.android.hspl.hsplcolorbook.Test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.hspl.hsplcolorbook.R;

import java.util.ArrayList;

public class ABcActivity extends AppCompatActivity {

    ArrayList<Integer> img_Array1;
    RecyclerView rv;
    abcadapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abc);
        setdata();
        rv = findViewById(R.id.testrecyclerview);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rv.setHasFixedSize(true);
        adapter = new abcadapter(this,img_Array1);
        rv.setAdapter(adapter);

    }

    private void setdata() {
         img_Array1 = new ArrayList<>();
        img_Array1.add(R.drawable.ic_a);
        img_Array1.add(R.drawable.ic_b);
        img_Array1.add(R.drawable.ic_c);
        img_Array1.add(R.drawable.ic_d);
        img_Array1.add(R.drawable.ic_e);
        img_Array1.add(R.drawable.ic_f);
        img_Array1.add(R.drawable.ic_g);
        img_Array1.add(R.drawable.ic_h);

        img_Array1.add(R.drawable.ic_a);
        img_Array1.add(R.drawable.ic_b);
        img_Array1.add(R.drawable.ic_c);
        img_Array1.add(R.drawable.ic_d);
        img_Array1.add(R.drawable.ic_e);
        img_Array1.add(R.drawable.ic_f);
        img_Array1.add(R.drawable.ic_g);
        img_Array1.add(R.drawable.ic_h);

        img_Array1.add(R.drawable.ic_a);
        img_Array1.add(R.drawable.ic_b);
        img_Array1.add(R.drawable.ic_c);
        img_Array1.add(R.drawable.ic_d);
        img_Array1.add(R.drawable.ic_e);
        img_Array1.add(R.drawable.ic_f);
        img_Array1.add(R.drawable.ic_g);
        img_Array1.add(R.drawable.ic_h);

        img_Array1.add(R.drawable.ic_a);
        img_Array1.add(R.drawable.ic_b);
    }
}
