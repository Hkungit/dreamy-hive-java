package com.dreamy.hive.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 通用返回结果
 */
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 是否成功
     */
    private Boolean success;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String message;

    /**
     * 数据
     */
    private T data;

    /**
     * 私有构造方法，禁止直接创建
     */
    private Result() {
    }

    /**
     * 判断是否成功
     * @return 是否成功
     */
    public boolean isSuccess() {
        return this.success != null && this.success;
    }

    /**
     * 成功返回结果
     *
     * @param data 数据
     * @param <T>  类型
     * @return 结果
     */
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setSuccess(true);
        result.setCode(200);
        result.setMessage("操作成功");
        result.setData(data);
        return result;
    }

    /**
     * 成功返回结果
     *
     * @param data    数据
     * @param message 消息
     * @param <T>     类型
     * @return 结果
     */
    public static <T> Result<T> success(T data, String message) {
        Result<T> result = new Result<>();
        result.setSuccess(true);
        result.setCode(200);
        result.setMessage(message);
        result.setData(data);
        return result;
    }
    public static Result<?> success() {
        Result<?> result = new Result<>();
        result.setSuccess(true);
        result.setCode(200);
        result.setMessage("操作成功");
        return result;
    }

    /**
     * 失败返回结果
     *
     * @param message 消息
     * @param <T>     类型
     * @return 结果
     */
    public static <T> Result<T> fail(String message) {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setCode(500);
        result.setMessage(message);
        return result;
    }

    /**
     * 失败返回结果
     *
     * @param code    状态码
     * @param message 消息
     * @param <T>     类型
     * @return 结果
     */
    public static <T> Result<T> fail(Integer code, String message) {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    /**
     * 参数验证失败返回结果
     *
     * @param message 消息
     * @param <T>     类型
     * @return 结果
     */
    public static <T> Result<T> validateFailed(String message) {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setCode(400);
        result.setMessage(message);
        return result;
    }

    /**
     * 未授权返回结果
     *
     * @param <T> 类型
     * @return 结果
     */
    public static <T> Result<T> unauthorized(String message) {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setCode(401);
        result.setMessage(message);
        return result;
    }

    /**
     * 未授权返回结果
     *
     * @param <T> 类型
     * @return 结果
     */
    public static <T> Result<T> forbidden(String message) {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setCode(403);
        result.setMessage(message);
        return result;
    }


}
