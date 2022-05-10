import { createStore } from 'vuex';
import user, { IUserState } from '@/store/modules/user/';

// export interface IRootState {
// 	[x: string]: any;
// }

export interface IModules {
	user: IUserState;
}

export default createStore<IModules>({
	modules: {
		user
	}
});
