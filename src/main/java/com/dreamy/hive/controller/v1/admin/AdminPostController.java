package com.dreamy.hive.controller.v1.admin;

import cn.dev33.satoken.annotation.SaCheckRole;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dreamy.hive.common.Result;
import com.dreamy.hive.entity.Post;
import com.dreamy.hive.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 后台帖子管理控制器
 */
@Slf4j
@RestController
@RequestMapping("/api/admin/posts")
@Tag(name = "后台帖子管理", description = "后台帖子管理相关接口")
@SaCheckRole("admin")
public class AdminPostController {

    @Autowired
    private PostService postService;

    /**
     * 获取帖子列表
     */
    @GetMapping
    @Operation(summary = "获取帖子列表", description = "分页获取帖子列表，支持关键词搜索和状态筛选")
    public Result<IPage<Post>> getPostList(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Integer status) {
        
        // 创建查询条件
        LambdaQueryWrapper<Post> queryWrapper = new LambdaQueryWrapper<>();
        
        // 如果有关键词，则进行标题或内容的模糊查询
        if (keyword != null && !keyword.isEmpty()) {
            queryWrapper.and(wrapper -> wrapper
                    .like(Post::getTitle, keyword)
                    .or()
                    .like(Post::getContent, keyword));
        }
        
        // 如果有状态筛选
        if (status != null) {
            queryWrapper.eq(Post::getStatus, status);
        }
        
        // 按创建时间倒序排序
        queryWrapper.orderByDesc(Post::getCreateTime);
        
        // 分页查询
        IPage<Post> page = new Page<>(pageNum, pageSize);
        page = postService.page(page, queryWrapper);
        
        return Result.success(page);
    }

    /**
     * 获取帖子详情
     */
    @GetMapping("/{postId}")
    @Operation(summary = "获取帖子详情", description = "获取指定帖子的详细信息")
    public Result<Post> getPostDetail(@PathVariable Long postId) {
        Post post = postService.getById(postId);
        if (post == null) {
            return Result.fail("帖子不存在");
        }
        return Result.success(post);
    }

    /**
     * 更新帖子状态
     */
    @PutMapping("/{postId}/status")
    @Operation(summary = "更新帖子状态", description = "更新指定帖子的状态")
    public Result<?> updatePostStatus(@PathVariable Long postId, @RequestParam Integer status) {
        Post post = postService.getById(postId);
        if (post == null) {
            return Result.fail("帖子不存在");
        }
        
        post.setStatus(status);
        boolean success = postService.updateById(post);
        if (!success) {
            return Result.fail("更新帖子状态失败");
        }
        
        return Result.success();
    }

    /**
     * 删除帖子
     */
    @DeleteMapping("/{postId}")
    @Operation(summary = "删除帖子", description = "删除指定帖子")
    public Result<?> deletePost(@PathVariable Long postId) {
        Post post = postService.getById(postId);
        if (post == null) {
            return Result.fail("帖子不存在");
        }
        
        boolean success = postService.removeById(postId);
        if (!success) {
            return Result.fail("删除帖子失败");
        }
        
        return Result.success();
    }
} 