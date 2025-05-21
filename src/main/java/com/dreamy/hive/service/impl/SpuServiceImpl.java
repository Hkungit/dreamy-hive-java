package com.dreamy.hive.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dreamy.hive.common.Result;
import com.dreamy.hive.dto.response.ProductDetailDTO;
import com.dreamy.hive.entity.Category;
import com.dreamy.hive.entity.ProductImage;
import com.dreamy.hive.entity.Sku;
import com.dreamy.hive.entity.Spu;
import com.dreamy.hive.mapper.SpuMapper;
import com.dreamy.hive.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 商品SPU服务实现类
 */
@Slf4j
@Service
public class SpuServiceImpl extends ServiceImpl<SpuMapper, Spu> implements SpuService {

    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private SkuService skuService;
    
    @Autowired
    private ProductImageService productImageService;
    
    @Autowired
    private ProductValidationService productValidationService;

    @Override
    public Result<Page<Spu>> listSpusByPage(int pageNum, int pageSize, String name, Long categoryId, Integer status) {
        try {
            Page<Spu> page = new Page<>(pageNum, pageSize);
            LambdaQueryWrapper<Spu> queryWrapper = new LambdaQueryWrapper<>();
            
            // 添加查询条件（如果有）
            if (StringUtils.hasText(name)) {
                queryWrapper.like(Spu::getName, name);
            }
            
            if (categoryId != null) {
                queryWrapper.eq(Spu::getCategoryId, categoryId);
            }
            
            if (status != null) {
                queryWrapper.eq(Spu::getStatus, status);
            }
            
            // 按创建时间降序排序
            queryWrapper.orderByDesc(Spu::getCreateTime);
            
            // 执行查询
            Page<Spu> spuPage = this.page(page, queryWrapper);
            return Result.success(spuPage);
        } catch (Exception e) {
            log.error("分页查询商品列表失败", e);
            return Result.fail("分页查询商品列表失败: " + e.getMessage());
        }
    }

    @Override
    public Result<Spu> getSpuDetail(Long id) {
        try {
            Spu spu = productValidationService.validateSpuExists(id);
            if (spu == null) {
                return Result.fail("商品不存在");
            }
            return Result.success(spu);
        } catch (Exception e) {
            log.error("获取商品详情失败", e);
            return Result.fail("获取商品详情失败: " + e.getMessage());
        }
    }
    
    @Override
    public Result<ProductDetailDTO> getProductDetail(Long id) {
        try {
            // 获取SPU信息
            Spu spu = productValidationService.validateSpuExists(id);
            if (spu == null) {
                return Result.fail("商品不存在");
            }
            
            // 获取分类信息
            Category category = categoryService.getById(spu.getCategoryId());
            if (category == null) {
                return Result.fail("商品分类不存在");
            }
            
            // 获取SKU列表
            Result<List<Sku>> skuResult = skuService.getSkusBySpuId(id);
            if (!skuResult.isSuccess()) {
                return Result.fail(skuResult.getMessage());
            }
            List<Sku> skuList = skuResult.getData();
            
            // 获取轮播图列表（主图）
            Result<List<ProductImage>> carouselResult = productImageService.getProductImagesBySpuId(id, 1);
            if (!carouselResult.isSuccess()) {
                return Result.fail(carouselResult.getMessage());
            }
            List<ProductImage> carouselImages = carouselResult.getData();
            
            // 获取详情图列表
            Result<List<ProductImage>> detailResult = productImageService.getProductImagesBySpuId(id, 2);
            if (!detailResult.isSuccess()) {
                return Result.fail(detailResult.getMessage());
            }
            List<ProductImage> detailImages = detailResult.getData();
            
            // 组装商品详情页DTO
            ProductDetailDTO productDetailDTO = new ProductDetailDTO();
            productDetailDTO.setSpu(spu);
            productDetailDTO.setCategory(category);
            productDetailDTO.setSkuList(skuList);
            productDetailDTO.setCarouselImages(carouselImages);
            productDetailDTO.setDetailImages(detailImages);
            
            return Result.success(productDetailDTO);
        } catch (Exception e) {
            log.error("获取商品详情页失败", e);
            return Result.fail("获取商品详情页失败: " + e.getMessage());
        }
    }

    @Override
    public Result<?> addSpu(Spu spu) {
        try {
            // 检查分类是否存在
            Category category = categoryService.getById(spu.getCategoryId());
            if (category == null) {
                return Result.fail("商品分类不存在");
            }
            
            // 设置默认值
            if (spu.getStatus() == null) {
                spu.setStatus(0); // 默认下架状态
            }
            
            // 保存商品
            boolean success = this.save(spu);
            if (success) {
                return Result.success(spu);
            } else {
                return Result.fail("添加商品失败");
            }
        } catch (Exception e) {
            log.error("添加商品失败", e);
            return Result.fail("添加商品失败: " + e.getMessage());
        }
    }

    @Override
    public Result<?> updateSpu(Long id, Spu spu) {
        try {
            // 检查商品是否存在
            Spu existingSpu = productValidationService.validateSpuExists(id);
            if (existingSpu == null) {
                return Result.fail("商品不存在");
            }
            
            // 检查分类是否存在
            Category category = categoryService.getById(spu.getCategoryId());
            if (category == null) {
                return Result.fail("商品分类不存在");
            }
            
            // 设置ID
            spu.setId(id);
            
            // 更新商品
            boolean success = this.updateById(spu);
            if (success) {
                return Result.success();
            } else {
                return Result.fail("更新商品失败");
            }
        } catch (Exception e) {
            log.error("更新商品失败", e);
            return Result.fail("更新商品失败: " + e.getMessage());
        }
    }

    @Override
    public Result<?> deleteSpu(Long id) {
        try {
            // 检查商品是否存在
            Spu existingSpu = productValidationService.validateSpuExists(id);
            if (existingSpu == null) {
                return Result.fail("商品不存在");
            }
            
            // 检查是否有关联的SKU
            Result<List<Sku>> skuResult = skuService.getSkusBySpuId(id);
            if (skuResult.isSuccess() && !skuResult.getData().isEmpty()) {
                return Result.fail("该商品下存在SKU，无法删除");
            }
            
            // 删除商品
            boolean success = this.removeById(id);
            if (success) {
                return Result.success();
            } else {
                return Result.fail("删除商品失败");
            }
        } catch (Exception e) {
            log.error("删除商品失败", e);
            return Result.fail("删除商品失败: " + e.getMessage());
        }
    }

    @Override
    public Result<?> updateSpuStatus(Long id, Integer status) {
        try {
            // 检查商品是否存在
            Spu existingSpu = productValidationService.validateSpuExists(id);
            if (existingSpu == null) {
                return Result.fail("商品不存在");
            }
            
            // 检查状态值是否合法
            if (status != 0 && status != 1) {
                return Result.fail("无效的商品状态");
            }
            
            // 更新状态
            Spu spu = new Spu();
            spu.setId(id);
            spu.setStatus(status);
            
            boolean success = this.updateById(spu);
            if (success) {
                return Result.success();
            } else {
                return Result.fail("更新商品状态失败");
            }
        } catch (Exception e) {
            log.error("更新商品状态失败", e);
            return Result.fail("更新商品状态失败: " + e.getMessage());
        }
    }

    @Override
    public Result<List<Spu>> getSpusByCategory(Long categoryId) {
        try {
            // 检查分类是否存在
            Category category = categoryService.getById(categoryId);
            if (category == null) {
                return Result.fail("分类不存在");
            }
            
            // 查询该分类下的商品
            LambdaQueryWrapper<Spu> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Spu::getCategoryId, categoryId)
                    .eq(Spu::getStatus, 1) // 只查询上架商品
                    .orderByDesc(Spu::getCreateTime);
            
            List<Spu> spuList = this.list(queryWrapper);
            return Result.success(spuList);
        } catch (Exception e) {
            log.error("获取分类商品列表失败", e);
            return Result.fail("获取分类商品列表失败: " + e.getMessage());
        }
    }

    @Override
    public Result<Page<Spu>> searchSpus(String keyword, int pageNum, int pageSize) {
        try {
            if (!StringUtils.hasText(keyword)) {
                return Result.fail("搜索关键词不能为空");
            }
            
            Page<Spu> page = new Page<>(pageNum, pageSize);
            LambdaQueryWrapper<Spu> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.like(Spu::getName, keyword)
                    .or()
                    .like(Spu::getSubTitle, keyword)
                    .or()
                    .like(Spu::getDescription, keyword)
                    .eq(Spu::getStatus, 1) // 只搜索上架商品
                    .orderByDesc(Spu::getCreateTime);
            
            Page<Spu> spuPage = this.page(page, queryWrapper);
            return Result.success(spuPage);
        } catch (Exception e) {
            log.error("搜索商品失败", e);
            return Result.fail("搜索商品失败: " + e.getMessage());
        }
    }
} 