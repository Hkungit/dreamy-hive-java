package com.dreamy.hive.controller.v1.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dreamy.hive.annotation.CurrentUser;
import com.dreamy.hive.annotation.RequiresPermission;
import com.dreamy.hive.annotation.RequiresRole;
import com.dreamy.hive.common.Result;
import com.dreamy.hive.dto.response.OrderResponseDTO;
import com.dreamy.hive.service.admin.AdminOrderService;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 管理端订单控制器
 */
@RestController
@RequestMapping("/v1/admin/orders")
@Tag(name = "管理端-订单管理", description = "管理端订单相关接口")
public class AdminOrderController {

    private final AdminOrderService adminOrderService;

    public AdminOrderController(AdminOrderService adminOrderService) {
        this.adminOrderService = adminOrderService;
    }

    /**
     * 分页获取所有订单
     * @param userId 管理员ID
     * @param page 页码
     * @param size 每页大小
     * @param status 订单状态（可选）
     * @param orderNo 订单编号（可选）
     * @return 订单分页数据
     */
    @GetMapping
    public Result<IPage<OrderResponseDTO>> getOrderList(@CurrentUser Long userId,
                                                           @RequestParam(defaultValue = "1") Integer page,
                                                           @RequestParam(defaultValue = "10") Integer size,
                                                           @RequestParam(required = false) Integer status,
                                                           @RequestParam(required = false) String orderNo) {
        IPage<OrderResponseDTO> orderPage = adminOrderService.getOrderList(page, size, status, orderNo);
        return Result.success(orderPage);
    }

    /**
     * 获取订单详情
     * @param userId 管理员ID
     * @param orderId 订单ID
     * @return 订单详情
     */
    @GetMapping("/{orderId}")
    public Result<OrderResponseDTO> getOrderDetail(@CurrentUser Long userId, @PathVariable Long orderId) {
        OrderResponseDTO orderResponseDTO = adminOrderService.getOrderById(orderId);
        return Result.success(orderResponseDTO);
    }

    /**
     * 发货
     * @param userId 管理员ID
     * @param orderId 订单ID
     * @return 操作结果
     */
    @PostMapping("/{orderId}/ship")
    public Result<Boolean> shipOrder(@CurrentUser Long userId, @PathVariable Long orderId) {
        boolean result = adminOrderService.shipOrder(userId, orderId);
        return Result.success(result);
    }

    /**
     * 取消订单
     * @param userId 管理员ID
     * @param orderId 订单ID
     * @return 操作结果
     */
    @PostMapping("/{orderId}/cancel")
    public Result<Boolean> cancelOrder(@CurrentUser Long userId, @PathVariable Long orderId) {
        boolean result = adminOrderService.cancelOrder(userId, orderId);
        return Result.success(result);
    }
}