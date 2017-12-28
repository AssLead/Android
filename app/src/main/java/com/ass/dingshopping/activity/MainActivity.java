package com.ass.dingshopping.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;

import com.ass.dingshopping.R;
import com.ass.dingshopping.adapter.MainContentAdapter;
import com.ass.dingshopping.fragment.FragmentExcel;
import com.ass.dingshopping.fragment.FragmentHome;
import com.ass.dingshopping.fragment.FragmentMore;
import com.ass.dingshopping.fragment.FragmentSketch;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
    @BindView(R.id.main_viewpager)
    ViewPager viewPager;
    @BindView(R.id.menu_home)
    RadioButton menu_home;
    @BindView(R.id.menu_sketch)
    RadioButton menuSketch;
    @BindView(R.id.menu_excel)
    RadioButton menuExcel;
    @BindView(R.id.menu_more)
    RadioButton menuMore;

    public FragmentHome mFragmentHome;
    public FragmentSketch mFragmentSketch;
    public FragmentExcel mFragmentExcel;
    public FragmentMore mFragmentMore;


    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }


    @Override
    protected void init() {
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    protected void initView() {
//        new FullTitleBar(this, "#00000000", false);

        ripple(findViewById(R.id.menu_home_container));
        ripple(findViewById(R.id.menu_sketch_container));
        ripple(findViewById(R.id.menu_excel_container));
        ripple(findViewById(R.id.menu_more_container));

        List<Fragment> fragments = new ArrayList<Fragment>();
        mFragmentHome = new FragmentHome();
        mFragmentSketch = new FragmentSketch();
        mFragmentExcel = new FragmentExcel();
        mFragmentMore = new FragmentMore();
        fragments.add(mFragmentHome);
        fragments.add(mFragmentSketch);
        fragments.add(mFragmentExcel);
        fragments.add(mFragmentMore);
        // ViewPager
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(new MainContentAdapter(getSupportFragmentManager(), fragments));
        viewPager.addOnPageChangeListener(this);
        viewPager.setCurrentItem(0);
    }

    @OnClick({R.id.menu_home_container, R.id.menu_sketch_container, R.id.menu_excel_container, R.id.menu_more_container})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.menu_home_container:
                viewPager.setCurrentItem(0);
                break;
            case R.id.menu_sketch_container:
                viewPager.setCurrentItem(1);
                break;
            case R.id.menu_excel_container:
                viewPager.setCurrentItem(2);
                break;
            case R.id.menu_more_container:
                viewPager.setCurrentItem(3);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {

    }

    @Override
    public void onPageScrolled(int position, float arg1, int arg2) {
    }

    @Override
    public void onPageSelected(int position) {
        setRadioButtonState(position);
    }

    private void setRadioButtonState(int position) {
        menu_home.setChecked(false);
        menuSketch.setChecked(false);
        menuExcel.setChecked(false);
        menuMore.setChecked(false);
        menu_home.setTextColor(0xff888888);
        menuSketch.setTextColor(0xff888888);
        menuExcel.setTextColor(0xff888888);
        menuMore.setTextColor(0xff888888);
        switch (position) {
            case 0:
                menu_home.setChecked(true);
                menu_home.setTextColor(0xff333333);
                break;
            case 1:
                menuSketch.setChecked(true);
                menuSketch.setTextColor(0xff333333);
                break;
            case 2:
                menuExcel.setChecked(true);
                menuExcel.setTextColor(0xff333333);
                break;
            case 3:
                menuMore.setChecked(true);
                menuMore.setTextColor(0xff333333);
                break;
        }
    }

    private long currentTime;

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        switch (keyCode) {
//            case KeyEvent.KEYCODE_BACK:
//                if (System.currentTimeMillis() - currentTime < 1500) {
//                    ToastUtil.cancelToast();
//                    moveTaskToBack(true);
//                } else {
//                    ToastUtil.toast("再按一次返回桌面");
//                    currentTime = System.currentTimeMillis();
//                    return true;
//                }
//                break;
//        }
//        return super.onKeyDown(keyCode, event);
//    }
}
