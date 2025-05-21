package com.dreamy.hive.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dreamy.hive.common.Result;
import com.dreamy.hive.dto.request.SkuWithAttributesDTO;
import com.dreamy.hive.entity.Sku;

import java.util.List;

/**
 * 商品SKU服务接口
 */
public interface SkuService extends IService<Sku> {
    
    /**
     * 根据SPU ID获取SKU列表
     * @param spuId SPU ID
     * @return SKU列表
     */
    Result<List<Sku>> getSkusBySpuId(Long spuId);
    
    /**
     * 获取SKU详情
     * @param id SKU ID
     * @return SKU详情
     */
    Result<Sku> getSkuDetail(Long id);
    
    /**
     * 添加SKU
     * @param sku SKU信息
     * @return 操作结果
     */
    Result<?> addSku(Sku sku);
    
    /**
     * 添加SKU及其属性
     * @param skuWithAttributes SKU及属性信息
     * @return 操作结果
     */
    Result<?> addSkuWithAttributes(SkuWithAttributesDTO skuWithAttributes);
    
    /**
     * 更新SKU
     * @param id SKU ID
     * @param sku SKU信息
     * @return 操作结果
     */
    Result<?> updateSku(Long id, Sku sku);
    
    /**
     * 更新SKU及其属性
     * @param id SKU ID
     * @param skuWithAttributes SKU及属性信息
     * @return 操作结果
     */
    Result<?> updateSkuWithAttributes(Long id, SkuWithAttributesDTO skuWithAttributes);
    
    /**
     * 删除SKU
     * @param id SKU ID
     * @return 操作结果
     */
    Result<?> deleteSku(Long id);
    
    /**
     * 更新SKU状态
     * @param id SKU ID
     * @param status 状态（0-禁用，1-启用）
     * @return 操作结果
     */
    Result<?> updateSkuStatus(Long id, Integer status);
    
    /**
     * 获取SKU及其属性
     * @param id SKU ID
     * @return SKU及属性信息
     */
    Result<SkuWithAttributesDTO> getSkuWithAttributes(Long id);
} 