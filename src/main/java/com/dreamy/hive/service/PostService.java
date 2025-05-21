package com.dreamy.hive.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dreamy.hive.common.Result;
import com.dreamy.hive.dto.request.PostCreateRequestDTO;
import com.dreamy.hive.dto.response.PostResponseDTO;
import com.dreamy.hive.entity.Post;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 帖子服务接口
 */
public interface PostService extends IService<Post> {
    
    /**
     * 创建帖子
     * @param postCreateRequestDTO 帖子创建请求DTO
     * @return 结果
     */
    Result<Post> createPost(PostCreateRequestDTO postCreateRequestDTO);
    
    /**
     * 上传帖子图片
     * @param file 图片文件
     * @return 图片URL
     */
    Result<String> uploadPostImage(MultipartFile file);
    
    /**
     * 获取帖子列表（分页）
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @param keyword 关键词（可选）
     * @return 帖子分页列表
     */
    Result<IPage<PostResponseDTO>> getPostList(int pageNum, int pageSize, String keyword);
    
    /**
     * 获取帖子详情
     * @param postId 帖子ID
     * @return 帖子详情
     */
    Result<PostResponseDTO> getPostDetail(Long postId);
    
    /**
     * 增加帖子浏览量
     * @param postId 帖子ID
     * @return 结果
     */
    Result<?> incrementViewCount(Long postId);
    
    /**
     * 删除帖子
     * @param postId 帖子ID
     * @return 结果
     */
    Result<?> deletePost(Long postId);
    
    /**
     * 获取用户的帖子列表
     * @param userId 用户ID
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return 帖子分页列表
     */
    Result<IPage<PostResponseDTO>> getUserPosts(Long userId, int pageNum, int pageSize);
}