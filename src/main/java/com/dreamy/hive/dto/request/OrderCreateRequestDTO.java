package com.dreamy.hive.dto.request;

import lombok.Data;

import jakarta.validation.constraints.NotNull;

/**
 * 订单创建请求DTO
 */
@Data
public class OrderCreateRequestDTO {
    /**
     * 收货地址ID
     */
    @NotNull(message = "收货地址不能为空")
    private Long addressId;
} 