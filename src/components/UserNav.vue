<template>
  <div class="common-layout">
    <el-container>
      <el-header class="bg-slate-600">
        <div style="text-align: center">
          <span style="    color: white;line-height: 53px;font-size: 26px;">欢迎使用仓库储存系统</span>
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
                  style="height: 650px"
                  router
              >
                <div style="margin-left: 25px;;color: white">欢迎你，<span>{{userstore.info.username}}</span>
                </div>


                <el-menu-item index="/user/userInfo">
                  <el-icon>
                    <CreditCard /></el-icon>
                  <span>个人信息</span>
                </el-menu-item>
                <el-menu-item index="3" >
                  <el-icon><document /></el-icon>
                  <span>仓库货物详情</span>
                </el-menu-item>

                <el-menu-item index="5">
                  <el-icon><setting /></el-icon>
                  <span>仓库租借</span>
                </el-menu-item>
                <el-menu-item index="7">
                  <el-icon><setting /></el-icon>
                  <span>仓库调拨</span>
                </el-menu-item>
                <el-menu-item index="8">
                  <el-icon><setting /></el-icon>
                  <span>货物质检</span>
                </el-menu-item>
                <el-menu-item index="6">
                  <el-icon><CloseBold /></el-icon>
                  <span @click="$router.push('/')">点击退出</span>
                </el-menu-item>
              </el-menu>
            </el-col>
          </el-row>
        </el-aside><el-main>  <el-card><router-view></router-view></el-card>
      </el-main>

      </el-container>
    </el-container>
  </div>
</template>
<script lang="ts" setup>
import { userStore } from "@/store/user";
import {onMounted, ref} from "vue";

const userstore = userStore();
</script>
