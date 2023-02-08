<script setup lang="ts">
interface PropType {
	title?: string;
}
withDefaults(defineProps<PropType>(), {
	title: '标题'
});
// const content = ref({} as HTMLElement);
const isClosed = ref(false);
// const height = ref('auto' as number | string);
// const observer = ref({} as ResizeObserver);
// onMounted(() => {
// 	observer.value = new ResizeObserver((entries: ResizeObserverEntry[]) => {
// 		console.log('🦃🦃e', entries);
// 		height.value = entries[0].contentRect.height + 'px';
// 		// 	entries[0].contentRect.height &&
// 		// 		(height.value = entries[0].contentRect.height + 'px');
// 	});
// 	observer.value.observe(content.value);
// });
</script>
<template>
	<div class="simple-playlist-container">
		<div class="title" @click="isClosed = !isClosed">
			<span>{{ title }}</span>
			<i-material-symbols:arrow-drop-down-rounded
				v-show="isClosed"
				class="option-icon" />
			<i-material-symbols:arrow-drop-up-rounded
				v-show="!isClosed"
				class="option-icon" />
		</div>
		<div
			class="simple-playlist-content"
			:class="{ close: isClosed, open: !isClosed }">
			<slot name="content"><span class="entity">暂无内容</span></slot>
		</div>
	</div>
</template>
<style lang="scss" scoped>
.simple-playlist-container {
	margin: 0 10px;
	// height: 100%;
	.title {
		display: flex;
		align-items: center;
		padding-top: 10px;
		font-size: 14px;
		font-weight: 500;
		.option-icon {
			width: 16px;
			height: 16px;
			color: rgb(136, 135, 135);
		}
	}
	.simple-playlist-content {
		transition: all 0.2s ease-in-out;
		overflow: overlay;
		&::-webkit-scrollbar {
			width: 5px;
			height: 8px;
			background-color: var(--el-color-info-light-9);
			// background-color: transparent;
		}
		&::-webkit-scrollbar-thumb {
			background-color: transparent;
		}
		.entity {
			font-size: 12px;
			color: rgb(158, 158, 158);
		}
	}
	.simple-playlist-content:hover {
		&::-webkit-scrollbar-thumb {
			background-color: var(--el-color-primary-light-5);
		}
	}
	.close {
		max-height: 0;
	}
	.open {
		// max-height: calc(100vh - 600px);
	}
}
</style>
