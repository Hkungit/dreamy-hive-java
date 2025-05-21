package com.dreamy.hive.dto.request;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Data
public class AddressRequestDTO {
    /**
     * 地址ID，新增时不需要传
     */
    private Long id;
    
    /**
     * 收货人
     */
    @NotBlank(message = "收货人不能为空")
    @Size(max = 50, message = "收货人长度不能超过50个字符")
    private String receiver;
    
    /**
     * 收货人电话
     */
    @NotBlank(message = "收货人电话不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号码格式不正确")
    private String phone;
    
    /**
     * 省
     */
    @NotBlank(message = "省份不能为空")
    @Size(max = 50, message = "省份长度不能超过50个字符")
    private String province;
    
    /**
     * 市
     */
    @NotBlank(message = "城市不能为空")
    @Size(max = 50, message = "城市长度不能超过50个字符")
    private String city;
    
    /**
     * 区/县
     */
    @NotBlank(message = "区/县不能为空")
    @Size(max = 50, message = "区/县长度不能超过50个字符")
    private String district;
    
    /**
     * 详细地址
     */
    @NotBlank(message = "详细地址不能为空")
    @Size(max = 255, message = "详细地址长度不能超过255个字符")
    private String detail;
    
    /**
     * 是否默认地址（0-否，1-是）
     */
    private Integer isDefault;
} 