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
 * Created by Administrator on 1/10/2018.
 */

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.PLVH> {

    List<ProductModel> productModels;
    Context context;


    public ProductListAdapter(List<ProductModel> productModels, Context context) {
        this.productModels = productModels;
        this.context = context;
    }

    @Override
    public PLVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.l_products,parent,false);
        return new PLVH(view);
    }

    @Override
    public void onBindViewHolder(PLVH holder, int position) {

        ProductModel model = productModels.get(position);
        holder.productName.setText(model.productName);


    }

    @Override
    public int getItemCount() {
        return productModels.size();
    }

    public class PLVH extends RecyclerView.ViewHolder {

        TextView productName;


        public PLVH(View itemView) {
            super(itemView);

            productName = (TextView) itemView.findViewById(R.id.tv_productName);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    context.startActivity(new Intent(context,FinalProductActivity.class).putExtra("productName",productModels.get(getAdapterPosition()).productName));

                }
            });

        }
    }
}
