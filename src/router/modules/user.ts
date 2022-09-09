import { RouteRecordRaw } from "vue-router";

// 用户路由
export const user: Array<RouteRecordRaw> = [

  {
    // 登录页面
    path: "/userLogin",
    name: "/userLogin",
    component: () => import("@/views/login.vue"),
  },
  
];
