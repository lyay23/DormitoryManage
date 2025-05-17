<template>
    <div class="login-container">
        <div class="system-title-container">
            <span class="system-main-title">宿舍管理系统</span>
            <span class="system-sub-title">用户登录</span>
        </div>
        <div class="login-box">
            <el-form ref="form" :model="form" :rules="rules" size="large">
                <el-form-item prop="username">
                    <el-input v-model="form.username" clearable prefix-icon="avatar" placeholder="请输入用户名"></el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input v-model="form.password" prefix-icon="lock" show-password placeholder="请输入密码"></el-input>
                </el-form-item>
                <el-form-item :model="form" prop="identity">
                    <el-radio v-model="form.identity" label="stu">学生</el-radio>
                    <el-radio v-model="form.identity" label="dormManager">宿舍管理员</el-radio>
                    <el-radio v-model="form.identity" label="admin">后勤管理中心</el-radio>
                </el-form-item>
                <el-form-item class="login-buttons">
                    <el-button :disabled="!disabled" style="width: 48%;" type="primary" @click="login">登 录</el-button>
                    <el-button style="width: 48%;" type="primary" @click="goToRegister">注 册</el-button> 
                </el-form-item>
            </el-form>
        </div>
    </div>
    <!-- <div class="footer">
        ©Copyright 1958-2024 江西理工大学
    </div> -->
</template>
<script src="@/assets/js/Login.js"></script>
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

/* .login-title {
    color: #303133; 
    font-size: 24px; 
    font-weight: bold;
    text-align: center; 
    margin-bottom: 25px; 
} */

/* 调整 el-radio 标签颜色使其更清晰 */
:deep(.el-radio__label) {
  color: #606266 !important;
}

.login-buttons {
    display: flex;
    justify-content: space-between; /* 让按钮平均分布 */
}

/* 移除之前的动画和 footer (如果需要保留footer，后续可以调整样式) */
/* @keyframes myanimation {
    0% {
        background-position: 0 0;
    }
    100% {
        background-position: 100% 100%;
    }
}

.footer {
    left: 0;
    bottom: 0;
    color: #fff; 
    width: 100%;
    position: absolute;
    text-align: center;
    line-height: 30px;
    padding-bottom: 10px;
    text-shadow: #000 0.1em 0.1em 0.1em;
    font-size: 14px;
}

.footer a,
.footer span {
    color: #fff;
} */
</style>