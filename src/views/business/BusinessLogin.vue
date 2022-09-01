<template>
  <el-row class="min-h-screen bg-sky-500">
    <el-col :lg="16" class="flex items-center justify-center flex-col">
      <div class="font-bold text-5xl text-light-50 mb-4">欢迎回来</div>
      <div class="text-gray-200 text-sm">
        本系统是智能化共享租赁平台，在租赁前，请先登录本系统。
      </div>
    </el-col>
    <el-col
      :lg="8"
      class="flex items-center justify-center flex-col bg-light-50"
    >
      <h2 class="font-bold text-2xl text-gray-600">欢迎尊贵商家回来</h2>
      <div class="flex items-center justify-center my-5">
        <span class="h-[1px] w-50 bg-gray-200"></span>
      </div>
      <div>
        <el-form style="max-width: 460px" ref="loginFormRef"
          :model="loginForm"
          :rules="rules"
          label-width="120px"
          class="mr-20"
          >
          <el-form-item label="账号">
            <el-input placeholder="请输入你的账号..." v-model="loginForm.username"/>
          </el-form-item>
          <el-form-item label="密码">
            <el-input placeholder="请输入你的密码..." v-model="loginForm.password" type="password"/>
          </el-form-item>
          <el-form-item label="验证码">
            <el-input  placeholder="请输入你的验证码..." v-model="loginForm.code"/>
             <span
              class="absolute right-0 top-0 cursor-pointer"
              @click="refreshCode"
            >
              <Captcha :timestamp="timestamp" />
            </span>
          </el-form-item>
        </el-form>
      </div>
        <div>
          <el-button
            type="success"
            class="w-50 my-2"
            round
            @click="submitForm(loginFormRef)"
            >登录</el-button
          >
        </div>
        <div>
          <el-button
            @click="$router.go(-1)"
            type="primary"
            class="w-50 my-2"
            round
            >返回上一页</el-button
          >
        </div>
      
    </el-col>
  </el-row>
</template>
<script setup lang="ts">
import { businessLogin } from "@/api/user";
import { reactive,ref} from "vue";
import { userStore } from "@/store/user";
import router from "@/router";
import Captcha from "@/components/Captcha.vue";
import { ElMessage, FormInstance, FormRules } from "element-plus";
const timestamp = ref(new Date().getTime().toString());
const loginFormRef = ref<FormInstance>();
const rules = reactive<FormRules>({
  username: [
    { required: true, message: "请输入账号", trigger: "blur" },
    { min: 8, max: 40, message: "账号长度8-30位", trigger: "blur" },
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
const loginForm = reactive({
    username:'',
    password:'',
    code:''
})
const submitForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return; console.log(loginForm) 
  await businessLogin({
    email: loginForm.username,
    password: loginForm.password,
    code: loginForm.code,
  }).then((res) => {
    if (res.status == 200) {
      const userstore = userStore();
      const data = res.data;
      userstore.setInfo(data.info);
      userstore.setToken(data.tokenHeader, data.token);
      router.push("/isrpBusiness");
      ElMessage({
        message: "登陆成功",
        type: "success",
        duration: 5 * 1000,
      });
    }
  });
};
const refreshCode = () => {
  timestamp.value = new Date().getTime().toString();
};

</script>