<template>
  <el-table :data="form"  style="width: 100%">
    <el-table-column type="expand">
      <template #default="props">
        <div m="4">


          <h3>物品</h3>
          <el-table :data="props.row.goods" >
            <el-table-column type="index" width="50" />
            <el-table-column label="物品名字" prop="goodsName" />
            <el-table-column label="物品描述" prop="city" >
              <template #default="scope">
                <span></span>
                {{scope.row.info}}
              </template>
            </el-table-column>
            <el-table-column label="质检结果" prop="city" >
              <template #default="scope">
                <span></span>
                {{scope.row.result}}
              </template>
            </el-table-column>
            <el-table-column label="操作"  >
              <template #default="scope">
                <el-button v-if="scope.row.overdue==0" @click="checkGoodsById(scope.row.goodsId)" type="primary">申请质检</el-button>
                <el-button  v-if="scope.row.overdue==1" @click="checkGoodsById(scope.row.goodsId)" disabled type="danger">质检中</el-button>
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
</template>

<script lang="ts" setup>
import {onMounted, reactive, ref} from 'vue'
import http from '@/axios'
import {ElMessage} from "element-plus";
import router from '@/router'
import {userStore} from "@/store/user";
import internal from "stream";
// do not use same name with ref
const form:any = ref([])
const  userstore = userStore()

const init = ()=>{
  http.get("/goods/getGoodsByUserId/"+userstore.info.id).then((r)=>{
    form.value = r.data
    console.log(r.data)
  })
}

onMounted(async ()=>{
  init();
})
const dialogVisible = ref(false)
const goods:any = ref({})
const checkGoodsById = async (id)=>{
  goods.goodsId = id;
  goods.overdue = 1;
  http.post("/goods/updateGoods",goods).then((r)=>{

    goods.value = r.data
    init();
    dialogVisible.value = true;
  })

}

</script>
