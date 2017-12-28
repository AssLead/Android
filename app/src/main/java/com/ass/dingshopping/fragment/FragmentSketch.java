package com.ass.dingshopping.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;


import com.ass.dingshopping.R;

import butterknife.BindView;
import butterknife.OnClick;

public class FragmentSketch extends BaseFragment {
    @BindView(R.id.listview)
    ListView listview;


    private Intent intent;


    @Override
    protected int layoutId() {
        return R.layout.fragment_sketct;
    }

    @Override
    protected void initView() {
    }

    @Override
    public void onResume() {
        super.onResume();
        initSketch();
    }

    @Override
    protected void init() {
    }

    private void initSketch() {
    }

    @OnClick({R.id.iv_add_sketch})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.iv_add_sketch:
//                intent = new Intent(getActivity(), ActivitySketch.class);
//                intent.putExtra("type", "create");
//                startActivity(intent);
                break;
        }
    }


}