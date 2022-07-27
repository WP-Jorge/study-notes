<script setup lang="ts">
import * as echarts from 'echarts';
import { onMounted, ref } from 'vue';
const elementItem = ref(null as unknown as HTMLElement);
onMounted(() => {
	let element = echarts.init(elementItem.value as unknown as HTMLElement);
	element.setOption({
		title: {
			text: 'Dataset'
		},
		xAxis: {
			type: 'category',
		},
		yAxis: {},
		series: {
			name: '日常',
			type: 'bar',
			encode: {
				x: '类别',
				y: '数量'
			},
			datasetIndex: 1
		},
		dataset: [
			{
				// 数据的分类
				dimensions: ['类别', '数量'],
				source: [
					['动漫', 119],
					['美食', 109],
					['旅游', 99],
					['音乐', 88],
					['摄影', 129],
					['数码', 82],
					['骑行', 97],
				]
			},
			{
				transform: {
					type: 'sort',
					config: {
						dimension: '数量',
						order: 'desc'
					}
				}
			}
		]
	});
});
</script>

<template>
	<div ref="elementItem" id="container">
	</div>
</template>

<style scoped lang="scss">
#container {
	width: 400px;
	height: 300px;
}
</style>
