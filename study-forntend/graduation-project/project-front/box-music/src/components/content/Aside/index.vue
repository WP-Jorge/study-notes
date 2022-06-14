<script setup lang="ts"></script>
<template>
	<div class="aside">
		<el-menu default-active="2" class="el-menu-vertical-demo">
			<el-menu-item-group title="Group One">
				<el-menu-item index="1-1">
					<el-icon><i-ant-design-heart-outlined /></el-icon>
					<span>Navigator Three</span>
				</el-menu-item>
				<el-menu-item index="1-2">item one</el-menu-item>
			</el-menu-item-group>
			<el-menu-item-group title="Group Two">
				<el-menu-item index="1-3">item three</el-menu-item>
			</el-menu-item-group>
			<el-menu-item index="1-4-1">item one</el-menu-item>
			<el-menu-item index="2">
				<el-icon><i-ant-design-heart-outlined /></el-icon>
				<span>Navigator Two</span>
			</el-menu-item>
			<el-menu-item index="3">
				<span>Navigator Three</span>
			</el-menu-item>
			<el-menu-item index="4">
				<el-icon><i-ant-design-heart-outlined /></el-icon>
				<span>Navigator Four</span>
			</el-menu-item>
		</el-menu>
	</div>
</template>
<style lang="scss" scoped>
.aside {
	width: 100%;
	height: 100%;

	.el-menu {
		height: 100%;
	}
}
</style>
