package com.haolyy.haolyy.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Base64;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.haolyy.haolyy.MainActivity;
import com.haolyy.haolyy.R;
import com.haolyy.haolyy.base.BaseApplication;
import com.haolyy.haolyy.entity.IsUpdateBean;
import com.haolyy.haolyy.swipe.ListBaseAdapter;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import rx.functions.Action1;

/**
 * 公共帮助类
 *
 * @author Kevin
 */
public class CommonUtils {

    /**
     * 根Activity，用于退出应用
     */
    public static Activity activity = null;

    /**
     * 使用Activity切换动画
     */
    public static boolean useSwitchAnimation = false;

    /**
     * 公用加载数据等待框
     */
    public static Dialog dialogForWaiting;

    public static ProgressDialog progressDialog;

    /**
     * AES密钥
     */
    private final static String secretKey = "abCd7890&*@#REZ8RETaaaf!@#$%<>83";

    private static long lastClickTime;

    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 800) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    public static int dp2px(Context context, float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                context.getResources().getDisplayMetrics());
    }

    public static int sp2px(Context context, float sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,
                context.getResources().getDisplayMetrics());
    }

    public static long dataOne(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        Date date;
        String times = null;
        try {
            date = sdr.parse(time);
            long l = date.getTime();
            //String stf = String.valueOf(l);
            //times = stf.substring(0, 10);
            return l;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取当前格式化的时间
     *
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String getCurrentFormatTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(new Date());
    }

    /**
     * yyyy-MM-dd
     *
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String getCurrentFormatDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(new Date());
    }

    /**
     * 字符串转换成日期
     *
     * @param str
     * @return date
     */
    @SuppressLint("SimpleDateFormat")
    public static Date StrToDate(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 字符串转换成日期
     *
     * @param str
     * @return date
     */
    @SuppressLint("SimpleDateFormat")
    public static Date StrToDateDay(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // long lt = new Long(s) * 1000;
        // Date date = new Date(lt);
        res = simpleDateFormat.format(new Date(Long.parseLong(s) * 1000));
        return res;
    }


    /**
     * 停止等待的自动圆圈圈 暂无实现
     */
    public static void closeWaitingDialog() {
        if (dialogForWaiting != null) {
            dialogForWaiting.dismiss();
            dialogForWaiting = null;
        }
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    /**
     * 获取大图片缩略图的特殊算法
     *
     * @param options
     * @param minSideLength
     * @param maxNumOfPixels
     * @return
     */
    public static int computeSampleSize(BitmapFactory.Options options, int minSideLength, int maxNumOfPixels) {
        int initialSize = computeInitialSampleSize(options, minSideLength, maxNumOfPixels);
        int roundedSize;
        if (initialSize <= 8) {
            roundedSize = 1;
            while (roundedSize < initialSize) {
                roundedSize <<= 1;
            }
        } else {
            roundedSize = (initialSize + 7) / 8 * 8;
        }

        return roundedSize;
    }

    /**
     * 获取大图片缩略图的特殊算法(第二部分)
     */
    public static int computeInitialSampleSize(BitmapFactory.Options options, int minSideLength, int maxNumOfPixels) {
        double w = options.outWidth;
        double h = options.outHeight;
        int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math.sqrt(w * h / maxNumOfPixels));
        int upperBound = (minSideLength == -1) ? 128
                : (int) Math.min(Math.floor(w / minSideLength), Math.floor(h / minSideLength));
        if (upperBound < lowerBound) {
            return lowerBound;
        }

        if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
            return 1;
        } else if (minSideLength == -1) {
            return lowerBound;
        } else {
            return upperBound;
        }
    }

    /**
     * md5加密
     *
     * @param string
     * @return
     */
    public static String md5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10)
                hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }

    /**
     * 格式化电话号码（显示前3后4中间用*代替）
     *
     * @param tel
     * @return
     */
    public static String FormatTel(String tel) {
        if (tel != null && tel.length() == 11) {
            return tel.substring(0, 3) + "****" + tel.substring(7, 11);
        } else {
            return "格式错误";
        }
    }

    /**
     * 这个地方大家别搞混了，为了方便小马把两个贴一起了，使用的时候记得分开使用 以最省内存的方式读取本地资源的图片
     */
    public static Bitmap readBitMap(Context context, int resId) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;

        // 获取资源图片
        InputStream is = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is, null, opt);
    }

    /**
     * 通用判断
     *
     * @param telNum
     * @return
     */
    public static boolean isMobiPhoneNum(String telNum) {
        String regex = "^((13[0-9])|(14[0-9])|(15[0-9])|(18[0-9])|(17[0-9]))\\d{8}$";
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(telNum);
        return m.matches();
    }

    public static boolean hasDandC(String str) {
        String reg = "^[a-zA-Z0-9]{0,20}$";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(str);
        boolean b = matcher.matches();
        return b;
    }

    /**
     * 校验身份证
     *
     * @param idCard
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isIDCard(String idCard) {
        String regex_card = "(^\\d{15}$)|(^\\d{17}([0-9]|X)$)";
        return Pattern.matches(regex_card, idCard);
    }

    /**
     * 全英文
     *
     * @param telNum
     * @return
     */
    public static boolean isEnglishPwd(String telNum) {
        String regex = "^[a-z]*|[A-Z]*$";
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(telNum);
        return m.matches();
    }

    /**
     * 全数字
     *
     * @param telNum
     * @return
     */
    public static boolean isNumberPwd(String telNum) {
        String regex = "^[0-9]*$";
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(telNum);
        return m.matches();
    }


    /**
     * 解密
     *
     * @param content 待解密内容
     * @return
     */
    public static byte[] decrypt(byte[] content) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "Crypto");
            sr.setSeed(secretKey.getBytes());
            kgen.init(128, sr);
            SecretKey secretKey = kgen.generateKey();

            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(content);
            return result; // 加密
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 月份数字转英文
     *
     * @param month
     * @return
     */
    public static String englishMonth(int month) {
        String cur_month = "";
        if (month == 0) {// 依据值判断月份(日历获取的的月份为0时是1月)
            cur_month = "January.";
        } else if (month == 1) {
            cur_month = "February.";
        } else if (month == 2) {
            cur_month = "March.";
        } else if (month == 3) {
            cur_month = "April.";
        } else if (month == 4) {
            cur_month = "May.";
        } else if (month == 5) {
            cur_month = "June.";
        } else if (month == 6) {
            cur_month = "July.";
        } else if (month == 7) {
            cur_month = "August.";
        } else if (month == 8) {
            cur_month = "September.";
        } else if (month == 9) {
            cur_month = "October.";
        } else if (month == 10) {
            cur_month = "November.";
        } else if (month == 11) {
            cur_month = "December.";
        }
        return cur_month;
    }

    /**
     * 对double数据进行取精度, 保留2位小数, 精度取值方式 BigDecimal.ROUND_HALF_UP(四舍五入)
     *
     * @param value double数据
     * @return 精度计算后的数据
     */
    public static double round(double value) {
        return round(value, 2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 对double数据进行取精度, 保留1位小数, 精度取值方式 BigDecimal.ROUND_HALF_UP(四舍五入)
     *
     * @param value double数据
     * @return 精度计算后的数据
     */
    public static double roundDecimal(double value) {
        return round(value, 1, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 对double数据进行取精度
     *
     * @param value        double数据
     * @param scale        精度位数(保留的小数位数)
     * @param roundingMode 精度取值方式
     * @return 精度计算后的数据
     */
    public static double round(double value, int scale, int roundingMode) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(scale, roundingMode);
        double d_value = bd.doubleValue();
        bd = null;
        return d_value;
    }

    /**
     * 取两位有效数字并格式化金额
     *
     * @param value
     * @returnb
     */
    public static String roundFormatAmt(double value) {
        return roundFormatAmt(value, 2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 格式化金额
     *
     * @param amt
     * @return
     */
    public static String formatAmt(Object amt) {
        try {
            if (Double.parseDouble(amt.toString()) < 1.00 && Double.parseDouble(amt.toString()) > 0) {
                return amt.toString();
            }
            BigDecimal bd = new BigDecimal(amt.toString());
            // bd.setScale(2);
            DecimalFormat myformat = new DecimalFormat();
            myformat.applyPattern("##,###.00");
            String formated = myformat.format(bd.doubleValue());
            return formated.equals(".00") ? "0.00" : formated;
        } catch (Exception e) {
            e.printStackTrace();
            return "-.--";
        }
    }

    /**
     * @param value
     * @param scale
     * @param roundingMode
     * @return
     */
    public static String roundFormatAmt(double value, int scale, int roundingMode) {
        // 取小数点后2位有效数字
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(scale, roundingMode);
        double d_value = bd.doubleValue();
        bd = null;
        // 格式化金额
        DecimalFormat df = new DecimalFormat();
        df.applyPattern("##,###.00");
        String str_value = "";
        if (d_value < 1) {
            str_value = "0" + df.format(d_value);
        } else {
            str_value = df.format(d_value);
        }
        return str_value.equals(".00") ? "0.00" : str_value;
    }

    /**
     * 抽取报错提示方法
     *
     * @param errorMessage
     */
    public static void showErrorMessageToast(Context mContext, String errorMessage) {
        try {
            if (!TextUtils.isEmpty(errorMessage)) {
                if (errorMessage.contains("非法请求")) {
                    Toast.makeText(mContext, errorMessage.substring(5), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(mContext, errorMessage, Toast.LENGTH_LONG).show();
                }
            } else {
                errorMessage = "未知错误";
                Toast.makeText(mContext, errorMessage, Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化配置
     *
     * @param listBaseAdapter
     */
    public static LRecyclerViewAdapter initLrecycleView(ListBaseAdapter listBaseAdapter, Context mContext, LRecyclerView recycleAsset) {
        LRecyclerViewAdapter lRecyclerViewAdapter = new LRecyclerViewAdapter(listBaseAdapter);
        //recycleAsset.setRefreshHeader(new ArrowRefreshHeader2(mContext));//必须在setadapter之前调用
        recycleAsset.setAdapter(lRecyclerViewAdapter);
        recycleAsset.setLayoutManager(new LinearLayoutManager(mContext));
        //设置头部加载颜色
        //recycleAsset.setHeaderViewColor(R.color.colorAccent, R.color.dark ,android.R.color.white);
        //设置底部加载颜色
        recycleAsset.setFooterViewColor(R.color.bg_FE7537, R.color.text_FE9337, android.R.color.white);
        //设置底部加载文字提示
        recycleAsset.setFooterViewHint("拼命加载中", "已经全部为你呈现了", "网络不给力啊，点击再试一次吧");

        return lRecyclerViewAdapter;
    }

    /**
     * 列表页空数据emptyview
     *
     * @param context
     * @param mRecyclerView
     * @param text
     * @param reid
     * @return
     */
    public static View getEmptyView(Activity context, RecyclerView mRecyclerView, String text, int reid) {
        TextView tv;
        ImageView iv;
        View notDataView = context.getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) mRecyclerView.getParent(), false);
        tv = (TextView) notDataView.findViewById(R.id.tv_empty);
        iv = (ImageView) notDataView.findViewById(R.id.iv_empty);
        tv.setText(text);
        if (reid == -1) {
            iv.setVisibility(View.INVISIBLE);
        } else {
            iv.setImageResource(reid);
        }
        return notDataView;
    }

    public static void showBankLogo(String code,TextView tvname, String cardId,TextView tv){
        int length = cardId.length();
        switch (code){
            case "ABC":
                tvname.setText("农业银行");
                //tv.setText(cardId.substring(length-4,length));
                break;
            case "CCB":
                tvname.setText("建设银行");
                //tv.setText(cardId.substring(length-4,length));
                break;
            case "BOC":
                tvname.setText("中国银行");
                //tv.setText(cardId.substring(length-4,length));
                break;
            case "CEB":
                tvname.setText("光大银行");
                //tv.setText(cardId.substring(length-4,length));
                break;
            case "CIB":
                tvname.setText("兴业银行");
                //tv.setText(cardId.substring(length-4,length));
                break;
            case "PINGAN":
                tvname.setText("平安银行");
               // tv.setText(cardId.substring(length-4,length));
                break;
            case "PSBC":
                tvname.setText("中国邮政储蓄银行");
                //tv.setText(cardId.substring(length-4,length));
                break;
            case "SPDB":
                tvname.setText("浦发银行");
                //tv.setText(cardId.substring(length-4,length));
                break;
            case "ICBC":
                tvname.setText("工商银行");
                //tv.setText(cardId.substring(length-4,length));
                break;
            case "CMBC":
                tvname.setText("民生银行");
                //tv.setText(cardId.substring(length-4,length));
                break;
            case "BOCOM":
                tvname.setText("交通银行");
                //tv.setText(cardId.substring(length-4,length));
                break;
            case "GDB":
                tvname.setText("农业银行");
                //tv.setText(cardId.substring(length-4,length));
                break;
            case "HXB":
                tvname.setText("华夏银行");
                //tv.setText(cardId.substring(length-4,length));
                break;
            case "CMB":
                tvname.setText("招商银行");
                //tv.setText(cardId.substring(length-4,length));
                break;
            case"BCCB":
                tvname.setText("北京银行");
                //tv.setText(cardId.substring(length-4,length));
                break;
            case "BJRCB":
                tvname.setText("北京农村商业银行");
                //tv.setText(cardId.substring(length-4,length));
                break;
            case "BOS":
                tvname.setText("上海银行");
                //tv.setText(cardId.substring(length-4,length));
                break;
            case"CBHB":
                tvname.setText("渤海银行");
                //tv.setText(cardId.substring(length-4,length));
                break;
            case"CITIC":
                tvname.setText("中信银行");
                tv.setText(cardId.substring(length-4,length));
                break;
            case "CZB":
                tvname.setText("浙商银行");
                //tv.setText(cardId.substring(length-4,length));
                break;
            case "HKBEA":
                tvname.setText("东亚银行");
                //tv.setText(cardId.substring(length-4,length));
                break;
            case "HZCB":
                tvname.setText("杭州银行");
                //tv.setText(cardId.substring(length-4,length));
                break;
            case "NJCB":
                tvname.setText("南京银行");
                //tv.setText(cardId.substring(length-4,length));
                break;
            case "SDB":
                tvname.setText("深发银行");
               // tv.setText(cardId.substring(length-4,length));
                break;
            case "SRCB":
                tvname.setText("上海农村商业银行");
                //tv.setText(cardId.substring(length-4,length));
                break;
        }
    }
   ////3.待售 4、开始募集（募集中） 5、已满标（募集完成） 7、计息中（还款中） 9、已结清（还款完成）12推出中
    public static String getBorrowStatus(int p0) {
        String s="募集中";
        switch (p0){
            case 3:
                s="待售";
                break;
            case 4:
                s="募集中";
                break;
            case 5:
                s="已告罄";
                break;
            case 7:
                s="收益中";
                break;
            case 9:
                s="已结清";
                break;
            case 12:
                s="退出中";
                break;
        }
        return s;
    }

    public static String secretId(String idCard) {
        String substring = idCard.substring(0, 6);
        String substring1 = idCard.substring(idCard.length() - 4, idCard.length());

        return substring+"********"+substring1;

    }

    public static void updateData(IsUpdateBean baseBean,Activity mContext,AlertDialog.Builder dialog,AlertDialog dia) {
        dialog = new AlertDialog.Builder(mContext);
        dialog.setMessage(baseBean.model.updateDesc);
        dialog.setCancelable(false);
        if ("0".equals(baseBean.model.verStatus)) {
            if (!BaseApplication.noUpdate || !BaseApplication.noIsApp) {
                BaseApplication.noIsApp = true;
                AlertDialog.Builder finalDialog = dialog;
                dialog.setPositiveButton("更新", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //下载需要写SD卡权限, targetSdkVersion>=23 需要动态申请权限
                        new RxPermissions(mContext)
                                // 申请权限
                                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                .subscribe(new Action1<Boolean>() {
                                    @Override
                                    public void call(Boolean granted) {
                                        if (granted) {
                                            // 请求成功，开启服务，下载文件
                                            Intent intent = new Intent(mContext, UpdateService.class);
                                            intent.putExtra("url", baseBean.model.downloadUrl);
                                            mContext.startService(intent);
                                        } else {
                                            // 用户拒绝权限
                                            UIUtils.showToastCommon(mContext, "获取文件权限失败");
                                            finalDialog.show();
                                        }
                                    }
                                });
                    }
                });
                //               if ("0".equals(baseBean.model.verStatus)) {
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // 非强制更新，进入主页
                        BaseApplication.noUpdate = true;
                    }
                });
                //               }
                dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                    @Override
                    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                        if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_BACK) {
                            mContext.finish();
                        }
                        return false;
                    }
                });
                dia = dialog.show();
            }
        } else if ("1".equals(baseBean.model.verStatus)) {
            AlertDialog.Builder finalDialog1 = dialog;
            dialog.setPositiveButton("更新", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //下载需要写SD卡权限, targetSdkVersion>=23 需要动态申请权限
                    new RxPermissions(mContext)
                            // 申请权限
                            .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            .subscribe(new Action1<Boolean>() {
                                @Override
                                public void call(Boolean granted) {
                                    if (granted) {
                                        // 请求成功，开启服务，下载文件
                                        Intent intent = new Intent(mContext, UpdateService.class);
                                        intent.putExtra("url", baseBean.model.downloadUrl);
                                        mContext.startService(intent);
                                    } else {
                                        // 用户拒绝权限
                                        UIUtils.showToastCommon(mContext, "获取文件权限失败");
                                        finalDialog1.show();
                                    }
                                }
                            });
                }
            });
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                    if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_BACK) {
                        mContext.finish();
                    }
                    return false;
                }
            });
            dia = dialog.show();
        } else if ("2".equals(baseBean.model.verStatus)) {
            dia = dialog.show();
        }

    }
}
