<template>
  <el-button type="primary" style="margin-left: 16px" @click="drawer = true">
    发布通知
  </el-button>

  <el-drawer v-model="drawer" title="I am the title" :with-header="false">
    <span>在此发布通知</span>
    <el-form :model="notice" label-width="120px">
      <el-form-item label="标题">
        <el-input v-model="notice.title" />
      </el-form-item>
      <el-form-item label="内容">
        <el-input v-model="notice.content" />
      </el-form-item>
    </el-form>
    <el-button type="primary" @click="submitPost">确认发布</el-button>
  </el-drawer>
  <el-row>
    <el-col
        v-for="(o, index) in list"
        :key="o"
        :span="6"
        :offset="1"
    >
      <el-card :body-style="{ padding: '0px' }" style="margin-top: 15px">

        <div style="padding: 14px">
          <h1 style="font-size: 18px;color: #562a2a">{{o.title}}</h1>
          <span>{{o.content}}</span>
          <div class="bottom">
            <time class="time">{{ o.createTime }}</time>


          </div>
          <el-button text class="button" style="color: red" @click="toDeleteNotice(o.id)">删除</el-button>
        </div>
      </el-card>
    </el-col>
  </el-row>
</template>

<script lang="ts" setup>
import {onMounted, ref} from 'vue'
import {getNotice} from "@/api/data.js";
import {deleteNotice,addNotice} from "@/api/notice";
import {ElMessage} from "element-plus";

const list = ref([])
const drawer = ref(false)
onMounted(async ()=>{
  await getNotice().then(res=>{
    list.value = res.data
  })
})
const toDeleteNotice = async (id)=>{
  await deleteNotice(id).then(()=>{
        ElMessage({
          message: "操作成功",
          type: "success",
          duration: 2 * 1000,
        });getNotice().then(res=>{
          list.value = res.data
        })
      }
  )
}
const notice = ref({})
const submitPost = async ()=>{
  await addNotice(notice.value).then(()=>{
    ElMessage({
      message: "操作成功",
      type: "success",
      duration: 2 * 1000,
    });getNotice().then(res=>{
      list.value = res.data
    })
  })
}
</script>

<style>
.time {
  font-size: 12px;
  color: #999;
}

.bottom {
  margin-top: 13px;
  line-height: 12px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.button {
  padding: 0;
  min-height: auto;
}

.image {
  width: 100%;
  display: block;
}
</style>
