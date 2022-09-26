<template>
  <el-table :data="form"  style="width: 100%"   ref="multipleTableRef"


            @selection-change="handleSelectionChange">
    <el-table-column type="selection" width="55" />

    <el-table-column label="货物名字"  >
      <template #default="scope">
        {{scope.row.goodsName}}
      </template>
    </el-table-column>

  </el-table>
  <div style="margin-top: 20px">
    <span>请选择需要调拨的仓库</span>
    <el-select v-model="twoValue" class="m-2" placeholder="Select" size="large">
      <el-option
          v-for="item in twoOption"
          :key="item.warehouseId"
          :label="item.warehouseName"
          :value="item.warehouseId"
      />
    </el-select>
    <el-button type="primary" @click="confirmChange">确认调拨</el-button>
  </div>
</template>

<script lang="ts" setup>
import {onMounted, reactive, ref} from 'vue'
import http from '@/axios'
import {ElMessage,ElTable} from "element-plus";
import router from '@/router'
import {userStore} from "@/store/user";
import internal from "stream";
import QS from "qs";

const form:any = ref([])
const  userstore = userStore()
const twoValue = ref(1)
const twoOption = ref([
  {
    "warehouseId":1,
    "warehouseName":1

  }
])
const init = ()=>{
  http.get("/goods/waitWare/"+userstore.info.id).then((r)=>{
    form.value = r.data
    console.log(r.data)
  })
  http.get("/ware/getWareSelectByUserId").then((res)=>{

    twoOption.value = res.data

  })
}

onMounted(async ()=>{
  init();
})
const dialogVisible = ref(false)
const goods:any = ref({})
const getGoodsById = async (id)=>{
  http.get("/goods/getGoodsById/"+id).then((r)=>{

    goods.value = r.data
  })
  dialogVisible.value = true;
}
const editGoods = async (id)=>{

  http.post("/goods/updateGoods",goods.value).then((r)=>{
    ElMessage({
      message: "操作成功",
      type: "success",
      duration: 2 * 1000,
    });
        init();
  })
  dialogVisible.value = false;
}
const multipleTableRef = ref<InstanceType<typeof ElTable>>()

const multipleSelection = ref()
const handleSelectionChange = (val) => {
  multipleSelection.value = val
  console.log(val)
}
const confirmChange =()=>{
  let ids:any = [];
  multipleSelection.value.forEach((r:any)=>{
    ids.push(r.goodsId)
  })

  let params = new URLSearchParams();
  params.append('id',ids);
  if (ids.length == 0){
    ElMessage({
      message: "请选择",
      type: "warning",
      duration: 2 * 1000,
    })
    return false;
  }
  http.post("/ware/intoWare/"+twoValue.value,QS.stringify({'id': ids}, {arrayFormat: 'brackets'})).then(()=>{
    init();
    router.go(0)
  })
}
</script>
