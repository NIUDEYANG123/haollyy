package com.haolyy.haolyy.entity.my;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wangyin on 2017/8/2.
 */

public class MessageBean implements Serializable {

    /**
     * code : 200
     * msg : 成功
     * model : {"code":"200","msg":"成功","model":{"accountMessages":[{"send_time":"2017-07-04 11:06:26","id":16,"title":"五一劳动节假期安排公告","type":2,"content":"尊敬的185****5479；\r\n您好！\r\n五一放假期间，好利网客服服务时间调整为：2017年4月29日8:00-12:00，4月30日09:00-18:00，5月1日10:00-15:00，5月2日恢复正常工作时间（8:00-22:00），给您带来不便敬请谅解，好利网祝您理财愉快！\r\n\r\n感谢您对我们的关注与支持。\r\n","status":1},{"send_time":"2017-07-04 11:05:37","id":15,"title":"优惠卷通知","type":1,"content":"用户185****5479您好，您有一张加息券将于明天2017-07-5到期，单笔投资满1000-10000可使用，累计投资满1000-10000可使用。","status":2},{"send_time":"2017-07-04 11:03:58","id":14,"title":"优惠卷通知","type":1,"content":"用户185****5479您好，您有一张加息券将于明天2017-07-5到期，单笔投资满10000-100000可使用，累计投资满10000-100000可使用。","status":3},{"send_time":"2017-07-04 11:02:39","id":13,"title":"五一劳动节假期安排公告","type":2,"content":"尊敬的185****5479；\r\n您好！\r\n五一放假期间，好利网客服服务时间调整为：2017年4月29日8:00-12:00，4月30日09:00-18:00，5月1日10:00-15:00，5月2日恢复正常工作时间（8:00-22:00），给您带来不便敬请谅解，好利网祝您理财愉快！\r\n\r\n感谢您对我们的关注与支持。\r\n","status":3},{"send_time":"2017-07-04 10:58:49","id":12,"title":"优惠卷通知","type":1,"content":"用户185****5479您好，您有一张加息券将于明天2017-07-5到期，单笔投资满10000-100000可使用，累计投资满10000-100000可使用。","status":2},{"send_time":"2017-07-04 10:56:44","id":11,"title":"优惠卷通知","type":1,"content":"用户185****5479您好，您有一张加息券将于明天2017-07-5到期，单笔投资满1000-10000可使用，累计投资满1000-10000可使用。","status":1},{"send_time":"2017-07-04 10:54:07","id":10,"title":"五一劳动节假期安排公告","type":2,"content":"尊敬的185****5479；\r\n您好！\r\n五一放假期间，好利网客服服务时间调整为：2017年4月29日8:00-12:00，4月30日09:00-18:00，5月1日10:00-15:00，5月2日恢复正常工作时间（8:00-22:00），给您带来不便敬请谅解，好利网祝您理财愉快！\r\n\r\n感谢您对我们的关注与支持。\r\n","status":3},{"send_time":"2017-07-04 10:51:04","id":9,"title":"优惠卷通知","type":1,"content":"用户185****5479您好，您有一张加息券将于明天2017-07-5到期，单笔投资满5000-50000可使用，累计投资满5000-50000可使用。","status":1}]}}
     */

    private String code;
    private String msg;
    private ModelBeanX model;

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

    public ModelBeanX getModel() {
        return model;
    }

    public void setModel(ModelBeanX model) {
        this.model = model;
    }

    public static class ModelBeanX implements Serializable {
        /**
         * code : 200
         * msg : 成功
         * model : {"accountMessages":[{"send_time":"2017-07-04 11:06:26","id":16,"title":"五一劳动节假期安排公告","type":2,"content":"尊敬的185****5479；\r\n您好！\r\n五一放假期间，好利网客服服务时间调整为：2017年4月29日8:00-12:00，4月30日09:00-18:00，5月1日10:00-15:00，5月2日恢复正常工作时间（8:00-22:00），给您带来不便敬请谅解，好利网祝您理财愉快！\r\n\r\n感谢您对我们的关注与支持。\r\n","status":1},{"send_time":"2017-07-04 11:05:37","id":15,"title":"优惠卷通知","type":1,"content":"用户185****5479您好，您有一张加息券将于明天2017-07-5到期，单笔投资满1000-10000可使用，累计投资满1000-10000可使用。","status":2},{"send_time":"2017-07-04 11:03:58","id":14,"title":"优惠卷通知","type":1,"content":"用户185****5479您好，您有一张加息券将于明天2017-07-5到期，单笔投资满10000-100000可使用，累计投资满10000-100000可使用。","status":3},{"send_time":"2017-07-04 11:02:39","id":13,"title":"五一劳动节假期安排公告","type":2,"content":"尊敬的185****5479；\r\n您好！\r\n五一放假期间，好利网客服服务时间调整为：2017年4月29日8:00-12:00，4月30日09:00-18:00，5月1日10:00-15:00，5月2日恢复正常工作时间（8:00-22:00），给您带来不便敬请谅解，好利网祝您理财愉快！\r\n\r\n感谢您对我们的关注与支持。\r\n","status":3},{"send_time":"2017-07-04 10:58:49","id":12,"title":"优惠卷通知","type":1,"content":"用户185****5479您好，您有一张加息券将于明天2017-07-5到期，单笔投资满10000-100000可使用，累计投资满10000-100000可使用。","status":2},{"send_time":"2017-07-04 10:56:44","id":11,"title":"优惠卷通知","type":1,"content":"用户185****5479您好，您有一张加息券将于明天2017-07-5到期，单笔投资满1000-10000可使用，累计投资满1000-10000可使用。","status":1},{"send_time":"2017-07-04 10:54:07","id":10,"title":"五一劳动节假期安排公告","type":2,"content":"尊敬的185****5479；\r\n您好！\r\n五一放假期间，好利网客服服务时间调整为：2017年4月29日8:00-12:00，4月30日09:00-18:00，5月1日10:00-15:00，5月2日恢复正常工作时间（8:00-22:00），给您带来不便敬请谅解，好利网祝您理财愉快！\r\n\r\n感谢您对我们的关注与支持。\r\n","status":3},{"send_time":"2017-07-04 10:51:04","id":9,"title":"优惠卷通知","type":1,"content":"用户185****5479您好，您有一张加息券将于明天2017-07-5到期，单笔投资满5000-50000可使用，累计投资满5000-50000可使用。","status":1}]}
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

        public static class ModelBean implements Serializable {
            private List<AccountMessagesBean> accountMessages;

            public List<AccountMessagesBean> getAccountMessages() {
                return accountMessages;
            }

            public void setAccountMessages(List<AccountMessagesBean> accountMessages) {
                this.accountMessages = accountMessages;
            }

            public static class AccountMessagesBean implements Serializable {
                /**
                 * send_time : 2017-07-04 11:06:26
                 * id : 16
                 * title : 五一劳动节假期安排公告
                 * type : 2
                 * content : 尊敬的185****5479；
                 您好！
                 五一放假期间，好利网客服服务时间调整为：2017年4月29日8:00-12:00，4月30日09:00-18:00，5月1日10:00-15:00，5月2日恢复正常工作时间（8:00-22:00），给您带来不便敬请谅解，好利网祝您理财愉快！

                 感谢您对我们的关注与支持。

                 * status : 1
                 */

                private String send_time;
                private int id;
                private String title;
                private int type;
                private String content;
                private int status;

                public String getSend_time() {
                    return send_time;
                }

                public void setSend_time(String send_time) {
                    this.send_time = send_time;
                }

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

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }
            }
        }
    }
}
