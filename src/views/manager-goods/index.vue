<template>
  <el-button type="primary">新增商品</el-button>
  <el-table :data="tableData" style="width: 100%">
    <el-table-column label="序号" width="180">
      <template #default="scope">
        <div style="display: flex; align-items: center">
          <span style="margin-left: 10px">{{ scope.row.id }}</span>
        </div>
      </template>
    </el-table-column>
    <el-table-column label="商品名称" width="180">
      <template #default="scope">
        <el-popover effect="light" trigger="hover" placement="top" width="auto">
          <template #default>

          </template>
          <template #reference>
            <el-tag class="s">{{ scope.row.goodName }}</el-tag>
          </template>
        </el-popover>
      </template>
    </el-table-column>
    <el-table-column label="商品价格" width="180">
      <template #default="scope">
        <div style="display: flex; align-items: center">
          <span style="margin-left: 10px">{{ scope.row.goodPrice }}</span>
        </div>
      </template>
    </el-table-column>
    <el-table-column label="商品总量" width="180">
      <template #default="scope">
        <div style="display: flex; align-items: center">
          <span style="margin-left: 10px">{{ scope.row.goodTotal }}</span>
        </div>
      </template>
    </el-table-column>
    <el-table-column label="商品剩余量" width="180">
      <template #default="scope">
        <div style="display: flex; align-items: center">
          <span style="margin-left: 10px">{{ scope.row.goodRemain }}</span>
        </div>
      </template>
    </el-table-column>
    <el-table-column label="操作">
      <template #default="scope">
        <el-button size="small" @click="handleEdit(scope.$index, scope.row)"
        >编辑</el-button
        >
        <el-button
            size="small"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)"
        >删除</el-button
        >
      </template>
    </el-table-column>
  </el-table>
</template>

<script lang="ts" setup>
import { Timer } from '@element-plus/icons-vue'
import {getAllGoods} from "@/api/goods";
import {onMounted, ref} from "vue";

const tableData:any = ref([])
onMounted(()=>{
  getAllGoods().then((res)=>{
    tableData.value = res.data;
    console.log(res.data)
  })
})

</script>
