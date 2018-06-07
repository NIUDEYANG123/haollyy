package com.haolyy.haolyy.model;

import com.haolyy.haolyy.base.BaseApplication;
import com.haolyy.haolyy.base.BaseBean;
import com.haolyy.haolyy.base.BaseModel;
import com.haolyy.haolyy.entity.Notice;
import com.haolyy.haolyy.entity.ReturnedBean;
import com.haolyy.haolyy.entity.product.BorrowPlanListBean;
import com.haolyy.haolyy.entity.product.DqyBean;
import com.haolyy.haolyy.entity.product.HomeBannerBean;
import com.haolyy.haolyy.entity.product.HomeListBean;
import com.haolyy.haolyy.entity.product.InvestRecordBean;
import com.haolyy.haolyy.entity.product.PlanBorrowForAppBean;
import com.haolyy.haolyy.entity.product.PlanListBean;
import com.haolyy.haolyy.entity.product.ProductDetailBean;
import com.haolyy.haolyy.entity.product.ProductSanListBean;
import com.haolyy.haolyy.entity.product.RepayPlanBean;
import com.haolyy.haolyy.entity.product.SanRepayPlanBean;
import com.haolyy.haolyy.service.ProductApi;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;

import static com.haolyy.haolyy.base.BaseApplication.juid;
import static com.haolyy.haolyy.base.BaseApplication.mUserName;

/**
 * Created by niudeyang on 2017/8/14.
 */

public class ProductModel extends BaseModel {
    private ProductApi productApi;
    private static ProductModel productModel;

    private ProductModel() {
        super();
        productApi = retrofit.create(ProductApi.class);
    }

    public static ProductModel getInstance() {
        if (productModel == null) {
            synchronized (ProductModel.class) {
                if (productModel == null) {
                    productModel = new ProductModel();
                }
            }
        }
        return productModel;
    }


    /**
     * 产品列表
     * 计划标预约标
     *
     * @param pageIndex
     * @return
     */

    public Observable<PlanListBean> planList(String pageIndex) {
        Map map = new HashMap<>();
        // map.put("platform",platform);
        map.put("pageIndex", pageIndex);
        map.put("borrowType", "2");
        return productApi.planList(map);
    }


    /**
     * 新手专享 赢计划查看更多
     *
     * @param productNo
     * @param pageIndex borrowActiveType 0:正常 1:新手标 2:活动标
     * @return
     */
    public Observable<ProductSanListBean> standardAndPlanList(String productNo, String borrowActiveType, String pageIndex) {
        Map map = new HashMap<>();
        map.put("productNo", productNo);
        //map.put("platform",platform);
        map.put("pageIndex", pageIndex);
        map.put("borrowType", "2");
        map.put("borrowActiveType", borrowActiveType);
        return productApi.standardAndPlanList(map);
    }

    /**
     * 短期赢更多
     *
     * @param pageIndex
     * @return
     */
    public Observable<DqyBean> getDqyMore(String pageIndex) {
        Map map = new HashMap<>();
        map.put("pageIndex", pageIndex);
        return productApi.getDqyMore(map);
    }

    /**
     * 散标、预约标列表2.1
     *
     * @param borrowType
     * @param appendRate
     * @param peroidLength
     * @param pageIndex
     * @return
     */
    public Observable<BorrowPlanListBean> borrowPlanList(String borrowType, String appendRate, String peroidLength, int pageIndex) {
        Map map = new HashMap<>();
        map.put("phoneNum", mUserName);
        map.put("borrowType", borrowType);
        //map.put("appendRate", appendRate);
        //map.put("periodLength", peroidLength);
        map.put("pageIndex", pageIndex + "");
        return productApi.borrowPlanList(map);
    }

    /**
     * 标的详情
     *
     * @param borrowNo
     * @return
     */
    public Observable<ProductDetailBean> productDetail(String borrowNo) {
        Map map = new HashMap<>();
        map.put("borrowNo", borrowNo);
        return productApi.productDetail(map);
    }

    /**
     * 首页列表
     * @return
     */
    public Observable<HomeListBean> queryList() {
        Map map = new HashMap<>();
        //map.put("platform", platform);
        // map.put("client", client);
        if (BaseApplication.mLoginState) {
            map.put("phoneNum", mUserName);
        }
        map.put("recommendNo", "1");
        map.put("planNo", "4");
        return productApi.queryList(map);
    }


    /**
     * banner和公告
     *
     * @return
     */
    public Observable<HomeBannerBean> gethomebanner() {
        Map map = new HashMap<>();
        map.put("phoneNum", mUserName);
        //map.put("platform", platform);
        //map.put("client", client);
        return productApi.homebanner(map);
    }

    /**
     * 公告列表
     *
     * @return
     */
    public Observable<Notice> getNotice(String pageNum, String pageSize) {
        Map map = new HashMap<>();
        map.put("pageNum", pageNum);
        map.put("pageSize", pageSize);
        return productApi.getNotice(map);
    }

    /**
     * 回款预告
     *
     * @return
     */
    public Observable<ReturnedBean> queryBillInvestAdvance(String pageNum) {
        Map map = new HashMap<>();
        map.put("userCode", juid);
        map.put("pageIndex", pageNum);
        return productApi.queryBillInvestAdvance(map);
    }

    /**
     * 投资记录
     *
     * @param borrowNo
     * @param pageIndex
     * @return
     */
    public Observable<InvestRecordBean> getInvestRecord(String borrowNo, String pageIndex) {
        Map map = new HashMap<>();
        map.put("borrowNo", borrowNo);
        map.put("pageIndex", pageIndex);
        return productApi.investRecord(map);
    }

    /**
     * 还款计划
     *
     * @param borrowNo
     * @param pageIndex
     * @return
     */
    public Observable<RepayPlanBean> repayPlan(String borrowNo, String pageIndex, String pageSize) {
        Map map = new HashMap<>();
        map.put("borrowNo", borrowNo);
        map.put("pageIndex", pageIndex);
        map.put("pageSize", pageSize);
        return productApi.repayPlan(map);
    }

    /**
     * 散标还款计划
     *
     * @param borrowNo
     * @return
     */
    public Observable<SanRepayPlanBean> getSanrepayPlan(String borrowNo) {
        Map map = new HashMap<>();
        map.put("borrowNo", borrowNo);
        return productApi.sanrepayPlan(map);
    }

    /**
     * 只显示5个标
     *
     * @return
     */
    public Observable<PlanBorrowForAppBean> getplanForApp() {
        Map map = new HashMap<>();
        return productApi.planBorrowForAppBean(map);
    }
}
