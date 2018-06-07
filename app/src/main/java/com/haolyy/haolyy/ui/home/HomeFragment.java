package com.haolyy.haolyy.ui.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ajguan.library.EasyRefreshLayout;
import com.ajguan.library.LoadModel;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.haolyy.haolyy.MainActivity;
import com.haolyy.haolyy.R;
import com.haolyy.haolyy.adapter.HomeProjectAdapter;
import com.haolyy.haolyy.base.BaseApplication;
import com.haolyy.haolyy.base.BaseFragment;
import com.haolyy.haolyy.base.WebActivity;
import com.haolyy.haolyy.config.NetConstantValues;
import com.haolyy.haolyy.custom.AutoVerticalScrollTextView;
import com.haolyy.haolyy.custom.InnerScrollListView;
import com.haolyy.haolyy.custom.LocalImageHolderView;
import com.haolyy.haolyy.custom.dialog.DialogInvestTips;
import com.haolyy.haolyy.entity.Notice;
import com.haolyy.haolyy.entity.product.HomeBannerBean;
import com.haolyy.haolyy.entity.product.HomeListBean;
import com.haolyy.haolyy.ui.home.View.HomeView;
import com.haolyy.haolyy.ui.home.presenter.HomePresenter;
import com.haolyy.haolyy.ui.product.ProductPlanDetailActivity;
import com.haolyy.haolyy.ui.product.ProductWinMoreActivity;
import com.haolyy.haolyy.utils.LogUtils;
import com.haolyy.haolyy.utils.WYUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * Created by haolyy on 2017/8/9.
 */

public class HomeFragment extends BaseFragment<HomePresenter, HomeView> implements HomeView, EasyRefreshLayout.EasyEvent {
    Unbinder unbinder;
    @BindView(R.id.banner)
    ConvenientBanner banner;
    @BindView(R.id.id_tab_iv_01)
    ImageView idTabIv01;
    @BindView(R.id.id_tab_iv_02)
    ImageView idTabIv02;
    @BindView(R.id.id_tab_iv_03)
    ImageView idTabIv03;
    @BindView(R.id.id_tab_iv_04)
    ImageView idTabIv04;
    @BindView(R.id.ll_new)
    LinearLayout llNew;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.swipe_container)
    EasyRefreshLayout easyRefreshLayout;
    @BindView(R.id.ll_project)
    LinearLayout llProject;
    @BindView(R.id.lv_project)
    InnerScrollListView lvProject;
    @BindView(R.id.ll_notice)
    LinearLayout llNotice;
    @BindView(R.id.notice_auto_roll)
    AutoVerticalScrollTextView noticeAutoRoll;
    @BindView(R.id.tv_news_periodLength)
    TextView tvNewsPeriodLength;
    @BindView(R.id.tv_news_periodUnit)
    TextView tvNewsPeriodUnit;
    @BindView(R.id.tv_news_investMinAmount)
    TextView tvNewsInvestMinAmount;
    @BindView(R.id.tv_news_annualizedRate)
    TextView tvNewsAnnualizedRate;
    @BindView(R.id.register_count)
    TextView registerCount;
    @BindView(R.id.tv_invest_count)
    TextView tvInvestCount;
    @BindView(R.id.tv_total_income)
    TextView tvTotalIncome;
    @BindView(R.id.tv_home_annotation)
    TextView tvHomeAnnotation;
    @BindView(R.id.rl_iv_notice)
    LinearLayout rlIvNotice;
    @BindView(R.id.rl_notice)
    RelativeLayout rlNotice;
    @BindView(R.id.notice_img)
    ImageView noticeImg;
    @BindView(R.id.id_tab_tv_01)
    TextView idTabTv01;
    @BindView(R.id.id_tab_ll_01)
    LinearLayout idTabLl01;
    @BindView(R.id.id_tab_tv_02)
    TextView idTabTv02;
    @BindView(R.id.id_tab_ll_02)
    LinearLayout idTabLl02;
    @BindView(R.id.id_tab_tv_03)
    TextView idTabTv03;
    @BindView(R.id.id_tab_ll_03)
    LinearLayout idTabLl03;
    @BindView(R.id.id_tab_tv_04)
    TextView idTabTv04;
    @BindView(R.id.id_tab_ll_04)
    LinearLayout idTabLl04;
    @BindView(R.id.ll_new_item)
    LinearLayout llNewItem;
    private DialogInvestTips dialogInvestTips;
    private ArrayList<String> imgs;
    private View view;
    private List<String> auto_roll_strings;
    private int autoRollIndex;
    private HomeBannerBean.ModelBean.ArticleBean articleBean;
    private HomeListBean.ModelBean.NoviciateBorrowBean noviciateBorrowBean;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 199) {
                noticeAutoRoll.next();
                autoRollIndex++;
                noticeAutoRoll.setText(auto_roll_strings.get(autoRollIndex % auto_roll_strings.size()));
                sendEmptyMessageDelayed(199, 3000);
            }
        }
    };
    private List<HomeListBean.ModelBean.PlanBorrowBean> borrowBeans;
    private List<Notice.ModelBean.ListBean> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home_main, container, false);
        unbinder = ButterKnife.bind(this, view);
        init();
        easyRefreshLayout.addEasyEvent(this);
        //隐藏上拉加载
        easyRefreshLayout.setLoadMoreModel(LoadModel.NONE);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.fetch("1", "3");
        //scrollView定位到顶部
        lvProject.setFocusable(false);
    }

    private void init() {
        dialogInvestTips = new DialogInvestTips(getActivity());
        dialogInvestTips.setText("敬请期待", "确认");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    @OnClick({R.id.id_tab_iv_01, R.id.id_tab_iv_02, R.id.id_tab_iv_03
            , R.id.id_tab_iv_04, R.id.ll_new, R.id.btn_next, R.id.ll_project, R.id.rl_iv_notice, R.id.rl_notice})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.id_tab_iv_01:
                startActivity(WebActivity.getWebIntent(mContext, "关于我们", NetConstantValues.CONTRACT_URL + "about/aboutUs.html"));
                break;
            case R.id.id_tab_iv_02:
                startActivity(WebActivity.getWebIntent(mContext, "信息披露", NetConstantValues.CONTRACT_URL + "about/disclosure.html"));
                break;
            case R.id.id_tab_iv_03:
                startActivity(WebActivity.getWebIntent(mContext, "平台数据", NetConstantValues.CONTRACT_URL + "about/disclosureD.html"));
                break;
            case R.id.id_tab_iv_04:
                dialogInvestTips.show();
               /* if (!BaseApplication.mLoginState) {
                    startActivity(new Intent(mContext, CheckUsernameActivity.class));
                    return;
                }else {
                   startActivity(WebActivity.getWebIntent(mContext,"邀请好友",NetConstantValues.invite));
                }*/
                break;
            case R.id.ll_new:
                mContext.startActivity(ProductWinMoreActivity.getReturnIntent(mContext, "1", "新手专享"));
                break;
            case R.id.btn_next:
                //新手标购买
                startActivity(ProductPlanDetailActivity.getDetailIntent(mContext, noviciateBorrowBean.getBorrowName(), noviciateBorrowBean.getBorrowNo(), false));
                //mContext.startActivity(ProductWinMoreActivity.getReturnIntent(mContext, "1", "新手专享"));
                break;
            case R.id.ll_project:
                ((MainActivity) getActivity()).setTabSelection(1);
                break;
            case R.id.rl_notice:
                startActivity(new Intent(mContext, NoticeActivity.class));
                break;
            case R.id.rl_iv_notice:
                if (null != list && null != auto_roll_strings) {
                    startActivity(WebActivity.getWebIntentContent(mContext, list.get(autoRollIndex % auto_roll_strings.size()).getTitle(), list.get(autoRollIndex % auto_roll_strings.size()).getNoticeContext()));
                }
                break;
        }
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(mContext);
    }

    @Override
    public void showBannerData(HomeBannerBean homeBannerBean) {
        if (homeBannerBean.model.banner.size() == 0) {
         /*   ArrayList<Integer> imgs = new ArrayList<>();
            imgs.add(R.mipmap.banner2);
            imgs.add(R.mipmap.banner1);
            imgs.add(R.mipmap.banner3);
            this.banner.setPages(new CBViewHolderCreator() {
                @Override
                public Object createHolder() {
                    return new LocalImageHolderViewNative();
                }
            }, imgs).setPageIndicator(new int[]{R.mipmap.point_circle, R.mipmap.point_solid});*/
        } else {
            if (imgs != null) {
                imgs.clear();
            }
           List<String> imgs = new ArrayList<>();
            for (int i = 0; i < homeBannerBean.model.banner.size(); i++) {
                imgs.add(homeBannerBean.model.banner.get(i).imageUrl);
            }
            this.banner.setPages(new CBViewHolderCreator() {
                @Override
                public Object createHolder() {
                    return new LocalImageHolderView();
                }
            }, imgs)
//                    .setPageIndicator(new int[]{R.mipmap.point_circle, R.mipmap.point_solid})
            ;

            if (imgs.size() > 1) {
                this.banner.startTurning(2000);
            }
            this.banner.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    HomeBannerBean.ModelBean.BannerBean bannerBean = homeBannerBean.model.banner.get(position);
                    if (!TextUtils.isEmpty(bannerBean.linkUrl)) {
                        LogUtils.e(tag, bannerBean.linkUrl);
                        if (bannerBean.linkUrl.equals("1")) {
//                        ((MainActivity) getActivity()).setTabSelection(1);
                        } else if (bannerBean.linkUrl.equals("2")) {
//                        ((MainActivity) getActivity()).setTabSelection(2);
                        }  else if (bannerBean.linkUrl.contains("carnivalJun")) {
                            startActivity(WebActivity.getWebIntent(mContext, "  ", bannerBean.linkUrl+"&userCode="+BaseApplication.juid));
                        } else {
                            startActivity(WebActivity.getWebIntent(mContext, "  ", bannerBean.linkUrl));
                        }
                    }
                }
            });
        }
        /**
         * 统计
         */
        if (homeBannerBean.model.homestatic != null) {
            registerCount.setText(WYUtils.getTwoNumStr(Double.valueOf(homeBannerBean.model.homestatic.registerCount) / 10000) + "万");
            tvInvestCount.setText(WYUtils.getTwoNumStr(Double.valueOf(homeBannerBean.model.homestatic.amount) / 10000) + "万");
            tvTotalIncome.setText(WYUtils.getTwoNumStr(Double.valueOf(homeBannerBean.model.homestatic.income) / 10000) + "万");
        }
    }

    @Override
    public void showHomeList(HomeListBean homeListBean) {
        if (null != homeListBean.getModel().getNoviciateBorrow() && homeListBean.getModel().getNoviciateBorrow().size() > 0) {
            noviciateBorrowBean = homeListBean.getModel().getNoviciateBorrow().get(0);
            llNewItem.setVisibility(View.VISIBLE);
            llNew.setVisibility(View.VISIBLE);
            if (noviciateBorrowBean != null) {
                // 新手标数据
                tvNewsAnnualizedRate.setText(noviciateBorrowBean.getAnnualizedRate() + noviciateBorrowBean.getAppendRate() + "");
                tvNewsInvestMinAmount.setText((int) noviciateBorrowBean.getInvestMinAmount() + "");
                tvNewsPeriodLength.setText(noviciateBorrowBean.getPeriodLength() + "");
                tvNewsPeriodUnit.setText(WYUtils.periodUnit(noviciateBorrowBean.getPeriodUnit() + ""));
            /*if (noviciateBorrowBean.getBorrowType() != 1 && noviciateBorrowBean.getPeriodLength() < 10) {
                tvHomeAnnotation.setVisibility(View.VISIBLE);
                tvHomeAnnotation.setText("债券期限10周，" + noviciateBorrowBean.getPeriodLength() + "周锁定期后可免费转让，存在无法转出可能");
            } else {
                tvHomeAnnotation.setVisibility(View.GONE);
            }*/
            }
        } else {
            llNew.setVisibility(View.GONE);
            llNewItem.setVisibility(View.GONE);
        }
        borrowBeans = homeListBean.getModel().getPlanBorrow();

        if (borrowBeans.size() > 0&&null!=lvProject) {
            //推荐标数据
            lvProject.setAdapter(new HomeProjectAdapter(borrowBeans, mContext));
            lvProject.setOnItemClickListener((adapterView, view, i, l) -> {
                if (borrowBeans.get(i).getPeriodLength() == 1) {
                    startActivity(ProductPlanDetailActivity.getDetailIntent(mContext, borrowBeans.get(i).getBorrowName(), borrowBeans.get(i).getBorrowNo(),
                            true));
                } else {
                    startActivity(ProductPlanDetailActivity.getDetailIntent(mContext, borrowBeans.get(i).getBorrowName(), borrowBeans.get(i).getBorrowNo(),
                            false));
                }
            });

        }
    }

    @Override
    public void showNotice(Notice notice) {
        /**
         * 公告
         */
        if (null != auto_roll_strings && auto_roll_strings.size() > 0)

        {
            auto_roll_strings.clear();
            handler.removeMessages(199);
        } else

        {
            auto_roll_strings = new ArrayList<>();
        }
        if (notice.getModel().getList().size() == 0) {
            llNotice.setVisibility(View.INVISIBLE);
        } else {
            llNotice.setVisibility(View.VISIBLE);
            list = notice.getModel().getList();
            for (int i = 0; i < list.size(); i++) {
                auto_roll_strings.add(list.get(i).getTitle());
            }
            handler.sendEmptyMessage(199);
        }
    }


    @Override
    public void onRefreshing() {
        easyRefreshLayout.refreshComplete();
        mPresenter.fetch("1", "3");
    }

    @Override
    public void onLoadMore() {
    }
}
