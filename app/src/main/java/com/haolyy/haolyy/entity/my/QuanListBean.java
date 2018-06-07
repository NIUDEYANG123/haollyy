package com.haolyy.haolyy.entity.my;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by niudeyang on 2017/9/5.
 */

public class QuanListBean implements Parcelable {

    private DataBean data;
    private String appcode;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getAppcode() {
        return appcode;
    }

    public void setAppcode(String appcode) {
        this.appcode = appcode;
    }



    public static class DataBean implements Parcelable {
        private String expire;
        private String unUse;
        private String used;
        private ArrayList<CouponListBean> couponList;

        public String getExpire() {
            return expire;
        }

        public void setExpire(String expire) {
            this.expire = expire;
        }

        public String getUnUse() {
            return unUse;
        }

        public void setUnUse(String unUse) {
            this.unUse = unUse;
        }

        public String getUsed() {
            return used;
        }

        public void setUsed(String used) {
            this.used = used;
        }

        public ArrayList<CouponListBean> getCouponList() {
            return couponList;
        }

        public void setCouponList(ArrayList<CouponListBean> couponList) {
            this.couponList = couponList;
        }

        public static class CouponListBean implements Parcelable {
            /**
             * amount : 500.0
             * end_date : 2017-09-30 00:00:00
             * coupon_rule_code : 10
             * create_date : 2017-08-28 00:00:00
             * use_status : 0
             * owner_id : 12
             * use_product : 赢计划A,赢计划B,赢计划C,赢计划D
             * coupon_type : 2
             * coupon_code : HL1503890001366
             * rule_details : 投资金额达标，投资成功一次性返现到账户余额
             * coupon_name : 500元返现券
             * fee : 3.0
             * max_day : 9999
             * profit : 7.0
             */

            private double amount;
            private String end_date;
            private int coupon_rule_code;
            private String create_date;
            private String use_status;
            private int owner_id;
            private String use_product;
            private String coupon_type;//1 加息 2返现 3体现
            private String coupon_code;
            private String rule_details;
            private String coupon_name;
            private double fee;
            private int max_day;
            private double profit;

            public double getAmount() {
                return amount;
            }

            public void setAmount(double amount) {
                this.amount = amount;
            }

            public String getEnd_date() {
                return end_date;
            }

            public void setEnd_date(String end_date) {
                this.end_date = end_date;
            }

            public int getCoupon_rule_code() {
                return coupon_rule_code;
            }

            public void setCoupon_rule_code(int coupon_rule_code) {
                this.coupon_rule_code = coupon_rule_code;
            }

            public String getCreate_date() {
                return create_date;
            }

            public void setCreate_date(String create_date) {
                this.create_date = create_date;
            }

            public String getUse_status() {
                return use_status;
            }

            public void setUse_status(String use_status) {
                this.use_status = use_status;
            }

            public int getOwner_id() {
                return owner_id;
            }

            public void setOwner_id(int owner_id) {
                this.owner_id = owner_id;
            }

            public String getUse_product() {
                return use_product;
            }

            public void setUse_product(String use_product) {
                this.use_product = use_product;
            }

            public String getCoupon_type() {
                return coupon_type;
            }

            public void setCoupon_type(String coupon_type) {
                this.coupon_type = coupon_type;
            }

            public String getCoupon_code() {
                return coupon_code;
            }

            public void setCoupon_code(String coupon_code) {
                this.coupon_code = coupon_code;
            }

            public String getRule_details() {
                return rule_details;
            }

            public void setRule_details(String rule_details) {
                this.rule_details = rule_details;
            }

            public String getCoupon_name() {
                return coupon_name;
            }

            public void setCoupon_name(String coupon_name) {
                this.coupon_name = coupon_name;
            }

            public double getFee() {
                return fee;
            }

            public void setFee(double fee) {
                this.fee = fee;
            }

            public int getMax_day() {
                return max_day;
            }

            public void setMax_day(int max_day) {
                this.max_day = max_day;
            }

            public double getProfit() {
                return profit;
            }

            public void setProfit(double profit) {
                this.profit = profit;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeDouble(this.amount);
                dest.writeString(this.end_date);
                dest.writeInt(this.coupon_rule_code);
                dest.writeString(this.create_date);
                dest.writeString(this.use_status);
                dest.writeInt(this.owner_id);
                dest.writeString(this.use_product);
                dest.writeString(this.coupon_type);
                dest.writeString(this.coupon_code);
                dest.writeString(this.rule_details);
                dest.writeString(this.coupon_name);
                dest.writeDouble(this.fee);
                dest.writeInt(this.max_day);
                dest.writeDouble(this.profit);
            }

            public CouponListBean() {
            }

            protected CouponListBean(Parcel in) {
                this.amount = in.readDouble();
                this.end_date = in.readString();
                this.coupon_rule_code = in.readInt();
                this.create_date = in.readString();
                this.use_status = in.readString();
                this.owner_id = in.readInt();
                this.use_product = in.readString();
                this.coupon_type = in.readString();
                this.coupon_code = in.readString();
                this.rule_details = in.readString();
                this.coupon_name = in.readString();
                this.fee = in.readDouble();
                this.max_day = in.readInt();
                this.profit = in.readDouble();
            }

            public static final Creator<CouponListBean> CREATOR = new Creator<CouponListBean>() {
                @Override
                public CouponListBean createFromParcel(Parcel source) {
                    return new CouponListBean(source);
                }

                @Override
                public CouponListBean[] newArray(int size) {
                    return new CouponListBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.expire);
            dest.writeString(this.unUse);
            dest.writeString(this.used);
            dest.writeTypedList(this.couponList);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.expire = in.readString();
            this.unUse = in.readString();
            this.used = in.readString();
            this.couponList = in.createTypedArrayList(CouponListBean.CREATOR);
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.data, flags);
        dest.writeString(this.appcode);
    }

    public QuanListBean() {
    }

    protected QuanListBean(Parcel in) {
        this.data = in.readParcelable(DataBean.class.getClassLoader());
        this.appcode = in.readString();
    }

    public static final Parcelable.Creator<QuanListBean> CREATOR = new Parcelable.Creator<QuanListBean>() {
        @Override
        public QuanListBean createFromParcel(Parcel source) {
            return new QuanListBean(source);
        }

        @Override
        public QuanListBean[] newArray(int size) {
            return new QuanListBean[size];
        }
    };
}
