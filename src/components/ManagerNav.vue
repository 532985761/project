<template>
  <div>
    <el-container>
      <el-header
        class="m-5 text-3xl font-bold flex justify-center items-center"
      >
        澡堂管理系统
        <span class="text-sm">| 管理平台</span>
      </el-header>
      <el-container>
        <el-aside :width="navWidth">
          <div class="text-center">
            <el-button
              :loading="loading"
              :icon="isCollapse == false ? CaretLeft : CaretRight"
              circle
              @click="(isCollapse = !isCollapse), (loading = true)"
            />
          </div>
          <el-menu
            :default-active="managerNavActive"
            :collapse="isCollapse"
            router
          >
            <el-menu-item
              index="/isrpManager/userid=isrp_grouptwo/8888"
              disabled
            >
              <el-icon><avatar /></el-icon>
              <template #title>
                欢迎您, {{ userStore().info.username }}</template
              >
            </el-menu-item>
            <el-menu-item
              index="/manage/goods"
            >
              <el-icon><tickets /></el-icon>
              <template #title>商品管理</template>
            </el-menu-item>
            <el-menu-item
              index="/manage/locate"
            >
              <el-icon><van /></el-icon>
              <template #title>澡堂位置管理</template>
            </el-menu-item>
            <el-menu-item
            index="/manage/store"
          >
            <el-icon><van /></el-icon>
            <template #title>澡堂储物柜管理</template>
          </el-menu-item>
            <el-menu-item index="/manage/user">
              <el-icon><user /></el-icon>
              <template #title>用户管理</template>
            </el-menu-item>
            <div @click="logout">
              <el-menu-item class="text-red-600">
                <el-icon><close /></el-icon>
                <template #title>退出</template>
              </el-menu-item>
            </div>
          </el-menu>
        </el-aside>
        <el-container>
          <el-main class="h-[76vh]"><router-view /></el-main>
    
        </el-container>
      </el-container>
    </el-container>
  </div>
</template>

<script lang="ts" setup>
import { ref, watch, onMounted } from "vue";
import {
  User,
  Tickets,
  Van,
  Close,
  CaretLeft,
  CaretRight,
  Avatar,
} from "@element-plus/icons-vue";
import { userStore } from "@/store/user";
import { ElMessage } from "element-plus";
import router from "@/router";

const loading = ref(false);
const isCollapse = ref(false);
const navWidth = ref("200px");
const managerNavActive = ref("/isrpManager/userid=isrp_grouptwo/8888");

onMounted(() => {
  const userstore = userStore();
  managerNavActive.value = userstore.navActive;
});

// 回调函数异常;
// const handleOpen = (key: string, keyPath: string[]) => {
//   console.log(key, keyPath, "close");
//   navWidth.value = "200px";
//   loading.value = false;
// };
// const handleClose = (key: string, keyPath: string[]) => {
//   console.log(key, keyPath, "close");
//   navWidth.value = "100px";
//   loading.value = false;
// };
watch(isCollapse, (newValue) => {
  if (newValue == true) {
    setNavWidth("70px");
  }
  if (newValue == false) {
    setNavWidth("200px");
  }
});

const setNavWidth = (width: string) => {
  setTimeout(() => {
    navWidth.value = width;
    loading.value = false;
  }, 400);
};
const logout = () => {
  userStore().logout();
  router.push(
    "/managerLogin"
  );
  ElMessage({
    message: "退出成功",
    type: "success",
    duration: 2 * 1000,
  });
};
</script>

<style scoped>
.el-menu-item.is-disabled {
  opacity: 1;
  color: #0ea5e9;
}
</style>
