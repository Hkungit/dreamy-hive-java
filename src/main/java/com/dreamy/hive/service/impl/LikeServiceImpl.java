package com.dreamy.hive.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dreamy.hive.common.Result;
import com.dreamy.hive.entity.Like;
import com.dreamy.hive.entity.Post;
import com.dreamy.hive.entity.User;
import com.dreamy.hive.mapper.LikeMapper;
import com.dreamy.hive.service.LikeService;
import com.dreamy.hive.service.PostService;
import com.dreamy.hive.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 点赞服务实现类
 */
@Slf4j
@Service
public class LikeServiceImpl extends ServiceImpl<LikeMapper, Like> implements LikeService {

    @Autowired
    private PostService postService;
    
    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public Result<Boolean> toggleLike(Long postId) {
        // 检查帖子是否存在
        Post post = postService.getById(postId);
        if (post == null) {
            return Result.fail("帖子不存在");
        }
        
        // 获取当前用户ID
        Long userId = StpUtil.getLoginIdAsLong();
        
        // 查询是否已点赞
        LambdaQueryWrapper<Like> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Like::getUserId, userId).eq(Like::getPostId, postId);
        Like like = getOne(queryWrapper);
        
        boolean isLiked;
        
        if (like != null) {
            // 已点赞，取消点赞
            removeById(like.getId());
            // 更新帖子点赞数
            post.setLikeCount(post.getLikeCount() - 1);
            isLiked = false;
        } else {
            // 未点赞，添加点赞
            like = new Like();
            like.setUserId(userId);
            like.setPostId(postId);
            save(like);
            // 更新帖子点赞数
            post.setLikeCount(post.getLikeCount() + 1);
            isLiked = true;
        }
        
        // 更新帖子
        postService.updateById(post);
        
        return Result.success(isLiked);
    }

    @Override
    public boolean isLiked(Long userId, Long postId) {
        LambdaQueryWrapper<Like> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Like::getUserId, userId).eq(Like::getPostId, postId);
        return count(queryWrapper) > 0;
    }

    @Override
    public Result<IPage<User>> getLikeUsersByPostId(Long postId, int pageNum, int pageSize) {
        // 检查帖子是否存在
        Post post = postService.getById(postId);
        if (post == null) {
            return Result.fail("帖子不存在");
        }
        
        // 查询点赞记录
        LambdaQueryWrapper<Like> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Like::getPostId, postId).orderByDesc(Like::getCreateTime);
        
        // 分页查询
        IPage<Like> likePage = new Page<>(pageNum, pageSize);
        likePage = page(likePage, queryWrapper);
        
        // 获取用户ID列表
        List<Long> userIds = likePage.getRecords().stream()
                .map(Like::getUserId)
                .collect(Collectors.toList());
        
        // 查询用户信息
        List<User> users = new ArrayList<>();
        if (!userIds.isEmpty()) {
            users = userService.listByIds(userIds);
        }
        
        // 构建用户分页结果
        IPage<User> userPage = new Page<>(pageNum, pageSize, likePage.getTotal());
        userPage.setRecords(users);
        
        return Result.success(userPage);
    }

    @Override
    public Result<List<Long>> getLikedPostIdsByUserId(Long userId) {
        // 检查用户是否存在
        User user = userService.getById(userId);
        if (user == null) {
            return Result.fail("用户不存在");
        }
        
        // 查询用户点赞的帖子ID
        LambdaQueryWrapper<Like> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Like::getUserId, userId).select(Like::getPostId);
        
        List<Long> postIds = list(queryWrapper).stream()
                .map(Like::getPostId)
                .collect(Collectors.toList());
        
        return Result.success(postIds);
    }
}