<template>
  <div>
    <div class="text-2xl font-bold flex items-center justify-center mt-30">
      当前页面不存在，请返回
    </div>
    <div class="flex items-center justify-center">
      <el-empty description="404 NotFound">
        <el-button @click="$router.go(-1)" type="primary">返回上一页</el-button>
      </el-empty>
    </div>
  </div>
</template>
