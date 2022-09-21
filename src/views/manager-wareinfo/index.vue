<template>
  <div>
    <el-button type="primary" @click="dialogVisible1 = true">新增仓库</el-button>
  </div>
  <el-table :data="tableData" height="350" style="width: 100%">
    <el-table-column type="index" width="50" label="序号" />

    <el-table-column prop="warehouseName" label="仓库名字" width="180" />
    <el-table-column  label="当前租赁状态" width="180" >
      <template #default="scope">
        <span v-if="scope.row.status === 1">出租中</span>
        <span v-else>等待出租</span>
      </template>
    </el-table-column>

    <el-table-column prop="creatTime" label="出租时间" />
    <el-table-column prop="rentTime" label="到期时间" />
    <el-table-column prop="area" label="总容量" >
      <template #default="scope">
        <span >{{scope.row.area}} ㎡</span>
      </template>
    </el-table-column>
    <el-table-column prop="areaRemain" label="剩余容量"  >
      <template #default="scope">
        <span >{{scope.row.areaRemain}} ㎡</span>
      </template>
    </el-table-column>
    <el-table-column label="操作"  > <template #default="scope">

      <el-popconfirm
          title="确定要删除此项吗？"
          @confirm="deleteWare(scope.row.warehouseId)"
      >
        <template #reference>
          <el-link type="danger">删除</el-link>
        </template>
      </el-popconfirm>
</template>
    </el-table-column>
  </el-table>
<!--新增表单-->
  <el-dialog
      v-model="dialogVisible1"
      title="新增仓库"
      width="30%"
      center

  >
    <!--   内容表单-->
    <el-form :model="wareForm" label-width="120px">
      <el-form-item label="仓库名">
        <el-input v-model="wareForm.warehouseName" />
      </el-form-item>
      <el-form-item label="仓库总容量">
        <el-input  v-model.number="wareForm.area" />
      </el-form-item>
      <el-form-item label="仓库介绍">
        <el-input v-model.number="wareForm.notice" />
      </el-form-item>

    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible1 = false">取消</el-button>
        <el-button type="primary" @click="toAddWare"
        >确认</el-button
        >
      </span>
    </template>
  </el-dialog>
</template>

<script lang="ts" setup>
import {onMounted, ref} from "vue";
import http from '@/axios'
import {ElMessage} from "element-plus";
const tableData = ref([
  {
    "warehouseId": 1,
    "warehouseName": "小张家仓库",
    "creatTime": "2022-09-15T08:30:10.000+00:00",
    "rentTime": null,
    "area": 150,
    "userId": 1,
    "notice": "欢迎大家来租赁仓库",
    "status": 1,
    "areaRemain":0
  }
])
const wareForm = ref(
  {
    "warehouseName": "小张家仓库",
    "area": 150,
    "userId": 1,
    "status": 0,
    "areaRemain":0
  }
)
const dialogVisible1 = ref(false)
const init = ()=>{
  http.get("/ware/getWarehouse").then((res)=>{
    tableData.value = res.data
    console.log(res)
  })
}
onMounted(async ()=>{
  init()

})
//删除仓库
const deleteWare =  (id)=>{
  http.get("/ware/deleteWarehouse/"+id).then(()=>{
    ElMessage({
      message: "操作成功",
      type: "success",
      duration: 2 * 1000,
    });
    init()
  })
}
//新增仓库
const toAddWare = ()=>{
  http.post("/ware/addWarehouse",wareForm.value).then(()=>{
    ElMessage({
      message: "操作成功",
      type: "success",
      duration: 2 * 1000,
    });
    init()
  })
}
</script>
