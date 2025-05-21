<template>
  <div class="category-container">
    <el-card class="box-card">
      <template #header>
        <div class="card-header">
          <span>分类管理</span>
          <el-button type="primary" @click="handleAdd">新增分类</el-button>
        </div>
      </template>

      <el-table
        v-loading="loading"
        :data="categoryList"
        row-key="id"
        border
        default-expand-all
        :tree-props="{ children: 'children', hasChildren: 'hasChildren' }"
      >
        <el-table-column prop="name" label="分类名称" min-width="200" />
        <el-table-column prop="sort" label="排序" width="80" />
        <el-table-column prop="status" label="状态" width="100">
          <template #default="scope">
            <el-tag :type="scope.row.status === '1' ? 'success' : 'info'">
              {{ scope.row.status === '1' ? '启用' : '禁用' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="createTime" label="创建时间" width="160" />
        <el-table-column label="操作" width="200" fixed="right">
          <template #default="scope">
            <el-button type="primary" link @click="handleEdit(scope.row)">编辑</el-button>
            <el-button
              type="primary"
              link
              @click="handleAdd(scope.row)"
            >
              添加子分类
            </el-button>
            <el-button
              type="primary"
              link
              :type="scope.row.status === '1' ? 'danger' : 'success'"
              @click="handleStatusChange(scope.row)"
            >
              {{ scope.row.status === '1' ? '禁用' : '启用' }}
            </el-button>
            <el-button
              type="danger"
              link
              @click="handleDelete(scope.row)"
              :disabled="scope.row.children && scope.row.children.length > 0"
            >
              删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <!-- 分类表单对话框 -->
    <el-dialog
      v-model="dialogVisible"
      :title="dialogType === 'add' ? '添加分类' : '编辑分类'"
      width="500px"
      append-to-body
    >
      <el-form
        ref="formRef"
        :model="form"
        :rules="rules"
        label-width="100px"
      >
        <el-form-item label="上级分类" prop="parentId">
          <el-tree-select
            v-model="form.parentId"
            :data="categoryOptions"
            check-strictly
            default-expand-all
            node-key="id"
            :props="{
              label: 'name',
              children: 'children',
              value: 'id'
            }"
            placeholder="请选择上级分类"
            style="width: 100%"
          />
        </el-form-item>
        <el-form-item label="分类名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入分类名称" />
        </el-form-item>
        <el-form-item label="排序" prop="sort">
          <el-input-number v-model="form.sort" :min="0" :max="999" style="width: 100%" />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-radio-group v-model="form.status">
            <el-radio label="1">启用</el-radio>
            <el-radio label="0">禁用</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="图标" prop="icon">
          <el-input v-model="form.icon" placeholder="请输入图标类名" />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="submitForm">确定</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script lang="ts" setup>
import { ref, reactive, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import type { FormInstance, FormRules } from 'element-plus'
import { getCategoryTree, getCategoryDetail, addCategory, updateCategory, deleteCategory } from '../../api/category'

const loading = ref(false)
const dialogVisible = ref(false)
const dialogType = ref<'add' | 'edit'>('add')
const formRef = ref<FormInstance>()
const categoryList = ref<any[]>([])
const categoryOptions = ref<any[]>([])

// 表单数据
const form = reactive({
  id: '',
  parentId: '0', // 默认为顶级分类
  name: '',
  sort: 0,
  status: '1',
  icon: ''
})

// 表单验证规则
const rules = reactive<FormRules>({
  name: [
    { required: true, message: '请输入分类名称', trigger: 'blur' },
    { min: 2, max: 50, message: '长度在 2 到 50 个字符', trigger: 'blur' }
  ],
  sort: [
    { required: true, message: '请输入排序值', trigger: 'blur' }
  ]
})

// 获取分类树
const getCategoryData = async () => {
  loading.value = true
  try {
    const res = await getCategoryTree()
    categoryList.value = res.data
    // 构建分类选项树，添加一个"顶级分类"选项
    categoryOptions.value = [
      {
        id: '0',
        name: '顶级分类',
        children: [...res.data]
      }
    ]
  } catch (error) {
    console.error('获取分类列表失败', error)
  } finally {
    loading.value = false
  }
}

// 重置表单
const resetForm = () => {
  form.id = ''
  form.parentId = '0'
  form.name = ''
  form.sort = 0
  form.status = '1'
  form.icon = ''
  formRef.value?.resetFields()
}

// 添加分类
const handleAdd = (row?: any) => {
  resetForm()
  dialogType.value = 'add'
  dialogVisible.value = true
  
  // 如果是添加子分类，设置父级ID
  if (row) {
    form.parentId = row.id
  }
}

// 编辑分类
const handleEdit = async (row: any) => {
  resetForm()
  dialogType.value = 'edit'
  
  try {
    const res = await getCategoryDetail(row.id)
    const categoryData = res.data
    
    // 填充表单数据
    Object.keys(form).forEach(key => {
      if (key in categoryData) {
        form[key as keyof typeof form] = categoryData[key]
      }
    })
    
    dialogVisible.value = true
  } catch (error) {
    console.error('获取分类详情失败', error)
    ElMessage.error('获取分类详情失败')
  }
}

// 修改状态
const handleStatusChange = async (row: any) => {
  const newStatus = row.status === '1' ? '0' : '1'
  const statusText = newStatus === '1' ? '启用' : '禁用'
  
  try {
    await ElMessageBox.confirm(`确认要${statusText}该分类吗?`, '提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    })
    
    await updateCategory(row.id, { status: newStatus })
    ElMessage.success(`${statusText}成功`)
    getCategoryData()
  } catch (error: any) {
    if (error !== 'cancel') {
      ElMessage.error(`${statusText}失败: ${error.message}`)
    }
  }
}

// 删除分类
const handleDelete = async (row: any) => {
  // 如果有子分类，不允许删除
  if (row.children && row.children.length > 0) {
    ElMessage.warning('该分类下有子分类，不能删除')
    return
  }
  
  try {
    await ElMessageBox.confirm('确认要删除该分类吗?', '提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    })
    
    await deleteCategory(row.id)
    ElMessage.success('删除成功')
    getCategoryData()
  } catch (error: any) {
    if (error !== 'cancel') {
      ElMessage.error(`删除失败: ${error.message}`)
    }
  }
}

// 提交表单
const submitForm = async () => {
  if (!formRef.value) return
  
  await formRef.value.validate(async (valid) => {
    if (valid) {
      try {
        if (dialogType.value === 'add') {
          await addCategory(form)
          ElMessage.success('添加分类成功')
        } else {
          await updateCategory(form.id, form)
          ElMessage.success('更新分类成功')
        }
        dialogVisible.value = false
        getCategoryData()
      } catch (error: any) {
        ElMessage.error(error.message || '操作失败')
      }
    }
  })
}

onMounted(() => {
  getCategoryData()
})
</script>

<style lang="scss" scoped>
.category-container {
  .card-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
}
</style>
