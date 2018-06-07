package com.haolyy.haolyy.custom.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.TextView;

import com.haolyy.haolyy.R;


public class DialogSignProcess extends Dialog {

    public DialogSignProcess(Context context) {
        super(context, R.style.dialogWrong);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.gravity = Gravity.CENTER | Gravity.CENTER_HORIZONTAL;
        getWindow().setAttributes(attributes);
        super.setContentView(R.layout.dialog_process);
    }


}
