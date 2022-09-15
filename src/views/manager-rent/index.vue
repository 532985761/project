<template>

  <el-table :data="tableData" height="350" style="width: 100%">
    <el-table-column type="index" width="50" label="序号" />

    <el-table-column prop="warehouseName" label="仓库名字" width="180" />
    <el-table-column  label="当前租赁状态" width="180" >
      <template #default="scope">
        <span v-if="scope.row.status === 1">出租中</span>
        <span v-else-if="scope.row.status === 2">不可租用</span>
        <span v-else-if="scope.row.status === 0">等待出租</span>
      </template>
    </el-table-column>

    <el-table-column prop="creatTime" label="出租时间" />
    <el-table-column prop="rentTime" label="到期时间" />
    <el-table-column label="是否出租"  >
      <template #default="scope">
        <el-switch v-if="scope.row.status !==1"
            v-model="scope.row.switch"
            class="mb-2"
            style="--el-switch-on-color: #13ce66; --el-switch-off-color: #ff4949"
            active-text="可租"
            inactive-text="不可租"
            @click="toChange(scope.row)"

        />
        <span v-if="scope.row.status ===1">等待租户租用结束后操作</span>

    </template>
    </el-table-column>
  </el-table>

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
    "areaRemain":0,
    "switch":false,
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
const value2 = ref(true)

const dialogVisible1 = ref(false)
const init = ()=>{
  http.get("/ware/getWarehouse").then((res)=>{
    tableData.value = res.data
    tableData.value.forEach( r =>{
      if (r.status == 0|| r.status==1) {
        r.switch = true
      }
      if (r.status == 2) {
        r.switch = false
      }

    })
    console.log(res)
  })
}
onMounted(async ()=>{
  init()
})
//改变租赁状态
const toChange = (row)=>{
  if (row.status == 1){
    ElMessage({
      message: "请等待用户租赁结束后操作",
      type: "warning",
      duration: 2 * 1000,
    })
    return  false;
  }
  http.get("/ware/changeWarehouse/"+row.warehouseId).then((res)=>{
   ElMessage({
     message: "操作成功",
     type: "success",
     duration: 2 * 1000,
   })
    init()
  })
}
</script>
