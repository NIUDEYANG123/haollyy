package com.haolyy.haolyy.entity;

import android.os.ParcelUuid;

import com.haolyy.haolyy.base.BaseBean;

import java.io.Serializable;
import java.util.List;

/**
 * @author wyman
 * @date 2018/3/3
 * description : 公告
 */

public class Notice implements Serializable{
    /**
     * code : success
     * msg : 成功
     * model : {"total":1,"list":[{"id":38,"title":"好利标题","noticeContext":"好利描述","createTime":"2017-11-10 20:10:04","read":false}]}
     */

    private String code;
    private String msg;
    private ModelBean model;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ModelBean getModel() {
        return model;
    }

    public void setModel(ModelBean model) {
        this.model = model;
    }

    public static class ModelBean implements Serializable{
        /**
         * total : 1
         * list : [{"id":38,"title":"好利标题","noticeContext":"好利描述","createTime":"2017-11-10 20:10:04","read":false}]
         */

        private int total;
        private List<ListBean> list;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Serializable{
            @Override
            public String toString() {
                return "ListBean{" +
                        "id=" + id +
                        ", title='" + title + '\'' +
                        ", noticeContext='" + noticeContext + '\'' +
                        ", createTime='" + createTime + '\'' +
                        ", read=" + read +
                        '}';
            }

            /**
             * id : 38
             * title : 好利标题
             * noticeContext : 好利描述
             * createTime : 2017-11-10 20:10:04
             * read : false
             */

            private int id;
            private String title;
            private String noticeContext;
            private String createTime;
            private boolean read;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getNoticeContext() {
                return noticeContext;
            }

            public void setNoticeContext(String noticeContext) {
                this.noticeContext = noticeContext;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public boolean isRead() {
                return read;
            }

            public void setRead(boolean read) {
                this.read = read;
            }
        }
    }
}
