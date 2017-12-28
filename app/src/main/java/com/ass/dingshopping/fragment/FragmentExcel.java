package com.ass.dingshopping.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.ass.dingshopping.R;
import com.ass.dingshopping.bean.ExcelBean;


import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class FragmentExcel extends BaseFragment {


    private List<ExcelBean> excelBeenList;

    private Intent intent;

    @Override
    protected int layoutId() {
        return R.layout.fragment_excel;
    }

    @Override
    protected void initView() {
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    protected void init() {

    }


    @OnClick({R.id.iv_add_excel})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.iv_add_excel:
//                intent = new Intent(getActivity(), ActivityExcel.class);
//                intent.putExtra("type", "create");
//                startActivity(intent);
                break;
        }
    }
}