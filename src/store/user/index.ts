import { defineStore } from "pinia"
import { Names } from '../store_name'
import { useCookies } from '@vueuse/integrations/useCookies'

const key = "pinia-user"

export const userStore = defineStore(Names.user, {
    state: () => {
        return {
            test: useCookies().get(key) != null ? useCookies().get(key).test : "",
            test2: useCookies().get(key) != null ? useCookies().get(key).test2 : "",
            // 用户信息
            info: useCookies().get(key) != null ? useCookies().get(key).info : "",
            // 用户token
            token: useCookies().get(key) != null ? useCookies().get(key).token : "",
            // 导航栏默认页
            navActive: useCookies().get(key) != null ? useCookies().get(key).navActive : "",
        }
    },
    getters: {

    },
    actions: {
        // 存储用户信息
        setInfo(info : any){
            this.info = info
        },
        // 存储用户token
        setToken(tokenHeader:any, token:any){
            this.token = tokenHeader + ' ' + token
        },
        // 退出登录
        logout() {
            this.info = ""
            this.token = ""
        }
    }
})
