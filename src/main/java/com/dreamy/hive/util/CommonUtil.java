package com.dreamy.hive.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * 通用工具类
 */
public class CommonUtil {
    
    private static final String BASE_NUM = "0123456789";
    private static final String BASE_CHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final Random RANDOM = new Random();
    
    /**
     * 生成订单编号
     * 格式：年月日时分秒 + 6位随机数
     */
    public static String generateOrderNo() {
        // 获取当前时间，格式：yyyyMMddHHmmss
        String timeStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        // 生成6位随机数
        String randomStr = generateRandomNum(6);
        return timeStr + randomStr;
    }
    
    /**
     * 生成指定长度的随机数字字符串
     */
    public static String generateRandomNum(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(BASE_NUM.charAt(RANDOM.nextInt(BASE_NUM.length())));
        }
        return sb.toString();
    }
    
    /**
     * 生成指定长度的随机字符串（数字+大写字母）
     */
    public static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(BASE_CHAR.charAt(RANDOM.nextInt(BASE_CHAR.length())));
        }
        return sb.toString();
    }
    
    /**
     * 生成SKU编码
     */
    public static String generateSkuCode() {
        // 格式：SKU + 年月日 + 4位随机数
        String dateStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String randomStr = generateRandomNum(4);
        return "SKU" + dateStr + randomStr;
    }
    
    /**
     * 生成验证码（6位数字）
     */
    public static String generateVerifyCode() {
        return generateRandomNum(6);
    }
}
