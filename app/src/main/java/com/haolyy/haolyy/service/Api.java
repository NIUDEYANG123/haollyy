package com.haolyy.haolyy.service;

import com.haolyy.haolyy.base.BaseBean;
import com.haolyy.haolyy.config.NetConstantValues;
import com.haolyy.haolyy.entity.my.MessageBean;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by haolyy on 2017/6/29.
 */

public interface Api {


    @FormUrlEncoded
    @POST(NetConstantValues.MESSAGE_LIST)
    Observable<MessageBean> getMessage(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(NetConstantValues.MODIFICATION_MESSAGE)
    Observable<BaseBean> modificationStatus(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(NetConstantValues.GESTURE)
    Observable<BaseBean> gesture(@FieldMap Map<String, String> params);
}
