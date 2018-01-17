package com.bid4cc.demo;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductDetailActivity extends AppCompatActivity {

    @BindView(R.id.iv_menu_icon)
    ImageView ivMenuIcon;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tv_downloadCat)
    TextView downloadCat;
    @BindView(R.id.iv_notif_icon)
    ImageView ivNotifIcon;
    @BindView(R.id.iv_productImage)
    ImageView ivProductImage;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        ButterKnife.bind(this);
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        title.setText(getIntent().getStringExtra("productName"));
        ivMenuIcon.setVisibility(View.VISIBLE);
        ivMenuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        downloadCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


//                    new DownloadFile().execute("https://letuscsolutions.files.wordpress.com/2015/07/five-point-someone-chetan-bhagat_ebook.pdf", "five-point-someone-chetan-bhagat_ebook.pdf");

                new DownloadTask(ProductDetailActivity.this,"http://unec.edu.az/application/uploads/2014/12/pdf-sample.pdf");


            }
        });
    }

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }




    private class DownloadFile extends AsyncTask<String, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showpDialog();
        }

        @Override
        protected Void doInBackground(String... strings) {

            String fileUrl = strings[0];
// -> https://letuscsolutions.files.wordpress.com/2015/07/five-point-someone-chetan-bhagat_ebook.pdf
            String fileName = strings[1];
// ->five-point-someone-chetan-bhagat_ebook.pdf
            String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
            File folder = new File(extStorageDirectory, "PDF DOWNLOAD");
            folder.mkdir();

            File pdfFile = new File(folder, fileName);

            try{
                pdfFile.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
            FileDownloader.downloadFile(fileUrl, pdfFile);
            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            hidepDialog();
            Toast.makeText(getApplicationContext(), "Download PDf successfully", Toast.LENGTH_SHORT).show();

            Log.d("Download complete", "----------");
        }
    }



}
