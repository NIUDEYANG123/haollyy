package com.haolyy.haolyy.custom.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.haolyy.haolyy.R;
import com.haolyy.haolyy.base.BaseApplication;
import com.haolyy.haolyy.utils.DateUtil;
import com.haolyy.haolyy.utils.LogUtils;

import rx.Subscription;


public class DialogOpenTips extends Dialog {
    private OnDoubleClickListener mdouble;
    private DialogOpenTips instance;
    private TextView tvContent,tvButton;
    private final ImageView iv;
    private final Subscription subscription;

    public DialogOpenTips(Context context) {
        super(context, R.style.dialogWrong);
        instance = this;
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.gravity = Gravity.CENTER | Gravity.CENTER_HORIZONTAL;
        getWindow().setAttributes(attributes);
        super.setContentView(R.layout.dialog_open_tips);
        tvContent= (TextView) findViewById(R.id.tv_time);
        tvButton= (TextView) findViewById(R.id.tv_btn);
        iv = (ImageView) findViewById(R.id.iv_progress);
        Glide.with(context).load(R.drawable.progress_open).asGif().into(iv);
        if(BaseApplication.remainTime>0){
            subscription = DateUtil.countDownOpen(instance,tvContent, BaseApplication.remainTime);
        }else {
            subscription = DateUtil.countDownOpen(instance,tvContent, 180000);
        }
        initListener();
    }
    public DialogOpenTips setText(String content, String button){
        tvContent.setText(content);
        tvButton.setText(button);
        return instance;
    }
    private void initListener() {
        tvButton.setOnClickListener(v -> {
            //subscription.unsubscribe();
            dismiss();
            if(null!=mdouble){
            mdouble.execute();}
        });
    }

    public interface OnDoubleClickListener {
        void execute();
    }

    public DialogOpenTips setOnDoubleClickListener(OnDoubleClickListener doubl) {
        this.mdouble = doubl;
        return instance;

    }


}
