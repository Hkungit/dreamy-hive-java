package com.dreamy.hive.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dreamy.hive.common.Result;
import com.dreamy.hive.entity.ProductImage;

import java.util.List;

/**
 * 商品图片服务接口
 */
public interface ProductImageService extends IService<ProductImage> {

    Result<List<ProductImage>> getImagesBySpuId(Long spuId);

    Result<List<ProductImage>> getImagesBySpuIdAndType(Long spuId, Integer type);

    Result<List<ProductImage>> getImagesBySkuId(Long skuId);

    /**
     * 添加商品图片
     * @param productImage 商品图片信息
     * @return 操作结果
     */
    Result<?> addProductImage(ProductImage productImage);
    
    /**
     * 批量添加商品图片
     * @param productImages 商品图片列表
     * @return 操作结果
     */
    Result<?> batchAddProductImages(List<ProductImage> productImages);
    
    /**
     * 根据SPU ID获取商品图片列表
     * @param spuId SPU ID
     * @param type 图片类型（可选）：1-主图，2-详情图
     * @return 商品图片列表
     */
    Result<List<ProductImage>> getProductImagesBySpuId(Long spuId, Integer type);
    
    /**
     * 根据SKU ID获取SKU图片列表
     * @param skuId SKU ID
     * @return SKU图片列表
     */
    Result<List<ProductImage>> getProductImagesBySkuId(Long skuId);
    
    /**
     * 删除商品图片
     * @param id 图片ID
     * @return 操作结果
     */
    Result<?> deleteProductImage(Long id);

    Result<?> updateProductImage(Long id, ProductImage productImage);

    /**
     * 更新商品图片排序
     * @param id 图片ID
     * @param sort 排序值
     * @return 操作结果
     */
    Result<?> updateProductImageSort(Long id, Integer sort);
} 