<template>
  <div class="common-layout">
    <el-container>
      <el-header class="bg-slate-600">
        <div style="text-align: center">
          <span style="    color: white;line-height: 53px;font-size: 26px;">欢迎来到仓库储存系统后台管理中心</span>
        </div>
      </el-header>
      <el-container  >
        <el-aside width="200px">
          <el-row>

            <el-col :span="24">
              <el-menu
                  active-text-color="#ffd04b"
                  background-color="#545c64"
                  class="el-menu-vertical-demo"
                  default-active="2"
                  text-color="#fff"
                  style="height: 700px"
                  router
              >
                <div style="margin-left: 25px;;color: white">欢迎你，<span>{{userstore.info.username}}</span>
                </div>

                <el-sub-menu index="1">
                  <template #title>
                    <el-icon><location /></el-icon>
                    <span>仓库信息</span>
                  </template>
                  <el-menu-item-group title="Group One">
                    <el-menu-item index="/manage/wareinfo">查看仓库信息</el-menu-item>
                  </el-menu-item-group>

                </el-sub-menu>
                <el-menu-item index="/manage/rent">
                  <el-icon>
                    <CreditCard /></el-icon>
                  <span>仓库租借管理</span>
                </el-menu-item>
                <el-menu-item index="/manage/transfer" >
                  <el-icon><document /></el-icon>
                  <span>仓库调拨</span>
                </el-menu-item>

                <el-sub-menu index="4">
                  <template #title>
                    <el-icon><location /></el-icon>
                    <span>库存变更</span>
                  </template>
                  <el-menu-item-group title="Group One">
                    <el-menu-item index="4-1">查看入库信息</el-menu-item>
                    <el-menu-item index="4-2">查看出库信息</el-menu-item>
                  </el-menu-item-group>

                </el-sub-menu>
                <el-menu-item index="5">
                  <el-icon><setting /></el-icon>
                  <span>货物质检</span>
                </el-menu-item>
                <el-menu-item index="/home">
                  <el-icon><CloseBold /></el-icon>
                  <span >点击退出</span>
                </el-menu-item>
              </el-menu>
            </el-col>
          </el-row>
        </el-aside><el-main> <el-card><router-view></router-view> </el-card>
      </el-main>

      </el-container>
    </el-container>
  </div>
</template>
<script lang="ts" setup>
import { userStore } from "@/store/user";
import {onMounted, ref} from "vue";
import router from '@/router'
const userstore = userStore();
</script>
