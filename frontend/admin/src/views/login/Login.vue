<template>
  <div class="login-container">
    <el-form
      ref="loginFormRef"
      :model="loginForm"
      :rules="loginRules"
      class="login-form"
      autocomplete="on"
      label-position="left"
    >
      <div class="title-container">
        <h3 class="title">Dreamy Hive 管理系统</h3>
      </div>

      <el-form-item prop="username">
        <el-input
          v-model="loginForm.username"
          placeholder="用户名"
          type="text"
          tabindex="1"
          autocomplete="on"
          :disabled="loading"
        >
          <template #prefix>
            <el-icon><User /></el-icon>
          </template>
        </el-input>
      </el-form-item>

      <el-form-item prop="password">
        <el-input
          v-model="loginForm.password"
          :type="passwordVisible ? 'text' : 'password'"
          placeholder="密码"
          tabindex="2"
          autocomplete="on"
          :disabled="loading"
          @keyup.enter="handleLogin"
        >
          <template #prefix>
            <el-icon><Lock /></el-icon>
          </template>
          <template #suffix>
            <el-icon
              class="show-pwd"
              @click="passwordVisible = !passwordVisible"
            >
              <View v-if="passwordVisible" />
              <Hide v-else />
            </el-icon>
          </template>
        </el-input>
      </el-form-item>

      <el-button
        :loading="loading"
        type="primary"
        style="width: 100%; margin-bottom: 30px"
        @click="handleLogin"
      >
        {{ loading ? '登录中...' : '登录' }}
      </el-button>

      <div class="tips">
        <span style="margin-right: 20px;">测试账号: admin</span>
        <span>测试密码: 123456</span>
      </div>
    </el-form>
  </div>
</template>

<script lang="ts" setup>
import { ref, reactive, onMounted } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { ElMessage } from 'element-plus'
import { User, Lock, View, Hide } from '@element-plus/icons-vue'
import type { FormInstance, FormRules } from 'element-plus'
import { useUserStore } from '../../store/modules/user'

const router = useRouter()
const route = useRoute()
const userStore = useUserStore()

const loginFormRef = ref<FormInstance>()
const loading = ref(false)
const passwordVisible = ref(false)

const loginForm = reactive({
  username: 'admin',
  password: '123456'
})

const loginRules = reactive<FormRules>({
  username: [
    { required: true, message: '请输入用户名', trigger: 'blur' },
    { min: 3, max: 20, message: '用户名长度在 3 到 20 个字符', trigger: 'blur' }
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    { min: 6, max: 20, message: '密码长度在 6 到 20 个字符', trigger: 'blur' }
  ]
})

const handleLogin = async () => {
  if (!loginFormRef.value) return
  
  await loginFormRef.value.validate(async (valid) => {
    if (valid) {      loading.value = true
      try {
        await userStore.login(loginForm.username, loginForm.password)
        
        // 获取重定向路径
        const redirect = route.query.redirect as string || '/'
        
        ElMessage({
          message: '登录成功',
          type: 'success'
        })
        
        // 延迟跳转，让用户看到成功消息
        setTimeout(() => {
          router.push(redirect)
        }, 500)
        
      } catch (error: any) {
        console.error('登录失败:', error)
        ElMessage({
          message: error.message || '用户名或密码错误，请重试',
          type: 'error',
          duration: 3000
        })
      } finally {
        loading.value = false
      }
    }
  })
}

// 检查是否已登录
onMounted(() => {
  if (userStore.isLoggedIn) {
    const redirect = route.query.redirect as string || '/'
    router.push(redirect)
  }
})
</script>

<style lang="scss" scoped>
.login-container {
  min-height: 100vh;
  width: 100%;
  background-color: #2d3a4b;
  overflow: hidden;
  display: flex;
  justify-content: center;
  align-items: center;

  .login-form {
    position: relative;
    width: 520px;
    max-width: 100%;
    padding: 160px 35px 0;
    margin: 0 auto;
    overflow: hidden;
  }

  .title-container {
    position: relative;
    margin-bottom: 40px;

    .title {
      font-size: 26px;
      color: #eee;
      margin: 0 auto 40px;
      text-align: center;
      font-weight: bold;
    }
  }

  .tips {
    font-size: 14px;
    color: #889aa4;
    margin-bottom: 10px;
    text-align: center;
  }

  :deep(.el-input) {
    height: 48px;
    
    input {
      height: 48px;
      background: transparent;
      border: 0;
      border-radius: 0;
      padding: 12px 5px 12px 15px;
      color: #eee;
      caret-color: #fff;
      
      &:-webkit-autofill {
        box-shadow: 0 0 0 1000px #283443 inset !important;
        -webkit-text-fill-color: #fff !important;
      }
    }
    
    .el-input__wrapper {
      background-color: rgba(0, 0, 0, 0.1);
      border: 1px solid rgba(255, 255, 255, 0.1);
      border-radius: 5px;
      color: #eee;
    }
    
    .el-input__prefix {
      color: #889aa4;
    }
  }

  .show-pwd {
    color: #889aa4;
    cursor: pointer;
    user-select: none;
  }
}
</style>
