<template>
  <el-button  @click="dialogVisible = true" type="primary"
  >新增商品</el-button
  ><!--  商品表格-->
  <el-table :data="tableData" style="width: 100%">
    <el-table-column label="序号" width="180">
      <template #default="scope">
        <div style="display: flex; align-items: center">
          <span style="margin-left: 10px">{{ scope.row.id }}</span>
        </div>
      </template>
    </el-table-column>
    <el-table-column label="商品名称" width="180">
      <template #default="scope">
        <el-popover effect="light" trigger="hover" placement="top" width="auto">
          <template #default>

          </template>
          <template #reference>
            <el-tag class="s">{{ scope.row.goodName }}</el-tag>
          </template>
        </el-popover>
      </template>
    </el-table-column>
    <el-table-column label="商品价格" width="180">
      <template #default="scope">
        <div style="display: flex; align-items: center">
          <span style="margin-left: 10px">{{ scope.row.goodPrice }}</span>
        </div>
      </template>
    </el-table-column>
    <el-table-column label="商品总量" width="180">
      <template #default="scope">
        <div style="display: flex; align-items: center">
          <span style="margin-left: 10px">{{ scope.row.goodTotal }}</span>
        </div>
      </template>
    </el-table-column>
    <el-table-column label="积分兑换" width="180">
      <template #default="scope">
        <div style="display: flex; align-items: center">
          <span style="margin-left: 10px">{{ scope.row.goodCredit }}积分/个</span>
        </div>
      </template>
    </el-table-column>
    <el-table-column label="商品剩余量" width="180">
      <template #default="scope">
        <div style="display: flex; align-items: center">
          <span style="margin-left: 10px">{{ scope.row.goodRemain }}</span>
        </div>
      </template>
    </el-table-column>
    <el-table-column label="操作">
      <template #default="scope">
        <el-button size="small" @click="handleEdit( scope.row)"
        >编辑</el-button
        >
        <el-button
            size="small"
            type="danger"
            @click="handleDelete(scope.row.id)"
        >删除</el-button
        >
      </template>
    </el-table-column>
  </el-table>
  <!--  新增商品-->

  <el-dialog
      v-model="dialogVisible"
      title="新增商品"
      width="30%"

  >
    <!--   内容表单-->
    <el-form :model="goods" label-width="120px">
      <el-form-item label="商品名">
        <el-input v-model="goods.goodName" />
      </el-form-item>
      <el-form-item label="商品价钱">
        <el-input  v-model.number="goods.goodPrice" />
      </el-form-item>
      <el-form-item label="商品总量">
        <el-input v-model.number="goods.goodTotal" />
      </el-form-item>
      <el-form-item label="商品剩余量">
        <el-input v-model.number="goods.goodRemain" />
      </el-form-item>
      <el-form-item label="积分/个">
        <el-input v-model.number="goods.goodCredit" />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="operateGoods"
        >确认</el-button
        >
      </span>
    </template>
  </el-dialog>
<!--编辑商品-->
  <!--  新增商品-->

  <el-dialog
      v-model="dialogVisible1"
      title="编辑商品"
      width="30%"

  >
    <!--   内容表单-->
    <el-form :model="goods" label-width="120px">
      <el-form-item label="商品名">
        <el-input v-model="goods.goodName" />
      </el-form-item>
      <el-form-item label="商品价钱">
        <el-input  v-model.number="goods.goodPrice" />
      </el-form-item>
      <el-form-item label="商品总量">
        <el-input v-model.number="goods.goodTotal" />
      </el-form-item>
      <el-form-item label="商品剩余量">
        <el-input v-model.number="goods.goodRemain" />
      </el-form-item>
      <el-form-item label="积分/个">
        <el-input v-model.number="goods.goodCredit" />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible1 = false">取消</el-button>
        <el-button type="primary" @click="toEditGoods"
        >确认</el-button
        >
      </span>
    </template>
  </el-dialog>
</template>

<script lang="ts" setup>
import {addGoods, getAllGoods, editGoods, deleteGoods} from "@/api/goods";
import {onMounted, reactive, ref} from "vue";
import {ElMessage} from "element-plus";

const tableData:any = ref([])
onMounted(()=>{
  getAllGoods().then((res)=>{
    tableData.value = res.data;
    console.log(res.data)
  })
})
const dialogVisible = ref(false);
const dialogVisible1 = ref(false);
let goods = ref({
  id:1,
    goodName: "",
    goodPrice: 0.00,
    goodCredit: "",
    goodTotal: 0,
    goodRemain: "",
    goodImg: ""
})

const operateGoods = async ()=>{

  await addGoods(goods.value).then(()=>{
    ElMessage({
      message: "操作成功",
      type: "success",
      duration: 2 * 1000,
    });
    getAllGoods().then((res)=>{
      tableData.value = res.data;
      console.log(res.data)
    })
  })
}
const toEditGoods = async ()=>{

  await editGoods(goods.value).then(()=>{
    ElMessage({
      message: "操作成功",
      type: "success",
      duration: 2 * 1000,
    });

  })
}
const handleDelete = async (id)=>{
  await deleteGoods(id).then(()=>{
    ElMessage({
      message: "操作成功",
      type: "success",
      duration: 2 * 1000,
    });
    getAllGoods().then((res)=>{
      tableData.value = res.data;
      console.log(res.data)
    })
  })
}
const handleEdit = async (row)=>{
   goods.value = row;
   dialogVisible1.value = true;
}
</script>
