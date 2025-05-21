package com.dreamy.hive.dto.response;

import lombok.Data;

import java.util.Date;

/**
 * 地址响应DTO
 */
@Data
public class AddressResponseDTO {
    /**
     * 地址ID
     */
    private Long id;
    
    /**
     * 用户ID
     */
    private Long userId;
    
    /**
     * 收货人
     */
    private String receiver;
    
    /**
     * 收货人电话
     */
    private String phone;
    
    /**
     * 省
     */
    private String province;
    
    /**
     * 市
     */
    private String city;
    
    /**
     * 区/县
     */
    private String district;
    
    /**
     * 详细地址
     */
    private String detail;
    
    /**
     * 是否默认地址（0-否，1-是）
     */
    private Integer isDefault;
    
    /**
     * 完整地址（省市区详细地址）
     */
    private String fullAddress;
    
    /**
     * 创建时间
     */
    private Date createTime;
} 