package com.ass.dingshopping.fragment;

import android.content.Intent;
import android.view.View;


import com.ass.dingshopping.R;

import butterknife.OnClick;

public class FragmentMore extends BaseFragment {

    private Intent intent;

    @Override
    protected int layoutId() {
        return R.layout.fragment_more;
    }

    @Override
    protected void init() {
    }

    @Override
    protected void initView() {
    }

    @OnClick({R.id.ll_company, R.id.ll_product, R.id.ll_files})
    public void onViewClicked(View v) {
        switch (v.getId()) {
//            case R.id.ll_company:
//                intent = new Intent(getActivity(), ActivityCompany.class);
//                startActivity(intent);
//                break;
//            case R.id.ll_product:
//                intent = new Intent(getActivity(), ActivityProduct.class);
//                startActivity(intent);
//                break;
//            case R.id.ll_files:
//                intent = new Intent(getActivity(), ActivityFiles.class);
//                startActivity(intent);
//                break;
        }
    }
}