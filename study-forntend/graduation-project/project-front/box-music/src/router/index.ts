import { createRouter, createWebHashHistory, RouteRecordRaw } from 'vue-router';
import { checkAuth } from './routerGuards';
import { setRouter } from './routerHooks';

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
		path: '/favorite',
		name: 'Favorite',
		component: () => import('@/pages/myMusic/Favorite/index.vue')
	},
	{
		path: '/myPlaylist',
		name: 'MyPlaylist',
		component: () => import('@/pages/myMusic/MyPlaylist/index.vue')
	},
	{
		path: '/localMusic',
		name: 'LocalMusic',
		component: () => import('@/pages/localMusic/LocalMusic/index.vue')
	},
	{
		path: '/finished',
		name: 'Finished',
		component: () => import('@/pages/localMusic/Finished/index.vue')
	},
	{
		path: '/downloading',
		name: 'Downloading',
		component: () => import('@/pages/localMusic/Downloading/index.vue')
	},
	{
		path: '/singerDescription',
		name: 'SingerDescription',
		component: () => import('@/pages/common/SingerDescription/index.vue')
	},
	{
		path: '/albumDescription',
		name: 'AlbumDescription',
		component: () => import('@/pages/common/AlbumDescription/index.vue')
	},
	{
		path: '/playlistDescription',
		name: 'PlaylistDescription',
		component: () => import('@/pages/common/PlaylistDescription/index.vue')
	},
	{
		path: '/personalCenter',
		name: 'PersonalCenter',
		component: () => import('@/pages/personalCenter/PersonalCenter/index.vue')
	}
];

const router = createRouter({
	history: createWebHashHistory(),
	routes
});

router.beforeEach(checkAuth);
router.afterEach(setRouter);

export default router;
