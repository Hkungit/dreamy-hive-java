package com.dreamy.hive.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单响应DTO
 */
@Data
public class OrderResponseDTO {
    /**
     * 订单ID
     */
    private Long id;
    
    /**
     * 订单编号
     */
    private String orderNo;
    
    /**
     * 用户ID
     */
    private Long userId;
    
    /**
     * 收货地址ID
     */
    private Long addressId;
    
    /**
     * 收货地址信息
     */
    private AddressResponseDTO address;
    
    /**
     * 订单总金额
     */
    private BigDecimal totalAmount;
    
    /**
     * 订单状态（0-待支付，1-已支付，2-已发货，3-已完成，4-已取消）
     */
    private Integer status;
    
    /**
     * 订单状态描述
     */
    private String statusDesc;
    
    /**
     * 支付时间
     */
    private Date paymentTime;
    
    /**
     * 发货时间
     */
    private Date shippingTime;
    
    /**
     * 完成时间
     */
    private Date completionTime;
    
    /**
     * 创建时间
     */
    private Date createTime;
    
    /**
     * 订单详情列表
     */
    private List<OrderDetailResponseDTO> orderDetails;
} 