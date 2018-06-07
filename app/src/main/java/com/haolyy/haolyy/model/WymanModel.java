package com.haolyy.haolyy.model;


import com.haolyy.haolyy.base.BaseApplication;
import com.haolyy.haolyy.base.BaseBean;
import com.haolyy.haolyy.base.BaseModel;
import com.haolyy.haolyy.config.Config;
import com.haolyy.haolyy.entity.IsUpdateBean;
import com.haolyy.haolyy.entity.my.MessageBean;
import com.haolyy.haolyy.service.Api;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by haolyy on 2017/6/29.
 */

public class WymanModel extends BaseModel {
    private Api api;

    private WymanModel() {
        super();
        api = retrofit.create(Api.class);
    }

    private static class SingletonHolder {
        private static final WymanModel WYMAN_MODEL = new WymanModel();
    }

    public static WymanModel getInstance() {
        return SingletonHolder.WYMAN_MODEL;
    }


    public Observable<MessageBean> getMessage(String page_index){
        Map<String, String> map = new HashMap<>();
        map.clear();
        map.put("user_id", BaseApplication.userId+"");
        map.put("page_index", page_index);
        map.put("mobile", BaseApplication.mUserName);
        return api.getMessage(map);
    }
    public Observable<BaseBean> modificationStatus(String id, String status){
        Map<String, String> map = new HashMap<>();
        map.clear();
        map.put("id", id);
        map.put("status", status);
        return api.modificationStatus(map);
    }

    /**
     * 手势密码
     * @param subscriber
     */
    public void gesture(String userCode,String setHandPasswordFlag,Subscriber<BaseBean> subscriber) {
        Map<String, String> map = new HashMap<>();
        map.put("userCode", userCode);
        map.put("setHandPasswordFlag", setHandPasswordFlag);
        Observable<BaseBean> observable =  api.gesture(map);
        toSubscribe(observable, subscriber);
    }
}
