package com.ass.dingshopping.dialog;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;

import com.ass.dingshopping.R;


/**
 * 加载dialog
 */
public class DialogLoad extends Dialog {
    private Context context;
    private boolean isShow = true;
    private View progressContainer;
    private TextView tv_load;

    public DialogLoad(Context context) {
        super(context, R.style.CustomProgressDialog);
        setContentView(R.layout.dialog_progressbar);
        this.context = context;
        LayoutParams lay = getWindow().getAttributes();
        setParams(lay);
        setCancelable(false);
        setCanceledOnTouchOutside(false);

        progressContainer = (View) findViewById(R.id.progress_container);
        tv_load = (TextView) findViewById(R.id.tv_load_text);

    }

    private void setParams(LayoutParams lay) {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        //lay.height = dm.heightPixels;
        lay.width = dm.widthPixels;
    }

    /**
     * 等待一会才显示
     */
    public void showLoad() {
        isShow = true;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!isShowing() && isShow) {
                    show();
                }
            }
        }, 1300);
    }

    public void dismissLoad() {
        isShow = false;
        if (isShowing()) {
            dismiss();
        }
    }

    public void setLoadText(String text) {
        tv_load.setText(text);
    }

    @Override
    public void show() {
        super.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setCancelable(true);
                setCanceledOnTouchOutside(true);
                progressContainer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismissLoad();
                    }
                });
            }
        }, 5000);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        isShow = false;
    }
}
