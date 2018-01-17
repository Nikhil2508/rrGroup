package com.bid4cc.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
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
    private static final String TAG = "FinalProductActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_product);
        ButterKnife.bind(this);
        modelList = new ArrayList<>();
//
//        for (int i = 0; i < 2; i++) {
//
//
//            FinalProdModel model = new FinalProdModel();
//            if (i == 0) {
//
//                model.title = "Strillare";
//                model.modelNumber = "222";
//            }
//            if (i == 1) {
//
//
//                model.title = "Prodigo";
//                model.modelNumber = "223";
//
//            }
//
//            modelList.add(model);
//        }


        try {
            JSONObject object = new JSONObject(loadJSONFromAsset());

            JSONArray array = object.getJSONArray("Fans");

            for (int i = 0; i < array.length(); i++) {
//
                FinalProdModel model = new FinalProdModel();
                model.title = array.getJSONObject(i).getString("productName");
                model.smallImage = array.getJSONObject(i).getString("smallImageLink");
                model.largeImage = array.getJSONObject(i).getString("largeLink");
                model.description = array.getJSONObject(i).getString("description");
                model.modelNumber = "2222";

                modelList.add(model);

                Log.d(TAG, "onCreate: fanssss----->  " + array.getJSONObject(i).getString("productName"));

            }
        } catch (JSONException e) {
            e.printStackTrace();
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


    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("productfans.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
