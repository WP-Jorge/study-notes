import { defineStore } from 'pinia';

import { UserInfo, Api, Role, UserLoginResponse } from '@/networks/user';

// 1.定义并导出容器
/**
 * 参数一：容器 id，必须为唯一值，将来 pinia 会把所有的容器挂载到跟容器上
 * 参数二：选项对象
 */
export const useUserStore = defineStore('user', {
	/**
	 * 类似组件的 data
	 */
	state: () => {
		return {
			userInfo: {} as UserInfo,
			token: '',
			apis: [] as Array<Api>,
			roles: [] as Array<Role>
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
		async login(userLoginResponse: UserLoginResponse) {
			this.$patch(() => {
				this.token = userLoginResponse.token;
				this.roles = userLoginResponse.roles;
				this.userInfo = userLoginResponse.userInfo;
				this.apis = userLoginResponse.apis;
			});
			localStorage.setItem(
				'user',
				JSON.stringify({
					userInfo: this.userInfo,
					apis: this.apis,
					roles: this.roles,
					token: this.token
				})
			);
		},
		async logout() {
			this.$patch(() => {
				this.token = '';
				this.roles = [];
				this.userInfo = {};
				this.apis = [];
			});
			localStorage.clear();
		}
	}
});

// 2.使用容器中的state

// 3.修改state

// 4.使用 action
