<template>
  <div class="role-management-container">
    <el-card class="box-card">
      <template #header>
        <div class="card-header">
          <span>角色列表</span>
          <el-button type="primary" @click="handleAddRole">新增角色</el-button>
        </div>
      </template>

      <!-- TODO: Filter/Search Area (Optional) -->

      <el-table v-loading="loading" :data="roleList" border style="width: 100%">
        <el-table-column type="index" width="50" label="#" />
        <el-table-column prop="name" label="角色名称" min-width="150" />
        <el-table-column prop="description" label="描述" min-width="200" />
        <el-table-column prop="createTime" label="创建时间" width="180" />
        <el-table-column label="操作" width="280" fixed="right">
          <template #default="scope">
            <el-button type="primary" link @click="handleEditRole(scope.row)">编辑</el-button>
            <el-button type="danger" link @click="handleDeleteRole(scope.row)">删除</el-button>
            <el-button type="success" link @click="handleOpenAssignPermissionDialog(scope.row)">分配权限</el-button>
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

    <!-- Add/Edit Role Dialog -->
    <el-dialog
      v-model="dialogVisible"
      :title="currentRole.id ? '编辑角色' : '新增角色'"
      width="500px"
      @close="resetRoleForm"
    >
      <el-form ref="roleFormRef" :model="roleForm" :rules="roleRules" label-width="80px">
        <el-form-item label="角色名称" prop="name">
          <el-input v-model="roleForm.name" placeholder="请输入角色名称" />
        </el-form-item>
        <el-form-item label="描述" prop="description">
          <el-input v-model="roleForm.description" type="textarea" placeholder="请输入角色描述" />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="submitRoleForm">确定</el-button>
        </div>
      </template>
    </el-dialog>

    <!-- Assign Permissions Dialog (Placeholder for Phase 2) -->
    <el-dialog
      v-model="permissionDialogVisible"
      :title="'分配权限 - ' + currentRole.name"
      width="600px"
    >
      <p>权限树将在这里 (Phase 2)</p>
       <el-tree
        ref="permissionTreeRef"
        :data="allPermissionsTree"
        show-checkbox
        node-key="id"
        :props="{ label: 'name', children: 'children' }"
        :default-checked-keys="roleAssignedPermissionIds"
        check-strictly
        default-expand-all
      />
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="permissionDialogVisible = false">取消</el-button>
          <el-button type="primary" @click="submitPermissionAssignment">确定</el-button>
        </div>
      </template>
    </el-dialog>

  </div>
</template>

<script lang="ts" setup>
import { ref, reactive, onMounted } from 'vue';
import { ElMessage, ElMessageBox } from 'element-plus';
import type { FormInstance, FormRules } from 'element-plus';
import { getRoleList, addRole, updateRole, deleteRole, getRolePermissions, assignPermissionsToRole } from '../../api/role';
import { getPermissionTree } from '../../api/permission';

// --- Data Properties ---
const roleList = ref<any[]>([]);
const loading = ref(false);
const dialogVisible = ref(false); // For Add/Edit Role
const permissionDialogVisible = ref(false); // For Assign Permissions
const currentRole = ref<any>({}); // For Add/Edit form and for assigning permissions
const roleForm = reactive({ id: '', name: '', description: '' });
const roleFormRef = ref<FormInstance>();
const permissionTreeRef = ref<any>(null); // Ref for el-tree instance

const allPermissionsTree = ref<any[]>([]); // For permission assignment tree
const roleAssignedPermissionIds = ref<string[]>([]); // IDs of permissions for currentRole

const queryParams = reactive({
  pageNum: 1,
  pageSize: 10,
  // Add any filter params if needed, e.g., name: ''
});
const total = ref(0);

// --- Validation Rules ---
const roleRules = reactive<FormRules>({
  name: [{ required: true, message: '角色名称不能为空', trigger: 'blur' }],
});

// --- Methods for Role CRUD ---
const fetchRoles = async () => {
  loading.value = true;
  try {
    const res = await getRoleList(queryParams);
    roleList.value = res.data.list || [];
    total.value = res.data.total || 0;
  } catch (error) {
    ElMessage.error('获取角色列表失败');
    console.error("fetchRoles error:", error);
  } finally {
    loading.value = false;
  }
};

const resetRoleForm = () => {
  currentRole.value = {};
  roleForm.id = '';
  roleForm.name = '';
  roleForm.description = '';
  roleFormRef.value?.resetFields();
};

const handleAddRole = () => {
  resetRoleForm();
  dialogVisible.value = true;
};

const handleEditRole = (row: any) => {
  resetRoleForm();
  currentRole.value = { ...row };
  roleForm.id = row.id;
  roleForm.name = row.name;
  roleForm.description = row.description;
  dialogVisible.value = true;
};

const handleDeleteRole = async (row: any) => {
  try {
    await ElMessageBox.confirm(`确定要删除角色 "${row.name}" 吗?`, '提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning',
    });
    await deleteRole(row.id);
    ElMessage.success('删除成功');
    fetchRoles(); // Refresh list
  } catch (error) {
    if (error !== 'cancel') {
      ElMessage.error('删除失败');
      console.error("handleDeleteRole error:", error);
    }
  }
};

const submitRoleForm = async () => {
  if (!roleFormRef.value) return;
  await roleFormRef.value.validate(async (valid) => {
    if (valid) {
      try {
        if (roleForm.id) { // Editing existing role
          await updateRole(roleForm.id, roleForm);
          ElMessage.success('更新成功');
        } else { // Adding new role
          await addRole(roleForm);
          ElMessage.success('添加成功');
        }
        dialogVisible.value = false;
        fetchRoles(); // Refresh list
      } catch (error) {
        ElMessage.error('操作失败');
        console.error("submitRoleForm error:", error);
      }
    }
  });
};

// --- Methods for Pagination ---
const handleSizeChange = (val: number) => {
  queryParams.pageSize = val;
  fetchRoles();
};

const handleCurrentChange = (val: number) => {
  queryParams.pageNum = val;
  fetchRoles();
};

// --- Methods for Permission Assignment (Phase 2) ---
const fetchAllPermissionsTree = async () => {
  try {
    const res = await getPermissionTree();
    allPermissionsTree.value = res.data || [];
  } catch (error) {
    ElMessage.error('获取权限树失败');
    console.error("fetchAllPermissionsTree error:", error);
    allPermissionsTree.value = [];
  }
};

const fetchRoleAssignedPermissions = async (roleId: string) => {
  try {
    const res = await getRolePermissions(roleId);
    // Assuming res.data contains an array of permission IDs directly
    roleAssignedPermissionIds.value = res.data || [];
  } catch (error) {
    ElMessage.error('获取角色权限失败');
    console.error("fetchRoleAssignedPermissions error:", error);
    roleAssignedPermissionIds.value = [];
  }
};

const handleOpenAssignPermissionDialog = async (row: any) => {
  currentRole.value = { ...row }; // Set current role for context
  roleAssignedPermissionIds.value = []; // Reset before fetching
  
  // Fetch all available permissions if not already fetched (or fetch always if they can change)
  // For simplicity, fetching them every time dialog opens, could be optimized
  await fetchAllPermissionsTree(); 
  
  // Fetch permissions assigned to the current role
  await fetchRoleAssignedPermissions(row.id);
  
  permissionDialogVisible.value = true;
};

const submitPermissionAssignment = async () => {
  if (!currentRole.value || !currentRole.value.id) {
    ElMessage.error('未选择角色');
    return;
  }
  
  const checkedKeys = permissionTreeRef.value?.getCheckedKeys() || [];
  const halfCheckedKeys = permissionTreeRef.value?.getHalfCheckedKeys() || []; // Include half-checked (parent) nodes if desired by logic

  // Combine fully checked and half-checked keys if your backend expects all relevant parent IDs
  // const finalPermissionIds = Array.from(new Set([...checkedKeys, ...halfCheckedKeys]));
  // For now, just using fully checked keys as per typical el-tree usage for leaf permissions
  const finalPermissionIds = checkedKeys;


  try {
    await assignPermissionsToRole(currentRole.value.id, { permissionIds: finalPermissionIds });
    ElMessage.success('权限分配成功');
    permissionDialogVisible.value = false;
  } catch (error) {
    ElMessage.error('权限分配失败');
    console.error("submitPermissionAssignment error:", error);
  }
};


// --- Lifecycle Hooks ---
onMounted(() => {
  fetchRoles();
  // fetchAllPermissionsTree(); // Fetch once on mount, or fetch when dialog opens
});

</script>

<style lang="scss" scoped>
.role-management-container {
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
