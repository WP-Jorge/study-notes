import { sleep } from '@/utils/baseUtil';
import { createSlice } from '@reduxjs/toolkit';
import { AppDispatch } from '../store';
import { UserInfo, Api, Role } from '@/networks/system';

export interface InitialState {
	userInfo: UserInfo;
	token: string;
	apis: Array<Api>;
	roles: Array<Role>;
}

const localStorageUser: InitialState = JSON.parse(
	localStorage.getItem('user') ?? '{}'
);

const initialState: InitialState = {
	userInfo: localStorageUser.userInfo ?? {},
	token: localStorageUser.token ?? '',
	apis: localStorageUser.apis ?? [],
	roles: localStorageUser.roles ?? []
};

const userSlice = createSlice({
	name: 'user',
	initialState,
	// reducers 存放同步 action
	reducers: {
		setUserInfo(state, action) {
			const userInfo = action.payload;
			state.userInfo = userInfo;
			let user = JSON.stringify({
				...JSON.parse(localStorage.getItem('user') as string),
				userInfo
			});
			localStorage.setItem('user', user);
		},
		login(state, action) {
			const { userInfo, token, roles, apis } = action.payload;
			state.userInfo = userInfo;
			state.token = token;
			state.roles = roles;
			state.apis = apis;
			localStorage.setItem(
				'user',
				JSON.stringify({
					userInfo,
					token,
					roles,
					apis
				})
			);
		},
		logout(state) {
			state.userInfo = {};
			state.token = '';
			state.roles = [];
			state.apis = [];
			localStorage.clear();
		}
	}
});

// 异步 action 单独定义并导出
export const setUserInfoAsync =
	(userInfo: UserInfo) => async (dispatch: AppDispatch) => {
		await sleep(500);
		dispatch(setUserInfo(userInfo));
	};

export const { login, logout, setUserInfo } = userSlice.actions;

export default userSlice.reducer;
