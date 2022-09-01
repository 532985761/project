import { defineStore } from "pinia"
import { Names } from '../store_name'
import { useCookies } from '@vueuse/integrations/useCookies'

const key = "pinia-goods"

export const goodsStore = defineStore(Names.goods, {
    state: () => {
        return {
            aaa: useCookies().get(key) != null ? useCookies().get(key).test : null,
            test2: useCookies().get(key) != null ? useCookies().get(key).test2 : null,
        }
    },
    getters: {

    },
    actions: {
        
    }
})