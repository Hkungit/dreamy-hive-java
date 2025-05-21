package com.dreamy.hive.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dreamy.hive.common.Result;
import com.dreamy.hive.dto.request.SkuWithAttributesDTO;
import com.dreamy.hive.entity.Sku;
import com.dreamy.hive.entity.Spu;
import com.dreamy.hive.entity.SkuAttribute;
import com.dreamy.hive.mapper.SkuMapper;
import com.dreamy.hive.service.SkuAttributeService;
import com.dreamy.hive.service.SkuService;
import com.dreamy.hive.service.ProductValidationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品SKU服务实现类
 */
@Slf4j
@Service
public class SkuServiceImpl extends ServiceImpl<SkuMapper, Sku> implements SkuService {

    @Autowired
    @Lazy
    private SkuAttributeService skuAttributeService;
    
    @Autowired
    private ProductValidationService productValidationService;

    @Override
    public Result<List<Sku>> getSkusBySpuId(Long spuId) {
        try {
            // 检查SPU是否存在
            Spu spu = productValidationService.validateSpuExists(spuId);
            if (spu == null) {
                return Result.fail("商品不存在");
            }
            
            // 查询该SPU下的所有SKU
            LambdaQueryWrapper<Sku> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Sku::getSpuId, spuId)
                    .eq(Sku::getStatus, 1) // 只查询启用状态的SKU
                    .orderByAsc(Sku::getSort);
            
            List<Sku> skuList = this.list(queryWrapper);
            return Result.success(skuList);
        } catch (Exception e) {
            log.error("获取SKU列表失败", e);
            return Result.fail("获取SKU列表失败: " + e.getMessage());
        }
    }

    @Override
    public Result<Sku> getSkuDetail(Long id) {
        try {
            Sku sku = this.getById(id);
            if (sku == null) {
                return Result.fail("SKU不存在");
            }
            return Result.success(sku);
        } catch (Exception e) {
            log.error("获取SKU详情失败", e);
            return Result.fail("获取SKU详情失败: " + e.getMessage());
        }
    }
    
    @Override
    public Result<SkuWithAttributesDTO> getSkuWithAttributes(Long id) {
        try {
            // 获取SKU信息
            Sku sku = this.getById(id);
            if (sku == null) {
                return Result.fail("SKU不存在");
            }
            
            // 获取SKU属性
            Result<List<SkuAttribute>> attributesResult = skuAttributeService.getAttributesBySkuId(id);
            if (!attributesResult.isSuccess()) {
                return Result.fail(attributesResult.getMessage());
            }
            
            // 组装返回数据
            SkuWithAttributesDTO skuWithAttributes = new SkuWithAttributesDTO();
            skuWithAttributes.setSku(sku);
            skuWithAttributes.setAttributes(attributesResult.getData());
            
            return Result.success(skuWithAttributes);
        } catch (Exception e) {
            log.error("获取SKU及属性失败", e);
            return Result.fail("获取SKU及属性失败: " + e.getMessage());
        }
    }

    @Override
    public Result<?> addSku(Sku sku) {
        try {
            // 检查SPU是否存在
            Spu spu = productValidationService.validateSpuExists(sku.getSpuId());
            if (spu == null) {
                return Result.fail("商品不存在");
            }
            
            // 检查SKU编码是否已存在
            if (productValidationService.isSkuCodeExists(sku.getSkuCode(), null)) {
                return Result.fail("SKU编码已存在");
            }
            
            // 设置默认值
            if (sku.getStatus() == null) {
                sku.setStatus(1); // 默认启用
            }
            
            // 设置默认排序值
            if (sku.getSort() == null) {
                sku.setSort(0); // 默认排序值为0
            }
            
            // 保存SKU
            boolean success = this.save(sku);
            if (success) {
                return Result.success(sku);
            } else {
                return Result.fail("添加SKU失败");
            }
        } catch (Exception e) {
            log.error("添加SKU失败", e);
            return Result.fail("添加SKU失败: " + e.getMessage());
        }
    }
    
    @Override
    @Transactional
    public Result<?> addSkuWithAttributes(SkuWithAttributesDTO skuWithAttributes) {
        try {
            // 先添加SKU
            Result<?> skuResult = this.addSku(skuWithAttributes.getSku());
            if (!skuResult.isSuccess()) {
                return skuResult;
            }
            
            Sku sku = (Sku) skuResult.getData();
            
            // 如果有属性，则添加属性
            List<SkuAttribute> attributes = skuWithAttributes.getAttributes();
            if (attributes != null && !attributes.isEmpty()) {
                // 设置SKU ID
                for (SkuAttribute attribute : attributes) {
                    attribute.setSkuId(sku.getId());
                }
                
                // 批量添加属性
                Result<?> attributeResult = skuAttributeService.batchAddSkuAttributes(attributes);
                if (!attributeResult.isSuccess()) {
                    return attributeResult;
                }
            }
            
            return Result.success(skuWithAttributes);
        } catch (Exception e) {
            log.error("添加SKU及属性失败", e);
            return Result.fail("添加SKU及属性失败: " + e.getMessage());
        }
    }

    @Override
    public Result<?> updateSku(Long id, Sku sku) {
        try {
            // 检查SKU是否存在
            Sku existingSku = productValidationService.validateSkuExists(id);
            if (existingSku == null) {
                return Result.fail("SKU不存在");
            }
            
            // 检查SPU是否存在
            Spu spu = productValidationService.validateSpuExists(sku.getSpuId());
            if (spu == null) {
                return Result.fail("商品不存在");
            }
            
            // 检查SKU编码是否已存在（排除自身）
            if (!existingSku.getSkuCode().equals(sku.getSkuCode()) && 
                productValidationService.isSkuCodeExists(sku.getSkuCode(), id)) {
                return Result.fail("SKU编码已存在");
            }
            
            // 设置ID
            sku.setId(id);
            
            // 更新SKU
            boolean success = this.updateById(sku);
            if (success) {
                return Result.success();
            } else {
                return Result.fail("更新SKU失败");
            }
        } catch (Exception e) {
            log.error("更新SKU失败", e);
            return Result.fail("更新SKU失败: " + e.getMessage());
        }
    }
    
    @Override
    @Transactional
    public Result<?> updateSkuWithAttributes(Long id, SkuWithAttributesDTO skuWithAttributes) {
        try {
            // 先更新SKU
            Sku sku = skuWithAttributes.getSku();
            sku.setId(id);
            Result<?> skuResult = this.updateSku(id, sku);
            if (!skuResult.isSuccess()) {
                return skuResult;
            }
            
            // 删除原有属性
            boolean attributeDeleted = productValidationService.deleteSkuAttributes(id);
            if (!attributeDeleted) {
                return Result.fail("删除SKU属性失败");
            }
            
            // 如果有新属性，则添加
            List<SkuAttribute> attributes = skuWithAttributes.getAttributes();
            if (attributes != null && !attributes.isEmpty()) {
                // 设置SKU ID
                for (SkuAttribute attribute : attributes) {
                    attribute.setSkuId(id);
                }
                
                // 批量添加属性
                Result<?> attributeResult = skuAttributeService.batchAddSkuAttributes(attributes);
                if (!attributeResult.isSuccess()) {
                    return attributeResult;
                }
            }
            
            return Result.success();
        } catch (Exception e) {
            log.error("更新SKU及属性失败", e);
            return Result.fail("更新SKU及属性失败: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public Result<?> deleteSku(Long id) {
        try {
            // 检查SKU是否存在
            Sku existingSku = productValidationService.validateSkuExists(id);
            if (existingSku == null) {
                return Result.fail("SKU不存在");
            }
            
            // 删除SKU属性
            boolean attributeDeleted = productValidationService.deleteSkuAttributes(id);
            if (!attributeDeleted) {
                return Result.fail("删除SKU属性失败");
            }
            
            // 删除SKU
            boolean success = this.removeById(id);
            if (success) {
                return Result.success();
            } else {
                return Result.fail("删除SKU失败");
            }
        } catch (Exception e) {
            log.error("删除SKU失败", e);
            return Result.fail("删除SKU失败: " + e.getMessage());
        }
    }

    @Override
    public Result<?> updateSkuStatus(Long id, Integer status) {
        try {
            // 检查SKU是否存在
            Sku existingSku = this.getById(id);
            if (existingSku == null) {
                return Result.fail("SKU不存在");
            }
            
            // 检查状态值是否合法
            if (status != 0 && status != 1) {
                return Result.fail("无效的SKU状态");
            }
            
            // 更新状态
            Sku sku = new Sku();
            sku.setId(id);
            sku.setStatus(status);
            
            boolean success = this.updateById(sku);
            if (success) {
                return Result.success();
            } else {
                return Result.fail("更新SKU状态失败");
            }
        } catch (Exception e) {
            log.error("更新SKU状态失败", e);
            return Result.fail("更新SKU状态失败: " + e.getMessage());
        }
    }
} 