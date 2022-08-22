import { MessageType } from '@/globals/GlobalValues';
import { defineStore } from 'pinia';
import router from '@/router';
const ipcRenderer = window.ipcRenderer;
export interface Menu {
	title?: string;
	label?: string;
	path?: string;
	children?: Menu[];
}

const menuList = [
	{
		title: '探索发现',
		path: '/discovery',
		children: [
			{
				label: '个性推荐',
				path: '/recommend'
			},
			{
				label: '音乐分类',
				path: '/category'
			},
			{
				label: '榜单',
				path: '/chart'
			},
			{
				label: '最近新增',
				path: '/recentlyAdded'
			}
		]
	},
	{
		title: '我的音乐',
		path: '/myMusic',
		children: [
			{
				label: '最近播放',
				path: '/recentlyPlay'
			},
			{
				label: '歌曲',
				path: '/music'
			},
			{
				label: '歌手',
				path: '/singer'
			}
		]
	},
	{
		title: '本地音乐',
		path: '/localMusic',
		children: [
			{
				label: '本地音乐',
				path: '/localMusic'
			},
			{
				label: '已完成',
				path: '/finished'
			},
			{
				label: '正在下载',
				path: '/downloading'
			}
		]
	},
	{
		title: '歌单',
		path: '/playlist',
		children: [
			{
				label: '我的喜欢',
				path: '/favorite'
			},
			{
				label: '我的歌单',
				path: '/myPlaylist'
			}
		]
	}
] as Menu[];

// 1.定义并导出容器
/**
 * 参数一：容器 id，必须为唯一值，将来 pinia 会把所有的容器挂载到跟容器上
 * 参数二：选项对象
 */
export const useSystemStore = defineStore('system', {
	/**
	 * 类似组件的 data
	 */
	state: () => {
		return {
			isMax:
				window.outerHeight === screen.availHeight &&
				window.outerWidth === screen.availWidth,
			isFullscreen: false,
			showSiderMenu: true,
			showMain: true,
			showMusicDetail: false,
			menuList,
			parentMenuIndex: JSON.parse(
				localStorage.getItem('parentMenuIndex') ?? '0'
			)
		};
	},
	/**
	 * 类似组件的 computed，用来封装计算属性，具有缓存功能
	 */
	getters: {
		menu: state => {
			const getCurrentMenu = (
				menuList: Menu[],
				targetPath: string
			): Menu | null => {
				for (const menu of menuList) {
					if (menu.path === targetPath) {
						return menu;
					}
					if (menu.children) {
						return getCurrentMenu(menu.children, targetPath);
					}
				}
				return null;
			};
			return getCurrentMenu(state.menuList, router.currentRoute.value.path);
		},
		parentMenu: state => {
			return state.menuList[state.parentMenuIndex];
		}
	},
	/**
	 * 类似组件的 methods，封装业务逻辑，修改 state
	 */
	actions: {
		async optionChange(optionType: MessageType) {
			ipcRenderer.send(optionType);
		}
	}
});

// 2.使用容器中的state

// 3.修改state

// 4.使用 action

// 监听主进程消息
// electronAPI.on(GlobalValues.WINDOW_MAX, (msg: any) => {
// 	const systemStore = useSystemStore();
// 	systemStore.isMax = msg;
// });
// electronAPI.on(GlobalValues.WINDOW_SCREEN, (msg: any) => {
// 	const systemStore = useSystemStore();
// 	systemStore.isFullscreen = msg;
// });

ipcRenderer.on(MessageType.WINDOW_MAX, (...msgs: any) => {
	const systemStore = useSystemStore();
	systemStore.isMax = msgs[1];
});
ipcRenderer.on(MessageType.WINDOW_SCREEN, (...msgs: any) => {
	const systemStore = useSystemStore();
	systemStore.isFullscreen = msgs[1];
});
