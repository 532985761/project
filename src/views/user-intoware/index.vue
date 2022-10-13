<template>
  <el-button @click="dialogVisible = true">新增货物</el-button>
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
  <el-dialog
      v-model="dialogVisible"
      title="Tips"
      width="30%"
      :before-close="handleClose"
  >
    <el-form
        label-width="100px"
        :model="goodsForm"
        style="max-width: 460px"
    >
      <el-form-item label="货物名字">
        <el-input v-model="goodsForm.goodsName" />
      </el-form-item>
      <el-form-item label="货物大小">
        <el-input v-model="goodsForm.area" />
      </el-form-item>
      <el-form-item label="货物描述">
        <el-input v-model="goodsForm.info" />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">Cancel</el-button>
        <el-button type="primary" @click="addGoods"
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
const goodsForm:any = ref({})
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
const addGoods=()=>{
  goodsForm.value.userId = userstore.info.id;
  goodsForm.value.status = 0;
  goodsForm.value.overdue = 0;
  http.post("/goods/addGoods",goodsForm.value).then(r=>{
    dialogVisible.value = false;
    ElMessage({
      message: "success",
      type: "success",
      duration: 2 * 1000,
    })
    init();
  })
}
</script>
