import { createApp, toRaw } from 'vue'
import { createPinia, PiniaPluginContext } from 'pinia'
import { useCookies } from '@vueuse/integrations/useCookies'
import router from './router'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import locale from 'element-plus/lib/locale/lang/zh-cn'
import App from './App.vue'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'


const app = createApp(App)

// Windi CSS
import 'virtual:windi.css'
// ElementPlusIcons
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}
// Pinia
const store = createPinia()
const cookie = useCookies()
const __piniaKey__:string = "grouptwo"
type Options = {
    key?:string
}
const piniaPlugin = (options:Options) => {
    return (context: PiniaPluginContext) => {
        const {store} = context
        store.$subscribe(() => {
            cookie.set(`${options?.key ?? __piniaKey__}-${store.$id}`, toRaw(store.$state))
        })
    }
}
store.use(piniaPlugin({
    key: "pinia"
}))
app.use(store)
// Element-Plus
app.use(ElementPlus, {locale})
// 路由
app.use(router)

app.mount('#app')
