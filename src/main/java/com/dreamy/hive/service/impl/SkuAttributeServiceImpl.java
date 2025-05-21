package com.dreamy.hive.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dreamy.hive.common.Result;
import com.dreamy.hive.entity.Attribute;
import com.dreamy.hive.entity.Sku;
import com.dreamy.hive.entity.SkuAttribute;
import com.dreamy.hive.mapper.SkuAttributeMapper;
import com.dreamy.hive.service.AttributeService;
import com.dreamy.hive.service.ProductValidationService;
import com.dreamy.hive.service.SkuAttributeService;
import com.dreamy.hive.service.SkuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * SKU属性关联服务实现类
 */
@Slf4j
@Service
public class SkuAttributeServiceImpl extends ServiceImpl<SkuAttributeMapper, SkuAttribute> implements SkuAttributeService {

    @Autowired
    @Lazy
    private SkuService skuService;
    
    @Autowired
    private AttributeService attributeService;
    
    @Autowired
    private ProductValidationService productValidationService;

    @Override
    public Result<List<SkuAttribute>> getAttributesBySkuId(Long skuId) {
        try {
            // 检查SKU是否存在
            Sku sku = productValidationService.validateSkuExists(skuId);
            if (sku == null) {
                return Result.fail("SKU不存在");
            }
            
            // 获取该SKU的所有属性
            List<SkuAttribute> attributeList = productValidationService.getSkuAttributes(skuId);
            return Result.success(attributeList);
        } catch (Exception e) {
            log.error("获取SKU属性列表失败", e);
            return Result.fail("获取SKU属性列表失败: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public Result<?> batchAddSkuAttributes(List<SkuAttribute> skuAttributes) {
        try {
            if (skuAttributes == null || skuAttributes.isEmpty()) {
                return Result.fail("属性列表不能为空");
            }
            
            // 验证SKU属性
            boolean valid = productValidationService.validateSkuAttributes(skuAttributes);
            if (!valid) {
                return Result.fail("SKU属性验证失败");
            }
            
            // 批量保存属性
            boolean success = this.saveBatch(skuAttributes);
            if (success) {
                return Result.success(skuAttributes);
            } else {
                return Result.fail("批量添加SKU属性失败");
            }
        } catch (Exception e) {
            log.error("批量添加SKU属性失败", e);
            return Result.fail("批量添加SKU属性失败: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public Result<?> deleteBySkuId(Long skuId) {
        try {
            // 检查SKU是否存在
            Sku sku = productValidationService.validateSkuExists(skuId);
            if (sku == null) {
                return Result.fail("SKU不存在");
            }
            
            // 删除该SKU的所有属性
            boolean deleted = productValidationService.deleteSkuAttributes(skuId);
            if (deleted) {
                return Result.success();
            } else {
                return Result.fail("删除SKU属性失败");
            }
        } catch (Exception e) {
            log.error("删除SKU属性失败", e);
            return Result.fail("删除SKU属性失败: " + e.getMessage());
        }
    }
} 