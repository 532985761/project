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
      {
        path: "goodsDetail",
        name: "goodsDetail",
        component: () => import("@/views/user-goodsDetail/index.vue"),
      },
      {
        path: "usertransfer",
        name: "usertransfer",
        component: () => import("@/views/user-transfer/index.vue"),
      },
      {
        path: "intoware",
        name: "intoware",
        component: () => import("@/views/user-intoware/index.vue"),
      },
      {
        path: "outware",
        name: "outware",
        component: () => import("@/views/user-transfer/index.vue"),
      }, {
        path: "rent",
        name: "userrent",
        component: () => import("@/views/user-rent/index.vue"),
      },
    ]
  },

];
