package com.bid4cc.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductSubCat extends AppCompatActivity {

    @BindView(R.id.iv_menu_icon)
    ImageView ivMenuIcon;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.iv_notif_icon)
    ImageView ivNotifIcon;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_container)
    LinearLayout toolbarContainer;
    @BindView(R.id.rv_productsSubCat)
    RecyclerView rvProductsSubCat;
    List<ProductSubCatModel> list;
    private String titleCategory;
    private static final String TAG = "ProductSubCat";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_sub_cat);
        ButterKnife.bind(this);
        titleCategory = getIntent().getStringExtra("title");
        Log.d(TAG, "onCreate: title----> " + titleCategory );
        list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {

            ProductSubCatModel model = new ProductSubCatModel();
            if (i == 0) {

                model.title = "Fans";
            }if (i == 1) {

                model.title = "Lighting";
            }if (i == 2) {

                model.title = "Home Appliances";
            }if (i == 3) {

                model.title = "Switches";
            }if (i == 4) {

                model.title = "Switchgear";
            }
            list.add(model);
        }

        ProductSubCategoryAdapter adapter = new ProductSubCategoryAdapter(list, this);
        rvProductsSubCat.setAdapter(adapter);
        rvProductsSubCat.setLayoutManager(new LinearLayoutManager(this));



        title.setText(titleCategory);
        ivMenuIcon.setVisibility(View.VISIBLE);
        ivMenuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
