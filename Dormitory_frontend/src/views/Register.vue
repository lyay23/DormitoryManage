<template>
  <div class="login-container">
    <div class="system-title-container">
      <span class="system-main-title">宿舍管理系统</span>
      <span class="system-sub-title">学生注册</span>
    </div>
    <div class="login-box">
      <el-form :model="registerForm" :rules="rules" ref="registerFormRef" size="large">
        <el-form-item prop="username">
          <el-input v-model="registerForm.username" placeholder="请输入学号" prefix-icon="User"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input type="password" v-model="registerForm.password" placeholder="请输入密码" prefix-icon="Lock" show-password></el-input>
        </el-form-item>
        <el-form-item prop="confirmPassword">
          <el-input type="password" v-model="registerForm.confirmPassword" placeholder="请再次输入密码" prefix-icon="Lock" show-password></el-input>
        </el-form-item>
        <el-form-item prop="name">
          <el-input v-model="registerForm.name" placeholder="请输入姓名" prefix-icon="User"></el-input>
        </el-form-item>
        <el-form-item prop="gender">
          <el-select v-model="registerForm.gender" placeholder="请选择性别" style="width: 100%">
            <el-option label="男" value="男"></el-option>
            <el-option label="女" value="女"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="age">
          <el-input-number v-model="registerForm.age" :min="1" :max="100" placeholder="请输入年龄(1-100岁)" style="width: 100%"></el-input-number>
        </el-form-item>
        <el-form-item prop="phoneNum">
          <el-input v-model="registerForm.phoneNum" placeholder="请输入手机号" prefix-icon="Phone"></el-input>
        </el-form-item>
        <el-form-item prop="email">
          <el-input v-model="registerForm.email" placeholder="请输入邮箱" prefix-icon="Message"></el-input>
        </el-form-item>
        <el-form-item class="login-buttons">
          <el-button type="primary" @click="handleSubmit" style="width: 48%;">注册</el-button>
          <el-button @click="goToLogin" style="width: 48%;">返回登录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import request from '@/utils/request'; // 假设你有一个封装好的request工具

export default {
  name: 'Register',
  data() {
    const validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (this.registerForm.confirmPassword !== '') {
          this.$refs.registerFormRef.validateField('confirmPassword');
        }
        callback();
      }
    };
    const validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.registerForm.password) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {
      registerForm: {
        username: '',
        password: '',
        confirmPassword: '',
        name: '',
        gender: '',
        age: 18,
        phoneNum: '',
        email: ''
      },
      rules: {
        username: [
          { required: true, message: '请输入学号', trigger: 'blur' },
          { min: 3, message: '学号长度至少为3位', trigger: 'blur' },
        ],
        password: [
          { required: true, validator: validatePass, trigger: 'blur' },
          { min: 6, message: '密码长度至少为6位', trigger: 'blur' },
        ],
        confirmPassword: [
          { required: true, validator: validatePass2, trigger: 'blur' },
        ],
        name: [
          { required: true, message: '请输入姓名', trigger: 'blur' },
        ],
        gender: [
          { required: true, message: '请选择性别', trigger: 'change' },
        ],
        age: [
          { required: true, message: '请输入年龄', trigger: 'blur' },
          { type: 'number', min: 1, max: 100, message: '年龄必须在1-100岁之间', trigger: 'blur' }
        ],
        phoneNum: [
          { required: true, message: '请输入手机号', trigger: 'blur' },
          { pattern: /^1[3-9]\d{9}$/, message: '请输入正确的手机号', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '请输入邮箱', trigger: 'blur' },
          { type: 'email', message: '请输入正确的邮箱地址', trigger: 'blur' }
        ]
      },
    };
  },
  methods: {
    handleSubmit() {
      this.$refs.registerFormRef.validate((valid) => {
        if (valid) {
          // 调用注册接口
          request.post('/stu/register', {
            username: this.registerForm.username,
            password: this.registerForm.password,
            name: this.registerForm.name,
            gender: this.registerForm.gender,
            age: this.registerForm.age,
            phoneNum: this.registerForm.phoneNum,
            email: this.registerForm.email
          }).then(res => {
            if (res.code === '0' || res.code === 0 || res.success ) { // 根据你的后端Result格式调整
              this.$message.success('注册成功！将跳转到登录页');
              setTimeout(() => {
                this.$router.push('/login');
              }, 1500);
            } else {
              this.$message.error(res.msg || '注册失败');
            }
          }).catch(err => {
            console.error('注册请求失败:', err);
            this.$message.error('注册请求失败，请检查网络或联系管理员');
          });
        } else {
          console.log('表单验证失败');
          return false;
        }
      });
    },
    goToLogin() {
      this.$router.push('/login'); // 假设登录页路由为 /login
    }
  },
};
</script>

<style scoped>
.login-container {
  position: fixed;
  height: 100%;
  width: 100%;
  top: 0;
  left: 0;
  background-color: #f0f2f5; /* 浅灰色背景 */
  display: flex;
  flex-direction: column; /* 允许标题和登录框垂直排列 */
  align-items: center;
  justify-content: center;
}

.system-title-container {
  text-align: center;
  margin-bottom: 20px; /* 系统标题与登录框的间距 */
}

.system-main-title {
  font-size: 28px; /* 主系统标题字号 */
  color: #303133;
  font-weight: bold;
  display: block; /* 让副标题换行 */
  margin-bottom: 5px;
}

.system-sub-title {
  font-size: 20px; /* 副标题（如登录/注册）字号 */
  color: #606266;
}

.login-box {
  width: 400px;
  padding: 30px 40px;
  background-color: #ffffff;
  border-radius: 8px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.login-buttons {
    display: flex;
    justify-content: space-between;
}
</style> 