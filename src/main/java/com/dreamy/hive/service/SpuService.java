package com.dreamy.hive.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dreamy.hive.common.Result;
import com.dreamy.hive.dto.response.ProductDetailDTO;
import com.dreamy.hive.entity.Spu;

import java.util.List;

/**
 * 商品SPU服务接口
 */
public interface SpuService extends IService<Spu> {
    
    /**
     * 分页查询商品列表
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @param name 商品名称（可选）
     * @param categoryId 分类ID（可选）
     * @param status 商品状态（可选）
     * @return 分页结果
     */
    Result<Page<Spu>> listSpusByPage(int pageNum, int pageSize, String name, Long categoryId, Integer status);
    
    /**
     * 获取商品详情
     * @param id 商品ID
     * @return 商品详情
     */
    Result<Spu> getSpuDetail(Long id);
    
    /**
     * 获取商品详情页信息（包含SKU列表、图片轮播等）
     * @param id 商品ID
     * @return 商品详情页信息
     */
    Result<ProductDetailDTO> getProductDetail(Long id);
    
    /**
     * 添加商品
     * @param spu 商品信息
     * @return 操作结果
     */
    Result<?> addSpu(Spu spu);
    
    /**
     * 更新商品
     * @param id 商品ID
     * @param spu 商品信息
     * @return 操作结果
     */
    Result<?> updateSpu(Long id, Spu spu);
    
    /**
     * 删除商品
     * @param id 商品ID
     * @return 操作结果
     */
    Result<?> deleteSpu(Long id);
    
    /**
     * 更新商品状态（上下架）
     * @param id 商品ID
     * @param status 状态（0-下架，1-上架）
     * @return 操作结果
     */
    Result<?> updateSpuStatus(Long id, Integer status);
    
    /**
     * 根据分类ID获取商品列表
     * @param categoryId 分类ID
     * @return 商品列表
     */
    Result<List<Spu>> getSpusByCategory(Long categoryId);
    
    /**
     * 搜索商品
     * @param keyword 关键词
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return 分页结果
     */
    Result<Page<Spu>> searchSpus(String keyword, int pageNum, int pageSize);
} 