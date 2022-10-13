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
                <el-button  @click="getGoodsById(scope.row.goodsId)" type="primary">填写质检结果</el-button>

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
  <el-dialog
      v-model="dialogVisible"
      title="Tips"
      width="30%"
      :before-close="handleClose"
  >
    <el-form :model="goods" label-width="120px">
      <el-form-item label="货物名">
        <el-input disabled  v-model="goods.goodsName" />
      </el-form-item>
      <el-form-item label="货物介绍">
        <el-input v-model="goods.result"  />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="editGoods(goods.goodsId)">修改</el-button>
      </el-form-item>
    </el-form>

  </el-dialog>
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
  http.get("/goods/getCheckGoods").then((r)=>{
    form.value = r.data
    console.log(r.data)
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
  goods.value.overdue = 0;
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

</script>
