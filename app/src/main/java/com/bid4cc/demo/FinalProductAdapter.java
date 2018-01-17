package com.bid4cc.demo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by NiCK on 1/17/2018.
 */

public class FinalProductAdapter extends RecyclerView.Adapter<FinalProductAdapter.FPVH> {

    List<FinalProdModel> modelList;
    Context context;

    public FinalProductAdapter(List<FinalProdModel> modelList, Context context) {
        this.modelList = modelList;
        this.context = context;
    }

    @Override
    public FPVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.l_final_product,parent,false);
        return new FPVH(view);
    }

    @Override
    public void onBindViewHolder(FPVH holder, int position) {

        FinalProdModel model = modelList.get(position);
        holder.title.setText(model.title);
        holder.modelNmber.setText("Model Number: " + model.modelNumber);

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class FPVH extends RecyclerView.ViewHolder {

        TextView title, modelNmber;


        public FPVH(View itemView) {
            super(itemView);

            title = (TextView)itemView.findViewById(R.id.tv_title);
            modelNmber = (TextView)itemView.findViewById(R.id.tv_modelNumber);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    context.startActivity(new Intent(context, ProductDetailActivity.class).putExtra("productName",modelList.get(getAdapterPosition()).title));

                }
            });

        }



    }
}
