package com.ass.dingshopping.fragment;

import android.content.Intent;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;


import com.ass.dingshopping.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class FragmentHome extends BaseFragment {
    @BindView(R.id.title_bar)
    View titleBar;
//    @BindView(R.id.project_tab)
//    NavigationTabStrip navigationTabStrip;
//    @BindView(R.id.project_viewpager)
//    ViewPager viewPager;
//    @BindView(R.id.button_expandable)
//    AngleExpandableButton angleExpandableButton;

    private Intent intent;

    @Override
    protected int layoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            titleBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
//        if (angleExpandableButton != null) {
//            angleExpandableButton.invalidate();
//        }
    }

    @Override
    protected void init() {
//        installButton();
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            fragments.add(new FragmentProject());
        }
//        viewPager.setAdapter(new MainContentAdapter(getActivity().getSupportFragmentManager(), fragments));
//        navigationTabStrip.setTitles("矩形", "立方体", "角度", "墙面", "自动", "手动");
//        navigationTabStrip.setViewPager(viewPager, 0);
//        navigationTabStrip.setStripLength(0.6f);
//        navigationTabStrip.setIntervalLine(false);
    }




}