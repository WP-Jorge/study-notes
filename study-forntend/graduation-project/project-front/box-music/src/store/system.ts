import { GlobalValues } from '@/globals/GlobalValues';
import { defineStore } from 'pinia';

const ipcRenderer = window.ipcRenderer;
const electronAPI = window.electronAPI;

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
			showMusicDetail: false
		};
	},
	/**
	 * 类似组件的 computed，用来封装计算属性，具有缓存功能
	 */
	getters: {},
	/**
	 * 类似组件的 methods，封装业务逻辑，修改 state
	 */
	actions: {
		async optionChange(optionType: string) {
			ipcRenderer.send(optionType);
		},
		async changeIsMax(flag: boolean) {
			this.$patch(() => {
				this.isMax = flag;
			});
		},
		async changeIsFullscreen(flag: boolean) {
			this.$patch(() => {
				this.isFullscreen = flag;
			});
		},
		async setShowMusicDetail(flag: boolean) {
			this.showMusicDetail = flag;
		}
	}
});

// 2.使用容器中的state

// 3.修改state

// 4.使用 action

// 监听主进程消息
electronAPI.on(GlobalValues.WINDOW_MAX, (msg: any) => {
	const systemStore = useSystemStore();
	systemStore.changeIsMax(msg);
});
electronAPI.on(GlobalValues.WINDOW_SCREEN, (msg: any) => {
	const systemStore = useSystemStore();
	systemStore.changeIsFullscreen(msg);
});
