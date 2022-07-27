<script setup lang="ts">
import * as echarts from 'echarts';
import { onMounted, ref } from 'vue';
const myEcharts = ref(null);
onMounted(() => {
	let element = echarts.init(myEcharts.value as unknown as HTMLElement);
	element.setOption({
		title: {
			text: '基础配置',
			link: 'https://www.baidu.com',
			target: '_brank',
			backgroundColor: 'pink',
			borderColor: 'yellowgreen',
			borderWidth: 5,
			padding: 5,
			x: 'left',
			y: 'top',
			textStyle: {
				color: 'yellow',
				fontSize: 14
			},
			subtext: '柱状图',
			sublink: 'https://www.baidu.com',
			subtextStyle: {
				color: 'green',
				fontSize: 12
			}
		},
		tooltip: {
			// 触发类型
			trigger: 'axis', // item 默认，图形触发；axis：坐标轴触发
			// 坐标轴指示器
			axisPointer: {
				type: 'shadow'// line 默认，显示一根实线；shadow：阴影效果；cross：十字准星
			},
			// 悬浮窗
			showContent: true, // true 默认，显示悬浮窗
			backgroundColor: 'pink',
			borderWidth: 5,
			textStyle: {
				color: 'red'
			},
			// 自定义悬浮窗信息
			formatter(params: any) {
				return `价格：${params[0].data.value}，日期：${params[0].data.date}`
			}
		},
		legend: {
			show: true, // 默认 true，设置图例的开启与关闭
			// icon: 'circle', // 圆形
			right: 0,
			// 图例宽高
			itemWidth: 20,
			itemHeight: 10,
			textStyle: {
				color: 'blue'
			}
		},
		xAxis: {
			data: ['毛衣', '裤子', '鞋子', '外套']
		},
		yAxis: {},
		grid: {
			show: true,
			top: '20%',
			right: '20%',
			bottom: '20%',
			left: '20%',
			backgroundColor: 'pink',
			borderWidth: 5,
			borderColor: 'skyblue'
		},
		series: {
			name: '服装',
			type: 'bar',
			data: [{ value: 100, date: '2022-01-03' }, { value: 50, date: '2022-03-22' }, { value: 70, date: '2022-03-12' }, { value: 200, date: '2022-03-02' }]
		}
	});
});
</script>

<template>
	<div ref="myEcharts" id="myDiv">
	</div>
</template>

<style scoped lang="scss">
#myDiv {
	width: 400px;
	height: 300px;
}
</style>
