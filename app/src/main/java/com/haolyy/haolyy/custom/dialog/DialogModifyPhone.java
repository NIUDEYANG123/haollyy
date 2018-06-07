package com.haolyy.haolyy.custom.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.DrawableRes;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.haolyy.haolyy.R;


public class DialogModifyPhone extends Dialog {
    private DialogModifyPhone instance;
    private TextView tvContent;
    private ImageView imageView;
    private Button button;

    public DialogModifyPhone(Context context) {
        super(context, R.style.dialogWrong);
        instance = this;
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.gravity = Gravity.CENTER | Gravity.CENTER_HORIZONTAL;
        getWindow().setAttributes(attributes);
        super.setContentView(R.layout.dialog_modify_phone);
        tvContent = findViewById(R.id.tv_modify_phone);
        button =  findViewById(R.id.btn_modify_phone);
        imageView = findViewById(R.id.iv_modify_phone);
        initListener();
    }
    public DialogModifyPhone setContent(String content, @DrawableRes int id){
        tvContent.setText(content);
        imageView.setImageResource(id);
        return instance;
    }

    private void initListener() {
        button.setOnClickListener(v -> {
            dismiss();
        });
    }
}
