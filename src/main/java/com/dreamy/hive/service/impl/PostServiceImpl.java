package com.dreamy.hive.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dreamy.hive.common.Result;
import com.dreamy.hive.dto.request.PostCreateRequestDTO;
import com.dreamy.hive.dto.response.FileUploadResponseDTO;
import com.dreamy.hive.dto.response.PostResponseDTO;
import com.dreamy.hive.entity.Like;
import com.dreamy.hive.entity.Post;
import com.dreamy.hive.entity.User;
import com.dreamy.hive.mapper.PostMapper;
import com.dreamy.hive.service.LikeService;
import com.dreamy.hive.service.OssService;
import com.dreamy.hive.service.PostService;
import com.dreamy.hive.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 帖子服务实现类
 */
@Slf4j
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {

    @Autowired
    private OssService ossService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private LikeService likeService;

    @Override
    @Transactional
    public Result<Post> createPost(PostCreateRequestDTO postCreateRequestDTO) {
        // 获取当前用户ID
        Long userId = StpUtil.getLoginIdAsLong();
        
        // 创建帖子
        Post post = new Post();
        post.setUserId(userId);
        post.setTitle(postCreateRequestDTO.getTitle());
        post.setContent(postCreateRequestDTO.getContent());
        post.setViewCount(0);
        post.setLikeCount(0);
        post.setCommentCount(0);
        post.setStatus(1); // 默认状态为正常
        
        // 处理图片
        List<String> imageUrls = new ArrayList<>();
        
        // 处理已上传的图片URL
        if (postCreateRequestDTO.getImageUrls() != null && !postCreateRequestDTO.getImageUrls().isEmpty()) {
            imageUrls.addAll(postCreateRequestDTO.getImageUrls());
        }
        
        // 处理新上传的图片文件
        if (postCreateRequestDTO.getImageFiles() != null && !postCreateRequestDTO.getImageFiles().isEmpty()) {
            for (MultipartFile file : postCreateRequestDTO.getImageFiles()) {
                if (file != null && !file.isEmpty()) {
                    try {
                        FileUploadResponseDTO responseDTO = ossService.validateAndUploadPostImage(file);
                        imageUrls.add(responseDTO.getUrl());
                    } catch (IOException e) {
                        log.error("上传帖子图片失败: {}", e.getMessage());
                        return Result.fail("图片上传失败: " + e.getMessage());
                    }
                }
            }
        }
        
        // 将图片URL列表转换为JSON字符串
        if (!imageUrls.isEmpty()) {
            post.setImages(JSON.toJSONString(imageUrls));
        }
        
        // 保存帖子
        boolean success = save(post);
        if (!success) {
            return Result.fail("帖子发布失败");
        }
        
        return Result.success(post);
    }

    @Override
    public Result<String> uploadPostImage(MultipartFile file) {
        try {
            FileUploadResponseDTO responseDTO = ossService.validateAndUploadPostImage(file);
            return Result.success(responseDTO.getUrl());
        } catch (IOException e) {
            log.error("上传帖子图片失败: {}", e.getMessage());
            return Result.fail("图片上传失败: " + e.getMessage());
        }
    }

    @Override
    public Result<IPage<PostResponseDTO>> getPostList(int pageNum, int pageSize, String keyword) {
        // 创建查询条件
        LambdaQueryWrapper<Post> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Post::getStatus, 1); // 只查询状态正常的帖子
        
        // 如果有关键词，则进行标题或内容的模糊查询
        if (StringUtils.hasText(keyword)) {
            queryWrapper.and(wrapper -> wrapper
                    .like(Post::getTitle, keyword)
                    .or()
                    .like(Post::getContent, keyword));
        }
        
        // 按创建时间倒序排序
        queryWrapper.orderByDesc(Post::getCreateTime);
        
        // 分页查询
        IPage<Post> page = new Page<>(pageNum, pageSize);
        page = page(page, queryWrapper);
        
        // 转换为PostResponseDTO
        IPage<PostResponseDTO> responsePage = page.convert(this::convertToPostResponseDTO);
        
        return Result.success(responsePage);
    }

    @Override
    public Result<PostResponseDTO> getPostDetail(Long postId) {
        // 查询帖子
        Post post = getById(postId);
        if (post == null) {
            return Result.fail("帖子不存在");
        }
        
        // 转换为PostResponseDTO
        PostResponseDTO postResponseDTO = convertToPostResponseDTO(post);
        
        return Result.success(postResponseDTO);
    }

    @Override
    @Transactional
    public Result<?> incrementViewCount(Long postId) {
        // 查询帖子
        Post post = getById(postId);
        if (post == null) {
            return Result.fail("帖子不存在");
        }
        
        // 增加浏览量
        post.setViewCount(post.getViewCount() + 1);
        updateById(post);
        
        return Result.success();
    }

    @Override
    @Transactional
    public Result<?> deletePost(Long postId) {
        // 查询帖子
        Post post = getById(postId);
        if (post == null) {
            return Result.fail("帖子不存在");
        }
        
        // 获取当前用户ID
        Long userId = StpUtil.getLoginIdAsLong();
        
        // 检查是否是帖子的作者
        if (!post.getUserId().equals(userId)) {
            return Result.fail("您无权删除此帖子");
        }
        
        // 删除帖子
        boolean success = removeById(postId);
        if (!success) {
            return Result.fail("帖子删除失败");
        }
        
        return Result.success();
    }

    @Override
    public Result<IPage<PostResponseDTO>> getUserPosts(Long userId, int pageNum, int pageSize) {
        // 创建查询条件
        LambdaQueryWrapper<Post> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Post::getUserId, userId)
                    .eq(Post::getStatus, 1) // 只查询状态正常的帖子
                    .orderByDesc(Post::getCreateTime); // 按创建时间倒序排序
        
        // 分页查询
        IPage<Post> page = new Page<>(pageNum, pageSize);
        page = page(page, queryWrapper);
        
        // 转换为PostResponseDTO
        IPage<PostResponseDTO> responsePage = page.convert(this::convertToPostResponseDTO);
        
        return Result.success(responsePage);
    }
    
    /**
     * 将Post实体转换为PostResponseDTO
     * @param post 帖子实体
     * @return PostResponseDTO
     */
    private PostResponseDTO convertToPostResponseDTO(Post post) {
        PostResponseDTO dto = PostResponseDTO.fromPost(post);
        
        // 获取用户信息
        User user = userService.getById(post.getUserId());
        dto.setUser(user);
        
        // 解析图片URL列表
        if (StringUtils.hasText(post.getImages())) {
            try {
                List<String> imageList = JSON.parseArray(post.getImages(), String.class);
                dto.setImageList(imageList);
            } catch (Exception e) {
                log.error("解析帖子图片失败: {}", e.getMessage());
            }
        }
        
        // 判断当前用户是否已点赞
        try {
            if (StpUtil.isLogin()) {
                Long currentUserId = StpUtil.getLoginIdAsLong();
                LambdaQueryWrapper<Like> likeQuery = new LambdaQueryWrapper<>();
                likeQuery.eq(Like::getUserId, currentUserId)
                         .eq(Like::getPostId, post.getId());
                boolean liked = likeService.count(likeQuery) > 0;
                dto.setLiked(liked);
            } else {
                dto.setLiked(false);
            }
        } catch (Exception e) {
            log.error("获取点赞状态失败: {}", e.getMessage());
            dto.setLiked(false);
        }
        
        return dto;
    }
}