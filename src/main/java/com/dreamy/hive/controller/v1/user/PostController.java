package com.dreamy.hive.controller.v1.user;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dreamy.hive.common.Result;
import com.dreamy.hive.dto.request.PostCreateRequestDTO;
import com.dreamy.hive.dto.response.PostResponseDTO;
import com.dreamy.hive.entity.Post;
import com.dreamy.hive.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 帖子控制器
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/posts")
@Tag(name = "帖子管理", description = "帖子相关接口")
public class PostController {

    @Autowired
    private PostService postService;

    /**
     * 创建帖子
     */
    @PostMapping
    @SaCheckLogin
    @Operation(summary = "创建帖子", description = "创建新帖子")
    public Result<Post> createPost(@RequestBody PostCreateRequestDTO postCreateRequestDTO) {
        return postService.createPost(postCreateRequestDTO);
    }

    /**
     * 上传帖子图片
     */
    @PostMapping("/upload-image")
    @SaCheckLogin
    @Operation(summary = "上传帖子图片", description = "上传帖子图片，返回图片URL")
    public Result<String> uploadPostImage(@RequestParam("file") MultipartFile file) {
        return postService.uploadPostImage(file);
    }

    /**
     * 获取帖子列表
     */
    @GetMapping
    @Operation(summary = "获取帖子列表", description = "分页获取帖子列表，支持关键词搜索")
    public Result<IPage<PostResponseDTO>> getPostList(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String keyword) {
        return postService.getPostList(pageNum, pageSize, keyword);
    }

    /**
     * 获取帖子详情
     */
    @GetMapping("/{postId}")
    @Operation(summary = "获取帖子详情", description = "获取指定帖子的详细信息")
    public Result<PostResponseDTO> getPostDetail(@PathVariable Long postId) {
        // 增加浏览量
        postService.incrementViewCount(postId);
        // 获取帖子详情
        return postService.getPostDetail(postId);
    }

    /**
     * 删除帖子
     */
    @DeleteMapping("/{postId}")
    @SaCheckLogin
    @Operation(summary = "删除帖子", description = "删除指定帖子")
    public Result<?> deletePost(@PathVariable Long postId) {
        return postService.deletePost(postId);
    }

    /**
     * 获取用户的帖子列表
     */
    @GetMapping("/user/{userId}")
    @Operation(summary = "获取用户帖子", description = "获取指定用户的帖子列表")
    public Result<IPage<PostResponseDTO>> getUserPosts(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {
        return postService.getUserPosts(userId, pageNum, pageSize);
    }
}