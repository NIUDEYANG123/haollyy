package com.haolyy.haolyy.ui.home.View;


import com.haolyy.haolyy.entity.Notice;
import com.haolyy.haolyy.entity.my.MessageBean;

/**
 * @author wangyin
 * @date 2017/10/28.
 * @description :
 */

public interface NoticeView {
    void getMessage(Notice notice);
    void getMessageMore(Notice notice);
}
