package com.haolyy.haolyy.ui.third;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.haolyy.haolyy.R;
import com.haolyy.haolyy.base.RxBus;

/**
 * @author wyman
 * @date 2018/2/7
 * description :
 */

public class GuideSkipActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_skip);

        new Handler().postDelayed(() -> {
            RxBus.getInstance().post("guideSkip");
            finish();
        }, 5000);
    }
}
