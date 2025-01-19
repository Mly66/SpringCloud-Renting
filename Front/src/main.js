import Vue from 'vue'
import App from './App.vue'

// UI组件
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

// 请求
import service from "@/utils/request"
// 路由
import VueRouter from "vue-router";
// 路由配置
import router from "./router";
// 挂载路由
Vue.use(VueRouter)
// 挂在UI组件
Vue.use(ElementUI);

Vue.prototype.request = service;


Vue.config.productionTip = false

new Vue({
  render: h => h(App),
  router:router,
}).$mount('#app')
