import request from './request'

// 获取帖子列表
export function getPostList(params: any) {
  return request({
    url: '/v1/admin/community/posts',
    method: 'get',
    params
  })
}

// 获取帖子详情
export function getPostDetail(id: string) {
  return request({
    url: `/v1/admin/community/posts/${id}`,
    method: 'get'
  })
}

// 审核帖子
export function reviewPost(id: string, status: string, reason?: string) {
  return request({
    url: `/v1/admin/community/posts/${id}/review`,
    method: 'put',
    data: { status, reason }
  })
}

// 删除帖子
export function deletePost(id: string) {
  return request({
    url: `/v1/admin/community/posts/${id}`,
    method: 'delete'
  })
}

// 获取评论列表
export function getCommentList(params: any) {
  return request({
    url: '/v1/admin/community/comments',
    method: 'get',
    params
  })
}

// 审核评论
export function reviewComment(id: string, status: string, reason?: string) {
  return request({
    url: `/v1/admin/community/comments/${id}/review`,
    method: 'put',
    data: { status, reason }
  })
}

// 删除评论
export function deleteComment(id: string) {
  return request({
    url: `/v1/admin/community/comments/${id}`,
    method: 'delete'
  })
}
