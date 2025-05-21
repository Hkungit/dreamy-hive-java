package com.dreamy.hive.controller.v1.common;

import com.dreamy.hive.common.Result;
import com.dreamy.hive.util.RedisUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Redis测试控制器
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/common/redis")
@Tag(name = "Redis测试接口", description = "用于测试Redis操作")
public class RedisTestController {

    @Autowired
    private RedisUtils redisUtils;

    @Operation(summary = "测试Redis字符串操作", description = "测试设置和获取String类型的数据")
    @GetMapping("/string/{key}/{value}")
    public Result<String> testString(@PathVariable("key") String key, @PathVariable("value") String value) {
        redisUtils.set(key, value);
        Object result = redisUtils.get(key);
        return Result.success("操作成功，值为: " + result);
    }

    @Operation(summary = "测试Redis字符串设置过期时间", description = "测试设置String类型的数据并设置过期时间")
    @GetMapping("/string/expire/{key}/{value}/{time}")
    public Result<String> testStringWithExpire(
            @PathVariable("key") String key,
            @PathVariable("value") String value,
            @PathVariable("time") long time) {
        redisUtils.set(key, value, time);
        Object result = redisUtils.get(key);
        long expireTime = redisUtils.getExpire(key);
        return Result.success("操作成功，值为: " + result + "，过期时间剩余: " + expireTime + "秒");
    }

    @Operation(summary = "测试Redis哈希操作", description = "测试设置和获取Hash类型的数据")
    @GetMapping("/hash/{key}")
    public Result<Map<Object, Object>> testHash(@PathVariable("key") String key) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "张三");
        map.put("age", 25);
        map.put("email", "zhangsan@example.com");

        redisUtils.hmset(key, map);
        Map<Object, Object> result = redisUtils.hmget(key);
        return Result.success(result);
    }

    @Operation(summary = "测试Redis列表操作", description = "测试向列表添加数据并获取")
    @GetMapping("/list/{key}")
    public Result<Object> testList(@PathVariable("key") String key) {
        // 删除已存在的列表
        if (redisUtils.hasKey(key)) {
            redisUtils.delete(key);
        }

        // 向列表添加数据
        redisUtils.lSet(key, "数据1");
        redisUtils.lSet(key, "数据2");
        redisUtils.lSet(key, "数据3");

        // 获取列表数据
        long size = redisUtils.lGetListSize(key);
        Object result = redisUtils.lGet(key, 0, size - 1);
        return Result.success(result);
    }

    @Operation(summary = "测试Redis集合操作", description = "测试向集合添加数据并获取")
    @GetMapping("/set/{key}")
    public Result<Object> testSet(@PathVariable("key") String key) {
        // 删除已存在的集合
        if (redisUtils.hasKey(key)) {
            redisUtils.delete(key);
        }

        // 向集合添加数据
        redisUtils.sSet(key, "唯一数据1");
        redisUtils.sSet(key, "唯一数据2");
        redisUtils.sSet(key, "唯一数据3");
        redisUtils.sSet(key, "唯一数据1");  // 重复数据不会添加

        // 获取集合数据
        Object result = redisUtils.sGet(key);
        return Result.success(result);
    }

    @Operation(summary = "删除Redis键", description = "删除指定的Redis键")
    @DeleteMapping("/delete/{key}")
    public Result<String> deleteKey(@PathVariable("key") String key) {
        boolean hasKey = redisUtils.hasKey(key);
        if (hasKey) {
            redisUtils.delete(key);
            return Result.success("键 " + key + " 删除成功");
        } else {
            return Result.fail("键 " + key + " 不存在");
        }
    }
}