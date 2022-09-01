<template>
  <el-button
    @click="$router.push('/isrpManager/userid=isrp_grouptwo/8888/orderModel')"
    >订单模式管理</el-button
  >
  <el-button
    type="primary"
    @click="$router.push('/isrpManager/userid=isrp_grouptwo/8888/orderProcess')"
    >订单流程管理</el-button
  >
  <el-button
    type="info"
    @click="$router.push('/isrpManager/userid=isrp_grouptwo/8888/user')"
    >用户管理</el-button
  >
  <el-button type="danger" @click="logout">退出</el-button>
</template>
<script lang="ts" setup>
import router from "@/router";
import { userStore } from "@/store/user";
import { ElMessage } from "element-plus";

const logout = () => {
  userStore().logout();
  router.push(
    "/managerLogin/userid=isrp_grouptwo/password=wbw2022isrp_grouptwo/8888"
  );
  ElMessage({
    message: "退出成功",
    type: "success",
    duration: 2 * 1000,
  });
};
</script>
