import { useSystemStore } from '@/store/system';
import { storeToRefs } from 'pinia';

export const setRouter = () => {
	const systemStore = useSystemStore();
	const { routerNum, routerPos } = storeToRefs(systemStore);
	routerNum.value = history.length;
	routerPos.value = history.state.position;
};
