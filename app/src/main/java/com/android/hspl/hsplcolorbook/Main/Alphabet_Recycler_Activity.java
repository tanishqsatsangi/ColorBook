package com.android.hspl.hsplcolorbook.Main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.hspl.hsplcolorbook.Adapters.verticalRecyclerViewAdapter;
import com.android.hspl.hsplcolorbook.Models.VerticalModel;
import com.android.hspl.hsplcolorbook.Models.horizontalModel;
import com.android.hspl.hsplcolorbook.R;

import java.util.ArrayList;

public class Alphabet_Recycler_Activity extends AppCompatActivity {
    RecyclerView verticalRecyclerView;
    verticalRecyclerViewAdapter adapter;
    ArrayList<VerticalModel> verticalarraylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabet__recycler);
        setdata();
        verticalRecyclerView = (RecyclerView) findViewById(R.id.vertical_recyclerview);
        verticalRecyclerView.setHasFixedSize(true);
        verticalRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new verticalRecyclerViewAdapter(Alphabet_Recycler_Activity.this, verticalarraylist);
        verticalRecyclerView.setAdapter(adapter);

    }

    private void setdata() {
        ArrayList<Integer> img_Array1 = new ArrayList<>();
        img_Array1.add(R.drawable.ic_anew);
        img_Array1.add(R.drawable.ic_b);
        img_Array1.add(R.drawable.ic_c);
        img_Array1.add(R.drawable.ic_d);
        img_Array1.add(R.drawable.ic_e);
        img_Array1.add(R.drawable.ic_f);
        img_Array1.add(R.drawable.ic_g);
        img_Array1.add(R.drawable.ic_h);

        img_Array1.add(R.drawable.ic_anew);
        img_Array1.add(R.drawable.ic_b);
        img_Array1.add(R.drawable.ic_c);
        img_Array1.add(R.drawable.ic_d);
        img_Array1.add(R.drawable.ic_e);
        img_Array1.add(R.drawable.ic_f);
        img_Array1.add(R.drawable.ic_g);
        img_Array1.add(R.drawable.ic_h);

        img_Array1.add(R.drawable.ic_anew);
        img_Array1.add(R.drawable.ic_b);
        img_Array1.add(R.drawable.ic_c);
        img_Array1.add(R.drawable.ic_d);
        img_Array1.add(R.drawable.ic_e);
        img_Array1.add(R.drawable.ic_f);
        img_Array1.add(R.drawable.ic_g);
        img_Array1.add(R.drawable.ic_h);

        img_Array1.add(R.drawable.ic_anew);
        img_Array1.add(R.drawable.ic_b);

        ArrayList<VerticalModel> verticalaaray = new ArrayList<VerticalModel>();
        for (int j = 0; j < 26; j++) {
            VerticalModel vm = new VerticalModel();
            ArrayList<horizontalModel> hmarray = new ArrayList<horizontalModel>();

            horizontalModel hm = new horizontalModel();
            hm.setImg(img_Array1.get(j ));
            hmarray.add(hm);

            horizontalModel hma=new horizontalModel();
            hma.setImg(R.drawable.lock_img);
            hmarray.add(hma);

            vm.setArrayList(hmarray);
            verticalaaray.add(vm);
        }
        verticalarraylist = verticalaaray;
    }

}
