import { createRouter, createWebHashHistory, RouteRecordRaw } from 'vue-router';
import { checkAuth } from './routerGuards';

const routes: Array<RouteRecordRaw> = [
	{
		path: '/',
		redirect: '/home'
	},
	{
		path: '/home',
		name: 'Home',
		component: () => import('@/pages/Home/index.vue')
	},
	{
		path: '/admin',
		name: 'Admin',
		component: () => import('@/pages/Admin/index.vue')
	},
	{
		path: '/login',
		name: 'Login',
		component: () => import('@/pages/Login/index.vue')
	}
];

const router = createRouter({
	history: createWebHashHistory(),
	routes
});

router.beforeEach(checkAuth);

export default router;
