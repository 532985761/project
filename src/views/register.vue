<template>
  <el-dialog
      v-model="centerDialogVisible"
      title="欢迎来到仓库储存系统注册中心"
      width="45%"
      center
      :close-on-click-modal="false"
  >
    <el-form
        v-loading="loading"
        element-loading-text="正在为您注册账号，请耐心等候！"
        ref="ruleFormRef"
        :model="ruleForm"
        status-icon
        :rules="rules"
        label-width="120px"
        class="demo-ruleForm"
    >
      <el-form-item label="账户" prop="email">
        <el-input v-model="ruleForm.email" placeholder="请输入用户名" />
      </el-form-item>
      <el-form-item label="密码" prop="pass">
        <el-input
            v-model="ruleForm.pass"
            type="password"
            autocomplete="off"
            placeholder="请输入密码"
        />
      </el-form-item>
      <el-form-item label="确认密码" prop="checkPass">
        <el-input
            v-model="ruleForm.checkPass"
            type="password"
            placeholder="请确认密码"
            autocomplete="off"
        />
      </el-form-item>

    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button
            @click="centerDialogVisible = false"
            class="w-60"
            type="warning"
        >取消</el-button
        >
        <el-button
            type="primary"
            class="w-60"
            @click="confirmRegister"
            v-loading="loading"
        >确认注册</el-button
        >
      </span>
    </template>
  </el-dialog>
</template>
<script lang="ts" setup>
import { ref, reactive, onMounted } from "vue";
import { ElButton, ElDialog } from "element-plus";
import { CircleCloseFilled } from "@element-plus/icons-vue";
import { testGoodsApi } from "@/api/goods";
import { getOneUserInfo, registerUser } from "@/api/user";
import { ElMessage } from "element-plus";
import type { FormInstance } from "element-plus";
let loading = ref(false);


//注册表单开始
const ruleForm = reactive({
  checkPass: "",
  pass: "",
  email: "",
});

let centerDialogVisible = ref(false);
const ruleFormRef = ref<FormInstance>();
const showDialog = (data: any) => {
  centerDialogVisible.value = data;
};

defineExpose({
  showDialog,
});


const validatePass = (rule: any, value: any, callback: any) => {
  if (value === "") {
    callback(new Error("请输入密码"));
  } else {
    if (ruleForm.checkPass !== "") {
      if (!ruleFormRef.value) return;
      ruleFormRef.value.validateField("checkPass", () => null);
    }
    callback();
  }
};
const validatePass2 = (rule: any, value: any, callback: any) => {
  if (value === "") {
    callback(new Error("请再次输入密码"));
  } else if (value !== ruleForm.pass) {
    callback(new Error("两次密码不匹配"));
  } else {
    callback();
  }
};

const rules = reactive({
  pass: [{ validator: validatePass, trigger: "blur" }],
  repass: [{ validator: validatePass2, trigger: "blur" }],
});

const submitForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  formEl.validate((valid) => {
    if (valid) {
      console.log("submit!");
    } else {
      console.log("error submit!");
      return false;
    }
  });
};

const resetForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  formEl.resetFields();
};
const value = ref("");


//注册表单结束

//确认注册
let data = ref({});
async function confirmRegister() {
  loading.value = true;
  let userForm = {
    username: ruleForm.email,
    pass: ruleForm.pass,

  };

  await registerUser(userForm)
      .then((res: any) => {
        if (res.status == 200) {
          ElMessage({
            message: res.data,
            type: "success",
          });
        }
      })
      .then((res: any) => {
        loading.value = false;
        centerDialogVisible.value = false;
      });
}
</script>

<style scoped>
.my-header {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
</style>
