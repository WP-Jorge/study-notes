import { createRouter, createWebHashHistory, RouteRecordRaw } from 'vue-router';
import { checkAuth } from './routerGuards';

const routes: Array<RouteRecordRaw> = [
	{
		path: '/',
		redirect: '/recommend'
	},
	{
		path: '/recommend',
		name: 'Recommend',
		component: () => import('@/pages/discovery/Recommend/index.vue')
	},
	{
		path: '/category',
		name: 'Category',
		component: () => import('@/pages/discovery/Category/index.vue')
	},
	{
		path: '/chart',
		name: 'Chart',
		component: () => import('@/pages/discovery/Chart/index.vue')
	},
	{
		path: '/recentlyAdded',
		name: 'RecentlyAdded',
		component: () => import('@/pages/discovery/RecentlyAdded/index.vue')
	},
	{
		path: '/recentlyPlay',
		name: 'RecentlyPlay',
		component: () => import('@/pages/myMusic/RecentlyPlay/index.vue')
	},
	{
		path: '/music',
		name: 'Music',
		component: () => import('@/pages/myMusic/Music/index.vue')
	},
	{
		path: '/singer',
		name: 'Singer',
		component: () => import('@/pages/myMusic/Singer/index.vue')
	},
	{
		path: '/finished',
		name: 'Finished',
		component: () => import('@/pages/download/Finished/index.vue')
	},
	{
		path: '/downloading',
		name: 'Downloading',
		component: () => import('@/pages/download/Downloading/index.vue')
	},
	{
		path: '/favorite',
		name: 'Favorite',
		component: () => import('@/pages/playlist/Favorite/index.vue')
	},
	{
		path: '/myPlaylist',
		name: 'MyPlaylist',
		component: () => import('@/pages/playlist/MyPlaylist/index.vue')
	}
];

const router = createRouter({
	history: createWebHashHistory(),
	routes
});

router.beforeEach(checkAuth);

export default router;
