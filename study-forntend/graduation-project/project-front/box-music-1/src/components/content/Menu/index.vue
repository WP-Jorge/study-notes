<script setup lang="ts">
import { useRouter } from 'vue-router';
import { useSystemStore } from '@/store/system';
const systemStore = useSystemStore();
const router = useRouter();
</script>
<template>
	<el-menu
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
