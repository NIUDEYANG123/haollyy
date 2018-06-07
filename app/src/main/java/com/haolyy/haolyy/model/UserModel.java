

package com.haolyy.haolyy.model;


import com.haolyy.haolyy.base.BaseApplication;
import com.haolyy.haolyy.base.BaseBean;
import com.haolyy.haolyy.base.BaseModel;
import com.haolyy.haolyy.entity.BBaseBean;
import com.haolyy.haolyy.entity.IsUpdateBean;
import com.haolyy.haolyy.entity.RTokenBean;
import com.haolyy.haolyy.entity.TokenBean;
import com.haolyy.haolyy.entity.bank.BankListBean;
import com.haolyy.haolyy.entity.login.LoginBean;
import com.haolyy.haolyy.entity.login.PassWordBean;
import com.haolyy.haolyy.entity.login.RegsiterBean;
import com.haolyy.haolyy.entity.userinfo.GetUserStatusBean;
import com.haolyy.haolyy.service.UserApi;
import com.haolyy.haolyy.utils.AESUtils;
import com.haolyy.haolyy.utils.LogUtils;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;

import static com.haolyy.haolyy.base.BaseApplication.mUserName;


/**
 * Created by niudeyang on 2017/8/14.
 */

public class UserModel extends BaseModel {
    private UserApi userApi;
    private static UserModel userModel;

    private UserModel() {
        super();
        userApi = retrofit.create(UserApi.class);
    }

    public static UserModel getInstance() {
        if (userModel == null) {
            synchronized (UserModel.class) {
                if (userModel == null) {
                    userModel = new UserModel();
                }
            }
        }
        return userModel;
    }

    /**
     * 获取Token
     * @return
     */
    public Observable<TokenBean> getToken() {
        Map<String, String> map = new HashMap<>();
        return userApi.getToken(map);
    }

    /**
     * 刷新token
     * @param subscriber
     */
    public void refreshToken(Subscriber<RTokenBean> subscriber) {
        Map<String, String> map = new HashMap<>();
        map.put("refreshToken", BaseApplication.refreshToken);
        Observable<RTokenBean> observable = userApi.refreshToken(map);
        toSubscribe(observable, subscriber);
    }

    /**
     * 短信验证码
     * @param phoneNum
     * @param token
     * @param operationType register(注册),forget(忘记密码)
     * @param imageCode
     * @return
     */
    public Observable<BBaseBean> getSmsCode(String phoneNum, String token, String operationType, String imageCode) {
        Map<String, String> map = new HashMap<>();
        map.put("phoneNum", phoneNum);
        map.put("token", token);
        map.put("operationType", operationType);
        map.put("imageCode", imageCode);
        //map.put("platform",platform);
        // map.put("client", client);
        map.put("smsTemplateCode", "100"); //短信模板:100  vercode
        return userApi.getSmsCode(map);
    }

    /**
     * 注册
     * @param phoneNum
     * @param passWord
     * @param imageCode
     * @param SmsCode
     * @param token
     * @return
     */
    public Observable<RegsiterBean> register(String phoneNum, String passWord, String imageCode, String SmsCode, String token,String inviteCode,String bdUrl) {
        Map<String, String> map = new HashMap<>();
        map.put("phoneNum", phoneNum);
        map.put("passWord", AESUtils.encrypt(passWord));
        // map.put("client", client);
        map.put("imageCode", imageCode);
        map.put("SmsCode", SmsCode);
        // map.put("platform", platform);
        map.put("token", token);
        map.put("inviteCode", inviteCode);
        map.put("bdUrl", bdUrl);
        return userApi.register(map);
    }
    /**
     * 用户是否注册
     * @param phoneNum
     * @return
     */
    public Observable<BaseBean> isRegister(String phoneNum) {
        Map<String, String> map = new HashMap<>();
        map.put("mobile", phoneNum);
        return userApi.isRegister(map);
    }
    /**
     * 忘记密码
     * @param phoneNum
     * @param passWord
     * @param imageCode
     * @param SmsCode
     * @param token
     * @return
     */
    public Observable<PassWordBean> forgetPassWord(String phoneNum, String passWord, String imageCode, String SmsCode, String token) {
        Map<String, String> map = new HashMap<>();
        map.put("phoneNum", phoneNum);
        map.put("passWord", AESUtils.encrypt(passWord));
        // map.put("client", client);
        map.put("imageCode", imageCode);
        map.put("SmsCode", SmsCode);
        // map.put("platform", platform);
        map.put("token", token);
        return userApi.forgetPassWord(map);
    }

    /**
     * 登录
     * @param phoneNum
     * @param passWord
     * @return
     */

    public Observable<LoginBean> login(String phoneNum, String passWord) {
        Map<String, String> map = new HashMap<>();
        LogUtils.e("密码："+passWord);
        map.put("phoneNum", phoneNum);
        map.put("passWord", AESUtils.encrypt(passWord));
        //map.put("passWord", passWord);
        LogUtils.e("密码加密："+ AESUtils.encrypt(passWord));
        //map.put("client", client);
        //map.put("platform",platform);
        //转json字符串
//        Gson gson=new Gson();
//        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=UTF-8"), gson.toJson(map));
//        return userApi.login(body);
        return userApi.login(map);
    }

    /**
     * 用户状态（在登录状态下查询）
     * @return
     */
    public Observable<GetUserStatusBean> getUserStatus() {
        Map<String, String> map = new HashMap<>();
        map.put("phoneNum", mUserName);
        //map.put("client", client);
        return userApi.getUserStatus(map);
    }

    /**
     * 退出登录
     * @return
     */
    public Observable<BBaseBean> logout() {
        Map<String, String> map = new HashMap<>();
        map.put("phoneNum", mUserName);
        //map.put("client", client);
        return userApi.logout(map);
    }

    /**
     * 修改登录密码
     * @param oldPassword
     * @param newPassword
     * @param newPasswordConfirm
     * @return
     */
    public Observable<BaseBean> changePass(String oldPassword, String newPassword, String newPasswordConfirm) {
        Map<String, String> map = new HashMap<>();
        map.put("phoneNum", mUserName);
        map.put("oldPassword", AESUtils.encrypt(oldPassword));
        map.put("newPassword", AESUtils.encrypt(newPassword));
        map.put("newPasswordConfirm", AESUtils.encrypt(newPasswordConfirm));
        return userApi.changePass(map);
    }

    /**
     * 银行卡列表
     *
     * @return
     * @busiType 业务场景（1、开户，2、换绑卡）
     */
    public Observable<BankListBean> getBanks(String busiType) {
        Map<String, String> map = new HashMap<>();
        //app只有快捷支付，全部传1
        map.put("gateBusiId", "1");
        return userApi.getBanks(map);
    }

    /**
     * 检测更新
     * @param subscriber
     */
    public void isUpdate(Subscriber<IsUpdateBean> subscriber) {
        Map<String, String> map = new HashMap<>();
        Observable<IsUpdateBean> observable = userApi.isUpdate(map);
        toSubscribe(observable, subscriber);
    }

    public void changeMobile(){

    }

    public Observable<BBaseBean> validSmsCode(String imageCode, String smsCode,String token){
        Map<String, String> map = new HashMap<>();
        map.put("phoneNum",mUserName);
        map.put("token",token);
        map.put("operationType","update");
        map.put("imageCode",imageCode);
        map.put("smsCode",smsCode);
        return userApi.validateSmsCode(map);
    }

    public Observable<BBaseBean> changeMobile(String imageCode, String smsCode,String token,String phoneNum){
        Map<String, String> map = new HashMap<>();
        map.put("oldPhoneNum",mUserName);
        map.put("phoneNum",phoneNum);
        map.put("token",token);
        map.put("operationType","update");
        map.put("imageCode",imageCode);
        map.put("smsCode",smsCode);
        return userApi.changeMobile(map);
    }

}

