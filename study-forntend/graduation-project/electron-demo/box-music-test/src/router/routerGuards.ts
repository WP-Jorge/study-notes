import { useUserStore } from '@/store/user';
import { RouteLocationNormalized, NavigationGuardNext } from 'vue-router';
import { ElMessage } from 'element-plus';

const checkPath: Array<string> = ['/admin'];

export const checkAuth = (to: RouteLocationNormalized, from: RouteLocationNormalized, next: NavigationGuardNext) => {
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
};
