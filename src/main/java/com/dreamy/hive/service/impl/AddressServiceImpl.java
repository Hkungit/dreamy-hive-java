package com.dreamy.hive.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dreamy.hive.common.Result;
import com.dreamy.hive.common.exception.BusinessException;
import com.dreamy.hive.dto.request.AddressRequestDTO;
import com.dreamy.hive.dto.response.AddressResponseDTO;
import com.dreamy.hive.entity.Address;
import com.dreamy.hive.mapper.AddressMapper;
import com.dreamy.hive.service.AddressService;
import com.dreamy.hive.util.UserContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {

    @Autowired
    private UserContext userContext;

    @Override
    @Transactional
    public Result<?> add(AddressRequestDTO dto) {
        Long userId = userContext.getCurrentUserId();

        // 创建地址对象
        Address address = new Address();
        BeanUtils.copyProperties(dto, address);
        address.setUserId(userId);

        // 如果设置为默认地址，先将其他地址设为非默认
        if (dto.getIsDefault() != null && dto.getIsDefault() == 1) {
            resetDefaultAddress(userId);
        }

        // 保存地址
        save(address);

        return Result.success();
    }

    @Override
    public Result<?> getAddressList() {
        Long userId = userContext.getCurrentUserId();

        // 查询用户的地址列表
        LambdaQueryWrapper<Address> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Address::getUserId, userId)
                .orderByDesc(Address::getIsDefault)
                .orderByDesc(Address::getCreateTime);

        List<Address> addressList = list(queryWrapper);

        // 转换为响应DTO
        List<AddressResponseDTO> responseDTOList = new ArrayList<>();
        for (Address address : addressList) {
            AddressResponseDTO responseDTO = convertToResponseDTO(address);
            responseDTOList.add(responseDTO);
        }

        return Result.success(responseDTOList);
    }

    @Override
    public Result<?> getDetail(Long id) {
        Long userId = userContext.getCurrentUserId();

        try {
            // 验证地址所有权
            Address address = validateAddressOwnership(id, userId);

            // 转换为响应DTO
            AddressResponseDTO responseDTO = convertToResponseDTO(address);

            return Result.success(responseDTO);
        } catch (BusinessException e) {
            return Result.fail(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Result<?> update(AddressRequestDTO dto) {
        Long userId = userContext.getCurrentUserId();

        try {
            // 验证地址所有权
            Address address = validateAddressOwnership(dto.getId(), userId);

            // 如果设置为默认地址，先将其他地址设为非默认
            if (dto.getIsDefault() != null && dto.getIsDefault() == 1) {
                resetDefaultAddress(userId);
            }

            // 更新地址
            BeanUtils.copyProperties(dto, address);
            updateById(address);

            return Result.success();
        } catch (BusinessException e) {
            return Result.fail(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Result<?> delete(Long id) {
        Long userId = userContext.getCurrentUserId();

        try {
            // 验证地址所有权
            validateAddressOwnership(id, userId);

            // 删除地址
            removeById(id);

            return Result.success();
        } catch (BusinessException e) {
            return Result.fail(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Result<?> setDefault(Long id) {
        Long userId = userContext.getCurrentUserId();

        try {
            // 验证地址所有权
            Address address = validateAddressOwnership(id, userId);

            // 先将所有地址设为非默认
            resetDefaultAddress(userId);

            // 设置当前地址为默认
            address.setIsDefault(1);
            updateById(address);

            return Result.success();
        } catch (BusinessException e) {
            return Result.fail(e.getMessage());
        }
    }

    @Override
    public Result<?> getDefault() {
        Long userId = userContext.getCurrentUserId();

        // 查询默认地址
        LambdaQueryWrapper<Address> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Address::getUserId, userId)
                .eq(Address::getIsDefault, 1);

        Address address = getOne(queryWrapper);

        // 如果没有默认地址，返回第一个地址
        if (address == null) {
            queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Address::getUserId, userId)
                    .orderByDesc(Address::getCreateTime)
                    .last("LIMIT 1");

            address = getOne(queryWrapper);
        }

        if (address == null) {
            return Result.success(null);
        }

        // 转换为响应DTO
        AddressResponseDTO responseDTO = convertToResponseDTO(address);

        return Result.success(responseDTO);
    }

    /**
     * 重置用户的默认地址
     * @param userId 用户ID
     */
    private void resetDefaultAddress(Long userId) {
        LambdaUpdateWrapper<Address> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Address::getUserId, userId)
                .eq(Address::getIsDefault, 1)
                .set(Address::getIsDefault, 0);

        update(updateWrapper);
    }

    /**
     * 将地址实体转换为响应DTO
     * @param address 地址实体
     * @return 地址响应DTO
     */
    private AddressResponseDTO convertToResponseDTO(Address address) {
        AddressResponseDTO responseDTO = new AddressResponseDTO();
        BeanUtils.copyProperties(address, responseDTO);

        // 拼接完整地址
        String fullAddress = address.getProvince() + " " + address.getCity() + " " +
                address.getDistrict() + " " + address.getDetail();
        responseDTO.setFullAddress(fullAddress);

        return responseDTO;
    }

    /**
     * 验证地址是否存在且属于当前用户
     * @param addressId 地址ID
     * @param userId 用户ID
     * @return 地址对象
     * @throws BusinessException 如果地址不存在或不属于用户
     */
    private Address validateAddressOwnership(Long addressId, Long userId) {
        // 检查地址是否存在
        Address address = getById(addressId);
        if (address == null) {
            throw new BusinessException("地址不存在");
        }

        // 检查是否是当前用户的地址
        if (!address.getUserId().equals(userId)) {
            throw new BusinessException("无权操作此地址");
        }

        return address;
    }

    /**
     * 获取地址详情DTO
     * @param userId 用户ID
     * @param addressId 地址ID
     * @return 地址响应DTO
     */
    public AddressResponseDTO getAddressById(Long userId, Long addressId) {
        // 验证地址所有权
        Address address = validateAddressOwnership(addressId, userId);

        // 转换为响应DTO
        return convertToResponseDTO(address);
    }
}