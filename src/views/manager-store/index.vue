<template>
  <el-table :data="tableData" style="width: 100%" height="500">
    <el-table-column label="所属澡堂" width="180">
      <template #default="scope">
        <div style="display: flex; align-items: center">
          <span style="margin-left: 10px">{{ scope.row.bath.bathName }}</span>
        </div>
      </template>
    </el-table-column>
    <el-table-column label="设备号" width="180">
      <template #default="scope">
        <div style="display: flex; align-items: center">
          <span style="margin-left: 10px">{{ scope.row.store.modelNumber }}</span>
        </div>
      </template>
    </el-table-column>
    <el-table-column label="租金" width="180">
      <template #default="scope">
        <div style="display: flex; align-items: center">
          <span style="margin-left: 10px">{{ scope.row.store.price }}元/月</span>
        </div>
      </template>
    </el-table-column>
    <el-table-column label="所属用户" width="180">
    <template #default="scope">
      <div style="display: flex; align-items: center">
        <span style="margin-left: 10px" v-if="scope.row.user"><el-tag type="danger">{{ scope.row.user.username }}</el-tag> </span>
        <span style="margin-left: 10px" v-else><el-tag>暂无用户</el-tag> </span>
      </div>
    </template>
  </el-table-column>
    <el-table-column label="租用月数" width="180">
      <template #default="scope">
        <div style="display: flex; align-items: center">
          <span style="margin-left: 10px">{{ scope.row.store.days }}个月</span>
        </div>
      </template>
    </el-table-column>
    <el-table-column label="租用状态" width="180">
      <template #default="scope">
        <div style="display: flex; align-items: center">
          <span style="margin-left: 10px;background-color: #2cee02;width: 20px;height: 20px" v-if="scope.row.store.status === 0"></span>
          <span style="margin-left: 10px;background-color: #ec0505;width: 20px;height: 20px" v-else></span>
        </div>
      </template>
    </el-table-column>

  </el-table>
</template>

<script lang="ts" setup>
import {onMounted, ref} from "vue";
import {getStore} from "@/api/data.js";

const tableData = ref([])
onMounted(async ()=>{
  await getStore().then((res)=>{
    tableData.value = res.data
    console.log(res.data)
  });
})
</script>
