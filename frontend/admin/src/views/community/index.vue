<template>
  <div class="community-container">
    <el-tabs v-model="activeTab" @tab-click="handleTabClick">
      <el-tab-pane label="帖子管理" name="posts">
        <div class="filter-container">
          <el-form :inline="true" :model="postQueryParams" class="form-inline">
            <el-form-item label="标题">
              <el-input v-model="postQueryParams.title" placeholder="请输入帖子标题" clearable />
            </el-form-item>
            <el-form-item label="状态">
              <el-select v-model="postQueryParams.status" placeholder="请选择状态" clearable>
                <el-option label="待审核" value="pending" />
                <el-option label="已通过" value="approved" />
                <el-option label="已拒绝" value="rejected" />
              </el-select>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="handlePostQuery">查询</el-button>
              <el-button @click="resetPostQuery">重置</el-button>
            </el-form-item>
          </el-form>
        </div>

        <el-card class="box-card">
          <template #header>
            <div class="card-header">
              <span>帖子列表</span>
            </div>
          </template>

          <el-table
            v-loading="postLoading"
            :data="postList"
            border
            style="width: 100%"
          >
            <el-table-column prop="title" label="标题" min-width="200" show-overflow-tooltip />
            <el-table-column prop="username" label="发布者" width="120" />
            <el-table-column prop="viewCount" label="浏览量" width="100" />
            <el-table-column prop="likeCount" label="点赞数" width="100" />
            <el-table-column prop="commentCount" label="评论数" width="100" />
            <el-table-column prop="status" label="状态" width="100">
              <template #default="scope">
                <el-tag :type="getPostStatusType(scope.row.status)">
                  {{ getPostStatusText(scope.row.status) }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="createTime" label="发布时间" width="160" />
            <el-table-column label="操作" width="240" fixed="right">
              <template #default="scope">
                <el-button type="primary" link @click="handleViewPost(scope.row)">
                  查看
                </el-button>
                <el-button
                  v-if="scope.row.status === 'pending'"
                  type="success"
                  link
                  @click="handleReviewPost(scope.row, 'approved')"
                >
                  通过
                </el-button>
                <el-button
                  v-if="scope.row.status === 'pending'"
                  type="danger"
                  link
                  @click="handleReviewPost(scope.row, 'rejected')"
                >
                  拒绝
                </el-button>
                <el-button type="danger" link @click="handleDeletePost(scope.row)">
                  删除
                </el-button>
              </template>
            </el-table-column>
          </el-table>

          <div class="pagination-container">
            <el-pagination
              v-model:current-page="postQueryParams.pageNum"
              v-model:page-size="postQueryParams.pageSize"
              :page-sizes="[10, 20, 50, 100]"
              layout="total, sizes, prev, pager, next, jumper"
              :total="postTotal"
              @size-change="handlePostSizeChange"
              @current-change="handlePostCurrentChange"
            />
          </div>
        </el-card>
      </el-tab-pane>

      <el-tab-pane label="评论管理" name="comments">
        <div class="filter-container">
          <el-form :inline="true" :model="commentQueryParams" class="form-inline">
            <el-form-item label="内容">
              <el-input v-model="commentQueryParams.content" placeholder="请输入评论内容" clearable />
            </el-form-item>
            <el-form-item label="状态">
              <el-select v-model="commentQueryParams.status" placeholder="请选择状态" clearable>
                <el-option label="待审核" value="pending" />
                <el-option label="已通过" value="approved" />
                <el-option label="已拒绝" value="rejected" />
              </el-select>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="handleCommentQuery">查询</el-button>
              <el-button @click="resetCommentQuery">重置</el-button>
            </el-form-item>
          </el-form>
        </div>

        <el-card class="box-card">
          <template #header>
            <div class="card-header">
              <span>评论列表</span>
            </div>
          </template>

          <el-table
            v-loading="commentLoading"
            :data="commentList"
            border
            style="width: 100%"
          >
            <el-table-column prop="content" label="评论内容" min-width="200" show-overflow-tooltip />
            <el-table-column prop="postTitle" label="所属帖子" min-width="200" show-overflow-tooltip />
            <el-table-column prop="username" label="评论者" width="120" />
            <el-table-column prop="likeCount" label="点赞数" width="100" />
            <el-table-column prop="status" label="状态" width="100">
              <template #default="scope">
                <el-tag :type="getCommentStatusType(scope.row.status)">
                  {{ getCommentStatusText(scope.row.status) }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="createTime" label="评论时间" width="160" />
            <el-table-column label="操作" width="240" fixed="right">
              <template #default="scope">
                <el-button type="primary" link @click="handleViewComment(scope.row)">
                  查看
                </el-button>
                <el-button
                  v-if="scope.row.status === 'pending'"
                  type="success"
                  link
                  @click="handleReviewComment(scope.row, 'approved')"
                >
                  通过
                </el-button>
                <el-button
                  v-if="scope.row.status === 'pending'"
                  type="danger"
                  link
                  @click="handleReviewComment(scope.row, 'rejected')"
                >
                  拒绝
                </el-button>
                <el-button type="danger" link @click="handleDeleteComment(scope.row)">
                  删除
                </el-button>
              </template>
            </el-table-column>
          </el-table>

          <div class="pagination-container">
            <el-pagination
              v-model:current-page="commentQueryParams.pageNum"
              v-model:page-size="commentQueryParams.pageSize"
              :page-sizes="[10, 20, 50, 100]"
              layout="total, sizes, prev, pager, next, jumper"
              :total="commentTotal"
              @size-change="handleCommentSizeChange"
              @current-change="handleCommentCurrentChange"
            />
          </div>
        </el-card>
      </el-tab-pane>
    </el-tabs>

    <!-- 帖子详情对话框 -->
    <el-dialog
      v-model="postDialogVisible"
      title="帖子详情"
      width="800px"
      append-to-body
    >
      <div v-if="currentPost" class="post-detail">
        <h2 class="post-title">{{ currentPost.title }}</h2>
        <div class="post-meta">
          <span>作者：{{ currentPost.username }}</span>
          <span>发布时间：{{ currentPost.createTime }}</span>
          <span>状态：
            <el-tag :type="getPostStatusType(currentPost.status)">
              {{ getPostStatusText(currentPost.status) }}
            </el-tag>
          </span>
        </div>
        <div class="post-content" v-html="currentPost.content"></div>
        <div class="post-stats">
          <span>浏览量：{{ currentPost.viewCount }}</span>
          <span>点赞数：{{ currentPost.likeCount }}</span>
          <span>评论数：{{ currentPost.commentCount }}</span>
        </div>
      </div>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="postDialogVisible = false">关闭</el-button>
          <el-button
            v-if="currentPost && currentPost.status === 'pending'"
            type="success"
            @click="handleReviewPost(currentPost, 'approved', true)"
          >
            通过
          </el-button>
          <el-button
            v-if="currentPost && currentPost.status === 'pending'"
            type="danger"
            @click="handleReviewPost(currentPost, 'rejected', true)"
          >
            拒绝
          </el-button>
        </div>
      </template>
    </el-dialog>

    <!-- 评论详情对话框 -->
    <el-dialog
      v-model="commentDialogVisible"
      title="评论详情"
      width="600px"
      append-to-body
    >
      <div v-if="currentComment" class="comment-detail">
        <div class="comment-meta">
          <span>评论者：{{ currentComment.username }}</span>
          <span>评论时间：{{ currentComment.createTime }}</span>
          <span>状态：
            <el-tag :type="getCommentStatusType(currentComment.status)">
              {{ getCommentStatusText(currentComment.status) }}
            </el-tag>
          </span>
        </div>
        <div class="comment-post">
          <span>所属帖子：{{ currentComment.postTitle }}</span>
        </div>
        <div class="comment-content">{{ currentComment.content }}</div>
        <div class="comment-stats">
          <span>点赞数：{{ currentComment.likeCount }}</span>
        </div>
      </div>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="commentDialogVisible = false">关闭</el-button>
          <el-button
            v-if="currentComment && currentComment.status === 'pending'"
            type="success"
            @click="handleReviewComment(currentComment, 'approved', true)"
          >
            通过
          </el-button>
          <el-button
            v-if="currentComment && currentComment.status === 'pending'"
            type="danger"
            @click="handleReviewComment(currentComment, 'rejected', true)"
          >
            拒绝
          </el-button>
        </div>
      </template>
    </el-dialog>

    <!-- 审核拒绝原因对话框 -->
    <el-dialog
      v-model="rejectDialogVisible"
      title="拒绝原因"
      width="500px"
      append-to-body
    >
      <el-form :model="rejectForm">
        <el-form-item label="拒绝原因" prop="reason">
          <el-input
            v-model="rejectForm.reason"
            type="textarea"
            rows="4"
            placeholder="请输入拒绝原因"
          />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="rejectDialogVisible = false">取消</el-button>
          <el-button type="primary" @click="confirmReject">确定</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script lang="ts" setup>
import { ref, reactive, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { getPostList, getPostDetail, updatePostStatus, deletePost } from '../../api/community' // reviewPost -> updatePostStatus
import { getCommentList, updateCommentStatus, deleteComment } from '../../api/community' // reviewComment -> updateCommentStatus

// 当前激活的标签页
const activeTab = ref('posts')

// 帖子相关
const postLoading = ref(false)
const postList = ref<any[]>([])
const postTotal = ref(0)
const postDialogVisible = ref(false)
const currentPost = ref<any>(null)

// 评论相关
const commentLoading = ref(false)
const commentList = ref<any[]>([])
const commentTotal = ref(0)
const commentDialogVisible = ref(false)
const currentComment = ref<any>(null)

// 拒绝对话框
const rejectDialogVisible = ref(false)
const rejectForm = reactive({
  reason: '',
  type: '', // 'post' 或 'comment'
  id: '',
  fromDialog: false
})

// 帖子查询参数
const postQueryParams = reactive({
  pageNum: 1,
  pageSize: 10,
  title: '',
  status: ''
})

// 评论查询参数
const commentQueryParams = reactive({
  pageNum: 1,
  pageSize: 10,
  content: '',
  status: ''
})

// 获取帖子状态类型
const getPostStatusType = (status: string) => {
  const statusMap: Record<string, string> = {
    pending: 'warning',
    approved: 'success',
    rejected: 'danger'
  }
  return statusMap[status] || 'info'
}

// 获取帖子状态文本
const getPostStatusText = (status: string) => {
  const statusMap: Record<string, string> = {
    pending: '待审核',
    approved: '已通过',
    rejected: '已拒绝'
  }
  return statusMap[status] || '未知状态'
}

// 获取评论状态类型
const getCommentStatusType = (status: string) => {
  return getPostStatusType(status)
}

// 获取评论状态文本
const getCommentStatusText = (status: string) => {
  return getPostStatusText(status)
}

// 标签页切换
const handleTabClick = () => {
  if (activeTab.value === 'posts') {
    getPostData()
  } else {
    getCommentData()
  }
}

// 获取帖子列表
const getPostData = async () => {
  postLoading.value = true
  try {
    const res = await getPostList(postQueryParams)
    postList.value = res.data.list
    postTotal.value = res.data.total
  } catch (error) {
    console.error('获取帖子列表失败', error)
  } finally {
    postLoading.value = false
  }
}

// 获取评论列表
const getCommentData = async () => {
  commentLoading.value = true
  try {
    const res = await getCommentList(commentQueryParams)
    commentList.value = res.data.list
    commentTotal.value = res.data.total
  } catch (error) {
    console.error('获取评论列表失败', error)
  } finally {
    commentLoading.value = false
  }
}

// 帖子查询
const handlePostQuery = () => {
  postQueryParams.pageNum = 1
  getPostData()
}

// 重置帖子查询
const resetPostQuery = () => {
  postQueryParams.title = ''
  postQueryParams.status = ''
  handlePostQuery()
}

// 评论查询
const handleCommentQuery = () => {
  commentQueryParams.pageNum = 1
  getCommentData()
}

// 重置评论查询
const resetCommentQuery = () => {
  commentQueryParams.content = ''
  commentQueryParams.status = ''
  handleCommentQuery()
}

// 帖子分页大小变化
const handlePostSizeChange = (val: number) => {
  postQueryParams.pageSize = val
  getPostData()
}

// 帖子当前页变化
const handlePostCurrentChange = (val: number) => {
  postQueryParams.pageNum = val
  getPostData()
}

// 评论分页大小变化
const handleCommentSizeChange = (val: number) => {
  commentQueryParams.pageSize = val
  getCommentData()
}

// 评论当前页变化
const handleCommentCurrentChange = (val: number) => {
  commentQueryParams.pageNum = val
  getCommentData()
}

// 查看帖子详情
const handleViewPost = async (row: any) => {
  try {
    const res = await getPostDetail(row.id)
    currentPost.value = res.data
    postDialogVisible.value = true
  } catch (error) {
    console.error('获取帖子详情失败', error)
    ElMessage.error('获取帖子详情失败')
  }
}

// 查看评论详情
const handleViewComment = (row: any) => {
  currentComment.value = row
  commentDialogVisible.value = true
}

// 审核帖子
const handleReviewPost = (row: any, status: string, fromDialog = false) => {
  if (status === 'rejected') {
    // 显示拒绝原因对话框
    rejectForm.type = 'post'
    rejectForm.id = row.id
    rejectForm.reason = ''
    rejectForm.fromDialog = fromDialog
    rejectDialogVisible.value = true
  } else {
    // 直接通过
    submitReviewPost(row.id, status)
  }
}

// 审核评论
const handleReviewComment = (row: any, status: string, fromDialog = false) => {
  if (status === 'rejected') {
    // 显示拒绝原因对话框
    rejectForm.type = 'comment'
    rejectForm.id = row.id
    rejectForm.reason = ''
    rejectForm.fromDialog = fromDialog
    rejectDialogVisible.value = true
  } else {
    // 直接通过
    submitReviewComment(row.id, status)
  }
}

// 确认拒绝
const confirmReject = () => {
  if (rejectForm.type === 'post') {
    submitReviewPost(rejectForm.id, 'rejected', rejectForm.reason)
  } else {
    submitReviewComment(rejectForm.id, 'rejected', rejectForm.reason)
  }
  rejectDialogVisible.value = false
}

// 提交帖子审核
const submitReviewPost = async (id: string, status: string, reason?: string) => { // reason is kept for now as per instructions but not used in API call
  try {
    await updatePostStatus(id, status) // Updated API call, reason is not passed
    ElMessage.success('审核操作成功')
    
    // 如果是从详情对话框操作的，关闭对话框
    if (rejectForm.fromDialog) {
      postDialogVisible.value = false
    }
    
    // 刷新列表
    getPostData()
  } catch (error) {
    console.error('审核操作失败', error)
    ElMessage.error('审核操作失败')
  }
}

// 提交评论审核
const submitReviewComment = async (id: string, status: string, reason?: string) => { // reason is kept for now as per instructions but not used in API call
  try {
    await updateCommentStatus(id, status) // Updated API call, reason is not passed
    ElMessage.success('审核操作成功')
    
    // 如果是从详情对话框操作的，关闭对话框
    if (rejectForm.fromDialog) {
      commentDialogVisible.value = false
    }
    
    // 刷新列表
    getCommentData()
  } catch (error) {
    console.error('审核操作失败', error)
    ElMessage.error('审核操作失败')
  }
}

// 删除帖子
const handleDeletePost = async (row: any) => {
  try {
    await ElMessageBox.confirm('确认要删除该帖子吗?', '提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    })
    
    await deletePost(row.id)
    ElMessage.success('删除成功')
    getPostData()
  } catch (error: any) {
    if (error !== 'cancel') {
      ElMessage.error(`删除失败: ${error.message}`)
    }
  }
}

// 删除评论
const handleDeleteComment = async (row: any) => {
  try {
    await ElMessageBox.confirm('确认要删除该评论吗?', '提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    })
    
    await deleteComment(row.id)
    ElMessage.success('删除成功')
    getCommentData()
  } catch (error: any) {
    if (error !== 'cancel') {
      ElMessage.error(`删除失败: ${error.message}`)
    }
  }
}

onMounted(() => {
  getPostData()
})
</script>

<style lang="scss" scoped>
.community-container {
  .filter-container {
    margin-bottom: 20px;
  }
  
  .card-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
  
  .pagination-container {
    margin-top: 20px;
    text-align: right;
  }
  
  .post-detail {
    .post-title {
      margin-top: 0;
      margin-bottom: 15px;
      font-size: 20px;
      color: #303133;
    }
    
    .post-meta {
      margin-bottom: 20px;
      color: #909399;
      font-size: 14px;
      
      span {
        margin-right: 15px;
      }
    }
    
    .post-content {
      margin-bottom: 20px;
      line-height: 1.6;
    }
    
    .post-stats {
      color: #909399;
      font-size: 14px;
      
      span {
        margin-right: 15px;
      }
    }
  }
  
  .comment-detail {
    .comment-meta {
      margin-bottom: 15px;
      color: #909399;
      font-size: 14px;
      
      span {
        margin-right: 15px;
      }
    }
    
    .comment-post {
      margin-bottom: 15px;
      font-weight: bold;
    }
    
    .comment-content {
      margin-bottom: 15px;
      padding: 15px;
      background-color: #f5f7fa;
      border-radius: 4px;
      line-height: 1.6;
    }
    
    .comment-stats {
      color: #909399;
      font-size: 14px;
    }
  }
}
</style>
