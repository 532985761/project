<template>
<!--  <el-button  @click="dialogVisible = true" type="primary"-->
<!--  >新增商品</el-button-->
  <!--  商品表格-->
  <el-table :data="tableData" style="width: 100%">
    <el-table-column label="序号" width="180">
      <template #default="scope">
        <div style="display: flex; align-items: center">
          <span style="margin-left: 10px">{{ scope.row.id }}</span>
        </div>
      </template>
    </el-table-column>
    <el-table-column label="用户名" width="180">
      <template #default="scope">
        <el-popover effect="light" trigger="hover" placement="top" width="auto">
          <template #default>

          </template>
          <template #reference>
            {{ scope.row.username }}
          </template>
        </el-popover>
      </template>
    </el-table-column>
    <el-table-column label="性别" width="180">
      <template #default="scope">
        <div style="display: flex; align-items: center">
          <span style="margin-left: 10px">{{ scope.row.sex === 1?'男':'女' }}</span>
        </div>
      </template>
    </el-table-column>
    <el-table-column label="零钱余额" width="180">
      <template #default="scope">
        <div style="display: flex; align-items: center">
          <span style="margin-left: 10px">{{ scope.row.balance }}</span>
        </div>
      </template>
    </el-table-column>
    <el-table-column label="积分余额" width="180">
      <template #default="scope">
        <div style="display: flex; align-items: center">
          <el-tag class="s">{{ scope.row.credit }}</el-tag>
        </div>
      </template>
    </el-table-column>

    <el-table-column label="操作">
      <template #default="scope">
        <el-button size="small" @click="handleEdit( scope.row)"
        >编辑</el-button
        >
        <el-button
            size="small"
            type="danger"
            @click="handleDelete(scope.row.id)"
        >删除</el-button
        >
      </template>
    </el-table-column>
  </el-table>
  <!--编辑商品-->
  <el-dialog
      v-model="dialogVisible1"
      title="编辑用户"
      width="30%"
  >
    <!--   内容表单-->
    <el-form :model="user" label-width="120px">
      <el-form-item label="用户名" >
        <el-input v-model="user.username" disabled />
      </el-form-item>
      <el-form-item label="零钱余额">
        <el-input  v-model.number="user.balance" />
      </el-form-item>
      <el-form-item label="积分余额">
        <el-input v-model.number="user.credit" />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible1 = false">取消</el-button>
        <el-button type="primary" @click="toEditUser"
        >确认</el-button
        >
      </span>
    </template>
  </el-dialog>
</template>

<script lang="ts" setup>
import {addGoods, getAllGoods, editGoods, deleteGoods} from "@/api/goods";
import {onMounted, reactive, ref} from "vue";
import {ElMessage} from "element-plus";
import {deleteUser, getAllUser, updateUser} from "@/api/user";

const tableData:any = ref([])
onMounted(()=>{
  getAllUser().then((res)=>{
    tableData.value = res.data;
    console.log(res.data)
  })
})
const dialogVisible = ref(false);
const dialogVisible1 = ref(false);
let user = ref({
  id: 1,
    username: "admin",
    password: "12345",
    balance: 100,
    status: 1,
    header: null,
    credit: "5"
})

const operateGoods = async ()=>{

  await addGoods(user.value).then(()=>{
    ElMessage({
      message: "操作成功",
      type: "success",
      duration: 2 * 1000,
    });
    getAllGoods().then((res)=>{
      tableData.value = res.data;
      console.log(res.data)
    })
  })
}
const toEditUser = async ()=>{

  await updateUser(user.value).then(()=>{
    ElMessage({
      message: "操作成功",
      type: "success",
      duration: 2 * 1000,
    });

  })
}
const handleDelete = async (id)=>{
  await deleteUser(id).then(()=>{
    ElMessage({
      message: "操作成功",
      type: "success",
      duration: 2 * 1000,
    });
    getAllUser().then((res)=>{
      tableData.value = res.data;
      console.log(res.data)
    })
  })
}
const handleEdit = async (row)=>{
  user.value = row;
  dialogVisible1.value = true;
}
</script>
