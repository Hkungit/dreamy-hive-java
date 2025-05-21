package com.dreamy.hive.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dreamy.hive.dto.request.OrderCreateRequestDTO;
import com.dreamy.hive.dto.response.OrderResponseDTO;

/**
 * 订单服务接口
 */
public interface OrderService {
    
    /**
     * 创建订单
     * @param userId 用户ID
     * @param orderCreateRequestDTO 订单创建请求
     * @return 订单响应DTO
     */
    OrderResponseDTO createOrder(Long userId, OrderCreateRequestDTO orderCreateRequestDTO);
    
    /**
     * 根据ID获取订单详情
     * @param userId 用户ID
     * @param orderId 订单ID
     * @return 订单响应DTO
     */
    OrderResponseDTO getOrderById(Long userId, Long orderId);
    
    /**
     * 分页获取用户订单列表
     * @param userId 用户ID
     * @param page 页码
     * @param size 每页大小
     * @return 订单分页数据
     */
    IPage<OrderResponseDTO> getUserOrders(Long userId, Integer page, Integer size);
    
    /**
     * 取消订单
     * @param userId 用户ID
     * @param orderId 订单ID
     * @return 是否成功
     */
    boolean cancelOrder(Long userId, Long orderId);
    
    /**
     * 支付订单
     * @param userId 用户ID
     * @param orderId 订单ID
     * @return 是否成功
     */
    boolean payOrder(Long userId, Long orderId);
    
    /**
     * 确认收货
     * @param userId 用户ID
     * @param orderId 订单ID
     * @return 是否成功
     */
    boolean confirmReceipt(Long userId, Long orderId);
} 