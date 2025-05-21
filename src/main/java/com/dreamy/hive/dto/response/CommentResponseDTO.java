package com.dreamy.hive.dto.response;

import com.dreamy.hive.entity.Comment;
import com.dreamy.hive.entity.User;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 评论响应DTO
 */
@Data
public class CommentResponseDTO {
    
    /**
     * 评论ID
     */
    private Long id;
    
    /**
     * 帖子ID
     */
    private Long postId;
    
    /**
     * 评论用户ID
     */
    private Long userId;
    
    /**
     * 评论用户信息
     */
    private User user;
    
    /**
     * 评论内容
     */
    private String content;
    
    /**
     * 父评论ID
     */
    private Long parentId;
    
    /**
     * 被回复用户ID
     */
    private Long replyUserId;
    
    /**
     * 被回复用户信息
     */
    private User replyUser;
    
    /**
     * 创建时间
     */
    private Date createTime;
    
    /**
     * 回复列表
     */
    private List<CommentResponseDTO> replies;
    
    /**
     * 从Comment实体转换为CommentResponseDTO
     * @param comment 评论实体
     * @return CommentResponseDTO
     */
    public static CommentResponseDTO fromComment(Comment comment) {
        CommentResponseDTO dto = new CommentResponseDTO();
        dto.setId(comment.getId());
        dto.setPostId(comment.getPostId());
        dto.setUserId(comment.getUserId());
        dto.setContent(comment.getContent());
        dto.setParentId(comment.getParentId());
        dto.setReplyUserId(comment.getReplyUserId());
        dto.setCreateTime(comment.getCreateTime());
        return dto;
    }
} 