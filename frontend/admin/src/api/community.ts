import request from './request'

// 获取帖子列表
export function getPostList(params: any) {
  return request({
    url: '/admin/posts', // Updated URL
    method: 'get',
    params
  })
}

// 获取帖子详情
export function getPostDetail(id: string) {
  return request({
    url: `/admin/posts/${id}`, // Updated URL
    method: 'get'
  })
}

// 更新帖子状态
export function updatePostStatus(id: string, status: string) { // Renamed, status in path
  return request({
    url: `/admin/posts/${id}/status/${status}`, // Updated URL
    method: 'put', 
    // No request body needed as status is in the path
  })
}

// 删除帖子
export function deletePost(id: string) {
  return request({
    url: `/admin/posts/${id}`, // Updated URL
    method: 'delete'
  })
}

// 获取评论列表
export function getCommentList(params: any) {
  return request({
    url: '/admin/comments', // Updated URL
    method: 'get',
    params // Params like userId, productId, status, pageNum, pageSize
  })
}

// 更新评论状态
export function updateCommentStatus(id: string, status: string) { // Renamed, status in path
  return request({
    url: `/admin/comments/${id}/status/${status}`, // Updated URL
    method: 'put',
    // No request body needed as status is in the path
  })
}

// 删除评论
export function deleteComment(id: string) {
  return request({
    url: `/admin/comments/${id}`, // Updated URL
    method: 'delete'
  })
}
