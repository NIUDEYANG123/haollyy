package com.haolyy.haolyy.config;

/**
 * Created by niudeyang on 2017/4/26.
 */

public interface
Config {
    String AppKey = "59f147248f4a9d52d2000080";
    String Message_secret = "acaf2e03faa70be074e5e803dcfa01f1";
    String app_master_secret = "twqzcqsmmpgxfnpybognt9nqmzhphblv";
    String TIP_ALL = "您所输入的账号或密码有误，请重新输入";
    String TIP_MOBILE = "手机号码格式不正确";
    String TIP_IMAGE = "图片验证码错误";
    String TIP_SMS = "短信验证码错误";
    String TIP_PASSS = "密码格式不正确";
    String TIP_PASSS2 = "密码不一致";
    String TIP_INVITE = "邀请码格式错误";
    String TIP_NET_ERROR = "网络异常";
    String tip_lock4 = "手势密码至少绘制四个点";
    String tip_lock2 = "两次绘制不一致，请重新绘制";
    String tip_lock = "请连续绘制解锁图案";

    String success = "success";// 接口成功

    String service_phone ="400-999-6780" ;//"400-088-0888"卓头
    String permisson_service_phone = "tel:4009996780";//"tel:4000880888";
    int seconds = 60000;//倒计时时
    String platform = "HLW";//
    String mer_id_ = "1";//商户号 1卓投 2中赢网
    String from_ = "3";//获客来源1 急借通2 发薪贷 3 卓投
    String client = "4";//安卓
    String returl = "https://www.haolyy.com";
    String PageType = "1";//app风格无标题
    int status_with_draw = 9001;
    int staus_recharge = 9002;
    int status_rebind = 9003;
    String sms_seq = "AAAAAAAA";

    /**
     * 获取短信验证码 operationType 短信验证码用途:1注册(register) 2修改密码(forget) 3充值(recharge) 4投资(investment) 5修改用户名(changeUserName) image_code 图形验证码
     */
    String SMS_OPERATION_TYPE_REG = "register";
    String SMS_OPERATION_TYPE_FOR = "forget";
    String SMS_OPERATION_TYPE_REC = "3";
    String SMS_OPERATION_TYPE_INV = "4";
    String SMS_OPERATION_TYPE_MOD = "5";
    String SMS_OPERATION_TYPE_ACTIVATE = "bosAcctActivate";
    String SMS_TEMPLATE_CODE_HOLYY = "100";//好利网100
    String LoginOUT = "loginOut";


}
