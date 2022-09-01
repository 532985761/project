<template>
  <el-button class="mt-4" style="width: 100%" @click="onAddItem"
    >添加用户</el-button
  >
  <el-drawer v-model="drawer" :direction="direction">
    <template #title>
      <h4>添加用户</h4>
    </template>
    <template #default>
      <div>
        <el-form
          ref="ruleFormRef"
          :model="ruleForm"
          label-width="100px"
          class="demo-ruleForm"
          :rules="rules"
          size="small"
          label-position="top"
        >
          <el-form-item label="用户昵称" prop="nickname">
            <el-input
              v-model.string="ruleForm.nickname"
              type="text"
              autocomplete="off"
              placeholder="请输入昵称"
            />
          </el-form-item>
          <el-form-item label="用户头像" prop="headerImg">
            <el-input
              v-model.string="ruleForm.headerImg"
              type="text"
              autocomplete="off"
              placeholder="请输入头像链接"
              ><template #prepend>Http://</template></el-input
            >
          </el-form-item>
          <el-form-item label="用户密码" prop="password">
            <el-input
              v-model.string="ruleForm.password"
              type="password"
              show-password
              autocomplete="off"
              placeholder="请输入密码"
            />
          </el-form-item>
          <el-form-item label="用户角色" prop="role">
            <el-select v-model="ruleForm.role" placeholder="请选择角色">
              <el-option label="用户" value="0" />
              <el-option label="商家" value="1" />
              <el-option label="管理员" value="2" />
            </el-select>
          </el-form-item>
          <el-form-item label="用户电话" prop="phone">
            <el-input
              v-model.string="ruleForm.phone"
              type="text"
              autocomplete="off"
              placeholder="请输入电话"
            />
          </el-form-item>
          <el-form-item label="用户邮箱" prop="email">
            <el-input
              v-model.string="ruleForm.email"
              type="email"
              autocomplete="off"
              placeholder="请输入邮箱"
            />
          </el-form-item>
          <el-form-item label="用户身份证" prop="idCardNum">
            <el-input
              v-model.string="ruleForm.idCardNum"
              type="text"
              autocomplete="off"
              placeholder="请输入用户身份证"
            />
          </el-form-item>
          <el-form-item label="用户性别" prop="sex">
            <el-select v-model="ruleForm.sex" placeholder="请选择性别">
              <el-option label="女" value="0" />
              <el-option label="男" value="1" />
              <el-option label="未知" value="2" />
            </el-select>
          </el-form-item>
          <el-form-item label="城市选择" prop="addressCity">
            <el-cascader
              placeholder="请选择城市"
              :options="regionData"
              v-model="ruleForm.addressCity"
            />
          </el-form-item>
          <el-form-item label="生日" prop="birth">
            <el-date-picker
              v-model="ruleForm.birth"
              type="date"
              placeholder="选择出生日期"
            />
          </el-form-item>
          <el-form-item label="个性签名" prop="sign">
            <el-input
              v-model.string="ruleForm.sign"
              type="text"
              autocomplete="off"
              placeholder="请输入个性签名"
            />
          </el-form-item>
        </el-form>
      </div>
    </template>
    <template #footer>
      <div style="flex: auto">
        <el-button type="primary" @click="confirmClick(ruleFormRef)"
          >确定</el-button
        >
        <el-button @click="cancelClick(ruleFormRef)">取消</el-button>
      </div>
    </template>
  </el-drawer>
</template>
<script lang="ts" setup>
import { ref, reactive } from "vue";
import { ElMessage, ElMessageBox, FormInstance, FormRules } from "element-plus";
import { addUser } from "@/api/user";
import { regionData, CodeToText } from "element-china-area-data";
import moment from "moment";

const drawer = ref(false);
const direction = ref("rtl");
const ruleFormRef = ref<FormInstance>();
const ruleForm = reactive({
  nickname: "",
  headerImg: "",
  password: "",
  role: "",
  phone: "",
  email: "",
  idCardNum: "",
  sex: "",
  addressCity: "",
  birth: "",
  sign: "",
});
const rules = reactive<FormRules>({
  email: [
    { required: true, message: "请输入邮箱", trigger: "blur" },
    {
      pattern: /^([0-9A-Za-z\-_\.]+)@([0-9a-z]+\.[a-z]{2,3}(\.[a-z]{2})?)$/g,
      message: "请输入正确的邮箱",
      trigger: "blur",
    },
  ],
  nickname: [{ required: true, message: "请输入昵称", trigger: "blur" }],
  password: [
    { required: true, message: "请输入密码", trigger: "blur" },
    { min: 6, max: 20, message: "密码长度在6-20位", trigger: "blur" },
  ],
  role: [{ required: true, message: "请输入角色", trigger: "blur" }],
  phone: [
    {
      pattern: /^((0\d{2,3}-\d{7,8})|(1[3584]\d{9}))$/,
      message: "请输入合法手机号/电话号",
      trigger: "blur",
    },
  ],
  idCardNum: [
    {
      pattern:
        /(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}$)/,
      message: "请输入合法身份证号",
      trigger: "blur",
    },
  ],
});

const emits = defineEmits(["getTimeStamp"]);

const confirmClick = (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  formEl.validate((valid) => {
    if (valid) {
      let userForm = JSON.parse(JSON.stringify(ruleForm));
      if (userForm.addressCity != null && userForm.addressCity != "") {
        userForm.addressCity = codeToCity(userForm.addressCity);
      }
      if (userForm.birth != null && userForm.birth != "") {
        userForm.birth = moment(userForm.birth).format("YYYY-MM-DD HH:mm:ss");
      }
      if (userForm.headerImg != null && userForm.headerImg != "") {
        userForm.headerImg = "http://" + userForm.headerImg;
      }
      ElMessageBox.confirm(`您确定添加用户： ${userForm.email} ?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
      })
        .then(() => {
          addUser(userForm).then((res) => {
            if (res.status == 200) {
              emits("getTimeStamp", new Date().getTime().toString());
              ElMessage({
                message: "添加成功",
                type: "success",
                duration: 2000,
              });
              drawer.value = false;
            }
          });
        })
        .catch(() => {
          // catch error
        });
    } else {
      console.log("error submit!");
      return false;
    }
  });
};

const cancelClick = (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  drawer.value = false;
};

const onAddItem = () => {
  drawer.value = true;
};

const codeToCity = (res: any) => {
  return CodeToText[res[2]];
};
</script>
