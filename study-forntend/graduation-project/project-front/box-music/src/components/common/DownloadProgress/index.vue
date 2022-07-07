<script setup lang="ts">
import { Music } from '@/networks/music';
import { PropType } from 'vue';
const props = defineProps({
	downloadMusic: {
		type: Object as PropType<Music>,
		required: true
	}
});

const downloadState = computed(() => {
	if (!props.downloadMusic.downloadItemInfo) {
		return;
	}
	if (props.downloadMusic.downloadItemInfo.progress) {
		let received =
			(
				props.downloadMusic.downloadItemInfo.receivedBytes /
				1024 /
				1024
			).toFixed(1) + 'MB';
		let total =
			(props.downloadMusic.downloadItemInfo.totalBytes / 1024 / 1024).toFixed(
				1
			) + 'MB';
		return `${received} / ${total}`;
	}
	return '0.0MB / 0.0MB';
});
const speed = () => {
	if (!props.downloadMusic.downloadItemInfo) {
		return;
	}
	if (props.downloadMusic.downloadItemInfo.state === 'progressing') {
		return (
			(props.downloadMusic.downloadItemInfo.speed / 1024 / 1024).toFixed(2) +
			'MB/s'
		);
	}
	if (
		props.downloadMusic.downloadItemInfo.state === 'interrupted' ||
		props.downloadMusic.downloadItemInfo.state === 'paused'
	) {
		return '已暂停';
	}
	if (props.downloadMusic.downloadItemInfo.state === 'cancelled') {
		return '已取消';
	}
	if (props.downloadMusic.downloadItemInfo.state === 'completed') {
		return '下载完成，等待写入音乐信息';
	}
	if (props.downloadMusic.downloadItemInfo.state === 'writing') {
		return '正在写入音乐信息';
	}
	if (props.downloadMusic.downloadItemInfo.state === 'writed') {
		return '完成';
	}
};
</script>
<template>
	<div class="download-progress">
		<template v-if="downloadMusic.downloadItemInfo?.state !== 'download-wait'">
			<el-progress
				:stroke-width="10"
				:percentage="
					(downloadMusic.downloadItemInfo?.progress
						? downloadMusic.downloadItemInfo.progress
						: 0) * 100
				"
				:format="speed" />
			<span class="download-state ellipse">
				{{ downloadState }}
			</span>
		</template>
		<span v-else class="download-state ellipse">等待中</span>
	</div>
</template>
<style lang="scss" scoped>
.download-progress {
	min-width: 400px;
	.el-progress {
		:deep(.el-progress__text) {
			display: flex;
			align-items: center;
			span {
				display: block;
				width: 120px;
				font-size: 10px;
				overflow: hidden;
				text-overflow: ellipsis;
				white-space: nowrap;
			}
		}
	}

	.download-state {
		color: #919191;
		font-size: 12px;
	}
}
</style>
