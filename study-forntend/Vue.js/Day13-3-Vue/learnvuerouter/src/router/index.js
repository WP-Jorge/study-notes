// 配置路由信息

// 1.导入相关工具
import VueRouter from 'vue-router'
import Vue from 'vue'

// 2.通过Vue.use(插件)，安装插件
Vue.use(VueRouter)

// 3.创建Vuerouter路由对象
// 3.1导入组件
// import Home from '../components/Home.vue'
// import About from '../components/About.vue'
// import User from '../components/User.vue'

// 3.2懒加载导入组件
const Home = () => import('../components/Home')
const HomeNews = () => import('../components/HomeNews')
const HomeMessages = () => import('../components/HomeMessages')
const About = () => import('../components/About')
const User = () => import('../components/User')
const Profile = () =>import('../components/Profile')


const routes = [
	{
		path: '',
		// redirect重定向，当path为''空时，重定向path到'/home'
		redirect: '/home'
	},
	{
		// 每个path对应一个组件
		path: '/home',
		component: Home,
		// meta元数据(描述数据的数据)
		meta: {
			title: '首页'
		},
		children: [
			// {
			// 	// 重定向
			// 	path: '',
			// 	redirect: 'news'
			// },
			{
				// 组件嵌套 子组件
				// 子组件path 不用添加/
				path: 'news',
				component: HomeNews
			},
			{
				path: 'messages',
				component: HomeMessages
			}
		]
	},
	{
		path: '/about',
		component: About,
		meta: {
			title: '关于'
		},
		// 路由独享守卫beforeEnter
		beforeEnter: ((to, from, next) => {
			// console.log('欢迎进入关于界面')
			next()
		})
	},
	{
		// 这里的id可以随便取
		path: '/user/:id',
		component: User,
		meta: {
			title: '用户'
		},
	},
	{
		path: '/profile',
		component: Profile,
		meta: {
			title: '档案'
		},
	}
]
const router = new VueRouter({
	// 配置路由和组件间的应用关系
	routes,
	// 模式
	mode: 'history',
	// 指定选中样式
	linkActiveClass: 'active'
})

// 全局守卫：前置守卫、后置钩子
// 前置守卫(guard)
router.beforeEach((to, from, next) => {
	// 切换路由时切换title
	// 从from到to
	document.title = to.matched[0].meta.title
	// console.log(to)
	// 必须调用next(),不然所有路由都将失效
	next()
})

// 后置钩子(hook)
router.afterEach((to, from) => {
	
})

// 4.将router对象传入到Vue实例中
export default router 

// 这个离开组件守卫通常用来禁止用户在还未保存修改前突然离开。该导航可以通过 next(false) 来取消。放在组件里面
// beforeRouteLeave (to, from, next) {
//   const answer = window.confirm('Do you really want to leave? you have unsaved changes!')
//   if (answer) {
//     next()
//   } else {
//     next(false)
//   }
// }


// import Vue from 'vue'
// import Router from 'vue-router'
// import HelloWorld from '@/components/HelloWorld'

// Vue.use(Router)

// export default new Router({
//   routes: [
//     {
//       path: '/',
//       name: 'HelloWorld',
//       component: HelloWorld
//     }
//   ]
// })
