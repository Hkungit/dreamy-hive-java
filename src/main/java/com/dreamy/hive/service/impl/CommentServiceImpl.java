package com.dreamy.hive.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dreamy.hive.common.Result;
import com.dreamy.hive.dto.request.CommentRequestDTO;
import com.dreamy.hive.entity.Comment;
import com.dreamy.hive.entity.Post;
import com.dreamy.hive.mapper.CommentMapper;
import com.dreamy.hive.service.CommentService;
import com.dreamy.hive.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 评论服务实现类
 */
@Slf4j
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private PostService postService;

    @Override
    @Transactional
    public Result<?> addComment(CommentRequestDTO commentRequestDTO) {
        // 检查帖子是否存在
        Post post = postService.getById(commentRequestDTO.getPostId());
        if (post == null) {
            return Result.fail("帖子不存在");
        }
        
        // 获取当前用户ID
        Long userId = StpUtil.getLoginIdAsLong();
        
        // 创建评论
        Comment comment = new Comment();
        comment.setPostId(commentRequestDTO.getPostId());
        comment.setUserId(userId);
        comment.setContent(commentRequestDTO.getContent());
        comment.setStatus(1); // 默认状态为正常
        
        // 保存评论
        boolean success = save(comment);
        if (!success) {
            return Result.fail("评论发布失败");
        }
        
        // 更新帖子评论数
        post.setCommentCount(post.getCommentCount() + 1);
        postService.updateById(post);
        
        return Result.success(comment);
    }

    @Override
    @Transactional
    public Result<?> replyComment(Long parentId, CommentRequestDTO commentRequestDTO) {
        // 检查父评论是否存在
        Comment parentComment = getById(parentId);
        if (parentComment == null) {
            return Result.fail("回复的评论不存在");
        }
        
        // 获取当前用户ID
        Long userId = StpUtil.getLoginIdAsLong();
        
        // 创建回复评论
        Comment reply = new Comment();
        reply.setPostId(parentComment.getPostId());
        reply.setUserId(userId);
        reply.setContent(commentRequestDTO.getContent());
        reply.setParentId(parentId);
        reply.setReplyUserId(commentRequestDTO.getReplyUserId() != null ? 
                commentRequestDTO.getReplyUserId() : parentComment.getUserId());
        reply.setStatus(1); // 默认状态为正常
        
        // 保存回复
        boolean success = save(reply);
        if (!success) {
            return Result.fail("回复发布失败");
        }
        
        // 更新帖子评论数
        Post post = postService.getById(parentComment.getPostId());
        post.setCommentCount(post.getCommentCount() + 1);
        postService.updateById(post);
        
        return Result.success(reply);
    }

    @Override
    public Result<IPage<Comment>> getCommentsByPostId(Long postId, int pageNum, int pageSize) {
        // 检查帖子是否存在
        Post post = postService.getById(postId);
        if (post == null) {
            return Result.fail("帖子不存在");
        }
        
        // 查询顶级评论（没有父评论的评论）
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getPostId, postId)
                    .isNull(Comment::getParentId)
                    .eq(Comment::getStatus, 1)  // 只查询状态正常的评论
                    .orderByDesc(Comment::getCreateTime);  // 按创建时间倒序排序
        
        // 分页查询
        IPage<Comment> page = new Page<>(pageNum, pageSize);
        page = page(page, queryWrapper);
        
        return Result.success(page);
    }

    @Override
    public Result<List<Comment>> getRepliesByCommentId(Long commentId) {
        // 检查评论是否存在
        Comment comment = getById(commentId);
        if (comment == null) {
            return Result.fail("评论不存在");
        }
        
        // 查询回复列表
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getParentId, commentId)
                    .eq(Comment::getStatus, 1)  // 只查询状态正常的评论
                    .orderByAsc(Comment::getCreateTime);  // 按创建时间升序排序
        
        List<Comment> replies = list(queryWrapper);
        
        return Result.success(replies);
    }

    @Override
    @Transactional
    public Result<?> deleteComment(Long commentId) {
        // 检查评论是否存在
        Comment comment = getById(commentId);
        if (comment == null) {
            return Result.fail("评论不存在");
        }
        
        // 获取当前用户ID
        Long userId = StpUtil.getLoginIdAsLong();
        
        // 检查是否是评论的作者
        if (!comment.getUserId().equals(userId)) {
            return Result.fail("您无权删除此评论");
        }
        
        // 查询该评论下的所有回复
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getParentId, commentId);
        List<Comment> replies = list(queryWrapper);
        
        // 删除该评论下的所有回复
        if (!replies.isEmpty()) {
            removeByIds(replies.stream().map(Comment::getId).toList());
        }
        
        // 删除评论
        boolean success = removeById(commentId);
        if (!success) {
            return Result.fail("评论删除失败");
        }
        
        // 更新帖子评论数
        Post post = postService.getById(comment.getPostId());
        post.setCommentCount(post.getCommentCount() - 1 - replies.size());
        postService.updateById(post);
        
        return Result.success();
    }
} 