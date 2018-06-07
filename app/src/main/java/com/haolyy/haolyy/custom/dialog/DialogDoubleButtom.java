package com.haolyy.haolyy.custom.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.TextView;

import com.haolyy.haolyy.R;


public class DialogDoubleButtom extends Dialog {
    private OnDoubleClickListener mdouble;
    private DialogDoubleButtom instance;
    private TextView tvContent,tvButton1,tvButton2;
    public DialogDoubleButtom(Context context) {
        super(context, R.style.dialogWrong);
        instance = this;
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.gravity = Gravity.CENTER | Gravity.CENTER_HORIZONTAL;
        getWindow().setAttributes(attributes);
        super.setContentView(R.layout.dialog_double_button);
        tvContent= (TextView) findViewById(R.id.tv_content);
        tvButton1= (TextView) findViewById(R.id.tv_cancel);
        tvButton2=(TextView) findViewById(R.id.tv_sure);
        initListener();
    }
    public DialogDoubleButtom setText(String content, String button){
        tvContent.setText(content);
        return instance;
    }
    private void initListener() {
        tvButton1.setOnClickListener(v -> {
            dismiss();
            mdouble.executeLeft();
        });
        tvButton2.setOnClickListener(v->{
            dismiss();
            mdouble.executeRight();
        });
    }

    public interface OnDoubleClickListener {
        void executeLeft();
        void executeRight();
    }

    public DialogDoubleButtom setOnDoubleClickListener(OnDoubleClickListener doubl) {
        this.mdouble = doubl;
        return instance;

    }


}
