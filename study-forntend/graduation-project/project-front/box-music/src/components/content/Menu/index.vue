<script setup lang="ts">
import { ref } from 'vue';

const activeIndex = ref('1');
const handleSelect = (key: string, keyPath: string[]) => {
	console.log(key, keyPath);
};
</script>
<template>
	<el-menu
		:default-active="activeIndex"
		class="menu"
		mode="horizontal"
		@select="handleSelect">
		<el-menu-item index="1">Processing Center</el-menu-item>
		<el-menu-item index="2">Info</el-menu-item>
		<el-menu-item index="3">Orders</el-menu-item>
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
