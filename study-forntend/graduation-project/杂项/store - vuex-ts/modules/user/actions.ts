import { mutationsTypes } from '@/store/modules/user/mutationsTypes';

export default {
	[mutationsTypes.SET_TOKEN](state: any, payload: string) {
		state.commit(mutationsTypes.SET_TOKEN, payload);
	}
};
