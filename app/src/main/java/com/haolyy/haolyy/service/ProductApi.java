package com.haolyy.haolyy.service;

import com.haolyy.haolyy.base.BaseBean;
import com.haolyy.haolyy.config.NetConstantValues;
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

import java.util.Map;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by niudeyang on 2017/8/14.
 */

public interface ProductApi {

    @FormUrlEncoded
    @POST(NetConstantValues.HOME_BANNER)
    Observable<HomeBannerBean> homebanner(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(NetConstantValues.NOTICE)
    Observable<Notice> getNotice(@FieldMap Map<String, String> params);
    @FormUrlEncoded
    @POST(NetConstantValues.BILL_ADVANCE)
    Observable<ReturnedBean> queryBillInvestAdvance(@FieldMap Map<String, String> params);
    @FormUrlEncoded
    @POST(NetConstantValues.HOME_BORROW_LIST)
    Observable<HomeListBean> queryList(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(NetConstantValues.PRODUCT_LIST)
    Observable<PlanListBean> planList(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(NetConstantValues.SYANDARD_PLANLIST)
    Observable<ProductSanListBean> standardAndPlanList(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(NetConstantValues.PRODUCT_DETAIL)
    Observable<ProductDetailBean> productDetail(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(NetConstantValues.INVEST_RECORD)
    Observable<InvestRecordBean> investRecord(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(NetConstantValues.REPAY_PLAN)
    Observable<RepayPlanBean> repayPlan(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(NetConstantValues.SAN_REPAY_PLAN)
    Observable<SanRepayPlanBean> sanrepayPlan(@FieldMap Map<String, String> params);
    @FormUrlEncoded
    @POST(NetConstantValues.PLANBORROWFORAPP)
    Observable<PlanBorrowForAppBean> planBorrowForAppBean(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(NetConstantValues.BORROWPLANLIST)
    Observable<BorrowPlanListBean> borrowPlanList(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(NetConstantValues.DQYMORE)
    Observable<DqyBean> getDqyMore(@FieldMap Map<String, String> params);
}
