package com.dreamy.hive.service;

import com.dreamy.hive.entity.Attribute;
import com.dreamy.hive.entity.Sku;
import com.dreamy.hive.entity.SkuAttribute;
import com.dreamy.hive.entity.Spu;

import java.util.List;

/**
 * 商品验证服务接口
 */
public interface ProductValidationService {
    
    /**
     * 验证SPU是否存在
     * @param spuId SPU ID
     * @return 存在返回SPU对象，不存在返回null
     */
    Spu validateSpuExists(Long spuId);
    
    /**
     * 验证SKU是否存在
     * @param skuId SKU ID
     * @return 存在返回SKU对象，不存在返回null
     */
    Sku validateSkuExists(Long skuId);
    
    /**
     * 验证SKU编码是否已存在
     * @param skuCode SKU编码
     * @param excludeId 排除的SKU ID（用于更新时验证）
     * @return 存在返回true，不存在返回false
     */
    boolean isSkuCodeExists(String skuCode, Long excludeId);
    
    /**
     * 验证属性是否存在
     * @param attributeId 属性ID
     * @return 存在返回Attribute对象，不存在返回null
     */
    Attribute validateAttributeExists(Long attributeId);
    
    /**
     * 获取SKU的所有属性
     * @param skuId SKU ID
     * @return SKU属性列表
     */
    List<SkuAttribute> getSkuAttributes(Long skuId);
    
    /**
     * 验证SKU属性列表
     * @param skuAttributes SKU属性列表
     * @return 验证通过返回true，否则返回false
     */
    boolean validateSkuAttributes(List<SkuAttribute> skuAttributes);
    
    /**
     * 删除SKU的所有属性
     * @param skuId SKU ID
     * @return 删除成功返回true，否则返回false
     */
    boolean deleteSkuAttributes(Long skuId);
} 