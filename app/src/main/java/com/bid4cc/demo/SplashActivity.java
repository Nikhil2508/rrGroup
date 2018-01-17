package com.bid4cc.demo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.iv_logo)
    ImageView ivLogo;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.l_header)
    LinearLayout lHeader;
    @BindView(R.id.fl_content)
    FrameLayout flContent;
    private Handler defaultHandler;
    private final int ERROR_TIMEOUT_MS = 3000;
    private final int RQ_GALLERY = 1;
    private final int RQ_CAMERA = 2;
    private final int SPLASH_TIMEOUT_MS = 2000;
    private int ANIM_TIME_MS = 500;
    private View loginSignupScreen;
    private View tvFbBtn;
    private View googleLoginBtn;
    private View tvLoginBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        initialize();
    }




    private void initialize() {
//        ((EClassApplication)getApplication()).trackScreen();
        defaultHandler = new Handler();
        showLoginOptions();
    }

    private void showLoginOptions() {
        defaultHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (SharedPrefUtils.getString(SplashActivity.this, "userId").isEmpty()) {

                    ivLogo.startAnimation(AnimationUtils.loadAnimation(SplashActivity.this, R.anim.a_move_up));
                    defaultHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
//                            ivLogo.startAnimation(AnimationUtils.loadAnimation(SplashActivity.this, R.anim.a_slide_in_right));
                            showLoginSignupScreen();
                        }
                    }, ANIM_TIME_MS);

//                    } else {
//                        showOfflineAppDialog();
//
//                    }

                } else {

                    goToHomeScreen();
//                    if (!checkForSDCard()) {
//
//                    } else {
//                        showOfflineAppDialog();
//
//                    }

                }
            }
        }, SPLASH_TIMEOUT_MS);
    }

    private void goToHomeScreen() {

        startActivity(new Intent(SplashActivity.this,HomePage.class));

    }

    private void showLoginSignupScreen() {

        loginSignupScreen = getLayoutInflater().inflate(R.layout.l_login_signup, null);
        loginSignupScreen.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        tvFbBtn = ButterKnife.findById(loginSignupScreen, R.id.bt_signIn);
        googleLoginBtn = ButterKnife.findById(loginSignupScreen, R.id.bt_guestLogin);
        tvLoginBtn = ButterKnife.findById(loginSignupScreen, R.id.bt_register);
        flContent.addView(loginSignupScreen);
        loginSignupScreen.startAnimation(AnimationUtils.loadAnimation(SplashActivity.this, R.anim.a_fade_in));

        tvFbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToHomeScreen();
            }
        });

        googleLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToHomeScreen();
            }
        });

        tvLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToHomeScreen();
            }
        });


    }
}
