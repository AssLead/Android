package com.ass.dingshopping.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.ass.dingshopping.R;
import com.ass.dingshopping.dialog.DialogLoad;
import com.ass.dingshopping.view.MaterialRippleLayout;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 基类
 */
public abstract class BaseFragment extends Fragment {
    protected final String TAG = getClass().getCanonicalName();
    protected View contentView;
    protected DialogLoad loadDialog;
    private Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (contentView == null) {
            contentView = inflater.inflate(layoutId(), container, false);
            unbinder = ButterKnife.bind(this, contentView);
        }
        if (contentView != null) {
            return contentView;
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void startActivity(Intent intent) {
        getActivity().startActivity(intent);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle bundle) {
        if (view != null) {
            loadDialog = new DialogLoad(getActivity());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                View title_bar = contentView.findViewById(R.id.title_bar);
                if (title_bar != null) {
                    title_bar.setVisibility(View.VISIBLE);
                }
                //  contentView.setPadding(0, DisplayUtil.getStatusBarHeight(getActivity()), 0, 0);
            }
            initView();
            init();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        ((ViewGroup) contentView.getParent()).removeView(contentView);
    }

    protected abstract int layoutId();

    protected abstract void init();

    protected abstract void initView();


    /**
     * 点击波浪动画
     */
    public void ripple(View view) {
        MaterialRippleLayout.on(view).rippleDelayClick(false).rippleRoundedCorners(200).rippleDuration(300).create();
    }

    public void showLoad() {
        if (loadDialog == null) {
            return;
        }
        loadDialog.show();
    }

    public void dismissLoad() {
        if (loadDialog == null) {
            return;
        }
        loadDialog.dismiss();
    }

    /**
     * 是否延迟显示加载
     *
     * @param isDelay
     */
    public void showLoad(boolean isDelay) {
        if (loadDialog == null) {
            return;
        }
        if (isDelay) {
            loadDialog.showLoad();
        } else {
            loadDialog.show();
        }
    }

    /**
     * 配合延迟显示
     *
     * @param isDelay
     */
    public void dismissLoad(boolean isDelay) {
        if (loadDialog == null) {
            return;
        }
        if (isDelay) {
            loadDialog.dismissLoad();
        } else {
            loadDialog.dismiss();
        }
    }
}
