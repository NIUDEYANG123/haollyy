package com.haolyy.haolyy.ui.home.View;


import com.haolyy.haolyy.entity.my.MessageBean;

/**
 * @author wangyin
 * @date 2017/10/28.
 * @description :
 */

public interface MessageView {
    void getMessage(MessageBean messageBean);
    void getMessageMore(MessageBean messageBean);
    void updateMessage(String opr_type,int position);
}
