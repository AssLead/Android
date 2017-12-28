package com.ass.dingshopping.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.ass.dingshopping.R;
import com.ass.dingshopping.airutils.SharePreUtils;
import com.ass.dingshopping.common.SharePreKey;
import com.ass.dingshopping.view.DepthPageTransformer;

/**
 * Created by Air on 2017/12/26.
 */

public class ActivityWelcomePage extends  BaseActivity{
    @Override
    protected int layoutId() {
        return R.layout.activity_welcome_page_layout;
    }

    @Override
    protected void initView() {
        vpContainer = findView(R.id.vp_page);
        if (SharePreUtils.getBoolean(SharePreKey.NOT_FIRST_USE_APP)) {
            //进入到登录界面
            Intent home = new Intent(ActivityWelcomePage.this, MainActivity.class);
            startActivity(home);
            finish();
        } else {
            page = new int[]{R.drawable.welcome_1, R.drawable.welcome_2, R.drawable.welcome_3};
            //创建欢迎页
            welPageAdapter = new WelPageAdapter(page);
            vpContainer.setAdapter(welPageAdapter);
            vpContainer.setPageTransformer(true, new DepthPageTransformer());
        }
    }

    @Override
    protected void init() {

    }

    private ViewPager vpContainer;
    private int page[];
    private WelPageAdapter welPageAdapter;



    @Override
    public void onBackPressed() {
        if (vpContainer.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            vpContainer.setCurrentItem(vpContainer.getCurrentItem() - 1);
        }
    }

    private class WelPageAdapter extends PagerAdapter {
        private int page[];

        public WelPageAdapter(int[] page) {
            this.page = page;
        }

        @Override
        public int getCount() {
            return page.length;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            ImageView imageView = new ImageView(container.getContext());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            container.setLayoutParams(layoutParams);
            container.addView(imageView);
            imageView.setBackgroundResource(page[position]);
            container.setContentDescription(null);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (position == page.length - 1) {
                        Intent intent = new Intent(ActivityWelcomePage.this, MainActivity.class);
                        startActivity(intent);
                        SharePreUtils.putBoolean(SharePreKey.NOT_FIRST_USE_APP, true);
                        finish();
                    }
                }
            });
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

}
