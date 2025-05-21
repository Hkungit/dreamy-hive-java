package com.dreamy.hive.service.admin.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dreamy.hive.dto.response.AddressResponseDTO;
import com.dreamy.hive.dto.response.OrderDetailResponseDTO;
import com.dreamy.hive.dto.response.OrderResponseDTO;
import com.dreamy.hive.entity.Address;
import com.dreamy.hive.entity.Order;
import com.dreamy.hive.entity.OrderDetail;
import com.dreamy.hive.entity.Sku;
import com.dreamy.hive.common.exception.BusinessException;
import com.dreamy.hive.mapper.AddressMapper;
import com.dreamy.hive.mapper.OrderDetailMapper;
import com.dreamy.hive.mapper.OrderMapper;
import com.dreamy.hive.mapper.SkuMapper;
import com.dreamy.hive.service.admin.AdminOrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 管理端订单服务实现类
 */
@Service
public class AdminOrderServiceImpl implements AdminOrderService {

    private final OrderMapper orderMapper;
    private final OrderDetailMapper orderDetailMapper;
    private final AddressMapper addressMapper;
    private final SkuMapper skuMapper;

    public AdminOrderServiceImpl(OrderMapper orderMapper, OrderDetailMapper orderDetailMapper,
                                AddressMapper addressMapper, SkuMapper skuMapper) {
        this.orderMapper = orderMapper;
        this.orderDetailMapper = orderDetailMapper;
        this.addressMapper = addressMapper;
        this.skuMapper = skuMapper;
    }

    @Override
    public IPage<OrderResponseDTO> getOrderList(Integer page, Integer size, Integer status, String orderNo) {
        Page<Order> pageParam = new Page<>(page, size);
        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
        
        // 添加筛选条件
        if (status != null) {
            queryWrapper.eq(Order::getStatus, status);
        }
        
        if (StringUtils.hasText(orderNo)) {
            queryWrapper.like(Order::getOrderNo, orderNo);
        }
        
        // 按创建时间降序排序
        queryWrapper.orderByDesc(Order::getCreateTime);
        
        IPage<Order> orderPage = orderMapper.selectPage(pageParam, queryWrapper);
        
        // 转换为DTO
        IPage<OrderResponseDTO> resultPage = new Page<>(orderPage.getCurrent(), orderPage.getSize(), orderPage.getTotal());
        List<OrderResponseDTO> records = orderPage.getRecords().stream().map(order -> {
            OrderResponseDTO dto = new OrderResponseDTO();
            BeanUtils.copyProperties(order, dto);
            
            // 设置订单状态描述
            dto.setStatusDesc(getOrderStatusDesc(order.getStatus()));
            
            // 查询订单详情
            LambdaQueryWrapper<OrderDetail> detailQueryWrapper = new LambdaQueryWrapper<>();
            detailQueryWrapper.eq(OrderDetail::getOrderId, order.getId());
            List<OrderDetail> details = orderDetailMapper.selectList(detailQueryWrapper);
            
            // 转换订单详情
            List<OrderDetailResponseDTO> detailDTOs = details.stream().map(detail -> {
                OrderDetailResponseDTO detailDTO = new OrderDetailResponseDTO();
                BeanUtils.copyProperties(detail, detailDTO);
                return detailDTO;
            }).collect(Collectors.toList());
            
            dto.setOrderDetails(detailDTOs);
            
            // 设置收货地址
            try {
                Address address = addressMapper.selectById(order.getAddressId());
                if (address != null) {
                    AddressResponseDTO addressResponseDTO = new AddressResponseDTO();
                    BeanUtils.copyProperties(address, addressResponseDTO);
                    dto.setAddress(addressResponseDTO);
                }
            } catch (Exception e) {
                // 地址可能已被删除，忽略异常
            }
            
            return dto;
        }).collect(Collectors.toList());
        
        resultPage.setRecords(records);
        return resultPage;
    }

    @Override
    public OrderResponseDTO getOrderById(Long orderId) {
        // 查询订单
        Order order = orderMapper.selectById(orderId);
        if (order == null) {
            throw new BusinessException("订单不存在");
        }
        
        // 查询订单详情
        LambdaQueryWrapper<OrderDetail> detailQueryWrapper = new LambdaQueryWrapper<>();
        detailQueryWrapper.eq(OrderDetail::getOrderId, orderId);
        List<OrderDetail> details = orderDetailMapper.selectList(detailQueryWrapper);
        
        // 查询收货地址
        Address address = addressMapper.selectById(order.getAddressId());
        
        // 转换为DTO
        OrderResponseDTO responseDTO = new OrderResponseDTO();
        BeanUtils.copyProperties(order, responseDTO);
        
        // 设置订单状态描述
        responseDTO.setStatusDesc(getOrderStatusDesc(order.getStatus()));
        
        // 设置收货地址
        if (address != null) {
            AddressResponseDTO addressResponseDTO = new AddressResponseDTO();
            BeanUtils.copyProperties(address, addressResponseDTO);
            responseDTO.setAddress(addressResponseDTO);
        }
        
        // 设置订单详情
        List<OrderDetailResponseDTO> detailDTOs = details.stream().map(detail -> {
            OrderDetailResponseDTO detailDTO = new OrderDetailResponseDTO();
            BeanUtils.copyProperties(detail, detailDTO);
            return detailDTO;
        }).collect(Collectors.toList());
        
        responseDTO.setOrderDetails(detailDTOs);
        
        return responseDTO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean shipOrder(Long adminId, Long orderId) {
        Order order = orderMapper.selectById(orderId);
        if (order == null) {
            throw new BusinessException("订单不存在");
        }
        
        if (order.getStatus() != 1) {
            throw new BusinessException("只有已支付状态的订单可以发货");
        }
        
        // 更新订单状态
        order.setStatus(2); // 2-已发货
        order.setShippingTime(new Date());
        order.setUpdateTime(new Date());
        order.setUpdateBy(adminId);
        orderMapper.updateById(order);
        
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean cancelOrder(Long adminId, Long orderId) {
        Order order = orderMapper.selectById(orderId);
        if (order == null) {
            throw new BusinessException("订单不存在");
        }
        
        if (order.getStatus() == 3 || order.getStatus() == 4) {
            throw new BusinessException("已完成或已取消的订单不能取消");
        }
        
        // 更新订单状态
        order.setStatus(4); // 4-已取消
        order.setUpdateTime(new Date());
        order.setUpdateBy(adminId);
        orderMapper.updateById(order);
        
        // 如果订单已支付或已发货，需要恢复库存
        if (order.getStatus() == 1 || order.getStatus() == 2) {
            LambdaQueryWrapper<OrderDetail> detailQueryWrapper = new LambdaQueryWrapper<>();
            detailQueryWrapper.eq(OrderDetail::getOrderId, orderId);
            List<OrderDetail> details = orderDetailMapper.selectList(detailQueryWrapper);
            
            for (OrderDetail detail : details) {
                Sku sku = skuMapper.selectById(detail.getSkuId());
                if (sku != null) {
                    sku.setStock(sku.getStock() + detail.getQuantity());
                    skuMapper.updateById(sku);
                }
            }
        }
        
        return true;
    }
    
    /**
     * 获取订单状态描述
     * @param status 状态码
     * @return 状态描述
     */
    private String getOrderStatusDesc(Integer status) {
        switch (status) {
            case 0:
                return "待支付";
            case 1:
                return "已支付";
            case 2:
                return "已发货";
            case 3:
                return "已完成";
            case 4:
                return "已取消";
            default:
                return "未知状态";
        }
    }
} 