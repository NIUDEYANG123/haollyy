package com.haolyy.haolyy.utils;

import android.text.TextUtils;

import java.text.DecimalFormat;

import static com.haolyy.haolyy.utils.AccountUtil.DoubleToString;


/**
 * Created by liliang on 2018/1/4.
 */

public class StringUtils {

    /**
     * 去掉小数点后的0
     *
     * @param s
     * @return
     */
    public static String subZeroAndDot(String s) {
        if (!TextUtils.isEmpty(s)) {
            if (s.indexOf(".") > 0) {
                s = s.replaceAll("0+?$", "");//去掉多余的0
                s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
            }
        }
        return s;
    }

    /**
     * 保留小数点后两位
     *
     * @param num
     * @return
     */
    public static String StringToString(String num) {
        if (!TextUtils.isEmpty(num)) {
            return new DecimalFormat("0.00").format(Double.parseDouble(num));
        }
        return num;
    }

    /**
     * "100000"转换成&100，000.00
     */
    public static String StringToMString(String s) {
        if (s == null || TextUtils.isEmpty(s)) {
            s = "0.00";
        }
        Double ll = Double.parseDouble(s);
        String s1 = DoubleToString(ll);
        return s1;
    }

    /**
     * "100000"转换成&100，000
     */
    public static String StringToCString(String s) {
//        if (!TextUtils.isEmpty(s)) {
//            if (s.indexOf(".") > 0) {
//                s = s.replaceAll("0+?$", "");//去掉多余的0
//                s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
//            }
//        }
        return s;
    }

    public static String DataToString(String s) {
        if (!TextUtils.isEmpty(s)) {
            if (s.contains("-") || s.contains(" ")) {

            }
        }
        return s;
    }

    /**
     * 判断str1中包含str2的个数
     * @param str1
     * @param str2
     * @return counter
     */
    private static int counter = 0;
    public static int countStr(String str1, String str2) {
        if (str1.indexOf(str2) == -1) {
            return 0;
        } else if (str1.indexOf(str2) != -1) {
            counter++;
            countStr(str1.substring(str1.indexOf(str2) + str2.length()), str2);
            return counter;
        }
        return 0;
    }

    /**
     * -替换年月日
     */
    public static  String SubString(String s){
        if (!TextUtils.isEmpty(s)){
            return s.substring(0,4) + "年" + s.substring(5,7) + "月" + s.substring(8,10) + "日";
        }
        return s;
    }

    /**
     * 招商银行0447  改为 招商银行（0447）
     */
    public static  String BracketString(String s){
        if (!TextUtils.isEmpty(s)){
            return s.substring(0,s.length()-4) + "(" + s.substring(s.length()-4,s.length()) + ")";
        }
        return s;
    }
}
