package com.haolyy.haolyy.custom.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.haolyy.haolyy.R;


public class DialogInvestTips extends Dialog {
    private OnDoubleClickListener mdouble;
    private DialogInvestTips instance;
    private TextView tvContent,tvButton;
    public DialogInvestTips(Context context) {
        super(context, R.style.dialogWrong);
        instance = this;
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.gravity = Gravity.CENTER | Gravity.CENTER_HORIZONTAL;
        getWindow().setAttributes(attributes);
        super.setContentView(R.layout.dialog_one_button);
        tvContent= (TextView) findViewById(R.id.tv_tips);
        tvButton= (TextView) findViewById(R.id.tv_button);
        initListener();
    }
    public DialogInvestTips setText(String content,String button){
        tvContent.setText(content);
        tvButton.setText(button);
        return instance;
    }
    private void initListener() {
        tvButton.setOnClickListener(v -> {
            dismiss();
            if(null!=mdouble){
            mdouble.execute();}
        });
    }

    public interface OnDoubleClickListener {
        void execute();
    }

    public DialogInvestTips setOnDoubleClickListener(OnDoubleClickListener doubl) {
        this.mdouble = doubl;
        return instance;

    }


}
