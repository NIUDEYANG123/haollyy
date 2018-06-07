package com.haolyy.haolyy;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        MyHandlerThread handlerThread = new MyHandlerThread("子线程");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper(), handlerThread);

        LooperThread looperThread = new LooperThread("looperThread");
        looperThread.start();
        looperThread.mHandler.sendEmptyMessage(2);//貌似行不通

        handler.postDelayed(() -> {
            Message message = handler.obtainMessage();
            message.arg1 = 1;
            message.sendToTarget();
        }, 3000);

    }

    class MyHandlerThread extends HandlerThread implements Handler.Callback {

        public MyHandlerThread(String name) {
            super(name);
        }

        @Override
        public boolean handleMessage(Message msg) {
            //打印线程的名称
            System.out.println(" handleMessage CurrentThread = " + msg.arg1 + Thread.currentThread().getName());
            return false;
        }
    }

    public Handler mHandler;

    class LooperThread extends Thread {
        public Handler mHandler;

        public LooperThread(String name) {
            super(name);
        }

        public void run() {
            Looper.prepare();
            mHandler = new Handler() {
                public void handleMessage(Message msg) {
                    // process incoming messages here
                    System.out.println(" handleMessage CurrentThread = " + msg.what + Thread.currentThread().getName());
                }
            };
            Looper.loop();
        }
    }
}