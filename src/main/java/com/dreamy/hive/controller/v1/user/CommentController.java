package com.dreamy.hive.controller.v1.user;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dreamy.hive.common.Result;
import com.dreamy.hive.dto.request.CommentRequestDTO;
import com.dreamy.hive.entity.Comment;
import com.dreamy.hive.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 评论控制器
 */
@Slf4j
@RestController
@RequestMapping("/api/v1")
@Tag(name = "评论管理", description = "评论相关接口")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 添加评论
     */
    @PostMapping("/posts/{postId}/comments")
    @SaCheckLogin
    @Operation(summary = "添加评论", description = "向帖子添加评论")
    public Result<?> addComment(@PathVariable Long postId, @RequestBody CommentRequestDTO commentRequestDTO) {
        commentRequestDTO.setPostId(postId);
        return commentService.addComment(commentRequestDTO);
    }

    /**
     * 回复评论
     */
    @PostMapping("/comments/{commentId}/replies")
    @SaCheckLogin
    @Operation(summary = "回复评论", description = "回复指定评论")
    public Result<?> replyComment(@PathVariable Long commentId, @RequestBody CommentRequestDTO commentRequestDTO) {
        return commentService.replyComment(commentId, commentRequestDTO);
    }

    /**
     * 获取帖子评论列表
     */
    @GetMapping("/posts/{postId}/comments")
    @Operation(summary = "获取帖子评论", description = "获取帖子的评论列表")
    public Result<IPage<Comment>> getCommentsByPostId(
            @PathVariable Long postId,
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {
        return commentService.getCommentsByPostId(postId, pageNum, pageSize);
    }

    /**
     * 获取评论回复列表
     */
    @GetMapping("/comments/{commentId}/replies")
    @Operation(summary = "获取评论回复", description = "获取评论的回复列表")
    public Result<List<Comment>> getRepliesByCommentId(@PathVariable Long commentId) {
        return commentService.getRepliesByCommentId(commentId);
    }

    /**
     * 删除评论
     */
    @DeleteMapping("/comments/{commentId}")
    @SaCheckLogin
    @Operation(summary = "删除评论", description = "删除指定评论")
    public Result<?> deleteComment(@PathVariable Long commentId) {
        return commentService.deleteComment(commentId);
    }
}