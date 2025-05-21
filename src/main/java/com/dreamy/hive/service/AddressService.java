package com.dreamy.hive.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dreamy.hive.common.Result;
import com.dreamy.hive.dto.request.AddressRequestDTO;
import com.dreamy.hive.dto.response.AddressResponseDTO;
import com.dreamy.hive.entity.Address;

public interface AddressService extends IService<Address> {
    /**
     * 添加收货地址
     * @param dto 地址请求DTO
     * @return 操作结果
     */
    Result<?> add(AddressRequestDTO dto);
    
    /**
     * 获取当前用户的地址列表
     * @return 地址列表结果
     */
    Result<?> getAddressList();
    
    /**
     * 获取地址详情
     * @param id 地址ID
     * @return 地址详情结果
     */
    Result<?> getDetail(Long id);
    
    /**
     * 更新地址
     * @param dto 地址请求DTO
     * @return 操作结果
     */
    Result<?> update(AddressRequestDTO dto);
    
    /**
     * 删除地址
     * @param id 地址ID
     * @return 操作结果
     */
    Result<?> delete(Long id);
    
    /**
     * 设置默认地址
     * @param id 地址ID
     * @return 操作结果
     */
    Result<?> setDefault(Long id);
    
    /**
     * 获取默认地址
     * @return 默认地址结果
     */
    Result<?> getDefault();
    
    /**
     * 获取地址详情DTO
     * @param userId 用户ID
     * @param addressId 地址ID
     * @return 地址响应DTO
     */
    AddressResponseDTO getAddressById(Long userId, Long addressId);
} 