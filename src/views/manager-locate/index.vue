<template>
  <el-table :data="tableData" style="width: 100%" height="500">
    <el-table-column label="澡堂位置编号" width="180">
      <template #default="scope">
        <div style="display: flex; align-items: center">
          <span style="margin-left: 10px">{{ scope.row.site.siteNum }}</span>
        </div>
      </template>
    </el-table-column>
    <el-table-column label="所属澡堂" width="180">
      <template #default="scope">
        <div style="display: flex; align-items: center">
          <span style="margin-left: 10px">{{ scope.row.bath.bathName }}</span>
        </div>
      </template>
    </el-table-column>
    <el-table-column label="澡堂所在位置" width="220">
      <template #default="scope">
        <div style="display: flex; align-items: center">
          <span style="margin-left: 10px">{{ scope.row.bath.locate }}</span>
        </div>
      </template>
    </el-table-column>


    <el-table-column label="此位置状态" width="180">
      <template #default="scope">
        <div style="display: flex; align-items: center">
          <span style="margin-left: 10px;background-color:  #47fd00;width: 20px;height: 20px"
                v-if="scope.row.site.status === 0"></span>
          <span style="margin-left: 10px;background-color: #ec0505;width: 20px;height: 20px"
                v-else-if="scope.row.site.status === 1"></span>
          <span style="margin-left: 10px;background-color: #d36308;width: 20px;height: 20px"
                v-else-if="scope.row.site.status === 2"></span>
        </div>
      </template>
    </el-table-column>
    <el-table-column label="此位置状态" width="180">
      <template #default="scope">
        <div style="display: flex; align-items: center">
          <span style="margin-left: 10px;" v-if="scope.row.site.status === 0">可预订</span>
          <span style="margin-left: 10px;" v-else-if="scope.row.site.status === 1">已预订</span>
          <span style="margin-left: 10px;" v-else-if="scope.row.site.status === 2">待维修</span>
        </div>
      </template>
    </el-table-column>
    <el-table-column label="操作" width="180">
      <template #default="scope">
        <div style="display: flex; align-items: center" v-if="scope.row.site.status !=1">
          <el-switch
              @click="toChangeSite(scope.row.site.id)"
              v-model="scope.row.site.switch"
              class="mb-2"
              style="--el-switch-on-color:  #d36308; --el-switch-off-color: #47fd00"

              active-text="待维修"
              inactive-text="可预订"
          />
        </div>
      </template>
    </el-table-column>

  </el-table>

</template>

<script lang="ts" setup>
import {onMounted, ref} from "vue";
import {getStore} from "@/api/data.js";
import {changeSite, getSite} from "@/api/site";
import {ElMessage} from "element-plus";

const tableData = ref([{"site": {"id": 1, "bathId": 1, "siteNum": 1, "status": 1,"switch":false},
  "bath": {
    "id": 1,
    "bathName": "三期澡堂",
    "bathTotal": 5,
    "bathRemain": 5,
    "principal": "张三",
    "locate": "大连东软信息学院三期中心",
    "phone": "18856563333"
  }
},])
onMounted(async () => {
  await getSite().then((res) => {
    tableData.value = res.data

    tableData.value.forEach((r) => {
      if (r.site.status == 0) {
        r.site.switch = false
      }
      if (r.site.status == 2) {
        r.site.switch = true
      }

    })

  });
})
const value1 = ref(true)
const toChangeSite = (val)=>{
  changeSite(val).then(()=>{
    ElMessage({
      message: "操作成功",
      type: "success",
      duration: 2 * 1000,
    });
    getSite().then((res) => {
      tableData.value = res.data

      tableData.value.forEach((r) => {
        if (r.site.status == 0) {
          r.site.switch = false
        }
        if (r.site.status == 2) {
          r.site.switch = true
        }

      })

    });
  })
}
</script>
