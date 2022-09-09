<template>
  <div class="common-layout">
    <el-container>
      <el-header class="bg-slate-600">
        <div style="text-align: center">
          <span style="    color: white;line-height: 53px;font-size: 26px;">欢迎来到仓库储存系统后台管理中心</span>
        </div>
      </el-header>
      <el-container>
        <el-aside width="200px" >
          <el-row>
            <el-col :span="24">
              <el-menu
                  active-text-color="#ffd04b"
                  background-color="#545c64"
                  class="el-menu-vertical-demo"
                  default-active="2"
                  text-color="#fff"
                  @open="handleOpen"
                  @close="handleClose"
              >
                <div style="margin-left: 25px;;color: white">欢迎你，<span>{{userstore.info.username}}</span>
                </div>

                <el-sub-menu index="1">
                  <template #title>
                    <el-icon><location /></el-icon>
                    <span>Navigator One</span>
                  </template>
                  <el-menu-item-group title="Group One">
                    <el-menu-item index="1-1">item one</el-menu-item>
                    <el-menu-item index="1-2">item two</el-menu-item>
                  </el-menu-item-group>
                  <el-menu-item-group title="Group Two">
                    <el-menu-item index="1-3">item three</el-menu-item>
                  </el-menu-item-group>
                  <el-sub-menu index="1-4">
                    <template #title>item four</template>
                    <el-menu-item index="1-4-1">item one</el-menu-item>
                  </el-sub-menu>
                </el-sub-menu>
                <el-menu-item index="2">
                  <el-icon><icon-menu /></el-icon>
                  <span>Navigator Two</span>
                </el-menu-item>
                <el-menu-item index="3" disabled>
                  <el-icon><document /></el-icon>
                  <span>Navigator Three</span>
                </el-menu-item>
                <el-menu-item index="4">
                  <el-icon><setting /></el-icon>
                  <span>Navigator Four</span>
                </el-menu-item>
                <el-menu-item index="4">
                  <el-icon><CloseBold /></el-icon>
                  <span>点击退出</span>
                </el-menu-item>
              </el-menu>
            </el-col>
          </el-row>
        </el-aside>
        <el-main>Main</el-main>
      </el-container>
    </el-container>
  </div>
</template>
<script lang="ts" setup>
import { userStore } from "@/store/user";
import {onMounted, ref} from "vue";



const userstore = userStore();
</script>
