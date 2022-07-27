<script setup lang="ts">
import * as echarts from 'echarts';
import { onMounted, ref } from 'vue';

const pie = ref(null);
onMounted(() => {
	const element = echarts.init(pie.value as unknown as HTMLElement);
	// 设置数据
	const data = [
		{
			value: 67,
			name: '美食',
			itemStyle: {
				color: 'skyblue'
			}
		},
		{
			value: 31,
			name: '日化',
			color: 'pink',
			itemStyle: {
				color: 'pink'
			}
		},
		{
			value: 64,
			name: '数码',
			color: 'yellowgreen',
			itemStyle: {
				color: 'yellowgreen'
			}
		},
		{
			value: 87,
			name: '动漫',
			itemStyle: {
				color: 'red'
			}
		},
	]
	// 配置对象
	const option = {
		title: {
			text: '饼状图'
		},
		legend: {
			right: 'right',
			// 图例的朝向
			origin: 'verical'
		},
		series: [
			{
				name: '销量统计',
				type: 'pie',
				data,
				// 设置饼图的半径，第一项内半径，第二项外半径
				radius: ['40%', '70%'],
				// 设置饼状图标签
				label: {
					show: true,
					position: 'center' // 默认：outside 外部展示，inside 内部展示，center 圈内展示
				},
				// 是否设置成南丁格尔图
				roseType: 'area',
				itemStyle: {
					color: 'pink',
					shadowBlur: 10,
					shadowColor: 'rgba(0, 0, 0, 0.5)'
				}
			}
		]
	}
	element.setOption(option);
});
</script>
<template>
	<div id="pie" ref="pie"></div>
</template>
<style lang="scss" scoped>
#pie {
	width: 400px;
	height: 300px;
}
</style>
