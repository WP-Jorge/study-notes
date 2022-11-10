<script setup lang="ts">
import { useRouter } from 'vue-router';
import { useSystemStore } from '@/store/system';
const systemStore = useSystemStore();
const router = useRouter();
const paths = computed(() => {
	return systemStore.parentMenu.children?.map(
		item => item.path
	) as unknown as string[];
});
// watchEffect(() => {
// 	if (router.currentRoute.value.path === '/') {
// 		// systemStore.parentMenuIndex = 0;
// 	}
// 	console.log('🦃🦃', router.currentRoute.value);
// });
// console.log(
// 	paths.value,
// 	router.currentRoute.value.path,
// 	paths.value?.includes(router.currentRoute.value.path)
// );
</script>
<template>
	<el-menu
		v-show="paths?.includes(router.currentRoute.value.path)"
		class="menu"
		mode="horizontal"
		:default-active="router.currentRoute.value.path"
		router
		:ellipsis="false">
		<el-menu-item
			v-for="item of systemStore.parentMenu.children"
			:key="item.path"
			:index="item.path">
			{{ item.label }}
		</el-menu-item>
	</el-menu>
</template>
<style lang="scss" scoped>
.menu {
	border-bottom: transparent;
	height: 40px;
	.el-menu-item {
		padding: 0 5px;
		margin: 0 15px;
		.is-active {
			border-bottom: 3px solid;
		}
	}
}
</style>
