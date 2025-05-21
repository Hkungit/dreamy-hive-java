package com.dreamy.hive.dto.response;

import com.dreamy.hive.entity.Category;
import com.dreamy.hive.entity.ProductImage;
import com.dreamy.hive.entity.Sku;
import com.dreamy.hive.entity.Spu;
import lombok.Data;

import java.util.List;

/**
 * 商品详情页DTO
 */
@Data
public class ProductDetailDTO {
    /**
     * SPU信息
     */
    private Spu spu;
    
    /**
     * 分类信息
     */
    private Category category;
    
    /**
     * SKU列表
     */
    private List<Sku> skuList;
    
    /**
     * 轮播图列表（主图）
     */
    private List<ProductImage> carouselImages;
    
    /**
     * 详情图列表
     */
    private List<ProductImage> detailImages;
} 