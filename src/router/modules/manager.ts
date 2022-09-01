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
      {
        // 商品管理
        path: "goods",
        name: 'goods',
        component: () => import("@/views/manager-goods/index.vue"),
      },
      {
        // 用户管理
        path: "user",
        name: 'user',
        component: () => import("@/views/manager-goods/index.vue"),
      },
      {
        // 澡堂位置管理
        path: "locate",
        name: 'locate',
        component: () => import("@/views/manager-locate/index.vue"),
      },
      {
        // 澡堂储物柜管理
        path: "store",
        name: 'store',
        component: () => import("@/views/manager-store/index.vue"),
      },
    ],
  },
];
