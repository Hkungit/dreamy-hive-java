package com.dreamy.hive.service.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dreamy.hive.dto.response.OrderResponseDTO;

/**
 * 管理端订单服务接口
 */
public interface AdminOrderService {
    
    /**
     * 分页获取所有订单
     * @param page 页码
     * @param size 每页大小
     * @param status 订单状态（可选）
     * @param orderNo 订单编号（可选）
     * @return 订单分页数据
     */
    IPage<OrderResponseDTO> getOrderList(Integer page, Integer size, Integer status, String orderNo);
    
    /**
     * 根据ID获取订单详情
     * @param orderId 订单ID
     * @return 订单响应DTO
     */
    OrderResponseDTO getOrderById(Long orderId);
    
    /**
     * 发货
     * @param adminId 管理员ID
     * @param orderId 订单ID
     * @return 是否成功
     */
    boolean shipOrder(Long adminId, Long orderId);
    
    /**
     * 取消订单
     * @param adminId 管理员ID
     * @param orderId 订单ID
     * @return 是否成功
     */
    boolean cancelOrder(Long adminId, Long orderId);
} 