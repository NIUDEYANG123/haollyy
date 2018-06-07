package com.haolyy.haolyy.custom;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.haolyy.haolyy.R;



public class CustomerDialog extends Dialog {
    private Window window;
    public  CustomerDialog instance;

    public CustomerDialog(Context context) {
        super(context, R.style.transparentFrameWindowStyle);
        instance = this;
        window = getWindow();
        setCanceledOnTouchOutside(false);
    }




    /**
     * 手势密码错误
     *
     * @param listener
     * @return
     */
    public CustomerDialog gesture(View.OnClickListener listener) {
        setContentView(R.layout.dialog_gesture_error_count);
        window.setGravity(Gravity.CENTER);
        TextView textView = (TextView) findViewById(R.id.btn_sure);
        textView.setOnClickListener(listener);
        return instance;
    }
    /**
     * 开启touchId
     *
     * @param listener
     * @return
     */
    public CustomerDialog touchId(View.OnClickListener listener) {
        setContentView(R.layout.dialog_touch_id);
        window.setGravity(Gravity.CENTER);
        TextView textView = (TextView) findViewById(R.id.touch_right);
        TextView textView2 = (TextView) findViewById(R.id.touch_left);
        textView.setOnClickListener(listener);
        textView2.setOnClickListener(listener);
        return instance;
    }

    /**
     * 验证touchId
     *
     * @param listener
     * @return
     */
    public CustomerDialog checkTouchId(View.OnClickListener listener) {
        setContentView(R.layout.dialog_check_touch_id);
        window.setGravity(Gravity.CENTER);
        TextView textView = (TextView) findViewById(R.id.btn_cancer);
        textView.setOnClickListener(listener);
        return instance;
    }
    /**
     * 删除消息
     */
    public CustomerDialog loginTips(View.OnClickListener listener,String content) {
        setContentView(R.layout.dialog_login);
        TextView tvLeft = (TextView) findViewById(R.id.login_left);
        TextView tv_content = (TextView) findViewById(R.id.tv_content);
        TextView tvRight = (TextView) findViewById(R.id.login_right);
        window.setGravity(Gravity.CENTER);
        tvLeft.setOnClickListener(listener);
        tvRight.setOnClickListener(listener);
        tv_content.setText(content);

        return instance;
    }
}
