<script setup lang="ts">
import { inject, onMounted, ref } from 'vue';
import { echartsKey } from '../type/type';
const echarts = inject(echartsKey);
const line = ref(null);
onMounted(() => {
	const element = echarts?.init(line.value as unknown as HTMLElement);
	const xData = ['Mon', 'Tue', 'Wed', 'Thu', 'Fir', 'Sat', 'Sun'];
	const data = [121, 321, 234, 67, 100, 234, 124]
	const option = {
		title: {
			text: '动画',
		},
		// 动画
		animation: true, // 开启动画，默认开启
		animationThreshold: 7, // 单个系列显示的图形数量大于这个数时会关闭动画
		animationDuration: 1000, // 动画时长
		animationDelay: 1000, // 动画延迟
		animationEasing: 'backIn', // 动过过度曲线
		xAxis: {
			type: 'category',
			data: xData
		},
		yAxis: {
		},
		series: [
			{
				name: '数值',
				data,
				type: 'line',
				smooth: true,
				// 区域样式
				areaStyle: {},
				markPoint: {
					data: [
						{ type: 'min', name: '最小值' },
						{ type: 'max', name: '最大值' }
					]
				},
				markLine: {
					data: [
						{ type: 'average', name: '平均值' }
					]
				}
			}
		]
	};
	element?.setOption(option as any);
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
