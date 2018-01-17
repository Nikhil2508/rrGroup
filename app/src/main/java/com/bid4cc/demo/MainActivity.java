package com.bid4cc.demo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.et_username)
    TextInputEditText etUsername;
    @BindView(R.id.et_password)
    TextInputEditText etPassword;
    @BindView(R.id.bt_signIn)
    TextView btSignIn;
    @BindView(R.id.bt_guestLogin)
    TextView btGuestLogin;
    @BindView(R.id.bt_register)
    TextView btRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        btSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this,HomePage.class));

            }
        });
        btGuestLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this,HomePage.class));

            }
        });
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this,HomePage.class));

            }
        });
    }



}


