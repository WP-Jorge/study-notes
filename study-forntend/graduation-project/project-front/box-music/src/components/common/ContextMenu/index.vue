<script setup lang="ts">
import { useContextMenuStore } from '@/store/contextMenu';
const menuStore = useContextMenuStore();
const contextMenu = ref(null);
onMounted(() => {
	document.addEventListener('click', e => {
		if (
			!(contextMenu.value as unknown as HTMLElement).contains(
				e.target as unknown as Node
			)
		)
			menuStore.options.visible = false;
	});
	menuStore.contextMenu = contextMenu as unknown as HTMLElement;
});
</script>
<template>
	<teleport to="body">
		<div
			ref="contextMenu"
			class="context-menu"
			:style="{
				top: `${menuStore.options.offsetTop}px`,
				left: `${menuStore.options.offsetLeft}px`,
				visibility: menuStore.options.visible ? 'visible' : 'hidden'
			}">
			<div
				class="container"
				:style="{
					visibility: menuStore.options.visible ? 'visible' : 'hidden'
				}">
				<li
					v-for="(item, index) of menuStore.contextMenuList"
					:key="index"
					:class="{ item: true, disable: !!item.disabled, hide: !!item.hide }"
					@click="
						item.callback && item.callback();
						menuStore.options.visible = false;
					">
					{{ item.title }}
				</li>
			</div>
		</div>
	</teleport>
</template>
<style lang="scss" scoped>
.context-menu {
	top: 100px;
	left: 500px;
	position: absolute;
	z-index: 10;
	width: 250px;
	max-height: 500px;
	.container {
		display: flex;
		flex-direction: column;
		padding: 10px 0;
		width: 100%;
		height: 100%;
		list-style: none;
		border-radius: 5px;
		box-shadow: 0px 0px 10px rgba($color: #000000, $alpha: 0.3);
		background-color: white;
		.item {
			padding: 10px;
			cursor: pointer;
			font-size: 14px;
			&:hover {
				background-color: #eaeaea;
			}
		}
		.disable {
			cursor: not-allowed;
		}
		.hide {
			display: none;
		}
	}
}
</style>
