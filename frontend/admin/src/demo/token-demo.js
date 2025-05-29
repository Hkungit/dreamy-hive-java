// Token管理功能演示脚本
// 可以在浏览器控制台中运行此脚本来测试token管理功能

console.log('🚀 开始Token管理功能演示...\n');

// 1. 导入工具函数（在实际项目中）
// import { getToken, setToken, clearAuth, saveLoginInfo } from '@/utils/auth'
// import { useUserStore } from '@/store/modules/user'

// 2. 模拟登录响应数据
const mockLoginResponse = {
  success: true,
  code: 200,
  message: "操作成功",
  data: {
    tokenName: "satoken",
    tokenValue: "5025a1e0-f40a-43fe-9bba-c515f73f8237",
    isLogin: true,
    loginId: "1",
    loginType: "login",
    tokenTimeout: 2592000,
    sessionTimeout: 2592000,
    tokenSessionTimeout: -2,
    tokenActiveTimeout: 3600,
    loginDeviceType: "DEF",
    tag: null
  }
};

console.log('📊 模拟登录响应数据:');
console.log(JSON.stringify(mockLoginResponse, null, 2));
console.log('\n');

// 3. 演示token保存
console.log('💾 Token保存演示:');
localStorage.setItem('token', mockLoginResponse.data.tokenValue);
localStorage.setItem('tokenName', mockLoginResponse.data.tokenName);
localStorage.setItem('loginId', mockLoginResponse.data.loginId);

console.log('✅ Token已保存到localStorage');
console.log('Token:', localStorage.getItem('token'));
console.log('TokenName:', localStorage.getItem('tokenName'));
console.log('LoginId:', localStorage.getItem('loginId'));
console.log('\n');

// 4. 演示请求头构建
console.log('🔧 请求头构建演示:');
const token = localStorage.getItem('token');
const tokenName = localStorage.getItem('tokenName') || 'satoken';

const headers = {};
if (token) {
  headers[tokenName] = token;
}

console.log('构建的请求头:', headers);
console.log('\n');

// 5. 演示登录状态检查
console.log('🔍 登录状态检查:');
const isLoggedIn = !!localStorage.getItem('token');
console.log('是否已登录:', isLoggedIn);
console.log('\n');

// 6. 演示清除认证信息
console.log('🧹 清除认证信息演示:');
function clearAuth() {
  localStorage.removeItem('token');
  localStorage.removeItem('tokenName');
  localStorage.removeItem('loginId');
  console.log('✅ 认证信息已清除');
}

// 可以调用 clearAuth() 来清除认证信息

console.log('🎉 Token管理功能演示完成！');
console.log('\n📝 使用说明:');
console.log('1. 登录时系统会自动保存token到localStorage');
console.log('2. 所有API请求会自动在请求头中添加token');
console.log('3. 401错误会自动清除token并跳转到登录页');
console.log('4. 页面刷新后会自动恢复登录状态');
console.log('\n🔧 可在控制台运行 clearAuth() 来清除认证信息');
