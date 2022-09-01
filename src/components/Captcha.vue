<template>
  <div>
    <div v-if="codeImg == ''">获取验证码</div>
    <div v-else><img :src="codeImg" /></div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, watch, toRef } from "vue";
import { captcha } from "@/api/user";

let codeImg = ref();

const props = defineProps({
  timestamp: String,
});

let timeStamp = toRef(props, "timestamp");

watch(timeStamp, (newValue, oldValue) => {
  captcha().then((res) => {
    if (res.status == 200) {
      codeImg.value = window.URL.createObjectURL(res.data);
    }
  });
});

onMounted(async () => {
  await captcha().then((res) => {
    if (res.status == 200) {
      codeImg.value = window.URL.createObjectURL(res.data);
    }
  });
});
</script>
