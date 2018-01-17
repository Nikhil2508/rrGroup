package com.bid4cc.demo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by NiCK on 1/14/2018.
 */

public class ProductSubCategoryAdapter extends RecyclerView.Adapter<ProductSubCategoryAdapter.ProdVH> {

    List<ProductSubCatModel> productSubCats;
    Context context;


    public ProductSubCategoryAdapter(List<ProductSubCatModel> productSubCats, Context context) {
        this.productSubCats = productSubCats;
        this.context = context;
    }

    @Override
    public ProdVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.l_product_categories,parent,false);
        return new ProdVH(view);
    }

    @Override
    public void onBindViewHolder(ProdVH holder, int position) {

        ProductSubCatModel model = productSubCats.get(position);
        holder.title.setText(model.title);

    }

    @Override
    public int getItemCount() {
        return productSubCats.size();
    }

    public class ProdVH extends RecyclerView.ViewHolder {

        TextView title;

        public ProdVH(View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.tv_title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    context.startActivity(new Intent(context, ProductsActivity.class).putExtra("title",productSubCats.get(getAdapterPosition()).title));

                }
            });
        }
    }
}
