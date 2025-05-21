package com.dreamy.hive.controller.v1.user;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dreamy.hive.annotation.CurrentUser;
import com.dreamy.hive.common.Result;
import com.dreamy.hive.dto.request.OrderCreateRequestDTO;
import com.dreamy.hive.dto.response.OrderResponseDTO;
import com.dreamy.hive.service.OrderService;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

/**
 * 订单控制器
 */
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * 创建订单
     * @param userId 当前用户ID
     * @param orderCreateRequestDTO 订单创建请求
     * @return 订单信息
     */
    @PostMapping
    public Result<OrderResponseDTO> createOrder(@CurrentUser Long userId,
                                                    @RequestBody @Valid OrderCreateRequestDTO orderCreateRequestDTO) {
        OrderResponseDTO orderResponseDTO = orderService.createOrder(userId, orderCreateRequestDTO);
        return Result.success(orderResponseDTO);
    }

    /**
     * 获取订单详情
     * @param userId 当前用户ID
     * @param orderId 订单ID
     * @return 订单详情
     */
    @GetMapping("/{orderId}")
    public Result<OrderResponseDTO> getOrderDetail(@CurrentUser Long userId, @PathVariable Long orderId) {
        OrderResponseDTO orderResponseDTO = orderService.getOrderById(userId, orderId);
        return Result.success(orderResponseDTO);
    }

    /**
     * 获取用户订单列表
     * @param userId 当前用户ID
     * @param page 页码
     * @param size 每页大小
     * @return 订单列表
     */
    @GetMapping
    public Result<IPage<OrderResponseDTO>> getUserOrders(@CurrentUser Long userId,
                                                            @RequestParam(defaultValue = "1") Integer page,
                                                            @RequestParam(defaultValue = "10") Integer size) {
        IPage<OrderResponseDTO> orderPage = orderService.getUserOrders(userId, page, size);
        return Result.success(orderPage);
    }

    /**
     * 取消订单
     * @param userId 当前用户ID
     * @param orderId 订单ID
     * @return 操作结果
     */
    @PostMapping("/{orderId}/cancel")
    public Result<Boolean> cancelOrder(@CurrentUser Long userId, @PathVariable Long orderId) {
        boolean result = orderService.cancelOrder(userId, orderId);
        return Result.success(result);
    }

    /**
     * 支付订单
     * @param userId 当前用户ID
     * @param orderId 订单ID
     * @return 操作结果
     */
    @PostMapping("/{orderId}/pay")
    public Result<Boolean> payOrder(@CurrentUser Long userId, @PathVariable Long orderId) {
        boolean result = orderService.payOrder(userId, orderId);
        return Result.success(result);
    }

    /**
     * 确认收货
     * @param userId 当前用户ID
     * @param orderId 订单ID
     * @return 操作结果
     */
    @PostMapping("/{orderId}/confirm")
    public Result<Boolean> confirmReceipt(@CurrentUser Long userId, @PathVariable Long orderId) {
        boolean result = orderService.confirmReceipt(userId, orderId);
        return Result.success(result);
    }
}