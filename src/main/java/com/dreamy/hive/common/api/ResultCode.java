package com.dreamy.hive.common.api;

/**
 * 常用API返回码
 */
public enum ResultCode implements IErrorCode {
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限"),
    USER_NOT_EXIST(1001, "用户不存在"),
    USER_DISABLED(1002, "用户已被禁用"),
    USER_EXISTS(1003, "用户已存在"),
    PASSWORD_ERROR(1004, "密码错误"),
    VERIFICATION_CODE_ERROR(1005, "验证码错误"),
    VERIFICATION_CODE_EXPIRED(1006, "验证码已过期"),
    PRODUCT_NOT_EXIST(2001, "商品不存在"),
    PRODUCT_OFF_SHELF(2002, "商品已下架"),
    PRODUCT_STOCK_NOT_ENOUGH(2003, "商品库存不足"),
    ORDER_NOT_EXIST(3001, "订单不存在"),
    ORDER_STATUS_ERROR(3002, "订单状态异常"),
    ORDER_CANCELED(3003, "订单已取消"),
    ORDER_PAID(3004, "订单已支付"),
    ORDER_DELIVERED(3005, "订单已发货"),
    ORDER_COMPLETED(3006, "订单已完成"),
    CART_ITEM_NOT_EXIST(4001, "购物车中无此商品"),
    ADDRESS_NOT_EXIST(5001, "收货地址不存在"),
    POST_NOT_EXIST(6001, "帖子不存在"),
    COMMENT_NOT_EXIST(6002, "评论不存在"),
    LIKE_ALREADY_EXISTS(6003, "已点赞");

    private final Integer code;
    private final String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
