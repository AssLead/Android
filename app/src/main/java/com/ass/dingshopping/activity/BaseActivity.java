package com.ass.dingshopping.activity;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ass.dingshopping.airutils.PermissionManager;
import com.ass.dingshopping.dialog.DialogLoad;
import com.ass.dingshopping.view.MaterialRippleLayout;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Activity基类
 * Created by Air on 2017/12/25.
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected final String TAG = getClass().getSimpleName();
    protected DialogLoad loadDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (layoutId() != -1) {
            setContentView(layoutId());
        }
        PermissionManager.checkPermission(this);

        ButterKnife.bind(this);

        loadDialog = new DialogLoad(this);

        initView();
        init();

    }

    protected <T> T findView(int i) {
        return (T) findViewById(i);
    }

    protected abstract int layoutId();

    protected abstract void initView();

    protected abstract void init();

    /**
     * 点击波浪动画
     */
    public void ripple(View view) {
        MaterialRippleLayout.on(view).rippleDelayClick(false).rippleRoundedCorners(200).rippleDuration(300).create();
    }

    public void showLoad(String loadText) {
        loadDialog.setLoadText(loadText);
        loadDialog.show();
    }

    public void showLoad() {
        loadDialog.show();
        loadDialog.setLoadText("加载中,请稍后~");
    }

    public void dismissLoad() {
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
        loadDialog.setLoadText("加载中,请稍后~");
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

    /**
     * 统计代码
     */
    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * 统计代码
     */
    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PermissionManager.PERMISSIONCODE:
//                boolean isAgree = true;
//                for (int i = 0; i < grantResults.length; i++) {
//                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
//                        isAgree = false;
//                    }
//                }
//                Log.i("------", "grantResults.length :" + grantResults.length );
//                if (grantResults.length > 0 && !isAgree) {
//                    PermissionManager.checkPermission(this);
//                }
//                Log.i("------", "isAgree:" + isAgree);
                break;
        }
    }

    /**
     * 程序是否在前台运行
     *
     * @return
     */
    public boolean isAppOnForeground() {
        ActivityManager activityManager = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        String packageName = getApplicationContext().getPackageName();

        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        if (appProcesses == null)
            return false;

        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(packageName) && appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }
        return false;
    }

}
