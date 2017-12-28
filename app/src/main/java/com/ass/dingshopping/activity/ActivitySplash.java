package com.ass.dingshopping.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.AnimationUtils;

import com.ass.dingshopping.R;
import com.ass.dingshopping.airutils.SharePreUtils;
import com.ass.dingshopping.common.SharePreKey;


/**
 * Created by Air on 2017/12/25.
 */

public class ActivitySplash extends BaseActivity {

    private View vSplash;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 3) {

                if (SharePreUtils.getBoolean(SharePreKey.NOT_FIRST_USE_APP)) {

                    Intent home = new Intent(ActivitySplash.this, MainActivity.class);
                    startActivity(home);

                    finish();
                } else {
                    Intent welcome = new Intent(ActivitySplash.this, ActivityWelcomePage.class);
                    startActivity(welcome);
                    finish();
                }
            }
        }
    };


    @Override
    protected int layoutId() {
        return R.layout.activity_splash_layout;
    }

    @Override
    protected void initView() {
        vSplash = findView(R.id.v_splash);
        vSplash.startAnimation(AnimationUtils.loadAnimation(this, R.anim.center_scale_alpha_in));
        handler.sendEmptyMessageDelayed(3, 2000);
    }

    @Override
    protected void init() {

    }


}
