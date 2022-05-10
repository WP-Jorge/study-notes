import { UserLoginRequest } from '@/networks/user/types';
import { mutationsTypes } from '@/store/modules/user/mutationsTypes';

export default {
	[mutationsTypes.LOGIN](state: any, userLoginInfo: UserLoginRequest) {
		state.userLoginInfo = userLoginInfo;
		localStorage.setItem('userLoginInfo', JSON.stringify(userLoginInfo));
	},
	[mutationsTypes.LOGOUT](state: any) {
		state.userLoginInfo = {};
		localStorage.removeItem('userLoginInfo');
	}
};
