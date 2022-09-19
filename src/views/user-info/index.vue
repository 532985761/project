<template>
  <el-form :model="form" label-width="120px">
    <el-form-item label="用户名">
      <el-input disabled  v-model="form.username" />
    </el-form-item>
    <el-form-item label="密码">
      <el-input v-model="form.password"  type="password" />
    </el-form-item>
    <el-form-item label="确认密码">
      <el-input v-model="form.checkpass" type="password" />
    </el-form-item>
    <el-form-item label="个性签名">
      <el-input v-model="form.userSign" />
    </el-form-item>
    <el-form-item label="邮箱">
      <el-input v-model="form.email" />
    </el-form-item>


    <el-form-item label="性别">
      <el-radio-group v-model="form.sex" @change="changeR">
        <el-radio label="男" />
        <el-radio label="女"  />
      </el-radio-group>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit">修改</el-button>
    </el-form-item>
  </el-form>
</template>

<script lang="ts" setup>
import {onMounted, reactive, ref} from 'vue'
import http from '@/axios'
import {ElMessage} from "element-plus";
import router from '@/router'
import {userStore} from "@/store/user";
import internal from "stream";
// do not use same name with ref
const form:any = ref({
  username:"",
  password:"",
  sign:"",
  sex:"",
  email:"",

})
const  userstore = userStore()

const init = ()=>{
  http.get("/user/getUserById/"+userstore.info.id).then((r)=>{
    form.value = r.data;
    if (form.value.sex == 1){
      form.value.sex='男'
    }if (form.value.sex ==0 ){
      form.value.sex='女'
    }
  })
}

onMounted(async ()=>{
init();
})
const sex = ref();
const onSubmit = () => {
  if (form.value.sex == '男'){
    form.value.sex=1
  }if (form.value.sex == '女'){
    form.value.sex=0
  }
  if (form.value.checkpass){
    if (form.value.password != form.value.checkpass){
      ElMessage({
        message: "密码不一致",
        type: "warning",
        duration: 2 * 1000,
      });
      return false
    }
  }

  http.post("/user/updateUser",form.value).then(()=>{
    ElMessage({
      message: "修改成功",
      type: "success",
      duration: 2 * 1000,
    });
    init()
  })
}
const changeR=(r)=>{

  console.log(r)
}

</script>
