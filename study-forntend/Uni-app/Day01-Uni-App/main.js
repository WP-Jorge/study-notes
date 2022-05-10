import Vue from 'vue'
import App from './App'

Vue.config.productionTip = false

// 使用Vue.prototype.name注册Vue全局属性、方法等，可在Vue实例中使用this.name进行引用
Vue.prototype.baseUrl = "地址"

App.mpType = 'app'

const app = new Vue({
    ...App
})
app.$mount()
