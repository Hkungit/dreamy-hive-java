package com.dreamy.hive.controller.v1.user;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dreamy.hive.common.Result;
import com.dreamy.hive.entity.User;
import com.dreamy.hive.service.LikeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 点赞控制器
 */
@Slf4j
@RestController
@RequestMapping("/api/v1")
@Tag(name = "点赞管理", description = "点赞相关接口")
public class LikeController {

    @Autowired
    private LikeService likeService;

    /**
     * 点赞或取消点赞
     */
    @PostMapping("/posts/{postId}/like")
    @SaCheckLogin
    @Operation(summary = "点赞或取消点赞", description = "对帖子进行点赞或取消点赞操作")
    public Result<Boolean> toggleLike(@PathVariable Long postId) {
        return likeService.toggleLike(postId);
    }

    /**
     * 获取帖子点赞用户列表
     */
    @GetMapping("/posts/{postId}/likes")
    @Operation(summary = "获取点赞用户", description = "获取对帖子点赞的用户列表")
    public Result<IPage<User>> getLikeUsersByPostId(
            @PathVariable Long postId,
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {
        return likeService.getLikeUsersByPostId(postId, pageNum, pageSize);
    }

    /**
     * 获取用户点赞的帖子ID列表
     */
    @GetMapping("/users/{userId}/liked-posts")
    @Operation(summary = "获取用户点赞帖子", description = "获取用户点赞过的帖子ID列表")
    public Result<List<Long>> getLikedPostIdsByUserId(@PathVariable Long userId) {
        return likeService.getLikedPostIdsByUserId(userId);
    }
}