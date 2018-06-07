package com.haolyy.haolyy.custom.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.haolyy.haolyy.R;
import com.haolyy.haolyy.utils.CommonUtils;
import com.haolyy.haolyy.utils.WYUtils;

public class DialogSignFragment extends Dialog {

    private final View v_close;
    private final TextView tv;
    private DialogSignFragment instance;
    private SignInterFace ss;
    private final TextView tv5;

    public DialogSignFragment(@NonNull Context context) {
        super(context, R.style.dialogWrong);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.gravity = Gravity.CENTER | Gravity.CENTER_HORIZONTAL;
        getWindow().setAttributes(attributes);
        super.setContentView(R.layout.dialog_sign);
        instance = this;
        v_close = findViewById(R.id.v_close);
        tv = findViewById(R.id.tv_sign);
        tv5 = findViewById(R.id.tv_h5);
        SpannableString spannableString = new SpannableString("点击开启按钮,即代表您同意《自动投标授委托书》");
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FF9933")), 13, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv5.setText(spannableString);
        v_close.setOnClickListener(v -> dismiss());
        tv.setOnClickListener(v -> {
            ss.sign();
            dismiss();
        });
        tv5.setOnClickListener(v -> ss.showSignH5());
    }

    public interface SignInterFace {
        void sign();

        void showSignH5();
    }

    public DialogSignFragment setSignInterface(SignInterFace s) {
        this.ss = s;
        return instance;
    }
}
