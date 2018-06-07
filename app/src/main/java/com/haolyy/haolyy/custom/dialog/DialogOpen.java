package com.haolyy.haolyy.custom.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.haolyy.haolyy.R;


public class DialogOpen extends Dialog {
    private OnDoubleClickListener mdouble;
    private DialogOpen instance;
    private TextView tvContent;
    private final TextView tv_open;
    private final ImageView button;

    public DialogOpen(Context context, int step) {
        super(context, R.style.dialogWrong);
        instance = this;
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.gravity = Gravity.CENTER | Gravity.CENTER_HORIZONTAL;
        getWindow().setAttributes(attributes);
        super.setContentView(R.layout.dialog_open);
        tv_open = (TextView) findViewById(R.id.tv_open);
        button = (ImageView) findViewById(R.id.iv_button);
        if (step == 1) {
            tv_open.setText("立即开启");
        } else if (step == 2) {
            tv_open.setText("立即激活");
        } else if (step == 3) {
            tv_open.setText("开启自动投标");
        }
        initListener();
    }

    private void initListener() {
        tv_open.setOnClickListener(v -> {
            dismiss();
            mdouble.execute();
        });
        button.setOnClickListener(v -> dismiss());
    }

    public interface OnDoubleClickListener {
        void execute();
    }

    public DialogOpen setOnDoubleClickListener(OnDoubleClickListener doubl) {
        this.mdouble = doubl;
        return instance;

    }


}
