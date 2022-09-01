<template>
  <el-row class="min-h-[90vh] m-5 shadow-lg shadow-dark-500 bg-slate-500">
    <el-col :lg="12" class="flex items-center justify-center flex-col">
      <manager-login-title />
    </el-col>
    <el-col :lg="12" class="bg-light-700"><manager-login-form /></el-col>
  </el-row>
</template>
<script setup lang="ts">
import managerLoginTitle from "./components/managerLoginTitle.vue";
import managerLoginForm from "./components/managerLoginForm.vue";
</script>
<style></style>
