import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui'
import locale from 'element-ui/lib/locale/lang/ja'
import 'element-ui/lib/theme-chalk/index.css'
import '@/assets/global.css'
import axios from 'axios'
import 'default-passive-events'
import store from './store'

Vue.config.productionTip = false
Vue.use(ElementUI, { locale })
Vue.prototype.$axios = axios

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
