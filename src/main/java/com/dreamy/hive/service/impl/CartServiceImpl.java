package com.dreamy.hive.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dreamy.hive.common.Result;
import com.dreamy.hive.dto.request.CartAddRequestDTO;
import com.dreamy.hive.dto.request.CartUpdateRequestDTO;
import com.dreamy.hive.dto.response.CartItemResponseDTO;
import com.dreamy.hive.entity.Cart;
import com.dreamy.hive.entity.Sku;
import com.dreamy.hive.entity.Spu;
import com.dreamy.hive.mapper.CartMapper;
import com.dreamy.hive.service.CartService;
import com.dreamy.hive.service.SkuService;
import com.dreamy.hive.service.SpuService;
import cn.dev33.satoken.stp.StpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {

    @Autowired
    private SkuService skuService;
    
    @Autowired
    private SpuService spuService;

    @Override
    @Transactional
    public Result<?> add(CartAddRequestDTO dto) {
        Long userId = StpUtil.getLoginIdAsLong();
        Long skuId = dto.getSkuId();
        
        // 检查SKU是否存在
        Sku sku = skuService.getById(skuId);
        if (sku == null) {
            return Result.fail("商品不存在");
        }
        
        // 检查商品状态
        if (sku.getStatus() == 0) {
            return Result.fail("商品已下架");
        }
        
        // 检查库存
        if (sku.getStock() < dto.getQuantity()) {
            return Result.fail("商品库存不足");
        }
        
        // 检查购物车中是否已存在该商品
        LambdaQueryWrapper<Cart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Cart::getUserId, userId).eq(Cart::getSkuId, skuId);
        Cart existCart = getOne(queryWrapper);
        
        if (existCart != null) {
            // 已存在，更新数量
            existCart.setQuantity(existCart.getQuantity() + dto.getQuantity());
            existCart.setChecked(1); // 添加到购物车的商品默认选中
            updateById(existCart);
        } else {
            // 不存在，新增
            Cart cart = new Cart();
            cart.setUserId(userId);
            cart.setSkuId(skuId);
            cart.setQuantity(dto.getQuantity());
            cart.setChecked(1); // 添加到购物车的商品默认选中
            save(cart);
        }
        
        return Result.success();
    }

    @Override
    public Result<?> getCartList() {
        Long userId = StpUtil.getLoginIdAsLong();
        
        // 查询用户的购物车列表
        LambdaQueryWrapper<Cart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Cart::getUserId, userId);
        List<Cart> cartList = list(queryWrapper);
        
        if (cartList.isEmpty()) {
            return Result.success(new ArrayList<>());
        }
        
        // 获取所有SKU ID
        List<Long> skuIds = cartList.stream().map(Cart::getSkuId).collect(Collectors.toList());
        
        // 批量查询SKU信息
        List<Sku> skuList = skuService.listByIds(skuIds);
        Map<Long, Sku> skuMap = skuList.stream().collect(Collectors.toMap(Sku::getId, sku -> sku));
        
        // 获取所有SPU ID
        List<Long> spuIds = skuList.stream().map(Sku::getSpuId).distinct().collect(Collectors.toList());
        
        // 批量查询SPU信息
        List<Spu> spuList = spuService.listByIds(spuIds);
        Map<Long, Spu> spuMap = spuList.stream().collect(Collectors.toMap(Spu::getId, spu -> spu));
        
        // 组装购物车项响应DTO
        List<CartItemResponseDTO> cartItemList = new ArrayList<>();
        for (Cart cart : cartList) {
            CartItemResponseDTO item = new CartItemResponseDTO();
            item.setId(cart.getId());
            item.setUserId(cart.getUserId());
            item.setSkuId(cart.getSkuId());
            item.setQuantity(cart.getQuantity());
            item.setChecked(cart.getChecked());
            
            // 设置SKU信息
            Sku sku = skuMap.get(cart.getSkuId());
            if (sku != null) {
                item.setSpuId(sku.getSpuId());
                item.setSkuName(sku.getName());
                item.setPrice(sku.getPrice());
                item.setPic(sku.getPic());
                item.setSpecs(sku.getSpecs());
                item.setStock(sku.getStock());
                
                // 计算小计金额
                BigDecimal subtotal = sku.getPrice().multiply(new BigDecimal(cart.getQuantity()));
                item.setSubtotal(subtotal);
                
                // 设置SPU信息
                Spu spu = spuMap.get(sku.getSpuId());
                if (spu != null) {
                    item.setProductName(spu.getName());
                }
            }
            
            cartItemList.add(item);
        }
        
        return Result.success(cartItemList);
    }

    @Override
    @Transactional
    public Result<?> update(CartUpdateRequestDTO dto) {
        Long userId = StpUtil.getLoginIdAsLong();
        
        // 查询购物车项
        Cart cart = getById(dto.getId());
        if (cart == null) {
            return Result.fail("购物车项不存在");
        }
        
        // 检查是否是当前用户的购物车项
        if (!cart.getUserId().equals(userId)) {
            return Result.fail("无权操作此购物车项");
        }
        
        // 检查SKU是否存在
        Sku sku = skuService.getById(cart.getSkuId());
        if (sku == null) {
            return Result.fail("商品不存在");
        }
        
        // 检查库存
        if (sku.getStock() < dto.getQuantity()) {
            return Result.fail("商品库存不足");
        }
        
        // 更新购物车项数量
        cart.setQuantity(dto.getQuantity());
        updateById(cart);
        
        return Result.success();
    }

    @Override
    @Transactional
    public Result<?> delete(Long id) {
        Long userId = StpUtil.getLoginIdAsLong();
        
        // 查询购物车项
        Cart cart = getById(id);
        if (cart == null) {
            return Result.fail("购物车项不存在");
        }
        
        // 检查是否是当前用户的购物车项
        if (!cart.getUserId().equals(userId)) {
            return Result.fail("无权操作此购物车项");
        }
        
        // 删除购物车项
        removeById(id);
        
        return Result.success();
    }

    @Override
    @Transactional
    public Result<?> updateChecked(Long id, Integer checked) {
        Long userId = StpUtil.getLoginIdAsLong();
        
        // 查询购物车项
        Cart cart = getById(id);
        if (cart == null) {
            return Result.fail("购物车项不存在");
        }
        
        // 检查是否是当前用户的购物车项
        if (!cart.getUserId().equals(userId)) {
            return Result.fail("无权操作此购物车项");
        }
        
        // 更新购物车项选中状态
        cart.setChecked(checked);
        updateById(cart);
        
        return Result.success();
    }

    @Override
    public List<Cart> getCheckedCarts() {
        Long userId = StpUtil.getLoginIdAsLong();
        
        // 查询用户选中的购物车项
        LambdaQueryWrapper<Cart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Cart::getUserId, userId).eq(Cart::getChecked, 1);
        
        return list(queryWrapper);
    }

    @Override
    @Transactional
    public void clearCheckedCarts(Long userId) {
        // 删除用户选中的购物车项
        LambdaUpdateWrapper<Cart> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Cart::getUserId, userId).eq(Cart::getChecked, 1);
        
        remove(updateWrapper);
    }
} 