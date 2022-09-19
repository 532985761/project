import { RouteRecordRaw } from "vue-router";

// 用户路由
export const user: Array<RouteRecordRaw> = [

  {
    // 登录页面
    path: "/user",
    name: "userInfo",
    component: () => import("@/components/UserNav.vue"),
    redirect:"/user/userInfo",
    children:[
      {
        path: "userInfo",
        name: "userInfo",
        component: () => import("@/views/user-info/index.vue"),
      },
    ]
  },

];
