

package com.haolyy.haolyy.utils;

import java.io.UnsupportedEncodingException;
import java.security.Provider;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES加密
 * Created by liliang on 2018/1/15.
 */

public class AESUtils {
    private static final String CipherMode = "AES/ECB/PKCS5Padding";//使用ECB加密，不需要设置IV，但是不安全PKCS5Padding
    private static final String strKey = "c572ea1baopaw598";

    /**
     * @param
     * @return AES加密算法加密
     * @throws Exception
     */
    public static String encrypt(String seed) {
        byte[] rawKey = null;
        byte[] result = null;
        try {
//            rawKey = getRawKey(strKey.getBytes());
//            result = encrypt(seed.getBytes("UTF-8"), rawKey);
            result = encrypt(seed.getBytes("UTF-8"), strKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //return toHex(result);
        return encodeBase(result);
    }

    public static byte[] encryptByte(String seed, String key) throws Exception {
        byte[] rawKey = getRawKey(key.getBytes());
        return encrypt(seed.getBytes("UTF-8"), rawKey);
    }

    /**
     * 解密
     *
     * @param byteData
     * @param byteKey
     */
    public static String decryptString(byte[] byteData, byte[] byteKey) throws Exception {
        byte[] rawKey = getRawKey(byteKey);
        byte[] result = decrypt(byteData, rawKey);
        return new String(result, "UTF-8");
    }


    /***
     * AES加密算法加密
     * @param byteData 数据
     * @param byteKey key
     */
    private static byte[] encrypt(byte[] byteData, byte[] byteKey) throws Exception {
        return Ase(byteData, byteKey, Cipher.ENCRYPT_MODE);
    }

    /***
     * AES加密算法解密
     * @param byteData 数据
     * @param byteKey key
     */
    private static byte[] decrypt(byte[] byteData, byte[] byteKey) throws Exception {
        return Ase(byteData, byteKey, Cipher.DECRYPT_MODE);
    }


    /***
     *
     * @param byteData
     * @param byteKey
     * @param opmode
     * @return
     * @throws Exception
     */
    private static byte[] Ase(byte[] byteData, byte[] byteKey, int opmode) throws Exception {
        //AES:加密方式、ECB:工作模式、PKCS5Padding：填充模式
        Cipher cipher = Cipher.getInstance(CipherMode);
        SecretKeySpec skeySpec = new SecretKeySpec(byteKey, "AES");
        cipher.init(opmode, skeySpec);
        byte[] decrypted = cipher.doFinal(byteData);
        return decrypted;
    }

    /**
     * 对秘钥进行处理
     */
    private static byte[] getRawKey(byte[] seed) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom sr = null;
        int sdk_version = android.os.Build.VERSION.SDK_INT;
        //SHA1PRNG :强随机种子算法，要区别4.2版本以上的调用方式
        if (sdk_version > 23) {  // Android  6.0 以上
            sr = SecureRandom.getInstance("SHA1PRNG", new CryptoProvider());
        } else if (sdk_version >= 17) {
            sr = SecureRandom.getInstance("SHA1PRNG", "Crypto");
        } else {
            sr = SecureRandom.getInstance("SHA1PRNG");
        }
        sr.setSeed(seed);
        kgen.init(128, sr); // 192 and 256 bits may not be available
        SecretKey skey = kgen.generateKey();
        byte[] raw = skey.getEncoded();
        return raw;
    }


    /**
     * 对秘钥进行处理
     */
    private static SecretKeySpec createKey(String password) {
        byte[] data = null;
        if (password == null) {
            password = "";
        }
        StringBuffer sb = new StringBuffer(16);
        sb.append(password);
        while (sb.length() < 16) {
            sb.append("0");
        }
        if (sb.length() > 16) {
            sb.setLength(16);
        }
        try {
            data = sb.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new SecretKeySpec(data, "AES");
    }

    // /** 加密字节数据 **/
    public static byte[] encrypt(byte[] content, String password) {
        try {
            SecretKeySpec key = createKey(password);
            System.out.println(key);
            Cipher cipher = Cipher.getInstance(CipherMode);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] result = cipher.doFinal(content);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static class CryptoProvider extends Provider {
        /**
         * Creates a Provider and puts parameters
         */
        public CryptoProvider() {
            super("Crypto", 1.0, "HARMONY (SHA1 digest; SecureRandom; SHA1withDSA signature)");
            put("SecureRandom.SHA1PRNG", "org.apache.harmony.security.provider.crypto.SHA1PRNG_SecureRandomImpl");
            put("SecureRandom.SHA1PRNG ImplementedIn", "Software");
        }
    }


    private static String toHex(byte[] buf) {
        //常量介绍
        final String HEX = "0123456789ABCDEF";
        if (buf == null)
            return "";
        StringBuffer result = new StringBuffer(2 * buf.length);
        for (int i = 0; i < buf.length; i++) {
            result.append(HEX.charAt((buf[i] >> 4) & 0x0f)).append(HEX.charAt(buf[i] & 0x0f));
        }
        return result.toString();
    }

    private static byte[] toByte(String hexString) {
        int len = hexString.length() / 2;
        byte[] result = new byte[len];
        for (int i = 0; i < len; i++)
            result[i] = Integer.valueOf(hexString.substring(2 * i, 2 * i + 2), 16).byteValue();
        return result;
    }


    /**
     * base64加密
     *
     * @param str
     * @return
     */
    public static String encodeBase(byte[] str) {
        //return Base64.encodeToString(str, Base64.DEFAULT);
        //return  new String(Base64Utils.encode(str, Base64Utils.DEFAULT));
        //return  Base64Encoder.encode(result);
        return new String(Base64Utils.encode(str));
    }

    /**
     * base64解密
     *
     * @param str
     * @return
     */
    public static byte[] decodeBase(String str) {
        //return new String(Base64Utils.decode(str, Base64Utils.DEFAULT));
        //return Base64Utils.decode(str.getBytes(), Base64Utils.DEFAULT);
        return null;
    }
}

