<script setup lang="ts">
import * as echarts from 'echarts';
import { onMounted, ref } from 'vue';
const line = ref(null);
onMounted(() => {
	const element = echarts.init(line.value as unknown as HTMLElement);
	const xData = ['Mon', 'Tue', 'Wed', 'Thu', 'Fir', 'Sat', 'Sun'];
	const dataA = [121, 221, 234, 147, 160, 234, 134];
	const dataB = [101, 161, 204, 139, 151, 234, 124];
	const dataC = [131, 91, 134, 67, 110, 234, 104];
	const dataD = [81, 71, 96, 167, 120, 134, 224];
	const option = {
		title: {
			text: '折线图',
			subtext: '折线图-多条'
		},
		legend: {},
		xAxis: {
			type: 'category',
			data: xData
		},
		yAxis: {
		},
		series: [
			{
				name: '动漫',
				type: 'line',
				data: dataA,
				// 同类型的数据需要匹配相同的stack属性
				stack: 'num',
				areaStyle: {},
				// 选中高亮状态
				emphasis: {
					focus: 'series' // series：聚焦当前的高亮item
				}
			},
			{
				name: '美食',
				type: 'line',
				data: dataB,
				stack: 'num',
				areaStyle: {},
				emphasis: {
					focus: 'series'
				}
			},
			{
				name: '旅游',
				type: 'line',
				data: dataC,
				stack: 'num',
				areaStyle: {},
				emphasis: {
					focus: 'series'
				}
			},
			{
				name: '音乐',
				type: 'line',
				data: dataD,
				stack: 'num',
				areaStyle: {},
				emphasis: {
					focus: 'series'
				}
			}
		]
	};
	element.setOption(option);
});

</script>
<template>
	<div ref="line" id="line"></div>
</template>
<style lang="scss" scoped>
#line {
	width: 400px;
	height: 300px;
}
</style>
