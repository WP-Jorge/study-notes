import { Music } from '@/networks/music';
import { Singer } from '@/networks/singer';
import { defineStore } from 'pinia';

// 1.定义并导出容器
/**
 * 参数一：容器 id，必须为唯一值，将来 pinia 会把所有的容器挂载到跟容器上
 * 参数二：选项对象
 */

export const useMusicStore = defineStore('music', {
	/**
	 * 类似组件的 data
	 */
	state: () => {
		return {
			musicList: [] as Music[],
			audio: ref(null) as unknown as HTMLAudioElement,
			music: {
				musicId: '',
				musicTitle: '',
				musicPic: '',
				singers: [] as Singer[]
			} as Music,
			play: false,
			currentTime: 0
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
		// setMusic(music: Music) {
		// 	this.music = music;
		// },
		// setPlay(flag: boolean) {
		// 	this.play = flag;
		// },
		// setCurrentTime(time: number) {
		// 	this.currentTime = time;
		// }
	}
});

// 2.使用容器中的state

// 3.修改state

// 4.使用 action
