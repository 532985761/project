<template>
  <div v-if="loading == true">
    <div
      v-loading="loading"
      style="width: 100%; margin-top: 50px"
      element-loading-text="Loading..."
    ></div>
  </div>
  <div v-else>
    <div v-if="total == 0">
      <el-empty style="width: 100%" />
    </div>
    <div v-else>
      <el-table :data="tableData" style="width: 100%" height="380">
        <el-table-column prop="userId" label="用户编号" width="150" />
        <el-table-column prop="nickname" label="用户昵称" width="150" />
        <el-table-column prop="headerImg" label="用户头像" width="150">
          <template #default="scope">
            <el-image
              :src="scope.row.headerImg"
              style="height: 50px"
            ></el-image>
          </template>
        </el-table-column>
        <el-table-column prop="role" label="用户角色" width="150">
          <template #default="scope">
            <div v-if="scope.row.role == 0">用户</div>
            <div v-if="scope.row.role == 1">商家</div>
            <div v-if="scope.row.role == 2">管理员</div>
          </template>
        </el-table-column>
        <el-table-column
          prop="phone"
          label="用户电话"
          width="200"
        ></el-table-column>
        <el-table-column
          prop="email"
          label="用户邮箱"
          width="150"
          fixed
        ></el-table-column>
        <el-table-column
          prop="idCardNum"
          label="用户身份证"
          width="200"
        ></el-table-column>
        <el-table-column prop="sex" label="用户性别" width="150">
          <template #default="scope">
            <div v-if="scope.row.sex == 0">女</div>
            <div v-if="scope.row.sex == 1">男</div>
            <div v-if="scope.row.sex == 2">未知</div>
          </template>
        </el-table-column>
        <el-table-column prop="status" label="用户状态" width="150">
          <template #default="scope">
            <div class="text-red-500" v-if="scope.row.status == 0">禁用</div>
            <div class="text-green-500" v-if="scope.row.status == 1">正常</div>
          </template>
        </el-table-column>
        <el-table-column
          prop="addressCity"
          label="用户城市"
          width="200"
        ></el-table-column>
        <el-table-column prop="birth" label="用户生日" width="150">
          <template #default="scope">
            <div v-if="scope.row.birth == null">未知</div>
            <div v-else>{{ scope.row.birth }}</div>
          </template>
        </el-table-column>
        <el-table-column
          prop="createTime"
          label="注册时间"
          width="200"
        ></el-table-column>
        <el-table-column
          prop="createTime"
          label="个性签名"
          width="200"
        ></el-table-column>
        <el-table-column label="操作" width="100" fixed="right">
          <template #default="scope">
            <el-button
              link
              type="danger"
              size="small"
              @click.prevent="deleteRow(scope.row)"
            >
              删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <div class="flex justify-center m-1">
        <el-dropdown size="small" @command="changePageSize">
          <el-button size="small">
            页大小: {{ pageSize
            }}<el-icon class="el-icon--right"><arrow-down /></el-icon>
          </el-button>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item
                v-for="(item, index) in pageSizeArray"
                :key="index"
                :command="item"
                >{{ item }}</el-dropdown-item
              >
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </div>
      <div class="flex justify-center">
        <el-pagination
          :currentPage="currentPage"
          :page-size="pageSize"
          :small="small"
          :disabled="disabled"
          :background="background"
          layout="total, prev, pager, next"
          :total="total"
          @current-change="handleCurrentChange"
        />
      </div>
    </div>
  </div>
</template>
<script lang="ts" setup>
import { onMounted, watch, ref, toRef } from "vue";
import { queryUserByPage, deleteUser } from "@/api/user";
import { ElMessage, ElMessageBox } from "element-plus";

const props = defineProps({
  timestamp: String,
});

let timeStamp = toRef(props, "timestamp");
const tableData = ref([]);
const small = ref(false);
const disabled = ref(false);
const background = ref(false);
const currentPage = ref(1);
const pageSize = ref(5);
const total = ref(0);
const loading = ref(true);
const pageSizeArray = [
  5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
];

onMounted(() => {
  initData();
});

watch(timeStamp, () => {
  initData();
});

const initData = () => {
  loading.value = true;
  queryUserByPage(currentPage.value, pageSize.value).then((res) => {
    if (res.status == 200) {
      tableData.value = res.data.content;
      total.value = res.data.totalElements;
      currentPage.value = res.data.pageable.pageNumber + 1;
      pageSize.value = res.data.pageable.pageSize;
    }
    loading.value = false;
  });
};
const changePageSize = (command: any) => {
  pageSize.value = command;
  initData();
};
const handleCurrentChange = (val: number) => {
  currentPage.value = val;
  initData();
};
const deleteRow = (row: any) => {
  ElMessageBox.confirm(`您确定删除订单模式： ${row.email} ?`, "提示", {
    confirmButtonText: "确定",
    cancelButtonText: "取消",
  })
    .then(() => {
      deleteUser(row.userId).then((res) => {
        if (res.status == 200) {
          initData();
          ElMessage({
            message: "删除成功",
            type: "success",
            duration: 2000,
          });
        }
      });
    })
    .catch(() => {
      // catch error
    });
};
</script>
