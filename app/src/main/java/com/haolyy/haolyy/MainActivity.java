package com.haolyy.haolyy;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.haolyy.haolyy.base.ActivityCollector;
import com.haolyy.haolyy.base.BaseActivity;
import com.haolyy.haolyy.base.BaseApplication;
import com.haolyy.haolyy.base.BasePresenter;
import com.haolyy.haolyy.config.Config;
import com.haolyy.haolyy.config.ConstantKey;
import com.haolyy.haolyy.custom.NoScrollViewPager;
import com.haolyy.haolyy.custom.dialog.DialogUpdate;
import com.haolyy.haolyy.entity.IsUpdateBean;
import com.haolyy.haolyy.model.UserModel;
import com.haolyy.haolyy.ui.account.CheckUsernameActivity;
import com.haolyy.haolyy.ui.account.LoginActivity;
import com.haolyy.haolyy.ui.home.HomeFragment;
import com.haolyy.haolyy.ui.more.FindFragment;
import com.haolyy.haolyy.ui.my.AboutUsActivity;
import com.haolyy.haolyy.ui.my.MyFragment;
import com.haolyy.haolyy.ui.product.ProductFragment;
import com.haolyy.haolyy.utils.CommonUtils;
import com.haolyy.haolyy.utils.DownloadService;
import com.haolyy.haolyy.utils.LogUtils;
import com.haolyy.haolyy.utils.SPUtils;
import com.haolyy.haolyy.utils.UIUtils;
import com.haolyy.haolyy.utils.UpdateService;
import com.haolyy.haolyy.utils.WYUtils;
import com.tbruyelle.rxpermissions.Permission;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.wyman.wangyin.mylibrary.ProgressSubscriber;
import com.wyman.wangyin.mylibrary.SubscriberOnNextListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;

import static com.haolyy.haolyy.config.Config.success;


/**
 * Created by haolyy on 2017/5/16.
 */

public class MainActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.id_tab_ll_01)
    LinearLayout idTabLl01;
    @BindView(R.id.id_tab_ll_02)
    LinearLayout idTabLl02;
    //    @BindView(R.id.id_tab_ll_03)
//    LinearLayout idTabLl03;
    @BindView(R.id.id_tab_ll_04)
    LinearLayout idTabLl04;
    @BindView(R.id.id_main_viewPager)
    NoScrollViewPager idMainViewPager;
    @BindView(R.id.id_tab_iv_01)
    ImageView idTabIv01;
    @BindView(R.id.id_tab_tv_01)
    TextView idTabTv01;
    @BindView(R.id.id_tab_iv_02)
    ImageView idTabIv02;
    @BindView(R.id.id_tab_tv_02)
    TextView idTabTv02;
    //    @BindView(R.id.id_tab_iv_03)
//    ImageView idTabIv03;
//    @BindView(R.id.id_tab_tv_03)
//    TextView idTabTv03;
    @BindView(R.id.id_tab_iv_04)
    ImageView idTabIv04;
    @BindView(R.id.id_tab_tv_04)
    TextView idTabTv04;
    private HomeFragment homeFragment;
    private ProductFragment productFragment;
    //    private FindFragment findFragment;
    private MyFragment myFragment;
    private List<Fragment> fragments;
    public static int currentPage;
    private static final String TAG = "MainActivity";
    private ImmersionBar mImmersionBar;
    private AlertDialog.Builder dialog;
    private AlertDialog dia;

    /**
     * 跳转到主页的方法
     * @param context
     * @param page
     * @return
     */
    public static Intent getMainIntent(Context context, int page) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("current", page);
        return intent;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
        LogUtils.e("UMENG_CHANNEL",WYUtils.getAppMetaData(mContext,"UMENG_CHANNEL"));
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*int cp = getIntent().getIntExtra("current", -1);
        LogUtils.e("current", cp + "");
        if (cp != -1) {
            currentPage = cp;
        }*/
        setTabSelection(currentPage);
    }

    /**
     * n+1次跳转到主页
     *
     * @param intent
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        LogUtils.e("onNewIntent", "onNewIntent");
        int cp = getIntent().getIntExtra("current", -1);
        if (cp != -1) {
            currentPage = cp;
        }
        initIntent(intent);
    }

    private void initIntent(Intent intent) {

        // 跳转到view
        if (intent == null || intent.getBooleanExtra("exitLogin", false)) {
            openActivity(new Intent(mContext, CheckUsernameActivity.class));
        }
    }

    private void init() {
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(this, mPermissionList, 123);
        }

        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE
                        , Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.BLUETOOTH)
                .subscribe(aBoolean -> {
                    if (aBoolean) {
                        //当所有权限都允许之后，返回true
                    } else {
                        //只要有一个权限禁止，返回false，
                        //下一次申请只申请没通过申请的权限
                    }
                });


        isUpdate();
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarDarkFont(true).init();

        fragments = new ArrayList<>();

        homeFragment = new HomeFragment();
        productFragment = new ProductFragment();
//        findFragment = new FindFragment();
        myFragment = new MyFragment();
        fragments.add(homeFragment);
        fragments.add(productFragment);
//        fragments.add(findFragment);
        fragments.add(myFragment);
        idMainViewPager.setOffscreenPageLimit(fragments.size());
        idMainViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });

        idTabLl01.setOnClickListener(this);
        idTabLl02.setOnClickListener(this);
//        idTabLl03.setOnClickListener(this);
        idTabLl04.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_tab_ll_01:
                currentPage = 0;
                setTabSelection(currentPage);
                break;
            case R.id.id_tab_ll_02:
                currentPage = 1;
                setTabSelection(currentPage);
                break;
//            case R.id.id_tab_ll_03:
//                currentPage = 2;
//                setTabSelection(currentPage);
//                break;
            case R.id.id_tab_ll_04:
                currentPage = 3;
                setTabSelection(currentPage);
                break;
        }
    }


    public void setTabSelection(int currentPage) {
        //选中前清除状态
        restView();
        switch (currentPage) {
            case 0://未登录
                idTabIv01.setImageResource(R.drawable.ic_home_checked);
                idTabTv01.setTextColor(getResources().getColor(R.color.tv_navigate_checked));
                switchPager(currentPage);
                mImmersionBar.statusBarDarkFont(false).init();//白 黑 黑
                break;
            case 1:
                idTabIv02.setImageResource(R.drawable.ic_product_checked);
                idTabTv02.setTextColor(getResources().getColor(R.color.tv_navigate_checked));
                switchPager(currentPage);
                mImmersionBar.statusBarDarkFont(true).init();
                break;
//            case 2:
//                idTabIv03.setImageResource(R.drawable.ic_baina_checked);
//                idTabTv03.setTextColor(getResources().getColor(R.color.tv_navigate_checked));
//                switchPager(currentPage);
//                break;
            case 3:
                idTabIv04.setImageResource(R.drawable.ic_my_checked);
                idTabTv04.setTextColor(getResources().getColor(R.color.tv_navigate_checked));
                switchPager(currentPage);
                mImmersionBar.statusBarDarkFont(true).init();
                break;
        }
    }

    private void switchPager(int currentPage) {
        idMainViewPager.setCurrentItem(currentPage, false);
    }

    /**
     * 重置所有状态
     */
    private void restView() {
        idTabIv01.setImageResource(R.drawable.ic_home);
        idTabIv02.setImageResource(R.drawable.ic_product);
//        idTabIv03.setImageResource(R.drawable.ic_baina);
        idTabIv04.setImageResource(R.drawable.ic_my);
        idTabTv01.setTextColor(getResources().getColor(R.color.tv_navigate));
        idTabTv02.setTextColor(getResources().getColor(R.color.tv_navigate));
//        idTabTv03.setTextColor(getResources().getColor(R.color.tv_navigate));
        idTabTv04.setTextColor(getResources().getColor(R.color.tv_navigate));

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(TAG, currentPage);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        currentPage = savedInstanceState.getInt(TAG);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (dia != null) {
            dia.dismiss();
            BaseApplication.noIsApp = false;
        }
        return WYUtils.clickBack(keyCode, event, MainActivity.this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mImmersionBar.destroy();
    }

    /**
     * 检测更新
     */
    public void isUpdate() {
        /*
        可以更新下载进度的更新服务暂时不用
        removeOldApk();
        Intent intent = new Intent(this, DownloadService.class);
        intent.putExtra(DownloadService.BUNDLE_KEY_DOWNLOAD_URL, "http://openbox.mobilem.360.cn/index/d/sid/3091484");
        isBindService = bindService(intent, conn, BIND_AUTO_CREATE);*/
        UserModel.getInstance().isUpdate(new ProgressSubscriber<IsUpdateBean>(new SubscriberOnNextListener<IsUpdateBean>() {
            @Override
            public void onNext(IsUpdateBean baseBean) {
                if (success.equals(baseBean.code)) {
                    if ("1".equals(baseBean.model.state) && "1".equals(baseBean.model.verStatus)) {
                        DialogUpdate dialogUpdate = new DialogUpdate(mContext);
                        dialogUpdate.setCancelable(false);
                        dialogUpdate.setOnKeyListener(new DialogInterface.OnKeyListener() {
                            @Override
                            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                                if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_BACK) {
                                    exitApp();
                                }
                                return false;
                            }
                        });
                        dialogUpdate.setText(baseBean.model.version, baseBean.model.updateDesc).setOnDoubleClickListener(() -> new RxPermissions(MainActivity.this)
                                // 申请权限
                                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                .subscribe(granted -> {
                                    if (granted) {
                                        // 请求成功，开启服务，下载文件
                                        Intent intent = new Intent(mContext, UpdateService.class);
                                        intent.putExtra("url", baseBean.model.downloadUrl);
                                        mContext.startService(intent);
                                    } else {
                                        // 用户拒绝权限
                                        UIUtils.showToastCommon(mContext, "获取文件权限失败");
                                    }
                                })).show();
                    } else if (baseBean.model.verStatus.equals("2")) {
                        DialogUpdate dialogUpdate = new DialogUpdate(mContext);
                        dialogUpdate.setCancelable(false);
                        dialogUpdate.setText("", baseBean.model.updateDesc).setOnDoubleClickListener(() -> {

                        });
                        dialogUpdate.setOnKeyListener((dialogInterface, i, keyEvent) -> {
                            if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_BACK) {
                                exitApp();
                            }
                            return false;
                        });
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.e(tag, "update" + e.toString());
//                UIUtils.showToastCommon(mContext, "获取最新版本失败，请检测网络是否正常");
            }

        }, mContext));
    }


    /**
     * 下面是监听下载进度的service
     */
    private boolean isBindService;

    private ServiceConnection conn = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            DownloadService.DownloadBinder binder = (DownloadService.DownloadBinder) service;
            DownloadService downloadService = binder.getService();

            //接口回调，下载进度
            downloadService.setOnProgressListener(new DownloadService.OnProgressListener() {
                @Override
                public void onProgress(float fraction) {
                    LogUtils.e(TAG, "下载进度：" + fraction);

                    //判断是否真的下载完成进行安装了，以及是否注册绑定过服务
                    if (fraction == DownloadService.UNBIND_SERVICE && isBindService) {
                        unbindService(conn);
                        isBindService = false;
                        UIUtils.showToastCommon(mContext, "下载完成");
                    }
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    /**
     * 删除上次更新存储在本地的apk
     */
    private void removeOldApk() {
        //获取老ＡＰＫ的存储路径
        File fileName = new File(SPUtils.getString(mContext, ConstantKey.SP_DOWNLOAD_PATH, ""));
        if (fileName != null && fileName.exists() && fileName.isFile()) {
            fileName.delete();
            LogUtils.e(TAG, "存储器内存在老APK，进行删除操作");
        }
    }
}
