import { createRouter, createWebHashHistory, createWebHistory, RouteRecordRaw } from 'vue-router';

const routes: Array<RouteRecordRaw> = [
	{
		path: '/',
		name: 'Home',
		component: () => import('@/views/Home.vue')
	},
	{
		path: '/about',
		name: 'About',
		component: () => import('@/views/About.vue')
	}
];

const router = createRouter({
	history: process.env.IS_ELECTRON ? createWebHashHistory() : createWebHistory(process.env.BASE_URL),
	routes
});

export default router;
