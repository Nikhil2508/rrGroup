package com.bid4cc.demo;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProductCategoryDetailFragment extends Fragment {


    @BindView(R.id.rv_products)
    RecyclerView rvProducts;
    List<ProductModel> productModels;
    Unbinder unbinder;

    public ProductCategoryDetailFragment() {
        // Required empty public constructor
    }


    public static ProductCategoryDetailFragment newInstance(String title) {

        Bundle args = new Bundle();
        args.putString("title", title);
        ProductCategoryDetailFragment fragment = new ProductCategoryDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_detail, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        TextView title = getActivity().findViewById(R.id.tv_subTitle);
        title.setText(getArguments().getString("title"));

        productModels = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            ProductModel model = new ProductModel();
            model.productName = "Hello world";

            productModels.add(model);
        }

        ProductListAdapter adapter = new ProductListAdapter(productModels,getContext());
        rvProducts.setAdapter(adapter);
        rvProducts.setLayoutManager(new GridLayoutManager(getContext(),2));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
