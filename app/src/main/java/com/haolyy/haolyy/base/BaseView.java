package com.haolyy.haolyy.base;

/**
 * @author wyman
 * @date 2017/10/26
 */

public interface BaseView<T> {
    /**
     * 获取通用数据
     *
     * @param t
     */
     void getCommonData(T t);

    /**
     * 网络请求失败
     */
     void requestError(String s);

    /**
     * 网络请求成功
     */
     void requestSuccess(String s);
}
