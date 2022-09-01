<template>
  <el-menu
    class="el-menu-demo"
    mode="horizontal"
    :ellipsis="false"
    background-color="#545c64"
    text-color="#fff"
  >
    <el-menu-item index="0">
      <el-image
        style="width: 50px; height: 50px; border-radius: 15px"
        src="/src/assets/znzzlogo.png"
        class="mt-0.5"
      />
      <span class="ml-3"> 欢迎来到智租网平台</span></el-menu-item
    >
    <div class="flex-grow" />
    <router-link to="/isrpUser/userCart"
      ><el-menu-item index="1">我的购物车</el-menu-item></router-link
    >

    <el-menu-item index="3"> </el-menu-item>

    <el-sub-menu index="2">
      <template #title>
        <el-avatar
          class="mt-2"
          :size="40"
          src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
        /><span class="ml-3">欢迎你：{{ nickname }}</span>
      </template>
      <el-menu-item index="2-1"
        ><el-icon><UserFilled /></el-icon>个人信息</el-menu-item
      >
      <el-menu-item index="2-2"
        ><el-icon><Shop /></el-icon>我的订单</el-menu-item
      >
      <el-menu-item index="2-3" @click="logout">
        <el-icon><CircleCloseFilled /></el-icon>退出登录</el-menu-item
      >
    </el-sub-menu>
  </el-menu>

  
  <el-row class="bg-light-600">
    <el-col :span="24"
      ><div class="grid-content ep-bg-purple-dark">
        <el-divider>
          <span style="font-weight: bold" class="italic text-2xl">
            欢迎光临<el-icon><star-filled /></el-icon>智能租租网</span
          >
        </el-divider>
      </div></el-col
    >
  </el-row>

  <router-view></router-view>
  <!-- 底部 -->
  <el-footer>
    <el-divider>
      <span class="italic text-lg">CopyRight By @grouptwo</span>
      <span style="font-weight: bold" class="italic text-2xl"
        >智能化共享租赁平台</span
      >
      <span style="font-weight: bold" class="italic text-2xl text-blue-500">
        <el-icon><star-filled /></el-icon>欢迎您的到来</span
      >
    </el-divider></el-footer
  >
</template>

<script lang="ts" setup>
import index from "@/views/user/index.vue";
import { ref, computed } from "vue";
import { userStore } from "@/store/user";
import { Search } from "@element-plus/icons-vue";
import type { TabsPaneContext } from "element-plus";
import router from "@/router";
import { ElMessage } from "element-plus";
import VueEvent from "@/utils/event";
import { useRouter } from "vue-router";
let keyWord = ref("");
// 退出
const userstore = userStore();
const logout = () => {
  router.push("/userLogin");
  userStore().logout();
  ElMessage({
    message: "退出成功",
    type: "success",
    duration: 2 * 1000,
  });
};

const nickname = userstore.info.nickname;
const activeName = ref("5");
const handleClick = (tab: TabsPaneContext, event: Event) => {
  if (tab.props.name == 5) {
    router.push("/isrpUser");
  }
  if (tab.props.name == 3) {
    router.push("/isrpUser/goodsdetail");
  }
  if (tab.props.name == 6) {
    router.push("/isrpUser/rentCenter/1/1");
  }
};
//激活导航栏
VueEvent.on("tomsg", (value: any) => {
  activeName.value = value.data;
});
//显示div
const showDiv = ref(true);
VueEvent.on("showDiv", (value: any) => {
  showDiv.value = value.data;
});
</script>

<style>
.flex-grow {
  flex-grow: 1;
}
.el-col {
  border-radius: 4px;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}

@keyframes kenburns-top {
  0% {
    transform: scale(0.7) translateY(0);
    transform-origin: 50% 16%;
  }
  100% {
    transform: scale(1.25) translateY(-15px);
    transform-origin: top;
  }
}
.kenburns-top {
  animation: kenburns-top 8s ease-out both;
}
</style>
