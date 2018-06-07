package com.haolyy.haolyy.custom.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.TextView;

import com.haolyy.haolyy.R;

/**
 * 可以考虑加个进度条
 */
public class DialogUpdate extends Dialog {
    private OnDoubleClickListener mdouble;
    private DialogUpdate instance;
    private TextView tvButton,tv_content,tv_version;
    public DialogUpdate(Context context) {
        super(context, R.style.dialogWrong);
        instance = this;
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.gravity = Gravity.CENTER | Gravity.CENTER_HORIZONTAL;
        getWindow().setAttributes(attributes);
        super.setContentView(R.layout.dialog_update);
        tv_version= (TextView) findViewById(R.id.tv_version);
        tv_content= (TextView) findViewById(R.id.tv_update_content);
        tvButton= (TextView)findViewById(R.id.tv_update);
        initListener();
    }
    public DialogUpdate setText(String version,String content){
        if(TextUtils.isEmpty(version)){
            tvButton.setText("退出程序");
        }else {
        tv_version.setText("发现新版本"+version);}
        tv_content.setText(content);
        return instance;
    }
    private void initListener() {
        tvButton.setOnClickListener(v -> {
            tvButton.setClickable(false);
            tv_content.setText("请耐心等待,更新中...");
            if(null!=mdouble){
            mdouble.execute();
            }
        });
    }

    public interface OnDoubleClickListener {
        void execute();
    }

    public DialogUpdate setOnDoubleClickListener(OnDoubleClickListener doubl) {
        this.mdouble = doubl;
        return instance;
    }


}
