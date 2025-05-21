package com.dreamy.hive.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dreamy.hive.entity.Attribute;
import com.dreamy.hive.entity.Sku;
import com.dreamy.hive.entity.SkuAttribute;
import com.dreamy.hive.entity.Spu;
import com.dreamy.hive.mapper.AttributeMapper;
import com.dreamy.hive.mapper.SkuAttributeMapper;
import com.dreamy.hive.mapper.SkuMapper;
import com.dreamy.hive.mapper.SpuMapper;
import com.dreamy.hive.service.ProductValidationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品验证服务实现类
 */
@Slf4j
@Service
public class ProductValidationServiceImpl implements ProductValidationService {

    @Autowired
    private SpuMapper spuMapper;
    
    @Autowired
    private SkuMapper skuMapper;
    
    @Autowired
    private AttributeMapper attributeMapper;
    
    @Autowired
    private SkuAttributeMapper skuAttributeMapper;

    @Override
    public Spu validateSpuExists(Long spuId) {
        if (spuId == null) {
            return null;
        }
        return spuMapper.selectById(spuId);
    }

    @Override
    public Sku validateSkuExists(Long skuId) {
        if (skuId == null) {
            return null;
        }
        return skuMapper.selectById(skuId);
    }

    @Override
    public boolean isSkuCodeExists(String skuCode, Long excludeId) {
        LambdaQueryWrapper<Sku> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Sku::getSkuCode, skuCode);
        
        if (excludeId != null) {
            queryWrapper.ne(Sku::getId, excludeId);
        }
        
        return skuMapper.selectCount(queryWrapper) > 0;
    }
    
    @Override
    public Attribute validateAttributeExists(Long attributeId) {
        if (attributeId == null) {
            return null;
        }
        return attributeMapper.selectById(attributeId);
    }
    
    @Override
    public List<SkuAttribute> getSkuAttributes(Long skuId) {
        LambdaQueryWrapper<SkuAttribute> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SkuAttribute::getSkuId, skuId)
                .orderByAsc(SkuAttribute::getId);
        
        return skuAttributeMapper.selectList(queryWrapper);
    }
    
    @Override
    public boolean validateSkuAttributes(List<SkuAttribute> skuAttributes) {
        if (skuAttributes == null || skuAttributes.isEmpty()) {
            return false;
        }
        
        Long skuId = skuAttributes.get(0).getSkuId();
        
        // 检查SKU是否存在
        Sku sku = validateSkuExists(skuId);
        if (sku == null) {
            return false;
        }
        
        // 检查所有属性的SKU ID是否一致
        for (SkuAttribute attribute : skuAttributes) {
            if (!skuId.equals(attribute.getSkuId())) {
                return false;
            }
            
            // 检查属性是否存在
            Attribute existingAttribute = validateAttributeExists(attribute.getAttributeId());
            if (existingAttribute == null) {
                return false;
            }
        }
        
        return true;
    }
    
    @Override
    @Transactional
    public boolean deleteSkuAttributes(Long skuId) {
        if (skuId == null) {
            return false;
        }
        
        // 检查SKU是否存在
        Sku sku = validateSkuExists(skuId);
        if (sku == null) {
            return false;
        }
        
        // 删除该SKU的所有属性
        LambdaQueryWrapper<SkuAttribute> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SkuAttribute::getSkuId, skuId);
        
        int result = skuAttributeMapper.delete(queryWrapper);
        return result >= 0; // 即使没有记录被删除，也认为是成功的
    }
} 