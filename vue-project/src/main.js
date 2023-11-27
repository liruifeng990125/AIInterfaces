// 引入Vue
import Vue from 'vue'
// 引入App
import App from './App.vue'
// 引入elemetn-ui组件库
import ElementUI from 'element-ui';
// 引入element-ui全部css
import 'element-ui/lib/theme-chalk/index.css';
// 关闭Vue的生产提示
Vue.config.productionTip=false
// 使用element
Vue.use(ElementUI)
// 创建vm 
//快捷注释 shift + alt +a 
/* const vm=new Vue({
    el:'#app',
    render:h=>h(App),  
}) */
new Vue({
    el: "#app",
    data() {
        return {
            username: "hello"
        }
    }
});


