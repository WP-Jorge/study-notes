import { createRouter, createWebHistory, createWebHashHistory, RouteRecordRaw, RouteLocationNormalized, NavigationGuardNext } from 'vue-router';
import { checkAuth } from './routerGuards';

const routes: Array<RouteRecordRaw> = [
	{
		path: '/',
		redirect: '/home'
	},
	{
		path: '/home',
		name: 'Home',
		component: () => import('@/views/home/index.vue')
	},
	{
		path: '/admin',
		name: 'Admin',
		component: () => import('@/views/admin/index.vue')
	},
	{
		path: '/login',
		name: 'Login',
		component: () => import('@/views/login/index.vue')
	}
];

const router = createRouter({
	history: process.env.IS_ELEVTRON ? createWebHashHistory() : createWebHistory(process.env.BASE_URL),
	routes
});

router.beforeEach(checkAuth);

export default router;
