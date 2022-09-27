<template>
  <el-table :data="form"  style="width: 100%">
    <el-table-column type="expand">
      <template #default="props">
        <div m="4">


          <h3>物品</h3>
          <el-table :data="props.row.goods"     @selection-change="handleSelectionChange"
                    ref="multipleTableRef"          >
            <el-table-column type="selection" width="55" />
            <el-table-column type="index" width="50" />
            <el-table-column label="物品名字" prop="goodsName" />
            <el-table-column label="物品描述" prop="city" >
              <template #default="scope">
                <span></span>
                {{scope.row.info}}
              </template>
            </el-table-column>
            <el-table-column label="操作"  >
              <template #default="scope">
                <el-button @click="getGoodsById(scope.row.goodsId)">编辑</el-button>
              </template>
            </el-table-column>

          </el-table>
        </div>
      </template>
    </el-table-column>
    <el-table-column label="仓库名"  >
      <template #default="scope">
        {{scope.row.ware.warehouseName}}
      </template>
    </el-table-column>

  </el-table>
  <div style="margin-top: 20px">
    <el-button type="primary" @click="confirmChange">确认出库</el-button>
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
// do not use same name with ref
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
  http.get("/goods/getGoodsByUserId/"+userstore.info.id).then((r)=>{
    form.value = r.data


    console.log(form.value)
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

  http.post("/ware/userOutWare/"+twoValue.value,QS.stringify({'id': ids}, {arrayFormat: 'brackets'})).then(()=>{
    init();
    router.go(0)
  })
}
</script>
