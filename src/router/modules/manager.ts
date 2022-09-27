import { RouteRecordRaw } from "vue-router";

// 管理端路由
export const manager: Array<RouteRecordRaw> = [
    {
        // 管理员首页
        path: "/home",
        name: 'home',
        component: () => import("@/views/Home.vue"),
    },
  // {
  //   // 管理员登陆页面
  //   path: "/managerLogin",
  //   component: () => import("@/views/manager-login/index.vue"),
  // },
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
        {
            // 仓库信息
            path: "wareinfo",
            name: 'wareinfo',
            component: () => import("@/views/manager-wareinfo/index.vue"),
        },
        {
            // 租借管理
            path: "rent",
            name: 'rent',
            component: () => import("@/views/manager-rent/index.vue"),
        },
        {
            // 仓库调拨
            path: "transfer",
            name: 'transfer',
            component: () => import("@/views/manager-transfer/index.vue"),
        },
        {
            // 货物质检
            path: "check",

            component: () => import("@/views/manager-check/index.vue"),
        },   {
            // 入库
            path: "intoware",

            component: () => import("@/views/manager-intoware/index.vue"),
        },  {
            // 出库
            path: "outware",

            component: () => import("@/views/manager-outware/index.vue"),
        },
    ],
  },
];
