<script setup lang="ts">
export type ChartType = {
	id: string;
	type: string;
};
import { useMusicStore } from '@/store/music';
const musicStore = useMusicStore();

const chartTypes = reactive([
	{
		id: 'music',
		type: '音乐榜'
	},
	{
		id: 'singer',
		type: '歌手榜'
	},
	{
		id: 'album',
		type: '专辑榜'
	}
]);

const itemClick = (chartType: ChartType) => {
	musicStore.chartType = chartType;
};

musicStore.chartType = chartTypes[0];
</script>
<template>
	<div class="chart-selector-card">
		<SimpleContainer>
			<template #title>榜单分类</template>
			<template #content>
				<div class="selector-container">
					<span
						v-for="item of chartTypes"
						:key="item.id"
						:class="{
							'selector-item': true,
							active: item === musicStore.chartType
						}"
						@click="itemClick(item)">
						{{ item.type }}
					</span>
				</div>
			</template>
		</SimpleContainer>
	</div>
</template>
<style lang="scss" scoped>
.chart-selector-card {
	.selector-container {
		.selector-item {
			display: inline-block;
			margin: 10px;
			cursor: pointer;

			&:hover {
				color: var(--el-color-info-dark-2);
			}
		}
		.active {
			// font-weight: bold;
			color: var(--el-color-primary);
		}
	}
}
</style>
