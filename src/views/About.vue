<template>
  <h1>{{ userStore().test }}</h1>
  <h1>{{ userStore().test2 }}</h1>
  <h1>{{ goodsStore().aaa }}</h1>
  <h1>{{ goodsStore().test2 }}</h1>
  <h1>{{ ordersStore().test }}</h1>
  <h1>{{ ordersStore().test2 }}</h1>
  <div>测试初始化数据: {{ data }}</div>
  <el-button class="bg-blue-200" @click="test">测试载入pinia</el-button>
  <el-button class="bg-red-500" @click="resetTest">测试置空pinia</el-button>
  <el-button class="bg-blue-500" @click="testApi">测试axios</el-button>
  <router-view />
</template>
<script setup lang="ts">
import { userStore } from "@/store/user";
import { ordersStore } from "@/store/orders";
import { goodsStore } from "@/store/goods";
import { useCookies } from "@vueuse/integrations/useCookies";
import { testUserApi } from "@/api/user";
import { testGoodsApi } from "@/api/goods";
import { testOrderApi } from "@/api/order";
import { onMounted, ref } from "vue";

let data = ref();

onMounted(async () => {
  data.value = await testUserApi().then((res) => res.data);
});

const cookie = useCookies();
/**数据存入pinia以及cookie */

const test = () => {
  const userstore = userStore();
  const ordersstore = ordersStore();
  const goodsstore = goodsStore();
  userstore.test = "usertest";
  userstore.test2 = "usertest2";
  ordersstore.test = "orderstest";
  ordersstore.test2 = "orderstest2";
  goodsstore.aaa = "啊对对对";
  goodsstore.test2 = "goodstest2";
};
const resetTest = () => {
  const userstore = userStore();
  const ordersstore = ordersStore();
  const goodsstore = goodsStore();
  userstore.test = null;
  userstore.test2 = null;
  ordersstore.test = null;
  ordersstore.test2 = null;
  goodsstore.aaa = null;
  goodsstore.test2 = null;
};

const testApi = async () => {
  testUserApi().then((res) => {
    console.log(res);
  });
  testGoodsApi().then((res) => {
    console.log(res);
  });
  testOrderApi().then((res) => {
    console.log(res);
  });
};
</script>
