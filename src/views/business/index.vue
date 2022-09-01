<template>
  <div>
    <h3 class="text-3xl text-shadow-md">
      欢迎来到厂家管理中心界面！
    </h3>
  </div>
  <el-divider />
  <div>
    <p class="text-xl text-shadow-md">平台商品展示</p>
  </div>
  
  <el-space wrap style="margin-left:110px;margin-top: 35px;">
    <el-card v-for="i in 8" :key="i" class="box-card" style="width: 250px">
      <template #header>
        <div class="card-header">
          <span>共享单车</span>
          <p style="color:red;margin-left: 65px;">154元/小时</p>
          <el-button class="button" text type="primary" style="margin-left:136px">商品详情</el-button>
        </div>
      </template>
      <div v-for="o in 1" :key="o" class="text item">
        <img src="../../assets/222.jpg" alt="">
      </div>
    </el-card>
  </el-space>
</template>
<script lang="ts" setup>

</script>
