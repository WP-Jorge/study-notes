<script setup lang="ts">
import { useSystemStore } from '@/store/system';
import { storeToRefs } from 'pinia';
import { useRouter } from 'vue-router';

const router = useRouter();
const systemStore = useSystemStore();
const { menuList } = storeToRefs(systemStore);
const menuClick = (index: number) => {
	systemStore.parentMenuIndex = index;
};
</script>
<template>
	<div class="aside">
		<el-menu
			class="el-menu-vertical-demo"
			:default-active="router.currentRoute.value.path"
			router>
			<el-menu-item-group
				v-for="(parent, index) of menuList"
				:key="parent.path"
				:title="parent.title">
				<el-menu-item
					v-for="child of parent.children"
					:key="child.path"
					:index="child.path"
					@click="menuClick(index)">
					<el-icon><i-ant-design-heart-outlined /></el-icon>
					<span>{{ child.label }}</span>
				</el-menu-item>
			</el-menu-item-group>
		</el-menu>
	</div>
</template>
<style lang="scss" scoped>
.aside {
	width: 100%;
}
</style>
