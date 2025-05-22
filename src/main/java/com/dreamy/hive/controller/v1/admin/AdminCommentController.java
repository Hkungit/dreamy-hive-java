package com.dreamy.hive.controller.v1.admin;

import cn.dev33.satoken.annotation.SaCheckRole;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dreamy.hive.annotation.RequiresPermission;
import com.dreamy.hive.annotation.RequiresRole;
import com.dreamy.hive.common.Result;
import com.dreamy.hive.entity.Comment;
import com.dreamy.hive.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 后台评论管理控制器
 */
@Slf4j
@RestController
@RequestMapping("/v1/admin/comments")
@Tag(name = "后台评论管理", description = "后台评论管理相关接口")
public class AdminCommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 获取评论列表
     */
    @GetMapping
    @Operation(summary = "获取评论列表", description = "分页获取评论列表，支持帖子ID筛选和状态筛选")
    public Result<IPage<Comment>> getCommentList(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) Long postId,
            @RequestParam(required = false) Integer status) {

        // 创建查询条件
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();

        // 如果有帖子ID筛选
        if (postId != null) {
            queryWrapper.eq(Comment::getPostId, postId);
        }

        // 如果有状态筛选
        if (status != null) {
            queryWrapper.eq(Comment::getStatus, status);
        }

        // 按创建时间倒序排序
        queryWrapper.orderByDesc(Comment::getCreateTime);

        // 分页查询
        IPage<Comment> page = new Page<>(pageNum, pageSize);
        page = commentService.page(page, queryWrapper);

        return Result.success(page);
    }

    /**
     * 获取评论详情
     */
    @GetMapping("/{commentId}")
    @Operation(summary = "获取评论详情", description = "获取指定评论的详细信息")
    public Result<Comment> getCommentDetail(@PathVariable Long commentId) {
        Comment comment = commentService.getById(commentId);
        if (comment == null) {
            return Result.fail("评论不存在");
        }
        return Result.success(comment);
    }

    /**
     * 更新评论状态
     */
    @PutMapping("/{commentId}/status")
    @Operation(summary = "更新评论状态", description = "更新指定评论的状态")
    public Result<?> updateCommentStatus(@PathVariable Long commentId, @RequestParam Integer status) {
        Comment comment = commentService.getById(commentId);
        if (comment == null) {
            return Result.fail("评论不存在");
        }

        comment.setStatus(status);
        boolean success = commentService.updateById(comment);
        if (!success) {
            return Result.fail("更新评论状态失败");
        }

        return Result.success();
    }

    /**
     * 删除评论
     */
    @DeleteMapping("/{commentId}")
    @Operation(summary = "删除评论", description = "删除指定评论")
    public Result<?> deleteComment(@PathVariable Long commentId) {
        Comment comment = commentService.getById(commentId);
        if (comment == null) {
            return Result.fail("评论不存在");
        }

        boolean success = commentService.removeById(commentId);
        if (!success) {
            return Result.fail("删除评论失败");
        }

        return Result.success();
    }
}