import { createRouter, createWebHashHistory } from 'vue-router';

const routes = [
	{
		name: 'home',
		path: '/home',
		component: () => import('../views/Home')
	},
	{
		name: 'about',
		path: '/about',
		component: () => import('../views/About')
	}
];

const router = createRouter({
	history: createWebHashHistory(),
	routes
});

export default router;
