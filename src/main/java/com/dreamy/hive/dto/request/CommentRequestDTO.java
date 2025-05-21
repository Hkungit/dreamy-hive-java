package com.dreamy.hive.dto.request;

import lombok.Data;

/**
 * 评论请求DTO
 */
@Data
public class CommentRequestDTO {
    
    /**
     * 帖子ID
     */
    private Long postId;
    
    /**
     * 评论内容
     */
    private String content;
    
    /**
     * 父评论ID（回复评论时使用）
     */
    private Long parentId;
    
    /**
     * 被回复用户ID（回复评论时使用）
     */
    private Long replyUserId;
} 