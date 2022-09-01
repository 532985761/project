<template>
  <div class="mt-30">
    <div class="text-2xl text-dark-50 font-bold m-10">请登录</div>
    <el-form
      ref="loginFormRef"
      :model="loginForm"
      :rules="rules"
      label-width="120px"
      class="mr-20"
      :size="formSize"
      status-icon
    >
      <el-form-item label="用户名" prop="username">
        <el-input v-model="loginForm.username" />
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input type="password" show-password v-model="loginForm.password" />
      </el-form-item>
      <el-form-item label="验证码" prop="code">
        <el-input v-model="loginForm.code" />
        <span
          class="absolute right-0 top-0 cursor-pointer"
          @click="refreshCode"
        >
          <captcha :timestamp="timestamp" />
        </span>
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          @click="submitForm(loginFormRef)"
          :loading="loading"
          >登录</el-button
        >
        <el-button @click="resetForm(loginFormRef)">重填</el-button>
        <el-button @click="$router.push('/')">返回上一页</el-button>

      </el-form-item>
 
    </el-form>
  </div>
</template>
<script lang="ts" setup>
import Captcha from "@/components/Captcha.vue";
import { reactive, ref } from "vue";
import { ElMessage, FormInstance, FormRules } from "element-plus";
import { managerLogin } from "@/api/user";
import { userStore } from "@/store/user";
import router from "@/router";

const loading = ref(false);
const formSize = ref("large");
const timestamp = ref(new Date().getTime().toString());
const loginFormRef = ref<FormInstance>();
const loginForm = reactive({
  username: "",
  password: "",
  code: "",
});
const rules = reactive<FormRules>({
  username: [
    { required: true, message: "请输入账号", trigger: "blur" },
    { min: 3, max: 40, message: "账号长度3-30位", trigger: "blur" },
  ],
  password: [
    { required: true, message: "请输入密码", trigger: "blur" },
    { min: 2, max: 20, message: "密码长度为2-20位", trigger: "blur" },
  ],
  code: [
    { required: true, message: "请输入验证码", trigger: "blur" },
    { min: 4, max: 4, message: "验证码长度为4位", trigger: "blur" },
  ],
});

const submitForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  loading.value = true;
  await managerLogin({
    username: loginForm.username,
    password: loginForm.password,
    code: loginForm.code,
  }).then((res) => {
    if (res.status == 200) {
      const userstore = userStore();
      const data = res.data;
      console.log(res);
      
      userstore.setInfo(res.data);
      router.push("/manage");
      ElMessage({
        message: "登陆成功",
        type: "success",
        duration: 2 * 1000,
      });
    }
    loading.value = false;
  });
};
const resetForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  formEl.resetFields();
};
const refreshCode = () => {
  timestamp.value = new Date().getTime().toString();
};
</script>
