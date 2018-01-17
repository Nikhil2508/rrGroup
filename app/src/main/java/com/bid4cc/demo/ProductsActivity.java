package com.bid4cc.demo;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class ProductsActivity extends AppCompatActivity {

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
    @BindView(R.id.rv_productsList)
    RecyclerView rvProductsList;
    List<ProductModel> list;
    private String titleSubCat;
    private static final String TAG = "ProductsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        ButterKnife.bind(this);
        titleSubCat = getIntent().getStringExtra("title");
        Log.d(TAG, "onCreate: json---> " + loadJSONFromAsset());
        list = new ArrayList<>();

        try {
            JSONObject object = new JSONObject(loadJSONFromAsset());
            JSONArray array = object.getJSONArray(titleSubCat);
            for (int i = 0; i < array.length(); i++) {

                ProductModel model = new ProductModel();
                model.productName =  array.getJSONObject(i).getString("category");
                list.add(model);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        for (int i = 0; i < 10; i++) {
//            ProductModel model = new ProductModel();
//            model.productName = "Hello";
//            list.add(model);
//        }

        ProductListAdapter adapter = new ProductListAdapter(list,this);
        rvProductsList.setAdapter(adapter);
        rvProductsList.setLayoutManager(new GridLayoutManager(this,2));

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        rvProductsList.setLayoutManager(mLayoutManager);
        title.setText(titleSubCat);
        ivMenuIcon.setVisibility(View.VISIBLE);
        ivMenuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        rvProductsList.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        rvProductsList.setItemAnimator(new DefaultItemAnimator());
        rvProductsList.setAdapter(adapter);


    }


    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("rr.json");
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




    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

}
