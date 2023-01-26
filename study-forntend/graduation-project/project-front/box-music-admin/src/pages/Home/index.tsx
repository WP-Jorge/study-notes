import React from 'react';
import './index.scss';

import EchardsCard, { MyChartOption } from '@/components/content/EchartsCard';
export default function Home() {
	const option = {
		title: {
			text: '折线图',
			subtext: '折线图-单条'
		},
		xAxis: {
			type: 'category',
			data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
		},
		yAxis: {
			type: 'value'
		},
		series: [
			{
				data: [150, 230, 224, 218, 135, 147, 260],
				type: 'line'
			}
		]
	} as MyChartOption;
	return (
		<div className="home">
			<EchardsCard option={option} width="350px" height="300px" />
			<EchardsCard option={option} width="350px" height="300px" />
			<EchardsCard option={option} width="350px" height="300px" />
			<EchardsCard option={option} width="350px" height="300px" />
			<EchardsCard option={option} width="350px" height="300px" />
		</div>
	);
}
