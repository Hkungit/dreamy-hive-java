package com.dreamy.hive.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dreamy.hive.common.Result;
import com.dreamy.hive.entity.ProductImage;
import com.dreamy.hive.entity.Sku;
import com.dreamy.hive.entity.Spu;
import com.dreamy.hive.mapper.ProductImageMapper;
import com.dreamy.hive.service.FileStorageService;
import com.dreamy.hive.service.ProductImageService;
import com.dreamy.hive.service.SkuService;
import com.dreamy.hive.service.SpuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品图片服务实现类
 */
@Slf4j
@Service
public class ProductImageServiceImpl extends ServiceImpl<ProductImageMapper, ProductImage> implements ProductImageService {

    @Autowired
    private SpuService spuService;
    
    @Autowired
    private SkuService skuService;
    
    @Autowired
    private FileStorageService fileStorageService;

    @Override
    public Result<List<ProductImage>> getImagesBySpuId(Long spuId) {
        try {
            // 检查SPU是否存在
            Spu spu = spuService.getById(spuId);
            if (spu == null) {
                return Result.fail("商品不存在");
            }
            
            // 查询该SPU的所有图片
            LambdaQueryWrapper<ProductImage> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(ProductImage::getSpuId, spuId)
                    .isNull(ProductImage::getSkuId) // 只查询SPU图片，不包含SKU图片
                    .orderByAsc(ProductImage::getSort);
            
            List<ProductImage> imageList = this.list(queryWrapper);
            return Result.success(imageList);
        } catch (Exception e) {
            log.error("获取商品图片列表失败", e);
            return Result.fail("获取商品图片列表失败: " + e.getMessage());
        }
    }

    @Override
    public Result<List<ProductImage>> getImagesBySpuIdAndType(Long spuId, Integer type) {
        try {
            // 检查SPU是否存在
            Spu spu = spuService.getById(spuId);
            if (spu == null) {
                return Result.fail("商品不存在");
            }
            
            // 查询该SPU指定类型的图片
            LambdaQueryWrapper<ProductImage> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(ProductImage::getSpuId, spuId)
                    .eq(ProductImage::getType, type)
                    .isNull(ProductImage::getSkuId) // 只查询SPU图片，不包含SKU图片
                    .orderByAsc(ProductImage::getSort);
            
            List<ProductImage> imageList = this.list(queryWrapper);
            return Result.success(imageList);
        } catch (Exception e) {
            log.error("获取商品图片列表失败", e);
            return Result.fail("获取商品图片列表失败: " + e.getMessage());
        }
    }

    @Override
    public Result<List<ProductImage>> getImagesBySkuId(Long skuId) {
        try {
            // 检查SKU是否存在
            Sku sku = skuService.getById(skuId);
            if (sku == null) {
                return Result.fail("SKU不存在");
            }
            
            // 查询该SKU的所有图片
            LambdaQueryWrapper<ProductImage> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(ProductImage::getSkuId, skuId)
                    .orderByAsc(ProductImage::getSort);
            
            List<ProductImage> imageList = this.list(queryWrapper);
            return Result.success(imageList);
        } catch (Exception e) {
            log.error("获取SKU图片列表失败", e);
            return Result.fail("获取SKU图片列表失败: " + e.getMessage());
        }
    }

    @Override
    public Result<?> addProductImage(ProductImage productImage) {
        try {
            // 验证SPU或SKU是否存在
            if (productImage.getSpuId() != null) {
                Spu spu = spuService.getById(productImage.getSpuId());
                if (spu == null) {
                    return Result.fail("商品不存在");
                }
            } else if (productImage.getSkuId() != null) {
                Sku sku = skuService.getById(productImage.getSkuId());
                if (sku == null) {
                    return Result.fail("SKU不存在");
                }
            } else {
                return Result.fail("SPU ID和SKU ID不能同时为空");
            }
            
            // 设置默认值
            if (productImage.getSort() == null) {
                productImage.setSort(0);
            }
            
            // 保存图片信息
            boolean success = this.save(productImage);
            if (success) {
                return Result.success(productImage);
            } else {
                return Result.fail("添加商品图片失败");
            }
        } catch (Exception e) {
            log.error("添加商品图片失败", e);
            return Result.fail("添加商品图片失败: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public Result<?> batchAddProductImages(List<ProductImage> productImages) {
        try {
            if (productImages == null || productImages.isEmpty()) {
                return Result.fail("图片列表不能为空");
            }
            
            // 验证SPU或SKU是否存在
            Long spuId = productImages.get(0).getSpuId();
            Long skuId = productImages.get(0).getSkuId();
            
            if (spuId != null) {
                Spu spu = spuService.getById(spuId);
                if (spu == null) {
                    return Result.fail("商品不存在");
                }
                
                // 检查所有图片的SPU ID是否一致
                for (ProductImage image : productImages) {
                    if (!spuId.equals(image.getSpuId())) {
                        return Result.fail("所有图片必须属于同一个商品");
                    }
                }
            } else if (skuId != null) {
                Sku sku = skuService.getById(skuId);
                if (sku == null) {
                    return Result.fail("SKU不存在");
                }
                
                // 检查所有图片的SKU ID是否一致
                for (ProductImage image : productImages) {
                    if (!skuId.equals(image.getSkuId())) {
                        return Result.fail("所有图片必须属于同一个SKU");
                    }
                }
            } else {
                return Result.fail("SPU ID和SKU ID不能同时为空");
            }
            
            // 设置默认值
            for (int i = 0; i < productImages.size(); i++) {
                ProductImage image = productImages.get(i);
                if (image.getSort() == null) {
                    image.setSort(i);
                }
            }
            
            // 批量保存图片信息
            boolean success = this.saveBatch(productImages);
            if (success) {
                return Result.success(productImages);
            } else {
                return Result.fail("批量添加商品图片失败");
            }
        } catch (Exception e) {
            log.error("批量添加商品图片失败", e);
            return Result.fail("批量添加商品图片失败: " + e.getMessage());
        }
    }

    @Override
    public Result<List<ProductImage>> getProductImagesBySpuId(Long spuId, Integer type) {
        try {
            // 检查SPU是否存在
            Spu spu = spuService.getById(spuId);
            if (spu == null) {
                return Result.fail("商品不存在");
            }
            
            // 查询该SPU的所有图片
            LambdaQueryWrapper<ProductImage> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(ProductImage::getSpuId, spuId);
            
            // 如果指定了类型，则按类型过滤
            if (type != null) {
                queryWrapper.eq(ProductImage::getType, type);
            }
            
            // 按排序字段排序
            queryWrapper.orderByAsc(ProductImage::getSort);
            
            List<ProductImage> imageList = this.list(queryWrapper);
            return Result.success(imageList);
        } catch (Exception e) {
            log.error("获取商品图片列表失败", e);
            return Result.fail("获取商品图片列表失败: " + e.getMessage());
        }
    }

    @Override
    public Result<List<ProductImage>> getProductImagesBySkuId(Long skuId) {
        try {
            // 检查SKU是否存在
            Sku sku = skuService.getById(skuId);
            if (sku == null) {
                return Result.fail("SKU不存在");
            }
            
            // 查询该SKU的所有图片
            LambdaQueryWrapper<ProductImage> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(ProductImage::getSkuId, skuId)
                    .orderByAsc(ProductImage::getSort);
            
            List<ProductImage> imageList = this.list(queryWrapper);
            return Result.success(imageList);
        } catch (Exception e) {
            log.error("获取SKU图片列表失败", e);
            return Result.fail("获取SKU图片列表失败: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public Result<?> deleteProductImage(Long id) {
        try {
            // 检查图片是否存在
            ProductImage image = this.getById(id);
            if (image == null) {
                return Result.fail("图片不存在");
            }
            
            // 删除图片
            boolean success = this.removeById(id);
            if (success) {
                // 尝试删除实际文件（如果URL包含文件路径）
                String url = image.getUrl();
                if (url != null && !url.isEmpty()) {
                    // 尝试从URL中提取文件名和目录
                    try {
                        String[] parts = url.split("/files/");
                        if (parts.length > 1) {
                            String pathPart = parts[1];
                            int slashIndex = pathPart.indexOf('/');
                            if (slashIndex != -1) {
                                String directory = pathPart.substring(0, slashIndex);
                                String filename = pathPart.substring(slashIndex + 1);
                                fileStorageService.deleteFile(filename, directory);
                            }
                        }
                    } catch (Exception e) {
                        log.warn("删除文件失败，但图片记录已删除: {}", e.getMessage());
                    }
                }
                
                return Result.success();
            } else {
                return Result.fail("删除图片失败");
            }
        } catch (Exception e) {
            log.error("删除图片失败", e);
            return Result.fail("删除图片失败: " + e.getMessage());
        }
    }

    @Override
    public Result<?> updateProductImage(Long id, ProductImage productImage) {
        try {
            // 检查图片是否存在
            ProductImage existingImage = this.getById(id);
            if (existingImage == null) {
                return Result.fail("商品图片不存在");
            }
            
            // 不允许修改SPU ID和SKU ID
            productImage.setSpuId(existingImage.getSpuId());
            productImage.setSkuId(existingImage.getSkuId());
            
            // 设置ID
            productImage.setId(id);
            
            // 更新图片
            boolean success = this.updateById(productImage);
            if (success) {
                return Result.success();
            } else {
                return Result.fail("更新商品图片失败");
            }
        } catch (Exception e) {
            log.error("更新商品图片失败", e);
            return Result.fail("更新商品图片失败: " + e.getMessage());
        }
    }

    @Override
    public Result<?> updateProductImageSort(Long id, Integer sort) {
        try {
            // 检查图片是否存在
            ProductImage image = this.getById(id);
            if (image == null) {
                return Result.fail("图片不存在");
            }
            
            // 更新排序
            ProductImage updateImage = new ProductImage();
            updateImage.setId(id);
            updateImage.setSort(sort);
            
            boolean success = this.updateById(updateImage);
            if (success) {
                return Result.success();
            } else {
                return Result.fail("更新图片排序失败");
            }
        } catch (Exception e) {
            log.error("更新图片排序失败", e);
            return Result.fail("更新图片排序失败: " + e.getMessage());
        }
    }
} 