<template>

  <el-table :data="tableData" height="350" style="width: 100%">
    <el-table-column type="index" width="50" label="序号" />
    <el-table-column prop="name" label="用户名字" width="180" />
    <el-table-column prop="content" label="提示内容" />
  </el-table>
</template>
<script lang="ts" setup>
import {onMounted, ref} from "vue";
import http from '@/axios'
import {ElMessage} from "element-plus";
const tableData = ref([{}])
const init = ()=>{
  http.get("/message/getMessage").then((res)=>{
    tableData.value = res.data
  })
}
onMounted(async ()=>{
  init()
})
</script>
