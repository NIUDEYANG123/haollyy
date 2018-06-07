package com.haolyy.haolyy.base;

import rx.Subscription;

/**
 * Created by wyman on 2017/4/7.
 */

public interface LifeSubscription {
    void bindSubscription(Subscription subscription);
}
