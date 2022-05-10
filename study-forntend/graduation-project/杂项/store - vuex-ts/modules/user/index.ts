import { Module } from 'vuex';
import { IModules } from '@/store';
import mutations from '@/store/modules/user/mutations';
import actions from '@/store/modules/user/actions';
import { Api, Role, UserInfo } from '@/networks/user/types';

export interface UserLoginInfo {
	userInfo?: UserInfo;
	token?: string;
	apis?: Array<Api>;
	roles?: Array<Role>;
}

export interface IUserState {
	userLoginInfo: UserLoginInfo;
}

let userLoginInfo = localStorage.getItem('userInfo') ? JSON.parse(localStorage.getItem('userInfo') as unknown as string) : {};

const state: IUserState = {
	userLoginInfo
};

export const user: Module<IUserState, IModules> = {
	namespaced: true,
	state,
	actions,
	mutations
};

export default user;
