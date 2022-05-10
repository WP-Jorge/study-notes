import Vue from 'vue'
// 1.导入路由插件
import Router from 'vue-router'

// 1.1导入懒加载组件
const Home = () => import('../views/home/Home')
const Category = () => import('../views/category/Category')
const Cart = () => import('../views/cart/Cart')
const Profile = () => import('../views/profile/Profile')

// 2.安装路由插件
Vue.use(Router)
// 3.创建理由对象
const routes = [
	{
		path: '',
		redirect: '/home'
	},
	{
		path: '/home',
		component: Home
	},
	{
		path: '/category',
		component: Category
	},
	{
		path: '/cart',
		component: Cart
	},
	{
		path: '/profile',
		component: Profile
	}
	
]

const router = new Router({
	routes,
	// 从默认hash模式修改成history模式
	mode: 'history'
})

// 4。导出路由对象
export default router