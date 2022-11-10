import { useUserStore } from '@/store/user';
import { RouteLocationNormalized, NavigationGuardNext } from 'vue-router';
import { ElMessage } from 'element-plus';
import { useSystemStore } from '@/store/system';
import { storeToRefs } from 'pinia';

const checkPath: Array<string> = ['/admin'];

export const checkAuth = (
	to: RouteLocationNormalized,
	from: RouteLocationNormalized,
	next: NavigationGuardNext
) => {
	const systemStore = useSystemStore();
	const { routerNum, routerPos } = storeToRefs(systemStore);
	let userStore = useUserStore();
	let path = to.path;
	let token = userStore.token;
	if (checkPath.includes(path)) {
		if (!token) {
			ElMessage.error('请登录！');
			return next({
				path: '/login'
			});
		}
	}
	next();
	routerNum.value = history.length;
	routerPos.value = history.state.position;
};
