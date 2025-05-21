package com.dreamy.hive.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dreamy.hive.common.exception.BusinessException;
import com.dreamy.hive.dto.request.OrderCreateRequestDTO;
import com.dreamy.hive.dto.response.AddressResponseDTO;
import com.dreamy.hive.dto.response.OrderDetailResponseDTO;
import com.dreamy.hive.dto.response.OrderResponseDTO;
import com.dreamy.hive.entity.Address;
import com.dreamy.hive.entity.Cart;
import com.dreamy.hive.entity.Order;
import com.dreamy.hive.entity.OrderDetail;
import com.dreamy.hive.entity.Sku;
import com.dreamy.hive.entity.Spu;
import com.dreamy.hive.mapper.CartMapper;
import com.dreamy.hive.mapper.OrderDetailMapper;
import com.dreamy.hive.mapper.OrderMapper;
import com.dreamy.hive.mapper.SkuMapper;
import com.dreamy.hive.mapper.SpuMapper;
import com.dreamy.hive.service.AddressService;
import com.dreamy.hive.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 订单服务实现类
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    private final OrderMapper orderMapper;
    private final OrderDetailMapper orderDetailMapper;
    private final CartMapper cartMapper;
    private final SkuMapper skuMapper;
    private final SpuMapper spuMapper;
    private final AddressService addressService;

    public OrderServiceImpl(OrderMapper orderMapper, OrderDetailMapper orderDetailMapper, 
                           CartMapper cartMapper, SkuMapper skuMapper, SpuMapper spuMapper,
                           AddressService addressService) {
        this.orderMapper = orderMapper;
        this.orderDetailMapper = orderDetailMapper;
        this.cartMapper = cartMapper;
        this.skuMapper = skuMapper;
        this.spuMapper = spuMapper;
        this.addressService = addressService;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrderResponseDTO createOrder(Long userId, OrderCreateRequestDTO orderCreateRequestDTO) {
        // 1. 查询用户购物车中已选中的商品
        LambdaQueryWrapper<Cart> cartQueryWrapper = new LambdaQueryWrapper<>();
        cartQueryWrapper.eq(Cart::getUserId, userId)
                        .eq(Cart::getChecked, 1);
        List<Cart> cartItems = cartMapper.selectList(cartQueryWrapper);
        
        if (cartItems.isEmpty()) {
            throw new BusinessException("购物车中没有选中的商品");
        }
        
        // 2. 验证收货地址
        Address address = addressService.getById(orderCreateRequestDTO.getAddressId());
        if (address == null || !address.getUserId().equals(userId)) {
            throw new BusinessException("收货地址不存在或不属于当前用户");
        }
        
        // 3. 创建订单
        Order order = new Order();
        order.setOrderNo(generateOrderNo());
        order.setUserId(userId);
        order.setAddressId(orderCreateRequestDTO.getAddressId());
        order.setStatus(0); // 0-待支付
        order.setCreateBy(userId);
        order.setUpdateBy(userId);
        
        // 4. 计算订单总金额并创建订单详情
        BigDecimal totalAmount = BigDecimal.ZERO;
        List<OrderDetail> orderDetails = new ArrayList<>();
        
        for (Cart cartItem : cartItems) {
            Sku sku = skuMapper.selectById(cartItem.getSkuId());
            if (sku == null) {
                throw new BusinessException("商品不存在，SKU ID: " + cartItem.getSkuId());
            }
            
            if (sku.getStock() < cartItem.getQuantity()) {
                throw new BusinessException("商品库存不足: " + sku.getSkuCode());
            }
            
            Spu spu = spuMapper.selectById(sku.getSpuId());
            if (spu == null) {
                throw new BusinessException("商品信息不完整，SPU ID: " + sku.getSpuId());
            }
            
            // 创建订单详情
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setSpuId(sku.getSpuId());
            orderDetail.setSkuId(sku.getId());
            orderDetail.setSkuCode(sku.getSkuCode());
            orderDetail.setProductName(spu.getName());
            orderDetail.setSpecs(sku.getSpecs());
            orderDetail.setPrice(sku.getPrice());
            orderDetail.setOriginalPrice(sku.getOriginalPrice());
            orderDetail.setQuantity(cartItem.getQuantity());
            orderDetail.setPic(sku.getPic());
            
            // 计算小计金额
            BigDecimal subtotal = sku.getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity()));
            orderDetail.setSubtotal(subtotal);
            
            // 累加总金额
            totalAmount = totalAmount.add(subtotal);
            
            orderDetails.add(orderDetail);
            
            // 减少库存
            sku.setStock(sku.getStock() - cartItem.getQuantity());
            skuMapper.updateById(sku);
        }
        
        order.setTotalAmount(totalAmount);
        orderMapper.insert(order);
        
        // 5. 保存订单详情
        for (OrderDetail orderDetail : orderDetails) {
            orderDetail.setOrderId(order.getId());
            orderDetailMapper.insert(orderDetail);
        }
        
        // 6. 清空购物车中已选中的商品
        cartMapper.delete(cartQueryWrapper);
        
        // 7. 返回订单信息
        return getOrderById(userId, order.getId());
    }

    @Override
    public OrderResponseDTO getOrderById(Long userId, Long orderId) {
        // 查询订单
        Order order = orderMapper.selectById(orderId);
        if (order == null || !order.getUserId().equals(userId)) {
            throw new BusinessException("订单不存在或不属于当前用户");
        }
        
        // 查询订单详情
        LambdaQueryWrapper<OrderDetail> detailQueryWrapper = new LambdaQueryWrapper<>();
        detailQueryWrapper.eq(OrderDetail::getOrderId, orderId);
        List<OrderDetail> details = orderDetailMapper.selectList(detailQueryWrapper);
        
        // 查询收货地址
        AddressResponseDTO addressResponseDTO = addressService.getAddressById(userId, order.getAddressId());
        
        // 转换为DTO
        OrderResponseDTO responseDTO = new OrderResponseDTO();
        BeanUtils.copyProperties(order, responseDTO);
        
        // 设置订单状态描述
        responseDTO.setStatusDesc(getOrderStatusDesc(order.getStatus()));
        
        // 设置收货地址
        responseDTO.setAddress(addressResponseDTO);
        
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
    public IPage<OrderResponseDTO> getUserOrders(Long userId, Integer page, Integer size) {
        // 分页查询用户订单
        Page<Order> pageParam = new Page<>(page, size);
        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Order::getUserId, userId)
                    .orderByDesc(Order::getCreateTime);
        
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
                AddressResponseDTO addressResponseDTO = addressService.getAddressById(userId, order.getAddressId());
                dto.setAddress(addressResponseDTO);
            } catch (Exception e) {
                // 地址可能已被删除，忽略异常
            }
            
            return dto;
        }).collect(Collectors.toList());
        
        resultPage.setRecords(records);
        return resultPage;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean cancelOrder(Long userId, Long orderId) {
        Order order = orderMapper.selectById(orderId);
        if (order == null || !order.getUserId().equals(userId)) {
            throw new BusinessException("订单不存在或不属于当前用户");
        }
        
        if (order.getStatus() != 0) {
            throw new BusinessException("只有待支付状态的订单可以取消");
        }
        
        // 更新订单状态
        order.setStatus(4); // 4-已取消
        order.setUpdateTime(new Date());
        order.setUpdateBy(userId);
        orderMapper.updateById(order);
        
        // 恢复库存
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
        
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean payOrder(Long userId, Long orderId) {
        Order order = orderMapper.selectById(orderId);
        if (order == null || !order.getUserId().equals(userId)) {
            throw new BusinessException("订单不存在或不属于当前用户");
        }
        
        if (order.getStatus() != 0) {
            throw new BusinessException("只有待支付状态的订单可以支付");
        }
        
        // 更新订单状态
        order.setStatus(1); // 1-已支付
        order.setPaymentTime(new Date());
        order.setUpdateTime(new Date());
        order.setUpdateBy(userId);
        orderMapper.updateById(order);
        
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean confirmReceipt(Long userId, Long orderId) {
        Order order = orderMapper.selectById(orderId);
        if (order == null || !order.getUserId().equals(userId)) {
            throw new BusinessException("订单不存在或不属于当前用户");
        }
        
        if (order.getStatus() != 2) {
            throw new BusinessException("只有已发货状态的订单可以确认收货");
        }
        
        // 更新订单状态
        order.setStatus(3); // 3-已完成
        order.setCompletionTime(new Date());
        order.setUpdateTime(new Date());
        order.setUpdateBy(userId);
        orderMapper.updateById(order);
        
        return true;
    }
    
    /**
     * 生成订单编号
     * @return 订单编号
     */
    private String generateOrderNo() {
        // 生成格式：年月日时分秒 + 4位随机数
        String dateStr = String.valueOf(System.currentTimeMillis());
        String uuid = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 4);
        return dateStr + uuid;
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