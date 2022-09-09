import { RouteRecordRaw } from "vue-router";

// 管理端路由
export const manager: Array<RouteRecordRaw> = [
  {
    // 管理员登陆页面
    path: "/managerLogin",
    component: () => import("@/views/manager-login/index.vue"),
  },
  {
    path: "/manage",
    component: () => import("@/components/ManagerNav.vue"),
    redirect: "/manage/index",
    children: [
        {
        // 管理员首页
        path: "index",
        name: 'index',
        component: () => import("@/views/manager-home/index.vue"),
      },

    ],
  },
];
