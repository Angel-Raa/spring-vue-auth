import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import { VueQueryPlugin, type VueQueryPluginOptions } from '@tanstack/vue-query'


const vueQueryOptions: VueQueryPluginOptions = {
  queryClientConfig: {
    defaultOptions: {
      queries: {
        staleTime: 5 * 60 * 1000, // 5 minutos por defecto
        refetchOnWindowFocus: false,
      },
    },
  },
};
const app = createApp(App)
app.use(VueQueryPlugin, vueQueryOptions)
app.use(router)

app.mount('#app')
