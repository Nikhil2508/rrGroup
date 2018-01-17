package com.bid4cc.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FinalProductActivity extends AppCompatActivity {

    @BindView(R.id.iv_menu_icon)
    ImageView ivMenuIcon;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.iv_notif_icon)
    ImageView ivNotifIcon;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_productsSubCat)
    RecyclerView rvProductsSubCat;
    List<FinalProdModel> modelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_product);
        ButterKnife.bind(this);
        modelList = new ArrayList<>();

        for (int i = 0; i < 2; i++) {


            FinalProdModel model = new FinalProdModel();
            if (i == 0) {

                model.title = "Strillare";
                model.modelNumber = "222";
            }
            if (i == 1) {


                model.title = "Prodigo";
                model.modelNumber = "223";

            }

            modelList.add(model);
        }


        FinalProductAdapter adapter = new FinalProductAdapter(modelList,this);
        rvProductsSubCat.setAdapter(adapter);
        rvProductsSubCat.setLayoutManager(new LinearLayoutManager(this));

        title.setText(getIntent().getStringExtra("productName"));
        ivMenuIcon.setVisibility(View.VISIBLE);
        ivMenuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
}
