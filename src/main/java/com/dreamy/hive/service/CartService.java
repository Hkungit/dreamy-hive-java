package com.dreamy.hive.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dreamy.hive.common.Result;
import com.dreamy.hive.dto.request.CartAddRequestDTO;
import com.dreamy.hive.dto.request.CartUpdateRequestDTO;
import com.dreamy.hive.entity.Cart;

import java.util.List;

public interface CartService extends IService<Cart> {
    /**
     * 添加商品到购物车
     * @param dto 购物车添加请求DTO
     * @return 操作结果
     */
    Result<?> add(CartAddRequestDTO dto);
    
    /**
     * 获取当前用户的购物车列表
     * @return 购物车列表结果
     */
    Result<?> getCartList();
    
    /**
     * 更新购物车商品数量
     * @param dto 购物车更新请求DTO
     * @return 操作结果
     */
    Result<?> update(CartUpdateRequestDTO dto);
    
    /**
     * 删除购物车商品
     * @param id 购物车ID
     * @return 操作结果
     */
    Result<?> delete(Long id);
    
    /**
     * 选中/取消选中购物车商品
     * @param id 购物车ID
     * @param checked 是否选中(1-选中，0-未选中)
     * @return 操作结果
     */
    Result<?> updateChecked(Long id, Integer checked);
    
    /**
     * 获取当前用户选中的购物车商品
     * @return 选中的购物车商品列表
     */
    List<Cart> getCheckedCarts();
    
    /**
     * 清空用户购物车中已选中的商品
     * @param userId 用户ID
     */
    void clearCheckedCarts(Long userId);
} 