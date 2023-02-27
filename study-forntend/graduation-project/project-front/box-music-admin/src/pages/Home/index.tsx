import React, { useEffect, useState } from 'react';
import './index.scss';

import EchartsCard, { MyChartOption } from '@/components/content/EchartsCard';
import {
	getCategoryMusicCountsApi,
	getMusicLevelCountsApi,
	getRecentlyUserCountsApi
} from '@/networks/charts';
export default function Home() {
	const [userCountOption, setUserCountOption] = useState({
		title: {
			text: '最近新增用户数',
			subtext: '单位：人'
		},
		tooltip: {
			trigger: 'axis',
			axisPointer: {
				type: 'cross',
				label: {
					backgroundColor: '#6a7985'
				}
			}
		},
		xAxis: {
			type: 'category',
			data: [],
			axisLabel: {
				interval: 0
			}
		},
		yAxis: {
			type: 'value'
		},
		series: [
			{
				data: [],
				type: 'line'
			}
		]
	} as MyChartOption);
	const [musicLevelOption, setMusicLevelOption] = useState({
		title: {
			text: '音乐质量'
		},
		legend: {
			orient: 'vertical',
			x: 'right',
			icon: 'circle',
			textStyle: {
				rich: {
					a: {
						fontSize: 16,
						color: '#EA5504',
						padding: 10
					},
					b: {
						fontSize: 14,
						color: '#333'
					}
				}
			}
		},
		series: [
			{
				data: [],
				type: 'pie',
				label: {
					show: true,
					formatter: function (args) {
						return `音质：${args.name}，数量：${args.value}`;
					}
				},
				radius: ['30%', '60%']
			}
		]
	} as MyChartOption);
	const [categoryMusicOption, setCategoryMusicOption] = useState({
		title: {
			text: '分类音乐数',
			subtext: '单位：首'
		},
		tooltip: {
			trigger: 'axis',
			axisPointer: {
				type: 'cross',
				label: {
					backgroundColor: '#6a7985'
				}
			}
		},
		xAxis: {
			type: 'category',
			data: [],
			axisLabel: {
				interval: 0,
				rotate: 90
			}
		},
		yAxis: {
			type: 'value'
		},
		series: [
			{
				data: [],
				type: 'bar'
			}
		]
	} as MyChartOption);
	useEffect(() => {
		getRecentlyUserCounts();
		getMusicLevelCounts();
		getCategoryMusicCounts();
	}, []);
	async function getRecentlyUserCounts() {
		let res = await getRecentlyUserCountsApi();
		setUserCountOption({
			xAxis: {
				data: res.data.userCountList
					.map((item: any) => item.day.slice(0, 10).replaceAll('-', '.'))
					.reverse()
			},
			series: [
				{
					data: res.data.userCountList.map((item: any) => item.num).reverse()
				}
			]
		});
	}
	async function getMusicLevelCounts() {
		let res = await getMusicLevelCountsApi();
		setMusicLevelOption({
			series: [
				{
					data: res.data.musicLevelCounts.map((item: any) => {
						return {
							name: item.level,
							value: item.num
						};
					})
				}
			]
		});
	}
	async function getCategoryMusicCounts() {
		let res = await getCategoryMusicCountsApi();
		setCategoryMusicOption({
			xAxis: {
				data: res.data.categoryMusicCounts.map((item: any) => item.categoryName)
			},
			series: [
				{
					data: res.data.categoryMusicCounts.map((item: any) => item.num)
				}
			]
		});
	}
	return (
		<div className="home">
			<EchartsCard option={userCountOption} width="45%" height="300px" />
			<EchartsCard option={musicLevelOption} width="45%" height="300px" />
			<EchartsCard option={categoryMusicOption} width="100%" height="300px" />
		</div>
	);
}
