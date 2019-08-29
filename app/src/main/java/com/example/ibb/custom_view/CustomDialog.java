package com.example.ibb.custom_view;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.ibb.R;

/**
 * Created by 张凯雅 on 2017/12/12.
 */

public class CustomDialog extends ProgressDialog {
    public CustomDialog(Context context) {
        super(context);
    }

    public CustomDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init(getContext());
    }

    private void init(Context context) {

        //设置不可取消，点击其他区域不能取消，实际中可以抽出去封装供外包设置
        setCancelable(false);
        setCanceledOnTouchOutside(false);

        setContentView(R.layout.progressdialogview);
        WindowManager.LayoutParams params=getWindow().getAttributes();
        params.width=WindowManager.LayoutParams.WRAP_CONTENT;
        params.height=WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(params);
    }

    @Override
    public void show() {
        super.show();
    }
}
