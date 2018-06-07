package com.haolyy.haolyy.entity.product;

import java.util.List;

/**
 * Created by liliang on 2017/9/15.
 */

public class HomeBannerBean {


    /**
     * code : success
     * msg : 成功
     * model : {"homestatic":{"income":2265958.64,"amount":3722735.96,"registerCount":194},"banner":[{"imageUrl":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505811103772&di=551be3987492c0a90bdad11f2dc61c79&imgtype=0&src=http%3A%2F%2Fpic.hsw.cn%2F0%2F12%2F25%2F40%2F12254064_630448.jpg","linkUrl":"http://www.baidu.com","id":3,"sort":8}],"article":{"announcementList":[{"imageCoverUrl":"http://192.168.6.93/uploads/20170926/59c9cd33dfae3.jpg","createTime":1504079301000,"homePageCoverUrl":"http://192.168.6.93/uploads/20170926/59c9cd3f34b08.jpg","description":"1儿童沟通沟通沟通沟通沟通沟通沟通沟通沟通沟通沟通沟通沟通个人","id":1,"title":"111","url":"11","content":"1台34认为我无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无"}],"eventList":[{"imageCoverUrl":"http://192.168.6.93/uploads/20170926/59c9cd7a7d206.jpg","createTime":1506397575000,"homePageCoverUrl":"http://192.168.6.93/uploads/20170926/59c9cd7e61495.jpg","description":"2323232323232323232323东方时尚所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所","id":32,"title":"android平太阳","url":"http://www.baidu.com","content":"233333333333333333333333333333速度vsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsd&nbsp;"}]}}
     */

    public String code;
    public String msg;
    public ModelBean model;

    public static class ModelBean {
        /**
         * homestatic : {"income":2265958.64,"amount":3722735.96,"registerCount":194}
         * banner : [{"imageUrl":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505811103772&di=551be3987492c0a90bdad11f2dc61c79&imgtype=0&src=http%3A%2F%2Fpic.hsw.cn%2F0%2F12%2F25%2F40%2F12254064_630448.jpg","linkUrl":"http://www.baidu.com","id":3,"sort":8}]
         * article : {"announcementList":[{"imageCoverUrl":"http://192.168.6.93/uploads/20170926/59c9cd33dfae3.jpg","createTime":1504079301000,"homePageCoverUrl":"http://192.168.6.93/uploads/20170926/59c9cd3f34b08.jpg","description":"1儿童沟通沟通沟通沟通沟通沟通沟通沟通沟通沟通沟通沟通沟通个人","id":1,"title":"111","url":"11","content":"1台34认为我无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无"}],"eventList":[{"imageCoverUrl":"http://192.168.6.93/uploads/20170926/59c9cd7a7d206.jpg","createTime":1506397575000,"homePageCoverUrl":"http://192.168.6.93/uploads/20170926/59c9cd7e61495.jpg","description":"2323232323232323232323东方时尚所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所","id":32,"title":"android平太阳","url":"http://www.baidu.com","content":"233333333333333333333333333333速度vsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsd&nbsp;"}]}
         */

        public HomestaticBean homestatic;
        public ArticleBean article;
        public List<BannerBean> banner;

        public static class HomestaticBean {
            /**
             * income : 2265958.64
             * amount : 3722735.96
             * registerCount : 194
             */

            public String income;
            public String amount;
            public String registerCount;
        }

        public static class ArticleBean {
            public List<AnnouncementListBean> announcementList;
            public List<EventListBean> eventList;

            public static class AnnouncementListBean {
                /**
                 * imageCoverUrl : http://192.168.6.93/uploads/20170926/59c9cd33dfae3.jpg
                 * createTime : 1504079301000
                 * homePageCoverUrl : http://192.168.6.93/uploads/20170926/59c9cd3f34b08.jpg
                 * description : 1儿童沟通沟通沟通沟通沟通沟通沟通沟通沟通沟通沟通沟通沟通个人
                 * id : 1
                 * title : 111
                 * url : 11
                 * content : 1台34认为我无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无
                 */

                public String imageCoverUrl;
                public String createTime;
                public String homePageCoverUrl;
                public String description;
                public String id;
                public String title;
                public String url;
                public String content;

            }

            public static class EventListBean {
                /**
                 * imageCoverUrl : http://192.168.6.93/uploads/20170926/59c9cd7a7d206.jpg
                 * createTime : 1506397575000
                 * homePageCoverUrl : http://192.168.6.93/uploads/20170926/59c9cd7e61495.jpg
                 * description : 2323232323232323232323东方时尚所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所
                 * id : 32
                 * title : android平太阳
                 * url : http://www.baidu.com
                 * content : 233333333333333333333333333333速度vsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsd&nbsp;
                 */

                public String imageCoverUrl;
                public String createTime;
                public String homePageCoverUrl;
                public String description;
                public String id;
                public String title;
                public String url;
                public String content;

            }
        }

        public static class BannerBean {
            /**
             * imageUrl : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505811103772&di=551be3987492c0a90bdad11f2dc61c79&imgtype=0&src=http%3A%2F%2Fpic.hsw.cn%2F0%2F12%2F25%2F40%2F12254064_630448.jpg
             * linkUrl : http://www.baidu.com
             * id : 3
             * sort : 8
             */

            public String imageUrl;
            public String linkUrl;
            public String id;
            public String sort;

        }
    }
}
