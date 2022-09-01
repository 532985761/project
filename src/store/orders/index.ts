import { defineStore } from "pinia"
import { Names } from '../store_name'
import { useCookies } from '@vueuse/integrations/useCookies'

const key = "pinia-orders"

export const ordersStore = defineStore(Names.orders, {
    state: () => {
        return {
            test: useCookies().get(key) != null ? useCookies().get(key).test : null,
            test2: useCookies().get(key) != null ? useCookies().get(key).test2 : null
        }
    },
    getters: {

    },
    actions: {
        
    }
})