<template>
  <div>
    <manager-user-title />
    <manager-user-add @get-time-stamp="getTimeStamp" />
    <manager-user-table :timestamp="timestamp" />
  </div>
</template>
<script lang="ts" setup>
import managerUserTitle from "./components/managerUserTitle.vue";
import managerUserAdd from "./components/managerUserAdd.vue";
import managerUserTable from "./components/managerUserTable.vue";
import { ref } from "vue";

const timestamp = ref("");

const getTimeStamp = (nowTime: string) => {
  timestamp.value = nowTime;
};
</script>
