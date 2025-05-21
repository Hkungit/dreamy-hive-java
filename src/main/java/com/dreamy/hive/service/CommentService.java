package com.dreamy.hive.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dreamy.hive.common.Result;
import com.dreamy.hive.dto.request.CommentRequestDTO;
import com.dreamy.hive.entity.Comment;

import java.util.List;

/**
 * 评论服务接口
 */
public interface CommentService extends IService<Comment> {
    
    /**
     * 添加评论
     * @param commentRequestDTO 评论请求DTO
     * @return 结果
     */
    Result<?> addComment(CommentRequestDTO commentRequestDTO);
    
    /**
     * 回复评论
     * @param parentId 父评论ID
     * @param commentRequestDTO 评论请求DTO
     * @return 结果
     */
    Result<?> replyComment(Long parentId, CommentRequestDTO commentRequestDTO);
    
    /**
     * 获取帖子评论列表（分页）
     * @param postId 帖子ID
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return 评论分页列表
     */
    Result<IPage<Comment>> getCommentsByPostId(Long postId, int pageNum, int pageSize);
    
    /**
     * 获取评论回复列表
     * @param commentId 评论ID
     * @return 回复列表
     */
    Result<List<Comment>> getRepliesByCommentId(Long commentId);
    
    /**
     * 删除评论
     * @param commentId 评论ID
     * @return 结果
     */
    Result<?> deleteComment(Long commentId);
} 