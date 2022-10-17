<template>
  <el-table :data="form"  style="width: 100%"   ref="multipleTableRef">
    <el-table-column label="仓库名字"  >
      <template #default="scope">
        {{scope.row.ware.warehouseName}}
      </template>
    </el-table-column>
    <el-table-column label="仓库拥有者名字"  >
      <template #default="scope">
        <span v-if="scope.row.user">   {{scope.row.user.username}}</span>

      </template>
    </el-table-column>
    <el-table-column label="租借开始时间"  >
      <template #default="scope">
        <span v-if="scope.row.user">   {{scope.row.ware.creatTime}}</span>

      </template>
    </el-table-column>
    <el-table-column label="租借结束时间"  >
      <template #default="scope">
        <span v-if="scope.row.user">   {{scope.row.ware.rentTime}}</span>

      </template>
    </el-table-column>
    <el-table-column label="仓库租借状态"  >
      <template #default="scope">
        <span v-if="scope.row.ware.status == 0"><span style="display: inline-block;width: 15px;height: 15px;background-color:#27f50a;"></span>可租用</span>
        <span v-if="scope.row.ware.status == 1"><span style="display: inline-block;width: 15px;height: 15px;background-color:#ff0000;"></span>不可租用</span>
      </template>
    </el-table-column>
    <el-table-column label="操作"  >
      <template #default="scope">
        <el-button v-if="scope.row.ware.status == 0" @click="getId(scope.row.ware.warehouseId)">租用</el-button>
        <el-button v-if="scope.row.ware.status == 1" disabled>不可租用</el-button>
      </template>
    </el-table-column>

  </el-table>
  <el-dialog
      v-model="dialogVisible"
      title="选择时间段"
      width="30%"

  >
    <div class="block">
      <span class="demonstration">Default</span>
      <el-date-picker
          v-model="dataRent"
          type="datetimerange"
          range-separator="To"
          start-placeholder="Start date"
          end-placeholder="End date"
      />
    </div>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">Cancel</el-button>
        <el-button type="primary" @click="confirmRent"
        >Confirm</el-button
        >
      </span>
    </template>
  </el-dialog>
</template>

<script lang="ts" setup>
import {onMounted, reactive, ref} from 'vue'
import http from '@/axios'
import {ElMessage,ElTable} from "element-plus";
import router from '@/router'
import {userStore} from "@/store/user";
import internal from "stream";
import QS from "qs";
import dateFormat from "@/utils/dateFormet";
const dataRent = ref<[Date, Date]>([
  new Date(),
  new Date(),
])
const form:any = ref([])
const  userstore = userStore()

const init = ()=>{
  http.get("/ware/wareRentInfo").then((r)=>{
    form.value = r.data
    console.log(r.data)
  })
}

onMounted(async ()=>{
  init();
})
const dialogVisible = ref(false)
let wareId:any= 0;

const getId = (id) =>{
  wareId = id;
  dialogVisible.value = true;
}
const confirmRent=()=>{
  console.log(dataRent.value[0]<new Date())
  //关于日期的判断
  //判断①起始日期小于当前日期
  if (dataRent.value[0]<new Date()){
    ElMessage({
      message: "起始日期有误",
      type: "warning",
      duration: 2 * 1000,
    })
    return false;
  }
  //起始日期大于结束日期
  if (dataRent.value[0]>dataRent.value[1]){
    ElMessage({
      message: "起止日期有误",
      type: "warning",
      duration: 2 * 1000,
    })
    return false;
  }
  const wh:any = {};
  wh.warehouseId = wareId;
  wh.creatTime = dateFormat(dataRent.value[0],'yyyy-MM-dd hh:mm:ss')
  wh.rentTime = dateFormat(dataRent.value[1],'yyyy-MM-dd hh:mm:ss')
  wh.userId = userstore.info.id
  http.post("/ware/rent",wh).then((r)=>{
    ElMessage({
      message: "操作成功！",
      type: "success",
      duration: 2 * 1000,
    })
    init();
    dialogVisible.value = false;

  })
}
</script>
