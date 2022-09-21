<template>
  <span>请选择原仓库</span>
  <el-select v-model="oneValue" class="m-2" placeholder="Select" size="large"   @change="getGoods">
    <el-option
        v-for="item in oneOption"
        :key="item.warehouseId"
        :label="item.warehouseName"
        :value="item.warehouseId"

    />
  </el-select>
  <el-table
      ref="multipleTableRef"
      :data="goods"
      style="width: 100%"
      @select="selectChange"
      @selection-change="handleSelectionChange"


  >
    <el-table-column type="selection" width="55" />
    <el-table-column label="货物名称" width="120">
      <template #default="scope">{{ scope.row.goodsName }}</template>
    </el-table-column>
    <el-table-column  label="货物大小" width="120" >
      <template #default="scope">{{ scope.row.area }} ㎡</template>


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
          @change="getGoods"
      />
    </el-select>
    <el-button type="primary" @click="confirmChange">确认调拨</el-button>
  </div>
</template>

<script lang="ts" setup>
import {onMounted, ref} from 'vue'
import {ElMessage, ElTable} from 'element-plus'
import http from "@/axios";
import QS from 'qs'
import router from '@/router'
import axios from "axios";
const oneValue = ref('请选择')
const oneOption = ref([
  {
    "warehouseId":1,
    "warehouseName":1

  }

])
const twoValue = ref(1)
const twoOption = ref([
  {
    "warehouseId":1,
    "warehouseName":1

  }

])
const init = ()=>{
http.get("/ware/getWareSelect").then((res)=>{
  oneOption.value = res.data
  twoOption.value = res.data

})
}
onMounted(()=>{
  init();
})

const goods = ref([])
const getGoods = ((res)=>{
  http.get("/ware/getGoodsByWareId/"+res).then((res)=>{
    goods.value = res.data
  })
})

const multipleTableRef = ref<InstanceType<typeof ElTable>>()
const multipleSelection = ref([])
const toggleSelection = (rows) => {
  if (rows) {
    rows.forEach((row) => {
      // TODO: improvement typing when refactor table
      // eslint-disable-next-line @typescript-eslint/ban-ts-comment
      // @ts-expect-error
      multipleTableRef.value!.toggleRowSelection(row, undefined)
    })
  } else {
    multipleTableRef.value!.clearSelection()
  }
}
const handleSelectionChange = (val) => {

  multipleSelection.value = val
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
  http.post("/ware/changeWare/"+twoValue.value,QS.stringify({'id': ids}, {arrayFormat: 'brackets'})).then(()=>{
init();
router.go(0)
  })
}

</script>
