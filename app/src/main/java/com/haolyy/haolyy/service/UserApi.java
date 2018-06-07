package com.haolyy.haolyy.service;


import com.haolyy.haolyy.base.BaseBean;
import com.haolyy.haolyy.config.NetConstantValues;
import com.haolyy.haolyy.entity.BBaseBean;
import com.haolyy.haolyy.entity.IsUpdateBean;
import com.haolyy.haolyy.entity.RTokenBean;
import com.haolyy.haolyy.entity.TokenBean;
import com.haolyy.haolyy.entity.bank.BankListBean;
import com.haolyy.haolyy.entity.login.LoginBean;
import com.haolyy.haolyy.entity.login.PassWordBean;
import com.haolyy.haolyy.entity.login.RegsiterBean;
import com.haolyy.haolyy.entity.userinfo.GetUserStatusBean;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by niudeyang on 2017/8/14.
 */

public interface UserApi {

    //json字符串
    @Headers({"Content-Type: application/json;charset=UTF-8", "Accept: application/json"})
    @POST(NetConstantValues.USER_LOGIN)
    Observable<LoginBean> login(@Body RequestBody requestBody);

    @FormUrlEncoded
    @POST(NetConstantValues.USER_LOGIN)
    Observable<LoginBean> login(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(NetConstantValues.USER_LOGINOUT)
    Observable<BBaseBean> logout(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(NetConstantValues.TOKEN)
    Observable<TokenBean> getToken(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(NetConstantValues.REFRESH_TOKEN)
    Observable<RTokenBean> refreshToken(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(NetConstantValues.SMSCODE)
    Observable<BBaseBean> getSmsCode(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(NetConstantValues.USER_REGISTER)
    Observable<RegsiterBean> register(@FieldMap Map<String, String> params);
    @FormUrlEncoded
    @POST(NetConstantValues.USER_IS_REGIESTER)
    Observable<BaseBean> isRegister(@FieldMap Map<String, String> params);
    @FormUrlEncoded
    @POST(NetConstantValues.USER_RESETPWD)
    Observable<PassWordBean> forgetPassWord(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(NetConstantValues.USERSTATUS)
    Observable<GetUserStatusBean> getUserStatus(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(NetConstantValues.USER_CAHNGE_PASS)
    Observable<BaseBean> changePass(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(NetConstantValues.GET_BANKS)
    Observable<BankListBean> getBanks(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(NetConstantValues.IS_UPDATE)
    Observable<IsUpdateBean> isUpdate(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(NetConstantValues.VALIDATESMSCODE)
    Observable<BBaseBean> validateSmsCode(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(NetConstantValues.USER_CHANGE_MOBILE)
    Observable<BBaseBean> changeMobile(@FieldMap Map<String, String> params);

}
