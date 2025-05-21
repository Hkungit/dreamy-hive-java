package com.dreamy.hive.dto.response;

import com.dreamy.hive.entity.Post;
import com.dreamy.hive.entity.User;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 帖子响应DTO
 */
@Data
public class PostResponseDTO {
    
    /**
     * 帖子ID
     */
    private Long id;
    
    /**
     * 用户ID
     */
    private Long userId;
    
    /**
     * 用户信息
     */
    private User user;
    
    /**
     * 标题
     */
    private String title;
    
    /**
     * 内容
     */
    private String content;
    
    /**
     * 图片URLs（JSON数组）
     */
    private String images;
    
    /**
     * 图片URL列表
     */
    private List<String> imageList;
    
    /**
     * 浏览量
     */
    private Integer viewCount;
    
    /**
     * 点赞数
     */
    private Integer likeCount;
    
    /**
     * 评论数
     */
    private Integer commentCount;
    
    /**
     * 状态（0-禁用，1-正常）
     */
    private Integer status;
    
    /**
     * 创建时间
     */
    private Date createTime;
    
    /**
     * 当前用户是否已点赞
     */
    private Boolean liked;
    
    /**
     * 从Post实体转换为PostResponseDTO
     * @param post 帖子实体
     * @return PostResponseDTO
     */
    public static PostResponseDTO fromPost(Post post) {
        PostResponseDTO dto = new PostResponseDTO();
        dto.setId(post.getId());
        dto.setUserId(post.getUserId());
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        dto.setImages(post.getImages());
        dto.setViewCount(post.getViewCount());
        dto.setLikeCount(post.getLikeCount());
        dto.setCommentCount(post.getCommentCount());
        dto.setStatus(post.getStatus());
        dto.setCreateTime(post.getCreateTime());
        return dto;
    }
} 