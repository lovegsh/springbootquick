import Router from 'vue-router'			// 引入VueRouter
import Vue from 'vue'
import About from '../components/HelloWorld.vue'
import Home from '../components/TheWelcome.vue'
import Im from '../components/Im.vue'

Vue.use(Router)
export default new Router({
    routes:[
        {
            path: 'im',
            name: 'Im',
            component: Im,
        },
    ]
})