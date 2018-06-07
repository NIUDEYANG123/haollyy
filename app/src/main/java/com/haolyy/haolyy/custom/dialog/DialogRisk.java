package com.haolyy.haolyy.custom.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.haolyy.haolyy.R;

public class DialogRisk extends Dialog {

    private final View v_close;
    private final TextView tv;
    private DialogRisk instance;
    private SignInterFace ss;

    public DialogRisk(@NonNull Context context) {
        super(context, R.style.dialogWrong);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.gravity = Gravity.CENTER | Gravity.CENTER_HORIZONTAL;
        getWindow().setAttributes(attributes);
        super.setContentView(R.layout.dialog_risk);
        instance = this;
        v_close = findViewById(R.id.v_close);
        tv = findViewById(R.id.tv_sign);

        v_close.setOnClickListener(v -> dismiss());
        tv.setOnClickListener(v -> {
            ss.risk();
            dismiss();
        });
    }

    public interface SignInterFace {
        void risk();
    }

    public DialogRisk setSignInterface(SignInterFace s) {
        this.ss = s;
        return instance;
    }
}
