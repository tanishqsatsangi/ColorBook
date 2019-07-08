package com.android.hspl.hsplcolorbook.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.hspl.hsplcolorbook.Models.VerticalModel;
import com.android.hspl.hsplcolorbook.Models.horizontalModel;
import com.android.hspl.hsplcolorbook.R;

import java.util.ArrayList;
import java.util.List;

public class verticalRecyclerViewAdapter extends RecyclerView.Adapter<verticalRecyclerViewAdapter.VerticalHolder> {
    Context context;
    private List<VerticalModel> verticalarrayList;

    public verticalRecyclerViewAdapter() {

    }

    public verticalRecyclerViewAdapter(Context context, List<VerticalModel> arrayList) {
        this.context = context;
        verticalarrayList = arrayList;


    }

    @NonNull
    @Override
    public VerticalHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.vertical_view_layout, viewGroup, false);
        return new VerticalHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VerticalHolder verticalHolder, int i) {
        VerticalModel vm = verticalarrayList.get(i);
        ArrayList<horizontalModel> hm = vm.getArrayList();
        horizontalRecyclerViewAdapter adapter = new horizontalRecyclerViewAdapter(context, hm, i);
        verticalHolder.recyclerView.setHasFixedSize(true);
        verticalHolder.recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        verticalHolder.recyclerView.setAdapter(adapter);

    }

    @Override
    public int getItemCount() {
        return verticalarrayList.size();
    }

    class VerticalHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;

        public VerticalHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.horizontal_recycleview);

        }
    }
}
