const state = {
	userInfo: []
};
const mutations = {
	setStateItem(state: any, data: any) {
		state[data.key] = data.value;
	}
};

const actions = {};

export default {
	state,
	actions,
	mutations,
	namespaced: true
};
