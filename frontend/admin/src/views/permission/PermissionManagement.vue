<template>
  <div class="permission-management-container">
    <el-card class="box-card">
      <template #header>
        <div class="card-header">
          <span>权限列表</span>
          <el-button type="primary" @click="handleAddPermission">新增权限</el-button>
        </div>
      </template>

      <!-- TODO: Filter/Search Area (Optional for now) -->

      <el-table v-loading="loading" :data="permissionList" border style="width: 100%">
        <el-table-column type="index" width="50" label="#" />
        <el-table-column prop="name" label="权限名称" min-width="150" />
        <el-table-column prop="permission" label="权限标识" min-width="180" />
        <el-table-column prop="type" label="类型" width="100">
          <template #default="scope">
            <el-tag :type="scope.row.type === 1 ? 'success' : scope.row.type === 2 ? 'warning' : 'info'">
              {{ scope.row.type === 1 ? '菜单' : scope.row.type === 2 ? '按钮' : '接口' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="parentId" label="父权限ID" width="100" />
        <el-table-column prop="uri" label="路径(URI)" min-width="180" />
        <el-table-column prop="createTime" label="创建时间" width="180" />
        <el-table-column label="操作" width="200" fixed="right">
          <template #default="scope">
            <el-button type="primary" link @click="handleEditPermission(scope.row)">编辑</el-button>
            <el-button type="danger" link @click="handleDeletePermission(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <div class="pagination-container">
        <el-pagination
          v-model:current-page="queryParams.pageNum"
          v-model:page-size="queryParams.pageSize"
          :page-sizes="[10, 20, 50, 100]"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
        />
      </div>
    </el-card>

    <!-- Add/Edit Permission Dialog -->
    <el-dialog
      v-model="dialogVisible"
      :title="permissionForm.id ? '编辑权限' : '新增权限'"
      width="600px"
      @close="resetPermissionForm"
    >
      <el-form ref="permissionFormRef" :model="permissionForm" :rules="permissionRules" label-width="100px">
        <el-form-item label="权限名称" prop="name">
          <el-input v-model="permissionForm.name" placeholder="请输入权限名称" />
        </el-form-item>
        <el-form-item label="父级权限" prop="parentId">
          <el-tree-select
            v-model="permissionForm.parentId"
            :data="permissionParentOptions"
            :props="{ value: 'id', label: 'name', children: 'children' }"
            check-strictly
            clearable
            filterable
            placeholder="请选择父级权限 (留空为顶级)"
            style="width: 100%;"
          />
        </el-form-item>
        <el-form-item label="权限类型" prop="type">
          <el-radio-group v-model="permissionForm.type">
            <el-radio :label="1">菜单</el-radio>
            <el-radio :label="2">按钮</el-radio>
            <el-radio :label="3">接口</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="权限标识" prop="permission">
          <el-input v-model="permissionForm.permission" placeholder="例如: sys:user:create" />
        </el-form-item>
        <el-form-item label="路径(URI)" prop="uri">
          <el-input v-model="permissionForm.uri" placeholder="例如: /users, /users/:id" />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="submitPermissionForm">确定</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script lang="ts" setup>
import { ref, reactive, onMounted } from 'vue';
import { ElMessage, ElMessageBox, ElTreeSelect } from 'element-plus'; // ElTreeSelect might be auto-imported
import type { FormInstance, FormRules } from 'element-plus';
import { 
  getPermissionList, 
  getPermissionTree, 
  addPermission, 
  updatePermission, 
  deletePermission, 
  getPermissionDetail // Assuming getPermissionDetail exists if needed, or use row data
} from '../../api/permission';

// --- Data Properties ---
const permissionList = ref<any[]>([]);
const loading = ref(false);
const dialogVisible = ref(false);
const permissionForm = reactive({
  id: '',
  name: '',
  parentId: null as string | null, // Important: null for top-level
  type: 1, // Default to Menu
  uri: '',
  permission: '' // Permission string
});
const permissionFormRef = ref<FormInstance>();
const permissionParentOptions = ref<any[]>([]); // For el-tree-select

const queryParams = reactive({
  pageNum: 1,
  pageSize: 10,
  // Add any filter params if needed, e.g., name: ''
});
const total = ref(0);

// --- Validation Rules ---
const permissionRules = reactive<FormRules>({
  name: [{ required: true, message: '权限名称不能为空', trigger: 'blur' }],
  type: [{ required: true, message: '请选择权限类型', trigger: 'change' }],
  permission: [{ required: true, message: '权限标识不能为空', trigger: 'blur' }],
  // URI can be optional depending on type, adjust as needed
  // parentId is optional (top-level)
});

// --- Methods for Permission CRUD ---
const fetchPermissions = async () => {
  loading.value = true;
  try {
    const res = await getPermissionList(queryParams);
    permissionList.value = res.data.list || [];
    total.value = res.data.total || 0;
  } catch (error) {
    ElMessage.error('获取权限列表失败');
    console.error("fetchPermissions error:", error);
  } finally {
    loading.value = false;
  }
};

const fetchPermissionParentOptions = async () => {
  try {
    const res = await getPermissionTree(); // Fetches the hierarchical tree
    // ElTreeSelect can directly use hierarchical data if 'children' prop is correctly mapped.
    // No transformation to flat list is needed if data structure matches.
    permissionParentOptions.value = res.data || [];
  } catch (error) {
    ElMessage.error('获取父级权限选项失败');
    console.error("fetchPermissionParentOptions error:", error);
    permissionParentOptions.value = [];
  }
};

const resetPermissionForm = () => {
  permissionForm.id = '';
  permissionForm.name = '';
  permissionForm.parentId = null;
  permissionForm.type = 1;
  permissionForm.uri = '';
  permissionForm.permission = '';
  permissionFormRef.value?.resetFields();
};

const handleAddPermission = () => {
  resetPermissionForm();
  // Parent options should be loaded onMounted or ensure they are loaded before dialog opens
  // if (permissionParentOptions.value.length === 0) {
  //   fetchPermissionParentOptions(); // Optionally fetch if not loaded
  // }
  dialogVisible.value = true;
};

const handleEditPermission = async (row: any) => {
  resetPermissionForm();
  // Populate form with row data. For complex objects or if detail is needed:
  // const res = await getPermissionDetail(row.id); // if detail endpoint provides more info
  // permissionForm = { ...res.data };
  permissionForm.id = row.id;
  permissionForm.name = row.name;
  permissionForm.parentId = row.parentId || null; // Ensure null if parentId is 0 or empty string from backend
  permissionForm.type = row.type;
  permissionForm.uri = row.uri;
  permissionForm.permission = row.permission;
  
  // if (permissionParentOptions.value.length === 0) {
  //   fetchPermissionParentOptions(); // Optionally fetch if not loaded
  // }
  dialogVisible.value = true;
};

const handleDeletePermission = async (row: any) => {
  try {
    await ElMessageBox.confirm(`确定要删除权限 "${row.name}" 吗?`, '提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning',
    });
    await deletePermission(row.id);
    ElMessage.success('删除成功');
    fetchPermissions(); // Refresh list
  } catch (error) {
    if (error !== 'cancel') {
      ElMessage.error('删除失败');
      console.error("handleDeletePermission error:", error);
    }
  }
};

const submitPermissionForm = async () => {
  if (!permissionFormRef.value) return;
  await permissionFormRef.value.validate(async (valid) => {
    if (valid) {
      const dataToSubmit = { ...permissionForm };
      // Ensure parentId is null if it's an empty string or similar, 
      // backend might expect null for top-level. ElTreeSelect handles this well.
      if (!dataToSubmit.parentId) {
        dataToSubmit.parentId = null;
      }

      try {
        if (dataToSubmit.id) { // Editing existing permission
          await updatePermission(dataToSubmit.id, dataToSubmit);
          ElMessage.success('更新成功');
        } else { // Adding new permission
          // Remove id field for add operation if backend auto-generates it
          const { id, ...addData } = dataToSubmit;
          await addPermission(addData);
          ElMessage.success('添加成功');
        }
        dialogVisible.value = false;
        fetchPermissions(); // Refresh list
        // Optionally, refresh parent options if a new permission could be a parent
        // fetchPermissionParentOptions(); 
      } catch (error) {
        ElMessage.error('操作失败');
        console.error("submitPermissionForm error:", error);
      }
    }
  });
};

// --- Methods for Pagination ---
const handleSizeChange = (val: number) => {
  queryParams.pageSize = val;
  fetchPermissions();
};

const handleCurrentChange = (val: number) => {
  queryParams.pageNum = val;
  fetchPermissions();
};

// --- Lifecycle Hooks ---
onMounted(() => {
  fetchPermissions();
  fetchPermissionParentOptions(); // Fetch parent options for the form select/tree-select
});

</script>

<style lang="scss" scoped>
.permission-management-container {
  .card-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
  .pagination-container {
    margin-top: 20px;
    text-align: right;
  }
}
</style>
