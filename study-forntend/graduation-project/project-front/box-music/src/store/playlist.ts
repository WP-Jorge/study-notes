import { Category } from '@/networks/category';
import { defineStore } from 'pinia';

// 1.定义并导出容器
/**
 * 参数一：容器 id，必须为唯一值，将来 pinia 会把所有的容器挂载到跟容器上
 * 参数二：选项对象
 */

export const usePlaylistStore = defineStore('playlist', {
	/**
	 * 类似组件的 data
	 */
	state: () => {
		return {
			category: {} as Category
		};
	},
	/**
	 * 类似组件的 computed，用来封装计算属性，具有缓存功能
	 */
	getters: {},
	/**
	 * 类似组件的 methods，封装业务逻辑，修改 state
	 */
	actions: {}
});

// 2.使用容器中的state

// 3.修改state

// 4.使用 action
