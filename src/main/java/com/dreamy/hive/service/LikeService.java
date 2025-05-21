package com.dreamy.hive.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dreamy.hive.common.Result;
import com.dreamy.hive.entity.Like;
import com.dreamy.hive.entity.User;

import java.util.List;

/**
 * 点赞服务接口
 */
public interface LikeService extends IService<Like> {
    
    /**
     * 点赞或取消点赞
     * @param postId 帖子ID
     * @return 结果，包含点赞状态
     */
    Result<Boolean> toggleLike(Long postId);
    
    /**
     * 检查用户是否已点赞
     * @param userId 用户ID
     * @param postId 帖子ID
     * @return 是否已点赞
     */
    boolean isLiked(Long userId, Long postId);
    
    /**
     * 获取帖子点赞用户列表
     * @param postId 帖子ID
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return 用户分页列表
     */
    Result<IPage<User>> getLikeUsersByPostId(Long postId, int pageNum, int pageSize);
    
    /**
     * 获取用户点赞的帖子ID列表
     * @param userId 用户ID
     * @return 帖子ID列表
     */
    Result<List<Long>> getLikedPostIdsByUserId(Long userId);
}