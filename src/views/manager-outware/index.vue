<template>


          <h3>物品</h3>
          <el-table :data="form" >
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
                <el-button  @click="confirmInto(scope.row.goodsId)" type="primary">确认出库</el-button>
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
  http.get("/goods/getOutGoods").then((r)=>{
    form.value = r.data
    console.log(r.data)
  })
}

onMounted(async ()=>{
  init();
})

const goods:any = ref({})
const confirmInto = async (id)=>{
  goods.value.goodsId = id;
  goods.value.status = 0;
  http.post("/goods/updateGoods",goods.value).then((r)=>{
    init()
    ElMessage({
      message: "操作成功",
      type: "success",
      duration: 2 * 1000,
    });
  })
}
</script>
