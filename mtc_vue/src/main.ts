import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import elementPlus from 'element-plus'
import './assets/main.css'
import 'element-plus/dist/index.css'
import 'echarts'
import { ElMessage } from 'element-plus'


import {install} from '@icon-park/vue-next/es/all';





// import axios from "./http";
import axios from "axios";
import VueAxios from "vue-axios";


import * as ElementPlusIconsVue from '@element-plus/icons-vue'
// main.ts


const app = createApp(App)
// Install
install(app); // use default prefix 'icon', eg: icon is People, name is icon-people.
install(app, 'i'); // use custom prefix 'i', eg: icon is People, name is i-people.
import '@icon-park/vue-next/styles/index.css';
axios.defaults.baseURL='/api'

app.use(VueAxios,axios)

//全局挂载axios
app.config.globalProperties.$axios = axios
// 如果您正在使用CDN引入，请删除下面一行。

for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}
import locale from 'element-plus/lib/locale/lang/zh-cn'
// 注意!!!这句必须跟在const app= xxxx  之后
app.use(elementPlus, { locale });

app.use(router).use(elementPlus)

app.mount('#app')


router.beforeEach((to, from, next) => {
    if (to.meta.withoutP) {
        next()
    }else{
        if (sessionStorage.getItem("email")) {
            next()
        }
        else {
            //next({name:'index',query:{msg:"请先登录"}}
            // sessionStorage.setItem("unlogin", "请先登录")
            next({ name: 'Login' })
        }
    }
})
