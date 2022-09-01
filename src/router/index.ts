import { createRouter,createWebHashHistory,RouteRecordRaw } from 'vue-router'
import { user } from './modules/user'
import { manager } from './modules/manager'
import { start, close } from '@/utils/nprogress';
import { userStore } from "@/store/user";

const whiteNames = [
  "/managerLogin",
  "/businessLogin",
  "/userLogin",
  "/",
  "/404"
]

const startRoutes: Array<RouteRecordRaw> = [
  { path: '/', component: () => import('@/views/Home.vue') },
  { path: '/404', component: () => import('@/views/404.vue') }
]

const endRoutes: Array<RouteRecordRaw> = [
  { path: '/:pathMatch(.*)', redirect:'/404' }
]

const routes = startRoutes.concat(user).concat(manager).concat(endRoutes)

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

router.beforeEach(async(to, from, next) => {
  start()
  // 白名单通过
  
  // 更新菜单项
  userStore().navActive = to.path
  next()
})

router.afterEach(async(to, from, next) => {
  close()
})


export default router

